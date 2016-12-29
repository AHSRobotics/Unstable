package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.InputMismatchException;


@TeleOp(name = "Team One 0.4.1", group = "Unstable Test")

public class TeamOneMain extends LinearOpMode{

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;
    DcMotor motorPoker;

    final double INCREMENTER = 0.01;
    private boolean killSwitch = false;
    private DcMotor[] allMotors = {motorLeft, motorRight, motorMiddle, motorPoker};

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialization
        motorLeft = hardwareMap.dcMotor.get("motorLeft"); // Need to name the motor this in the configuration on the phone
        printMessage("Left Motor Stats: ", "GOOD");

        motorRight = hardwareMap.dcMotor.get("motorRight"); // Same here
        printMessage("Right Motor Stats: ", "GOOD");

        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        printMessage("Middle Motor Stats: ", "GOOD");

        motorPoker = hardwareMap.dcMotor.get("motorPoker");
        printMessage("Poker Motor Stats: ", "GOOD");

        motorRight.setDirection(DcMotor.Direction.REVERSE); // This is suppose to stop the bot from turning in circles because the motors by default run in opposite directions

        // Waiting for the okay from the driver
        printMessage("> ", "Ready to start, Good Luck!");
        waitForStart();

        double middleSpeed = 0.0;

        while (opModeIsActive()) {
            if(!killSwitch) {
                /* Main driving controls */
                motorRight.setPower(-gamepad1.right_stick_y); // The left stick y axis controls the left motor
                motorLeft.setPower(-gamepad1.left_stick_y); // The right stick y axis controls the right motor

                /* Middle Strafing Controls */
                if (gamepad2.dpad_left) {
                    middleSpeed -= INCREMENTER;

                    if (middleSpeed >= -1.0) {
                        motorMiddle.setPower(middleSpeed);
                    } else {
                        middleSpeed = -1.0;
                    }

                }

                if (gamepad2.dpad_right) {
                    middleSpeed += INCREMENTER;

                    if (middleSpeed <= 1.0) {
                        motorMiddle.setPower(middleSpeed);
                    } else {
                        middleSpeed = 1.0;
                    }
                }

                // Turns off the motor
                if (!gamepad2.dpad_left && !gamepad2.dpad_right) {
                    middleSpeed = 0; // Has to be to zero to create the acceleration effect
                    motorMiddle.setPower(middleSpeed);
                }


               /* The Poker */

                // Turning off the controller if nothing is being pressed
                if (!gamepad2.a && !gamepad2.b) {
                    motorPoker.setPower(0);

                    // Some safety checking
                    if (motorPoker.getPower() != 0.0) {
                        telemetry.addData("Condition: ", "UNSAFE POWER: " + Double.toString(motorPoker.getPower()));
                    } else {
                        telemetry.addData("Condition: ", "SAFE POWER: " + Double.toString(motorPoker.getPower()));
                    }

                    telemetry.update();
                }

                if (gamepad2.a) {
                    motorPoker.setPower(1.0);
                }

                // Putting the poker back in

                if (gamepad2.b) {
                    motorPoker.setPower(-1.0);
                }




            }else{
                printMessage("WARNING: ", "THE KILLSWITCH HAS BEEN ACTIVATE, ALL MOTORS STOP. DEACTIVATE KILLSWITCH TO CONTINUE DRIVING");
            }

            /* Killswitch */
            if(gamepad1.back && gamepad1.start && gamepad1.a || gamepad2.back && gamepad2.start && gamepad2.a){
                flipKillSwitch();

                for(DcMotor motor : this.allMotors){
                    motor.setPower(0.0);
                }
            }

            idle();
        }
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

    public void flipKillSwitch(){
        this.killSwitch = (this.killSwitch) ? false : true;
    }

}

/*
* TODO
* Test Killswitch
* Add Drive Wheels + Loader Bar controller 1
* */