import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 수열
public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 수열의 길이
        int[] arr = new int[N]; // 수열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxLen = 1;

        int asc = 1, des = 1;
        for (int i = 1; i < N; i++) {
            // 증가한 경우
            if (arr[i] >= arr[i - 1]) {
                asc++;
            } else {
                maxLen = Math.max(asc, maxLen);
                asc = 1;
            }

            // 감소한 경우
            if (arr[i] <= arr[i - 1]) {
                des++;
            } else {
                maxLen = Math.max(des, maxLen);
                des = 1;
            }
        }
        
        maxLen = Math.max(des, maxLen);
        System.out.println(maxLen);
    }

}
