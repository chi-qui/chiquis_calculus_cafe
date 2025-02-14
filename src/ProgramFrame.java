import javax.swing.*;

public class ProgramFrame extends JFrame {
    ProgramFrame() {
        // Frame Customization
        this.setTitle("Chiqui's Calculus Café");
        this.setLayout(null);
        this.setSize(GlobalVariables.width,GlobalVariables.height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // + Components
        this.add(new StartScreen()); // Apparently I only need this, IDK-WTF I'm doing

        // Make Frame Visible
        this.setVisible(true);
    }
}