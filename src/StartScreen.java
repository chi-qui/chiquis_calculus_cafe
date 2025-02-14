import javax.swing.*;
import java.awt.*;

public class StartScreen extends JLayeredPane {
    StartScreen(){
        // Customization
        this.setBounds(0,0,GlobalVariables.width,GlobalVariables.height);

        // + Elements to StartScreen Pane
        this.add(new Title());
        this.add(new Author());
        this.add(new BeginButton());
        this.add(new CreditsButton());
        this.add(new ExitButton());
        this.add(new FunFact());

        // Make StartScreen Pane Visible
        this.setVisible(true);
    }
}

class Title extends JLabel{
    Title(){
        // Customization
        this.setBounds(200,25, 500, 100);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("<html><h1 style=\"font-size:3.5em; \">Chiqui's Calculus Café</h1></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class Author extends JLabel{
    Author(){
        // Customization
        this.setBounds(350,125, 200, 30);
        this.setOpaque(true);
        this.setBackground(Color.BLUE);
        this.setText("<html><h4>created by Douglas Serrano</h4></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class BeginButton extends JButton{
    BeginButton(){
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

class CreditsButton extends JButton{
    CreditsButton(){
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

class ExitButton extends JButton{
    ExitButton(){
        // Customization
        this.setBounds(400,350,100,50);
        this.setOpaque(true);
        this.setBackground(Color.MAGENTA);
        this.setText("<html><h1>exit</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            System.exit(0);
        });
    }
}

class FunFact extends JLabel{
    FunFact(){
        // Customization
        this.setBounds(300,425, 300, 100);
        this.setOpaque(true);
        this.setBackground(Color.red);
        // Need to find a way to condense this BTW
        this.setText("<html><h4 align=\"center\">Fun Fact:<br>Coffee is actually toxic to cats, as they are unable to metabolize caffeine effectively. So don't let them have any!</h4></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}