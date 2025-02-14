import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class AbsoluteValue extends JLayeredPane {
    AbsoluteValue(){
        // Reset PageNum whenever entering this pane
        GlobalVariables.pageNum = 0;
        System.out.printf("[AbsoluteValue] resetting pageNum on entry to [%d]\n", GlobalVariables.pageNum);

        // Customization
        this.setBounds(0,0,GlobalVariables.width,GlobalVariables.height);

        // + Elements to AbsoluteValue Pane
        this.add(new AVReturnButton());
        this.add(new AVLesson());
        this.add(new AVNextButton());
        this.add(new AVBeforeButton());
        this.add(new AVPageLabel());
    }
}

class AVReturnButton extends JButton{
    AVReturnButton(){
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

class AVLesson extends JLabel{
    AVLesson(){
        try {
            GlobalVariables.lesson = Files.lines(Paths.get("text/av_lesson.txt"))
                    .skip(GlobalVariables.pageNum)
                    .findFirst()
                    .get();

            System.out.printf("[AVLesson] On pageNum [%d]\n", GlobalVariables.pageNum);
            System.out.printf("[AVLesson] Retrieving line [%d] from [text/av_lesson.txt]\n", (GlobalVariables.pageNum + 1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Customization
        this.setBounds(100, 50, 700, 300);
        this.setOpaque(true);
        this.setBackground(Color.pink);
        this.setText(GlobalVariables.lesson);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class AVNextButton extends JButton{
    AVNextButton(){
        // Customization
        this.setBounds(200,350,100,25);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("<html><h1 style=\"font-size:1em; \">next</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact
    }
}

class AVBeforeButton extends JButton{
    AVBeforeButton(){
        // Customization
        this.setBounds(100,350,100,25);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("<html><h1 style=\"font-size:1em; \">before</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact
    }
}

class AVPageLabel extends JLabel{
    AVPageLabel(){
        // Customization
        this.setBounds(300,350,100,25);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("page-" + GlobalVariables.pageNum);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}