import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1052 물병
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 현재 물병 수
        int K = Integer.parseInt(st.nextToken()); // 최대 옮길 수 있는 물병 수

        int max = N;
        while (getBottleCnt(max) > K) {
            max++;
        }
        
        System.out.println(max-N);
    }

    public static int getBottleCnt(int n) {
        int cnt = 0;

        String bin = Integer.toBinaryString(n);
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                cnt++;
            }
        }

        return cnt;
    }
}