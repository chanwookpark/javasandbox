import org.junit.Test;

import java.util.regex.Pattern;

/**
 * @author chanwook
 */
public class RegexTest {

    @Test
    public void test1() throws Exception {
        assert Pattern.compile("APP/\\d*\\s").matcher("APP/11 bbb").find();
    }
}
