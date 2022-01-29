import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "BlueWarehouseAuto")




public class BlueWarehouseAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    GyroTurn gyroTurn = new GyroTurn(robot,telemetry,this);

    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        drive.backward(.3, 24);
        drive.backward(.1, 19);
        int levelHeight = findTeamFreight.getLevel(SetupDirection.backward);
        strafe.right(.2,7);
        arm.setHeight(levelHeight);
        mast.setPosition(robot.MAST_LEFT_POSITION);
        strafe.left(.15, 10);
        claw.open();
        if (levelHeight == robot.LEVEL_ONE_HEIGHT){
            arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        }
        strafe.right(.15,5);
        gyroTurn.goodEnough(0);
        drive.forward(.3,36);
        strafe.left(.15,2);
        gyroTurn.goodEnough(90);
        strafe.right(.15,8);
        drive.backward(.35,28);
        strafe.left(.35,27);
        drive.backward(.35,19);
        gyroTurn.goodEnough(0);
        mast.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        strafe.right(.3, 4);
        arm.setPosition(robot.ARM_FLOOR_POSITION);


        telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
        telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
        telemetry.update();
    }
}
