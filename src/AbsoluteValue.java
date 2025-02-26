import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class AbsoluteValue extends JLayeredPane {
    AbsoluteValue(){
        // Customization
        this.setBounds(0, 0, GlobalVariables.width, GlobalVariables.height);
        this.setOpaque(true);
        this.setBackground(new Color(221, 229, 182));

        // Reset count to 0 when entering
        GlobalVariables.count = 0;
        System.out.printf("[AbsoluteValue] resetting count on entry to [%d]\n", GlobalVariables.count);


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
            this.add(new AVTimerTimer(this));
            this.add(new AVTimerLabel());
            this.add(new AVQuestion());
            this.add(new AVQuitButton());
            this.add(new AVTextBox());
            this.add(new AVQuestionNumber());
        }
    }
}

class AVPracticeButton extends JButton{
    AVPracticeButton(){
        // Customization
        this.setBounds(400,350,100,25);
        this.setOpaque(true);
        this.setBackground(new Color(108, 88, 76));
        this.setText("<html><h1 style=\"font-size:1em; \">practice</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            GlobalVariables.questionNum = 0; // Reset when starting practice
            GlobalVariables.practice = true; // Set to true to start practice

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
        this.setBackground(new Color(169, 132, 103));
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
        this.setBackground(new Color(108, 88, 76));
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
        this.setBackground(new Color(108, 88, 76));
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
        this.setBackground(new Color(108, 88, 76));
        this.setText("page " + GlobalVariables.pageNum + "-3");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class AVTimerTimer extends JLabel{
    AVTimerTimer(Container parent){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int count = 10 + 1; // + 1 is because GlobalVariable.count starts at 0
            @Override
            public void run() {
                if(!GlobalVariables.practice){
                    timer.cancel();
                }
                GlobalVariables.count = count;
                System.out.println("[AVTimer] " + count + ", set GlobalVariable.count = " + GlobalVariables.count);
                count--;
                if(count < 0){
                    System.out.println("[AVTimer] finished");
                    GlobalVariables.practice = false; // Set practice to false when done
                    GlobalVariables.practiceDone = true; // Set practice done to true when done.
                    timer.cancel();
                }
                parent.add(new AVTimerLabel());
            }
        };
        timer.scheduleAtFixedRate(task, 0,1000);
    }
}

class AVTimerLabel extends JLabel{
    AVTimerLabel(){
        // Customization
        this.setBounds(400,0,100,25);
        this.setOpaque(true);
        this.setBackground(new Color(108, 88, 76));
        if(GlobalVariables.count == 0){
            this.setText("...");
        }else{
            this.setText("Timer: " + GlobalVariables.count);
        }
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}


class AVQuestion extends JLabel{
    AVQuestion(){
        // Difficulty:
        // 1 = Easy ; 2 = Normal ; 3 = Intermediate ; 4 = Hard
        int randDiff = ThreadLocalRandom.current().nextInt(1, 3 + 1);
        System.out.println("[AVQuestion] Random difficulty: " + randDiff);
        if(randDiff == 1){GlobalVariables.currentQuestion = ThreadLocalRandom.current().nextInt(-100, 100 + 1);
            GlobalVariables.currentQuestion = ThreadLocalRandom.current().nextInt(-15, 15 + 1);
            GlobalVariables.currentQuestionString = String.valueOf(GlobalVariables.currentQuestion);
            System.out.println("[AVQuestion] Generated Question: |" + GlobalVariables.currentQuestion + "|");
        } else if (randDiff == 2) {
            int random = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            if(random == 1){
                int twoNum1 = ThreadLocalRandom.current().nextInt(-10, 10 + 1);
                int twoNum2 =ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                GlobalVariables.currentQuestion = twoNum1 + twoNum2;
                GlobalVariables.currentQuestionString = twoNum1 + " + " + twoNum2;
                System.out.println("[AVQuestion] Generated Question: |" + twoNum1 + " + " + twoNum2 + "|");
            } else if (random == 2) {
                int twoNum1 = ThreadLocalRandom.current().nextInt(-10, 10 + 1);
                int twoNum2 =ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                GlobalVariables.currentQuestion = twoNum1 - twoNum2;
                GlobalVariables.currentQuestionString = twoNum1 + " - " + twoNum2;
                System.out.println("[AVQuestion] Generated Question: |" + twoNum1 + " - " + twoNum2 + "|");
            }
        } else if (randDiff == 3){
            int random = ThreadLocalRandom.current().nextInt(1, 2 + 1);
            if(random == 1){
                int threeNum1 = ThreadLocalRandom.current().nextInt(-20, 20 + 1);
                int threeNum2 =ThreadLocalRandom.current().nextInt(-10, 10 + 1);
                int threeNum3 =ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                GlobalVariables.currentQuestion = threeNum1 - threeNum2 * threeNum3;
                GlobalVariables.currentQuestionString = threeNum1 + " - " + threeNum2 + " ( " + threeNum3 + " )";
                System.out.println("[AVQuestion] Generated Question: |" + threeNum1 + " - " + threeNum2 + " ( " + threeNum3 + " )" + "|");
            } else if (random == 2) {
                int threeNum1 = ThreadLocalRandom.current().nextInt(-5, 5 + 1);
                int threeNum2 =ThreadLocalRandom.current().nextInt(-10, 10 + 1);
                int threeNum3 =ThreadLocalRandom.current().nextInt(-20, 20 + 1);
                GlobalVariables.currentQuestion = threeNum1 * threeNum2 + threeNum3;
                GlobalVariables.currentQuestionString = " ( " + threeNum1 + " ) " + threeNum2 + " + " + threeNum3;
                System.out.println("[AVQuestion] Generated Question: |" + " ( " + threeNum1 + " ) " + threeNum2 + " + " + threeNum3 + "|");
            }
        }




        int answer = Math.abs(GlobalVariables.currentQuestion);
        System.out.println("[AVQuestion] Answer: " + answer);

        // Customization
        this.setBounds(100, 50, 700, 300);
        this.setOpaque(true);
        this.setBackground(new Color(169, 132, 103));
        this.setText("<html><p style = \"font-size:8em;\">| " + GlobalVariables.currentQuestionString + " |</p></html>");
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}

class AVQuitButton extends JButton{
    AVQuitButton(){
        // Customization
        this.setBounds(700,350,100,25);
        this.setOpaque(true);
        this.setBackground(new Color(108, 88, 76));
        this.setText("<html><h1 style=\"font-size:1em; \">Quit</h1></html>");
        this.setForeground(Color.white);
        this.setFocusPainted(false); // Gets rid of an ugly artifact

        // Action
        this.addActionListener(_ -> {
            System.out.println("[AVQuitButton] Quit practice");
            GlobalVariables.practice = false;
            Container parent = this.getParent();
            parent.setVisible(false);

            ProgramFrame frame = (ProgramFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().add(new AbsoluteValue());
            frame.setVisible(true);
        });
    }
}

class AVTextBox extends JTextField{
    private final static String newline = "\n";
    AVTextBox (){
        // Customization
        this.setBounds(400,350,100,25);
        this.setOpaque(true);
        this.setBackground(new Color(108, 88, 76));
        this.setForeground(Color.white);
        JTextArea textArea = new JTextArea(5, 20);

        this.addActionListener(_ -> {
            // Stole this from oracles website
            // This body grabs the users input, confirming it on the Console
            String text = this.getText();
            textArea.append(text + newline);
            this.selectAll();
            GlobalVariables.currentResponse = text;
            text = text.replaceAll("\\s+",""); // REMOVE ALL SPACES
            System.out.println("[AVTextBox] user entered in: " + text);

            // This body evaluates the current questions answer
            int answer = Math.abs(GlobalVariables.currentQuestion);

            // If response is correct add a point to correctCount
            // Else if response incorrect add a point to incorrectCount
            if(String.valueOf(answer).equals(text)){
                GlobalVariables.correctCount++;
                System.out.println("[AVTextBox] answer " + GlobalVariables.ANSI_GREEN + "CORRECT" + GlobalVariables.ANSI_RESET + ", correctCount: " + GlobalVariables.ANSI_GREEN + GlobalVariables.correctCount + GlobalVariables.ANSI_RESET);
            }
            else{
                GlobalVariables.incorrectCount++;
                System.out.println("[AVTextBox] answer " + GlobalVariables.ANSI_RED + "INCORRECT" + GlobalVariables.ANSI_RESET + ", incorrectCount: " + GlobalVariables.ANSI_RED + GlobalVariables.incorrectCount + GlobalVariables.ANSI_RESET);
            }

            // Get parent
            Container parent = this.getParent();

            // Update AVQuestionNumber
            GlobalVariables.questionNum++;
            parent.add(new AVQuestionNumber());

            // Generate a new question
            parent.add(new AVQuestion());
        });
    }
}

class AVQuestionNumber extends JLabel{
    AVQuestionNumber(){
        // Customization
        this.setBounds(100,350,100,25);
        this.setOpaque(true);
        this.setBackground(new Color(108, 88, 76));
        this.setText("Answered: " + GlobalVariables.questionNum);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setForeground(Color.white);
    }
}