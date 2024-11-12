import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 보석 도둑
public class Main {

    static class Jewelry {

        int weight, value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 보석 수
        int K = Integer.parseInt(st.nextToken()); // 가방 수

        // 무게 오름차순, 가격 내림차순
        Queue<Jewelry> jewelries = new PriorityQueue<>(
            (o1, o2) -> o1.weight != o2.weight ? o1.weight - o2.weight : o2.value - o1.value);
        // 무게 오름차순
        Queue<Integer> bags = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewelries.add(new Jewelry(weight, value));
        }
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        long answer = 0;
        // 가격 내림차순
        Queue<Integer> acquiredJewelries = new PriorityQueue<>(Collections.reverseOrder());
        while (!bags.isEmpty()) {
            int bagWeight = bags.poll();
            while (!jewelries.isEmpty() && jewelries.peek().weight <= bagWeight) {
                acquiredJewelries.add(jewelries.poll().value);
            }
            if(!acquiredJewelries.isEmpty()) {
                answer += acquiredJewelries.poll();
            }
        }

        System.out.println(answer);
    }
}