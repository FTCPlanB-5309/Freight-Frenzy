import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RedWarehouseAutoTest")

public class RedWarehouseAutoTest extends LinearOpMode{
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    GyroTurn gyroturn = new GyroTurn(robot,telemetry,this);
    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        drive.backward(.3, 24);
        drive.backward(.1, 19);
        int levelHeight = findTeamFreight.getLevel(SetupDirection.backward);
        strafe.left(.15,6);
        arm.setHeight(levelHeight);
        mast.setPosition(robot.MAST_RIGHT_POSITION);
        strafe.right(.15, 10);
        claw.open();
        if (levelHeight == robot.LEVEL_ONE_HEIGHT){
            arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        }
        strafe.left(.15,5);
        gyroturn.absolute(0);
        drive.forward(.3,36);
        strafe.right(.15,2);
        gyroturn.absolute(-90);
        strafe.left(.15,8);
        drive.backward(.35,28);
        strafe.right(.35,27);
        drive.backward(.35,19);
        gyroturn.absolute(0);
        mast.setPosition(robot.ARM_MIDDLE_POSITION);
        arm.setPosition(robot.ARM_FLOOR_POSITION);
    }
}
