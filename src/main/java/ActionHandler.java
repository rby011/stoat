//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.*;

public final class ActionHandler {
    private static Set actionSet = null;
    private static List actionList = null;
    public static int a = 0;
    private static ActionHandler instance = new ActionHandler();

    private ActionHandler() {
        actionSet = new HashSet();
        actionList = new ArrayList();
    }

    public static ActionHandler getInstance() {
        return instance;
    }

    public static int addAction(Action action) {
        if (actionSet.add(action)) {//Set returns true if the adding item is new
            System.out.println("[ActionHandler] AgentController: add Action new action <id:" + action.getActionID() + "> into the global action list.");
            return action.getActionID();
        } else {
            System.out.println("[ActionHandler] AgentController: an old action <id:" + action.getActionID() + ">.");
            return action.b;// 이해되지 않음
        }
    }

    public static void printActionList() {
        Iterator iterator = actionList.iterator();
        while (iterator.hasNext()) {
            Action action = (Action) iterator.next();
            System.out.println(action.toActionString());
        }
    }

    // Q, O, G 의 a() 를 통해 무엇인가 collection 객체를 얻어오고
    // 거기서 iterator 를 얻어오는데 그 iterator 는 모두 action source 가 "Static" 인 action 을 가지고 있음을 가정으로 한다
    // 그리고 그 action 들을 모두 action list 에 추가한다
    public static void c() {
        Iterator var0 = Q.a().a.iterator();

        String var4;
        while (var0.hasNext()) {
            PA var1;
            if ((var1 = (PA) var0.next()).getViewID() != -1) {
                var1.setActionID();
                var4 = "Static";
                var1.actionSource = var4;
                actionList.add(var1);
            }
        }

        var0 = O.a().a.iterator();

        Iterator var2;
        MA var5;
        while (var0.hasNext()) {
            var2 = ((N) var0.next()).a.iterator();

            while (var2.hasNext()) {
                if ((var5 = (MA) var2.next()).getViewID() != -1) {
                    var5.setActionID();
                    var4 = "Static";
                    var5.actionSource = var4;
                    actionList.add(var5);
                }
            }
        }

        var0 = G.a().a.iterator();

        while (var0.hasNext()) {
            var2 = ((F) var0.next()).a.iterator();

            while (var2.hasNext()) {
                if ((var5 = (MA) var2.next()).getViewID() != -1) {
                    var5.setActionID();
                    var4 = "Static";
                    var5.actionSource = var4;
                    actionList.add(var5);
                }
            }
        }

        a = actionList.size();
    }

    public static Action getAction(int actionID) {
        Iterator iterator = actionSet.iterator();
        Action action;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
        } while ((action = (Action) iterator.next()).getActionID() != actionID);
        return action;
    }

    public static void a(AppState var0) {
        System.out.println("[ActionHandler] AgentController: add Action keyevent \"back\" action");
        RA var1;// 아마도 KeyEventAction 이지 않을까 함
        (var1 = new RA()).setActionID();
        var1.a("");
        var1.setViewID(-1);
        var1.actionSource = "System";
        var1.c(var0.f());
        var1.setActionCommand("keyevent_back\n", "");
        Integer var4 = addAction(var1);
        var0.a(var4);
    }

    public static void b(AppState var0) {
        System.out.println("[ActionHandler] AgentController: add Action \"reset\" action");
        RA var1;
        (var1 = new RA()).setActionID();
        var1.setViewID(-1);
        var1.a("");
        String var3 = "System";
        var1.actionSource = var3;
        var1.c(var0.f());
        var1.setActionCommand("reset\n", "");
        Integer var4 = addAction((Action) var1);
        var0.a(var4);
    }

    public static void c(AppState var0) {
        String var1 = var0.f();
        System.out.println("[ActionHandler] AgentController: add possible SYSTEM actions for the app state in the Activity: " + var1);
        RA var2;
        String var4;
        int var7;
        if (L.a().a(var1) || ConfigOptions.i.equals(var1)) {
            (var2 = new RA()).setActionID();
            var2.setViewID(-1);
            var2.a("");
            var4 = "System";
            var2.actionSource = var4;
            var2.c(var1);
            var2.setActionCommand("menu\n", "");
            var7 = addAction((Action) var2);
            var0.a(var7);
        }

        (var2 = new RA()).setActionID();
        var2.setViewID(-1);
        var2.a("");
        var4 = "System";
        var2.actionSource = var4;
        var2.c(var1);
        var2.setActionCommand("back\n", "");
        var7 = addAction((Action) var2);
        var0.a(var7);
        if (var0.h().a()) {
            RA var5;
            (var5 = new RA()).setActionID();
            var5.a(var0.h().b());
            var5.c(var0.f());
            var4 = "Screen";
            var5.actionSource = var4;
            var5.setActionType("scroll");
            String var8 = "scroll(direction='down')\n";
            var5.setActionCommand(var8, "");
            int var6 = addAction((Action) var5);
            var0.a(var6);
            System.out.println("[ActionHandler] AgentController: create Action *Scroll Down* Action!");
            (var5 = new RA()).setActionID();
            var5.a(var0.h().b());
            var5.c(var0.f());
            var4 = "Screen";
            var5.actionSource = var4;
            var5.setActionType("scroll");
            var8 = "scroll(direction='up')\n";
            var5.setActionCommand(var8, "");
            var6 = addAction((Action) var5);
            var0.a(var6);
            System.out.println("[ActionHandler] AgentController: create Action *Scroll Up* Action!");
        }

    }
}
