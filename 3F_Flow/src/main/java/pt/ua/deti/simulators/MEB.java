package pt.ua.deti.simulators;

import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.SystemUtils;

/**
 * Module that executes the MEB application.
 *
 * @author Claudia Cardoso <claudiacardoso@ua.pt>
 * @author Sara Figueiredo <scfigueiredo@ua.pt>
 * @author Joao Silva <jpss@ua.pt>
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public final class MEB extends Simulator {

    /**
     * Constructs a new MEB object.
     *
     * @param programsHome Path to the directory containing the simulators
     * inside their respective folders.
     * @param workingDir Path to the directory containing the files to be used
     * by the simulators.
     * @param debug Flag that enables or disables debug output.
     */
    public MEB(String version, String programsHome, String workingDir, boolean debug) {
        super(Simulators.MEB, version, programsHome, workingDir, debug);
    }

    @Override
    public int beginSimulation() throws ExecutionException {
        int ret;

        try {
            String[] command;
            if (SystemUtils.IS_OS_WINDOWS) {
                command = new String[]{getProgramsHome() + "meb/" + getVersion() + "/Console1.exe"};
            } else {
                command = new String[]{"wine", getProgramsHome() + "meb/" + getVersion() + "/Console1.exe"};
            }

            ret = execute(command, getWorkingDir(), getWorkingDir() + "meb.log");
        } catch (Exception ex) {
            throw new ExecutionException("A problem ocurred while trying to execute MEB", ex);
        }

        return ret;
    }
}
