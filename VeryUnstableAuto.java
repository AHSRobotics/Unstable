package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import java.util.InputMismatchException;

@Autonomous(name = "Auto Test", group = "Autonomous")
@Disabled // Will be removed once we are going to test
public class VeryUnstableAuto extends LinearOpMode{

    /* All bots will have these, autonomous will be shaped for the bot */
    DcMotor motorLeft;
    DcMotor motorRight;

    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");

        printMessage("> ", "Please Work!");

        waitForStart();

        /* The loop may need to get removed */
        while(opModeIsActive()){
            moveFoward(1.0, 2000);
        }

    }

    /* Movement methods */
    public void moveLeft(double power, int sleepTime) throws InterruptedException{
        ;
    }

    public void moveRight(double power, int sleepTime) throws InterruptedException{
        ;
    }

    public void moveFoward(double power, int sleepTime) throws InterruptedException{
        motorLeft.setPower(power);
        motorRight.setPower(power);

        Thread.sleep(sleepTime);
    }

    public void moveReverse(double power, int sleepTime) throws InterruptedException{
        moveFoward(-power, sleepTime);
    }

    public void printMessage(String prompt, String message){
        try {
            telemetry.addData(prompt, message);
            telemetry.update();
        }

        catch(InputMismatchException e){
            telemetry.addData("WARNING: ", "A call to \"printMessage\" has an incorrect data type entered as one of the arguments. Talk to one of the programmers to fix the problem.");
            telemetry.update();
        }
    }

}

