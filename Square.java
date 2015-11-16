package ch.hslu.prg1.allg.hanoi;

import java.awt.*;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 */

public class Square
{
    private int size;
    private int heigth;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new square at default position with default color.
     */
    public Square()
    {
        size = 60;
        heigth = 60;
        xPosition = 310;
        yPosition = 120;
        color = "red";
        isVisible = false;
    }
    
    public Square(int size, int heigth, int xPos, int yPos, String color)
    {
        this.size = size;
        this.heigth = heigth;
        xPosition = xPos;
        yPosition = yPos;
        this.color = color;
        isVisible = false;
    }

    /**
     * Make this square visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this square invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     * Move the square a few pixels to the right.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Move the square a few pixels to the left.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Move the square a few pixels up.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Move the square a few pixels down.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Move the square horizontally by 'distance' pixels.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the square vertically by 'distance' pixels.
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * get the yPos, which is needed for purposes in the Tower-class.
     */
    public int getYPos() {
    	return yPosition;
    }
    
    
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the square vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -2;
            distance = -distance;
        }
        else 
        {
            delta = 2;
        }

        for(int i = 0; i < distance; i++)
        {
            yPosition += delta;
            draw();
        }
    }
/**
 * Method used in Tower-class for moving to Blocks from one pole to another
 * @param hor
 * @param ver
 */
    public void moveDiag(int hor, int ver) {
        {
            int yPos = yPosition;
        	
        	erase();
            yPosition = yPosition / 2 + 20;
            draw();
            timeout();
            
        	erase();
            yPosition = 20;
            draw();
            timeout();

            erase();
            xPosition += hor;
            draw();
            timeout();

        	erase();
            yPosition = (yPos + ver) / 2;
            draw();
            timeout();
            
        	erase();
            yPosition = yPos + ver;
            draw();
        }
    }
    
    
    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newSize)
    {
        erase();
        size = newSize;
        draw();
    }

    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

	public void timeout() {
        try{
            Thread.sleep(20);
           } catch(Exception e){
              System.out.println(e.getMessage());
           }
	}
    /**
     * Draw the square with current specifications on screen.
     */
    private void draw()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                        new Rectangle(xPosition, yPosition, size, heigth));
            canvas.wait(10);
        }
    }

    /**
     * Erase the square on screen.
     */
    private void erase()
    {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
