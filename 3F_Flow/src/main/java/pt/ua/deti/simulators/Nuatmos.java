package pt.ua.deti.simulators;

import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.SystemUtils;

/**
 * Module that executes the Nuatmos application.
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public final class Nuatmos extends Simulator {
    /**
     * Constructs a new Nuatmos object.
     * @param programsHome Path to the directory containing the simulators inside their respective folders.
     * @param workingDir Path to the directory containing the files to be used by the simulators.
     * @param debug Flag that enables or disables debug output.
     */
    public Nuatmos(String version, String programsHome, String workingDir, boolean debug)
    {
        super(Simulators.NUATMOS, version, programsHome, workingDir, debug);
    }

    @Override
    public int beginSimulation() throws ExecutionException {
        int ret = 0;
        
        try {
            String[] command;
            if(SystemUtils.IS_OS_WINDOWS) {
                command = new String[] { getProgramsHome() + "nuatmos/" + getVersion() + "/Console1.exe" };
            }
            else {
                command = new String[] { "wine",  getProgramsHome() + "nuatmos/" + getVersion() + "/Console1.exe"};
            }
                
            ret = execute(command, getWorkingDir(), getWorkingDir() + "nuatmos.log");
        } catch (Exception ex) {
            throw new ExecutionException("A problem ocurred while trying to execute NUATMOS", ex);
        }
        
        return ret;
    }
    
}
