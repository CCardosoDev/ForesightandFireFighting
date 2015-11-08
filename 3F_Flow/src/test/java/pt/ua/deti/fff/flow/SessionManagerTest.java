/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.fff.flow;

import java.util.Iterator;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pt.ua.deti.simulators.Simulators;

/**
 *
 * @author Regateiro
 */
public class SessionManagerTest {
    private static SessionManager sm = null;
    private static final String programsHome = System.getProperty("user.dir") + "/testResources/";
    private static final String requester = "junit";
    
    public SessionManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        sm = SessionManager.getInstance();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    @SuppressWarnings("SleepWhileInLoop")
    public void setUp() {
        sm.initSession("test1", programsHome, programsHome + "NAFolder/", requester);
        sm.initSession("test2", programsHome, programsHome + "NAFolder/", requester);
        sm.initSession("test3", programsHome, programsHome + "files/", requester);
        sm.startSimulationSession("test1", false);
        sm.startSimulationSession("test2", false);
    }
    
    @After
    public void tearDown() {
        sm.removeSession("test1");
        sm.removeSession("test2");
        sm.removeSession("test3");
    }

    /**
     * Test of getInstance method, of class SessionManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertNotNull(sm);
        
        SessionManager instance = SessionManager.getInstance();
        assertEquals(instance, sm);
    }

    /**
     * Test of initSession method, of class SessionManager.
     */
    @Test
    public void testInitSession() {
        System.out.println("initSession");
        assertNotNull(sm);
        
        boolean result = sm.initSession("test1", programsHome, programsHome + "files1/", requester);
        assertFalse(result);
        
        result = sm.initSession("test2", programsHome, programsHome + "files2/", requester);
        assertFalse(result);
        
        result = sm.initSession("test3", programsHome, programsHome + "files3/", requester);
        assertFalse(result);
        
        result = sm.initSession("test4", programsHome, programsHome + "files4/", requester);
        assertTrue(result);
        
        sm.removeSession("test4");
    }

    /**
     * Test of addSimulatorToSession method, of class SessionManager.
     */
    @Test
    public void testAddSimulatorToSession() {
        System.out.println("addSimulatorToSession");
        assertNotNull(sm);
        sm.initSession("test4", programsHome, programsHome + "files4/", requester);
        
        boolean result = sm.addSimulatorToSession("test4", Simulators.NUATMOS, "v1", true);
        assertTrue(result);
        
        result = sm.addSimulatorToSession("test5", Simulators.NUATMOS, "v1", true);
        assertFalse(result);
        
        sm.removeSession("test4");
        
    }

    /**
     * Test of startSimulationSession method, of class SessionManager.
     */
    @Test
    @SuppressWarnings("SleepWhileInLoop")
    public void testStartSimulationSession() {
        System.out.println("startSimulationSession");
        assertNotNull(sm);
        assertEquals(SimulationState.READY, sm.getSimulationSessionState("test3"));
        
        sm.addSimulatorToSession("test3", Simulators.NUATMOS, "v1", true);
        //sm.addSimulatorToSession("test3", Simulators.DISPERFIRE, true);
        //sm.addSimulatorToSession("test3", Simulators.MEB, true);
        
        boolean result = sm.startSimulationSession("test3", false);
        assertTrue(result);
        
        result = sm.startSimulationSession("test5", false);
        assertFalse(result);
        
        Iterator<? extends ISimulationJobInfo> jobs = sm.getSession("test3").iterator();
        
        for(ISimulationJobInfo job; jobs.hasNext();) {
            job = jobs.next();
            assertEquals(job.getRetVal(), 0);
        }
    }

    /**
     * Test of getSimulationSessionState method, of class SessionManager.
     */
    @Test
    public void testGetSimulationSessionState() {
        System.out.println("getSimulationSessionState");
        assertNotNull(sm);
        
        SimulationState result = sm.getSimulationSessionState("test1");
        assertEquals(SimulationState.FINISHED, result);
        
        result = sm.getSimulationSessionState("test2");
        assertEquals(SimulationState.FINISHED, result);
        
        result = sm.getSimulationSessionState("test3");
        assertEquals(SimulationState.READY, result);
    }

    /**
     * Test of getSession method, of class SessionManager.
     */
    @Test
    public void testGetSession() {
        System.out.println("getSession");
        assertNotNull(sm);
        
        ISimulationSessionInfo result = sm.getSession("test1");
        assertNotNull(result);
        assertEquals(result.getRequester(), "junit");
    }
    
    /**
     * Test of removeSession method, of class SessionManager.
     */
    @Test
    public void testRemoveSession() {
        System.out.println("removeSession");
        assertNotNull(sm);
        boolean res = sm.initSession("test4", programsHome, programsHome + "files4/", requester);
        assertTrue(res);
        
        ISimulationSessionInfo result = sm.getSession("test4");
        assertNotNull(result);
        assertEquals(result.getRequester(), "junit");
        
        res = sm.removeSession("test4");
        assertTrue(res);
    
        result = sm.getSession("test4");
        assertNull(result);
    }

    /**
     * Test of getMostRecentCompletedSession method, of class SessionManager.
     */
    @Test
    public void testGetMostRecentCompletedSession() {
        System.out.println("getMostRecentCompletedSession");
        assertNotNull(sm);
        
        ISimulationSessionInfo result = sm.getMostRecentCompletedSession();
        assertTrue(result.getFinishTime() >= sm.getSession("test1").getFinishTime());
        assertTrue(result.getFinishTime() >= sm.getSession("test2").getFinishTime());
    }

    /**
     * Test of removeCompletedSessions method, of class SessionManager.
     */
    @Test
    @SuppressWarnings("SleepWhileInLoop")
    public void testRemoveCompletedSessions() {
        System.out.println("removeCompletedSessions");
        assertNotNull(sm);
        
        Map<String, ISimulationSessionInfo> result = sm.removeCompletedSessions();
        assertNotNull(result.get("test1"));
        assertNotNull(result.get("test2"));
        assertNull(result.get("test3"));
        assertNull(sm.getSession("test1"));
        assertNull(sm.getSession("test2"));
        assertNotNull(sm.getSession("test3"));
    }
}