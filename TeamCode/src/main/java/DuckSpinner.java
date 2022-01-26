
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DuckSpinner {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public DuckSpinner(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void spin(Color alliance,SetupDirection direction) throws InterruptedException {
        if (alliance == Color.red)
            robot.duckSpinner.setPower(-0.4);
        else
            robot.duckSpinner.setPower(0.4);
        //Strafe Right
        if (direction == SetupDirection.backward && alliance == Color.red) {
//            robot.leftFrontDrive.setPower(0.1);
            robot.leftRearDrive.setPower(-0.13);
//            robot.rightFrontDrive.setPower(-0.1);
            robot.rightRearDrive.setPower(0.13);
            telemetry.addData("strafe right", "ok");
            telemetry.update();
        }
        //Srafe Left
        if (direction == SetupDirection.backward && alliance == Color.blue) {
//            robot.leftFrontDrive.setPower(-0.1);
            robot.leftRearDrive.setPower(0.13);
//            robot.rightFrontDrive.setPower(0.1);
            robot.rightRearDrive.setPower(-0.13);
            telemetry.addData("strafe left", "ok");
            telemetry.update();

        }
        //Backup
        if (direction == SetupDirection.forward) {
            robot.leftFrontDrive.setPower(-.1);
            robot.rightFrontDrive.setPower(-.1);
            robot.rightRearDrive.setPower(-.1);
            robot.leftRearDrive.setPower(-.1);
            telemetry.addData("backup ", "ok");
            telemetry.update();
        }

        for (int i=0; linearOpMode.opModeIsActive() && i < 35; i++) {
            Thread.sleep(100);
        }
        robot.stop();
        robot.duckSpinner.setPower(0);
    }
}