//Matthew Paul and Isaiah Jeter
import java.util.*;
public class Recursion {
	public static int factorial (int n) {
		if (n == 1) {
			return 1;
		}
		else {
			return n * factorial(n-1);
		}
	}
	public static int nSquared (int n) {
		if (n == 1) {
			return 1;
		}
		else {
			return n*n + nSquared (n-1);
		}
	}
	public static int arr (int [] a, int begin, int end) {
		if (a.length - begin == a.length - end) {
			return a[end];
		}
		else {
			return (a[begin] + arr(a, begin + 1, end));
		}
	}
	public static String noSeven (String a) {
		if (a.length () == 0) {
			return "";
		}
		else {
			if (a.charAt(0) == '7') {
				return noSeven (a.substring(1));
			}
			else {
				return "" + a.charAt(0) + noSeven (a.substring(1));
			}
		}
	}
	public static int steps (int n) {
		if (n == 1) {
			return 1;
		}
		else if (n == 2) {
			return 2;
		}
		else if (n == 3) {
			return 4;
		}
		else {
			return (steps(n-1)+steps(n-2)+steps(n-3));
		}

	}
}