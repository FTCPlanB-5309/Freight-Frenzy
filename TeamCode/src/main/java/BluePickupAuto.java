import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Autonomous(name = "BluePickupAuto")

public class BluePickupAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    DuckSpinner duckspinner = new DuckSpinner(robot, telemetry,this);
    GyroTurn gyroturn = new GyroTurn(robot,telemetry,this);
    DuckWings duckWings = new DuckWings(robot,telemetry,this);
    DriveToLine driveToLine = new DriveToLine(robot,telemetry,this);
    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();


        // Delivering the autonomous freight item.
        arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        mast.setPositionNoWait(robot.MAST_CENTER_RIGHT_POSITION);
        drive.backward(.4, 43);
        int levelHeight = findTeamFreight.getLevel(SetupDirection.backward);

        //Use arm encoder value if claw distance is too high.
        if(robot.getAverageDistance(robot.clawDistanceSensor, DistanceUnit.CM) > robot.LEVEL_THREE_HEIGHT) {

            if (levelHeight == robot.LEVEL_ONE_HEIGHT) {
                strafe.left(.5, 7);
                arm.setPosition(robot.ARM_BOTTOM_POSITION);
                mast.setPosition(robot.MAST_RIGHT_POSITION);
                strafe.right(.5, 12);
                claw.open();
            }else if (levelHeight == robot.LEVEL_THREE_HEIGHT) {
                arm.setPosition(robot.ARM_TOP_POSITION);
                mast.setPosition(robot.MAST_RIGHT_POSITION);
                strafe.right(.5, 5);
                claw.open();
            } else {
                mast.setPosition(robot.MAST_RIGHT_POSITION);
                strafe.right(.5, 5);
                claw.open();
            }
        } else {
            if (levelHeight == (robot.LEVEL_ONE_HEIGHT)) {
                strafe.left(.5,7);
                arm.setHeight(levelHeight);
                mast.setPosition(robot.MAST_RIGHT_POSITION);
                strafe.right(.5, 12);
                claw.open();
            } else {
                arm.setHeight(levelHeight);
                mast.setPosition(robot.MAST_RIGHT_POSITION);
                strafe.right(.5, 5);
                claw.open();
            }
        }


        // Driving to the duck turn table
        strafe.left(0.5,23);
        gyroturn.goodEnough(0);
        long distanceToWall;
        int distance;
        distanceToWall = robot.getAverageDistance(robot.frontDistanceSensor, DistanceUnit.INCH);
        distance = (int) distanceToWall - 5;
        if (Math.abs(distance)>60)
            drive.forward(0.5,38);
        else
            drive.forward(0.5, distance);

        gyroturn.goodEnough(0);
        distanceToWall = robot.getAverageDistance(robot.leftDistanceSensor, DistanceUnit.INCH);
        distance = (int) distanceToWall - 9;
        if (Math.abs(distance)>10)
            strafe.left(0.2,7);
        else
            strafe.left(.2, distance);
        gyroturn.goodEnough(-90);
        distanceToWall = robot.getAverageDistance(robot.leftDistanceSensor, DistanceUnit.INCH);
        distance = (int)(distanceToWall - 4);
        if (Math.abs(distance)>10)
            strafe.left(0.2,4 );
        else
            strafe.left(.2, distance);

        //Double check for correct distance to the turntable
        distanceToWall = robot.getAverageDistance(robot.leftDistanceSensor, DistanceUnit.INCH);
        distance = (int)(distanceToWall - 4);
        if (Math.abs(distance)>1 && (Math.abs(distance) < 10))
            strafe.left(.2, distance);

        drive.backward(0.25,1);


        // Spin the turn table to drop the duck
        duckWings.open(Color.blue);
        mast.setPositionNoWait(robot.MAST_CENTER_LEFT_POSITION);
        arm.setPositionNoWait(robot.ARM_FLOOR_POSITION);
        duckspinner.spin(Color.blue, SetupDirection.backward);


        // Grabbing the duck
        gyroturn.goodEnough(-90);
        strafe.right(0.5,20);
        duckWings.close(Color.blue);
        gyroturn.goodEnough(0);
        distanceToWall = (int) robot.getAverageDistance(robot.frontDistanceSensor, DistanceUnit.INCH);
        distance = (int) distanceToWall - 8;
        claw.openWide();
        mast.setPosition(robot.MAST_FORWARD_POSITION);
        if (Math.abs(distance)>15)
            drive.forward(0.2,11 );
        else
            drive.forward(.2, distance);

        //Grab the duck
        claw.close();

        // Driving to the alliance hub to score the duck
        arm.setPositionNoWait(robot.ARM_TOP_POSITION);
        mast.setPositionNoWait(robot.MAST_RIGHT_POSITION);
        drive.backward(.5, 37);
        strafe.right(.5, (int)(28));
        claw.open();


        // Park in Storage Facility
        strafe.left(.5,31);
        arm.setPositionNoWait(robot.ARM_FLOOR_POSITION);
        drive.forward(.5,12);
        driveToLine.forward(25, .5, Color.blue);
        drive.backward(.5, 7);
        gyroturn.goodEnough(0);
        strafe.left(.2, (int) robot.getAverageDistance(robot.leftDistanceSensor, DistanceUnit.INCH));
        //add some logic in case of crazy distance sensor values

//        mast.setPosition(robot.MAST_RIGHT_POSITION);
//        arm.setPosition(robot.ARM_FLOOR_POSITION);

    }
}