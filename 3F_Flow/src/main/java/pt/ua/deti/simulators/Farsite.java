package pt.ua.deti.simulators;

import java.util.concurrent.ExecutionException;

/**
 * Data type that executes the FARSITE application.
 * @author Claudia Cardoso <claudiacardoso@ua.pt>
 * @author Sara Figueiredo <scfigueiredo@ua.pt>
 * @author Joao Silva <jpss@ua.pt>
 */
public class Farsite extends Simulator{

     /**
     * Constructs a new FARSITE object.
     * @param programsHome Path to the directory containing the simulators inside their respective folders.
     * @param workingDir Path to the directory containing the files to be used by the simulators.
     * @param debug Flag that enables or disables debug output.
     */
    public Farsite(String version, String programsHome, String workingDir, boolean debug) {
        super(Simulators.FARSITE, version, programsHome, workingDir, debug);
    }
    
    
    @Override
    public int beginSimulation() throws ExecutionException {
        int ret = 0;
        
        /*try {
            String[] command;
            if(SystemUtils.IS_OS_WINDOWS) {
                command = new String[] { getProgramsHome() + "nuatmos/" + getVersion() + "/Console1.exe" };
            }
            else {
                command = new String[] { "wine",  getProgramsHome() + "nuatmos/" + getVersion() + "/Console1.exe" };
            }
                
            ret = execute(command, getWorkingDir(), getWorkingDir() + "nuatmos.log");
        } catch (Exception ex) {
            throw new ExecutionException("A problem ocurred while trying to execute NUATMOS", ex);
        }*/
        
        return ret;
    }
    
}
