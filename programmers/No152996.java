import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 시소 짝꿍
public class No152996 {

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 100, 180, 360, 100, 270 }));
	}

	public static long solution(int[] weights) {
		long answer = 0;

		Map<Double, Integer> map = new HashMap<>();
		Arrays.sort(weights);
		for (int weight : weights) {
			answer += helper(weight, map);
		}
		return answer;
	}

	public static long helper(int w, Map<Double, Integer> map) {
		long answer = 0;
		
		// 시소에 균형맞게 앉을 수 있는 파트너의 무게
		double d1 = w * 1.0;
		double d2 = (w * 2.0) / 3.0;
		double d3 = (w * 1.0) / 2.0;
		double d4 = (w * 3.0) / 4.0;
		
		// 해당 무게를 가진 파트너가 있다면, 파트너 수만큼 answer 증가
		if (map.containsKey(d1)) answer += map.get(d1);
		if (map.containsKey(d2)) answer += map.get(d2);
		if (map.containsKey(d3)) answer += map.get(d3);
		if (map.containsKey(d4)) answer += map.get(d4);
		
		// 자신의 무게 저장
		map.put(w * 1.0, map.getOrDefault(w * 1.0, 0) + 1);
		
		return answer;
	}
}
