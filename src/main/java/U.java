//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public final class U {
    private String a;
    private String b;
    private String c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private String h;
    private String i;
    private String j;
    private String k;
    private int l;

    public U(String var1, String var2, String var3) {
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    public final void a(boolean var1, boolean var2, boolean var3, boolean var4) {
        this.d = var1;
        this.e = var2;
        this.f = var3;
        this.g = var4;
    }

    public final void a(int var1) {
        this.l = var1;
    }

    public final int a() {
        return this.l;
    }

    public final boolean b() {
        return this.h.contains("\n");
    }

    public final String c() {
        int var1 = this.h.indexOf("\n");
        return this.h.substring(0, var1);
    }

    public final void a(boolean var1) {
        this.d = true;
    }

    public final void b(boolean var1) {
        this.e = true;
    }

    public final void c(boolean var1) {
        this.g = true;
    }

    public final void a(String var1) {
        this.k = var1;
    }

    public final void b(String var1) {
        this.h = var1;
    }

    public final String d() {
        return this.h;
    }

    public final String e() {
        return this.a;
    }

    public final String f() {
        return this.c;
    }

    public final void c(String var1) {
        this.i = var1;
    }

    public final String g() {
        return this.i;
    }

    public final void d(String var1) {
        this.j = var1;
    }

    public final String h() {
        return this.j;
    }

    public final boolean i() {
        return this.d;
    }

    public final boolean j() {
        return this.e;
    }

    public final boolean k() {
        return this.g;
    }

    public final String toString() {
        return this.a + " " + this.b + " " + this.c + " " + this.d + " " + this.e + " " + this.f + " " + this.g + " " + this.h + " " + this.k;
    }
}
