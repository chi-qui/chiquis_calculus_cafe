import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AbsoluteValue extends JLayeredPane {
    AbsoluteValue(){
        // Customization
        this.setBounds(0, 0, GlobalVariables.width, GlobalVariables.height);

        if (!GlobalVariables.practice) {
            // Reset PageNum whenever entering this pane
            GlobalVariables.pageNum = 0;
            System.out.printf("[AbsoluteValue] resetting pageNum on entry to [%d]\n", GlobalVariables.pageNum);

            // + Elements to AbsoluteValue Pane
            this.add(new AVReturnButton());
            this.add(new AVLesson());
            this.add(new AVNextButton());
            this.add(new AVBeforeButton());
            this.add(new AVPageLabel());
            this.add(new AVPracticeButton());
        }
        else {
            // + Elements to AbsoluteValue Pane (when in practice)
            this.add(new AVTimer());
            this.add(new AVQuestion());
            this.add(new AVQuitButton());
        }
    }
}

class AVPracticeButton extends JButton{
    AVPracticeButton(){
        // Customization
        this.setBounds(400,350,100,25);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("<html><h1 style=\"font-size:1em; \">practice</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            GlobalVariables.practice = true;

            Container parent = this.getParent();
            parent.setVisible(false);

            ProgramFrame frame = (ProgramFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().add(new AbsoluteValue());
            frame.setVisible(true);
        });
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
            frame.getContentPane().add(new SelectScreen());
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

            System.out.printf("[AVLesson] On pageNum [%d], line [%d] from [text/av_lesson.txt]\n", GlobalVariables.pageNum, (GlobalVariables.pageNum + 1));
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
        if (GlobalVariables.pageNum == 3){
            ImageIcon image = new ImageIcon("images/absolute_value_formula_concept.png");
            JLabel imageLabel = new JLabel(image);
            imageLabel.setBounds(200,100,image.getIconWidth(),image.getIconHeight());
            this.add(imageLabel);
        }
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

        // Action
        this.addActionListener(_ -> {
            if (GlobalVariables.pageNum == 3){
                System.out.println("[AVNextButton] Reached Limit");
            }
            else {
                GlobalVariables.pageNum++;
                Container parent = this.getParent();
                parent.add(new AVLesson());
                parent.add(new AVPageLabel());
            }
        });
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

        // Action
        this.addActionListener(_ -> {
            if (GlobalVariables.pageNum == 0){
                System.out.println("[AVBeforeButton] Reached Limit");
            }
            else {
                GlobalVariables.pageNum--;
                Container parent = this.getParent();
                parent.add(new AVLesson());
                parent.add(new AVPageLabel());
            }
        });
    }
}

class AVPageLabel extends JLabel{
    AVPageLabel(){
        // Customization
        this.setBounds(300,350,100,25);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("page " + GlobalVariables.pageNum + "-3");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class AVTimer extends JLabel{
    AVTimer(){
        // Customization
        this.setBounds(400,0,100,25);
        this.setOpaque(true);
        this.setBackground(Color.ORANGE);
        this.setText("Timer");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class AVQuestion extends JLabel{
    AVQuestion(){
        // Customization
        this.setBounds(100, 50, 700, 300);
        this.setOpaque(true);
        this.setBackground(Color.ORANGE);
        this.setText("Question");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class AVQuitButton extends JButton{
    AVQuitButton(){
        // Customization
        this.setBounds(400,500,100,50);
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.setText("<html><h1 style=\"font-size:1em; \">Quit</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            GlobalVariables.practice = false;
            Container parent = this.getParent();
            parent.setVisible(false);

            ProgramFrame frame = (ProgramFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().add(new AbsoluteValue());
            frame.setVisible(true);
        });
    }
}