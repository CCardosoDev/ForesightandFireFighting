package pt.ua.deti.simulators;

import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.SystemUtils;

/**
 * Module that executes the DISPERFIRE application.
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public final class DisperFire extends Simulator {
    
    /**
     * Constructs a new DisperFire object.
     * @param programsHome Path to the directory containing the simulators inside their respective folders.
     * @param workingDir Path to the directory containing the files to be used by the simulators.
     * @param debug Flag that enables or disables debug output.
     */
    public DisperFire(String version, String programsHome, String workingDir, boolean debug)
    {
        super(Simulators.DISPERFIRE, version, programsHome, workingDir, debug);
    }
    
    @Override
    public int beginSimulation() throws ExecutionException {
        int ret;
        
        try {
            String[] command;
            if(SystemUtils.IS_OS_WINDOWS) {
                command = new String[] { getProgramsHome() + "disperfire/" + getVersion() + "/Console1.exe" };
            }
            else {
                command = new String[] { "wine", getProgramsHome() + "disperfire/" + getVersion() + "/Console1.exe" };
            }
                
            ret = execute(command, getWorkingDir(), getWorkingDir() + "disperfire.log");
        } catch (Exception ex) {
            throw new ExecutionException("A problem ocurred while trying to execute DISPERFIRE", ex);
        }
        
        return ret;
    }
}
