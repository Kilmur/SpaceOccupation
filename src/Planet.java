
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

    int[] conditions;
    long[] energy;
    Phase[] phases;

    public void nextStep() {
        for(int level=0; level<phases.length; level++) {
            long producedEnergy = phases[level].getProducedEnergy();
            int next = level + 1;
            if(next == phases.length) {
                energy[level] += producedEnergy; // the last phase supplies itself
            } else {
                energy[next] = producedEnergy;
            }
            phases[level].nextStep(energy[level]);
        }
    }

    public void createPhase(int level, long population) {
        phases[level].setPopulation(population);
    }
}
