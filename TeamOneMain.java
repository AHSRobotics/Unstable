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

    final double INCREMENTER = 0.01; // Used to control the strafing wheel
    private boolean killSwitch = false; // Will determine if the motors will run
    private DcMotor[] allMotors = {motorLeft, motorRight, motorMiddle, motorPoker}; // Used to shut down all of the motors

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialization
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        motorPoker = hardwareMap.dcMotor.get("motorPoker");

        motorRight.setDirection(DcMotor.Direction.REVERSE);

        printMessage("> ", "Ready to start, Good Luck!");
        waitForStart();

        double middleSpeed = 0.0;

        while (opModeIsActive()) {
            if(!killSwitch) {

                motorRight.setPower(-gamepad1.right_stick_y);
                motorLeft.setPower(-gamepad1.left_stick_y);

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
* Add something that could mess around with servos
* */
