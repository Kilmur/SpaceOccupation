import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

public abstract class Handler<Target> {
    public abstract void apply(Target target);
}

@XmlType
class ConditionHandler extends Handler<Planet> {
    private int condition;
    private int delta;

    public ConditionHandler() {}

    public ConditionHandler(int condition, int delta) {
        this.condition = condition;
        this.delta = delta;
    }

    @XmlAttribute
    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    @XmlAttribute
    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public void apply(Planet target) {
        target.conditions[condition] += delta;
    }
}

abstract class LinkedHandler<Target> extends Handler<Target> {
    Target target;
    @XmlAttribute @XmlID String id;

    public void apply() {
        this.apply(target);
    }

    @SuppressWarnings("unchecked")
    public void afterUnmarshal(Unmarshaller u, Object parent) {
        this.target = (Target) parent;
    }
}

@XmlType
class PlanetaryDisaster extends LinkedHandler<Planet> {
    private Handler<Planet>[] handlers;

    public PlanetaryDisaster() {}

    public PlanetaryDisaster(Planet target, Handler<Planet>[] handlers) {
        this.target = target;
        this.handlers = handlers;
    }

    @XmlElementWrapper
    @XmlAnyElement
    public Handler<Planet>[] getHandlers(){
        return handlers;
    }

    public void setHandlers(Handler<Planet>[] handlers) {
        this.handlers = handlers;
    }

    @Override
    public void apply(Planet target) {
        for(Handler<Planet> handler : handlers) {
            handler.apply(target);
        }
    }
}

@XmlType
class SpaceDisaster extends LinkedHandler<PlanetarySystem> {
    private Handler<Planet>[] handlers;

    public SpaceDisaster() {}

    public SpaceDisaster(PlanetarySystem target, Handler<Planet>[] handlers) {
        this.target = target;
        this.handlers = handlers;
    }

    @XmlElementWrapper
    @XmlAnyElement
    public Handler<Planet>[] getHandlers(){
        return handlers;
    }

    public void setHandlers(Handler<Planet>[] handlers) {
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
