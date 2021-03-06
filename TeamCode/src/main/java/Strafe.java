import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Strafe {
    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;
//    GyroTurn gyroTurn = new GyroTurn(robot, telemetry, linearOpMode);

    public Strafe(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode) {
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;

    }

    public void left(double speed, int distance) throws InterruptedException {
        if (!linearOpMode.opModeIsActive())
            return;
//        robot.setupDriveTrain();
        robot.driveUsingEncoders();
        robot.resetDriveEncoders();

        int target = distance * robot.STRAFE_CLICKS_PER_INCH;
        robot.leftFrontDrive.setTargetPosition(-target);
        robot.leftRearDrive.setTargetPosition(target);
        robot.rightFrontDrive.setTargetPosition(target);
        robot.rightRearDrive.setTargetPosition(-target);

        robot.driveToPosition();

        robot.leftFrontDrive.setPower(-speed);
        robot.leftRearDrive.setPower(speed);
        robot.rightFrontDrive.setPower(speed);
        robot.rightRearDrive.setPower(-speed);

        telemetry.addData("linear opmode is working, target = ", target);
        telemetry.addData("Encoder Clicks", robot.leftRearDrive.getCurrentPosition());
//        double mainDirection = robot.getHeading();
//        double currentDirection = mainDirection;
//       Thread.sleep(5000);
//        telemetry.addData("", target);
        while (robot.leftRearDrive.isBusy() && robot.leftFrontDrive.isBusy() && robot.rightRearDrive.isBusy()
                && robot.rightFrontDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            telemetry.addData("Encoder Clicks", robot.leftRearDrive.getCurrentPosition());
//            telemetry.update();

            robot.leftFrontDrive.setPower(-speed);
            robot.leftRearDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.rightRearDrive.setPower(-speed);
            }

//            telemetry.addData("main direction: ", mainDirection);
            telemetry.addData("speed: ", speed);
            telemetry.update();


        robot.stop ();
    }

    public void right(double speed, int distance) {
        if (!linearOpMode.opModeIsActive())
            return;
//        robot.setupDriveTrain();
        robot.driveUsingEncoders();
        robot.resetDriveEncoders();
        robot.driveToPosition();

        int target = distance * robot.STRAFE_CLICKS_PER_INCH;
        robot.leftFrontDrive.setTargetPosition(target);
        robot.leftRearDrive.setTargetPosition(-target);
        robot.rightFrontDrive.setTargetPosition(-target);
        robot.rightRearDrive.setTargetPosition(target);

        robot.leftFrontDrive.setPower(-speed);
        robot.leftRearDrive.setPower(speed);
        robot.rightFrontDrive.setPower(speed);
        robot.rightRearDrive.setPower(-speed);

        telemetry.addData("linear opmode is working, target = ", target);
        telemetry.addData("Encoder Clicks", robot.leftRearDrive.getCurrentPosition());
//        double mainDirection = robot.getHeading();
//        double currentDirection = mainDirection;

        while (robot.leftRearDrive.isBusy() && robot.leftFrontDrive.isBusy() && robot.rightRearDrive.isBusy()
                && robot.rightFrontDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            telemetry.addData("Encoder Clicks", robot.leftRearDrive.getCurrentPosition());
//            telemetry.update();

            robot.leftFrontDrive.setPower(-speed);
            robot.leftRearDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.rightRearDrive.setPower(-speed);

//            telemetry.addData("main direction: ", mainDirection);
            telemetry.addData("speed: ", speed);
            telemetry.update();
        }

        robot.stop();
    }
}