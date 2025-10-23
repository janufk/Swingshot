import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI 
{
    public String selectedTarget = null;
    protected JPanel leftPanel;
    public int targetSelect; //stores which button is clicked
    public ball footBall;
    private boolean ballSeen = true;
    public goalkeeper keeper;
    public goalkeeperAnimation keeperAnimate;
    public gameLogic logic = new gameLogic();
    public Image savedkeeper;
    public Image notsavedkeeper;


    void panels() 
    {
        JFrame frame = new JFrame("Swingshot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        footBall = new ball(505, 700,50,50);
        keeper = new goalkeeper(425, 355, 200, 300);

        
        leftPanel = new JPanel() 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                

                g.setColor(new Color(15, 166, 254));
                g.fillRect(0, 0, 1200, 600);

                g.setColor(new Color(15, 100, 29));
                g.fillRect(0, 600, 1200, 200);

                g.setColor(Color.white);
                g.fillRect(215, 300, 10, 300);

                g.setColor(Color.white);
                g.fillRect(815, 300, 10, 300);

                g.setColor(Color.white);
                g.fillRect(215, 300, 600, 10);

                g.setColor(Color.white);
                g.fillRect(165, 600, 10, 75);

                g.setColor(Color.white);
                g.fillRect(865, 600, 10, 75);

                g.setColor(Color.white);
                g.fillRect(165, 600, 10, 75);

                g.setColor(Color.white);
                g.fillRect(165, 675, 710, 10);

                g.setColor(Color.white);
                g.fillRect(0, 600, 1200, 5);

                for (int i = 0; i < 15; i++) {
                    int y = 320 + i * 20;
                    g.drawLine(215, y, 815, y);
                }

                for (int i = 0; i < 31; i++) {
                    int x = 215 + i * 20;
                    g.drawLine(x, 300, x, 600);
                }

                g.setColor(Color.white);
                g.fillOval(515, 720, 30, 30);

                g.setColor(Color.yellow);
                g.fillArc(-100, -100, 200, 200, 270, 90);

                

                keeper.goalkeeperImage(g);
                if (ballSeen) {
                    footBall.ballImage(g);
                }


                g.setColor(Color.BLACK);
                g.setFont(new Font("default", Font.BOLD, 16));
                g.drawString("Score: " + logic.getScore(), 500, 250);


            }
        };
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftPanel.setPreferredSize(new Dimension(1200, 800));
        leftPanel.setBackground(Color.WHITE);
        keeperAnimate = new goalkeeperAnimation(keeper, leftPanel);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        rightPanel.setPreferredSize(new Dimension(500, 800));

        JPanel shotPanel = new JPanel(new GridBagLayout());
        shotPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton shotPowerButton = new JButton("SHOOT!");
        shotPowerButton.setPreferredSize(new Dimension(100, 30));

        JProgressBar powerBar = new JProgressBar(0, 100);
        powerBar.setPreferredSize(new Dimension(300, 25));
        powerBar.setValue(0);
        powerBar.setStringPainted(true);

        Timer powerTimer = new Timer(20, null);
        powerTimer.addActionListener(e -> {
            int value = powerBar.getValue();
            Boolean increasing = (Boolean) powerBar.getClientProperty("increasing");
            if (increasing == null) increasing = true;
            if (increasing) {
                value++;
                if (value >= 100) {
                    value = 100;
                    increasing = false;
                }
            } else {
                value--;
                if (value <= 0) {
                    value = 0;
                    increasing = true;
                }
            }
            powerBar.putClientProperty("increasing", increasing);
            powerBar.setValue(value);
        }
        );

        notsavedkeeper = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp_Image_2025-10-23_at_11.22.55__1_-removebg-preview.png").getImage();
        savedkeeper = new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp_Image_2025-10-23_at_11.22.55-removebg-preview.png").getImage();
        powerTimer.start();

        shotPowerButton.addActionListener(e -> 
        {
            shotPowerButton.setEnabled(false);
            powerTimer.stop();
            int powerValue = powerBar.getValue();
            System.out.println("Power level: " + powerValue);
            if (selectedTarget == null) {
                JOptionPane.showMessageDialog(frame, "Please select a shot direction first!");
                shotPowerButton.setEnabled(true);
            } else {
                ballAnimation animation = new ballAnimation(footBall, leftPanel);
                animation.shooting(powerValue, selectedTarget);

                keeperAnimate.dive();
                Timer resultTimer = new Timer(800, ev2 -> {
                    boolean saved = logic.savePenalty(targetSelect, powerValue);

                    if(saved){
                        keeper.setImage(savedkeeper);
                        keeper.setSize(135, 265);
                        keeper.setPosition(keeper.getX(), 370);
                        ballSeen = false;
                    }

                    else{
                        keeper.setImage(notsavedkeeper);
                        keeper.setSize(150, 290);
                        keeper.setPosition(keeper.getX(), 360);
                    }
                    leftPanel.repaint();
                
                logic.savePenalty(targetSelect, powerValue);
                leftPanel.repaint();
                Timer resetTimer = new Timer(2000, ev -> {
                reset(powerBar, shotPowerButton, powerTimer);
            }
            );

            resetTimer.setRepeats(false);
            resetTimer.start();
        });

        resultTimer.setRepeats(false);
        resultTimer.start();
    }
    }
    );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(30, 0, 0, 0);
        shotPanel.add(powerBar, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        shotPanel.add(shotPowerButton, gbc);

        JPanel accuracyPanel = new JPanel(new GridBagLayout());
        accuracyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton topLeft = new JButton("Top Left");
        JButton topRight = new JButton("Top Right");
        JButton bottomLeft = new JButton("Bottom Left");
        JButton bottomRight = new JButton("Bottom Right");
        JButton center = new JButton("Center");

        Dimension btnSize = new Dimension(110, 60);
        for (JButton b : new JButton[]{topLeft, topRight, bottomLeft, bottomRight, center}) 
        {
            b.setPreferredSize(btnSize);
        }

        ActionListener targetListener = e -> 
        {
            selectedTarget = ((JButton) e.getSource()).getText();

            if(selectedTarget == "Center")
            {
                targetSelect = 0;
            }

            else if(selectedTarget == "Top Left")
            {
                targetSelect = 1;
            }

            else if(selectedTarget == "Bottom Left")
            {
                targetSelect = 2;
            }

            else if(selectedTarget == "Top Right")
            {
                targetSelect = 3;
            }

            else if(selectedTarget == "Bottom Right")
            {
                targetSelect = 4;
            }
            System.out.println("Selected Target: " + selectedTarget);
        };

        topLeft.addActionListener(targetListener);
        topRight.addActionListener(targetListener);
        bottomLeft.addActionListener(targetListener);
        bottomRight.addActionListener(targetListener);
        center.addActionListener(targetListener);

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(20, 20, 20, 20);
        g.fill = GridBagConstraints.NONE;

        g.gridx = 0; g.gridy = 0; g.anchor = GridBagConstraints.NORTHWEST;
        accuracyPanel.add(topLeft, g);
        g.gridx = 1; g.anchor = GridBagConstraints.NORTH;
        accuracyPanel.add(center, g);
        g.gridx = 2; g.anchor = GridBagConstraints.NORTHEAST;
        accuracyPanel.add(topRight, g);

        g.gridy = 1; g.gridx = 0; g.anchor = GridBagConstraints.SOUTHWEST;
        accuracyPanel.add(bottomLeft, g);
        g.gridx = 2; g.anchor = GridBagConstraints.SOUTHEAST;
        accuracyPanel.add(bottomRight, g);

        rightPanel.add(shotPanel);
        rightPanel.add(accuracyPanel);

        frame.add(leftPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.setSize(1700, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void reset(JProgressBar powerBar, JButton shotPowerButton, Timer powerTimer){ //java quick fix
        footBall.setPosition(505, 700); //resets ball
        keeper.setPosition(425, 355); //resets keeper
        keeper.setSize(200, 300); //sets keeper size
        keeper.setImage(new ImageIcon("/Users/janufkrishnan/Downloads/WhatsApp Image 2025-10-19 at 15.19.11-converted-from-jpeg-2.png").getImage());//keeper image reset

        ballSeen = true;

        selectedTarget = null;
        targetSelect = 6; //resets all target related input
        
        powerBar.setValue(0);
        leftPanel.repaint(); //resets the field
        shotPowerButton.setEnabled(true);

        powerTimer.start();


    }

    public static void main(String[] args) 
    {
        new GUI().panels();
    }
}
