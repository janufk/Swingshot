import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class goalkeeper 
{
    private int x;
    private int y;
    private int width;           //private cuz variables are used in GUI/goalkeeper too
    private int height;
    private Image goalkeeper_football;

    public goalkeeper(int x, int y, int width, int height) 
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        goalkeeper_football = new ImageIcon("C:\\Users\\20242326\\Downloads\\goalkeeper_standing.png").getImage();
    }

    public void goalkeeperImage(Graphics g) {
        g.drawImage(goalkeeper_football, x, y, width, height, null);
    }

    public void setImage(Image newImage)
    {
    this.goalkeeper_football = newImage;
    }


    public void setPosition(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    public int getX() 
    { 
        return x; 
    }
    public int getY() 
    {
         return y; 
    }

    public void setSize(int width, int height) { //need this cuz the images need to change for diving
    this.width = width;
    this.height = height;
}

}
