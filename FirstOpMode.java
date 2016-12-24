// This is basically a rewrite of TeleOpTutorial.java written from what I can remember
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // Importing
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/* import com.qualcomm.robotcore.hardware.DcMotorSimple; */

@TeleOp(name = "Tests 0.3.3", group = "Unstable Test") // The name and group that should show up on the driver station

public class FirstOpMode extends LinearOpMode{

    /* Creating variables with the access modifier of private and of type DcMotor */
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;
    DcMotor motorPoker;
    DcMotor motorLoader;

    final double INCREMENTER = 0.01;

    @Override
    public void runOpMode() throws InterruptedException { // The method that is called once the Op Mode is selected

        // Initialization
        motorLeft = hardwareMap.dcMotor.get("motorLeft"); // Need to name the motor this in the configuration on the phone
        addMessage("Left Motor Stats: ", "GOOD");

        motorRight = hardwareMap.dcMotor.get("motorRight"); // Same here
        addMessage("Right Motor Stats: ", "GOOD");

        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        addMessage("Middle Motor Stats: ", "GOOD");

        motorPoker = hardwareMap.dcMotor.get("motorPoker");
        addMessage("Poker Motor Stats: ", "GOOD");

        motorLoader = hardwareMap.dcMotor.get("motorLoader");
        addMessage("Loader Motor Stats: ", "GOOD");

        motorRight.setDirection(DcMotor.Direction.REVERSE); // This is suppose to stop the bot from turning in circles because the motors by default run in opposite directions

        // Waiting for the okay from the driver
        telemetry.addData("> ", "Start");
        telemetry.update();
        waitForStart();

       double middleSpeed = 0.0;

        while (opModeIsActive()) {
            /* Main driving controls */
            motorLeft.setPower(-gamepad1.right_stick_y); // The left stick y axis controls the left motor
            motorRight.setPower(-gamepad1.left_stick_y); // The right stick y axis controls the right motor

            /* Middle Strafing Controls */
            if(gamepad2.dpad_left){
                middleSpeed -= INCREMENTER;

                if(middleSpeed >= -1.0){
                    motorMiddle.setPower(middleSpeed);
                }else{
                    middleSpeed = -1.0;
                }

            }

            if(gamepad2.dpad_right){
                middleSpeed += INCREMENTER;

                if(middleSpeed <= 1.0){
                    motorMiddle.setPower(middleSpeed);
                }else{
                    middleSpeed = 1.0;
                }
            }

            // Turns off the motor
            if(!gamepad2.dpad_left && !gamepad2.dpad_right){
                middleSpeed = 0; // Has to be to zero to create the acceleration effect
                motorMiddle.setPower(middleSpeed);
            }


           /* The Poker */

            // Turning off the controller if nothing is being pressed
            if(!gamepad2.a && !gamepad2.b){
                motorPoker.setPower(0);

                // Some safety checking
                if(motorPoker.getPower() != 0.0) {
                    telemetry.addData("Condition: ", "UNSAFE POWER: " + Double.toString(motorPoker.getPower()));
                }else{
                    telemetry.addData("Condition: ", "SAFE POWER: " + Double.toString(motorPoker.getPower()));
                }

                telemetry.update();
            }

            if(gamepad2.a) {
                motorPoker.setPower(1.0);
            }

            // Putting the poker back in

            if(gamepad2.b){
                motorPoker.setPower(-1.0);
            }


            /* The Loader (button is subject to change) */
            if(gamepad1.x){
                motorLoader.setPower(1.0);
            }

            idle();
        }
    }

    public void addMessage(String prompt, String message){
        telemetry.addData(prompt, message);
        telemetry.update();
    }

}

/*
* TODO
* Add in the Beacon Pointer + Strafing controller 2
* Add Drive Wheels + Loader Bar controller 1
* Get rid of the reverse comments if we don't need them
* Change the names to make sense
* Add Killswitch
*
* NOTE
* The motors for left and right do not control what there name says it does. Left = Right, Right = Left
* */
