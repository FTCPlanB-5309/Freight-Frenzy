import androidx.core.math.MathUtils;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Mechanaum Teleop", group="Teleop")

public class MechanumTeleop extends LinearOpMode {

    MechanumHardware robot = new MechanumHardware();


    @Override
    public void runOpMode() {
        int stick_direction;
        double ly;
        double rx;
        double lx;
        boolean slow_mode;
        boolean normal_mode;
        robot.teleopInit(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Drivetrain
            ly = -gamepad1.left_stick_y; //drive forward
            lx = gamepad1.left_stick_x; //strafe
            rx = -gamepad1.right_stick_x; //turn

            if (Math.abs(ly) > robot.TELEOPDEADZONE ||
                    Math.abs(lx) > robot.TELEOPDEADZONE ||
                    Math.abs(rx) > robot.TELEOPDEADZONE) {

                normal_mode = true;
                slow_mode = false;
                // Compute the drive speed of each drive motor based on formula from reddit
                double FL_power_raw = ly + lx - (rx * .7f);
                double FR_power_raw = ly - lx + (rx * .7f);
                double RL_power_raw = ly - lx - (rx * .7f);
                double RR_power_raw = ly + lx + (rx * .7f);

                //Clip the values generated by the above formula so that they never go outside of -1 to 1
                double FL_power = Range.clip(FL_power_raw, -1, 1);
                double FR_power = Range.clip(FR_power_raw, -1, 1);
                double RL_power = Range.clip(RL_power_raw, -1, 1);
                double RR_power = Range.clip(RR_power_raw, -1, 1);

                robot.leftFrontDrive.setPower(FL_power * robot.DRIVE_SPEED_REDUCTION);
                robot.rightFrontDrive.setPower(FR_power * robot.DRIVE_SPEED_REDUCTION);
                robot.leftRearDrive.setPower(RL_power * robot.DRIVE_SPEED_REDUCTION);
                robot.rightRearDrive.setPower(RR_power * robot.DRIVE_SPEED_REDUCTION);
            }
                else robot.stop();


                // Attachments
                if (gamepad1.left_bumper) {
                    robot.duckSpinner.setPower(.75);
                }
                else if (gamepad1.left_trigger>0.5){
                    robot.duckSpinner.setPower(-.75);
                }
                else {robot.duckSpinner.setPower(0);}

                //Rotate Center Mast

                if (Math.abs(gamepad2.right_stick_x) > robot.TELEOPDEADZONE)
                    robot.mastRotator.setPower(-gamepad2.right_stick_x * 0.2);
                else
                    robot.mastRotator.setPower(0);

//                if (gamepad2.dpad_right)
//                    robot.mastRotator.setPosition(robot.mastRotator.getPosition() - 0.0005);

                // Wrist Up/Down
            if (gamepad2.right_stick_y > robot.TELEOPDEADZONE)
                robot.wristServo.setPosition(robot.wristServo.getPosition() - 0.0005);
            else if (gamepad2.right_stick_y < -robot.TELEOPDEADZONE)
                robot.wristServo.setPosition(robot.wristServo.getPosition() + 0.0005);
            if (gamepad2.a)
                robot.wristServo.setPosition(robot.GRABBER_GROUND_POS);
            if (gamepad2.y)
                robot.wristServo.setPosition(robot.GRABBER_AIR_POS);

//            if (gamepad2.dpad_down)
//                robot.wristServo.setPosition(robot.wristServo.getPosition() - 0.0005);


                // Grabber Open/Close
                if (gamepad2.right_trigger > 0.5) {
                    robot.leftClawServo.setPosition(robot.LEFT_CLAW_CLOSED);
                    robot.rightClawServo.setPosition(robot.RIGHT_CLAW_CLOSED);
                }

                if (gamepad2.right_bumper) {
                    robot.leftClawServo.setPosition(robot.LEFT_CLAW_OPEN);
                    robot.rightClawServo.setPosition(robot.RIGHT_CLAW_OPEN);
                }

                // Arm Control
                if (Math.abs(gamepad2.left_stick_y) > robot.TELEOPDEADZONE) {
                    robot.armMotor.setPower(-gamepad2.left_stick_y * 0.7);

                } else {
                    robot.armMotor.setPower(0);
                }
                telemetry.addData("wrist", robot.wristServo.getPosition());
                telemetry.addData("arm",robot.armMotor.getCurrentPosition());
                telemetry.addData("mast", robot.mastRotator.getCurrentPosition());
                telemetry.update();

            }
        }
    }