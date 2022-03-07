import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Disabled
@Autonomous(name = "RedDuckWarehouse")

public class RedDuckWarhouse extends LinearOpMode {
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
        mast.setPositionNoWait(robot.MAST_CENTER_LEFT_POSITION);
        drive.backward(.4, 43);
        int levelHeight = findTeamFreight.getLevel(SetupDirection.backward);

        //logic to use arm encoder value if claw distance is too high.
        if(robot.clawDistanceSensor.getDistance(DistanceUnit.CM) > robot.LEVEL_THREE_HEIGHT) {

            if (levelHeight == robot.LEVEL_ONE_HEIGHT) {
                arm.setPosition(robot.ARM_BOTTOM_POSITION);
                mast.setPosition(robot.MAST_LEFT_POSITION);
                strafe.left(.5, 5);
                claw.open();
            }else if (levelHeight == robot.LEVEL_THREE_HEIGHT) {
                arm.setPosition(robot.ARM_TOP_POSITION);
                mast.setPosition(robot.MAST_LEFT_POSITION);
                strafe.left(.5, 5);
                claw.open();
            } else {
                mast.setPosition(robot.MAST_LEFT_POSITION);
                strafe.left(.5, 5);
                claw.open();
            }
        } else {
            arm.setHeight(levelHeight);
            mast.setPosition(robot.MAST_LEFT_POSITION);
            strafe.left(.5, 5);
            gyroturn.goodEnough(0);
            claw.open();
        }

        // Driving to the duck turn table
        strafe.right(0.5,25);
        gyroturn.goodEnough(0);
        if(levelHeight == robot.LEVEL_ONE_HEIGHT)
            arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        long distanceToWall;
        int distance;
        distanceToWall = Math.round(robot.frontDistanceSensor.getDistance(DistanceUnit.INCH));
        distance = (int) distanceToWall - 5;
        if (Math.abs(distance)>60)
            drive.forward(0.5,39 );
        else
            drive.forward(.5, distance);

        gyroturn.goodEnough(0);
        distanceToWall = Math.round(robot.rightDistanceSensor.getDistance(DistanceUnit.INCH));
        distance = (int) distanceToWall - 11;
        if (Math.abs(distance)>10)
            strafe.right(0.2,5 );
        else
            strafe.right(.2, distance);
        gyroturn.goodEnough(90);
        distanceToWall = Math.round(robot.rightDistanceSensor.getDistance(DistanceUnit.INCH));
        distance = (int)(distanceToWall - 5);
        if (Math.abs(distance)>10)
            strafe.right(0.2,1 );
        else
            strafe.right(.2, distance);
        gyroturn.goodEnough(90);
        drive.backward(0.25,1);

        // Spin the turn table to drop the duck
        duckWings.open(Color.red);
        mast.setPositionNoWait(robot.MAST_CENTER_LEFT_POSITION);
        arm.setPositionNoWait(robot.ARM_FLOOR_POSITION);
        duckspinner.spin(Color.red,SetupDirection.backward);

        // Grabbing the duck
        gyroturn.goodEnough(90);
        strafe.left(0.5,20);
        duckWings.close(Color.red);
        gyroturn.goodEnough(0);
        claw.openWide();
        distanceToWall = (int) Math.round(robot.frontDistanceSensor.getDistance(DistanceUnit.INCH));
        distance = (int) distanceToWall - 7;
        mast.setPosition(robot.MAST_FORWARD_POSITION);
        if (Math.abs(distance)>15)
            drive.forward(0.2,10 );
        else
            drive.forward(.2, distance);

        //Grab the duck
        claw.close();

        // Driving to the alliance hub to score the duck
        arm.setPositionNoWait(robot.ARM_TOP_POSITION);
        mast.setPositionNoWait(robot.MAST_LEFT_POSITION);
        drive.backward(.5, 37);
        strafe.left(.5, (int)(27));
        claw.open();

        // Park in Warehouse

        if (levelHeight == robot.LEVEL_THREE_HEIGHT) {
            strafe.right(.5, 4);
        } else {
            strafe.right(.5, 3);

        }
        distanceToWall = Math.round(robot.frontDistanceSensor.getDistance(DistanceUnit.INCH));
        distance = (int) distanceToWall - 5;
        if (Math.abs(distance)>60)
            drive.forward(0.5,41 );
        else
            drive.forward(.5, distance);
        gyroturn.goodEnough(90);
        distanceToWall = Math.round(robot.rightDistanceSensor.getDistance(DistanceUnit.INCH));
        distance = (int) distanceToWall - 0;
        if (Math.abs(distance)>10)
            strafe.right(0.5,10 );
        else
            strafe.right(.5, distance);
//        strafe.right(.3,4);

        drive.forward(.75,71);

    }
}