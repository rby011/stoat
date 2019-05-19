//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class D {
    private List b = new ArrayList();
    private List actionList = new ArrayList();
    private Map d = new HashMap();
    public List transitionList = new ArrayList();
    private int e;
    private W f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;

    public final void a() {
        try {
            MessageDigest var1 = MessageDigest.getInstance("MD5");
            String var2 = "";

            Iterator var3;
            V var4;
            for (var3 = this.f.a.iterator(); var3.hasNext(); var2 = var2 + var4.a() + var4.b()) {
                var4 = (V) var3.next();
            }

            U var6;
            for (var3 = this.f.b.iterator(); var3.hasNext(); var2 = var2 + var6.e() + var6.f()) {
                var6 = (U) var3.next();
            }

            var1.update(StandardCharsets.UTF_8.encode(var2));
            this.l = String.format("%032x", new BigInteger(1, var1.digest()));
        } catch (NoSuchAlgorithmException var5) {
            System.out.println("[AppState] E: fail to generate md5 value!");
            var5.printStackTrace();
            System.exit(0);
        }
    }

    public final String b() {
        return this.l;
    }

    public D() {
    }

    public final void a(String var1) {
        this.j = var1;
    }

    public final void b(String var1) {
        this.g = var1;
        String var2 = this.g;
        int var4 = var2.lastIndexOf(47);
        int var3 = (var2 = this.g.substring(var4 + 1)).indexOf(46);
        var2 = var2.substring(0, var3);
        System.out.println(var2);
        this.h = var2;
    }

    public final void updateTransition(String var1) {
        this.i = var1;
    }

    public final void d(String var1) {
        this.k = var1;
    }

    public final void a(W var1) {
        this.f = var1;
    }

    public final String updateTransition() {
        return this.j;
    }

    public final String d() {
        return this.g;
    }

    public final String e() {
        return this.h;
    }

    public final String f() {
        return this.i;
    }

    public final String g() {
        return this.k;
    }

    public final W h() {
        return this.f;
    }

    public final int i() {
        return this.e;
    }

    public final Transition a(int var1) {
        return (Transition) this.d.get(var1);
    }

    public final Map j() {
        return this.d;
    }

    public final boolean k() {
        return this.k != null;
    }

    public final void a(Transition var1) {
        this.d.put(var1.getTransitionID(), var1);
        this.transitionList.add(var1.getTransitionID());
        this.e = this.transitionList.size();
        System.out.println("[AppState] AgentController: now the app state " + this.h + " has " + this.e + " unique transitions.");
    }

    public final boolean l() {
        return this.j == null;
    }

    public final void a(Integer actionID) {
        System.out.println("[AppState] AgentController: add an action <id: " + actionID + "> into the app state'TestManager invokable actions list");
        this.actionList.add(actionID);
    }

    public final void m() {
        String var1 = "";

        Integer var3;
        A var4;
        for (Iterator var2 = this.actionList.iterator(); var2.hasNext(); var1 = var1 + "<" + var4.a + ">    " + var3 + "@" + var4.g()) {
            var3 = (Integer) var2.next();
            B.a();
            if ((var4 = B.a(var3)) == null) {
                System.out.println("[AppState] AgentController: action id: " + var3);
                System.out.println("[AppState] E: action is null !! ");
                System.exit(0);
            }

            if (var4.g() == null) {
                System.out.println("[AppState] E: action command is null ??");
            }
        }

        System.out.println(var1);
    }

    public final String n() {
        String var1 = "";

        Integer var3;
        A var4;
        for (Iterator var2 = this.actionList.iterator(); var2.hasNext(); var1 = var1 + "    " + var3 + "@" + var4.g()) {
            var3 = (Integer) var2.next();
            B.a();
            var4 = B.a(var3);
            if (/*!MA &&*/ var4 == null) {
                throw new AssertionError();
            }

            if (var4.g() == null) {
                System.out.println("[AppState] E: action command is null ??");
            }
        }

        return var1;
    }

    public String toString() {
        String var1 = this.i + "\n";

        Y var3;
        for (Iterator var2 = this.b.iterator(); var2.hasNext(); var1 = var1 + var3.toString()) {
            var3 = (Y) var2.next();
        }

        return var1;
    }

    public final Transition b(Transition orginal) {
        Iterator iterator = this.transitionList.iterator();

        int transitionID;
        Transition replace;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            transitionID = (Integer) iterator.next();
        } while (!(replace = (Transition) this.d.get(transitionID)).compareTransition(orginal)); // 그래프 형태인듯 하다

        System.out.println("[AppState] AgentController: this is A duplicate transition, alias to the transition: id@ " + replace.getTransitionID());
        return replace;
    }

    public static void updateTransition(Transition transition) {
        System.out.println("[AppState] AgentController: update the transition: id@" + transition.getTransitionID());
        System.out.println("[AppState] AgentController: the original transition: " + transition.toString());
        transition.increaseExecutionCount();
        System.out.println("[AppState] AgentController: the updated transition: " + transition.toString());
    }

    public final void o() {
        System.out.println("[AppState] AgentController:  this state has " + this.e + " transitions.");
        int var3;
        if (E.s) {
            System.out.println("[AppState] AgentController: init the probab from fsm building");
            int var8 = 0;

            int var2;
            Transition var9;
            for (var2 = 0; var2 < this.e; ++var2) {
                var3 = (Integer) this.transitionList.get(var2);
                var9 = (Transition) this.d.get(var3);
                var8 += var9.getExecutionCount();
            }

            System.out.println("[AppState] AgentController: total executed times: " + var8);

            for (var2 = 0; var2 < this.e; ++var2) {
                var3 = (Integer) this.transitionList.get(var2);
                int var5;
                double var6 = (double) (var5 = (var9 = (Transition) this.d.get(var3)).getExecutionCount()) / (double) var8;
                var9.setG(var6);
                System.out.println("[AppState] AgentController: the execution prob: transition id: " + var3 + ", execution time: " + var5 + ", prob: " + var6);
            }

        } else {
            System.out.println("[AppState] AgentController: init the probab by using the average probability");
            double var1 = 1.0D / (double) this.e;

            for (var3 = 0; var3 < this.e; ++var3) {
                int var4 = (Integer) this.transitionList.get(var3);
                ((Transition) this.d.get(var4)).setG(var1);
                System.out.println("[AppState] AgentController: the execution prob: transition id: " + var4 + ", prob: " + var1);
            }

        }
    }

    public static String e(String var0) {
        int var1;
        if ((var1 = var0.lastIndexOf(47)) == -1) {
            System.out.println("[AppState] E: Fail to locate the '/' in this appStateName: " + var0);
            System.exit(0);
        }

        return var0.substring(var1 + 1);
    }
}
