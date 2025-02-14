import javax.swing.*;
import java.awt.*;

public class SelectScreen extends JLayeredPane {
    SelectScreen(){
        // Customization
        this.setBounds(0,0,GlobalVariables.width,GlobalVariables.height);

        // + Elements to SelectScreen Pane
        this.add(new AbsoluteValueButton());
        this.add(new SelectScreenReturnButton());

        // Make SelectScreen Pane Visible
        this.setVisible(true);
    }
}

class SelectScreenReturnButton extends JButton{
    SelectScreenReturnButton(){
        // Customization
        this.setBounds(400,500,100,50);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("<html><h1 style=\"font-size:1em; \">return</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            Container parent = this.getParent();
            parent.setVisible(false);

            ProgramFrame frame = (ProgramFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().add(new StartScreen());
            frame.setVisible(true);
        });
    }
}

class AbsoluteValueButton extends JButton{
    AbsoluteValueButton(){
        // Customization
        this.setBounds(50,50,175,50);
        this.setOpaque(true);
        this.setBackground(Color.BLUE);
        this.setText("<html><h1 style=\"font-size:1.2em; \">1. Absolute Value</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            Container parent = this.getParent();
            parent.setVisible(false);

            ProgramFrame frame = (ProgramFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().add(new AbsoluteValue());
            frame.setVisible(true);
        });
    }
}
