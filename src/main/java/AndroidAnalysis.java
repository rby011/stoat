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

        while (var4.hasNext()) {
            SootClass var5 = (SootClass) var4.next();
            System.out.println("app class: " + var5.getName());
            Iterator var6 = var5.getMethods().iterator();

            while (var6.hasNext()) {
                SootMethod var3 = (SootMethod) var6.next();
                System.out.println(" method: " + var3.getName());
            }
        }

        a();//메뉴 이벤트 추출해서 L.menuEventListenerNames() 에 추가
        b();//팬텀 클래스 관련 메서드 확인
        L.a().b();
        Q.a();
        Q.b();
        L.a().b(ConfigOptions.FSMOutputDir);
        Q.a();
        Q.a(ConfigOptions.FSMOutputDir);
    }

    // 앱 코드내에서 메뉴 관련된 리스너가 있어면 MenuAction Action 을 생성해서 L 의 무엇인가에 추가한다
    private static void a() {
        Iterator clsIterator = Scene.v().getApplicationClasses().iterator();

        while (clsIterator.hasNext()) {
            SootClass cls = (SootClass) clsIterator.next();
            System.out.println("app class: " + cls.toString());
            Iterator methodIterator = cls.getMethods().iterator();

            while (methodIterator.hasNext()) {
                SootMethod method = (SootMethod) methodIterator.next();
                L var10000 = L.a();//FSM의 무엇인가보다
                SootMethod methodLocal = method;
                L var7 = var10000;//눈여겨보아야하는 이벤트 리스너 목록이다
                String methodName;
                MenuAction var5;
                if ((methodName = methodLocal.getName()).equals(var7.menuEventListenerNames[0])) {
                    System.out.println("[Android Analysis] \"onCreateOptionsMenu\" --> option menu ");
                    (var5 = new MenuAction()).e("OptionsMenu"); // MenuAction 는 메뉴 관련 Action 을 의미하는구나
                    var5.f(methodName);
                    var5.c(methodLocal.getDeclaringClass().toString());
                    var7.a(var5);
                } else if (methodName.equals(var7.menuEventListenerNames[1])) {
                    System.out.println("[Android Analysis] \"onCreateContextMenu\" --> context menu ");
                    (var5 = new MenuAction()).e("ContextMenu"); // MenuAction 는 메뉴 관련 Action 을 의미하는구나
                    var5.f(methodName);
                    var5.c(methodLocal.getDeclaringClass().toString());
                    var7.a(var5);
                }
            }
        }

    }

    // 외부 데이터 구조나 행위를 유밠시키지는 않고 단지 phantom 클래스에 대한 메서드 호출 여부를 체크하는데 사용된다
    // 무엇때문에 이 메서드를 추가했을까?
    private static void b() {
        Iterator clsIterator = Scene.v().getApplicationClasses().iterator();

        while (clsIterator.hasNext()) {
            SootClass cls = (SootClass) clsIterator.next();
            System.out.println("[Action Analysis] AgentController: app class: " + cls.getName());
            Iterator methodIterator = cls.getMethods().iterator();

            while (methodIterator.hasNext()) {
                SootMethod method = (SootMethod) methodIterator.next();
                System.out.println("[Action Analysis] AgentController: method: " + method.getName());

                try {
                    Iterator var5 = method.retrieveActiveBody().getUnits().iterator();
                    while (var5.hasNext()) {
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
