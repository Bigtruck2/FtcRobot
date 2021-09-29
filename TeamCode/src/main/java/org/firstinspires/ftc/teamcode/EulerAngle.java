package org.firstinspires.ftc.teamcode;

public class EulerAngle {
    private final double pitch;
    private final double yaw;
    private final double roll;
    public EulerAngle(double pitch,double yaw, double roll){
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }
    public EulerAngle(double pitch, double yaw){
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = 0;
    }
    EulerAngle(double yaw){
        this.yaw = yaw;
        this.pitch = 0;
        this.roll = 0;
    }

    public double getYaw() {
        return yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public double getRoll() {
        return roll;
    }
    public String toString(){
        return this.pitch + " " + this.yaw + " " + this.roll;
    }

    public Vector toVector() {
        return new Vector(-Math.cos(pitch) * Math.sin(yaw),-Math.sin(pitch),Math.cos(pitch) * Math.cos(yaw));
    }
}
