
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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

    public void spin(AllianceColor alliance) throws InterruptedException {
        if (alliance == AllianceColor.red)
            robot.duckSpinner.setPower(-0.65);
        else
            robot.duckSpinner.setPower(0.65);
        for (int i=0; linearOpMode.opModeIsActive() && i < 30; i++) {
            Thread.sleep(100);
        }

        robot.duckSpinner.setPower(0);
    }
}