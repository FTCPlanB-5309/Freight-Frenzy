import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveToLine {
    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public DriveToLine(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void forward(int maxDistance, double speed, Color lineColor){
        if (!linearOpMode.opModeIsActive())
            return;
        int target = maxDistance * robot.CLICKS_PER_INCH;
        robot.leftFrontDrive.setTargetPosition(target);
        robot.leftRearDrive.setTargetPosition(target);
        robot.rightFrontDrive.setTargetPosition(target);
        robot.rightRearDrive.setTargetPosition(target);

        robot.driveToPosition();

        robot.leftFrontDrive.setPower(speed);
        robot.leftRearDrive.setPower(speed);
        robot.rightFrontDrive.setPower(speed);
        robot.rightRearDrive.setPower(speed);

        int red;
        int blue;

        while (robot.leftRearDrive.isBusy() && robot.leftFrontDrive.isBusy() &&
                robot.rightRearDrive.isBusy() && robot.rightFrontDrive.isBusy() &&
                linearOpMode.opModeIsActive()) {
            Thread.yield();
            robot.leftFrontDrive.setPower(speed);
            robot.leftRearDrive.setPower(speed);
            robot.rightFrontDrive.setPower(speed);
            robot.rightRearDrive.setPower(speed);

            red = robot.colorSensor.red();
            blue = robot.colorSensor.blue();

            if (lineColor.equals(Color.red) && red > 200)
                break;
            else if (lineColor.equals(Color.blue) && blue > 250)
                break;
            else if (lineColor.equals(Color.white) && blue > 300 && red > 250)
                break;

            telemetry.addData("red = ", red);
            telemetry.addData("blue = ", blue);
            telemetry.update();
        }
    }
}

