package org.firstinspires.ftc.teamcode;

public class Vector {
    private double x;
    private double y;
    private double z;
    //create vector
    public Vector(double x,double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    //create vector and set the lengths later
    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    //the length of the vector
    public double length() {
        return Math.sqrt(x*x + y*y + z*z);
    }
    //a vector that has a length of 1
    public Vector normalize(){
        double length = length();
        x /= length;
        y /= length;
        z /= length;
        return this;
    }
    //returns x
    public double getX() {
        return x;
    }
    //returns y
    public double getY() {
        return y;
    }
    //returns z
    public double getZ() {
        return z;
    }

    //change length of the vector without effecting direction
    public Vector multiply(double d){
        this.x *= d;
        this.y *= d;
        this.z *= d;
        return this;
    }
    //change x value
    public void setX(double x) {
        this.x = x;
    }
    //change y value
    public void setY(double y) {
        this.y = y;
    }
    //change z value
    public void setZ(double z) {
        this.z = z;
    }
    //normalize vector and multiply it
    public void setLength(double length) {
        this.normalize().multiply(length);
    }
    //add vectors together like in physics class just with 3 dimensions
    public void add(Vector vector){
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
    }
    //subtract vectors https://www.varsitytutors.com/hotmath/hotmath_help/topics/adding-and-subtracting-vectors
    public void subtract(Vector vector){
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
    }
    //converts vector to an angle with yaw and pitch. does not have length when converted
    public EulerAngle toEulerAngle(){
        double doublePi = 2 * Math.PI;
        double yawAtan = Math.atan2(-x, z);
        double yaw = (yawAtan + doublePi) % doublePi;
        double pitch = Math.atan(-this.y / Math.sqrt(x*x + z*z));
        return new EulerAngle(pitch,yaw);
    }
    //returns each value of vector
    public String toString(){
        return this.x + " " + this.y + " " + this.z;
    }
    //returns length of vector
    public double toDouble(){
        return length();
    }
}
