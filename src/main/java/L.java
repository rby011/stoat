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

public final class L {
    private static L b = new L();
    private List c = null;
    public final String[] a = new String[]{"onCreateOptionsMenu", "onCreateContextMenu"};

    private L() {
        this.c = new ArrayList();
    }

    public static L a() {
        return b;
    }

    public final void a(KA var1) {
        this.c.add(var1);
    }

    public final boolean a(String var1) {
        if (this.c.size() == 0) {
            return false;
        } else {
            Iterator var2 = this.c.iterator();

            KA var3;
            do {
                if (!var2.hasNext()) {
                    return false;
                }
            } while(!(var3 = (KA)var2.next()).i().equals(this.a[0]) || !var3.e().equals(var1));

            return true;
        }
    }

    public final void b() {
        if (this.c.size() != 0) {
            System.out.println("---------------------");
            System.out.println("Detected Inherited Actions: ");
            System.out.println("ActionID\tActionName \t\tOverrideMethodLine\t\tOverrideMethod\t\tActivityName");
            System.out.println("---------------------");
            Iterator var1 = this.c.iterator();

            while(var1.hasNext()) {
                KA var2 = (KA)var1.next();
                System.out.println(var2.toString());
            }

        }
    }

    public final void b(String var1) {
        if (this.c.size() != 0) {
            PrintWriter var2 = null;

            try {
                var2 = new PrintWriter(var1 + "/inheritedActions.txt", "UTF-8");
            } catch (FileNotFoundException var5) {
                System.out.println("can not open the file");
                var5.printStackTrace();
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
            }

            var2.println("---------------------");
            var2.println("Detected Inherited Actions: ");
            var2.println("ActionID\tActionName \t\tOverrideMethodLine\t\tOverrideMethod\t\tActivityName");
            var2.println("---------------------");
            Iterator var3 = this.c.iterator();

            while(var3.hasNext()) {
                KA var4 = (KA)var3.next();
                var2.println(var4.toString());
            }

            O.a().a(var1);
            G.a().a(var1);
            var2.close();
        }
    }
}
