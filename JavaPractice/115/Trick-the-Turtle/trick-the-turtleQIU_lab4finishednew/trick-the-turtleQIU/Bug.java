import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bug here.
 * 
 * @author (XIAQIN QIU) 
 * @version (a version number or a date)
 */
public class Bug extends Animal
{
    /**
     * Act - do whatever the Bug wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * the bug moves faster than the turtle and randomly turns.
     */
    public void act() 
    {
        move(7);
        
        if (atWorldEdge())
        {
             turn(20);
        }
        if (Greenfoot.getRandomNumber(100)<30)
        {
            turn(Greenfoot.getRandomNumber(60)-30);
        }
    }    
}
