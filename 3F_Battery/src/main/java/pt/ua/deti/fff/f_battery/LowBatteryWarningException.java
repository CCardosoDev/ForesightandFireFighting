package pt.ua.deti.fff.f_battery;

/**
 *
 * @author Francisco Vaz, nº 26572 <a26572@ua.pt>
 */
public class LowBatteryWarningException extends Exception {
    
    private int fireman_id;
    private int status;
  
    public LowBatteryWarningException(int fireman_id, int status)
    {
        super("Fireman's " + fireman_id + " battery is at " + status + "%");
        this.fireman_id = fireman_id;
        this.status = status;
    }

    /** Gets the fireman's id that the battery reached the limit.
     * 
     * @return the fireman_id
     */
    public int getFireman_id()
    {
        return fireman_id;
    }

    /** Get the status of the battery that the reached the limit
     * 
     * @return status
     */
    public int getStatus()
    {
        return status;
    }
    
    
    
}
