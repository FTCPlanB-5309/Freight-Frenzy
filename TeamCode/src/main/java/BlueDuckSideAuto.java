import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "BlueDuckSideAuto")

public class BlueDuckSideAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    DuckSpinner duckspinner = new DuckSpinner(robot, telemetry,this);
    DriveToLine driveToLine = new DriveToLine(robot,telemetry,this);

    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        arm.setPosition(robot.ARM_MIDDLE_POSITION);
        mast.setPosition(robot.MAST_FORWARD_POSITION);
        drive.forward(0.10, 32 );

        int levelHeight = findTeamFreight.getLevel(SetupDirection.forward);
        arm.setHeight(levelHeight);
        mast.setPosition(robot.MAST_LEFT_POSITION);

        strafe.left(.15, 4);
        claw.open();
        strafe.right(0.25, 8);
        mast.setPosition(robot.MAST_LEFT_POSITION);
        arm.setPosition(robot.ARM_FLOOR_POSITION);
        drive.backward(0.25, 16);
        strafe.right(.25, 25);
        drive.backward(.25, 9);
        duckspinner.spin(Color.blue, SetupDirection.forward);
        drive.forward(.25, 7);
        driveToLine.forward(25, .25, Color.blue);
        drive.backward(.25, 3);

        telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
        telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
        telemetry.addData("Claw Distance in CM", robot.clawDistanceSensor.getDistance(DistanceUnit.CM));
        telemetry.update();
    }
}