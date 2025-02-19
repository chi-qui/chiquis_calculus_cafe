import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class StartScreen extends JLayeredPane {
    StartScreen(){
        // Customization
        this.setBounds(0,0,GlobalVariables.width,GlobalVariables.height);
        this.setOpaque(true);
        this.setBackground(Color.gray);

        // + Elements to StartScreen Pane
        this.add(new StScTitle());
        this.add(new StScAuthor());
        this.add(new StScBeginButton());
        this.add(new StScCreditsButton());
        this.add(new StScExitButton());
        this.add(new StScFunFact());
        this.add(new StScChiqui());

        // Make StartScreen Pane Visible
        this.setVisible(true);
    }
}

class StScTitle extends JLabel{
    StScTitle(){
        // Customization
        this.setBounds(200,25, 500, 100);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("<html><h1 style=\"font-size:3.5em; \">Chiqui's Calculus Café</h1></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class StScAuthor extends JLabel{
    StScAuthor(){
        // Customization
        this.setBounds(350,125, 200, 30);
        this.setOpaque(true);
        this.setBackground(Color.BLUE);
        this.setText("<html><h4>created by Douglas Serrano</h4></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class StScBeginButton extends JButton{
    StScBeginButton(){
        // Customization
        this.setBounds(325,175,250,75);
        this.setOpaque(true);
        this.setBackground(Color.MAGENTA);
        this.setText("<html><h1 style=\"font-size:3.7em; \">begin</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            Container parent = this.getParent();
            parent.setVisible(false);

            ProgramFrame frame = (ProgramFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().add(new SelectScreen());
            frame.setVisible(true);
        });
    }
}

class StScCreditsButton extends JButton{
    StScCreditsButton(){
        // Customization
        this.setBounds(350,275,200,50);
        this.setOpaque(true);
        this.setBackground(Color.MAGENTA);
        this.setText("<html><h1>credits</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            Container parent = this.getParent();
            parent.setVisible(false);

            ProgramFrame frame = (ProgramFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().add(new CreditsScreen());
            frame.setVisible(true);
        });
    }
}

class StScExitButton extends JButton{
    StScExitButton(){
        // Customization
        this.setBounds(400,350,100,50);
        this.setOpaque(true);
        this.setBackground(Color.MAGENTA);
        this.setText("<html><h1>exit</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> System.exit(0));
    }
}

class StScFunFact extends JLabel{
    StScFunFact() {
        // Just know this randomly picks a line in my text file
        int min = 0;
        int max = 6;
        String randomFunFact;
        try {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            randomFunFact = Files.lines(Paths.get("text/fun_facts.txt"))
                    .skip(randomNum)
                    .findFirst()
                    .get();
            // Command Line Prompts
            System.out.printf("[FunFact] Generated random number: [%d]\n", randomNum);
            System.out.printf("[FunFact] Retrieving line [%d] from [text/fun_facts.txt]\n", (randomNum+1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Customization
        this.setBounds(300, 425, 300, 100);
        this.setOpaque(true);
        this.setBackground(Color.red);
        this.setText(randomFunFact);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class StScChiqui extends JLabel{
    StScChiqui(){
        // Stole this from stack overflow
        ImageIcon imageIcon = new ImageIcon("images/chiqui.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        // Customization
        this.setIcon(imageIcon);
        this.setBounds(0, 265, 300, 300);
    }
}