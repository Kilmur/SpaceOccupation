import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlanetarySystem {

  Planet[] planets;
  ArrayList<Planet> carriers;
  // События, возможные для данной звездной системы
  ArrayList<Event> availableEvents;
  // События, действующие в данный момент в звездной системе
  ArrayList<Event> actualEvents;

  public void nextStep () {
    
  }

}
