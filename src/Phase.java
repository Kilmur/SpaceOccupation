import java.util.ArrayList;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Phase {

    public static final int REPRODUCTION_RATE = 0;
    public static final int ENERGY_CONSUMPTION = 1;
    public static final int ENERGY_PRODUCTION = 2;
    public static final int DESCENDANT_PERCENT = 3;
    public static final int FEATURES_NUMBER = 4;

    private long population;
    private long energy;
    boolean withDescendants = false;
    Planet home;

    double[] features;
    ArrayList<Integer>[] tolerances;
    double[] mortalities;
    Development[] developments;

    @XmlElement
    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @XmlElement
    public long getEnergy() {
        return energy;
    }

    public void setEnergy(long energy) {
        this.energy = energy;
    }

    @XmlElement
    public boolean isWithDescendants() {
        return withDescendants;
    }

    public void setWithDescendants(boolean withDescendants) {
        this.withDescendants = withDescendants;
    }

    @XmlTransient
    public long getDescendants() {
        return (long) (population * features[DESCENDANT_PERCENT]);
    }

    @XmlTransient
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

    public void afterUnmarshal(Unmarshaller u, Object parent) {
        try {
            this.home = (Planet) parent;
        } catch (ClassCastException e) {
            this.home = null;
        }
    }
}

@XmlRootElement
class Life {
    Phase[] phases;

    @XmlElement
    public Phase[] getPhases() {
        return phases;
    }

    public void setPhases(Phase[] phases) {
        this.phases = phases;
    }
}
