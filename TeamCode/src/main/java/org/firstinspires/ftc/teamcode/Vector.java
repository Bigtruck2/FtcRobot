package org.firstinspires.ftc.teamcode;

public class Vector {
    private double x;
    private double y;
    private double z;
    public Vector(double x,double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }
    public Vector normalize(){
        double length = length();
        x /= length;
        y /= length;
        z /= length;
        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    public Vector multiply(double d){
        this.x *= d;
        this.y *= d;
        this.z *= d;
        return this;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setLength(double length) {
        this.normalize().multiply(length);
    }
    public void add(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
    }
    public void subtract(Vector vector){
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
    }

    public EulerAngle toEulerAngle(){
        double doublePi = 2 * Math.PI;
        double yawAtan = Math.atan2(-x, z);
        double yaw = (yawAtan + doublePi) % doublePi;
        double pitch = Math.atan(-this.y / Math.sqrt(x*x + z*z));
        return new EulerAngle(pitch,yaw);
    }
    public String toString(){
        return this.x + " " + this.y + " " + this.z;
    }
    public double toDouble(){
        return this.x + this.y + this.z;
    }
}
