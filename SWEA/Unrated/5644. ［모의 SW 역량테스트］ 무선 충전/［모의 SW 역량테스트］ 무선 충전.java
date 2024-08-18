import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 무선 충전
public class Solution {

    static class Pos {

        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(int dir) {
            if (dir == 1) { // 상
                y--;
            } else if (dir == 2) { // 우
                x++;
            } else if (dir == 3) { // 하
                y++;
            } else if (dir == 4) { // 좌
                x--;
            }
        }
    }

    static class BC {

        Pos pos;
        int range, power;

        public BC(Pos pos, int range, int power) {
            this.pos = pos;
            this.range = range;
            this.power = power;
        }

    }

    static int dirCnt; // 총 이동시간
    static int BCCnt; // BC의 개수

    static int[] dirA; // 사용자 A의 이동 정보
    static int[] dirB; // 사용자 B의 이동 정보
    static BC[] bcs; // BC의 정보

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer answer = new StringBuffer();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            dirCnt = Integer.parseInt(st.nextToken()); // 총 이동 시간
            BCCnt = Integer.parseInt(st.nextToken()); // BC의 개수

            dirA = new int[dirCnt]; // 사용자 A의 이동 정보
            dirB = new int[dirCnt]; // 사용자 B의 이동 정보
            bcs = new BC[BCCnt]; // BC의 정보

            // 사용자 A의 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < dirCnt; i++) {
                dirA[i] = Integer.parseInt(st.nextToken());
            }
            // 사용자 B의 이동 정보
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < dirCnt; i++) {
                dirB[i] = Integer.parseInt(st.nextToken());
            }

            // BC의 정보
            for (int i = 0; i < BCCnt; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()); // x 좌표
                int y = Integer.parseInt(st.nextToken()); // y 좌표
                int range = Integer.parseInt(st.nextToken()); // 충전 범위
                int power = Integer.parseInt(st.nextToken()); // 처리량
                bcs[i] = new BC(new Pos(x, y), range, power);
            }

            answer.append(String.format("#%d %d\n", t, solution()));
        }

        System.out.println(answer);
    }

    public static int solution() {
        // 초기 좌표
        Pos userA = new Pos(1, 1);
        Pos userB = new Pos(10, 10);

        // 최대 충전량
        int result = 0;

        // 초기 좌표에서 충전
        result += charge(userA, userB);

        // 이동 후 좌표에서 충전
        for (int i = 0; i < dirCnt; i++) {
            userA.move(dirA[i]);
            userB.move(dirB[i]);
            result += charge(userA, userB);
        }

        return result;
    }

    public static int charge(Pos userA, Pos userB) {
        List<Integer> chargeA = new ArrayList<>(); // A를 충전할 수 있는 BC 정보
        List<Integer> chargeB = new ArrayList<>(); // B를 충전할 수 있는 BC 정보

        for (int i = 0; i < BCCnt; i++) {
            int distA = Math.abs(userA.x - bcs[i].pos.x) + Math.abs(
                userA.y - bcs[i].pos.y); // 사용자 A와 BC와의 거리
            int distB = Math.abs(userB.x - bcs[i].pos.x) + Math.abs(
                userB.y - bcs[i].pos.y); // 사용자 B와 BC와의 거리

            if (bcs[i].range >= distA) { // BC의 충전 범위내이므로, A를 충전할 수 있다.
                chargeA.add(i);
            }
            if (bcs[i].range >= distB) { // BC의 충전 범위내이므로, B를 충전할 수 있다.
                chargeB.add(i);
            }
        }

        int max = 0;

        // A와 B 둘다 충전할 수 있는 BC가 존재하는 경우
        if (chargeA.size() > 0 && chargeB.size() > 0) {
            for (int i : chargeA) {
                for (int j : chargeB) {
                    int temp = 0;
                    // 같은 BC인 경우, 처치량을 나눠가지므로 하나의 처리량만 더해준다.
                    if (i == j) {
                        temp += bcs[i].power;
                    }
                    // 다른 BC의 경우, 각각 처리량을 더해준다.
                    else {
                        temp += bcs[i].power;
                        temp += bcs[j].power;
                    }

                    max = Math.max(temp, max);
                }
            }
        }
        // A만 충전할 수 있는 BC가 존재하는 경우
        else if (chargeA.size() > 0 && chargeB.size() == 0) {
            for (int i : chargeA) {
                max = Math.max(bcs[i].power, max);
            }
        }
        // B만 충전할 수 있는 BC가 존재하는 경우
        else if (chargeA.size() == 0 && chargeB.size() > 0) {
            for (int i : chargeB) {
                max = Math.max(bcs[i].power, max);
            }
        }

        return max;
    }


}
