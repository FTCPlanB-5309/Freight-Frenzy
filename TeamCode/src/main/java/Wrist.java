import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Wrist {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;


    public Wrist(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void setPosition(double newPosition) throws InterruptedException {
        if (!linearOpMode.opModeIsActive())
            return;
        robot.wristServo.setPosition(newPosition);
        Thread.sleep(1000);
    }

    public void updatePosition() {
        if (!linearOpMode.opModeIsActive())
            return;
//        double pos = Range.clip(0.606 - (0.00007*robot.armMotor.getCurrentPosition()), 0.1, 0.7);
        double pos = 0.606 + -7E-05*Math.abs(robot.armMotor.getCurrentPosition());
        robot.wristServo.setPosition(pos);
    }
}
