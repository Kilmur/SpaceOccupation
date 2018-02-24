
public enum Level {
    Unicellular(0), Multicellular(1), Primitive(2),
    Developed(3), Thinking(4), Intelligent(5);

    private int level;

    Level(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
