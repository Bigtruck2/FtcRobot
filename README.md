### Sks robotics

if you dont know how to code or dont know java this will be difficult to understand plz go learn to code here
https://www.w3schools.com/java/default.asp

good coding practice is important for a project this size
    -DON'T MAKE STATIC METHODS OR VARIABLES
    -make most if not all variables private and make getters and setters for them if needed
    -methods should mostly be public but if you want you can make it private if only accessed in the class its made in
    -ONE CLASS PER FILE
    -plz don't make silly variable method or class names
    -document your code so next people understand what u did
    - use correct number of tabs some people dont like pyramid code but it makes the code easier to read
    -put blank space if you think it makes it more clear and to separate different things in a method
    - if you use github make sure not to delete other peoples code
    - delete unused imports
    -if you add a library tell everyone and make sure to agree on it
    -upload code somewhere so you dont lose progress if something happens
    -if you think you could do a better job then rewrite it
    -talk to group before changing something
    -talk to group when coding something because there may be better ways to write thing or you might write something wrong and have to restart


download android studio here:
https://developer.android.com/studio
get android 10.0 NOT 11
new project from version control and put this link https://github.com/Bigtruck2/FtcRobot.git
or goto https://github.com/Bigtruck2/FtcRobot and download a zip of source code and import it into android studio
https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Android-Studio-Tutorial for more info on android studio

make edits to the code 
plug in a usb type a into your computer
plug in a usb type c into the ftc robot control device
select the TeamCode configuration and make sure the device says ftc robot control device or something similar
hit play and wait for the code to build
select the project with the android logo on the android phone

Dr. Feffer should be able to help with adding hardware and fixing problems with the robot or code

after making a robot variable and passing in the right and left motors, controls should be done like this:
robot.setBoth(gamepad1.left_stick_y);
robot.addTurnPower(gamepad1.left_stick_x);

robot.setBoth(gamepad1.left_stick_y); calls this method
public void setBoth(double d){
    left.setPower(-(0.5 *d));
    right.setPower(-(0.5 *d));
    powerLeft = -(0.5 *d);
    powerRight = -(0.5 *d);
}

robot.addTurnPower(gamepad1.left_stick_x); calls this method
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
left.setPower(); and right.setPower(powerRight + .3*power); have been replace by
public void setLeft(double d){
    left.setPower(-(0.5 *d));
    powerLeft = -(0.5 *d);
}
public void setRight(double d){
    right.setPower(-(0.5 *d));
    powerRight = -(0.5 *d);
}
this is to keep track of the motor power in the robot class DONT USE left.setPower(); and right.setPower(powerRight + .3*power);

the regular method for adding hardware was replaced by
public DcMotor motor(String s){
    hardwareList.add(hardwareMap.dcMotor.contains(s) ? hardwareMap.dcMotor.get(s) : null);
    return hardwareMap.dcMotor.contains(s) ? hardwareMap.dcMotor.get(s) : null;
}
public Object hardware(Class<?> c, String s){
    hardwareList.add(hardwareMap.get(c,s));
    return hardwareMap.get(c,s);
}
it is not necessary to use this method but it makes the code much cleaner and adds the hardware to a hardware list
the hardware list could be changed to a hashmap 
the hardware list is not currently used but can be later

EULER ANGLES ARE MEASURED IN RADIANS

https://en.wikipedia.org/wiki/Euclidean_vector
https://en.wikipedia.org/wiki/Euler_angles

Vectors can be turned into a euler angle which is a direction
not all parameters for the vectors and euler angles need to be filled
any vectors being made should have variables without data set to 0

with euler angles you should only use yaw but pitch and roll and there in case its needed
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
Euler angles can also be converted to normalized vectors
https://www.khanacademy.org/computing/computer-programming/programming-natural-simulations/programming-vectors/a/vector-magnitude-normalization#:~:text=To%20normalize%20a%20vector%2C%20therefore,the%20unit%20vector%20readily%20accessible.
public Vector normalize(){
    double length = Math.sqrt(x*x + y*y + z*z);
    x /= length;
    y /= length;
    z /= length;
    return this;
}

Vectors and euler angles wont really be useful for manual control but should be useful for autonomous mode
look on youtube for ftc robot tutorials if you need help with any of this or if you try to do something not covered by this readme
