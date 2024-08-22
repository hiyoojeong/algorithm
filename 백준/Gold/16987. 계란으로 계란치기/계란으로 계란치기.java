import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 16987. 계란으로 계란치기
public class Main {

    static class Egg {

        int hp, w;

        public Egg(int hp, int w) {
            this.hp = hp;
            this.w = w;
        }
    }

    static int N, maxCnt;
    static Egg[] eggs;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int hp = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(hp, w);
        }

        maxCnt = 0;
        dfs(0);

        System.out.println(maxCnt);
    }

    public static void dfs(int idx) {
        // 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료한다.
        if (idx == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (eggs[i].hp <= 0) {
                    cnt++;
                }
            }
            maxCnt = Math.max(cnt, maxCnt);
            return;
        }

        // 손에 든 계란이 깨졌으면 치지 않고 넘어간다.
        if (eggs[idx].hp <= 0) {
            dfs(idx + 1);
            return;
        }

        // 꺨 계란을 찾는다.
        boolean isBroken = false;
        for (int i = 0; i < N; i++) {
            // 자기 자신은 깰 수 없다.
            if (i == idx) {
                continue;
            }

            // 이미 깨진 계란은 깨지 않는다.
            if (eggs[i].hp <= 0) {
                continue;
            }

            // 깨기
            eggs[idx].hp -= eggs[i].w;
            eggs[i].hp -= eggs[idx].w;
            isBroken = true;
            // 오른쪽 계란으로 이동
            dfs(idx + 1);
            // 되돌리기
            eggs[idx].hp += eggs[i].w;
            eggs[i].hp += eggs[idx].w;
        }

        // 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
        if(!isBroken) {
            dfs(idx + 1);
        }
    }

}
