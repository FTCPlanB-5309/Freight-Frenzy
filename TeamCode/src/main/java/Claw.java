
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Claw {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public Claw(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

   public void open(){
       if (!linearOpMode.opModeIsActive())
           return;
        robot.leftClawServo.setPosition(robot.LEFT_CLAW_OPEN);
        robot.rightClawServo.setPosition(robot.RIGHT_CLAW_OPEN);
   }
   public void close(){
       if (!linearOpMode.opModeIsActive())
           return;
        robot.leftClawServo.setPosition(robot.LEFT_CLAW_CLOSED);
        robot.rightClawServo.setPosition(robot.RIGHT_CLAW_CLOSED);
   }
}