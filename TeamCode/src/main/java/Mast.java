import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Mast {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;



    public Mast(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void setPosition(int newPosition) {
        if (!linearOpMode.opModeIsActive())
            return;
        robot.mastRotator.setTargetPosition(newPosition);
        robot.mastRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mastRotator.setPower(0.1);
        while (robot.armMotor.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            return;
        }
        robot.mastRotator.setPower(0);
    }
}
