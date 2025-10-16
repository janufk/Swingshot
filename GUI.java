import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {
    public String selectedTarget = null;
    protected JPanel leftPanel; // accessible to subclasses like setup

    void panels() {
        JFrame frame = new JFrame("Swingshot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        
        leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                

                g.setColor(new Color(15, 166, 254));//sky
                g.fillRect(0, 0, 1200, 600);

                g.setColor(new Color(15, 100, 29));//field
                g.fillRect(0, 600, 1200, 200);

                g.setColor(Color.white); //setting up goal post
                g.fillRect(215, 300, 10, 300);

                g.setColor(Color.white); //setting up goal post
                g.fillRect(815, 300, 10, 300);

                g.setColor(Color.white); //setting up goal post (crossbar)
                g.fillRect(215, 300, 600, 10);

                g.setColor(Color.white); //setting up goal lines
                g.fillRect(165, 600, 10, 75);

                g.setColor(Color.white); //setting up goal lines
                g.fillRect(865, 600, 10, 75);

                g.setColor(Color.white); //setting up goal lines
                g.fillRect(165, 600, 10, 75);

                g.setColor(Color.white); //setting up goal lines
                g.fillRect(165, 675, 710, 10);

                g.setColor(Color.white); //setting up goal lines
                g.fillRect(0, 600, 1200, 5);

                g.setColor(Color.white); //setting up penalty spot
                g.fillOval(515, 720, 30, 30);

            }
        };
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftPanel.setPreferredSize(new Dimension(1200, 800));
        leftPanel.setBackground(Color.WHITE);

        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        rightPanel.setPreferredSize(new Dimension(500, 800));

        // top panel
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
        });
        powerTimer.start();

        shotPowerButton.addActionListener(e -> {
            powerTimer.stop();
            int powerValue = powerBar.getValue();
            System.out.println("Power level: " + powerValue);
        });

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

        // bottom panel
        JPanel accuracyPanel = new JPanel(new GridBagLayout());
        accuracyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton topLeft = new JButton("Top Left");
        JButton topRight = new JButton("Top Right");
        JButton bottomLeft = new JButton("Bottom Left");
        JButton bottomRight = new JButton("Bottom Right");
        JButton center = new JButton("Center");

        Dimension btnSize = new Dimension(110, 60);
        for (JButton b : new JButton[]{topLeft, topRight, bottomLeft, bottomRight, center}) {
            b.setPreferredSize(btnSize);
        }

        ActionListener targetListener = e -> {
            selectedTarget = ((JButton) e.getSource()).getText();
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

        // top row
        g.gridx = 0; g.gridy = 0; g.anchor = GridBagConstraints.NORTHWEST;
        accuracyPanel.add(topLeft, g);
        g.gridx = 1; g.anchor = GridBagConstraints.NORTH;
        accuracyPanel.add(center, g);
        g.gridx = 2; g.anchor = GridBagConstraints.NORTHEAST;
        accuracyPanel.add(topRight, g);

        // bottom row
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

    public static void main(String[] args) {
        new GUI().panels();
    }

}

//* oh yeah */
