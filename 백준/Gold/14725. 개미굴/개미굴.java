import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 14725 개미굴

public class Main {

    static class Node implements Comparable<Node> {
        int depth;
        String val;
        List<Node> next;

        Node(int depth, String val, List<Node> next) {
            this.depth = depth;
            this.val = val;
            this.next = new ArrayList<>();
        }

        @Override
        public int compareTo(Node o) {
            return this.val.compareTo(o.val);
        }
    }

    static StringBuffer sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuffer();

        Node tree = new Node(-1, "", new ArrayList<>());

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            Node cur = tree;
            for (int j = 0; j < M; j++) {
                String val = st.nextToken();

                Node next = findNext(cur, val);
                if (next == null) {
                    next = new Node(j, val, new ArrayList<>());
                    cur.next.add(next);
                }

                cur = next;
            }
        }

        makeAnswer(tree);
        System.out.println(sb);
    }

    public static Node findNext(Node node, String val) {
        for (Node next : node.next) {
            if (val.equals(next.val)) {
                return next;
            }
        }
        return null;
    }

    public static void makeAnswer(Node node) {
        if (node.depth >= 0) {
            for (int i = 0; i < node.depth; i++) {
                sb.append("--");
            }
            sb.append(node.val).append("\n");
        }

        Collections.sort(node.next);

        for (Node next : node.next) {
            makeAnswer(next);
        }
    }
}