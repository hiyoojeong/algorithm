import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 처음에는 정렬을 해서 넣으면 쉽게 구하겠다 싶음 -> 들어오는 순서도 중요하다고 생각함
// 근데 N과 K가 매우 적어서 100 * 100 * 100 = 100만 이라 4중포문까지도 가능하겠다 싶음
// 96퍼 틀림 뭐지 -> 
public class Main {
    static int[] plug;
    static int[] num;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        plug = new int[N];
        num = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int count = 0;

        for (int i = 0; i < K; i++) {
            int n = num[i]; // 지금 꽂아야 하는 애

            if (find(n, N)) { // 이미 꽂혀있으면 넘긴다.
                continue;
            }

            if (idx < N) { // 꽂을 수 있으면 꽂는다.
                // plug[count++] = n;
            	plug[idx++] = n;
            } 
            else {
                
                int change_plug = 0;
                int latest_use = 0;

                for (int j = 0; j < N; j++) {
                    int rembmer_k = 0;
                    for (int k = i + 1; k < K; k++) {
                        if (plug[j] == num[k]) {
                            rembmer_k = k; // 멀티탭에 j번쨰로 꽂혀 있는 게, 앞으로 가장 나중에 나오는 위치를 저장
                            break;
                        }
                    }
                    // 이후에 사용되지 않을 거 저장
                    if(rembmer_k == 0) {
                        change_plug = j;
                        break;
                    }
                    // 가장 늦게 사용되는 거 저장
                    if (rembmer_k > latest_use) {
                        latest_use = rembmer_k;
                        change_plug = j;
                    }
                    
                }

                plug[change_plug] = n;
                count++;
            }
        }
         // System.out.println(count-N);
        System.out.println(count);
    }

    static boolean find(int n, int size) {
        for (int i = 0; i < size; i++) {
            if (plug[i] == n) {
                return true;
            }
        }
        return false;
    }
}