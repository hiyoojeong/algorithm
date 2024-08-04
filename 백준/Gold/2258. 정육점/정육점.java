import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 정육점
public class Main {

    static class Meat implements Comparable<Meat> {

        int weight, price;

        public Meat(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Meat o) {
            // 가격 기준으로 오름차순
            // 무게 기준으로 내림차순
            return this.price != o.price ? this.price - o.price : o.weight - this.weight;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 고기 덩이리의 개수
        int M = Integer.parseInt(st.nextToken()); // 은혜가 필요한 고기의 양

        List<Meat> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            list.add(new Meat(weight, price));
        }

        // 정렬
        // 가격 기준 오름차순, 무게 기준 내림차순
        Collections.sort(list);

        int totalWeight = list.get(0).weight;
        int totalPrice = list.get(0).price;

        int result = Integer.MAX_VALUE;

        boolean isPossible = false;
        for (int i = 1; i < N; i++) {
            Meat pre = list.get(i - 1);
            Meat current = list.get(i);

            // 가격 업데이트
            // 현재 고기 가격이 이전 고기 가격보다 더 비싸다면, 이전 고기를 모두 덤을 받을 수 있다.
            if (pre.price < current.price) {
                totalPrice = current.price;
            }
            // 현재 고기 가격이 이전 고기 가격과 같다면, 현재 고기를 돈 주고 사야한다.
            else if (pre.price == current.price) {
                totalPrice += current.price;
            }

            // 무게 업데이트
            totalWeight += current.weight;

            // 은혜가 원하는 양의 고기를 구매한 경우, 최소 가격을 업데이트한다.
            if (totalWeight >= M) {
                isPossible = true;
                // 최소 가격이면 업데이트
                result = Math.min(totalPrice, result);
            }
        }

        System.out.println(isPossible ? result : -1);
    }
}
