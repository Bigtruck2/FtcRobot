package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.MathUtil;


public class Robot {
    private final DcMotor right;
    private final DcMotor left;
    private double powerRight,powerLeft = 0;
    private final BNO055IMU imu;
    private boolean running = false;
    private Telemetry telemetry;
    private boolean busy;
    //constructor
    public Robot(DcMotor right, DcMotor left, BNO055IMU imu, Telemetry telemetry){
        this.right = right;
        this.left = left;
        this.imu = imu;
        this.telemetry = telemetry;
    }
    //be careful when calling this method on the main thread
    //example setDirection(179.54);
    //yaw = 0 to 180 and -0 to -180
    //adds angle to starting angle and points that direction
    public synchronized void setDirection(double yaw){
        setBusy(true);
        byte power = 1;
        int difference = (int) (yaw -imu.getAngularOrientation().firstAngle);
        if(Math.abs(difference)<180){
            if(difference>0){
                power*=-1;
            }
        }else {
            if(difference<0){
                power*=-1;
            }
        }
        setTurnPower(power);
        while (Math.abs(difference)>2){
            difference = (int) (yaw -imu.getAngularOrientation().firstAngle);
        }
        setBusy(false);
    }
    //be careful when calling this method on the main thread
    //example addDirection(179.54);
    //yaw = 0 to 180 and -0 to -180
    //turns robot to the angle from current angle
    public void addDirection(double yaw){
        yaw = (int) imu.getAngularOrientation().firstAngle+yaw;
        if(yaw>180){
            yaw-=360;
        }else if(yaw < -180){
            yaw+=360;
        }
        setDirection(yaw);
    }
    //set left power
    //d = -1 to 1 example setLeft(-0.3);
    public void setLeft(double d){
       left.setPower(-(0.5 *d));
       powerLeft = -(0.5 *d);
    }
    //set right power
    //example setRight(-0.3);
    public void setRight(double d){
        right.setPower(-(0.5 *d));
        powerRight = -(0.5 *d);

    }
    //set speed of both motor
    //example setBoth(-0.3);
    public void setBoth(double d){
        left.setPower(-(0.5 *d));
        right.setPower(-(0.5 *d));
        powerLeft = -(0.5 *d);
        powerRight = -(0.5 *d);
    }
    //this is how all the turning is done
    //its added to the power it was set to
    //example addTurnPower(-.5)
    public void addTurnPower(double power){
        powerRight = MathUtil.combine(powerRight, -.3*power);
        right.setPower(powerRight);
        powerLeft = MathUtil.combine(powerLeft, .3*power);
        left.setPower(powerLeft);
    }
    //sets power to 0 then calls addTurnPower();
    public void setTurnPower(double power){
        setBoth(0);
        addTurnPower(power);
    }
    //idk if this works
    public synchronized void move(double meters){
        //0.284628294 circumference
        //560 = 1 rotation
        setBusy(true);
        right.setTargetPosition((int)(right.getCurrentPosition()+((meters/0.284628294)*560)+.5));
        left.setTargetPosition((int) (left.getCurrentPosition()+((meters/0.284628294)*560)+.5));
        while (right.isBusy()||left.isBusy()){

        }
        setBusy(false);
    }

    //variable getters and setters
    //mostly booleans
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public boolean isBusy() {
        return busy;
    }
}
