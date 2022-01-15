import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "RedPickupAuto")

public class RedPickupAuto extends LinearOpMode {
    MechanumHardware robot = new MechanumHardware();
    Drive drive = new Drive(robot, telemetry, this);
    Strafe strafe = new Strafe(robot, telemetry, this);
    FindTeamFreight findTeamFreight = new FindTeamFreight(robot, telemetry, this);
    Arm arm = new Arm(robot, telemetry, this);
    Claw claw = new Claw(robot, telemetry, this);
    Mast mast = new Mast(robot, telemetry, this);
    DuckSpinner duckspinner = new DuckSpinner(robot, telemetry,this);
    GyroTurn gyroturn = new GyroTurn(robot,telemetry,this);
    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();

        arm.setPositionNoWait(robot.ARM_MIDDLE_POSITION);
        drive.backward(.3, 24);
        mast.setPositionNoWait(robot.MAST_FORWARD_POSITION);
        drive.backward(.1, 17);
        int levelHeight = findTeamFreight.getLevel(SetupDirection.backward);
        arm.setHeight(levelHeight);
        mast.setPosition(robot.MAST_LEFT_POSITION);
        strafe.left(.15, 5);
        claw.open();
        strafe.right(0.5,24);
        drive.forward(0.5,36);
        gyroturn.absolute(90);
        drive.backward(0.25,3);
        strafe.right(0.25,5 );
        duckspinner.spin(Color.red,SetupDirection.backward);
        strafe.left(0.5,20);
        gyroturn.absolute(0);
        while (true) {
            telemetry.addData("Front Distance",robot.frontDistanceSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("armMotor", robot.armMotor.getCurrentPosition());
            telemetry.addData("mastRotator", robot.mastRotator.getCurrentPosition());
            telemetry.addData("Claw Distance in CM", robot.clawDistanceSensor.getDistance(DistanceUnit.CM));
            telemetry.update();

        }

//        mast.setPosition(robot.MAST_RIGHT_POSITION);
//        arm.setPosition(robot.ARM_FLOOR_POSITION);

    }
}