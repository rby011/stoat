//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class A {
    private static int c = 1;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    public String a;
    public int b;

    public A() {
    }

    public final void a() {
        this.d = c++;
    }

    public final void a(int var1) {
        this.e = -1;
    }

    public final void a(String var1) {
        this.f = var1;
    }

    public final void b(String var1) {
        this.g = var1;
    }

    public final void c(String var1) {
        this.j = var1;
    }

    public final void d(String var1) {
        this.h = var1;
    }

    public final void a(String var1, String var2) {
        String var3;
        if ((var3 = this.g) != null) {
            var3 = var3.replaceAll("\n", "").replaceAll(";", "");
            var3 = var2 + "@\"" + var3 + "\"";
        } else {
            var3 = var2 + "@null";
        }

        if (var2.equals("")) {
            this.i = var1;
        } else {
            this.i = var1.substring(0, var1.length() - 1) + ":" + var3 + "\n";
        }
    }

    private String i() {
        int var1;
        return (var1 = this.i.lastIndexOf(58)) == -1 ? this.i : this.i.substring(0, var1);
    }

    public final int b() {
        return this.d;
    }

    public final int c() {
        return this.e;
    }

    public final String d() {
        return this.g;
    }

    public final String e() {
        return this.j;
    }

    public final String f() {
        return this.h;
    }

    public final String g() {
        return this.i;
    }

    public final String h() {
        String var1 = "\t";
        var1 = var1 + "action_id: " + this.d + "; view_id: " + this.e;
        if (this.g != null) {
            var1 = var1 + "; view_text: " + this.g;
        } else {
            var1 = var1 + "; view_text: [ ]";
        }

        if (this.h != null) {
            var1 = var1 + "; action_type: " + this.h;
        } else {
            var1 = var1 + "; action_type: [ ]";
        }

        if (this.i != null) {
            var1 = var1 + "; action_cmd: " + this.i;
        } else {
            var1 = var1 + "; action_cmd: [ ]";
        }

        if (this.a != null) {
            var1 = var1 + "; action_source: " + this.a;
        } else {
            var1 = var1 + "; action_source: [ ]";
        }

        return var1;
    }

    public int hashCode() {
        String var1 = this.i;
        String var2 = var1;
        int var3 = 0;

        for(int var4 = 0; var4 < var2.length(); ++var4) {
            var3 += var2.charAt(var4);
        }

        return var3 + this.e;
    }

    public boolean equals(Object var1) {
        System.out.println("equal");
        if (var1 instanceof A) {
            String var2 = this.i();
            A var5;
            String var3 = (var5 = (A)var1).i();
            System.out.println("[Action] AgentController: cmd: " + var2 + ", view cis: " + this.f + ", activity name: " + this.j);
            System.out.println("equal:" + var2 + "|" + var3 + "|");
            System.out.println("cmd2'TestManager view cis: " + var5.f + " activity name" + var5.j);
            if (var2.equals(var3) && this.f.equals(var5.f) && this.j.equals(var5.j)) {
                this.b = var5.d;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
