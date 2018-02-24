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
        int i = 0;
        for(Phase phase: phases){
            if(phase.nextStep()){
                if(i != (energyPhases.length - 1)){
                    i++;
                    energyPhases[i] = phase.energyGainPhase();
                }else{
                    energyPhases[i] += phase.energyGainPhase();
                }
            }

        }
    }

    public void createPhase(int level, long population){
        phases.get(level).setPopulation(population);
    }

    public long getEnergyForPhase(int level){
        return energyPhases[level];
    }


}
