package br.com.mrocigno.estalagemnerd.utils;

public class TimerUtils {

    public static final int MILI_HOURS = 3600000;
    public static final int MILI_MINUTES = 60000;

    public static String transformMilisegs(int milisegs){
        int hours = milisegs / MILI_HOURS;
        int minutes = (milisegs % MILI_HOURS) / 60000;
        int seconds = ((milisegs % MILI_HOURS) % 60000) / 1000;
        return (hours > 0? hours + ":" : "") + (minutes < 10? "0":"") + minutes + ":"  + (seconds < 10? "0":"") + seconds;
    }

}
