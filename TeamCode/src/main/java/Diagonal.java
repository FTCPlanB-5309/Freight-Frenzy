
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Diagonal {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public Diagonal(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void frontLeft(double speed, int distance) throws InterruptedException{
        if (!linearOpMode.opModeIsActive())
            return;
        int target = distance * robot.DIAGONAL_CLICKS_PER_INCH;
        robot.leftRearDrive.setTargetPosition(target);
        robot.rightFrontDrive.setTargetPosition(target);

        robot.driveToPosition();

        robot.leftRearDrive.setPower(speed);
        robot.rightFrontDrive.setPower(speed);



        while (robot.leftRearDrive.isBusy() && robot.rightFrontDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            robot.leftRearDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);

            telemetry.addData("linear opmode is working, target = ", target);
            telemetry.update();
        }

        robot.stop ();

    }


    public void backLeft(double speed, int distance) throws InterruptedException {
        if (!linearOpMode.opModeIsActive())
            return;

        int target = distance * robot.DIAGONAL_CLICKS_PER_INCH;
        robot.leftFrontDrive.setTargetPosition(-target);
        robot.rightRearDrive.setTargetPosition(-target);

        robot.driveToPosition();

        robot.leftFrontDrive.setPower(-speed);
        robot.rightRearDrive.setPower(-speed);

        while (robot.leftFrontDrive.isBusy() && robot.rightRearDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            Thread.yield();
            robot.leftFrontDrive.setPower(-speed);
            robot.rightRearDrive.setPower(-speed);
        }


        telemetry.addData("linear opmode is working, target = ", target);
        telemetry.addData("Encoder Clicks", robot.leftRearDrive.getCurrentPosition());
        telemetry.update();

        robot.stop ();

    }

    public void backRight(double speed, int distance) throws InterruptedException {
        if (!linearOpMode.opModeIsActive())
            return;

        int target = distance * robot.CLICKS_PER_INCH;
        robot.leftRearDrive.setTargetPosition(-target);
        robot.rightFrontDrive.setTargetPosition(-target);

        robot.driveToPosition();

        robot.leftRearDrive.setPower(-speed);
        robot.rightFrontDrive.setPower(-speed);


        while (robot.leftRearDrive.isBusy() && robot.rightFrontDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            robot.leftRearDrive.setPower(-speed);
            robot.rightFrontDrive.setPower(-speed);
        }
        telemetry.addData("linear opmode is working, target = ", target);
        telemetry.addData("Encoder Clicks", robot.leftRearDrive.getCurrentPosition());
        telemetry.update();

        robot.stop ();

    }
    public void frontRight(double speed, int distance) throws InterruptedException{
        if (!linearOpMode.opModeIsActive())
            return;
        int target = distance * robot.DIAGONAL_CLICKS_PER_INCH;
        robot.leftFrontDrive.setTargetPosition(target);
        robot.rightRearDrive.setTargetPosition(target);

        robot.driveToPosition();

        robot.leftFrontDrive.setPower(speed);
        robot.rightRearDrive.setPower(speed);



        while (robot.leftFrontDrive.isBusy() && robot.rightRearDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            robot.leftFrontDrive.setPower(speed);
            robot.rightRearDrive.setPower(speed);

            telemetry.addData("linear opmode is working, target = ", target);
            telemetry.update();
        }

        robot.stop ();

    }

}
