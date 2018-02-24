
public abstract class Handler<Target> {
	public abstract void apply(Target target);
	public abstract void revoke(Target target);
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
	
	public void revoke(Planet target) {
		target.conditions[condition] -= delta;
	}
}