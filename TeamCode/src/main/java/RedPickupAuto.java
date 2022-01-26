import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "RedPickupAuto")

public class RedPickupAuto extends LinearOpMode {
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
        drive.backward(.4, 41);
        int levelHeight = findTeamFreight.getLevel(SetupDirection.backward);
        arm.setHeight(levelHeight);
        mast.setPosition(robot.MAST_LEFT_POSITION);
        strafe.left(.5, 5);
        claw.open();


        // Driving to the duck turn table
        strafe.right(0.5,24);
        drive.forward(0.5,36);
        gyroturn.goodEnough(90);
        drive.backward(0.25,2);
        int distanceToWall = (int) ((robot.frontDistanceSensor.getDistance(DistanceUnit.CM) - 29) / 2.54);
        int distance = (distanceToWall -12) + 12;
        if (Math.abs(distance)>10)
            strafe.right(0.25,7 );
        else
            strafe.right(.25, distance);


        // Spin the turn table to drop the duck
        duckWings.open(Color.red);
        duckspinner.spin(Color.red,SetupDirection.backward);


        // Grabbing the duck
        strafe.left(0.5,20);
        duckWings.close(Color.red);
        mast.setPositionNoWait(robot.MAST_CENTER_LEFT_POSITION);
        arm.setPositionNoWait(robot.ARM_FLOOR_POSITION);
        gyroturn.goodEnough(0);
        distanceToWall = (int) ((robot.frontDistanceSensor.getDistance(DistanceUnit.CM) - 29) / 2.54);
        mast.setPositionNoWait(robot.MAST_FORWARD_POSITION);
        claw.openWide();
        drive.forward(0.2,distanceToWall);
        claw.close();

        // Driving to the alliance hub to score the duck
        arm.setPositionNoWait(robot.ARM_TOP_POSITION);
        mast.setPositionNoWait(robot.MAST_LEFT_POSITION);
        drive.backward(.5, 35);
        strafe.left(.5, (int)(27));
        claw.open();


        // Park in Storage Facility
        arm.setPositionNoWait(robot.ARM_FLOOR_POSITION);
        strafe.right(.5,34);
        drive.forward(.5,12);
        driveToLine.forward(25, .5, Color.red);
        drive.backward(.5, 6);
        //add some logic in case of crazy distance sensor values

        

//        mast.setPosition(robot.MAST_RIGHT_POSITION);
//        arm.setPosition(robot.ARM_FLOOR_POSITION);

    }
}