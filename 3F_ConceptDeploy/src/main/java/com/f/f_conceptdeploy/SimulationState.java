/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.f.f_conceptdeploy;

/**
 *
 * This data type represents the state of the simulation.
 * 
 * @author Claudia Cardoso
 * @author Sara Figueiredo
 * @author Joao Silva
 */
public enum SimulationState {
    
    ready,
    runningArcgis,
    runningNuatmos,
    runningFarsite,
    runningDisperfire,
    runningMEB,
    logging,
    done,
    error;
}
