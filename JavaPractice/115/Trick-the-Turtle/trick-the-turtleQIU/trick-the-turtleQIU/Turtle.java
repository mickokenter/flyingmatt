import greenfoot.*; 
/**
 * Create a Turtle class under the Animal
 */
public class Turtle extends Animal
{
    /**
     * set up a lettuceEaten counter
     */
    private int lettuceEaten;
    /**
     * set up a bugHit counter
     */
    public int bugHit;
    private Counter theCounter;
    public Turtle (Counter counter)
    {
        theCounter=counter;
    }
    
    /**
     * the Turtle's method to act and how the game ends. When the point is up to 15, game ends.
     */
    public void act()
    {
        tryToEatLettuce();
        tryToEatBug();
        move(getMoveDistance());
        if (Greenfoot.isKeyDown("right")){
                turn(5);
        }
        if (Greenfoot.isKeyDown("left")){
                turn(-5);
        }
        if (theCounter.getValue()>=15)
        {
            Greenfoot.stop();
            Greenfoot.playSound("sounds/victory.wav");
        }
    }
    /**
     * if lettuce are eaten by the turtle up to 11, the turtle moves slower
     */
    private int getMoveDistance() 
    {
        if (lettuceEaten>10)
        {
            return 3;
        }
        else
        {
            return 5;
        }
    }   
    /**
     * function which the turtle meets a lettuce and eat it. And when the amount is up to a certain number(5), a third snake 
     * will appear to threaten the turtle. The third snake will appear outside the square with width of 200pixels and the center is the Turtle. 
     * When the turtle is near the edge of the world, the snake will appear outside the square or on the edge. The snake will face to any
     * sides.
     */
    private void tryToEatLettuce()
    {
        int X=getWorld().getWidth();
        int Y=getWorld().getHeight();
        if (canSee(Lettuce.class))
        {
            eat(Lettuce.class);
            Greenfoot.playSound("sounds/slurp.wav");
            lettuceEaten++;   // lettuceEaten=lettuceEaten+1;
            theCounter.add(1);
            if (lettuceEaten==5)
            {
                int X1=getX();
                int Y1=getY();
                int LXX1;
                int RXX1;
                int UYY1;
                int DYY1;
                if (X1<100)
                {
                    LXX1=0;
                }
                else
                {
                    LXX1=Greenfoot.getRandomNumber(X1-100);
                }
                if (X1>X-100)
                {
                    RXX1=X;
                }
                else
                {
                    RXX1=X-Greenfoot.getRandomNumber(X-100-X1);
                }
                if (Y1<75)
                {
                    UYY1=0;
                }
                else
                {
                    UYY1=Greenfoot.getRandomNumber(Y1-75);
                }
                if (Y1>Y-75)
                {
                    DYY1=Y;
                }
                else
                {
                    DYY1=Y-Greenfoot.getRandomNumber(Y-Y1-75);
                }
                int DICEX1;
                if (Greenfoot.getRandomNumber(10)<=5)
                {
                    DICEX1=LXX1;
                }
                else
                {
                    DICEX1=RXX1;
                }
                int DICEY1;
                if (Greenfoot.getRandomNumber(10)<=5)
                {
                    DICEY1=UYY1;
                }
                else
                {
                    DICEY1=DYY1;
                }
                Snake snake3;
                snake3=new Snake();
                getWorld().addObject(snake3, DICEX1, DICEY1);
                snake3.turn(Greenfoot.getRandomNumber(360));
            }
        }
    } 
    /**
     * the function of trying to eat the bug. the turtle has to bite the bug for 8 times to eliminate it. then the turtle gets 5 points.
     */
    private void tryToEatBug()
    {
        if (canSee(Bug.class))
        {
            bugHit++;
            Greenfoot.playSound("sounds/hit.wav");
            if (bugHit>=8)
            {
                eat(Bug.class);
                theCounter.add(5);
                Greenfoot.playSound("sounds/bugkilled.wav");
            }
        }
    }
    
   
}