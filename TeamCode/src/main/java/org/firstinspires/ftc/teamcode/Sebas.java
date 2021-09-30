package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.TouchSensor;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.ArrayList;

@TeleOp(name = "Sebas")
public class Sebas extends LinearOpMode {
    //create variable here so they can be accessed by other methods keep them private if possible and make final if you don't want them to change
    //if you need to make a variable public then make getters and setters
    //DON'T MAKE STATIC METHODS OR VARIABLES
    //make an instance of the class if you want to access the method or variable

    private DcMotor right;
    private DcMotor left;
    private TouchSensor touchSensor;
    private ColorRangeSensor color;
    private final ArrayList<Object> hardwareList = new ArrayList<>();
    private Robot robot;
    @Override
    public void runOpMode() {
        //get all hardware here
        right = motor("rightMotor");
        left = motor("leftMotor");
        robot = new Robot(right,left);
        touchSensor = (TouchSensor) hardware(TouchSensor.class,"touch");
        color = (ColorRangeSensor) hardware(ColorRangeSensor.class, "color");
        //get the robot ready to run
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (!touchSensor.isPressed()) {
                    if(color.getDistance(DistanceUnit.CM)>5) {
                        robot.setBoth(gamepad1.left_stick_y);
                        robot.addTurnPower(gamepad1.left_stick_x);
                        telemUpdate();
                    }else {
                        robot.setBoth(0);
                    }
                }else {
                    robot.setBoth(0);
                }
            }
        }
    }
    //get motor quickly
    public DcMotor motor(String s){
        hardwareList.add(hardwareMap.dcMotor.contains(s) ? hardwareMap.dcMotor.get(s) : null);
         return hardwareMap.dcMotor.contains(s) ? hardwareMap.dcMotor.get(s) : null;
    }
    //get any hardware quickly
    public Object hardware(Class<?> c, String s){
        hardwareList.add(hardwareMap.get(c,s));
        return hardwareMap.get(c,s);
    }
    //telemetry is the things shown on the android phone
    //its useful because it provides data and tells you whats working and whats not
    public void telemUpdate(){
        telemetry.addData("Left Power", left.getPower());
        telemetry.addData("Right Power", right.getPower());
        telemetry.addData("Touch Sensor Pressed", touchSensor.isPressed());
        telemetry.addData("Distance of color sensor cm",color.getDistance(DistanceUnit.CM));
        telemetry.update();
    }
}
