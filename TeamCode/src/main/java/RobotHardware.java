import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware
{
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;
    public DcMotor  duckSpinner = null;
    public DcMotor armMotor = null;
    public Servo duckArm = null;
    public Servo frontWheel = null;
    public Servo rearWheel = null;
    public Servo leftClawServo = null;
    public Servo rightClawServo = null;
    public Servo wristServo = null;



    com.qualcomm.robotcore.hardware.HardwareMap hwMap = null;

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


    public void teleopInit(com.qualcomm.robotcore.hardware.HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        duckSpinner = hwMap.get(DcMotor.class, "duckSpinner");
        armMotor = hwMap.get(DcMotor.class, "armMotor");

        duckArm = hwMap.get(Servo.class, "duckArm");
        frontWheel = hwMap.get(Servo.class, "frontWheel");
        rearWheel = hwMap.get(Servo.class, "rearWheel");
        leftClawServo = hwMap.get(Servo.class, "leftClawServo");
        rightClawServo = hwMap.get(Servo.class, "rightClawServo");
        wristServo = hwMap.get(Servo.class, "wristServo");


        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set servos to start positions
        duckArm.setPosition(DUCK_ARM_IN);
        frontWheel.setPosition(FRONT_WHEEL_DOWN);
        rearWheel.setPosition(REAR_WHEEL_DOWN);
        leftClawServo.setPosition(LEFT_CLAW_OPEN);
        rightClawServo.setPosition(RIGHT_CLAW_OPEN);
        wristServo.setPosition(GRABBER_GROUND_POS);

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        duckSpinner.setPower(0);
        armMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
        armMotor.setPower(0);
    }
}
