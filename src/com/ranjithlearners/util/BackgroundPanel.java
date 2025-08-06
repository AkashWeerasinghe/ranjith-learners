package com.ranjithlearners.util;


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    private BufferedImage backgroundImage;

    public BackgroundPanel() {
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/com/ranjithlearners/img/student_bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(new BorderLayout());  // Allows adding components on top
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}