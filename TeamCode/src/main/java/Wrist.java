import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Wrist {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    int floorPosition;
    int bottomPosition;
    int middlePosition;
    int topPosition;

    public Wrist(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void floor() throws InterruptedException {
        robot.wristServo.setPosition(floorPosition);
        Thread.sleep(1000);
    }

    public void bottom() throws InterruptedException {
        robot.wristServo.setPosition(bottomPosition);
        Thread.sleep(1000);
    }

    public void middle() throws InterruptedException {
        robot.wristServo.setPosition(middlePosition);
        Thread.sleep(1000);
    }

    public void top() throws InterruptedException {
        robot.wristServo.setPosition(topPosition);
        Thread.sleep(1000);
    }
}
