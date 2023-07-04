import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        char[] wordArray = word.toCharArray();
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        char[] updatedArray = new char[wordArray.length];
        int freq = 1;
        int count = 0;
        int[] diffCount = new int[50];
        int total = 0;

        for (int i = 0; i < wordArray.length; i++) {
            char a  = wordArray[i];
            for (char b: vowels) {
                if (a == b){
                    updatedArray[i] = 'v';
                    break;
                } else {
                    updatedArray[i] = 'c';
                }
            }
        }

        for (int i = 0; i < updatedArray.length - 1; i++) {
            if (updatedArray[i] == updatedArray[i + 1]) {
                freq++;
            } else {
                if (freq > 2) {
                    diffCount[count] = freq;
                    count++;
                }
                freq = 1;
            }
            if (freq > 2) {
                diffCount[count] = freq;
            }
        }

        if (count != 0) {
            for (int x : diffCount) {
                if (x != 0) {
                    int ans;
                    if (x % 2 == 0) {
                        ans = x / 2 - 1;
                    } else {
                        ans = x / 2;
                    }
                    total +=  ans;
                }
            }
        } else {
            int ans;
            if (freq % 2 == 0) {
                ans = freq / 2 - 1;
            } else {
                ans = freq / 2;
            }
            total = ans;
        }


        System.out.println(total);
    }
}