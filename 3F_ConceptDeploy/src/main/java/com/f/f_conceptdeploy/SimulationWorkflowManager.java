package com.f.f_conceptdeploy;

/**
 * This data type represents the work flow manager in charge of a single
 * simulation.
 *
 * @author Claudia Cardoso
 * @author Sara Figueiredo
 * @author Joao Silva
 */
public class SimulationWorkflowManager {

    /**
     * The simulation identification string.
     */
    private String id;
    /**
     * Base directory for outputting intermediate and final results.
     */
    private String baseDir;
    /**
     * Coordinates of the current simulation.
     *
     * @deprecated o tipo de dados ja foi definido nos parsers, nao é para usar
     * string apagar esta linha depois de corrigir
     *
     */
    @Deprecated
    private String coordinates;
    /**
     * Resolution of the current simulation.
     */
    private double resolution;
    /**
     * State the current simulation.
     */
    private SimulationState state;
    
    /**
     * The database access manager. 
     */
    private DatabaseAccessManager databaseAccessManager;

    /**
     * Instantiate the data type.
     *
     *
     * @param baseDir Base directory for outputting intermediate and final
     * results.
     * @param coordinates Coordinates of the current simulation.
     * @param resolution Resolution of the current simulation.
     * @param databaseAccessManager The database access manager.
     */
    public SimulationWorkflowManager(String baseDir, String coordinates, double resolution, DatabaseAccessManager databaseAccessManager) {
        this.baseDir = baseDir;
        this.coordinates = coordinates;
        this.resolution = resolution;
        this.state = SimulationState.ready;
        this.databaseAccessManager = databaseAccessManager;
        this.id = null; //The simulation id is to be auto generated.
    }

    /*
     * TALVEZ NÃO SERA NECESSARIO!
     * 
     * private ArcGISWrapper arc; NuatmosWrapper nuatmos; FarsiteWrapper
     * farsite; DisperfireWrapper disperfire; MEBWrapper meb;
     * DatabaseAccessManager dbManager;
     */
    
    /**
     * Run the simulation.
     *
     * @throws SimulationExecutionException in case something goes wrong, check
     * the log database for details.
     */
    public void execute() throws SimulationExecutionException {
    }

    /**
     * Get the current simulation state.
     * @return the state of the current simulation.
     */
    public synchronized SimulationState getState() {

        return state;
    }

    /**
     *  Store data in the database.
     */
    public void log() {
    }
}
