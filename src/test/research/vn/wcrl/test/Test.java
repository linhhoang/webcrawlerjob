package research.vn.wcrl.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Test {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("(.*_)(.*)(_)");
		Matcher matcher = pattern.matcher("WNW1_A132DE_aaaakddddd2323d.xml");
		
		if (matcher.find())
		{
			System.out.println(matcher.group(3));
		}
	}
}
