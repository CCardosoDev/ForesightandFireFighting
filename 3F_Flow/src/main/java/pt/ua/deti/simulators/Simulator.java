package pt.ua.deti.simulators;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * Common class of the different simulators
 * @author Diogo Regateiro <diogoregateiro@ua.pt>
 */
abstract class Simulator implements ISimulator {
    private Simulators name;
    private String version;
    private String programsHome;
    private String workingDir;
    private boolean debug;
    
    /**
     * Constructs a new Simulator object.
     * @param name The name of the simulator.
     * @param debug Whether or not to output debug information.
     */
    public Simulator(Simulators name, String version, String programsHome, String workingDir, boolean debug) {
        this.name = name;
        this.version = version;
        this.programsHome = programsHome;
        this.workingDir = workingDir;
        this.debug = debug;
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
    public Simulators getName() {
        return name;
    }
    
    @Override
    public String getVersion() {
        return version;
    }
    
    @Override
    public abstract int beginSimulation() throws ExecutionException;
    
    @Override
    public int execute(String[] program, String currentDir, String outputLog) throws Exception {
        // Declaração do programa a executar e qual o seu diretorio de trabalho
        ProcessBuilder pb = new ProcessBuilder(program);
        pb.directory(new File(currentDir));

        // Redirecionar o output para ficheiro
        pb.redirectErrorStream(true);
        pb.redirectOutput(new File(outputLog));

        // Iniciar o programa
        if (debug) {
            System.out.print("Starting " + name + "... ");
        }
        Process p;
        try {
            p = pb.start();
        } catch (Exception ex) {
            throw ex;
        }
        if (debug) {
            System.out.print("DONE\nRunning... ");
        }

        // Esperar que termine
        while (true) {
            try {
                p.waitFor();
                break;
            } catch (InterruptedException ex) {
            }
        }

        if (debug) {
            System.out.println("DONE");
        }
        
        return p.exitValue();
    }
}
