//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Q {
    private static Q b;
    public List a = null;

    private Q() {
        String[] var10000 = new String[]{"setOnClickListener", "setOnLongClickListener", "setOnCreateContextMenuListener", "setOnDragListener", "setOnKeyListener", "setOnTouchListener"};
        var10000 = new String[]{"registerForContextMenu"};
        this.a = new ArrayList();
    }

    public static Q a() {
        return b;
    }

    public static void b() {
        if (b.a.size() != 0) {
            System.out.println("---------------------");
            System.out.println("Detected Registered Actions: ");
            System.out.println("ActionID\tActionType \t\tListnerLine\t\tCallingMethod\tactivityName");
            System.out.println("---------------------");
            Iterator var0 = b.a.iterator();

            while(var0.hasNext()) {
                PA var1 = (PA)var0.next();
                System.out.println(var1.toString());
            }

        }
    }

    public static void a(String var0) {
        if (b.a.size() != 0) {
            PrintWriter var1 = null;

            try {
                var1 = new PrintWriter(var0 + "/registeredActions.txt", "UTF-8");
            } catch (IOException var3) {
                System.out.println("can not open the file");
                var3.printStackTrace();
            }

            var1.println("---------------------");
            var1.println("Detected Registered Actions: ");
            var1.println("ActionID\tActionType \t\tListnerLine\t\tCallingMethod\tactivityName");
            var1.println("---------------------");
            Iterator var4 = b.a.iterator();

            while(var4.hasNext()) {
                PA var2 = (PA)var4.next();
                var1.println(var2.toString());
            }

            var1.close();
        }
    }

    static {
        Q.class.desiredAssertionStatus();
        b = new Q();
    }
}
