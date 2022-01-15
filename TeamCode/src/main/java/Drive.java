
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public Drive(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void forward(double speed, int distance) throws InterruptedException{
        if (!linearOpMode.opModeIsActive())
            return;
        int target = distance * robot.CLICKS_PER_INCH;
       robot.leftFrontDrive.setTargetPosition(target);
       robot.leftRearDrive.setTargetPosition(target);
       robot.rightFrontDrive.setTargetPosition(target);
       robot.rightRearDrive.setTargetPosition(target);

        robot.driveToPosition();

       robot.leftFrontDrive.setPower(speed);
       robot.leftRearDrive.setPower(speed);
       robot.rightFrontDrive.setPower(speed);
       robot.rightRearDrive.setPower(speed);



//       Thread.sleep(5000);
//        telemetry.addData("", target);
        robot.leftObjectDistance = 40;
        robot.rightObjectDistance = 40;
        while (robot.leftRearDrive.isBusy() && robot.leftFrontDrive.isBusy() && robot.rightRearDrive.isBusy()
                && robot.rightFrontDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
                robot.leftFrontDrive.setPower(speed);
                robot.leftRearDrive.setPower(speed);
                robot.rightFrontDrive.setPower(speed);
                robot.rightRearDrive.setPower(speed);

            robot.getSideDistance();
            telemetry.addData("leftDistanceSensorValue", robot.leftObjectDistance);
            telemetry.addData("RightDistanseSensorValue", robot.rightObjectDistance);

            telemetry.addData("linear opmode is working, target = ", target);
            telemetry.update();
       }

       robot.stop ();

    }

    public void backward(double speed, int distance) throws InterruptedException {
        if (!linearOpMode.opModeIsActive())
            return;

        int target = distance * robot.CLICKS_PER_INCH;
        robot.leftFrontDrive.setTargetPosition(-target);
        robot.leftRearDrive.setTargetPosition(-target);
        robot.rightFrontDrive.setTargetPosition(-target);
        robot.rightRearDrive.setTargetPosition(-target);

        robot.driveToPosition();

        robot.leftFrontDrive.setPower(-speed);
        robot.leftRearDrive.setPower(-speed);
        robot.rightFrontDrive.setPower(-speed);
        robot.rightRearDrive.setPower(-speed);

        robot.leftObjectDistance = 40;
        robot.rightObjectDistance = 40;

        while (robot.leftRearDrive.isBusy() && robot.leftFrontDrive.isBusy() && robot.rightRearDrive.isBusy()
                && robot.rightFrontDrive.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
                robot.leftFrontDrive.setPower(-speed);
                robot.leftRearDrive.setPower(-speed);
                robot.rightFrontDrive.setPower(-speed);
                robot.rightRearDrive.setPower(-speed);

            robot.getSideDistance();
            telemetry.addData("leftDistanceSensorValue", robot.leftObjectDistance);
            telemetry.addData("RightDistanseSensorValue", robot.rightObjectDistance);

            telemetry.addData("linear opmode is working, target = ", target);
            telemetry.addData("Encoder Clicks", robot.leftRearDrive.getCurrentPosition());
            telemetry.update();

        }



        robot.stop ();

    }
}