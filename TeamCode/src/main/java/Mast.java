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
        robot.mastRotator.setPower(0.5);
        while (robot.mastRotator.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
        }
        robot.mastRotator.setPower(0);
    }

    public void setPositionNoWait(int newPosition) {
        if (!linearOpMode.opModeIsActive())
            return;
        robot.mastRotator.setTargetPosition(newPosition);
        robot.mastRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mastRotator.setPower(0.5);
    }
}
