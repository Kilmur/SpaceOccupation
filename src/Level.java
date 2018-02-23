
public enum Level {
    SingleCellular(0), MultiCellular(1), Primitive(2),
    Developed(3), Thinking(4), Intelligent(5);

    private int level;

    Level(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
