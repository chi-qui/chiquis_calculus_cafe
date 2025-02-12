import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JLayeredPane initialPane = new JLayeredPane();
        JLabel title = new JLabel();
        JLabel credits = new JLabel();
        JLabel funfact = new JLabel();

        // Title Label
        title.setBounds(300,50, 300, 50);
        title.setOpaque(true);
        title.setBackground(Color.RED);
        title.setText("<html><h1>Chiqui's Calculus Café</h1></html>");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);

        // Credits Label
        credits.setBounds(350,100, 200, 30);
        credits.setOpaque(true);
        credits.setBackground(Color.BLUE);
        credits.setText("<html><h4>created by Douglas Serrano</h4></html>");
        credits.setHorizontalAlignment(JLabel.CENTER);
        credits.setForeground(Color.white);

        // Fun-fact Label
        funfact.setBounds(300,450, 300, 100);
        funfact.setOpaque(true);
        funfact.setBackground(Color.red);
        funfact.setText("<html><h4 align=\"center\">Fun Fact: Coffee is actually toxic to cats, as they are unable to metabolize caffeine effectively. So don't let them have any!</h4></html>");
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


        // Basic Frame Settings
        frame.setTitle("Chiqui's Calculator Café");
        frame.setLayout(null);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // + Pane
        frame.add(initialPane);

    }
}
