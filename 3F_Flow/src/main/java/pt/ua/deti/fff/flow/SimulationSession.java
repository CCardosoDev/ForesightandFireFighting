package pt.ua.deti.fff.flow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;

/**
 * Manages the execution of a queue of simulators.
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public class SimulationSession implements ISimulationSessionInfo, Runnable {

    private Queue<SimulationJob> waiting;
    private Queue<SimulationJob> done;
    private SimulationJob current;
    private SimulationState state;
    private String programsHome;
    private String workingDir;
    private String requester;
    private boolean errorState;
    private List<String> errorMsgs;
    private long startTime;
    private long finishTime;

    /**
     * Constructs a new SimulationSession object.
     * @param programsHome Path to the directory containing the simulators inside their respective folders.
     * @param workingDir Path to the directory containing the files to be used by the simulators.
     */
    public SimulationSession(String programsHome, String workingDir, String requester) {
        waiting = new ArrayDeque<>();
        done = new ArrayDeque<>();
        current = null;
        errorState = false;
        errorMsgs = new ArrayList<>();
        state = SimulationState.READY;
        this.programsHome = programsHome;
        this.workingDir = workingDir;
        this.requester = requester;
        startTime = 0;
        finishTime = 0;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    @Override
    public long getFinishTime() {
        return finishTime;
    }

    @Override
    public String getProgramsHome() {
        return programsHome;
    }

    @Override
    public String getWorkingDir() {
        return workingDir;
    }

    @Override
    public String getRequester() {
        return requester;
    }

    
    @Override
    public ISimulationJobInfo getCurrentJob() {
        return current;
    }
    
    @Override
    public Iterator<? extends ISimulationJobInfo> iterator() {
        if(state == SimulationState.FINISHED) {
            return done.iterator();
        }
        
        return null;
    }
    
    @Override
    public SimulationState getSimulationState() {
        return state;
    }
    
    @Override
    public boolean getErrorState() {
        return errorState;
    }
    
    @Override
    public List<String> getErrorMessages() {
        return Collections.unmodifiableList(errorMsgs);
    }
 
    /**
     * Adds a simulation job to the queue. If the simulation has been started it has no effect.
     * @param job The SimulationJob to add to the queue.
     */
    public void addSimulationJob(SimulationJob job) {
        if(state == SimulationState.READY) {
            waiting.add(job);
        }
    }
    
    /**
     * Starts the simulation flow, executing the Simulators added in order.
     */
    @Override
    public void run() {
        if(state != SimulationState.READY) {
            errorState = true;
            errorMsgs.add("Tried to rerun a simulation session.");
            return; 
        }
        
        state = SimulationState.RUNNING;
        startTime = Calendar.getInstance().getTimeInMillis();
        while (!waiting.isEmpty()) {
            current = waiting.remove();
            
            try {
                current.execute();
                
                if(current.getRetVal() != 0) {
                    errorState = true;
                    errorMsgs.add("Error while executing " + current.getProgramName() + ": Return value was " + current.getRetVal() + ".");
                }
            } catch (ExecutionException ex) {
                errorState = true;
                errorMsgs.add("Error while executing " + current.getProgramName() + ".\nDetails:\n" + ex.toString());
            }
            
            done.add(current);
            current = null;
        }
        finishTime = Calendar.getInstance().getTimeInMillis();
        state = SimulationState.FINISHED;
    }
}
