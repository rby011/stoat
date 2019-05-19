//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class AppState {
    private List b = new ArrayList();
    private List actionIDList = new ArrayList();
    private Map transitionMap = new HashMap();
    public List transitionIDList = new ArrayList();
    private int transitionSize;
    private UIPage uiPage;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;

    // uiPage.menuEventListenerNames, uiPage.getTransitionInTheMap 컬렉션 내 아이템별로 menuEventListenerNames(), getTransitionInTheMap() 값을 얻어와 하나의 string 으로 만들어
    // 이를 MD5 다이제스트로 인코드하고 이를 정수로 변환해서 저장한다
    // 앱 상태를 정수로 표현하는 로직으로 보인다
    public final void a() {
        try {
            MessageDigest var1 = MessageDigest.getInstance("MD5");
            String var2 = "";

            Iterator var3;
            V var4;
            for (var3 = this.uiPage.a.iterator(); var3.hasNext(); var2 = var2 + var4.a() + var4.b()) {
                var4 = (V) var3.next();
            }

            U var6;
            for (var3 = this.uiPage.b.iterator(); var3.hasNext(); var2 = var2 + var6.e() + var6.f()) {
                var6 = (U) var3.next();
            }

            var1.update(StandardCharsets.UTF_8.encode(var2));
            this.l = String.format("%032x", new BigInteger(1, var1.digest()));
        } catch (NoSuchAlgorithmException var5) {
            System.out.println("[AppState] ConfigOptions: fail to generate md5 value!");
            var5.printStackTrace();
            System.exit(0);
        }
    }

    public final String b() {
        return this.l;
    }

    public AppState() {
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

    public final void c(String var1) {
        this.i = var1;
    }

    public final void d(String var1) {
        this.k = var1;
    }

    public final void a(UIPage var1) {
        this.uiPage = var1;
    }

    public final String z() {
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

    public final UIPage h() {
        return this.uiPage;
    }

    public final int i() {
        return this.transitionSize;
    }

    public final Transition getTransition(int transitionID) {
        return (Transition) this.transitionMap.get(transitionID);
    }

    public final Map getTransitionMap() {
        return this.transitionMap;
    }

    public final boolean k() {
        return this.k != null;
    }

    public final void addTransition(Transition transition) {
        this.transitionMap.put(transition.getTransitionID(), transition);
        this.transitionIDList.add(transition.getTransitionID());
        this.transitionSize = this.transitionIDList.size();
        System.out.println("[AppState] AgentController: now the app state " + this.h + " has " + this.transitionSize + " unique transitions.");
    }

    public final boolean l() {
        return this.j == null;
    }

    public final void addActionID(Integer actionID) {
        System.out.println("[AppState] AgentController: add an action <id: " + actionID + "> into the app state'TestManager invokable actions list");
        this.actionIDList.add(actionID);
    }

    public final void m() {
        String var1 = "";

        Integer var3;
        Action var4;
        for (Iterator var2 = this.actionIDList.iterator(); var2.hasNext(); var1 = var1 + "<" + var4.actionSource + ">    " + var3 + "@" + var4.getActionCommand()) {
            var3 = (Integer) var2.next();
            ActionHandler.getInstance();
            if ((var4 = ActionHandler.getAction(var3)) == null) {
                System.out.println("[AppState] AgentController: action id: " + var3);
                System.out.println("[AppState] ConfigOptions: action is null !! ");
                System.exit(0);
            }

            if (var4.getActionCommand() == null) {
                System.out.println("[AppState] ConfigOptions: action command is null ??");
            }
        }

        System.out.println(var1);
    }

    public final String n() {
        String var1 = "";

        Integer var3;
        Action var4;
        for (Iterator var2 = this.actionIDList.iterator(); var2.hasNext(); var1 = var1 + "    " + var3 + "@" + var4.getActionCommand()) {
            var3 = (Integer) var2.next();
            ActionHandler.getInstance();
            var4 = ActionHandler.getAction(var3);
            if (/*!MA &&*/ var4 == null) {
                throw new AssertionError();
            }

            if (var4.getActionCommand() == null) {
                System.out.println("[AppState] ConfigOptions: action command is null ??");
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

    public final Transition getTransitionInTheMap(Transition transition) {
        Iterator iterator = this.transitionIDList.iterator();

        int transitionID;
        Transition inTheMapTransition;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            transitionID = (Integer) iterator.next();
        } while (!(inTheMapTransition = (Transition) this.transitionMap.get(transitionID)).compareTransition(transition));

        System.out.println("[AppState] AgentController: this is Action duplicate transition, alias to the transition: id@ " + inTheMapTransition.getTransitionID());
        return inTheMapTransition;
    }

    public static void updateTransition(Transition transition) {
        System.out.println("[AppState] AgentController: update the transition: id@" + transition.getTransitionID());
        System.out.println("[AppState] AgentController: the original transition: " + transition.toString());
        transition.increaseExecutionCount();
        System.out.println("[AppState] AgentController: the updated transition: " + transition.toString());
    }

    public final void o() {
        System.out.println("[AppState] AgentController:  this state has " + this.transitionSize + " transitions.");
        int var3;
        if (ConfigOptions.s) {
            System.out.println("[AppState] AgentController: init the probab from fsm building");
            int var8 = 0;

            int var2;
            Transition var9;
            for (var2 = 0; var2 < this.transitionSize; ++var2) {
                var3 = (Integer) this.transitionIDList.get(var2);
                var9 = (Transition) this.transitionMap.get(var3);
                var8 += var9.getExecutionCount();
            }

            System.out.println("[AppState] AgentController: total executed times: " + var8);

            for (var2 = 0; var2 < this.transitionSize; ++var2) {
                var3 = (Integer) this.transitionIDList.get(var2);
                int var5;
                double var6 = (double) (var5 = (var9 = (Transition) this.transitionMap.get(var3)).getExecutionCount()) / (double) var8;
                var9.setG(var6);
                System.out.println("[AppState] AgentController: the execution prob: transition id: " + var3 + ", execution time: " + var5 + ", prob: " + var6);
            }

        } else {
            System.out.println("[AppState] AgentController: init the probab by using the average probability");
            double var1 = 1.0D / (double) this.transitionSize;

            for (var3 = 0; var3 < this.transitionSize; ++var3) {
                int var4 = (Integer) this.transitionIDList.get(var3);
                ((Transition) this.transitionMap.get(var4)).setG(var1);
                System.out.println("[AppState] AgentController: the execution prob: transition id: " + var4 + ", prob: " + var1);
            }

        }
    }

    public static String e(String var0) {
        int var1;
        if ((var1 = var0.lastIndexOf(47)) == -1) {
            System.out.println("[AppState] ConfigOptions: Fail to locate the '/' in this appStateName: " + var0);
            System.exit(0);
        }

        return var0.substring(var1 + 1);
    }
}
