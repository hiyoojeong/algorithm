// https://school.programmers.co.kr/learn/courses/30/lessons/135807
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%88%AB%EC%9E%90-%EC%B9%B4%EB%93%9C-%EB%82%98%EB%88%84%EA%B8%B0-JAVA

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
		int answerA = getMaxNum(arrayA, arrayB);
		int answerB = getMaxNum(arrayB, arrayA);

		return answerA > answerB ? answerA : answerB;
	}

	public int getMaxNum(int[] division, int[] non_division) {
		int num = division[0];
		for (int i = 0; i < division.length; i++)
			if (num > division[i])
				num = division[i];

		while (num > 1) {
			boolean check = true;
			
			for (int i = 0; i < division.length; i++) {
				if (division[i] % num != 0) {
					check = false;
					break;
				}
			}
			
			if(check) {
				for (int i = 0; i < non_division.length; i++) {
					if (non_division[i] % num == 0) {
						check = false;
						break;
					}
				}
			}
			
			if(check)
				return num;
			
			num--;
		}

		return 0;
	}
}