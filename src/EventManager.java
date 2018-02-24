import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class EventManager {
	List<EventWrapper> events;
	
	public EventManager() {
		events = new LinkedList<EventWrapper>();
	}
	
	public void addEvent(Event event, int start, int duration) {
		events.add(new EventWrapper(event, start, duration));
	}
	
	public void nextStep() {
		events = events.stream()
				.filter(wrapper -> wrapper.nextStep())
				.collect(Collectors.toList());
	}
}


class EventWrapper {
	int applyDelay, revokeDelay;
	Event event;
	
	public EventWrapper(Event event, int start, int duration) {
		applyDelay = start;
		revokeDelay = start + duration;
	}
	
	public boolean nextStep() {
		if(applyDelay == 0) {
			event.apply();
		}
		applyDelay -= 1;
		
		if(revokeDelay == 0) {
			event.revoke();
			return false;
		}
		revokeDelay -= 1;
		return true;
	}
}