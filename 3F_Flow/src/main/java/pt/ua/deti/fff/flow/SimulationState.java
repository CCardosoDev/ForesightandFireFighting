package pt.ua.deti.fff.flow;

/**
 * Enumerate of the different states a simulation may be in.
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
public enum SimulationState {
    /**
     * Simulation chain is READY to start, other jobs may be added.
     */
    READY,
    /**
     * Simulation chain is RUNNING
     */
    RUNNING,
    /**
     * Simulation chain has FINISHED and information about the simulation may be requested.
     */
    FINISHED
}
