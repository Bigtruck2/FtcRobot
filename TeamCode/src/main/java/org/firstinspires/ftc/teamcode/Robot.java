package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.vuforia.ImageTarget;

import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.teamcode.util.MathUtil;


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

    public synchronized void setDirection(int yaw){
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
            setTurnPower(power);
        }
    }
    public synchronized void addDirection(int yaw){
        yaw = (int) MathUtil.combine(imu.getAngularOrientation().firstAngle,yaw);
        if(yaw>180){
            yaw-=360;
        }else{
            yaw+=360;
        }
        setDirection(yaw);
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
        powerRight = MathUtil.combine(powerRight,.3*power);
        right.setPower(powerRight);
        powerLeft = MathUtil.combine(powerLeft, .3*power);
        left.setPower(powerLeft);
    }
    public void setTurnPower(double power){
        setBoth(0);
        addTurnPower(power);
    }
}
