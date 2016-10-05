import greenfoot.*; 

public class Turtle extends Animal
{

    public void act()
    {
        tryToEatLettuce();
        move(getMoveDistance());
        if (Greenfoot.isKeyDown("right")){
                turn(5);
        }
        if (Greenfoot.isKeyDown("left")){
                turn(-5);
        }
    }

    public void tryToEatLettuce()
    {
        if (canSee(Lettuce.class))
        {
            eat(Lettuce.class);
        }
    }    
    
    public int getMoveDistance()
    {
        World NewWorld=getWorld();
        if (NewWorld.numberOfObjects()<=6)
        {
            return 3;
        }
        else
        {
            return 5;
        }
    }
}