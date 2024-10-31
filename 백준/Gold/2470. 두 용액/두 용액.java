import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 두 용액
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int minDist = Integer.MAX_VALUE;
        int[] answer = new int[2];
        for (int i = 0; i < N - 1; i++) {
            int left = i + 1;
            int right = N - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int dist = arr[i] + arr[mid];

                if (Math.abs(dist) < minDist) {
                    minDist = Math.abs(dist);
                    answer = new int[]{arr[i], arr[mid]};
                }

                if (dist == 0) {
                    break;
                } else if (dist < 0) { // 값을 키워서 0에 더 가깝게
                    left = mid + 1;
                } else if (dist > 0) { // 값을 줄여서 0에 더 가깝게
                    right = mid - 1;
                }
            }
            if (minDist == 0) {
                break;
            }
        }

        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1]);
    }
}