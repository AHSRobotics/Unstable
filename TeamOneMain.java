package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import java.util.InputMismatchException;

@TeleOp(name = "Team One 0.1.0", group = "Unstable Test")

public class TeamOneMain extends LinearOpMode{
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor sweeper;

    @Override
    public void runOpMode() throws InterruptedException{
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        sweeper = hardwareMap.dcMotor.get("sweeper");

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        printMessage("> ", "Ready to start, Good Luck!");

        waitForStart();

        while(opModeIsActive()){
            motorLeft.setPower(-gamepad1.left_stick_y);
            motorRight.setPower(-gamepad1.right_stick_y);

            /*TODO
            * Ask Jeff what buttons he wants for this robots sweeper control
            */

            sweeper.setPower(-gamepad1.right_trigger);

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
* Ask Jeff if he wants to add a killswitch to the bot, the same one that is in Kyles code
*/