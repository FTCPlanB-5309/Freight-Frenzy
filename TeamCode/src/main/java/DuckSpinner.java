
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

    public void blue(){
        robot.duckSpinner.setTargetPosition(robot.BLUE_DUCK_POSITION);
        robot.duckSpinner.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.duckSpinner.setPower(robot.DUCK_SPINNER_SPEED);
        while (robot.duckSpinner.isBusy()){
            Thread.yield();
        }
        robot.duckSpinner.setPower(0);
    }
    public void red() {
        robot.duckSpinner.setTargetPosition(robot.RED_DUCK_POSITION);
        robot.duckSpinner.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.duckSpinner.setPower(robot.DUCK_SPINNER_SPEED);
        while (robot.duckSpinner.isBusy()) {
            Thread.yield();
        }
        robot.duckSpinner.setPower(0);
    }
    public void timed(int mils) throws InterruptedException {
        robot.duckSpinner.setPower(robot.DUCK_SPINNER_SPEED);
        Thread.sleep(mils);
        robot.duckSpinner.setPower(0);
    }
}