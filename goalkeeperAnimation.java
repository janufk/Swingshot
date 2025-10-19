import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class goalkeeperAnimation {
    private goalkeeper goalkeeper;  // uses goalkeeper class
    private JPanel panel;
    private Timer timer;
    private int targetSelectX, targetSelectY;
    private double animationx, animationy;
    private int steps = 30; // fewer steps = faster dive
    private int currentStep = 0;
    private Random random = new Random();

    private Image standImage;
    private Image diveLeftImage;
    private Image diveRightImage;

    public goalkeeperAnimation(goalkeeper goalkeeper, JPanel panel) {
        this.goalkeeper = goalkeeper;
        this.panel = panel;

        // Load goalkeeper images
        standImage = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.19.11-converted-from-jpeg-2.png").getImage();
        diveLeftImage = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.19.11 (2)-converted-from-jpeg-2.png").getImage();
        diveRightImage = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.19.11 (1)-converted-from-jpeg-2.png").getImage();


    }

    public void dive() {
        int dive = 2; //0 = centre, 1 = top left, 2 = bottom left, 3 = top right, 4 = bottom right, 5 = top right

        int startX = goalkeeper.getX();
        int startY = goalkeeper.getY();

        if (dive == 0) 
        { // centre
            targetSelectX = startX;
            targetSelectY = startY;
            goalkeeper.setImage(standImage);
        }
         else if (dive == 1) 
        { // top left
            targetSelectX = startX - 300;
            targetSelectY = startY + 80;
            goalkeeper.setImage(diveLeftImage);
            goalkeeper.setSize(300, 200);
        }
         else if (dive == 2) 
        {//bottom left
            targetSelectX = startX - 230;
            targetSelectY = startY + 100;
            goalkeeper.setImage(diveLeftImage);
            goalkeeper.setSize(300, 200);
        }

        else if (dive == 3) 
        {//top right
            targetSelectX = startX + 120;
            targetSelectY = startY + 40;
            goalkeeper.setImage(diveRightImage);
        }

        else if (dive == 4) 
        {//bottom right
            targetSelectX = startX + 180;
            targetSelectY = startY + 100;
            goalkeeper.setImage(diveRightImage);
            goalkeeper.setSize(300, 200);
        }

        animationx = (targetSelectX - startX) / (double) steps;
        animationy = (targetSelectY - startY) / (double) steps;
        currentStep = 0;

        // Timer to animate the dive
        timer = new Timer(15, e -> {
            if (currentStep < steps) {
                goalkeeper.setPosition((int)(goalkeeper.getX() + animationx),
                                       (int)(goalkeeper.getY() + animationy));
                panel.repaint();
                currentStep++;
            }
        });

        timer.start();
    }
}
