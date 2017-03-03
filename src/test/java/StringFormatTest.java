import org.junit.Test;

/**
 * @author chanwook
 */
public class StringFormatTest {

    @Test
    public void simple() throws Exception {

        System.out.println(String.format("%1$10s", "t")); //left Padding
        System.out.println(String.format("%1$-10s", "t")); //right Padding

    }
}
