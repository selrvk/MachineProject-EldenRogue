package Prog3MP;
    /**
     * This class handles the Player's stats and inventory.
     * @author Charles, Myrine.
     */
public class Player {

    protected int hp,dex,inte,end,str,fth;
    private String sName, sClass;
    private int iLevel, iRunes;
    private Inventory inventory;
    private LevelUp levelUp;

    /** 
     * Empty constructor.
     */
    public Player(){
        

    }
    /**
     * Constructor
     * @param name Player's name.
     * @param jobClass Player's Job Class.
     * @param level Player's level.
     * @param runes Player's runes.
     */
    public Player(String name, String jobClass, int level, int runes){

        inventory = new Inventory();
        levelUp = new LevelUp();

        this.sName = name;
        this.sClass = jobClass;
        this.iLevel = level;
        this.iRunes = runes;

    }

    /**
     * Gets the levelUp instance of the player
     * @return returns the instance.
     */
    public LevelUp getLevelUp(){

        return levelUp;
    }
    /**
     * Gets the inventory instance of the player.
     * @return returns the instance
     */
    public Inventory getInventory(){

        return inventory;
    }
    /**
     * Gets the player's name.
     * @return returns the player's name.
     */
    public String getName(){

        return sName;

    }
    /**
     * Gets the player's class.
     * @return returns the player's class. 
     */
    public String getCClass(){

        return sClass;

    }
    /**
     * Adds to the specific stat.
     */
    public void addLevel(int levelsGained){

        this.iLevel += levelsGained;

    }
    /**
     * Gets the player's specific stat.
     */
    public int getLevel(){

        return this.iLevel;

    }
    /**
     * Gets the player's runes.
     */
    public int getRunes(){

        return this.iRunes;

    }
    /**
     * Adds to the player's runes.
     */
    public void addRunes(int runes){

        this.iRunes += runes;
    }
    /**
     * Deducts the player's runes.
     */
    public void deductRunes(int runes){

        this.iRunes -= runes;
    }
    /**
     * Gets the player's specific stat.
     */
    public int getFth() {
        return fth;
    }
    /**
     * Adds to the specific stat.
     */
    public void addFth(int fth) {
        this.fth += fth;
    }
    /**
     * Gets the player's specific stat.
     */
    public int getStr() {
        return str;
    }
    /**
     * Adds to the specific stat.
     */
    public void addStr(int str) {
        this.str += str;
    }
    /**
     * Gets the player's specific stat.
     */
    public int getEnd() {
        return end;
    }
    /**
     * Adds to the specific stat.
     */
    public void addEnd(int end) {
        this.end += end;
    }
    /**
     * Gets the player's specific stat.
     */
    public int getInte() {
        return inte;
    }
    /**
     * Adds to the specific stat.
     */
    public void addInt(int inte) {
        this.inte += inte;
    }
    /**
     * Gets the player's specific stat.
     */
    public int getDex() {
        return dex;
    }
    /**
     * Adds to the specific stat.
     */
    public void addDex(int dex) {
        this.dex += dex;
    }
    /**
     * Gets the player's specific stat.
     */
    public int getHp() {
        return hp;
    }
    /**
     * Adds to the specific stat.
     */
    public void addHp(int hp) {
        this.hp += hp;
    }
    
}
