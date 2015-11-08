package pt.ua.deti.fff.parsers.dataTypes;

/**
 *
 * @author Francisco Vaz, nº 26572
 */

public enum Stab {
    ONE(1), TWO(2), THREE(3);
    private int value;
 
    private Stab(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return value;
    }
}