package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.InputMismatchException;

@TeleOp(name = "Team Two 0.3.0", group = "Unstable Test")

public class TeamTwoMain extends LinearOpMode{

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor scissorMotorOne;
    DcMotor scissorMotorTwo;
    DcMotor sweeper;

    final double FULL_POWER = 1.0;

    @Override
    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        sweeper = hardwareMap.dcMotor.get("sweeper");
        //scissorMotorOne = hardwareMap.dcMotor.get("scissorMotorOne");
        //scissorMotorTwo = hardwareMap.dcMotor.get("scissorMotorTwo");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        sweeper.setDirection(DcMotor.Direction.REVERSE);

        printMessage("> ", "Ready to start, Good Luck!");

        waitForStart();

        while(opModeIsActive()){
            motorLeft.setPower(gamepad1.left_stick_y);
            motorRight.setPower(gamepad1.right_stick_y);

            /* Controlling sweeper motor */
            if (gamepad1.left_trigger > 0.0) {
                sweeper.setDirection(DcMotor.Direction.FORWARD); // Need to change direction based on button pressed
                sweeper.setPower(-gamepad1.left_trigger);
            }

            if (gamepad1.right_trigger > 0.0) {
                sweeper.setDirection(DcMotor.Direction.REVERSE);
                sweeper.setPower(-gamepad1.right_trigger);
            }

            if(gamepad1.right_trigger == 0 && gamepad1.right_trigger == 0)
                sweeper.setPower(0);

            /* Controlling the scissor action */
            /*if(gamepad2.right_trigger > 0){
                scissorMotorOne.setPower(FULL_POWER);
                scissorMotorTwo.setPower(-FULL_POWER);
            }*/

            /*if(gamepad2.left_trigger > 0){
                scissorMotorOne.setPower(-FULL_POWER);
                scissorMotorTwo.setPower(FULL_POWER);
            }*/

            /*if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0){
                scissorMotorOne.setPower(0);
                scissorMotorTwo.setPower(0);
            }*/

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
        }
    }
}



/* TODO
* Get things tested and ask if there is anything more
*/