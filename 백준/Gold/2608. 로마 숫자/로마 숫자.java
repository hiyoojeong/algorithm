import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 로마 숫자
public class Main {

    static class Num implements Comparable<Num> {

        String mark;
        int value;

        public Num(String mark, int value) {
            this.mark = mark;
            this.value = value;
        }

        @Override
        public int compareTo(Num o) {
            return o.value - this.value;
        }
    }

    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        // 입력
        String n1 = br.readLine();
        String n2 = br.readLine();

        int sum_arabian = convertToArabian(n1) + convertToArabian(n2);
        String sum_roman = convertToRoman(sum_arabian);

        System.out.println(sum_arabian);
        System.out.println(sum_roman);

    }

    public static int convertToArabian(String roman) {
        int arabian = 0;

        // 로마 숫자를 아라비아 숫자로 바꾼다.
        for (int i = 0; i < roman.length(); i++) {
            char ch = roman.charAt(i);
            // I, X, C로 시작하는 경우, 작은 숫자가 큰 숫자의 왼쪽에 올 수 있으므로 확인하고 값을 더해준다.
            if ((ch == 'I' || ch == 'X' || ch == 'C') && i < roman.length() - 1) {
                String temp = Character.toString(ch) + Character.toString(roman.charAt(i + 1));
                if (map.containsKey(temp)) {
                    arabian += map.get(temp);
                    i++;
                    continue;
                }
            }
            // 그 외이 경우는, 그냥 값을 더해준다.
            arabian += map.get(Character.toString(ch));
        }
        return arabian;
    }

    public static String convertToRoman(int arabian) {
        // 내림차순 리스트에 저장한다.
        List<Num> romanNums = new ArrayList<>();
        for (String key : map.keySet()) {
            romanNums.add(new Num(key, map.get(key)));
        }

        Collections.sort(romanNums);

        // 아라비아 숫자를 로마 숫자로 바꾼다.
        String roman = "";
        for (Num romanNum : romanNums) {
            // 로마 숫자로 나누었을 때, 몫이 0보다 크면 사용할 수 있다.
            int temp = arabian / romanNum.value;
            if (temp > 0) {
                for (int i = 0; i < temp; i++) {
                    roman += romanNum.mark;
                }
                arabian %= romanNum.value;
            }

            // 로마 숫자로 나누었을 때, 나머지가 0이 되면 숫자 변경을 종료한다.
            if (arabian == 0) {
                break;
            }
        }

        return roman;
    }

}