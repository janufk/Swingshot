import javax.swing.*;
import java.awt.*;

public class ballAnimation {
    private ball footBall;
    private JPanel panel;
    private Timer timer;
    private int targetSelectX, targetSelectY;
    private double animationx, animationy; //private cuz it needs to be seen in this class only                                                                                                                                         
    private int steps = 50;
    private int currentStep = 0;

    //need constructor to initialise
    public ballAnimation(ball footBall, JPanel panel) 
    {
        this.footBall = footBall;
        this.panel = panel;
    }

    public void shooting(int power, String targetSelect) 
    {
        if (targetSelect.equals("Top Left")) 
        {
            targetSelectX = 260;
            targetSelectY = 350;
        } 
        else if (targetSelect.equals("Top Right")) 
        {
            targetSelectX = 760;
            targetSelectY = 350;
        } 
        else if (targetSelect.equals("Bottom Left")) 
        {
            targetSelectX = 260;
            targetSelectY = 580;
        } 
        else if (targetSelect.equals("Bottom Right")) 
        {
            targetSelectX = 760;
            targetSelectY = 580;
        } 

        else if (targetSelect.equals("Center")) 
        {
            targetSelectX = 525;
            targetSelectY = 450;
        }

        if (power >= 90) {
            targetSelectY = 100; //goes over the goal if theres too much power
        }

        //animation
        int startX = footBall.getX();
        int startY = footBall.getY();

        animationx = (targetSelectX - startX) / (double) steps;
        animationy = (targetSelectY - startY) / (double) steps;
        currentStep = 0;

        timer = new Timer(10, e -> {
            if (currentStep < steps) {
                footBall.setPosition((int) (footBall.getX() + animationx),
                                     (int) (footBall.getY() + animationy));
                panel.repaint();
                currentStep++;
            } 
            else
            {
                ((Timer) e.getSource()).stop();
            }
        }
        );

        timer.start();
    }
}
