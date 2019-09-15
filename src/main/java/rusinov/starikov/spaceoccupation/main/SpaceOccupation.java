/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rusinov.starikov.spaceoccupation.main;

import rusinov.starikov.spaceoccupation.mappers.JSONMapper;
import rusinov.starikov.spaceoccupation.models.PlanetarySystem;
import rusinov.starikov.spaceoccupation.processors.PlanetarySystemProcessor;

/**
 *
 * @author isrusin
 */
public class SpaceOccupation {

    private JSONMapper mapper;
    private PlanetarySystem system;
    private PlanetarySystemProcessor systemProcessor;

    public SpaceOccupation() {
        mapper = new JSONMapper();
        system = new PlanetarySystem();
        systemProcessor = new PlanetarySystemProcessor();
    }
    
    public void processNextStep() {
        
    }
    
    public PlanetarySystem getPlanetarySystem() {
        return system;
    }
    
    public void saveTo(String filename) {
        
    }
    
    public void loadFrom(String filename) {
        
    }
}
