package gui;

import logic.FileStats;
import logic.TextAnalyzer;
import db.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class TextAnalyzerGUI extends JFrame {
    private JTextArea textArea;
    private JButton openFileButton;

    public TextAnalyzerGUI() {
        super("Text Analyzer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        openFileButton = new JButton("Open Text File");
        openFileButton.addActionListener(e -> openAndAnalyzeFile());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(openFileButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        // Initialize database at startup
        DatabaseManager.initializeDatabase();
    }

    private void openAndAnalyzeFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Analyze the file
                FileStats stats = TextAnalyzer.analyzeFile(selectedFile);

                // Display stats in text area
                StringBuilder sb = new StringBuilder();
                sb.append("File: ").append(selectedFile.getName()).append("\n");
                sb.append("Lines: ").append(stats.getLineCount()).append("\n");
                sb.append("Words: ").append(stats.getWordCount()).append("\n");
                sb.append("Characters: ").append(stats.getCharCount()).append("\n");
                sb.append("Top Words:\n");
                stats.getTopWords().forEach(entry -> 
                    sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n")
                );

                textArea.setText(sb.toString());

                // Save results to database
                DatabaseManager.saveAnalysis(
                    selectedFile.getName(),
                    stats.getLineCount(),
                    stats.getWordCount(),
                    stats.getCharCount()
                );

                JOptionPane.showMessageDialog(this, "Analysis saved to database successfully.");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextAnalyzerGUI gui = new TextAnalyzerGUI();
            gui.setVisible(true);
        });
    }
}
