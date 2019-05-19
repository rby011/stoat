//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class O {
    private static O b = null;
    public List a = null;

    private O() {
        this.a = new ArrayList();
    }

    public static O a() {
        if (b == null) {
            b = new O();
        }

        return b;
    }

    public final void a(String var1) {
        if (this.a.size() != 0) {
            PrintWriter var2 = null;

            try {
                var2 = new PrintWriter(var1 + "/optionsmenuitems.txt", "UTF-8");
            } catch (FileNotFoundException var4) {
                System.out.println("can not open the file");
                var4.printStackTrace();
            } catch (UnsupportedEncodingException var5) {
                var5.printStackTrace();
            }

            var2.println("---------------------");
            var2.println("Detected OptionsMenu Actions: ");
            var2.println("---------------------");
            Iterator var6 = this.a.iterator();

            while(var6.hasNext()) {
                N var3 = (N)var6.next();
                var2.println(var3.toString());
            }

            var2.close();
        }
    }
}
