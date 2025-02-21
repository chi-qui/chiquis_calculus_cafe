import javax.swing.*;
import java.awt.*;

public class CreditsScreen extends JLayeredPane {
    CreditsScreen(){
        // Customization
        this.setBounds(0,0,GlobalVariables.width,GlobalVariables.height);
        this.setOpaque(true);
        this.setBackground(new Color(221, 229, 182));

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
        this.setBounds(100,50, 700, 400);
        this.setOpaque(true);
        this.setBackground(new Color(169, 132, 103));
        this.setText("<html><p align=\"center\" style=\"font-size:2em; \">Created by Douglas Serrano<br>https://github.com/chi-qui<br><br>Still working on this...</p></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class CSReturnButton extends JButton{
    CSReturnButton(){
        // Customization
        this.setBounds(400,530,100,25);
        this.setOpaque(true);
        this.setBackground(new Color(108, 88, 76));
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