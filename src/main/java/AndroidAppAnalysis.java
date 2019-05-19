//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import soot.*;
import soot.jimple.InvokeExpr;
import soot.options.Options;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AndroidAppAnalysis {
    private static final String[] a = new String[]{"onCreate", "onStart", "onResume", "onRestart", "onPause", "onStop", "onDestroy"};
    private static final String[] b = new String[]{"onCreate", "onStart", "onResume", "onRestart", "onPause", "onStop", "onDestroy"};
    private static String c = null;
    private String d = null;
    private static String e = null;
    private List f = new ArrayList();
    private List g = new ArrayList();

    public AndroidAppAnalysis() {
        new ArrayList();
    }

    public static void main(String[] var0) {
        System.out.println("[Android Analysis] This is the main entry of MainAndroidAnalysis");
        (new AndroidAppAnalysis()).a(var0);
    }

    public final void a(String[] var1) {
        System.out.println("[Android Analysis] AndroidAppAnalysis.run");
        System.out.println("[Android Analysis] command line options: ");
        String[] var2 = var1;
        int var3 = var1.length;

        int var4;
        for(var4 = 0; var4 < var3; ++var4) {
            String var5 = var2[var4];
            System.out.print(var5 + " ");
        }

        System.out.println("\n");
        if (var1.length > 0 && !var1[0].equals("--help") && !var1[0].equals("-H")) {
            if (var1[0].equals("--list") && var1.length == 2) {
                SootClass var14 = Scene.v().loadClassAndSupport(var1[1]);
                Scene.v().loadNecessaryClasses();
                a(var14);
            } else {
                if (var1[0].equals("--apk-name")) {
                    c = var1[1];
                }

                this.d = E.AndroidLIBDir;
                this.a(c);
                String var9 = this.d;
                var9 = var9 + ":" + c + "/bin/classes";
                var9 = var9 + ":" + c + "/libs";
                String[] var10000 = new String[]{"-W", "-keep-line-number", "-allow-phantom-refs", "-F", "J", "-PA", "cg", "verbose:true", "-cp", var9};
                String[] var13 = new String[var1.length - 2];

                for(int var15 = 0; var15 < var1.length - 2; ++var15) {
                    var13[var15] = var1[var15 + 2];
                }

                System.out.println("[Android Analysis]: args passing to soot ");
                String[] var16 = var13;
                int var7 = var13.length;

                for(int var10 = 0; var10 < var7; ++var10) {
                    String var11 = var16[var10];
                    System.out.print(var11 + " ");
                }

                System.out.println("\n");
                Options.v().parse(var13);
                PackManager.v().getPack("wjtp").add(new Transform("wjtp.ActionAnalysis", new C()));
                ArrayList var17 = new ArrayList();
                Iterator var8 = this.f.iterator();

                String var6;
                SootClass var12;
                while(var8.hasNext()) {
                    var9 = (String)var8.next();
                    (var12 = Scene.v().forceResolve(var9, 3)).setApplicationClass();
                    Scene.v().loadNecessaryClasses();
                    var2 = a;

                    for(var4 = 0; var4 < 7; ++var4) {
                        var6 = var2[var4];
                        if (var12.declaresMethodByName(var6)) {
                            var17.add(var12.getMethodByName(var6));
                            System.out.println("[Android Analysis] an entry point (\"acticity callback\"): " + var6);
                        }
                    }
                }

                var8 = this.g.iterator();

                while(var8.hasNext()) {
                    var9 = (String)var8.next();
                    (var12 = Scene.v().forceResolve(var9, 3)).setApplicationClass();
                    Scene.v().loadNecessaryClasses();
                    var2 = b;

                    for(var4 = 0; var4 < 7; ++var4) {
                        var6 = var2[var4];
                        if (var12.declaresMethodByName(var6)) {
                            var17.add(var12.getMethodByName(var6));
                            System.out.println("[Android Analysis] an entry point (\"service callback\"): " + var6);
                        }
                    }
                }

                Scene.v().setEntryPoints(var17);
                PackManager.v().runPacks();
                PackManager.v().writeOutput();
                System.out.println("[Android Analysis] Analysis Finished...");
            }
        } else {
            System.out.println("Usage: MainAndroidAnalysis <main class to be analyzed> [options]");
            System.out.println("Required libraries:  soot, android SDK");
        }
    }

    private void a(String var1) {
        try {
            Document var8;
            String var2 = (var8 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(var1 + "/AndroidManifest.xml"))).getElementsByTagName("manifest").item(0).getAttributes().getNamedItem("package").getNodeValue();
            e = new String(var2);
            System.out.println("[Android Analysis] app package name: " + e);
            NodeList var3 = var8.getElementsByTagName("activity");
            System.out.println("[Android Analysis] activities count: " + var3.getLength());

            for(int var4 = 0; var4 < var3.getLength(); ++var4) {
                String var6;
                if ((var6 = var3.item(var4).getAttributes().getNamedItem("android:name").getNodeValue()).startsWith(".")) {
                    var6 = var2 + var6;
                    System.out.println("[Android Analysis] [detected activity]: " + var6);
                } else if (!var6.contains(".")) {
                    var6 = var2 + "." + var6;
                    System.out.println("[Android Analysis] [detected activity]: " + var6);
                } else {
                    System.out.println("[Android Analysis] [detected activity]: " + var6);
                }

                this.f.add(var6);
            }

            NodeList var9 = var8.getElementsByTagName("service");
            System.out.println("[Android Analysis] services count: " + var9.getLength());

            for(int var5 = 0; var5 < var9.getLength(); ++var5) {
                if ((var1 = var9.item(var5).getAttributes().getNamedItem("android:name").getNodeValue()).startsWith(".")) {
                    var1 = var2 + var1;
                    System.out.println("[Android Analysis] [detected service]: " + var1);
                } else if (!var1.contains(".")) {
                    var1 = var2 + "." + var1;
                    System.out.println("[Android Analysis] [detected service]: " + var1);
                } else {
                    System.out.println("[Android Analysis] [detected service]: " + var1);
                }

                this.g.add(var1);
            }

        } catch (Exception var7) {
            System.out.println("[Android Analysis] Error in scanning AndroidManifest.xml: " + var7);
        }
    }

    private static void a(SootClass var0) {
        System.out.println(var0.toString());
        Iterator var3 = var0.getMethods().iterator();

        while(true) {
            SootMethod var1;
            do {
                if (!var3.hasNext()) {
                    return;
                }
            } while(!(var1 = (SootMethod)var3.next()).isConcrete());

            System.out.println("\t" + var1.toString());
            Iterator var4 = var1.retrieveActiveBody().getUseBoxes().iterator();

            while(var4.hasNext()) {
                Value var2;
                if ((var2 = ((ValueBox)var4.next()).getValue()) instanceof InvokeExpr) {
                    InvokeExpr var5 = (InvokeExpr)var2;
                    System.out.println("\t\t" + var5.getMethod().toString());
                }
            }
        }
    }
}
