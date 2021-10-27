package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.ImageTarget;


public class Robot {
    private final DcMotor right;
    private final DcMotor left;
    private double powerRight,powerLeft = 0;
    private final BNO055IMU imu;
    public Robot(DcMotor right, DcMotor left, BNO055IMU imu){
        this.right = right;
        this.left = left;
        this.imu = imu;
    }

    public synchronized void setDirection(double yaw){
        byte power = 1;
        if(yaw > 0){
            power*=-1;

        }
        while(yaw == imu.getAngularOrientation().firstAngle){
            this.setBoth(0);
            this.addTurnPower(power);
        }
    }
    public synchronized void addDirection(double yaw){
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
        while (Math.abs(difference)>2){
            difference = (int) (yaw -imu.getAngularOrientation().firstAngle);
            this.setBoth(0);
            this.addTurnPower(power);
        }
    }
    //set left power
    public void setLeft(double d){
       left.setPower(-(0.5 *d));
       powerLeft = -(0.5 *d);
    }
    //set right power
    public void setRight(double d){
        right.setPower(-(0.5 *d));
        powerRight = -(0.5 *d);

    }
    //set speed of both motor
    public void setBoth(double d){
        left.setPower(-(0.5 *d));
        right.setPower(-(0.5 *d));
        powerLeft = -(0.5 *d);
        powerRight = -(0.5 *d);

    }
    //this is how all the turning is done
    //its added to the power it was set to
    public void addTurnPower(double power){
        if(powerRight<0) {
            right.setPower(powerRight - .3*power);
            powerRight = powerRight - .3*power;

        }else {
            right.setPower(powerRight + .3*power);
            powerRight = powerRight + .3*power;
        }
        if(powerLeft<0) {
            left.setPower(powerLeft + .3*power);
            powerLeft = powerLeft + .3*power;
        }else {
            left.setPower(powerLeft - .3*power);
            powerLeft = powerLeft - .3*power;
        }
    }
    public void setTurnPower(double power){
        if(powerRight<0) {
            right.setPower(-.3*power);
            powerRight = -.3*power;
        }else {
            right.setPower(.3*power);
            powerRight = .3*power;
        }
        if(powerLeft<0) {
            left.setPower(.3*power);
            powerLeft = .3*power;

        }else {
            left.setPower(-.3*power);
            powerLeft = -.3*power;
        }
    }
}
