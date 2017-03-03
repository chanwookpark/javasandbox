import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author chanwook
 */
public class Aes256Test {

    @Test
    public void encryption() throws Exception {

        String staticKey = "abcdefghijk12345"; //16, 24, 32 가능
        String data = "test123";

        final SecretKey key = new SecretKeySpec(staticKey.getBytes(), "AES");

        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //IV = Initial Vector 약자. 16bit
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

        final byte[] encrypted = cipher.doFinal(data.getBytes());
        final String result = new String(Base64.getEncoder().encode(encrypted));

        System.out.println("encrypt result >>> " + result);
    }
}
