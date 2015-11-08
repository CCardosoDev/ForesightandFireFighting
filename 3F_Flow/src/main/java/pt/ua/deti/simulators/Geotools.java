/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ua.deti.simulators;

import java.util.concurrent.ExecutionException;

/**
 * Data type that wraps the Geographic information provider.
 *
 * @author Claudia Cardoso <claudiacardoso@ua.pt>
 * @author Sara Figueiredo <scfigueiredo@ua.pt>
 * @author Joao Silva <jpss@ua.pt>
 */
public class Geotools extends Simulator {

    /**
     * Constructs a new Geotools object.
     *
     * @param programsHome Path to the directory containing the simulators
     * inside their respective folders.
     * @param workingDir Path to the directory containing the files to be used
     * by the simulators.
     * @param debug Flag that enables or disables debug output.
     */
    private double lowestLeftX;
    private double lowestLeftY;
    private double highestRigthX;
    private double highestRigthY;
    private double resolution;

    public Geotools(String version, String name, String programsHome, String workingDir, boolean debug, double lowestLeftX, double lowestLeftY, double highestRigthX, double highestRigthY, double resolution) {
        super(Simulators.GEOTOOLS, version, programsHome, workingDir, debug);

        this.lowestLeftX = lowestLeftX;
        this.lowestLeftY = lowestLeftY;
        this.highestRigthX = highestRigthX;
        this.highestRigthY = highestRigthY;
        this.resolution = resolution;
    }

    @Override
    public int beginSimulation() throws ExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
