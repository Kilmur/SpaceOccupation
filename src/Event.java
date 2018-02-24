
public abstract class Event {
	public String name;

	public abstract void apply();
	public abstract void revoke();
}


class PlanetaryEvent extends Event {
	Planet target;
	private ConditionHandler[] handlers;
	
	public PlanetaryEvent(Planet target, String name, ConditionHandler[] handlers) {
		this.name = name;
		this.target = target;
		this.handlers = handlers;
	}

	@Override
	public void apply() {
		for(ConditionHandler handler : handlers) {
			handler.apply(target);
		}
	}

	@Override
	public void revoke() {
		for(ConditionHandler handler : handlers) {
			handler.revoke(target);
		}		
	}
}


class SystemEvent extends Event {
	PlanetarySystem target;
	private ConditionHandler[] handlers;
	
	public SystemEvent(PlanetarySystem target, String name, ConditionHandler[] handlers) {
		this.name = name;
		this.target = target;
		this.handlers = handlers;
	}

	@Override
	public void apply() {
		for(ConditionHandler handler : handlers) {
			for(Planet planet : target.planets) {
				handler.apply(planet);
			}
		}
	}

	@Override
	public void revoke() {
		for(ConditionHandler handler : handlers) {
			for(Planet planet : target.planets) {
				handler.revoke(planet);
			}
		}
	}
}