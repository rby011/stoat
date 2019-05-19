//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootMethod;

import java.util.Iterator;
import java.util.Map;

public final class AndroidAnalysis extends SceneTransformer {
    public AndroidAnalysis() {
    }

    protected final void internalTransform(String var1, Map var2) {
        System.out.println("[Android Analysis] SimpleAnalysis.internalTransform");
        Iterator var4 = Scene.v().getApplicationClasses().iterator();

        while(var4.hasNext()) {
            SootClass var5 = (SootClass)var4.next();
            System.out.println("app class: " + var5.getName());
            Iterator var6 = var5.getMethods().iterator();

            while(var6.hasNext()) {
                SootMethod var3 = (SootMethod)var6.next();
                System.out.println(" method: " + var3.getName());
            }
        }

        a();
        b();
        L.a().b();
        Q.a();
        Q.b();
        L.a().b(ConfigOptions.FSMOutputDir);
        Q.a();
        Q.a(ConfigOptions.FSMOutputDir);
    }

    private static void a() {
        Iterator var0 = Scene.v().getApplicationClasses().iterator();

        while(var0.hasNext()) {
            SootClass var1 = (SootClass)var0.next();
            System.out.println("app class: " + var1.toString());
            Iterator var6 = var1.getMethods().iterator();

            while(var6.hasNext()) {
                SootMethod var2 = (SootMethod)var6.next();
                L var10000 = L.a();
                SootMethod var3 = var2;
                L var7 = var10000;
                String var4;
                KA var5;
                if ((var4 = var3.getName()).equals(var7.a[0])) {
                    System.out.println("[Android Analysis] \"onCreateOptionsMenu\" --> option menu ");
                    (var5 = new KA()).e("OptionsMenu");
                    var5.f(var4);
                    var5.c(var3.getDeclaringClass().toString());
                    var7.a(var5);
                } else if (var4.equals(var7.a[1])) {
                    System.out.println("[Android Analysis] \"onCreateContextMenu\" --> context menu ");
                    (var5 = new KA()).e("ContextMenu");
                    var5.f(var4);
                    var5.c(var3.getDeclaringClass().toString());
                    var7.a(var5);
                }
            }
        }

    }

    private static void b() {
        Iterator var0 = Scene.v().getApplicationClasses().iterator();

        while(var0.hasNext()) {
            SootClass var1 = (SootClass)var0.next();
            System.out.println("[Action Analysis] AgentController: app class: " + var1.getName());
            Iterator var4 = var1.getMethods().iterator();

            while(var4.hasNext()) {
                SootMethod var2 = (SootMethod)var4.next();
                System.out.println("[Action Analysis] AgentController: method: " + var2.getName());

                try {
                    Iterator var5 = var2.retrieveActiveBody().getUnits().iterator();

                    while(var5.hasNext()) {
                        var5.next();
                    }
                } catch (RuntimeException var3) {
                    System.out.println("[Action Analysis] AgentController: catch Action runtime exception when retrieve active body. continue...");
                    var3.printStackTrace();
                }
            }
        }

    }
}
