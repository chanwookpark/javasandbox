import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author chanwook
 */
public class RegexTest {

    @Test
    public void test1() throws Exception {

        String v1 = "APP/11 bbb";

        final boolean r = Pattern.compile("APP/\\d*\\s").matcher(v1).find();

        assert r;
    }
}
