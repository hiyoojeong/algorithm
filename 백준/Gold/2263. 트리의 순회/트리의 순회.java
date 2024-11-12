import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 트리의 순회
public class Main {

    static Map<Integer, Integer> in;
    static int[] post;
    static StringBuilder pre;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        pre = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        in = new HashMap<>();
        post = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            in.put(Integer.parseInt(st.nextToken()), i);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, N - 1, 0, N - 1);
        System.out.println(pre);
    }

    private static void dfs(int inS, int inE, int postS, int postE) {
        int parent = post[postE];
        pre.append(parent + " ");

        if (postS == postE) {
            return;
        }

        int leftChild = in.get(parent) - inS;
        int rightChild = inE - in.get(parent);

        if(leftChild > 0) {
            dfs(inS, inS + leftChild - 1, postS, postS + leftChild - 1);
        }
        if(rightChild > 0) {
            dfs(inE - rightChild + 1, inE, postS + leftChild, postS + leftChild + rightChild - 1);
        }
    }
}

/*
11
4 3 5 2 6 7 1 9 11 10 8
4 5 3 7 6 2 11 10 9 8 1
 */