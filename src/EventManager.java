import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class EventManager {
    List<Event> events;

    public EventManager() {
        events = new LinkedList<Event>();
    }

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addEvent(String name, LinkedHandler<Object> handler, int delay) {
        events.add(new Event(name, handler, delay));
    }

    public void nextStep() {
        events = events.stream()
                .filter(wrapper -> wrapper.waitAndApply())
                .collect(Collectors.toList());
    }
}

@XmlType
class Event {
    private int delay;
    private String name;
    private LinkedHandler<Object> handler;

    public Event() {}

    public Event(String name, LinkedHandler<Object> handler, int delay) {
        this.name = name;
        this.handler = handler;
        this.delay = delay;
    }

    @XmlAttribute
    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlIDREF
    public LinkedHandler<Object> getHandler(){
        return handler;
    }

    public void setHandler(LinkedHandler<Object> handler){
        this.handler = handler;
    }

    public boolean waitAndApply() {
        if(delay == 0) {
            handler.apply();
            return false;
        }
        delay -= 1;
        return true;
    }
}
