import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DuckWings { MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public DuckWings(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void open(Color color){
        if (!linearOpMode.opModeIsActive())
            return;
        if (color == Color.red){
            robot.frontRightWingServo.setPosition(robot.FRONT_RIGHT_WING_OPEN);
            robot.backRightWingServo.setPosition(robot.BACK_RIGHT_WING_OPEN);
        }
        else{
            robot.frontLeftWingServo.setPosition(robot.FRONT_LEFT_WING_OPEN);
            robot.backLeftWingServo.setPosition(robot.BACK_LEFT_WING_OPEN);
        }
    }

    public void close(Color color){
        if (!linearOpMode.opModeIsActive())
            return;
        if (color == Color.red){
            robot.frontRightWingServo.setPosition(robot.FRONT_RIGHT_WING_CLOSE);
            robot.backRightWingServo.setPosition(robot.BACK_RIGHT_WING_CLOSE);
        }
        else{
            robot.frontLeftWingServo.setPosition(robot.FRONT_LEFT_WING_CLOSE);
            robot.backLeftWingServo.setPosition(robot.BACK_LEFT_WING_CLOSE);
        }
    }
}
