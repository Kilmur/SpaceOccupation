
public class Phase {

    private long population;
    private double repRate;
    private double consumption;    // Затраты энергии на 1 единицу
    private double production;     // Производство энергии 1-ой единицей
    private long energy;
    private long energyForNextPhase;
    private long extraEnergy;
    private Level level;
    Planet planet;

    boolean hasOffspring = false;

    public Phase(Planet planet, Level level){
        this.planet = planet;
        this.level = level;
    }

    public boolean nextStep(){
        survive();
        if(population <= 0) return false;
        reproduce();
        feed();
        if(population <= 0) return false;
        return true;
    }

    private void survive(){
        // изменяет population в зависимости от катастрофы
    }

    private void reproduce(){
        population = (long) Math.ceil(population * repRate);
    }

    private void feed(){
        energy = planet.getEnergyForPhase(level.getLevel());
        long nessEnergy = (long) (population * consumption);
        if(energy > nessEnergy){
            energyForNextPhase = (long) (population * production);
            extraEnergy = energy - nessEnergy;
        }else{
            population = (long) (energy / consumption);
            energyForNextPhase = (long) (population * production);
            extraEnergy = 0;  // Нужен минимум какой то
        }
    }

    public long energyGainPhase(){
        return energyForNextPhase;
    }

    public long getExtraEnergy() {
        return extraEnergy;
    }


}
