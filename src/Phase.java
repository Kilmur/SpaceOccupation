import java.util.ArrayList;

public class Phase {

    public static final int REPRODUCTION_RATE = 0;
    public static final int ENERGY_CONSUMPTION = 1;
    public static final int ENERGY_PRODUCTION = 2;
    public static final int DESCENDANT_PERCENT = 3;
    public static final int FEATURES_NUMBER = 4;

    private long population;
    private long energy;
    boolean hasDescendants = false;
    Planet home;

    double[] features;
    ArrayList<Integer>[] tolerances;
    double[] mortalities;
    Development[] developments;

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public long getDescendants() {
        return (long) (population * features[DESCENDANT_PERCENT]);
    }

    public long getProducedEnergy() {
        return (long) (population * features[ENERGY_PRODUCTION]);
    }

    public void nextStep(long energyAddition) {
        energy += energyAddition;
        survive();
        reproduce();
        feed();
    }

    private void survive() {
        // изменяет population в зависимости от катастрофы
    }

    private void reproduce() {
        population = (long) Math.ceil(population * features[REPRODUCTION_RATE]);
    }

    private void feed() {
        long feedable = (long) (energy / features[ENERGY_CONSUMPTION]);
        population = Math.min(population, feedable);
        energy -= (long) (population * features[ENERGY_CONSUMPTION]);
    }
}
