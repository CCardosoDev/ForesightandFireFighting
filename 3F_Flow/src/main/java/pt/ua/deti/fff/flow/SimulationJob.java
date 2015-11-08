package pt.ua.deti.fff.flow;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
import pt.ua.deti.simulators.ISimulator;
import pt.ua.deti.simulators.Simulators;

/**
 * This class is responsible to gather all the information about a simulation job.
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public class SimulationJob implements ISimulationJobInfo {
    private ISimulator program;
    private int retVal;
    private long startTime;
    private long finishTime;
    private String requester;
    private boolean finished;

    /**
     * Constructs a SimulationJob object.
     * @param program The Simulator bound to this job.
     * @param requester The name of the requester of this job.
     */
    public SimulationJob(ISimulator program, String requester) {
        this.program = program;
        this.retVal = 0;
        this.startTime = 0;
        this.finishTime = 0;
        this.requester = requester;
        this.finished = false;
    }
    
    /**
     * Starts to execute this program.
     * @throws ExecutionException If any execution error occurred.
     */
    public void execute() throws ExecutionException {
        startTime = Calendar.getInstance().getTimeInMillis();
        retVal = program.beginSimulation();
        finishTime = Calendar.getInstance().getTimeInMillis();
        finished = true;
    }
    
    @Override
    public Simulators getProgramName() {
        return program.getName();
    }

    @Override
    public int getRetVal() {
        return retVal;
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
    public String getRequester() {
        return requester;
    }
    
    @Override
    public boolean isFinished() {
        return finished;
    }
}
