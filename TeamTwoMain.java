package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.InputMismatchException;

@TeleOp(name = "Team Two 0.2.0", group = "Unstable Test")

public class TeamTwoMain extends LinearOpMode{

    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor sweeper;

    private boolean killSwitch = false;
    private DcMotor[] allMotors = {motorLeft, motorRight, sweeper};

    @Override
    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        sweeper = hardwareMap.dcMotor.get("sweeper");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        sweeper.setDirection(DcMotor.Direction.REVERSE);

        printMessage("> ", "Ready to start, Good Luck!");

        waitForStart();

        while(opModeIsActive()){
            if(!killSwitch) {
                motorLeft.setPower(-gamepad1.left_stick_y);
                motorRight.setPower(-gamepad1.right_stick_y);

                /* Controlling sweeper motor */
                if (gamepad1.left_trigger > 0.0) {
                    sweeper.setDirection(DcMotor.Direction.FORWARD); // Need to change direction based on button pressed
                    sweeper.setPower(-gamepad1.left_trigger);
                }

                if (gamepad1.right_trigger > 0.0) {
                    sweeper.setDirection(DcMotor.Direction.REVERSE);
                    sweeper.setPower(-gamepad1.right_trigger);
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
        }
    }

    public void flipKillSwitch(){
        this.killSwitch = (this.killSwitch) ? false : true;
    }
}



/* TODO
* Get things tested and ask if there is anything more
*/
