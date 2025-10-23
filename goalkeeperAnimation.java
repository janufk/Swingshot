import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class goalkeeperAnimation {
    private goalkeeper goalkeeper;
    private JPanel panel;
    private Timer timer;
    private int targetSelectX, targetSelectY;
    private double animationx, animationy;
    private int steps = 30;
    private int currentStep = 0;
    private Random random = new Random();
    public static int dive;

    private Image standImage;
    private Image diveLeftImage;
    private Image diveRightImage;
    private Image diveRightTopImage;
    private Image diveLeftTopImage;

    public goalkeeperAnimation(goalkeeper goalkeeper, JPanel panel) {
        this.goalkeeper = goalkeeper;
        this.panel = panel;

        //load goalkeeper images
        standImage = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.19.11-converted-from-jpeg-2.png").getImage();
        diveLeftImage = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.19.11 (2)-converted-from-jpeg-2.png").getImage();
        diveRightImage = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.19.11 (1)-converted-from-jpeg-2.png").getImage();
        diveRightTopImage = new ImageIcon("/Users/janufkrishnan/Downloads/goalkeeper_dive_right_no_bg_top.PNG").getImage();
        diveLeftTopImage = new ImageIcon("/Users/janufkrishnan/Downloads/goalkeeper_dive_left_no_bg_top.PNG").getImage();


    }

    public void dive() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        dive = random.nextInt(5); //0 = centre, 1 = top left, 2 = bottom left, 3 = top right, 4 = bottom right, 5 = top right

        int startX = goalkeeper.getX();
        int startY = goalkeeper.getY(); //becaus of image dimensions wont be equal

        if (dive == 0) 
        { // centre
            targetSelectX = startX;
            targetSelectY = startY;
            goalkeeper.setImage(standImage);
        }
         else if (dive == 1) 
        { // top left
            targetSelectX = startX - 225;
            targetSelectY = startY - 10;
            goalkeeper.setImage(diveLeftTopImage);
            goalkeeper.setSize(250, 150);
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
            targetSelectX = startX + 150;
            targetSelectY = startY - 30;
            goalkeeper.setImage(diveRightTopImage);
            goalkeeper.setSize(310, 210);
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

        //timer to animate the dive
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
