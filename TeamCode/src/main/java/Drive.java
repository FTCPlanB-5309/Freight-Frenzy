import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive {

    RobotHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public Drive(RobotHardware robot, Telemetry telemetry, LinearOpMode linearOpMode) {
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void forward1000(double power){
        robot.leftDrive.setTargetPosition(1000);
        robot.rightDrive.setTargetPosition(1000);

        robot.driveToPosition();

        robot.leftDrive.setPower(power);
        robot.rightDrive.setPower(power);

        while(linearOpMode.opModeIsActive() && robot.leftDrive.getCurrentPosition() < 1000){
            Thread.yield();
            telemetry.addData("left drive encoder",robot.leftDrive.getCurrentPosition());
            telemetry.update();
        }
        robot.stop();
    }
}