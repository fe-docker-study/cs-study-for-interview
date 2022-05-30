
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static String bruteForce(String a, String b) {
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<8; i++) {
			int na = a.charAt(i)-'0';
			int nb = b.charAt(i)-'0';
			list.add(na);
			list.add(nb);
		}
		
		for(int i=16; i>=3; i--) {
			List<Integer> temp = new ArrayList<>();
			for(int j=0; j<list.size()-1; j++) {
				int n1 = list.get(j);
				int n2 = list.get(j+1);
				temp.add((n1+n2)%10);
			}
			list.clear();
			list.addAll(temp);
		}
		
		String result  = "";
		result += Integer.toString(list.get(0));
		result += Integer.toString(list.get(1));
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a = br.readLine();
		String b = br.readLine();
		
		String result = bruteForce(a, b);
		System.out.println(result);
	}
}
