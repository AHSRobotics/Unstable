// This is basically a rewrite of TeleOpTutorial.java written from what I can remember
package com.example.benjamin_tibnam.ftc;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // Importing
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/* import com.qualcomm.robotcore.hardware.DcMotorSimple; */

@TeleOp(name = "Blue Pride Night", group = "Unstable Test") // The name and group that should show up on the driver station

public class FirstOpMode extends LinearOpMode{

    /* Creating variables with the access modifier of private and of type DcMotor */
    private DcMotor motorLeft;
    private DcMotor motorRight;

    /*
    * Note For Team 2
    *
    * The variable defined below is for Team 1's bot middle motor
    * If this is Team 2 delete all lines of code with the // !! comment next to it
    * */
    private final double MIDDLE_MOTOR_INCREMENTAL = 0.01;
    private final int TEAM = 2;

    @Override
    public void runOpMode() throws InterruptedException { // The method that is called once the Op Mode is selected

        // Initialization
        motorLeft = hardwareMap.dcMotor.get("motorLeft"); // Need to name the motor this in the configuration on the phone
        motorRight = hardwareMap.dcMotor.get("motorRight"); // Same here
        motorLeft.setDirection(DcMotor.Direction.REVERSE); // This is suppose to stop the bot from turning in circles because the motors by default run in opposite directions

        // Waiting for the okay from the driver
        telemetry.addData("> ", "Ready to start");
        telemetry.update();
        waitForStart();

        int loops = 0;

        while (opModeIsActive()) {
            motorLeft.setPower(-gamepad1.left_stick_y); // The left stick y axis controls the left motor
            motorRight.setPower(-gamepad1.right_stick_y); // The right stick y axis controls the right motor

            if(gamepad1.a){
                writeAndUpdate("Button Pressed: ", "A");
            }else if(gamepad1.b){
                writeAndUpdate("Button Pressed: ", "B");
            }else if(gamepad1.x){
                writeAndUpdate("Button Pressed: ", "X");
            }else if(gamepad1.y){
                writeAndUpdate("Button Pressed: ", "Y");
            }

            writeAndUpdate("Total Times Ran: ", Integer.toString(++loops));

            idle();
        }
    }

    public void writeAndUpdate(String prompt, String content){
        telemetry.addData(prompt, content);
        telemetry.update();
    }
}

/*
* TODO
* Update apps version of 'FirstOpMode.java'
*
* */
