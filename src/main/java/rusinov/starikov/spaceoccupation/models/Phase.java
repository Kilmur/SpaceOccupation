package rusinov.starikov.spaceoccupation.models;

public class Phase {
/*
    public static final int REPRODUCTION_RATE = 0;
    public static final int ENERGY_CONSUMPTION = 1;
    public static final int ENERGY_PRODUCTION = 2;
    public static final int DESCENDANT_PERCENT = 3;
    public static final int FEATURES_NUMBER = 4;
*/
    private long population;
    private long energy;
    private boolean active;

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getEnergy() {
        return energy;
    }

    public void setEnergy(long energy) {
        this.energy = energy;
    }

    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
