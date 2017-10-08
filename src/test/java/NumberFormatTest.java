import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * https://docs.oracle.com/javase/tutorial/i18n/format/decimalFormat.html
 *
 * @author chanwook
 */
public class NumberFormatTest {

    @Test
    public void doubleFormat() throws Exception {
        double number = Double.parseDouble("100000000.123");
        final DecimalFormat format = new DecimalFormat("#,###.00");

        System.out.println(String.format("%s->%s", number, format.format(number)));
    }

    @Test
    public void numberFormat() throws Exception {
        final NumberFormat format = NumberFormat.getInstance();
        double number = Double.parseDouble("100000000.123");

        System.out.println(String.format("%s->%s", number, format.format(number)));
    }
}
