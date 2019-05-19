//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class W {
    public List a = new ArrayList();
    public List b = new ArrayList();
    private boolean c = false;
    private String d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private ArrayList n;

    public final boolean a() {
        return this.c;
    }

    public final String b() {
        return this.c ? this.d : null;
    }

    public W() {
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.n = new ArrayList();
        this.e = -1;
        this.f = -1;
        this.h = -1;
        this.i = -1;
        this.j = -1;
        this.k = -1;
        this.l = -1;
        this.m = -1;
    }

    private static String a(ArrayList var0) {
        String var1 = "";

        Integer var2;
        for(Iterator var3 = var0.iterator(); var3.hasNext(); var1 = var1 + var2.toString()) {
            var2 = (Integer)var3.next();
        }

        return var1;
    }

    public final void a(NodeList var1, boolean var2, boolean var3, boolean var4, boolean var5) {
        int var6 = var1.getLength();

        for(int var7 = 0; var7 < var6; ++var7) {
            Node var8;
            if ((var8 = var1.item(var7)).getNodeType() == 1) {
                System.out.println(var8.getNodeName() + "   [open]");
                if (var8.hasAttributes()) {
                    NamedNodeMap var9;
                    Node var10;
                    if ((var10 = (var9 = var8.getAttributes()).getNamedItem("index")) != null) {
                        this.n.add(Integer.parseInt(var10.getNodeValue()));
                    } else {
                        System.out.println("E: has not find \"index\" attribute ??");
                        System.exit(0);
                    }

                    String var11;
                    String var12;
                    String var13;
                    String var14;
                    String var15;
                    String var16;
                    String var17;
                    if (var8.hasChildNodes()) {
                        var11 = null;
                        var12 = null;
                        var13 = null;
                        var14 = null;

                        try {
                            var15 = a(this.n);
                            var16 = var9.getNamedItem("package").getNodeValue();
                            var17 = var9.getNamedItem("class").getNodeValue();
                            var11 = var9.getNamedItem("clickable").getNodeValue();
                            var12 = var9.getNamedItem("long-clickable").getNodeValue();
                            var13 = var9.getNamedItem("checkable").getNodeValue();
                            var14 = var9.getNamedItem("scrollable").getNodeValue();
                            System.out.println("non-leaf node ok!!");
                            V var25 = new V(var15, var16, var17);
                            if (var17.equals("android.widget.ListView")) {
                                var25.a(true);
                            } else {
                                var25.a(false);
                            }

                            if (Boolean.parseBoolean(var14)) {
                                System.out.println("[UIPage]: this page is scrollable.");
                                this.c = true;
                                this.d = var15;
                            }

                            this.a.add(var25);
                        } catch (NullPointerException var21) {
                            System.out.println("E: has not find the specified attribute ??");
                            System.exit(0);
                        }

                        this.a(var8.getChildNodes(), Boolean.parseBoolean(var11) || var2, Boolean.parseBoolean(var12) || var3, Boolean.parseBoolean(var13) || var4, Boolean.parseBoolean(var14) || var5);
                    } else {
                        try {
                            var11 = a(this.n);
                            var12 = var9.getNamedItem("package").getNodeValue();
                            var13 = var9.getNamedItem("class").getNodeValue();
                            var14 = var9.getNamedItem("clickable").getNodeValue();
                            var15 = var9.getNamedItem("long-clickable").getNodeValue();
                            var16 = var9.getNamedItem("checkable").getNodeValue();
                            var17 = var9.getNamedItem("scrollable").getNodeValue();
                            String var18 = var9.getNamedItem("text").getNodeValue();
                            String var19 = var9.getNamedItem("resource-id").getNodeValue();
                            String var20 = var9.getNamedItem("content-desc").getNodeValue();
                            String var23 = var9.getNamedItem("index").getNodeValue();
                            System.out.println("leaf node ok!!");
                            U var24;
                            (var24 = new U(var11, var12, var13)).a(Boolean.parseBoolean(var14), Boolean.parseBoolean(var15), Boolean.parseBoolean(var17), Boolean.parseBoolean(var16));
                            var24.b(var18);
                            var24.c(var19);
                            var24.d(var20);
                            var24.a(var23);
                            int var10001;
                            if (var13.equals("android.widget.TextView")) {
                                ++this.e;
                                var10001 = this.e;
                            } else if (var13.equals("android.widget.Button")) {
                                ++this.f;
                                System.out.println("instance = " + this.f);
                                var10001 = this.f;
                            } else if (var13.equals("android.widget.ImageView")) {
                                ++this.g;
                                var10001 = this.g;
                            } else if (var13.equals("android.widget.ToggleButton")) {
                                ++this.h;
                                var10001 = this.h;
                            } else if (var13.equals("android.widget.EditText")) {
                                ++this.i;
                                var10001 = this.i;
                            } else if (var13.equals("android.widget.CheckBox")) {
                                ++this.j;
                                var10001 = this.j;
                            } else if (var13.equals("android.widget.RadioButton")) {
                                ++this.k;
                                var10001 = this.k;
                            } else if (var13.equals("android.widget.CheckedTextView")) {
                                ++this.l;
                                var10001 = this.l;
                            } else if (var13.equals("android.widget.SeekBar")) {
                                ++this.m;
                                var10001 = this.m;
                            } else {
                                System.out.println("[UIPage] the widget type is not handled??");
                                var10001 = 0;
                            }

                            var24.a(var10001);
                            System.out.println("cis = " + var11);
                            if (var3) {
                                System.out.println("parent node'TestManager long-clickable is true, set its own'TestManager long-clickable as true");
                                var24.b(true);
                            }

                            if (var2) {
                                System.out.println("parent node'TestManager clickable is true, set its own'TestManager clickable as true");
                                var24.a(true);
                            }

                            if (var4) {
                                System.out.println("parent node'TestManager checkable is true, set its own'TestManager checkable as true");
                                var24.c(true);
                            }

                            this.b.add(var24);
                        } catch (NullPointerException var22) {
                            System.out.println("E: has not find the specified attribute ??");
                            System.exit(0);
                        }

                        this.n.remove(this.n.size() - 1);
                        System.out.println(var8.getNodeName() + "   [close]");
                    }
                }
            }
        }

    }
}
