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

             // The TensorFlow software will scale the input images from the camera to a lower resolution.
             // This can result in lower detection accuracy at longer distances (> 55cm or 22").
             // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
             // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
             // should be set to the value of the images used to create the TensorFlow Object Detection model
             // (typically 16/9).
             tfod.setZoom(1, 16.0/9.0);
         }
    }
    /**
     * Test method for detecting ducks, adapted from example.
     * It will display a brief label for non-duck objects detected, with
     * telemetry data for ducks including size and confidence.
     * If a duck's size is more than 300 in width or height,
     * it will not be counted as it's likely a false positive
     * (real ducks should be less than  275 in either direction).
     * @return true if a normal-sized duck is found, false if there are no ducks or the ducks detected are likely not real
     */
    public boolean scanForDuck() {
        if (tfod != null) {
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                int i = 0;
                for (Recognition recognition : updatedRecognitions) {
                    telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    if (recognition.getLabel().equals("Duck")) {
                        float width = Math.abs(recognition.getRight() - recognition.getLeft());
                        float height = Math.abs(recognition.getBottom() - recognition.getTop());
                        if (width < 300 && height < 300) {
                            i++;
                            return true;
                        }
                        telemetry.addData(" Size (lr)", width);
                        telemetry.addData(" Size (tb)", height);
                        telemetry.addData(" Confidence", recognition.getConfidence());
                    }
                }
                telemetry.update();
            }
        }
        return false;
    }
}
