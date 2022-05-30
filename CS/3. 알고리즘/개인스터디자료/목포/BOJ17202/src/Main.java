import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String num1 = br.readLine();
        String num2 = br.readLine();

        String cur = "";

        for (int i = 0; i < num1.length(); i++) {
            cur += num1.charAt(i);
            cur += num2.charAt(i);
        }

        String newNum = "";

        while (cur.length() != 2) {
            newNum = "";
            for (int i = 0; i < cur.length()-1; i++) {

                int calRes = (Character.getNumericValue(cur.charAt(i)) + Character.getNumericValue(cur.charAt(i + 1))) % 10;
                newNum += calRes;
            }
            cur = newNum;
        }

        System.out.println(newNum);
    }
}
