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

public FreightLevel getLevel() {
    if (robot.leftObjectDistance < 5)
        return FreightLevel.level1;
    else if (robot.rightObjectDistance < 5)
        return FreightLevel.level3;
    else return FreightLevel.level2;

}

}