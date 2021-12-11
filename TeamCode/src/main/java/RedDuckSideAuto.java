import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "RedDuckSideAuto")

public class RedDuckSideAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Wrist wrist = new Wrist(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this, wrist);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    DuckSpinner duckspinner = new DuckSpinner(robot, telemetry,this);

    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        arm.setPosition(robot.ARM_MIDDLE_POSITION);
        mast.setPosition(robot.MAST_FORWARD_POSITION);
        drive.forward(0.10, 32 );

        FreightLevel level = findTeamFreight.getLevel();
        if (level == FreightLevel.level1)
        arm.setHeight(robot.LEVEL_ONE_HEIGHT);

        if (level == FreightLevel.level2)
            arm.setHeight(robot.LEVEL_TWO_HEIGHT);

        if (level == FreightLevel.level3)
            arm.setHeight(robot.LEVEL_THREE_HEIGHT);

        mast.setPosition(robot.MAST_RIGHT_POSITION);

        strafe.right(.15, 2);
        claw.open();
        strafe.left(0.25, 31);
        wrist.setPosition(robot.WRIST_FLOOR_POSITION);
        arm.setPosition(robot.ARM_FLOOR_POSITION);
        drive.backward(0.25, 27);
        duckspinner.spin(AllianceColor.red);
        drive.forward(.25,17);

        telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
        telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
        telemetry.addData("wrist", robot.wristServo.getPosition());
        telemetry.addData("Claw Distance in CM", robot.clawDistanceSensor.getDistance(DistanceUnit.CM));
        telemetry.update();
        //drive.forward1000(.5);

        /*
        find out where the special marker is (1 out of 3)
        move forward
        turn left 90
        Move arm to specific level (based on the marker position)
        move forward
        drop block
        move backward
        move arm back
        extend the "duck wheel" out
        spin wheel
        bring arm back
        make sure to StOp!11!!!!!11!111!!!
         */
    }
}