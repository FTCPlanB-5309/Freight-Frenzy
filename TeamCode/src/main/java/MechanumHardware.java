import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MechanumHardware
{
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftRearDrive = null;
    public DcMotor rightRearDrive = null;
    public DcMotor duckSpinner = null;
    public DcMotor armMotor = null;


//    public Servo duckArm = null;
    public Servo leftClawServo = null;
    public Servo rightClawServo = null;
    public Servo wristServo = null;
    public Servo mastRotator = null;


    HardwareMap hwMap = null;

    //Hardware constants
    public static final double TELEOPDEADZONE = 0.05;

    public static final double DUCK_ARM_OUT = 0;
    public static final double DUCK_ARM_IN = 0.33;

    public static final double GRABBER_GROUND_POS = 0.5;
    public static final double RIGHT_CLAW_CLOSED = 0.87;
    public static final double RIGHT_CLAW_OPEN = 0.67;
    public static final double LEFT_CLAW_CLOSED = 0.27;
    public static final double LEFT_CLAW_OPEN = 0.5;
    public static final double MAST_START_POSITION = 0.5;

    public static final int CLICKS_PER_INCH = 44;


    public void teleopInit(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontDrive = hwMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hwMap.get(DcMotor.class, "rightFrontDrive");
        leftRearDrive = hwMap.get(DcMotor.class, "leftRearDrive");
        rightRearDrive = hwMap.get(DcMotor.class, "rightRearDrive");
        duckSpinner = hwMap.get(DcMotor.class, "duckSpinner");
        armMotor = hwMap.get(DcMotor.class, "armMotor");

//        duckArm = hwMap.get(Servo.class, "duckArm");
        leftClawServo = hwMap.get(Servo.class, "leftClawServo");
        rightClawServo = hwMap.get(Servo.class, "rightClawServo");
        wristServo = hwMap.get(Servo.class, "wristServo");
        mastRotator = hwMap.get(Servo.class, "mastRotator");


        leftFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRearDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set servos to start positions
//        duckArm.setPosition(DUCK_ARM_IN);
        leftClawServo.setPosition(LEFT_CLAW_OPEN);
        rightClawServo.setPosition(RIGHT_CLAW_OPEN);
        wristServo.setPosition(GRABBER_GROUND_POS);
        mastRotator.setPosition(MAST_START_POSITION);

        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        rightRearDrive.setPower(0);
        duckSpinner.setPower(0);
        armMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetDriveEncoders () {
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void driveToPosition () {
        resetDriveEncoders();
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }


    public void driveUsingEncoders () {
        resetDriveEncoders();
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void stop () {
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        rightRearDrive.setPower(0);
       // armMotor.setPower(0);
    }
}
