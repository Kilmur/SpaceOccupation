
public abstract class Handler<Target> {
	public abstract void apply(Target target);
	
	
}


class ConditionHandler extends Handler<Planet>{
	int condition;
	int delta;
	
	public ConditionHandler(int condition, int delta) {
		this.condition = condition;
		this.delta = delta;
	}
	
	public void apply(Planet target) {
		target.conditions[condition] += delta;
	}
}


abstract class LinkedHandler<Target> extends Handler<Target> {
	Target target;

	public void apply() {
		this.apply(target);
	}
}


class PlanetaryDisaster extends LinkedHandler<Planet> {
	Planet target;
	private Handler<Planet>[] handlers;
	
	public PlanetaryDisaster(Planet target, Handler<Planet>[] handlers) {
		this.target = target;
		this.handlers = handlers;
	}

	@Override
	public void apply(Planet target) {
		for(Handler<Planet> handler : handlers) {
			handler.apply(target);
		}
	}
}


class SpaceDisaster extends LinkedHandler<PlanetarySystem> {
	PlanetarySystem target;
	private Handler<Planet>[] handlers;
	
	public SpaceDisaster(PlanetarySystem target, Handler<Planet>[] handlers) {
		this.target = target;
		this.handlers = handlers;
	}

	@Override
	public void apply(PlanetarySystem target) {
		for(Handler<Planet> handler : handlers) {
			for(Planet planet : target.planets) {
				handler.apply(planet);
			}
		}
	}
}
