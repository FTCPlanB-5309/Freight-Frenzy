import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
        robot.mastRotator.setPower(0.1);

    }

    public void right() {
        robot.mastRotator.setTargetPosition(rightPosition);
        robot.mastRotator.setPower(0.1);

    }

    public void forward() {
        robot.mastRotator.setTargetPosition(forwardPosition);
        robot.mastRotator.setPower(0.1);

    }

}
