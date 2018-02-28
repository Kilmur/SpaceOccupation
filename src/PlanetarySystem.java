import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PlanetarySystem {

    Planet[] planets;
    ArrayList<Planet> carriers;
    EventManager manager;

    public void nextStep(){
    }

}
