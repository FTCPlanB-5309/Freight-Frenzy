import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "ArmTest")

public class ArmTest extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    Wrist wrist = new Wrist(robot, telemetry, this);
    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();










    }
}

