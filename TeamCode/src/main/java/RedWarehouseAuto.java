import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Disabled
@Autonomous(name = "RedWarehouseAuto")

public class RedWarehouseAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    DuckSpinner duckspinner = new DuckSpinner(robot, telemetry, this);
    GyroTurn gyroTurn = new GyroTurn(robot,telemetry, this);

    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        arm.setPosition(robot.ARM_MIDDLE_POSITION);
        mast.setPosition(robot.MAST_FORWARD_POSITION);
        drive.forward(0.10, 2);
        strafe.right(.25, 5);
        drive.forward(.10, 30);

        int levelHeight = findTeamFreight.getLevel(SetupDirection.forward);
        arm.setHeight(levelHeight);
        mast.setPosition(robot.MAST_LEFT_POSITION);
        strafe.left(.15, 2);
        claw.open();
        strafe.right(0.25, 2);
        mast.setPosition(robot.MAST_FORWARD_POSITION);
        drive.backward(0.25, 28);
        arm.setPosition(robot.ARM_MIDDLE_POSITION);
        gyroTurn.absolute(-90);
        strafe.right(.25,4);
        drive.forward(.24,26);
        arm.setPosition(robot.ARM_FLOOR_POSITION);

        telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
        telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
        telemetry.update();
        gyroTurn.updateHeading();
    }
}
