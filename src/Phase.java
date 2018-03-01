import java.util.ArrayList;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {
        "population", "energy", "withDescendants",
        "features", "tolerances", "mortalities", "developments"
        })
public class Phase {

    public static final int REPRODUCTION_RATE = 0;
    public static final int ENERGY_CONSUMPTION = 1;
    public static final int ENERGY_PRODUCTION = 2;
    public static final int DESCENDANT_PERCENT = 3;
    public static final int FEATURES_NUMBER = 4;

    private long population;
    private long energy;
    private boolean withDescendants = false;
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

    @XmlElementWrapper
    @XmlElement(name = "feature")
    public double[] getFeatures() {
        return features;
    }

    public void setFeatures(double[] features) {
        this.features = features;
    }

    @XmlElement
    public ArrayList<Integer>[] getTolerances() {
        return tolerances;
    }

    public void setTolerances(ArrayList<Integer>[] tolerances) {
        this.tolerances = tolerances;
    }

    @XmlElementWrapper
    @XmlElement(name = "mortality")
    public double[] getMortalities() {
        return mortalities;
    }

    public void setMortalities(double[] mortalities) {
        this.mortalities = mortalities;
    }

    @XmlElement
    public Development[] getDevelopments() {
        return developments;
    }

    public void setDevelopments(Development[] developments) {
        this.developments = developments;
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
