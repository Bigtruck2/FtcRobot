package org.firstinspires.ftc.teamcode.util;

public class MathUtil {
    //only class u can make static methods
    public static double combine(double first, double second){
        if(first>0) {
            return first + second;
        }else {
            return first - second;
        }
    }
    public static double diffrence(double first, double second){
        if(first<0) {
            return first + second;
        }else {
            return first - second;
        }
    }
}
