import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BasicHardwareMap
{
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor  duckSpinner = null;
    public Servo duckArm = null;
    public Servo frontWheel = null;
    public Servo rearWheel = null;

    HardwareMap hwMap = null;

    //Hardware constants
    public static final double TELEOPDEADZONE = 0.05;

    public static final double DUCK_ARM_OUT = 0;
    public static final double DUCK_ARM_IN = 0.33;

    public static final double FRONT_WHEEL_DOWN = 0.18;
    public static final double FRONT_WHEEL_UP = 0.05;
    public static final double REAR_WHEEL_DOWN = .36;
    public static final double REAR_WHEEL_UP = 0.23;

    public static final double GRABBER_GROUND_POS = 0.5;
    public static final double RIGHT_CLAW_CLOSED = 0.87;
    public static final double RIGHT_CLAW_OPEN = 0.67;
    public static final double LEFT_CLAW_CLOSED = 0.27;
    public static final double LEFT_CLAW_OPEN = 0.5;


    public void teleopInit(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        duckSpinner = hwMap.get(DcMotor.class, "duckSpinner");

        duckArm = hwMap.get(Servo.class, "duckArm");
        frontWheel = hwMap.get(Servo.class, "frontWheel");
        rearWheel = hwMap.get(Servo.class, "rearWheel");


        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set servos to start positions
        duckArm.setPosition(DUCK_ARM_IN);
        frontWheel.setPosition(FRONT_WHEEL_DOWN);
        rearWheel.setPosition(REAR_WHEEL_DOWN);

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        duckSpinner.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetDriveEncoders () {
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void driveToPosition () {
        resetDriveEncoders();
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void driveUsingEncoders () {
        resetDriveEncoders();
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stop () {
        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}
