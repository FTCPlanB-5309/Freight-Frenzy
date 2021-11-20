import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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

}
