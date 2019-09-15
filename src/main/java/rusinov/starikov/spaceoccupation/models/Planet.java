package rusinov.starikov.spaceoccupation.models;

public class Planet {

    public static final int TEMPERATURE = 0;
    public static final int OXIDATION = 1;
    public static final int HUMIDITY = 2;
    public static final int PRESSURE = 3;
    public static final int ACIDITY = 4;
    public static final int RADIATION = 5;
    public static final int IMPACT = 6;
    public static final int CONDITIONS_NUMBER = 7;
    public static final int PHASE_NUMBER = 6;

    private int posX;
    private int posY;

    private int[] conditions = new int[CONDITIONS_NUMBER];
    private long[] phaseEnergies = new long[PHASE_NUMBER];
    private Phase[] phases = new Phase[PHASE_NUMBER];

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int[] getConditions() {
        return conditions;
    }

    public void setConditions(int[] conditions) {
        this.conditions = conditions;
    }

    public long getEnergy() {
        return phaseEnergies[0];
    }

    public void setEnergy(long energy) {
        this.phaseEnergies[0] = energy;
    }

    public Phase[] getPhases() {
        return phases;
    }

    public void setPhases(Phase[] phases) {
        this.phases = phases;
    }  
}
