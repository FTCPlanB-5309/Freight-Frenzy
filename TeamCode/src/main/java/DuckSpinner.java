
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
            robot.duckSpinner.setPower(-0.45);
        else
            robot.duckSpinner.setPower(0.45);
        robot.leftFrontDrive.setPower(-.05);
        robot.rightFrontDrive.setPower(-.05);
        robot.rightRearDrive.setPower(-.05);
        robot.leftRearDrive.setPower(-0.05);
        for (int i=0; linearOpMode.opModeIsActive() && i < 30; i++) {
            Thread.sleep(100);
        }
        robot.stop();
        robot.duckSpinner.setPower(0);
    }
}