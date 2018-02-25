import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class EventManager {
	List<Event> events;
	
	public EventManager() {
		events = new LinkedList<Event>();
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


class Event {
	int delay;
	String name;
	LinkedHandler<Object> handler;
	
	public Event(String name, LinkedHandler<Object> handler, int delay) {
		this.name = name;
		this.handler = handler;
		this.delay = delay;
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