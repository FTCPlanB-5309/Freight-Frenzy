import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FindTeamFreight {

    MechanumHardware robot;
    Telemetry telemetry;
    LinearOpMode linearOpMode;

    public FindTeamFreight(MechanumHardware robot, Telemetry telemetry, LinearOpMode linearOpMode){
        this.robot = robot;
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
    }

public int getLevel(SetupDirection sd) {
        FreightLevel level;
    if (sd == SetupDirection.forward) {
        if (robot.leftObjectDistance < 7)
            return robot.LEVEL_ONE_HEIGHT;
        else if (robot.rightObjectDistance < 7)
            return robot.LEVEL_THREE_HEIGHT;
        else return robot.LEVEL_TWO_HEIGHT;
    }
        else {
            if (robot.leftObjectDistance < 7)
                return robot.LEVEL_THREE_HEIGHT;
            else if (robot.rightObjectDistance < 7)
                return robot.LEVEL_ONE_HEIGHT;
            else return robot.LEVEL_TWO_HEIGHT;

        }

 }

}