package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.ArrayList;

@TeleOp(name = "Robot Code")
public class MainLoop extends LinearOpMode {
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
    private BNO055IMU imu;
    private final BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    //private Camera camera;
    @Override
    public void runOpMode() {
        //get all hardware here
        right = motor("rightMotor");
        left = motor("leftMotor");

        imu = hardwareMap.get(BNO055IMU.class,"imu");
        robot = new Robot(right,left,imu);
        touchSensor = (TouchSensor) hardware(TouchSensor.class,"touch");
        color = (ColorRangeSensor) hardware(ColorRangeSensor.class, "color");
        //camera = (Camera) hardware(Camera.class,"camera");
        //get the robot ready to run
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.temperatureUnit = BNO055IMU.TempUnit.FARENHEIT;
        imu.initialize(parameters);

        waitForStart();
        if (opModeIsActive()) {
            robot.setBoth(0);
            Thread thread = new Thread(new Server(robot));
            thread.start();
            while (opModeIsActive()) {
                if (!touchSensor.isPressed()) {
                        robot.setBoth(gamepad1.left_stick_y);
                        robot.addTurnPower(gamepad1.left_stick_x);
                }else {
                    robot.setBoth(0);
                }
                telemUpdate();
            }
            robot.setRunning(false);
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        telemetry.addData("Angular Orientation",imu.getAngularOrientation().toString());
        telemetry.update();
    }
}
