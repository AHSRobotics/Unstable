package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.InputMismatchException;


@TeleOp(name = "Team One 0.5.0", group = "Unstable Test")

public class TeamOneMain extends LinearOpMode{

    /*Movement Wheels*/
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorMiddle;

    /*Launcher*/
    DcMotor motorLauncher;
    DcMotor motorLauncherTwo;

    final double INCREMENTER = 0.01; // Used to control the strafing wheel
    final double FULL_POWER = 1.0;
    @Override
    public void runOpMode() throws InterruptedException {

        // Initialization
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorMiddle = hardwareMap.dcMotor.get("motorMiddle");
        motorLauncher = hardwareMap.dcMotor.get("motorLauncher");
        motorLauncherTwo = hardwareMap.dcMotor.get("motorLauncherTwo");

        motorRight.setDirection(DcMotor.Direction.REVERSE);

        printMessage("> ", "Ready to start, Good Luck!");
        waitForStart();

        double middleSpeed = 0.0;

        while (opModeIsActive()) {


            motorRight.setPower(gamepad1.right_stick_y);
            motorLeft.setPower(gamepad1.left_stick_y);

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


            /* The Launcher */
            if(gamepad2.right_trigger > 0){
                motorLauncher.setPower(-FULL_POWER);
                motorLauncherTwo.setPower(-FULL_POWER);
            }
            else if(gamepad2.right_trigger <= 0) {
                motorLauncher.setPower(0);
                motorLauncherTwo.setPower(0);
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
}

/*
* TODO
* Get the fifth motor working
* */

/*
* NOTE
* If there is an error with the code than it is probably with the motor
* * */