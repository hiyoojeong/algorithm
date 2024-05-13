import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();

		PriorityQueue<Integer> nums = new PriorityQueue<Integer>(Collections.reverseOrder());
		while (N != 0) {
			nums.add(N % 10);
			N /= 10;
		}
		
		StringBuffer sb = new StringBuffer();
		int size = nums.size();
		for(int i=0; i<size; i++) {
			sb.append(nums.poll());
		}
		
		System.out.println(sb);

	}

}
