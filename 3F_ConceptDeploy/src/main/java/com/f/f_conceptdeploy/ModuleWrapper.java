/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.f.f_conceptdeploy;

/**
 * This data type represents the interface for all the module wrappers. 
 * Therefore, this is the main access for the execution operation involving the work flow modules.
 * 
 * @author Claudia Cardoso
 * @author Sara Figueiredo
 * @author Joao Silva
 */

public interface ModuleWrapper {

    /**
     * This operation starts the execution of a specific module.
     * 
     * @throws ModuleExecutionException 
     */
    public void execute() throws ModuleExecutionException;
}
