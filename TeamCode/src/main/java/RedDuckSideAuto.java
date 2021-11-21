import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RedDuckSideAuto")

public class RedDuckSideAuto extends LinearOpMode {
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

        FreightLevel level = findTeamFreight.getLevel();
        if (level == FreightLevel.level1) {
            arm.setPosition(robot.ARM_BOTTOM_POSITION);
            Thread.sleep(3000);
        }
        if (level == FreightLevel.level2) {
            arm.setPosition(robot.ARM_MIDDLE_POSITION);
            Thread.sleep(3000);
        }
        if (level == FreightLevel.level3) {
            arm.setPosition(robot.ARM_TOP_POSITION);
            wrist.setPosition(robot.WRIST_TOP_POSITION);
            Thread.sleep(3000);
        }
        drive.forward(0.25, 32 );
        strafe.right(.15, 2);
        Thread.sleep(5000);
        claw.open();
        telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
        telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
        telemetry.addData("wrist", robot.wristServo.getPosition());
        telemetry.update();
        //drive.forward1000(.5);

        /*
        find out where the special marker is (1 out of 3)
        move forward
        turn left 90
        Move arm to specific level (based on the marker position)
        move forward
        drop block
        move backward
        move arm back
        extend the "duck wheel" out
        spin wheel
        bring arm back
        make sure to StOp!11!!!!!11!111!!!
         */
    }
}