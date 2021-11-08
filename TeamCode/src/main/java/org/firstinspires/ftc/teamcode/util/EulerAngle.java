package org.firstinspires.ftc.teamcode.util;

public class EulerAngle {
    private final double pitch;
    private final double yaw;
    private final double roll;
    //euler angle with pitch yaw and roll
    public EulerAngle(double pitch,double yaw, double roll){
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }
    //euler angle with pitch and yaw
    public EulerAngle(double pitch, double yaw){
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = 0;
    }
    //euler angle with yaw
    EulerAngle(double yaw){
        this.yaw = yaw;
        this.pitch = 0;
        this.roll = 0;
    }
    //returns yaw
    public double getYaw() {
        return yaw;
    }
    //returns pitch
    public double getPitch() {
        return pitch;
    }
    //returns roll
    public double getRoll() {
        return roll;
    }
    //returns string of each rotation
    public String toString(){
        return this.pitch + " " + this.yaw + " " + this.roll;
    }
    //converts euler angle to vector idk how it works https://stackoverflow.com/questions/1568568/how-to-convert-euler-angles-to-directional-vector
    public Vector toVector() {
        return new Vector(-Math.cos(pitch) * Math.sin(yaw),-Math.sin(pitch),Math.cos(pitch) * Math.cos(yaw));
    }
}
