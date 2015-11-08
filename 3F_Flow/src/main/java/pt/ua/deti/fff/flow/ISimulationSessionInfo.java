package pt.ua.deti.fff.flow;

import java.util.Iterator;
import java.util.List;

/**
  Interface for reading information out of a SimulationJob
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public interface ISimulationSessionInfo {
    /**
     * Gets the time when this simulation session started.
     * @return The time when this simulation session stared.
     */
    public long getStartTime();

    /**
     * Gets the time when this simulation session finished.
     * @return The time when this simulation session finished.
     */
    public long getFinishTime();

    /**
     * Gets the home directory of the simulators
     * @return Path to the home directory of the simulators
     */
    public String getProgramsHome();

    /**
     * Gets the working directory of the simulators
     * @return Path to the working directory of the simulators
     */
    public String getWorkingDir();

    /**
     * Gets the requester
     * @return The requester
     */
    public String getRequester();
    
    /**
     * Gets the current job being executed.
     * @return An interface allowing read access to the current job being executed, or null if none is running.
     */
    public ISimulationJobInfo getCurrentJob();
    
    /**
     * Returns an iterator over the finished jobs.
     * @return Iterator if all jobs have been completed, null otherwise.
     */
    public Iterator<? extends ISimulationJobInfo> iterator();
    
    /**
     * Gets the state of the whole queue.
     * @return The SimulationState object.
     */
    public SimulationState getSimulationState();
    
    /**
     * Gets the error state of this manager.
     * @return false if no error occurred, true otherwise.
     */
    public boolean getErrorState();
    
    /**
     * Gets the error messages of this manager. This list is unmodifiable.
     * @return The list of error messages.
     */
    public List<String> getErrorMessages();
}
