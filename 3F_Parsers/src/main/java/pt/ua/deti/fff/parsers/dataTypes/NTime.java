/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.parsers.dataTypes;

/**
 *
 * @author Francisco Vaz, nº 26572
 */
public class NTime {
    int nHour;
    int nMinute;
    int nSecond;

    public NTime(int hour, int minute, int second)
    {
        if (hour < 0 || hour > 23 || minute < 0 || minute >= 59 || second < 0 || second > 59)
            throw new IllegalArgumentException();
                
        this.nHour = hour;
        this.nMinute = minute;
        this.nSecond = second;
    }

    public int getHour()
    {
        return this.nHour;
    }

    public int getMinute()
    {
        return this.nMinute;
    }

    public int getSecond()
    {
        return nSecond;
    }    
}
