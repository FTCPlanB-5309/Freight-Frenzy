import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "BlueDuckSideAuto")

public class BlueDuckSideAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Wrist wrist = new Wrist(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this, wrist);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    DuckSpinner duckspinner = new DuckSpinner(robot, telemetry,this);
    GyroTurn gyroTurn = new GyroTurn(robot, telemetry, this);

    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        arm.setPosition(robot.ARM_MIDDLE_POSITION);
        mast.setPosition(robot.MAST_FORWARD_POSITION);
        drive.forward(0.25, 22 );
        drive.forward(0.10, 10 );

        FreightLevel level = findTeamFreight.getLevel();
        if (level == FreightLevel.level3) {
            arm.setPosition(robot.ARM_BOTTOM_POSITION);
            wrist.setPosition(robot.WRIST_BOTTOM_POSITION);
            mast.setPosition(robot.MAST_LEFT_POSITION);

        }
        if (level == FreightLevel.level2) {
            arm.setPosition(robot.ARM_MIDDLE_POSITION);
            wrist.setPosition(robot.WRIST_MIDDLE_POSITION);
            mast.setPosition(robot.MAST_LEFT_POSITION);
        }
        if (level == FreightLevel.level1) {
            arm.setPosition(robot.ARM_TOP_POSITION);
            wrist.setPosition(robot.WRIST_TOP_POSITION);
            mast.setPosition(robot.MAST_LEFT_POSITION);

        }
        strafe.left(.15, 4);
        claw.open();
        strafe.right(0.25, 33);
        wrist.setPosition(robot.WRIST_FLOOR_POSITION);
        arm.setPosition(robot.ARM_FLOOR_POSITION);
        drive.backward(0.25, 27);
        duckspinner.spin(AllianceColor.blue);

        drive.forward(0.25,3);
        Thread.sleep(250);
        gyroTurn.absolute(0); //ToDo: change back to 1 if zero fails
        Thread.sleep(250);

        drive.forward(.25,17);

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