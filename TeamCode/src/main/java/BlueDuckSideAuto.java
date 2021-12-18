import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "BlueDuckSideAuto")

public class BlueDuckSideAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Wrist wrist = new Wrist(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this, wrist);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    DuckSpinner duckspinner = new DuckSpinner(robot, telemetry,this);
    GyroTurn gyroTurn = new GyroTurn(robot, telemetry, this);

    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        wrist.setPosition(robot.WRIST_FLOOR_POSITION);
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

        mast.setPosition(robot.MAST_LEFT_POSITION);

        strafe.left(.15, 2);
        claw.open();
        strafe.right(0.25, 31);
        mast.setPosition(robot.MAST_LEFT_POSITION);
        arm.setPosition(robot.ARM_FLOOR_POSITION);
        drive.backward(0.25, 27);
        duckspinner.spin(Color.blue);
        drive.forward(.25,19);

        telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
        telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
        telemetry.addData("wrist", robot.wristServo.getPosition());
        telemetry.addData("Claw Distance in CM", robot.clawDistanceSensor.getDistance(DistanceUnit.CM));
        telemetry.update();
    }
}