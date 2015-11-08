package pt.ua.deti.fff.flow;

import java.util.Date;
import java.util.Iterator;
import pt.ua.deti.simulators.Simulators;

/**
 *
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public class App {
    @SuppressWarnings("SleepWhileInLoop")
    public static void main(String[] args)
    { 
        String path = System.getProperty("user.dir") + "/testResources/";
        String sessionId = "TestSession";
        
        SessionManager sm = SessionManager.getInstance();
        sm.initSession(sessionId, path, path + "files/", "Regateiro");
        sm.addSimulatorToSession(sessionId, Simulators.NUATMOS, "v1", true);
        sm.addSimulatorToSession(sessionId, Simulators.DISPERFIRE, "v1", true);
        
        // Its now blocking
        sm.startSimulationSession(sessionId, false);
        
        ISimulationSessionInfo sessionInfo = sm.getSession(sessionId);
        Iterator<? extends ISimulationJobInfo> myJobs = sessionInfo.iterator();
        
        System.out.println("\n********** DEBUB **********");
        for(ISimulationJobInfo jobInfo; myJobs.hasNext(); ) {
            jobInfo = myJobs.next();
            System.out.println("Simulator: " + jobInfo.getProgramName());
            System.out.println("Start: " + (new Date(jobInfo.getStartTime())));
            System.out.println("Finish: " + (new Date(jobInfo.getFinishTime())));
            System.out.println("Return value: " + jobInfo.getRetVal());
            System.out.println("Requester: " + jobInfo.getRequester());
            System.out.println();
        }
        
        System.out.println("Total Time: " + (sessionInfo.getFinishTime() - sessionInfo.getStartTime()) + "ms");
    }
}
