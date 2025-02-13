import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JLayeredPane startScreen = new JLayeredPane();
        JLayeredPane menuScreen = new JLayeredPane();
        JLabel title = new JLabel();
        JLabel author = new JLabel();
        JLabel funFact = new JLabel();
        JButton beginButton = new JButton();
        JButton exitButton = new JButton();
        JButton creditsButton = new JButton();
        JButton absoluteValueButton = new JButton();
        JButton returnToStartScreen = new JButton();

        // Begin button
        beginButton.setBounds(325,175,250,75);
        beginButton.setOpaque(true);
        beginButton.setBackground(Color.MAGENTA);
        beginButton.setText("<html><h1 style=\"font-size:3.7em; \">begin</h1></html>");
        beginButton.setForeground(Color.white);
        beginButton.setFocusPainted(false); // Gets rid of an ugly artifact

        beginButton.addActionListener(_ -> {
            startScreen.setVisible(false);
            menuScreen.setVisible(true);
        });


        // Credits button
        creditsButton.setBounds(350,275,200,50);
        creditsButton.setOpaque(true);
        creditsButton.setBackground(Color.MAGENTA);
        creditsButton.setText("<html><h1>credits</h1></html>");
        creditsButton.setForeground(Color.white);
        creditsButton.setFocusPainted(false); // Gets rid of an ugly artifact

        // Exit button
        exitButton.setBounds(350,350,200,50);
        exitButton.setOpaque(true);
        exitButton.setBackground(Color.MAGENTA);
        exitButton.setText("<html><h1>exit</h1></html>");
        exitButton.setForeground(Color.white);
        exitButton.setFocusPainted(false); // Gets rid of an ugly artifact

        exitButton.addActionListener(_ -> frame.dispose());

        // Title Label
        title.setBounds(200,25, 500, 100);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setText("<html><h1 style=\"font-size:3.7em; \">Chiqui's Calculus Café</h1></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);

        // Author Label
        author.setBounds(350,125, 200, 30);
        author.setOpaque(true);
        author.setBackground(Color.BLUE);
        author.setText("<html><h4>created by Douglas Serrano</h4></html>");
        author.setHorizontalAlignment(JLabel.CENTER);
        author.setForeground(Color.white);

        // Fun-fact Label
        funFact.setBounds(300,425, 300, 100);
        funFact.setOpaque(true);
        funFact.setBackground(Color.red);
        // Need to find a way to condense this BTW
        funFact.setText("<html><h4 align=\"center\">Fun Fact:<br>Coffee is actually toxic to cats, as they are unable to metabolize caffeine effectively. So don't let them have any!</h4></html>");
        funFact.setHorizontalAlignment(JLabel.CENTER);
        funFact.setForeground(Color.white);

        // Window Dimensions
        int width = 900;
        int height = 600;

        // Initial Layered Pane
        startScreen.setBounds(0,0,width, height);
        // + Labels
        startScreen.add(title);
        startScreen.add(author);
        startScreen.add(funFact);
        startScreen.add(beginButton);
        startScreen.add(exitButton);
        startScreen.add(creditsButton);

        // Absolute Value button
        absoluteValueButton.setBounds(50,50,250,75);
        absoluteValueButton.setOpaque(true);
        absoluteValueButton.setBackground(Color.BLUE);
        absoluteValueButton.setText("<html><h1 style=\"font-size:1.5em; \">1. Absolute Value: | x |</h1></html>");
        absoluteValueButton.setForeground(Color.white);
        absoluteValueButton.setFocusPainted(false); // Gets rid of an ugly artifact

        // Return To Initial button
        returnToStartScreen.setBounds(400,500,125,50);
        returnToStartScreen.setOpaque(true);
        returnToStartScreen.setBackground(Color.RED);
        returnToStartScreen.setText("<html><h1 style=\"font-size:1em; \">return home</h1></html>");
        returnToStartScreen.setForeground(Color.white);
        returnToStartScreen.setFocusPainted(false); // Gets rid of an ugly artifact

        returnToStartScreen.addActionListener(_ -> {
            startScreen.setVisible(true);
            menuScreen.setVisible(false);
        });

        // Menu Pane
        menuScreen.setBounds(0,0,width, height);
        menuScreen.setVisible(false);
        // + Labels
        menuScreen.add(absoluteValueButton);
        menuScreen.add(returnToStartScreen);

        // Basic Frame Settings
        frame.setTitle("Chiqui's Calculus Café"); // Don't know why it said calculator
        frame.setLayout(null);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // + Pane
        frame.add(menuScreen);
        frame.add(startScreen);
    }
}
