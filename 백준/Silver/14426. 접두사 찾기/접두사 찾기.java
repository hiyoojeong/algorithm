import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접두사 찾기
public class Main {

    static class Node {

        Node[] next = new Node[26];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node root = new Node();
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            Node cur = root;
            for (int j = 0; j < word.length(); j++) {
                int pos = word.charAt(j) - 'a';
                if (cur.next[pos] == null) { // 다음 문자가 없는 경우, 연결
                    cur.next[pos] = new Node();
                }
                cur = cur.next[pos];
            }
        }

        int cnt = 0;
        for (int i = 0; i < m; i++) {
            String word = br.readLine();
            Node cur = root;
            boolean isFind = true;
            for (int j = 0; j < word.length(); j++) {
                int pos = word.charAt(j) - 'a';
                if (cur.next[pos] == null) { // 다음 문자가 없는 경우, 접두사 아님
                    isFind = false;
                    break;
                }
                cur = cur.next[pos];
            }
            if (isFind) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}