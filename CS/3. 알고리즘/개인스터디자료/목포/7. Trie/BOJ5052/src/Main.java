import javax.crypto.spec.PSource;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];
            TrieNode rootNode = new TrieNode();

            boolean isContain = false;
            for (int i = 0; i < n; i++) {
                numbers[i] = br.readLine();
                rootNode.insert(numbers[i]);
            }

            for (int i = 0; i < n; i++) {
                if(rootNode.contains(numbers[i])) {
                    isContain = true;
                    break;
                }
            }

            if (isContain) System.out.println("NO");
            else System.out.println("YES");
        }

    }
}

class TrieNode {
    Map<Character, TrieNode> childNode = new HashMap<>();
    boolean isLastChar;

    public void insert(String word) {
        TrieNode thisNode = this;

        for (int i = 0 ; i < word.length(); i++) {
            char c = word.charAt(i);

            thisNode.childNode.computeIfAbsent(c, s -> new TrieNode());
            thisNode = thisNode.childNode.get(c);

            if (i == word.length() - 1) {
                thisNode.isLastChar = true;
                return;
            }
        }
    }

    public boolean contains(String word) {
        TrieNode thisNode = this;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode node = thisNode.childNode.get(c);

            if (node == null) return false;
            thisNode = node;
        }

        if (thisNode.isLastChar) {
            if (thisNode.childNode.isEmpty()) return false;
        }

        return true;
    }
}
