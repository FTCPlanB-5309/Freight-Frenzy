import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Arm {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;


    public Arm(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

    public void setPosition(int newPosition) {
        robot.armMotor.setTargetPosition(newPosition);
        robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.armMotor.setPower(-.75); // -0.73 former
        while (robot.armMotor.isBusy() && linearOpMode.opModeIsActive()) {
            Thread.yield();
        }
        robot.armMotor.setPower(0);
    }

    public void setPositionNoWait(int newPosition) {
        robot.armMotor.setTargetPosition(newPosition);
        robot.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.armMotor.setPower(-1);
    }



    public void setHeight(int newHeight) throws InterruptedException {

        double  clawHeight;
        int encoderValueAtDesiredClawHeight = robot.armMotor.getCurrentPosition();
        clawHeight = robot.getAverageDistance(robot.clawDistanceSensor, DistanceUnit.CM);

        robot.armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (Math.abs(clawHeight-newHeight) > 1 && linearOpMode.opModeIsActive()
                && Math.abs(robot.armMotor.getCurrentPosition())<robot.ARM_TOP_POSITION) {

            if(clawHeight > newHeight){
                robot.armMotor.setPower(-.73); // -0.73 former
            }
            else robot.armMotor.setPower(.73); // 0.73 former

            Thread.yield();

            clawHeight = robot.getAverageDistance(robot.clawDistanceSensor, DistanceUnit.CM);
            encoderValueAtDesiredClawHeight = robot.armMotor.getCurrentPosition();

            telemetry.addData("Set Height",newHeight);
            telemetry.addData("clawHeight", clawHeight);
            telemetry.addData("encoder value", encoderValueAtDesiredClawHeight);
            telemetry.update();
        }
        robot.armMotor.setPower(0);
        setPosition(encoderValueAtDesiredClawHeight);
    }

}
