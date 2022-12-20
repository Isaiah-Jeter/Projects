//Matthew Paul and Isaiah Jeter
import java.util.*;
public class RecursivePermutations {
	public static ArrayList<String> recursivePermutations (String str) {
		ArrayList<String> ret = new ArrayList<String> ();
		int n = str.length ();
		char ch = str.charAt (0);
		String copy = str.substring (1);
		if (n == 1 || n == 0) {
			 ret.add (str);
		}
		else {
		ArrayList<String> tempRet = recursivePermutations(copy);
		for (int a = 0; a < tempRet.size(); a++) {
			for (int b = 0; b <= tempRet.get(a).length(); b++) {
				ret.add (tempRet.get(a).substring(0, b) + ch + tempRet.get(a).substring(b));
			}
		}
		}
		return ret;
	}
}
