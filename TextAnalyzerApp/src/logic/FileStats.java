package logic;

import java.util.List;
import java.util.Map;

public class FileStats {
    private int lineCount;
    private int wordCount;
    private int charCount;
    private List<Map.Entry<String, Integer>> topWords;

    public FileStats(int lineCount, int wordCount, int charCount, List<Map.Entry<String, Integer>> topWords) {
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.charCount = charCount;
        this.topWords = topWords;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharCount() {
        return charCount;
    }

    public List<Map.Entry<String, Integer>> getTopWords() {
        return topWords;
    }
} 