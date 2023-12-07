package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 좌표 압축
public class No18870 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 숫자를 입력받는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 중복을 없앤다.
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(nums[i]);
		}

		// 숫자를 오름차순으로 정리한다.
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			pq.add(it.next());
		}

		// 숫자와 오름차순으로 정리했을 때 몇번째에 위치하는지 저장한다.
		HashMap<Integer, Integer> map = new HashMap<>();
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			map.put(pq.poll(), i);
		}
		
		// 숫자에 따른 오름차순으로 정리했을 때 몇번째에 위치하는지를 저장한다.
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<N; i++) {
			sb.append(map.get(nums[i]) + " ");
		}
		
		System.out.println(sb);

	}

}
