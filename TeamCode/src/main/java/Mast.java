import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Mast {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    int leftPosition;
    int rightPosition;
    int forwardPosition;

    public Mast(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void left() {
        robot.mastRotator.setTargetPosition(leftPosition);
        robot.mastRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mastRotator.setPower(0.1);
        while (robot.mastRotator.isBusy()) {
            Thread.yield();
        }
        robot.mastRotator.setPower(0);
    }

    public void right() {
        robot.mastRotator.setTargetPosition(rightPosition);
        robot.mastRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mastRotator.setPower(0.1);
        while (robot.mastRotator.isBusy()) {
            Thread.yield();
        }
        robot.mastRotator.setPower(0);
    }

    public void forward() {
        robot.mastRotator.setTargetPosition(forwardPosition);
        robot.mastRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.mastRotator.setPower(0.1);
        while (robot.mastRotator.isBusy()) {
            Thread.yield();
        }
        robot.mastRotator.setPower(0);
    }

}
