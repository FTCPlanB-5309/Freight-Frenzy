import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Arm {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;
    Wrist wrist;


    public Arm(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode, Wrist wrist){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
        this.wrist = wrist;
    }

    public void setPosition(int newPosition) {
        robot.armMotor.setTargetPosition(newPosition);
        robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.armMotor.setPower(-0.73);
        while (robot.armMotor.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
            wrist.updatePosition();
        }
        robot.armMotor.setPower(0);
    }

    public void setPositionNoWait(int newPosition) {
        robot.armMotor.setTargetPosition(newPosition);
        robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.armMotor.setPower(-0.73);
    }



    public void setHeight(int newHeight) {

        double  clawHeight;
        clawHeight = robot.clawDistanceSensor.getDistance(DistanceUnit.CM);

        robot.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (Math.abs(clawHeight-newHeight) > 1 && linearOpMode.opModeIsActive()
                && Math.abs(robot.armMotor.getCurrentPosition())<robot.ARM_TOP_POSITION) {

            if(clawHeight > newHeight){
                robot.armMotor.setPower(-0.73);
            }
            else robot.armMotor.setPower(0.73);

            Thread.yield();
            wrist.updatePosition();

            clawHeight = robot.clawDistanceSensor.getDistance(DistanceUnit.CM);

            telemetry.addData("Set Height",newHeight);
            telemetry.addData("clawHeight", clawHeight);
            telemetry.update();
        }
        robot.armMotor.setPower(0);
    }

}
