import javax.swing.*;
import java.awt.*;

public class SelectScreen extends JLayeredPane {
    SelectScreen(){
        // Customization
        this.setBounds(0,0,GlobalVariables.width,GlobalVariables.height);
        this.setOpaque(true);
        this.setBackground(Color.gray);

        // + Elements to SelectScreen Pane
        this.add(new SSAbsoluteValueButton());
        this.add(new SSReturnButton());

        // Make SelectScreen Pane Visible
        this.setVisible(true);
    }
}

class SSReturnButton extends JButton{
    SSReturnButton(){
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

class SSAbsoluteValueButton extends JButton{
    SSAbsoluteValueButton(){
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
