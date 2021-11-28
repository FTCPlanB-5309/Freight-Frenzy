import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;



    public Arm(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void setPosition(int newPosition) {
        robot.armMotor.setTargetPosition(newPosition);
        robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.armMotor.setPower(-0.7);
        while (robot.armMotor.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
        }
        robot.armMotor.setPower(0);
    }
}
