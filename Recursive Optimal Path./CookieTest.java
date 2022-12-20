import java.util.*;

public class CookieTest {
	public static void main (String [] args) {
		int [][] ok = {{1, 2, -1}, {-1, 5, 8}, {7,-1, 10}};
		CookieMonster use = new CookieMonster (ok);
		System.out.println (use.maxCallStackDepth());
		System.out.println (use.getMostCookies());
	}
}
