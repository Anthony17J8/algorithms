package org.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given a string s, return the longest
 * palindromic
 * <p>
 * substring
 * in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {


    @Test
    public void longestPalindrome() {
        assertEquals("bb", getResult("cbbd"));
        assertEquals("a", getResult("a"));
        assertEquals("a", getResult("ac"));
//        assertEquals("aba", getResult("babad"));
        assertEquals("aaaaaaaaa", getResult("aaaaaaaaa"));
        assertEquals("aca", getResult("aacabdkacaa"));
        assertEquals("vkkv", getResult("mqizdjrfqtmcsruvvlhdgzfrmxgmmbguroxcbhalzggxhzwfznfkrdwsvzhieqvsrbyedqxwmnvovvnesphgddoikfwuujrhxwcrbttfbmlayrlmpromlzwzrkjkzdvdkpqtbzszrngczvgspzpfnvwuifzjdrmwfadophxscxtbavrhfkadhxrmvlmofbzqshqxazzwjextdpuszwgrxirmmlqitjjpijptmqfbggkwaolpbdglmsvlwdummsrdyjhmgrasrblpjsrpkkgknsucsshjuxunqiouzrdwwooxclutkrujpfebjpoodvhknayilcxjrvnykfjhvsikjabsdnvgguoiyldshbsmsrrlwmkfmyjbbsylhrusubcglaemnurpuvlyyknbqelmkkyamrcmjbncpafchacckhymtasylyfjuribqxsekbjkgzrvzjmjkquxfwopsbjudggnfbuyyfizefgxamocxjgkwxidkgursrcsjwwyeiymoafgyjlhtcdkgrikzzlenqgtdukivvdsalepyvehaklejxxmmoycrtsvzugudwirgywvsxqapxyjedbdhvkkvrxxsgifcldkspgdnjnnzfalaslwqfylmzvbxuscatomnmgarkvuccblpoktlpnazyeazhfucmfpalbujhzbykdgcirnqivdwxnnuznrwdjslwdwgpvjehqcbtjljnxsebtqujhmteknbinrloregnphwhnfidfsqdtaexencwzszlpmxjicoduejjomqzsmrgdgvlrfcrbyfutidkryspmoyzlgfltclmhaeebfbunrwqytzhuxghxkfwtjrfyxavcjwnvbaydjnarrhiyjavlmfsstewtxrcifcllnugldnfyswnsewqwnvbgtatccfeqyjgqbnufwttaokibyrldhoniwqsflvlwnjmffoirzmoxqxunkuepj"));

    }

    // work but too long
    private String getResult(String s) {
        if (s.length() == 1) {
            return s;
        }
        int pointer1 = 0;
        int pointer2 = pointer1 + 1;
        String result = Character.valueOf(s.charAt(pointer1)).toString();

        while (pointer1 < s.length() - 1) {
            char c1 = s.charAt(pointer1);
            char c2 = s.charAt(pointer2);

            if (pointer2 + 1 - pointer1 > result.length() && c1 == c2 && isPalindromic(pointer1, pointer2 + 1, s)) {
                result = s.substring(pointer1, pointer2 + 1);
            }
            pointer2++;
            if (pointer2 == s.length()) {
                if (result.length() == s.length() - pointer1) {
                    break;
                }
                pointer1++;
                pointer2 = pointer1 + 1;
            }
        }
        return result;
    }

    private boolean isPalindromic(int i, int j, String s) {
        int left = i;
        int right = j - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
