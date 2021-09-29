package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.hardware.TouchSensor;


import java.util.ArrayList;

@TeleOp(name = "Sebas")
public class Sebas extends LinearOpMode {
    private DcMotor right;
    private DcMotor left;
    private DcMotor arm;
    private TouchSensor touchSensor;
    private final ArrayList<Object> hardwareList = new ArrayList<>();
    @Override
    public void runOpMode() {
        right = motor("rightMotor");
        left = motor("leftMotor");
        arm = motor("armMotor");
        Robot robot = new Robot(right,left);
        touchSensor = (TouchSensor) hardware(TouchSensor.class,"touch");
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (!touchSensor.isPressed()) {
                    robot.setBoth(gamepad1.left_stick_y);
                    robot.addTurnPower(gamepad1.left_stick_x);
                    arm.setPower(-(.5 * gamepad1.right_stick_y));
                    telemUpdate();
                }
            }
        }
    }
    public DcMotor motor(String s){
        hardwareList.add(hardwareMap.dcMotor.contains(s) ? hardwareMap.dcMotor.get(s) : null);
         return hardwareMap.dcMotor.contains(s) ? hardwareMap.dcMotor.get(s) : null;
    }
    public Object hardware(Class c, String s){
        hardwareList.add(hardwareMap.get(c,s));
        return hardwareMap.get(c,s);
    }
    public void telemUpdate(){
        telemetry.addData("Left Power", left.getPower());
        telemetry.addData("Right Power", right.getPower());
        telemetry.addData("Arm Power",arm.getPower());
        telemetry.addData("Touch Sensor Pressed", touchSensor.isPressed());
        telemetry.update();
    }
}
