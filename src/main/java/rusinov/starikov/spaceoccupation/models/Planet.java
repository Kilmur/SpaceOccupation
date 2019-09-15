package rusinov.starikov.spaceoccupation.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

 /**
  * Класс, описывающий объект Планеты
  */
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
  long[] energy = {0l};
  // Фазы жизни, развивающиеся на данной планете
  Phase[] phases;
  // События, возможные для данной планеты
  ArrayList<Event> availableEvents;
  // События, действующие в данный момент
  ArrayList<Event> actualEvents;

  @XmlAttribute
  public int getPosX() {
    return posX;
  }

  public void setPosX(int posX) {
    this.posX = posX;
  }

  @XmlAttribute
  public int getPosY() {
    return posY;
  }

  public void setPosY(int posY) {
    this.posY = posY;
  }

  @XmlElementWrapper(name = "conditions")
  @XmlElement(name = "condition")
  public int[] getConditions() {
    return conditions;
  }

  public void setConditions(int[] conditions) {
    this.conditions = conditions;
  }

  @XmlAttribute(name = "energy")
  public long getEnergy() {
    return energy[0];
  }

  public void setEnergy(long energy) {
    this.energy[0] = energy;
  }

  public List<Long> getProducedEnergies() {
    LinkedList<Long> energies = new LinkedList<Long>();
    for(int i=1; i<energy.length; i++) {
      energies.add(energy[i]);
    }
    return energies;
  }

  public void setProducedEnergies(List<Long> energies) {
    energies.add(0, this.energy[0]);
    int length = energies.size();
    energy = new long[length];
    for(int i=0; i<energy.length; i++) {
      energy[i] = energies.get(i);
    }
  }

  @XmlElement
  public Phase[] getPhases() {
    return phases;
  }

  public void setPhases(Phase[] phases) {
    this.phases = phases;
  }

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
