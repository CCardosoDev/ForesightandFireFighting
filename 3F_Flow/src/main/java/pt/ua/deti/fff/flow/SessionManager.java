package pt.ua.deti.fff.flow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import pt.ua.deti.simulators.DisperFire;
import pt.ua.deti.simulators.Nuatmos;
import pt.ua.deti.simulators.ISimulator;
import pt.ua.deti.simulators.Simulators;

/**
 * Class that manages all simulation sessions
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public class SessionManager {
   private static SessionManager sm = null;
   private Map<String, SimulationSession> sessions;
   
   
   private SessionManager() {
       sessions = new HashMap<>();
   }
   
    /**
     * Gets the instance of this class.
     * @return The SessionManager object.
     */
    public static SessionManager getInstance() {
       if(sm == null) {
           sm = new SessionManager();
       }
       return sm;
    }
    
    /**
     * Initializes a new session to be able to add simulation jobs.
     * @param sessionId The session id.
     * @param programsHome Path to the directory containing the simulators inside their respective folders.
     * @param workingDir Path to the directory containing the files to be used by the simulators.
     * @param requester The name of the requester of this session.
     * @return false if the session already existed, true otherwise.
     */
    public boolean initSession(String sessionId, String programsHome, String workingDir, String requester) {
        if(!sessions.containsKey(sessionId)) {
            sessions.put(sessionId, new SimulationSession(programsHome, workingDir, requester));
            return true;
        }
        
        return false;
    }
    
    /**
     * Adds a new simulation job to the session with the given id. Simulation jobs are executed in order of insertion.
     * @param sessionId The session id.
     * @param name The name of the simulator on this job.
     * @param version The version of the simulator.
     * @param debug Whether if there should be any debugging output.
     * @return false if sessionId does not exist, true otherwise;
     */
    public boolean addSimulatorToSession(String sessionId, Simulators name, String version, boolean debug) {
        if(sessions.containsKey(sessionId)) {
            SimulationSession sfm = sessions.get(sessionId);
            
            ISimulator sim = null;
            switch(name) {
                case NUATMOS:
                    sim = new Nuatmos(version, sfm.getProgramsHome(), sfm.getWorkingDir(), debug);
                    break;
                case DISPERFIRE:
                    sim = new DisperFire(version, sfm.getProgramsHome(), sfm.getWorkingDir(), debug);
            }
            
            sfm.addSimulationJob(new SimulationJob(sim, sfm.getRequester()));
            return true;
        }
        
        return false;
    }
    
    /**
     * Adds the simulators needed to create the standard execution flow.
     * @param sessionId The session id.
     * @param debug Whether if there should be any debugging output.
     * @return false if sessionId does not exist, true otherwise;
     */
    public boolean setupStandardExecutionFlow(String sessionId, boolean debug) {
        //TODO
        String[] lastVersionOfEachSimulator = new String[] {"v1", "v1", "v1", "v1", "v1" };
        if(sessions.containsKey(sessionId)) {
            addSimulatorToSession(sessionId, Simulators.GEOTOOLS, lastVersionOfEachSimulator[0], debug);
            addSimulatorToSession(sessionId, Simulators.NUATMOS, lastVersionOfEachSimulator[1],debug);
            addSimulatorToSession(sessionId, Simulators.FARSITE, lastVersionOfEachSimulator[2],debug);
            addSimulatorToSession(sessionId, Simulators.DISPERFIRE, lastVersionOfEachSimulator[3],debug);
            addSimulatorToSession(sessionId, Simulators.MEB, lastVersionOfEachSimulator[4],debug);
            return true;
        }
        return false;
    }
    
    /**
     * Starts the simulation session on a new thread.
     * @param sessionId The session id.
     * @param async Indicates whether this session should execute asynchronously
     * @return false if sessionId does not exist, true otherwise; 
     */
    public boolean startSimulationSession(String sessionId, boolean async) {
        if(sessions.containsKey(sessionId)) {
            if(async) {
                (new Thread(sessions.get(sessionId))).start();
            }
            else {
                sessions.get(sessionId).run();
            }
            
            return true;
        }
        
        return false;
    }
    
    /**
     * Removes the session from internal storage, but only if the sessionId exists
     * and the session is currently not in RUNNING state.
     * @param sessionId The id of the session to be removed.
     * @return false if sessionId does not exist or the session in running, true otherwise; 
     */
    public boolean removeSession(String sessionId) {
        if(sessions.containsKey(sessionId)) {
            if(getSimulationSessionState(sessionId) != SimulationState.RUNNING) {
                sessions.remove(sessionId);
                return true;
            }
        }
        
        return false;
    }

    /**
     * Gets the state of the simulation of the session with the given id.
     * @param sessionId The session id.
     * @return false if sessionId does not exist, true otherwise; 
     */
    public SimulationState getSimulationSessionState(String sessionId) {
        if(sessions.containsKey(sessionId)) {
            return sessions.get(sessionId).getSimulationState();
        }
        
        return null;
    }

    /**
     * Gets an interface with methods to read information about the requested session.
     * @param sessionId The session id.
     * @return null if sessionId does not exist, an ISimulationSessionInfo otherwise; 
     */
    public ISimulationSessionInfo getSession(String sessionId) {
        if(sessions.containsKey(sessionId)) {
            return sessions.get(sessionId);
        }
        
        return null;
    }
    
    /**
     * Gets an interface with methods to read information about the most recent completed session.
     * @return false if sessionId does not exist, true otherwise; 
     */
    public ISimulationSessionInfo getMostRecentCompletedSession() {
        ISimulationSessionInfo sessionInfo = null;
        
        for (Iterator<SimulationSession> it = sessions.values().iterator(); it.hasNext();) {
            SimulationSession session = it.next();

            if(sessionInfo == null || sessionInfo.getFinishTime() < session.getFinishTime()) {
                sessionInfo = session;
            }
        }
        
        return sessionInfo;
    }
    
    /**
     * Gets all the completed sessions. This method removes the returned 
     * sessions from the internal storage so the callee should save them
     * if needed.
     * @return A Map with all the completed sessions.
     */
    public Map<String, ISimulationSessionInfo> removeCompletedSessions() {
        Map<String, ISimulationSessionInfo> completedSessions = new HashMap<>();
        
        for (Iterator<String> it = sessions.keySet().iterator(); it.hasNext();) {
            String sessionId = it.next();
            ISimulationSessionInfo sessionInfo = sessions.get(sessionId);

            if(sessionInfo.getSimulationState() == SimulationState.FINISHED) {
                completedSessions.put(sessionId, sessionInfo);
            }
        }
        
        for (Iterator<String> it = completedSessions.keySet().iterator(); it.hasNext();) {
            String sessionId = it.next();
            sessions.remove(sessionId);
        }
        
        return completedSessions;
    }
}
