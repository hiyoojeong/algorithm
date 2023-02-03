// https://school.programmers.co.kr/learn/courses/30/lessons/43165
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%83%80%EA%B2%9F-%EB%84%98%EB%B2%84-JAVA

class Solution {
    static int[] op = { 0, 1 }; // 0이면 음수, 1이면 양수
	static int result  = 0;
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        int[] info = new int[numbers.length];
		recursion(0, info, numbers, target);
		answer = result;
        
        return answer;
    }
    
    public static void recursion(int cnt, int[] info, int[] numbers, int target) {
		if (cnt == info.length) {
			int sum = 0, n;
			for (int i = 0; i < info.length; i++) {
				if (info[i] == 0) {
					n = numbers[i] * (-1);
				} else {
					n = numbers[i];
				}
				sum += n;
			}
			if(sum == target) {
				result++;
			}
			return;
		}

		for (int i = 0; i < 2; i++) {
			info[cnt] = op[i];
			recursion(cnt + 1, info, numbers, target);
		}
	}
}