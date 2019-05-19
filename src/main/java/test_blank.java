//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class test_blank {
    public test_blank() {
    }

    public static void main(String[] var0) {
        for(int var4 = 0; var4 < 1000; ++var4) {
            String var1 = "3435;||||;sdg34546;|456567" + var4;

            try {
                MessageDigest var2;
                (var2 = MessageDigest.getInstance("MD5")).update(StandardCharsets.UTF_8.encode(var1));
                var1 = String.format("%032x", new BigInteger(1, var2.digest()));
                System.out.println(var1);
            } catch (NoSuchAlgorithmException var3) {
                var3.printStackTrace();
            }
        }

    }
}
