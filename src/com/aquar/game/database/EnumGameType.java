/**
 * 
 */
package com.aquar.game.database;

/**
 * @author Edison
 * Game Type definition
 */
public enum EnumGameType {
    // Action Game
    ACT("Action Game"),
    // Shooting Game
    STG("Shooting Game"),
    // Fighting Game
    FTG("Fighting Game"),
    //Adventure Game
    AVG("Adventure Game"),
    // Simulation Game
    SLG("Simulation Game"),
    // Role-playing game
    RPG("Role-playing game"),
    // Strategy Game
    STRATEGY("Strategy Game"),
    // Sports Game
    SPORT("Sports Game"),
    // Racing Game
    RACING("Racing Game"),
    // Casual Game
    CASUAL("Casual Game"),
    // Music Game
    MUSIC("Music Game"),
    //Multiplayer Online Game
    MMOG("Multiplayer Online Game");
    
    private String name;
    
    EnumGameType(String name) {
        this.name = name;
    }
 
    public static EnumGameType getEnum(int idx) {
        if (idx < values().length) {
            return values()[idx];
        } else {
            return null;
        }
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return name;
    }
}
