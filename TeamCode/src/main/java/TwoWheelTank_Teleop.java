import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Two Wheel Tank Drive", group="Teleop")

public class TwoWheelTank_Teleop extends LinearOpMode {

    BasicHardwareMap robot = new BasicHardwareMap();


    @Override
    public void runOpMode() {

        double leftY;
        double rightX;

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
            leftY = -gamepad1.left_stick_y;
            rightX = gamepad1.right_stick_x;
            if (Math.abs(gamepad1.right_stick_x) > robot.TELEOPDEADZONE) {
                robot.leftDrive.setPower(MathUtils.clamp(leftY + rightX, -1, 1));
                robot.rightDrive.setPower(MathUtils.clamp(leftY - rightX, -1, 1));
            }
            else if (Math.abs(gamepad1.left_stick_y) > robot.TELEOPDEADZONE) {
                robot.leftDrive.setPower(leftY);
                robot.rightDrive.setPower(leftY);
            }
            else {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
            }


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