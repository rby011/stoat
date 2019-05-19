//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class G {
    private static G b = new G();
    public List a = null;

    private G() {
        this.a = new ArrayList();
    }

    public static G a() {
        return b;
    }

    public final void a(String var1) {
        if (this.a.size() != 0) {
            PrintWriter var2 = null;

            try {
                var2 = new PrintWriter(var1 + "/contextmenuitems.txt", "UTF-8");
            } catch (IOException var4) {
                System.out.println("can not open the file");
                var4.printStackTrace();
            }

            var2.println("---------------------");
            var2.println("Detected OptionsMenu Actions: ");
            var2.println("---------------------");
            Iterator var5 = this.a.iterator();

            while(var5.hasNext()) {
                F var3 = (F)var5.next();
                var2.println(var3.toString());
            }

            var2.close();
        }
    }
}
