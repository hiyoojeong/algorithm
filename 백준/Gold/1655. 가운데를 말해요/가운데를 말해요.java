import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

// 가운데를 말해요
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 중앙값 이하
        Queue<Integer> minHeap = new PriorityQueue<>(); // 중앙값 초과

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (maxHeap.size() == minHeap.size()) { // 앞부분(중앙값 이하)에 넣기
                maxHeap.add(num);
            } else { // 뒷부분(중앙값 초과)에 넣기
                minHeap.add(num);
            }

            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) { // 순서가 꼬였다면 peek끼리 교환
                minHeap.add(maxHeap.poll());
                maxHeap.add(minHeap.poll());
            }

            sb.append(maxHeap.peek() + "\n");
        }

        System.out.println(sb);
    }
}