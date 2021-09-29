package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;


public class Robot {
    private DcMotor right, left;
    private double powerRight,powerLeft = 0;
    public Robot(DcMotor right, DcMotor left){
        this.right = right;
        this.left = left;
    }
    public void setDir(){

    }
    public void setLeft(double d){
       left.setPower(-(0.5 *d));
       powerLeft = -(0.5 *d);
    }
    public void setRight(double d){
        right.setPower(-(0.5 *d));
        powerRight = -(0.5 *d);

    }
    public void setBoth(double d){
        left.setPower(-(0.5 *d));
        right.setPower(-(0.5 *d));
        powerLeft = -(0.5 *d);
        powerRight = -(0.5 *d);

    }
    public void addPower(double power){
        if(powerRight<0) {
            right.setPower(powerRight + .5*power);
            powerRight = powerRight + .5*power;
        }else {
            right.setPower(powerRight - .5*power);
            powerRight = powerRight - .5*power;
        }
        if(powerLeft<0) {
            left.setPower(powerLeft + .5*power);
            powerLeft = powerLeft + .5*power;

        }else {
            left.setPower(powerLeft - .5*power);
            powerLeft = powerLeft - .5*power;
        }
    }
    public void addTurnPower(double power){
        if(powerRight<0) {
            right.setPower(powerRight + .3*power);
            powerRight = powerRight + .3*power;
        }else {
            right.setPower(powerRight - .3*power);
            powerRight = powerRight - .3*power;
        }
        if(powerLeft<0) {
            left.setPower(powerLeft - .3*power);
            powerLeft = powerLeft - .3*power;

        }else {
            left.setPower(powerLeft + .3*power);
            powerLeft = powerLeft + .3*power;
        }
    }
}
