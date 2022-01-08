
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DuckSpinner {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public DuckSpinner(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void spin(Color alliance) throws InterruptedException {
        if (alliance == Color.red)
            robot.duckSpinner.setPower(-0.4);
        else
            robot.duckSpinner.setPower(0.4);
        robot.leftFrontDrive.setPower(-.1);
        robot.rightFrontDrive.setPower(-.1);
        robot.rightRearDrive.setPower(-.1);
        robot.leftRearDrive.setPower(-.1);
        for (int i=0; linearOpMode.opModeIsActive() && i < 30; i++) {
            Thread.sleep(100);
        }
        robot.stop();
        robot.duckSpinner.setPower(0);
    }
}