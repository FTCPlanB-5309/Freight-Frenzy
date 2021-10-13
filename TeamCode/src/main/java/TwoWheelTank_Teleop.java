import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Two Wheel Tank Drive", group="Teleop")

public class TwoWheelTank_Teleop extends LinearOpMode {

    BasicHardwareMap robot = new BasicHardwareMap();


    @Override
    public void runOpMode() {

        double left;
        double right;

        robot.teleopInit(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
//  Duck attachment code, WIP
//
//            if (gamepad2.a)
//                robot.duckSpinner.setPower(1);
//            else robot.duckSpinner.setPower(0);
//
//            if (gamepad2.dpad_right)
//                robot.duckArm.setPosition(robot.DUCK_ARM_OUT);
//
//            if (gamepad2.dpad_left)
//                robot.duckArm.setPosition(robot.DUCK_ARM_IN);

            // Drivetrain
            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            robot.leftDrive.setPower(left);
            robot.rightDrive.setPower(right);

            // Balance wheels
            if(gamepad1.dpad_up) {
                robot.frontWheel.setPosition(robot.FRONT_WHEEL_UP);
                robot.rearWheel.setPosition(robot.REAR_WHEEL_UP);
            }
            if(gamepad1.dpad_down) {
                robot.frontWheel.setPosition(robot.FRONT_WHEEL_DOWN);
                robot.rearWheel.setPosition(robot.REAR_WHEEL_DOWN);
            }
        }
    }
}