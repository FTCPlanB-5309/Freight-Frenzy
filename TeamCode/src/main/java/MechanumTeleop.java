import androidx.core.math.MathUtils;
import com.qualcomm.robotcore.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

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
            rx = gamepad1.right_stick_x; //turn

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

                if (gamepad1.a) {
                    robot.frontRightWingServo.setPosition(robot.FRONT_RIGHT_WING_CLOSE);
                    robot.backRightWingServo.setPosition(robot.BACK_RIGHT_WING_CLOSE);
                    robot.frontLeftWingServo.setPosition(robot.FRONT_LEFT_WING_CLOSE);
                    robot.backLeftWingServo.setPosition(robot.BACK_LEFT_WING_CLOSE);
                }
            if (gamepad1.b) {
                robot.frontRightWingServo.setPosition(robot.FRONT_RIGHT_WING_OPEN);
                robot.backRightWingServo.setPosition(robot.BACK_RIGHT_WING_OPEN);
                robot.frontLeftWingServo.setPosition(robot.FRONT_LEFT_WING_OPEN);
                robot.backLeftWingServo.setPosition(robot.BACK_LEFT_WING_OPEN);
            }


                //Rotate Center Mast

                if (Math.abs(gamepad2.right_stick_x) > robot.TELEOPDEADZONE)
                    robot.mastRotator.setPower(-gamepad2.right_stick_x  );
                else
                    robot.mastRotator.setPower(0);


                // Grabber Open/Close
                if (gamepad2.right_trigger > 0.5) {
                    robot.leftClawServo.setPosition(robot.LEFT_CLAW_CLOSED);
                    robot.rightClawServo.setPosition(robot.RIGHT_CLAW_CLOSED);
                }

                if (gamepad2.right_bumper) {
                    robot.leftClawServo.setPosition(robot.LEFT_CLAW_OPEN);
                    robot.rightClawServo.setPosition(robot.RIGHT_CLAW_OPEN);
                }

                //Grabber open wide
            if (gamepad2.left_trigger > 0.5) {
                robot.leftClawServo.setPosition(robot.LEFT_CLAW_WIDE);
                robot.rightClawServo.setPosition(robot.RIGHT_CLAW_WIDE);
            }

                // Arm Control
                if (Math.abs(gamepad2.left_stick_y) > robot.TELEOPDEADZONE) {
                    robot.armMotor.setPower(-gamepad2.left_stick_y);
                } else {
                    robot.armMotor.setPower(0);
                }
                telemetry.addData("Red", robot.colorSensor.red());
                telemetry.addData("Blue", robot.colorSensor.blue());
                telemetry.addData("arm",robot.armMotor.getCurrentPosition());
                telemetry.addData("mast", robot.mastRotator.getCurrentPosition());
                telemetry.addData("Claw Distance in CM", robot.clawDistanceSensor.getDistance(DistanceUnit.CM));
                telemetry.addData("Front Distance",robot.frontDistanceSensor.getDistance(DistanceUnit.INCH));
                telemetry.addData("Left Distance",robot.leftDistanceSensor.getDistance(DistanceUnit.INCH));
                telemetry.addData("Right Distance",robot.rightDistanceSensor.getDistance(DistanceUnit.INCH));

            telemetry.update();

            }
        }
    }