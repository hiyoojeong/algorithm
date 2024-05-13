// 타겟 넘버
public class No43165 {

	static int[] op = { 0, 1 }; // 0이면 음수, 1이면 양수
	static int result  = 0; // 타겟 넘버를 만드는 방법의 수

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;

		System.out.println(solution(numbers, target));
	}

	public static int solution(int[] numbers, int target) {
		int answer = 0;

		int[] info = new int[numbers.length]; // numbers를 뺄지, 더할지 정보를 저장한 배열
		recursion(0, info, numbers, target);
		
		answer = result;
		return answer;
	}

	public static void recursion(int cnt, int[] info, int[] numbers, int target) {
		if (cnt == info.length) { // numbers를 뺄지, 더할지 정보를 저장한 배열 생성완료
			int sum = 0, number;
			for (int i = 0; i < info.length; i++) {
				if (info[i] == 0) { // number를 빼는 경우
					number = numbers[i] * (-1);
				} else { // number를 더는 경우
					number = numbers[i];
				}
				sum += number;
			}
			if(sum == target) {
				result++;
			}
			return;
		}

		for (int i = 0; i < 2; i++) { // numbers를 뺄지, 더할지 정보를 저장
			info[cnt] = op[i];
			recursion(cnt + 1, info, numbers, target);
		}
	}

}
