package pt.ua.deti.fff.parsers.dataTypes;

/**
 *
 * @author Francisco Vaz, nº 26572
 */
public class FuelType {
    private int value;
    private String type;

    public FuelType(int value, String type)
    {
        this.value = value;
        this.type = type;
    }

    public int getValue()
    {
        return value;
    }

    public String getType()
    {
        return type;
    }
}
