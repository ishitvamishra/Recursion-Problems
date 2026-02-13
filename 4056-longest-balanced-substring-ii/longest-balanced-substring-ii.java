class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int max = 0;

        // 1. longest run of the same character (single-character balanced substrings)
        int run = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                run++;
            } else {
                max = Math.max(max, run);
                run = 1;
            }
        }
        max = Math.max(max, run);

        // 2. two-character pairs (exclude the third character)
        max = Math.max(max, longestTwoChar(s, 'a', 'b', 'c'));
        max = Math.max(max, longestTwoChar(s, 'a', 'c', 'b'));
        max = Math.max(max, longestTwoChar(s, 'b', 'c', 'a'));

        // 3. three-character case
        max = Math.max(max, longestThreeChar(s));

        return max;
    }

    // Longest substring containing only x and y (no z) with equal counts of x and y
    private int longestTwoChar(String s, char x, char y, char z) {
        int n = s.length();
        int max = 0;
        int start = 0;
        for (int i = 0; i <= n; i++) {
            if (i == n || s.charAt(i) == z) {
                if (start < i) {
                    int len = processSegment(s, start, i - 1, x, y);
                    if (len > max) max = len;
                }
                start = i + 1;
            }
        }
        return max;
    }

    // Process a contiguous block that contains only x and y (indices l..r)
    private int processSegment(String s, int l, int r, char x, char y) {
        Map<Integer, Integer> firstOccurrence = new HashMap<>();
        int diff = 0;
        firstOccurrence.put(0, l - 1); // virtual position before the segment
        int max = 0;
        for (int i = l; i <= r; i++) {
            char c = s.charAt(i);
            if (c == x) diff++;
            else if (c == y) diff--; // no other characters appear in the segment

            Integer prev = firstOccurrence.get(diff);
            if (prev != null) {
                int len = i - prev; // substring (prev+1 .. i) has equal x and y
                if (len > max) max = len;
            } else {
                firstOccurrence.put(diff, i);
            }
        }
        return max;
    }

    // Longest substring where a, b and c all appear the same number of times
    private int longestThreeChar(String s) {
        int n = s.length();
        Map<Long, Integer> firstOccurrence = new HashMap<>();
        int countA = 0, countB = 0, countC = 0;
        firstOccurrence.put(encode(0, 0), -1); // before the string starts
        int max = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') countA++;
            else if (c == 'b') countB++;
            else countC++;

            int dAB = countA - countB;
            int dAC = countA - countC;
            long key = encode(dAB, dAC);

            Integer prev = firstOccurrence.get(key);
            if (prev != null) {
                int len = i - prev; // substring (prev+1 .. i) has equal counts
                if (len > max) max = len;
            } else {
                firstOccurrence.put(key, i);
            }
        }
        return max;
    }

    // Encode two ints into one long (each fits in 32 bits)
    private long encode(int a, int b) {
        return ((long) a << 32) | (b & 0xffffffffL);
    }
}
