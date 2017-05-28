import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://docs.oracle.com/javase/tutorial/essential/regex/index.html
 *
 * @author chanwook
 */
public class RegExTest2 {

    @Test
    public void characterTest() throws Exception {

        // Simple
        assert Pattern.compile("[bcr]at").matcher("bat").matches();
        assert Pattern.compile("[bcr]at").matcher("cat").matches();
        assert Pattern.compile("[bcr]at").matcher("rat").matches();
        assert !Pattern.compile("[bcr]at").matcher("tat").matches();

        // Negation
        assert !Pattern.compile("[^bcr]at").matcher("bat").matches();
        assert Pattern.compile("[^bcr]at").matcher("tat").matches();

        // Range
        assert Pattern.compile("[a-c]").matcher("a").matches();
        assert !Pattern.compile("[a-c]").matcher("d").matches();
        assert !Pattern.compile("[^a-c]").matcher("a").matches();
        assert Pattern.compile("[^a-c]").matcher("d").matches();

        // Union
        assert Pattern.compile("[1-3[6-9]]").matcher("1").matches();
        assert Pattern.compile("[1-3[6-9]]").matcher("3").matches();
        assert !Pattern.compile("[1-3[6-9]]").matcher("0").matches();
        assert !Pattern.compile("[1-3[6-9]]").matcher("5").matches();
        assert Pattern.compile("[1-3[6-9]]").matcher("6").matches();
        assert Pattern.compile("[1-3[6-9]]").matcher("9").matches();
        assert !Pattern.compile("[1-3[6-9]]").matcher("10").matches();
        assert !Pattern.compile("[1-3[6-9]]").matcher("11").matches();

        // Intersection
        assert Pattern.compile("[0-9&&[345]]").matcher("3").matches();
        assert Pattern.compile("[0-9&&[345]]").matcher("5").matches();
        assert !Pattern.compile("[0-9&&[345]]").matcher("2").matches();
        assert !Pattern.compile("[0-9&&[345]]").matcher("6").matches();

        // Subtraction
        assert !Pattern.compile("[0-9&&[^345]]").matcher("3").matches();
        assert !Pattern.compile("[0-9&&[^345]]").matcher("5").matches();
        assert Pattern.compile("[0-9&&[^345]]").matcher("2").matches();
        assert Pattern.compile("[0-9&&[^345]]").matcher("6").matches();
    }

    @Test
    public void predefinedCharacters() throws Exception {
        assert Pattern.compile(".").matcher("@").matches();
        assert Pattern.compile(".").matcher("1").matches();
        assert Pattern.compile(".").matcher("a").matches();

        assert Pattern.compile("\\d").matcher("1").matches();
        assert !Pattern.compile("\\d").matcher("d").matches();

        assert !Pattern.compile("\\D").matcher("1").matches();
        assert Pattern.compile("\\D").matcher("d").matches();

        assert !Pattern.compile("\\s").matcher("1").matches();
        assert !Pattern.compile("\\s").matcher("d").matches();
        assert Pattern.compile("\\s").matcher(" ").matches();

        assert Pattern.compile("\\S").matcher("1").matches();
        assert Pattern.compile("\\S").matcher("d").matches();
        assert !Pattern.compile("\\S").matcher(" ").matches();

        assert Pattern.compile("\\w").matcher("1").matches();
        assert Pattern.compile("\\w").matcher("d").matches();
        assert !Pattern.compile("\\w").matcher(" ").matches();

        assert !Pattern.compile("\\W").matcher("1").matches();
        assert !Pattern.compile("\\W").matcher("d").matches();
        assert Pattern.compile("\\W").matcher(" ").matches();
    }

    @Test
    public void quantifiers() throws Exception {
        assert Pattern.compile("a?").matcher("").matches();
        assert Pattern.compile("a*").matcher("").matches();
        assert !Pattern.compile("a+").matcher("").matches();

        final Matcher m1 = Pattern.compile("a*").matcher("a");
        assert m1.find();
        assert "a".equals(m1.group());
        assert m1.find();
        assert "".equals(m1.group());

        final Matcher m2 = Pattern.compile("a?").matcher("a");
        assert m2.find();
        assert "a".equals(m2.group());
        assert m2.find();
        assert "".equals(m2.group());

        final Matcher m3 = Pattern.compile("a+").matcher("a");
        assert m3.find();
        assert "a".equals(m3.group());
        assert !m3.find();

        assert Pattern.compile("a{3}").matcher("aaa").matches();
        assert !Pattern.compile("a{3}").matcher("aa").matches();
        assert !Pattern.compile("a{3}").matcher("aaaa").matches();

        assert Pattern.compile("a{3,}").matcher("aaaaaa").matches(); // a=6
        assert Pattern.compile("a{3,6}").matcher("aaaaa").matches(); // a=5
        assert Pattern.compile("a{3,6}").matcher("aaaaaa").matches(); // a=6
        assert !Pattern.compile("a{3,6}").matcher("aaaaaaa").matches(); // a=7

        assert Pattern.compile("(dog){3}").matcher("dogdogdog").matches();//dog=3
        assert !Pattern.compile("(dog){3}").matcher("dogdogdogdog").matches();//dog=2
        assert !Pattern.compile("(dog){3}").matcher("dogdogdogdogdog").matches();//dog=4

        assert Pattern.compile("[abc]{3}").matcher("abc").matches();
        assert !Pattern.compile("[abc]{3}").matcher("ac").matches();
        assert !Pattern.compile("[abc]{3}").matcher("abca").matches();

        // greedy
        final Matcher greedy = Pattern.compile(".*foo").matcher("xxxfooxfoo");
        assert greedy.find();
        System.out.println(greedy.group());
        assert "xxxfooxfoo".equals(greedy.group());
        assert !greedy.find();

        // reluctant
        final Matcher reluctant = Pattern.compile(".*?foo").matcher("xxxfooxfoo");
        assert reluctant.find();
        assert "xxxfoo".equals(reluctant.group());
        assert reluctant.find();
        assert "xfoo".equals(reluctant.group());

        // possessive
        final Matcher possessive = Pattern.compile(".*+foo").matcher("xxxfooxfoo");
        assert !possessive.find();
    }

    @Test
    public void capturingGroups() throws Exception {
        final Pattern pattern = Pattern.compile("([0-9]{2})/([0-9]{2})/([0-9]{4})");
        final Matcher matcher = pattern.matcher("01/05/2019");

        assert matcher.matches();

        assert "01/05/2019".equals(matcher.group(0));
        assert "01".equals(matcher.group(1));
        assert "05".equals(matcher.group(2));
        assert "2019".equals(matcher.group(3));
    }

    @Test
    public void boundaryMatchers() throws Exception {
        assert Pattern.compile("^dog$").matcher("dog").matches();
        assert !Pattern.compile("^dog$").matcher("  dog").matches();
        assert !Pattern.compile("^dog$").matcher("dog  ").matches();

        assert Pattern.compile("\\s*dog$").matcher("    dog").matches();
        assert Pattern.compile("^dog\\w*").matcher("dogaskjflkasjflkalskjf").matches();

        assert Pattern.compile("\\bdog\\b").matcher("The dog plays in the yard.").matches();
    }
}
