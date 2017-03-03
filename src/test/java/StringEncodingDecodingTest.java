import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @author chanwook
 */
public class StringEncodingDecodingTest {

    final String charsetName = "utf-8";

    @Test
    public void base64() throws Exception {

        final String encoded = Base64.getEncoder().encodeToString("test01!".getBytes());

        System.out.println("enc : " + encoded);

        final byte[] decoded = Base64.getDecoder().decode(encoded);

        System.out.println("dec : " + decoded);
    }

    @Test
    public void base64t2() throws Exception {
        final String encodeSaltValue = Base64.getEncoder().encodeToString("salt-value".getBytes());
        System.out.println("encode salt : " + encodeSaltValue);

        String encodedUserId = Base64.getEncoder().encodeToString("userId".getBytes()) + encodeSaltValue;
        System.out.println("userId+salt: " + encodedUserId);

        final String removeSalt = encodedUserId.replaceAll(encodeSaltValue, "");
        System.out.println("userId-salt: " + removeSalt);

        System.out.println("decode userId+salt : " + new String(Base64.getDecoder().decode(encodedUserId)));
        System.out.println("decode userId-salt :" + new String(Base64.getDecoder().decode(removeSalt)));
    }

    @Test
    public void base64t3() throws Exception {

        String userId = "userId is not safe.";

        String step1 = Base64.getEncoder().encodeToString(userId.getBytes(charsetName));
        System.out.println("base64:" + String.valueOf(step1));

        final String step2 = URLEncoder.encode(step1, charsetName);
        System.out.println("base64+url:" + step2);

        final String step3 = URLDecoder.decode(step2, charsetName);
        System.out.println("decode+url:" + step3);

        final byte[] step4 = Base64.getDecoder().decode(step3);
        System.out.println("decode+base64:" + new String(step4, "utf-8"));
    }

}
