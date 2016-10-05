import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage
public class TurtleWorld extends World
{
    /**
     * Create the turtle world. Our world has a size 
     * of 600x480 cells, where every cell is just 1 pixel.
     */
    public TurtleWorld() 
    {
        super(600, 480, 1);

        prepare();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     * 2 snakes will appear in the left section randomly and face to the left.
     * 1 bug will appear randomly in the whole world and will face to any angles.
     * the turtle will appear on the certain point and face to right.
     */
    private void prepare()
    {
        Counter myCounter = new Counter();
        addObject(myCounter, 50,30);
        int numberOfLettuce=Greenfoot.getRandomNumber(20)+20;
        int countLettuce=1;
        while (countLettuce<=numberOfLettuce)
        {
            Lettuce newLettuce = new Lettuce();
            int randomX=Greenfoot.getRandomNumber(getWidth());
            int randomY=Greenfoot.getRandomNumber(getHeight());
            addObject(newLettuce, randomX, randomY);
            countLettuce++;
        }
        int countSnake=1;
        while (countSnake<=2)
        {
            Snake newSnake = new Snake();
            addObject(newSnake, Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()));
            countSnake++;
        }
        Turtle turtle = new Turtle(myCounter);
        addObject(turtle, Greenfoot.getRandomNumber(getWidth()),Greenfoot.getRandomNumber(getHeight()));
        Bug bug = new Bug();
        addObject(bug, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
        bug.turn(Greenfoot.getRandomNumber(360)-180);
        
    }
}