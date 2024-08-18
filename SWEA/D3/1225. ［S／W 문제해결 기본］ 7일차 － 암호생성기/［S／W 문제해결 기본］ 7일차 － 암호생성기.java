import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Solution {

    static Queue<Integer> queue = new LinkedList<>();
    ;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuffer answer = new StringBuffer();

        for (int test_case = 1; test_case <= 10; test_case++) {
            // 테스트 케이스의 번호
            int T = sc.nextInt();

            // 8개의 데이터
            for (int i = 0; i < 8; i++) {
                queue.add(sc.nextInt());
            }

            int cycle = 1; // 1~5까지 차례대로 감소시키는 값
            while (true) {
                int now = queue.poll() - cycle;

                if (now <= 0) { // 0이 되거나 0보다 작아지는 경우, 0으로 유지 후 프로그램 종료
                    queue.add(0);
                    break;
                } else {
                    queue.add(now);
                }

                cycle = (cycle % 5) + 1;
            }

            answer.append("#").append(T).append(" ");
            while (!queue.isEmpty()) {
                answer.append(queue.poll()).append(" ");
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }
}