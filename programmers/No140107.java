// 점 찍기
public class No140107 {

	public static void main(String[] args) {
		System.out.println(solution(2, 4));
		System.out.println(solution(1, 5));
	}

	static public long solution(int k, int d) {
		long answer = 0;

		// 시간초과
//		double x = 0, y = 0, distance;
//		for (long i = 0; i <= d; i++) {
//			for (long j = 0; j <= d; j++) {
//				x = i * k;
//				y = j * k;
//				
//				distance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
//				if (distance > d) 
//					break;
//				
//				answer++;
//			}
//		}
		
		long maxy = 0; // 최대로 가질 수 있는 y
		for (long x = 0; x <= d; x += k) {
			// 좌표 x와 최대 거리 d에 대하여 최대로 가질 수 있는 y를 구한다.
			maxy = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
			// k배 단위로 y값을 가질 수 있으므로 k로 나눈 뒤 1을 더한다.
			answer += (maxy / k) + 1;
		}

		return answer;
	}

}
