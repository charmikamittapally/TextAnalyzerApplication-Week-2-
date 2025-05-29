import gui.TextAnalyzerGUI;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextAnalyzerGUI gui = new TextAnalyzerGUI();
            gui.setVisible(true); // ← Add this line
        });
    }
}
