package com.example.sarth.nyasihui;

public class  GPIO {
    public static  final int ZERO = 3;
    public static  final int ONE = 4;
    public static  final int TWO = 7;
    public static  final int THREE =8 ;
    public static  final int FOUR = 9;
    public static  final int FIVE = 10;
    public static  final int SIX = 11;
    public static  final int SEVEN = 12;

    public static final int getPinNo(int index){

        switch(index){
            case 0: return GPIO.ZERO;
            case 1: return GPIO.ONE;
            case 2: return GPIO.TWO;
            case 3: return GPIO.THREE;
            case 4: return GPIO.FOUR;
            case 5: return GPIO.FIVE;
            case 6: return GPIO.SIX;
            case 7: return GPIO.SEVEN;
        }
        return -1;
    }
}
