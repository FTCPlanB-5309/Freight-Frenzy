import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.security.cert.TrustAnchor;

public class GyroTurn {
    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;
    double currHeading;

    // State used for updating telemetry
    private Orientation angles;
    private Acceleration gravity;

    public GyroTurn(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode) {
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void absolute(double target) {

        double diff;
        double speed;

        if (!linearOpMode.opModeIsActive())
            return;
        robot.driveUsingEncoders();

        updateHeading();

        while (currHeading != target && linearOpMode.opModeIsActive()) {

            diff = Math.abs(currHeading - target);
            speed = TurnSpeed(diff);

            if (target > currHeading)
                TurnRight(speed);
            else if (target < currHeading)
                TurnLeft(speed);
            
            updateHeading();

        }
        robot.stop ();
    }

    public void goodEnough(double target) {

        double diff;
        double speed;

        if (!linearOpMode.opModeIsActive())
            return;
        robot.driveUsingEncoders();

        updateHeading();

        while (Math.abs(currHeading - target) > 1 && linearOpMode.opModeIsActive()) {

            diff = Math.abs(currHeading - target);
            speed = TurnSpeed(diff);

            if (target > currHeading)
                TurnRight(speed);
            else if (target < currHeading)
                TurnLeft(speed);

            updateHeading();

        }
        robot.stop ();
    }



    private double TurnSpeed (double diff) {

        if (diff > 80) {
        return robot.HIGH_TURN_POWER;
        }
        else if (diff < 5) {
            return robot.LOW_TURN_POWER;
        }
        else
            return .005667 * diff + .046667;
    }

    private void TurnRight (double speed) {

        robot.leftFrontDrive.setPower(speed);
        robot.leftRearDrive.setPower(speed);
        robot.rightFrontDrive.setPower(-speed);
        robot.rightRearDrive.setPower(-speed);

    }

    private void TurnLeft (double speed) {

        robot.leftFrontDrive.setPower(-speed);
        robot.leftRearDrive.setPower(-speed);
        robot.rightFrontDrive.setPower(speed);
        robot.rightRearDrive.setPower(speed);

    }

    public void updateHeading() {
        angles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity = robot.imu.getGravity();
        currHeading = angles.firstAngle;
        telemetry.addData("Heading: ", currHeading);
        telemetry.update();
        }
}
