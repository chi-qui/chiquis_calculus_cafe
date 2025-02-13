import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JLayeredPane initialPane = new JLayeredPane();
        JLayeredPane menuPane = new JLayeredPane();
        JLabel title = new JLabel();
        JLabel credits = new JLabel();
        JLabel funfact = new JLabel();
        JButton begin = new JButton();
        JButton exit = new JButton();
        JButton settings = new JButton();
        JButton absoluteValueButton = new JButton();
        JButton returnToInitial = new JButton();

        // Begin button
        begin.setBounds(325,175,250,75);
        begin.setOpaque(true);
        begin.setBackground(Color.MAGENTA);
        begin.setText("<html><h1 style=\"font-size:3.7em; \">begin</h1></html>");
        begin.setForeground(Color.white);
        begin.setFocusPainted(false); // Gets rid of an ugly artifact

        begin.addActionListener(_ -> {
            initialPane.setVisible(false);
            menuPane.setVisible(true);
        });


        // Credits button
        settings.setBounds(350,275,200,50);
        settings.setOpaque(true);
        settings.setBackground(Color.MAGENTA);
        settings.setText("<html><h1>settings</h1></html>");
        settings.setForeground(Color.white);
        settings.setFocusPainted(false); // Gets rid of an ugly artifact

        // Exit button
        exit.setBounds(350,350,200,50);
        exit.setOpaque(true);
        exit.setBackground(Color.MAGENTA);
        exit.setText("<html><h1>exit</h1></html>");
        exit.setForeground(Color.white);
        exit.setFocusPainted(false); // Gets rid of an ugly artifact

        exit.addActionListener(_ -> frame.dispose());

        // Title Label
        title.setBounds(200,25, 500, 100);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setText("<html><h1 style=\"font-size:3.7em; \">Chiqui's Calculus Café</h1></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);

        // Credits Label
        credits.setBounds(350,125, 200, 30);
        credits.setOpaque(true);
        credits.setBackground(Color.BLUE);
        credits.setText("<html><h4>created by Douglas Serrano</h4></html>");
        credits.setHorizontalAlignment(JLabel.CENTER);
        credits.setForeground(Color.white);

        // Fun-fact Label
        funfact.setBounds(300,425, 300, 100);
        funfact.setOpaque(true);
        funfact.setBackground(Color.red);
        // Need to find a way to condense this BTW
        funfact.setText("<html><h4 align=\"center\">Fun Fact:<br>Coffee is actually toxic to cats, as they are unable to metabolize caffeine effectively. So don't let them have any!</h4></html>");
        funfact.setHorizontalAlignment(JLabel.CENTER);
        funfact.setForeground(Color.white);

        // Window Dimensions
        int width = 900;
        int height = 600;

        // Initial Layered Pane
        initialPane.setBounds(0,0,width, height);
        // + Labels
        initialPane.add(title);
        initialPane.add(credits);
        initialPane.add(funfact);
        initialPane.add(begin);
        initialPane.add(exit);
        initialPane.add(settings);

        // Absolute Value button
        absoluteValueButton.setBounds(50,50,250,75);
        absoluteValueButton.setOpaque(true);
        absoluteValueButton.setBackground(Color.BLUE);
        absoluteValueButton.setText("<html><h1 style=\"font-size:1.5em; \">1. Absolute Value: | x |</h1></html>");
        absoluteValueButton.setForeground(Color.white);
        absoluteValueButton.setFocusPainted(false); // Gets rid of an ugly artifact

        // Return To Initial button
        returnToInitial.setBounds(400,500,125,50);
        returnToInitial.setOpaque(true);
        returnToInitial.setBackground(Color.RED);
        returnToInitial.setText("<html><h1 style=\"font-size:1em; \">return home</h1></html>");
        returnToInitial.setForeground(Color.white);
        returnToInitial.setFocusPainted(false); // Gets rid of an ugly artifact

        returnToInitial.addActionListener(_ -> {
            initialPane.setVisible(true);
            menuPane.setVisible(false);
        });

        // Menu Pane
        menuPane.setBounds(0,0,width, height);
        menuPane.setVisible(false);
        // + Labels
        menuPane.add(absoluteValueButton);
        menuPane.add(returnToInitial);

        // Basic Frame Settings
        frame.setTitle("Chiqui's Calculus Café"); // Don't know why it said calculator
        frame.setLayout(null);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // + Pane
        frame.add(menuPane);
        frame.add(initialPane);


    }
}
