package logic;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class TextAnalyzer {

    public static FileStats analyzeFile(File file) throws IOException {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;
        Map<String, Integer> wordFreq = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                lineCount++;
                charCount += line.length();

                // Split line into words based on non-word characters
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (word.isEmpty()) continue;
                    wordCount++;
                    word = word.toLowerCase();
                    wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
                }
            }
        }

        // Sort words by frequency descending
        List<Entry<String, Integer>> sortedWords = new ArrayList<>(wordFreq.entrySet());
        sortedWords.sort((a, b) -> b.getValue() - a.getValue());

        // Take top 10 or fewer
        List<Entry<String, Integer>> topWords = sortedWords.size() > 10 ? sortedWords.subList(0, 10) : sortedWords;

        return new FileStats(lineCount, wordCount, charCount, topWords);
    }
}

