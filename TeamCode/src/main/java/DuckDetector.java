import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class DuckDetector {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_DM.tflite";
    private static final String[] LABELS = {
            "Duck",
            "Marker"
    };


    private static final String VUFORIA_KEY =
            "AV5lMdL/////AAABmYl8p4yeaEBpg80chUBr03OEyO2uvBZSdFt80EPGeZY6RLNOxd+um0wYnUnvUtSKSRGDHPxjUybk/" +
                    "5S3xKgJn0vYHVl5OhDJeo75MxhpDax25TizaBl/eJ19okrNQV2D8DYyURQktmaETVqx5X2GL4SNmkovGNQV6O" +
                    "NlcHWfyuyyhP/+eQx3JgLqPKSD9lWkLEf9Wc3D1k2N9o1EfzpvOVv+jazDnUjGOVy+wrATGq5H8Tk2VVNryzl" +
                    "ZZ4qoD5JOzyAvDKrOmH/3V/qk77SdOONeVbokFFc8ErD4S+cf/EkqjBxZTwzjyT0P/0BSqwWjz7716YeEJ0C9" +
                    "o7dkNNbJ/AkGDf51lt1VFzSmPtldip8h";


    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;
    public DuckDetector(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }
    public void init(){
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

         parameters.vuforiaLicenseKey = VUFORIA_KEY;
         parameters.cameraName = robot.hwMap.get(WebcamName.class, "Webcam 1");


         vuforia = ClassFactory.getInstance().createVuforia(parameters);
         int tfodMonitorViewId = robot.hwMap.appContext.getResources().getIdentifier(
                 "tfodMonitorViewId", "id", robot.hwMap.appContext.getPackageName());
         TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
         tfodParameters.minResultConfidence = 0.8f;
         tfodParameters.isModelTensorFlow2 = true;
         tfodParameters.inputSize = 320;
         tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
         tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);


         if (tfod != null) {
             tfod.activate();
             tfod.setZoom(1, 16.0/9.0);
         }
    }


    public boolean scanForDuck() {
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals("Duck")) {
                        float width = Math.abs(recognition.getRight() - recognition.getLeft());
                        float height = Math.abs(recognition.getBottom() - recognition.getTop());
                        if (width < 300 && height < 300) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
