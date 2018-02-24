import java.util.ArrayList;

public class Planet {

    int posX;
    int posY;

    long totalEnergy;     // Общая энергия
    long[] energyPhases;
    ArrayList<Phase> phases;

    public Planet(int posX, int posY, long energyGain) {
        this.posX = posX;
        this.posY = posY;
        energyPhases = new long[6];
        energyPhases[0] = energyGain;
        phases = new ArrayList<Phase>();
    }

    public void nextStep(){
        for(Phase phase: phases){
            if(!phase.nextStep()){} // Удаление фазы


        }
    }

    public void createPhase(){

    }

    public long getEnergyForPhase(int level){
        return energyPhases[level];
    }


}
