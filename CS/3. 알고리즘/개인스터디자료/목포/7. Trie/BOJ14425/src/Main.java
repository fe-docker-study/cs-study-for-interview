import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Main에서 각 Node에 접근하면 시간초과가 남..,,
 * Main에 직접 작성할 시 가능..
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int cnt = 0;
        TrieNode rootNode = new TrieNode();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            rootNode.insert(str);
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine();

            if (rootNode.contains(str)) cnt++;
        }

        System.out.println(cnt);

        /*Map<String, Integer> map = new HashMap<>();
        int cnt = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            map.put(br.readLine(), 1);
        }

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (map.get(str) != null) cnt++;
        }*/

    }
}

class TrieNode {
    Map<Character, TrieNode> childNode = new HashMap<>();
    boolean isLastChar;

    public void insert(String word) {
        TrieNode trieNode = this;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            trieNode.childNode.computeIfAbsent(c, s -> new TrieNode());
            trieNode = trieNode.childNode.get(c);

            if(i == word.length() - 1) {
                trieNode.isLastChar = true;
                return;
            }
        }
    }

    public boolean contains(String word) {
        TrieNode trieNode = this;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            TrieNode node = trieNode.childNode.get(c);
            if (node == null) return false;
            trieNode = node;
        }

        return trieNode.isLastChar;
    }
}

