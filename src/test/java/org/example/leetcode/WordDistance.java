package org.example.leetcode;

import java.util.List;

public class WordDistance {

    private final List<String> words;
    public WordDistance(List<String> wordsDict) {
        this.words = wordsDict;
    }

    /**
     * @param word1: word1
     * @param word2: word2
     * @return: the shortest distance between two words
     */
    public int shortest(String word1, String word2) {
        int idxW1 = -1;
        int idxW2 = -1;
        int cur = 0;
        int bestD = words.size();
        while (cur < words.size()) {
            if (word1.equals(words.get(cur))) {
                idxW1 = cur;
            } else if (word2.equals(words.get(cur))) {
                idxW2 = cur;
            }
            if (idxW1 != -1 && idxW2 != -1 && Math.abs(idxW1 - idxW2) < bestD) {
                bestD = Math.abs(idxW1 - idxW2);
            }
            cur++;
        }
        return bestD;
    }
}