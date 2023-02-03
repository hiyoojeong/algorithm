// https://school.programmers.co.kr/learn/courses/30/lessons/150368
// https://velog.io/@hiyoojeong/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-2020-KAKAO-BLIND-RECRUITMENT-%EC%9D%B4%EB%AA%A8%ED%8B%B0%EC%BD%98-%ED%95%A0%EC%9D%B8%ED%96%89%EC%82%AC-JAVA

class Solution {
    static int[] sales = { 10, 20, 30, 40 };
	static int[] saleArr = null;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = { 0, 0 };

		saleArr = new int[emoticons.length];
		permutation(0, users, emoticons, answer);

		return answer;
    }
    
    private static void permutation(int cnt, int[][] users, int[] emoticons, int[] answer) {
		if (cnt == emoticons.length) {
			int totplus = 0, totcost = 0;
			for (int i = 0; i < users.length; i++) {
				int s = users[i][0], c = users[i][1], nowcost = 0;
				for (int j = 0; j < emoticons.length; j++) {
					if (s <= saleArr[j]) {
						int cost = emoticons[j] - (emoticons[j] * saleArr[j] / 100);
						nowcost += cost;
						if (nowcost >= c) {
							nowcost = 0;
							totplus++;
							break;
						}
					}
				}
				totcost += nowcost;
				
			}

			if (answer[0] < totplus) {
				answer[0] = totplus;
				answer[1] = totcost;
			} else if (answer[0] == totplus) {
				if (answer[1] < totcost) {
					answer[1] = totcost;
				}
			}
			return;
		}

		for (int i = 0; i < sales.length; i++) {
			saleArr[cnt] = sales[i];
			permutation(cnt + 1, users, emoticons, answer);
		}
	}
}