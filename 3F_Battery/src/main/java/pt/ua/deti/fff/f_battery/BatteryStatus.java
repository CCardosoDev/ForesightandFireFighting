package pt.ua.deti.fff.f_battery;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Francisco Vaz, nº 26572 <a26572@ua.pt>
 */
public class BatteryStatus {
    
    /** A map containing the firemans' battery status.
     * The key is the fireman's ID and the value is the battery status
     */
    private Map<Integer, Integer> batteryStatus;
    
    /** The value at wich a warning is thrown
     * 
     */
    private int limit;
    
    /** Class constructor
     * 
     */
    public BatteryStatus()
    {
        this.batteryStatus = new HashMap();
    }
    
    /** Sets the fireman's battery status.
     * 
     * @param fireman_id the firemans ID
     * @param status the battery status
     */
    public synchronized void setStatus(int fireman_id, int status) throws LowBatteryWarningException
    {
        this.batteryStatus.put(fireman_id, status);
        if (status < limit)
            throw new LowBatteryWarningException(fireman_id, status);
    }

    /** Gets the fireman's battery status.
     * 
     * @param fireman_id the firemans ID
     * @return the battery status
     */
    public synchronized int get(int fireman_id)
    {
        return this.batteryStatus.get(fireman_id);
    }

    /** Gets the battery warning defined limit
     * 
     * @return the limit at wich a warning is thrown
     */
    public int getLimit() 
    {
        return limit;
    }
    
    /** Sets the battery limit at wich a warning is thrown
     * 
     * @param limit battery limit
     */
    public void setLimit(int limit) 
    {
        this.limit = limit;
    }
    
}
