import java.io.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://docs.oracle.com/javase/tutorial/essential/regex/test_harness.html
 *
 * @author chanwook
 */
public class RegexTestConsoleApp {

    public static void main(String[] args) {

        final Console console = System.console();
        if (console == null) throw new RuntimeException("시스템 콘솔 생성 실패");

        while (true) {
            final Pattern pattern = Pattern.compile(console.readLine("%n정규표현식 입력 :"));
            final Matcher matcher = pattern.matcher(console.readLine("테스트 텍스트 입력 : "));

            boolean found = false;
            while (matcher.find()) {
                console.format("I found the text" +
                                " \"%s\" starting at " +
                                "index %d and ending at index %d.%n",
                        matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            if (!found) console.format("Not found!%n");
        }
    }

}
