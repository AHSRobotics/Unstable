package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="Autonomous Test", group="Autonomous Tests")
public class TestForAnyRobot extends LinearOpMode{
    DcMotor motorLeft;
    DcMotor motorRight;

    final double FULL_POWER_FOWARD = 1.0;
    final double FULL_POWER_BACKWARDS = FULL_POWER_FOWARD * -1.0;
    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        motorRight.setDirection(DcMotor.Direction.REVERSE);

        while (opModeIsActive()) {
            motorLeft.setPower(FULL_POWER_FOWARD);
            motorRight.setPower(FULL_POWER_FOWARD);
            Thread.sleep(5000);
        }
    }
}
