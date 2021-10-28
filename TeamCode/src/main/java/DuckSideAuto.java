import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Autonomous")

public class DuckSideAuto extends LinearOpMode {
    RobotHardware robot = new RobotHardware();
    Drive drive = new Drive(robot, telemetry, this);

    public void runOpMode() throws InterruptedException {
        robot.teleopInit(hardwareMap);
        waitForStart();
        //drive.forward1000(.5);

        /*
        find out where the special marker is (1 out of 3)
        move forward
        turn left 90
        Move arm to specific level (based on the marker position)
        move forward
        drop block
        move backward
        move arm back
        extend the "duck wheel" out
        spin wheel
        bring arm back
        make sure to StOp!11!!!!!11!111!!!
         */
    }
}