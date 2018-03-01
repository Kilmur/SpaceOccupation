import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Life {
    String name;
    Phase[] phases;

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "phase")
    public Phase[] getPhases() {
        return phases;
    }

    public void setPhases(Phase[] phases) {
        this.phases = phases;
    }

    public static void main(String[] args) {
        Phase[] phases = {new Phase(), new Phase(), new Phase()};
        phases[1].setEnergy(100);
        phases[2].setEnergy(200);
        phases[0].setDevelopments(null);
        phases[0].setFeatures(new double[]{0.1, 0.5, 0.6, 0.4});
        phases[1].setFeatures(new double[]{0.7, 0.5, 0.1, 0.2});
        phases[2].setFeatures(new double[]{0.1, 0.1, 0.1, 0.1});
        phases[0].setMortalities(new double[]{2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 2.7});
        phases[1].setMortalities(new double[]{1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7});
        phases[2].setMortalities(new double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7});
        ArrayList<Integer>[] tolerances = new ArrayList[7];
        phases[0].setTolerances(tolerances);
        phases[1].setTolerances(tolerances);
        phases[2].setTolerances(tolerances);
        phases[0].setPopulation(1000);
        Life life = new Life();
        life.setName("Carbon life");
        life.setPhases(phases);
        try {
            File file = new File("data/test_life.xml.tmp");
            JAXBContext jaxbContext = JAXBContext.newInstance(Life.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(life, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
