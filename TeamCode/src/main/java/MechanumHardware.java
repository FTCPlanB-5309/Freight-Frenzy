import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Arrays;


public class MechanumHardware
{
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftRearDrive = null;
    public DcMotor rightRearDrive = null;
    public DcMotor duckSpinner = null;
    public DcMotor armMotor = null;
    public DcMotor mastRotator = null;

//    public Servo duckArm = null;
    public Servo leftClawServo = null;
    public Servo rightClawServo = null;
    public Servo frontLeftWingServo = null;
    public Servo frontRightWingServo = null;
    public Servo backLeftWingServo = null;
    public Servo backRightWingServo = null;
    BNO055IMU imu;

    public Rev2mDistanceSensor rightDistanceSensor;
    public Rev2mDistanceSensor leftDistanceSensor;
    public Rev2mDistanceSensor clawDistanceSensor;
    public Rev2mDistanceSensor frontDistanceSensor;
    public RevColorSensorV3 colorSensor;

    HardwareMap hwMap = null;



    //Hardware constants
    public static final double TELEOPDEADZONE = 0.05;
    public static final int CLICKS_PER_INCH = 44;
    public static final double DRIVE_SPEED_REDUCTION = 1;
    public static final int STRAFE_CLICKS_PER_INCH = 48;
    public static final int DIAGONAL_CLICKS_PER_INCH = 44;


    public final double HIGH_TURN_POWER = 0.6;
    public final double MEDIUM_TURN_POWER = 0.12;
    public final double LOW_TURN_POWER = 0.05;

    public static final double DUCK_ARM_OUT = 0;
    public static final double DUCK_ARM_IN = 0.33;

    public static final int BLUE_DUCK_POSITION = 0;
    public static final int RED_DUCK_POSITION = 0;
    public static final double DUCK_SPINNER_SPEED = 0.5;

    public static final double GRABBER_GROUND_POS = 0.6 ;
    public static final double GRABBER_AIR_POS = .21;
    public static final double RIGHT_CLAW_CLOSED = 0.87;
    public static final double RIGHT_CLAW_OPEN = 0.72;
    public static final double LEFT_CLAW_CLOSED = 0.27;
    public static final double LEFT_CLAW_OPEN = 0.43;
    public static final double LEFT_CLAW_WIDE = 0.57;
    public static final double RIGHT_CLAW_WIDE = 0.55;

    public static final double MAST_START_POSITION = 0.5;


    public static final int MAST_LEFT_POSITION = 6000;
    public static final int MAST_RIGHT_POSITION = 0;
    public static final int MAST_FORWARD_POSITION = 3000;
    public static final int MAST_CENTER_LEFT_POSITION = 3500;
    public static final int MAST_CENTER_RIGHT_POSITION = 2500;

    public static final int ARM_FLOOR_POSITION = 0;
    public static final int ARM_BOTTOM_POSITION = 1800;
    public static final int ARM_MIDDLE_POSITION = 3200;
    public static final int ARM_TOP_POSITION = 5500;

    public static final double FRONT_RIGHT_WING_OPEN = .39;
    public static final double FRONT_LEFT_WING_OPEN = .53;
    public static final double BACK_RIGHT_WING_OPEN = .30;
    public static final double BACK_LEFT_WING_OPEN = .7;
    public static final double FRONT_RIGHT_WING_CLOSE = .76;
    public static final double FRONT_LEFT_WING_CLOSE = .16;
    public static final double BACK_RIGHT_WING_CLOSE = .68;
    public static final double BACK_LEFT_WING_CLOSE = .31;

    public double leftObjectDistance;
    public double rightObjectDistance;
    double leftDistance;
    double rightDistance;
    public double floorDistance;

    public static int LEVEL_ONE_HEIGHT = 16;
    public static int LEVEL_TWO_HEIGHT = 33;
    public static int LEVEL_THREE_HEIGHT = 55;

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
        frontLeftWingServo = hwMap.get(Servo.class,"FrontLeftWingServo");
        frontRightWingServo = hwMap.get(Servo.class,"FrontRightWingServo");
        backLeftWingServo = hwMap.get(Servo.class,"BackLeftWingServo");
        backRightWingServo = hwMap.get(Servo.class,"BackRightWingServo");

        mastRotator = hwMap.get(DcMotor.class, "mastRotator");

        leftDistanceSensor = hwMap.get(Rev2mDistanceSensor.class, "leftDistanceSensor");
        rightDistanceSensor = hwMap.get(Rev2mDistanceSensor.class, "rightDistanceSensor");
        clawDistanceSensor = hwMap.get(Rev2mDistanceSensor.class, "clawDistanceSensor");
        frontDistanceSensor = hwMap.get(Rev2mDistanceSensor.class, "frontDistanceSensor");
        colorSensor = hwMap.get(RevColorSensorV3.class, "colorSensor");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        rightFrontDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        mastRotator.setDirection(DcMotorSimple.Direction.REVERSE);
        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set servos to start positions
        leftClawServo.setPosition(LEFT_CLAW_CLOSED);
        rightClawServo.setPosition(RIGHT_CLAW_CLOSED);
        frontRightWingServo.setPosition(FRONT_RIGHT_WING_CLOSE);
        frontLeftWingServo.setPosition(FRONT_LEFT_WING_CLOSE);
        backRightWingServo.setPosition(BACK_RIGHT_WING_CLOSE);
        backLeftWingServo.setPosition(BACK_LEFT_WING_CLOSE);
        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        rightRearDrive.setPower(0);
        duckSpinner.setPower(0);
        armMotor.setPower(0);
        mastRotator.setPower(0);

        mastRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mastRotator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void getSideDistance() {
        leftDistance = leftDistanceSensor.getDistance(DistanceUnit.INCH);
        rightDistance = rightDistanceSensor.getDistance(DistanceUnit.INCH);

        if (leftDistance < leftObjectDistance &&
            leftDistance != 0) {
            leftObjectDistance = leftDistance;
        }
        if (rightDistance < rightObjectDistance &&
            rightDistance != 0) {
            rightObjectDistance = rightDistance;
        }
    }

    public int getAverageDistance(Rev2mDistanceSensor sensor, DistanceUnit units) {
        double [] sensorValues = new double[5];
        double averageValue;
        int roundedValue;
        for (int i = 0; i<5; i++)
            sensorValues [i] = sensor.getDistance(units);
        Arrays.sort(sensorValues);
        averageValue = (sensorValues[1]+sensorValues[2]+sensorValues[3]) / 3;
        roundedValue = (int)Math.round(averageValue);
        return roundedValue;
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
