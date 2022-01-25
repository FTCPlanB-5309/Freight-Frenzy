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
    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        //TODO compress this to just drive @ .4 and do the nowaits before driving
        // also change mast position to Left Center

        // Delivering the autonomous freight item.
        arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        drive.backward(.5, 25);
        mast.setPositionNoWait(robot.MAST_FORWARD_POSITION);
        drive.backward(.1, 6);
        int levelHeight = findTeamFreight.getLevel(SetupDirection.backward);
        arm.setHeight(levelHeight);
        drive.backward(.5,10);
        mast.setPosition(robot.MAST_LEFT_POSITION);
        strafe.left(.5, 5);
        claw.open();

        // TODO check the distance with the distance sensor before strafing in to the turn table
        // TODO also set a default in case of bad sensor reading

        // Driving to the duck turn table
        strafe.right(0.5,24);
        drive.forward(0.5,36);
        gyroturn.goodEnough(90);
        drive.backward(0.25,3);
        strafe.right(0.25,7 );

        // TODO Deploy the right duck wings

        // Spin the turn table to drop the duck
        duckspinner.spin(Color.red,SetupDirection.backward);

        // TODO retract duck wings and take out strafing for duck

        // Grabbing the duck
        strafe.left(0.5,20);
        mast.setPositionNoWait(robot.MAST_CENTER_LEFT_POSITION);
        arm.setPositionNoWait(robot.ARM_FLOOR_POSITION);
        gyroturn.goodEnough(0);
        strafe.right(.3, 3);
        int distanceToWall = (int) ((robot.frontDistanceSensor.getDistance(DistanceUnit.CM) - 29) / 2.54);
        mast.setPositionNoWait(robot.MAST_CENTER_RIGHT_POSITION);
        claw.close();
        drive.forward(0.2,distanceToWall);
        strafe.left(.25, 13);
        drive.backward(.25, 6);
        claw.openWide();
        mast.setPositionNoWait(robot.MAST_FORWARD_POSITION);
        drive.forward(.25, 6);
        claw.close();

        // Driving to the alliance hub to score the duck
        arm.setPositionNoWait(robot.ARM_TOP_POSITION);
        mast.setPositionNoWait(robot.MAST_LEFT_POSITION);
        drive.backward(.5, 35);
        strafe.left(.5, (int)(18));
        claw.open();

        // TODO Finish navigation and sense the line

        // Park in Storage Facility
        strafe.right(.5,33);
        //add some logic in case of crazy distance sensor values

        while (true) {
            telemetry.addData("Front Distance",robot.frontDistanceSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
            telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
            telemetry.addData("Claw Distance in CM", robot.clawDistanceSensor.getDistance(DistanceUnit.CM));
            telemetry.update();

        }

//        mast.setPosition(robot.MAST_RIGHT_POSITION);
//        arm.setPosition(robot.ARM_FLOOR_POSITION);

    }
}