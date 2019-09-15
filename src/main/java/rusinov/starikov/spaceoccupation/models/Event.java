package rusinov.starikov.spaceoccupation.models;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс, описывающий происходящее событие (для Звездной системы или Планеты)
 */
@XmlRootElement(name = "Event")
@XmlType(propOrder = {"name", "description", "changeConditions", "duration"})

public class Event {
  
  private static final int CHANGE_TEMPERATURE = 0;
  private static final int CHANGE_OXIDATION = 1;
  private static final int CHANGE_HUMIDITY = 2;
  private static final int CHANGE_PRESSURE = 3;
  private static final int CHANGE_ACCIDITY = 4;
  private static final int CHANGE_RADIATION = 5;
  private static final int CHANGE_IMPACT = 6;
  // имя события
  private String name;
  // описание события
  private String description;
  // массив изменений условий
  private int[] changeConditions;
  // продолжительность события
  private int duration;

  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  @XmlElementWrapper(name = "changeConditions")
  @XmlElement(name = "changeCondition")
  public int[] getChangeConditions() {
    return changeConditions;
  }
  
  public void setChangeConditions(int[] changeConditions) {
    this.changeConditions = changeConditions;
  }
  
  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
  
  
}
