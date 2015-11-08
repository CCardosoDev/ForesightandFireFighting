package pt.ua.deti.fff.flow;

import pt.ua.deti.simulators.Simulators;

/**
 * Interface for reading information out of a SimulationJob
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public interface ISimulationJobInfo {
    
    /**
     * Gets the program name.
     * @return The program name.
     */
    public Simulators getProgramName();
    
    /**
     * Gets the return value.
     * @return The return value.
     */
    public int getRetVal();
    
    /**
     * Gets the program's start time.
     * @return The program's start time, in milliseconds.
     */
    public long getStartTime();
    
    /**
     * Gets the program's finish time.
     * @return The program's finish time, in milliseconds.
     */
    public long getFinishTime();
    
    /**
     * Gets this program's requester.
     * @return This program's requester.
     */
    public String getRequester();
    
    /**
     * Gets whether this program has finished.
     * @return true if the program has finished running, false otherwise.
     */
    public boolean isFinished();
}
