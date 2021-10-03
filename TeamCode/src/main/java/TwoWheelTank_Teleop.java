import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Two Wheel Tank Drive", group="Teleop")


public class TwoWheelTank_Teleop extends LinearOpMode {

    BasicHardwareMap robot = new BasicHardwareMap();


    @Override
    public void runOpMode() {

        double right_stick_x;
        double right_stick_y;

        double lift_rate;
        double rotation_rate;

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




            // Attachments


            right_stick_x = gamepad2.right_stick_x;

            if (Math.abs(right_stick_x) > robot.TELEOPDEADZONE) {
                rotation_rate = Math.abs(right_stick_x) * .00126 + .000737;
                if (right_stick_x > 0)
                    robot.armRotator.setPosition(robot.armRotator.getPosition() + rotation_rate);
                else
                    robot.armRotator.setPosition(robot.armRotator.getPosition() - rotation_rate);
            }

            telemetry.addData("rotator position", robot.armRotator.getPosition());
            telemetry.addData("lifter position", robot.armLifter.getPosition());
            telemetry.update();


            right_stick_y = gamepad2.right_stick_y;
            if (Math.abs(right_stick_y) > robot.TELEOPDEADZONE) {
                lift_rate = Math.abs(right_stick_y) * .001 + .000737;
                if (right_stick_y > 0)
                    robot.armLifter.setPosition(robot.armLifter.getPosition() + lift_rate);
                else
                    robot.armLifter.setPosition(robot.armLifter.getPosition() - lift_rate);

            }

            if (gamepad2.right_trigger > 0.8) {
                robot.leftGrabber.setPosition(robot.LEFT_GRABBER_CLOSED);
                robot.rightGrabber.setPosition(robot.RIGHT_GRABBER_CLOSED);
            }

            if (gamepad2.right_bumper) {
                robot.leftGrabber.setPosition(robot.LEFT_GRABBER_OPEN);
                robot.rightGrabber.setPosition(robot.RIGHT_GRABBER_OPEN);
            }

            if (gamepad2.a)
                robot.duckSpinner.setPower(1);
            else robot.duckSpinner.setPower(0);

            if (gamepad2.dpad_right)
                robot.duckArm.setPosition(robot.DUCK_ARM_OUT);

            if (gamepad2.dpad_left)
                robot.duckArm.setPosition(robot.DUCK_ARM_IN);


            // DriveTrain

            left = -gamepad1.left_stick_y;
            right = -gamepad1.right_stick_y;

            robot.leftFrontDrive.setPower(left);
            robot.rightFrontDrive.setPower(right);

        }
    }
}