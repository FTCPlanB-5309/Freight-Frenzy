import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "RedWarehouseAutoNew")

public class RedWarehouseAutoTest extends LinearOpMode{
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
        strafe.left(.2,7);

        //logic to use arm encoder value if claw distance is too high.
        if(robot.clawDistanceSensor.getDistance(DistanceUnit.CM) > robot.LEVEL_THREE_HEIGHT) {
            if (levelHeight == robot.LEVEL_ONE_HEIGHT) {
                arm.setPosition(robot.ARM_BOTTOM_POSITION);
                strafe.right(.15, 10);
                claw.open();
            } else if (levelHeight == robot.LEVEL_THREE_HEIGHT) {
                arm.setPosition(robot.ARM_TOP_POSITION);
                strafe.right(.15, 10);
                claw.open();
            } else {
                strafe.right(.15, 10);
                claw.open();
            }
        }
        else {
                arm.setHeight(levelHeight);
                strafe.right(.15, 10);
                claw.open();
        }
        if(levelHeight != robot.LEVEL_THREE_HEIGHT) {
            strafe.left(.15, 9);
        }
        if (levelHeight == robot.LEVEL_ONE_HEIGHT){
            arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        }

        gyroTurn.goodEnough(0);
        drive.forward(.3,36);
        if (levelHeight != robot.LEVEL_THREE_HEIGHT) {
            strafe.right(.15, 4);
        }
        gyroTurn.goodEnough(-90);
        strafe.left(.15,8);
        drive.backward(.35,28);
        strafe.right(.35,27);
        drive.backward(.35,19);
        gyroTurn.goodEnough(0);
        mast.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        strafe.left(.3, 4);
        arm.setPosition(robot.ARM_FLOOR_POSITION);

        telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
        telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
        telemetry.update();

    }
}
