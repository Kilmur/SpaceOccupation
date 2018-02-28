
public class Planet {

    public static final int TEMPERATURE = 0;
    public static final int OXIDATION = 1;
    public static final int HUMIDITY = 2;
    public static final int PRESSURE = 3;
    public static final int ACIDITY = 4;
    public static final int RADIATION = 5;
    public static final int IMPACT = 6;
    public static final int CONDITIONS_NUMBER = 7;

    int posX;
    int posY;

    long[] energy;
    Phase[] phases;
    int[] conditions;

    public void nextStep() {
        for(int level=0; level<phases.length; level++) {
            phases[level].nextStep(energy[level]);
            energy[level] = phases[level].getProducedEnergy();
        }
    }

    public void createPhase(int level, long population) {
        phases[level].setPopulation(population);
    }
}
