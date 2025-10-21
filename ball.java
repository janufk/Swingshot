import javax.swing.*;
import java.awt.*;

public class ball {
    private int x;
    private int y;
    private int width;           //private cuz variables are used in GUI/goalkeeper too
    private int height;
    private Image ball_football;

    public ball(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        ball_football = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.35.24-converted-from-jpeg-2.png").getImage();
    }

    public void ballImage(Graphics g) {
        g.drawImage(ball_football, x, y, width, height, null);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { 
        return x; 
    }
    public int getY() {
         return y; 
        }
}
