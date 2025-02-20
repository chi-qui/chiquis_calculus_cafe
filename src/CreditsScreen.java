import javax.swing.*;
import java.awt.*;

public class CreditsScreen extends JLayeredPane {
    CreditsScreen(){
        // Customization
        this.setBounds(0,0,GlobalVariables.width,GlobalVariables.height);
        this.setOpaque(true);
        this.setBackground(Color.gray);

        // + Elements to CreditsScreen Pane
        this.add(new CSInfoLabel());
        this.add(new CSReturnButton());

        // Make CreditsScreen Pane Visible
        this.setVisible(true);
    }
}

class CSInfoLabel extends JLabel {
    CSInfoLabel(){
        // Customization
        this.setBounds(300,200, 300, 100);
        this.setOpaque(true);
        this.setBackground(Color.pink);
        this.setText("<html><h4 align=\"center\" style=\"font-size:3em; \">Nothing here yet lol</h4></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class CSReturnButton extends JButton{
    CSReturnButton(){
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