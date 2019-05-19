//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.*;

public final class B {
    private static Set b = null;
    private static List c = null;
    public static int a = 0;
    private static B d = new B();

    private B() {
        b = new HashSet();
        c = new ArrayList();
        new HashSet();
    }

    public static B a() {
        return d;
    }

    public static int a(A var0) {
        if (b.add(var0)) {
            System.out.println("[ActionHandler] AgentController: add A new action <id:" + var0.b() + "> into the global action list.");
            return var0.b();
        } else {
            System.out.println("[ActionHandler] AgentController: an old action <id:" + var0.b() + ">.");
            return var0.b;
        }
    }

    public static void b() {
        Iterator var0 = c.iterator();

        while(var0.hasNext()) {
            A var1 = (A)var0.next();
            System.out.println(var1.h());
        }

    }

    public static void c() {
        Iterator var0 = Q.a().a.iterator();

        String var4;
        while(var0.hasNext()) {
            PA var1;
            if ((var1 = (PA)var0.next()).c() != -1) {
                var1.a();
                var4 = "Static";
                var1.a = var4;
                c.add(var1);
            }
        }

        var0 = O.a().a.iterator();

        Iterator var2;
        MA var5;
        while(var0.hasNext()) {
            var2 = ((N)var0.next()).a.iterator();

            while(var2.hasNext()) {
                if ((var5 = (MA)var2.next()).c() != -1) {
                    var5.a();
                    var4 = "Static";
                    var5.a = var4;
                    c.add(var5);
                }
            }
        }

        var0 = G.a().a.iterator();

        while(var0.hasNext()) {
            var2 = ((F)var0.next()).a.iterator();

            while(var2.hasNext()) {
                if ((var5 = (MA)var2.next()).c() != -1) {
                    var5.a();
                    var4 = "Static";
                    var5.a = var4;
                    c.add(var5);
                }
            }
        }

        a = c.size();
    }

    public static A a(int var0) {
        Iterator var1 = b.iterator();

        A var2;
        do {
            if (!var1.hasNext()) {
                return null;
            }
        } while((var2 = (A)var1.next()).b() != var0);

        return var2;
    }

    public static void a(D var0) {
        System.out.println("[ActionHandler] AgentController: add A keyevent \"back\" action");
        RA var1;
        (var1 = new RA()).a();
        var1.a("");
        var1.a(-1);
        String var3 = "System";
        var1.a = var3;
        var1.c(var0.f());
        var1.a("keyevent_back\n", "");
        Integer var4 = a((A)var1);
        var0.a(var4);
    }

    public static void b(D var0) {
        System.out.println("[ActionHandler] AgentController: add A \"reset\" action");
        RA var1;
        (var1 = new RA()).a();
        var1.a(-1);
        var1.a("");
        String var3 = "System";
        var1.a = var3;
        var1.c(var0.f());
        var1.a("reset\n", "");
        Integer var4 = a((A)var1);
        var0.a(var4);
    }

    public static void c(D var0) {
        String var1 = var0.f();
        System.out.println("[ActionHandler] AgentController: add possible SYSTEM actions for the app state in the Activity: " + var1);
        RA var2;
        String var4;
        int var7;
        if (L.a().a(var1) || E.i.equals(var1)) {
            (var2 = new RA()).a();
            var2.a(-1);
            var2.a("");
            var4 = "System";
            var2.a = var4;
            var2.c(var1);
            var2.a("menu\n", "");
            var7 = a((A)var2);
            var0.a(var7);
        }

        (var2 = new RA()).a();
        var2.a(-1);
        var2.a("");
        var4 = "System";
        var2.a = var4;
        var2.c(var1);
        var2.a("back\n", "");
        var7 = a((A)var2);
        var0.a(var7);
        if (var0.h().a()) {
            RA var5;
            (var5 = new RA()).a();
            var5.a(var0.h().b());
            var5.c(var0.f());
            var4 = "Screen";
            var5.a = var4;
            var5.d("scroll");
            String var8 = "scroll(direction='down')\n";
            var5.a(var8, "");
            int var6 = a((A)var5);
            var0.a(var6);
            System.out.println("[ActionHandler] AgentController: create A *Scroll Down* Action!");
            (var5 = new RA()).a();
            var5.a(var0.h().b());
            var5.c(var0.f());
            var4 = "Screen";
            var5.a = var4;
            var5.d("scroll");
            var8 = "scroll(direction='up')\n";
            var5.a(var8, "");
            var6 = a((A)var5);
            var0.a(var6);
            System.out.println("[ActionHandler] AgentController: create A *Scroll Up* Action!");
        }

    }
}
