import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends Animal
{
    /**
     * Act - do whatever the Snake wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * a snake moves at 5 and turns randomly.
     */
    public void act() 
    {
        tryToEatTurtle();
        move(5);
        
        if (atWorldEdge())
        {
             turn(15);
        }
        if (Greenfoot.getRandomNumber(100)<10)
        {
            turn(Greenfoot.getRandomNumber(80)-40);
        }
    }    
    
    /**
     * the function of how a snake eats a turtle. And the game will end when a turtle dies.
     */
    public void tryToEatTurtle()
    {
        if (canSee(Turtle.class))
        {
            eat(Turtle.class);
            Greenfoot.playSound("sounds/au.wav");
            Greenfoot.stop();
         }     
    }   
}
