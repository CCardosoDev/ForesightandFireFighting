package pt.ua.deti.simulators;

import java.util.concurrent.ExecutionException;

/**
 *
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public interface ISimulator {
    /**
     * Gets the path to the folder where the simulators are stored.
     * @return The path to the folder where the simulators are stored.
     */
    public String getProgramsHome();

    /**
     * Gets the path to the folder where the files used by the simulators are stored.
     * @return The path to the folder where the files used by the simulators are stored.
     */
    public String getWorkingDir();
    
    /**
     * Gets the name of the simulator.
     * @return The simulator's name.
     */
    public Simulators getName();
    
    /**
     * Gets the version of the simulator.
     * @return The simulator's version.
     */
    public String getVersion();
    
    /**
     * Starts the execution of the simulator.
     * @return The return value of the simulator.
     * @throws ExecutionException if an execution error occurs.
     */
    public abstract int beginSimulation() throws ExecutionException;
    
    /**
     * Executes a program
     * @param program The program to be executed and its parameters.
     * @param currentDir Path to the directory containing the files to be used by the program.
     * @param outputLog Path to the log file to use as the output.
     * @return The return value of the program.
     * @throws Exception if a fatal error occurs.
     */
    public int execute(String[] program, String currentDir, String outputLog) throws Exception;
}
