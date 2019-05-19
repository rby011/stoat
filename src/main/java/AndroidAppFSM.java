//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

public class AndroidAppFSM {
    private Map c;
    private List d;
    private int e;
    private Map f;
    private String g;
    public int stateCount;
    public int transitionCount;
    private static AndroidAppFSM h = null;
    private Set i = null;

    public final Map a() {
        return this.c;
    }

    public static String b() {
        return E.FSMFilePath + "/ui/S_1.xml";
    }

    private AndroidAppFSM() {
        this.c = new HashMap();
        this.d = new ArrayList();
        this.f = new HashMap();
        this.g = null;
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
    }

    public static AndroidAppFSM c() {
        if (h == null) {
            h = new AndroidAppFSM();
        }

        return h;
    }

    public AndroidAppFSM(byte var1) {
        System.out.println("[AndroidAppFSM] AgentController: create the app FSM for MCMC sampling.");
        this.c = new HashMap();
        this.i = new HashSet();
        this.d = new ArrayList();
    }

    public final Set d() {
        return this.i;
    }

    private D g() {
        int var1 = this.e - 2;
        if (/*J || decompiled 된 원문장*/var1 >= 0 && var1 < this.d.size()) {
            String var3 = (String)this.d.get(var1);
            D var4;
            if (!(var4 = this.a(var3)).l()) {
                var3 = var4.updateTransition();
                var4 = this.a(var3);
            }

            return var4;
        } else {
            throw new AssertionError();
        }
    }

    private String h() {
        D var1 = this.g();
        System.out.println("[AndroidAppFSM] AgentController: the last state is: " + var1.d());
        return var1.d();
    }

    public final List restoreFSMFromFile() {
        return this.d;
    }

    public final D a(String var1) {
        return (D)this.c.get(var1);
    }

    public final void a(String var1, int var2) {
        String var3 = D.e(var1);
        System.out.println("[AndroidAppFSM] AgentController: building the app FSM, the received app state name: " + var3 + ", the executed action id: " + var2);
        D var5 = this.a(var1);
        D var4;
        if ((var4 = this.g()).k()) {
            if (var4.g().equals("RESET_APP_STATE") && !var5.k()) {
                System.out.println("[AndroidAppFSM] AgentController: the last state is A *RESET* app state, the current state is the entry app state ");
                return;
            }

            if (var4.g().equals("EMPTY_APP_STATE") && var5.k()) {
                System.out.println("[AndroidAppFSM] AgentController: the last state is an *EMPTY* app state, the current state is A *RESET* app state");
                return;
            }
        }

        Transition var6;
        if (var5.l()) {
            System.out.println("[AndroidAppFSM] AgentController: this received app state: " + var3 + " is A *new* state, the transition is A *new* transition. ");
            var6 = new Transition(this.h(), this.g, var2);
            var4.a(var6);
            System.out.println("[AndroidAppFSM] AgentController: the new transition is : ");
            System.out.println(var6.toString());
        } else {
            System.out.println("[AndroidAppFSM] AgentController: this received app state: " + var3 + " is an *old* app state.");
            var6 = new Transition(this.h(), var5.updateTransition(), var2);
            Transition var7;
            if ((var7 = var4.b(var6)) == null) {
                System.out.println("[AndroidAppFSM] AgentController: the transition is A *new* transition. ");
                System.out.println("[AndroidAppFSM] AgentController: this is the new transition: ");
                System.out.println(var6.toString());
                var4.a(var6);
            } else {
                System.out.println("[AndroidAppFSM] AgentController: the transition is an *old* transition: ");
                D.updateTransition(var7);
            }
        }
    }

    public final void f() {
        System.out.println("[AndroidAppFSM] AgentController: computing initial transition probablities from the FSM model ...");
        System.out.println("[AndroidAppFSM] AgentController: the FSM has total " + this.d.size() + " states. ");
        Iterator var1 = this.d.iterator();

        while(var1.hasNext()) {
            String var2 = (String)var1.next();
            D var3;
            if ((var3 = (D)this.c.get(var2)).l()) {
                System.out.println("[AndroidAppFSM] AgentController: unique state: " + var3.e());
                var3.o();
            }
        }

    }

    public final void b(String var1) {
        System.out.println("AgentController: compute the properties of the current FSM model... ");
        this.stateCount = 0;
        this.transitionCount = 0;
        Iterator var2 = this.c.entrySet().iterator();

        while(var2.hasNext()) {
            D var4;
            if ((var4 = (D)((Entry)var2.next()).getValue()).l()) {
                ++this.stateCount;
                this.transitionCount += var4.i();
            }
        }

        System.out.println("[AndroidAppFSM] AgentController: the FSM model has total *unique states*: " + this.stateCount + "; total *unique transitions*: " + this.transitionCount);
        PrintWriter var3 = null;

        try {
            (var3 = new PrintWriter(new BufferedWriter(new FileWriter(var1, true)))).write(this.stateCount + "  " + this.transitionCount + "\n");
        } catch (IOException var5) {
            System.out.println("[AndroidAppFSM] E: failed to write fsm_building_progress_file!");
            var5.printStackTrace();
        }

        var3.close();
    }

    public final void c(String var1) {
        System.out.println("[AndroidAppFSM] AgentController: export the FSM into the dot file: " + var1);
        String var2 = "  ";
        String var3 = "\n";
        String var4 = " -> ";
        String var5 = ";";
        String var6 = "digraph graphtest {" + var3;
        Iterator var7 = this.d.iterator();

        while(true) {
            String var8;
            D var17;
            do {
                if (!var7.hasNext()) {
                    var6 = var6 + "}";
                    PrintWriter var16 = null;

                    try {
                        (var16 = new PrintWriter(var1, "UTF-8")).write(var6);
                    } catch (IOException var14) {
                        System.out.println("[AndroidAppFSM] E: failed to export dot file!");
                        var14.printStackTrace();
                    }

                    var16.close();
                    return;
                }

                var8 = (String)var7.next();
            } while(!(var17 = (D)this.c.get(var8)).l());

            System.out.println("\tunique state name: " + var17.e());
            Iterator var9 = var17.transitionList.iterator();

            while(var9.hasNext()) {
                int var10 = (Integer)var9.next();
                Transition var15;
                String var11 = (var15 = var17.a(var10)).getSourceState();
                String var12 = var15.getDestinationState();
                System.out.println("\t\ttransition: " + ((D)this.c.get(var11)).e() + " -> " + ((D)this.c.get(var12)).e() + ", action:" + var15.d());
                if (((D)this.c.get(var12)).k()) {
                    var6 = var6 + var2 + ((D)this.c.get(var11)).e() + var4 + ((D)this.c.get(var12)).e() + var2 + "[ style=dotted,label=\"@" + var15.c() + "\"] " + var5 + var3;
                } else {
                    var6 = var6 + var2 + ((D)this.c.get(var11)).e() + var4 + ((D)this.c.get(var12)).e() + var2 + "[ label=\"@" + var15.c() + "\"] " + var5 + var3;
                }
            }
        }
    }

    public final void d(String var1) {
        System.out.println("[AndroidAppFSM] AgentController: dump the FSM into the file: " + var1);
        PrintWriter var2 = null;

        try {
            (var2 = new PrintWriter(var1, "UTF-8")).println("# the app FSM, including all unique app states and transitions. Please keep this file unchanged. ");
            var2.println(this.stateCount + ";" + this.transitionCount);
            Iterator var6 = this.c.entrySet().iterator();

            label29:
            while(true) {
                D var3;
                do {
                    if (!var6.hasNext()) {
                        break label29;
                    }
                } while(!(var3 = (D)((Entry)var6.next()).getValue()).l());

                var2.println(var3.d());
                var2.println(var3.e() + ";" + var3.f() + ";" + var3.i());
                Iterator var7 = var3.j().entrySet().iterator();

                while(var7.hasNext()) {
                    Transition var4 = (Transition)((Entry)var7.next()).getValue();
                    var2.println(var4.getSourceState() + ";" + var4.getDestinationState() + ";" + var4.c() + ";" + var4.d().replace("\n", "") + ";" + var4.getTransitionID() + ";" + var4.getExecutionCount() + ";" + var4.f());
                }
            }
        } catch (IOException var5) {
            System.out.println("[AndroidAppFSM] E: failed to dump FSM!");
            var5.printStackTrace();
        }

        var2.close();
    }

    public final void restoreFSMFromFile(String fsmFilePath) {
        System.out.println("[AndroidAppFSM] AgentController: restore the FSM from the file: " + fsmFilePath);

        try {
            FileInputStream fin = new FileInputStream(fsmFilePath);
            InputStreamReader fr = new InputStreamReader(fin, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(fr);

            try {
                String agentControllerName = br.readLine();
                System.out.println("AgentController: " + agentControllerName);
                String[] var14;
                int stateCnt = Integer.parseInt((var14 = br.readLine().split(";"))[0]);
                int transitionCnt = Integer.parseInt(var14[1]);
                System.out.println("[AndroidAppFSM] AgentController: app states count: " + stateCnt + ", transitions count: " + transitionCnt);
                this.stateCount = stateCnt;
                this.transitionCount = transitionCnt;

                for(int i = 0; i < stateCnt; ++i) {
                    D var5 = new D();
                    agentControllerName = br.readLine();
                    var5.b(agentControllerName);
                    int var6 = Integer.parseInt((var14 = br.readLine().split(";"))[2]);
                    var5.updateTransition(var14[1]);

                    for(int var7 = 0; var7 < var6; ++var7) {
                        var14 = br.readLine().split(";");
                        Transition transition;
                        (transition = new Transition()).getSourceState(var14[0]);
                        transition.getDestinationState(var14[1]);
                        transition.getSourceState(Integer.parseInt(var14[2]));
                        transition.c(var14[3]);
                        transition.setTransitionID(Integer.parseInt(var14[4]));
                        transition.setExecutionCount(Integer.parseInt(var14[5]));
                        transition.setG(0.0D);
                        this.i.add(Integer.parseInt(var14[2]));
                        var5.a(transition);
                    }

                    String var16 = var5.d();
                    this.c.put(var16, var5);
                    this.d.add(var16);
                    this.e = this.d.size();
                    this.g = var16;
                }
            } catch (IOException var9) {
                System.out.println("[AndroidAppFSM] E: failed to restore FSM, *readline* !");
                var9.printStackTrace();
                return;
            }
        } catch (FileNotFoundException var10) {
            System.out.println("[AndroidAppFSM] E: failed to restore FSM!");
            var10.printStackTrace();
        }

    }

    public final void a(String var1, String var2) {
        D var3 = new D();
        W var4 = new W();
        if (var1.contains("EMPTY_APP_STATE")) {
            var3.b(var1);
            var3.updateTransition(var2);
            var3.d("EMPTY_APP_STATE");
            System.out.println("D: this is an empty state! ");
        } else if (var1.contains("RESET_APP_STATE")) {
            var3.b(var1);
            var3.updateTransition(var2);
            var3.d("RESET_APP_STATE");
            System.out.println("D: this is A reset state! ");
        } else {
            var3.b(var1);
            var3.updateTransition(var2);

            try {
                File var6 = new File(var1);
                Document var7;
                (var7 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(var6)).getDocumentElement().normalize();
                if (var7.hasChildNodes()) {
                    NodeList var8;
                    if ((var8 = var7.getChildNodes()).getLength() == 1) {
                        Node var10;
                        if ((var10 = var8.item(0)).hasChildNodes()) {
                            var4.a(var10.getChildNodes(), false, false, false, false);
                        } else {
                            System.out.println("E: the root element \"hierarchy\" has no child nodes?? ");
                            System.exit(0);
                        }
                    } else {
                        System.out.println("E: the root element node is not the unique \"hierarchy\"?? ");
                        System.exit(0);
                    }
                }
            } catch (Exception var5) {
                System.out.println(var5.getMessage());
                System.out.println("D: error when parsing the xml file");
                System.exit(0);
            }
        }

        var3.a(var4);
        D var9 = var3;
        String var12 = var3.d();
        this.c.put(var12, var9);
        this.d.add(var12);
        this.e = this.d.size();
        this.g = var12;
        System.out.println("[AndroidAppFSM] AgentController: checking *alias* for the app state: " + var9.e());
        var9.a();
        var12 = var9.b();
        System.out.println("[AndroidAppFSM] its md5 value = " + var12);
        if (this.f.containsKey(var12)) {
            D var11 = (D)this.f.get(var12);
            var9.a(var11.d());
            System.out.println("[AndroidAppFSM] AgentController: the app state: " + var9.e() + " is alias to this previous state: " + var11.e());
        } else {
            System.out.println("[AndroidAppFSM] AgentController: this is A new app state");
            this.f.put(var12, var9);
        }
    }

    public static void main(String[] var0) {
        String var1 = "/home/suting/AppTest/fr.kwiatkowski.ApkTrack_11_src.tar2.gz_fsm_building_3/ui/S_871.xml";
        c().a(var1, "");
    }

    public final void f(String var1) {
        PrintWriter var2 = null;

        try {
            var2 = new PrintWriter(var1, "UTF-8");
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        Iterator var3 = this.c.entrySet().iterator();

        while(var3.hasNext()) {
            Entry var4;
            String var5 = (String)(var4 = (Entry)var3.next()).getKey();
            D var7 = (D)var4.getValue();
            var2.println("the state file name: " + var5);
            var2.println(var7.toString());
            var2.println("-----------\n");
        }

        var2.close();
        System.out.println("AgentController: dump all app states in the FSM into the file: " + var1);
    }

    public final String g(String var1) {
        D var2;
        W var3 = (var2 = (D)this.c.get(var1)).h();
        System.out.println("[AndroidAppFSM] AgentController: start to detect invokable actions in the current app state. ");
        if (var3.b.size() == 0) {
            if (var2.g().equals("EMPTY_APP_STATE")) {
                System.out.println("[AndroidAppFSM] AgentController: the app state does not has view components, this is an empty state.");
                B.a();
                B.a(var2);
            } else if (var2.g().equals("RESET_APP_STATE")) {
                System.out.println("[AndroidAppFSM] AgentController: the app state does not has view components, this is A reset state.");
                B.a();
                B.b(var2);
            }

            System.out.println("==========");
            System.out.println("[AndroidAppFSM] AgentController: the final detected invokable actions on this app state: ");
            var2.m();
            return var2.n();
        } else {
            String var8;
            if (var1.contains("overflow_menu")) {
                System.out.println("[AndroidAppFSM] AgentController: read overflow menu action list aagtl_overflow_menu_events.txt");

                try {
                    FileInputStream var15 = null;
                    if (var1.contains("aagtl")) {
                        var15 = new FileInputStream("/home/suting/proj/fsmdroid/special/aagtl_overflow_menu_events.txt");
                    } else if (var1.contains("sanity")) {
                        var15 = new FileInputStream("/home/suting/proj/fsmdroid/special/sanity_overflow_menu_events.txt");
                    } else if (var1.contains("wiki")) {
                        var15 = new FileInputStream("/home/suting/proj/fsmdroid/special/wiki_overflow_menu_events.txt");
                    } else if (var1.contains("bubble")) {
                        var15 = new FileInputStream("/home/suting/proj/fsmdroid/special/frozenbubble_overflow_menu_events.txt");
                    }

                    InputStreamReader var12 = new InputStreamReader(var15, Charset.forName("UTF-8"));
                    BufferedReader var16 = new BufferedReader(var12);

                    try {
                        while((var1 = var16.readLine()) != null) {
                            System.out.println("AgentController: " + var1 + "#");
                            H var17;
                            (var17 = new H()).a();
                            var17.a("");
                            var17.c(var2.f());
                            var8 = "Screen";
                            var17.a = var8;
                            var17.d("tap");
                            var1 = var1 + "\n";
                            var17.a(var1, "");
                            B.a();
                            Integer var13 = B.a(var17);
                            var2.a(var13);
                            System.out.println("[AndroidAppFSM] AgentController: create A *Tap* Action!");
                        }
                    } catch (IOException var9) {
                        System.out.println("[AndroidAppFSM] E: failed to read overflow menu file, *readline* !");
                        var9.printStackTrace();
                        System.exit(0);
                    }
                } catch (FileNotFoundException var10) {
                    System.out.println("[AndroidAppFSM] E: failed to read overflow menu fil!");
                    var10.printStackTrace();
                    System.exit(0);
                }

                B.a();
                B.c(var2);
                System.out.println("==========");
                System.out.println("[AndroidAppFSM] AgentController: the final detected invokable actions on this app state: ");
                var2.m();
                return var2.n();
            } else {
                Iterator var14 = var3.b.iterator();

                while(true) {
                    Integer var5;
                    H var6;
                    String var7;
                    U var11;
                    while(true) {
                        label226:
                        do {
                            while(true) {
                                while(true) {
                                    while(true) {
                                        while(true) {
                                            while(var14.hasNext()) {
                                                String var4 = (var11 = (U)var14.next()).f();
                                                System.out.println("[AndroidAppFSM] AgentController: view type: " + var4);
                                                System.out.println("[AndroidAppFSM] AgentController: cis - " + var11.e());
                                                if (!var11.f().contains(".TextView") && !var11.f().contains(".Button") && !var11.f().contains(".ToggleButton")) {
                                                    if (!var11.f().contains(".ImageView") && !var11.f().contains(".ImageButton")) {
                                                        if (!var11.f().contains(".EditText") && !var11.f().contains(".MultiAutoCompleteTextView")) {
                                                            if (!var11.f().contains(".CheckBox") && !var11.f().contains(".RadioButton")) {
                                                                if (var11.f().contains(".CheckedTextView")) {
                                                                    System.out.println("checkable = " + var11.k());
                                                                    continue label226;
                                                                }

                                                                if (var11.f().contains(".SeekBar")) {
                                                                    System.out.println("clickable = " + var11.i());
                                                                    if (var11.i()) {
                                                                        (var6 = new H()).a();
                                                                        var6.a(var11.e());
                                                                        var6.b(var11.d());
                                                                        var6.c(var2.f());
                                                                        var8 = "Screen";
                                                                        var6.a = var8;
                                                                        var6.d("click");
                                                                        if (!var11.g().equals("")) {
                                                                            var7 = "click(resource-id='" + var11.g() + "')\n";
                                                                        } else if (!var11.h().equals("")) {
                                                                            var7 = "click(content-desc='" + var11.h() + "')\n";
                                                                        } else {
                                                                            var7 = "click(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                                                                        }

                                                                        var6.a(var7, var11.f());
                                                                        B.a();
                                                                        var5 = B.a(var6);
                                                                        var2.a(var5);
                                                                        System.out.println("[AndroidAppFSM] AgentController: create A *SeekBar Click* Action!");
                                                                    }
                                                                }
                                                            } else {
                                                                System.out.println("checkable = " + var11.k());
                                                                if (var11.k()) {
                                                                    (var6 = new H()).a();
                                                                    var6.a(var11.e());
                                                                    var6.b(var11.d());
                                                                    var6.c(var2.f());
                                                                    var8 = "Screen";
                                                                    var6.a = var8;
                                                                    var6.d("click");
                                                                    if (!var11.g().equals("")) {
                                                                        var7 = "click(resource-id='" + var11.g() + "')\n";
                                                                    } else if (!var11.d().equals("")) {
                                                                        if (!var11.b()) {
                                                                            var7 = "click(text='" + var11.d() + "')\n";
                                                                        } else {
                                                                            var7 = "click(textContains='" + var11.c() + "')\n";
                                                                        }
                                                                    } else if (!var11.h().equals("")) {
                                                                        var7 = "click(content-desc='" + var11.h() + "')\n";
                                                                    } else {
                                                                        var7 = "click(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                                                                    }

                                                                    var6.a(var7, var11.f());
                                                                    B.a();
                                                                    var5 = B.a(var6);
                                                                    var2.a(var5);
                                                                    System.out.println("[AndroidAppFSM] AgentController: create A *CheckBox/RadioButton* Action!");
                                                                }
                                                            }
                                                        } else {
                                                            (var6 = new H()).a();
                                                            var6.a(var11.e());
                                                            var6.b(var11.d());
                                                            var6.c(var2.f());
                                                            var8 = "Screen";
                                                            var6.a = var8;
                                                            var6.d("edit");
                                                            if (!var11.g().equals("")) {
                                                                var7 = "edit(resource-id='" + var11.g() + "')\n";
                                                            } else if (!var11.h().equals("")) {
                                                                var7 = "edit(content-desc='" + var11.h() + "')\n";
                                                            } else {
                                                                var7 = "edit(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                                                            }

                                                            var6.a(var7, var11.f());
                                                            B.a();
                                                            var5 = B.a(var6);
                                                            var2.a(var5);
                                                            System.out.println("[AndroidAppFSM] AgentController: create an *EditText* Action!");
                                                        }
                                                    } else {
                                                        if (var11.i()) {
                                                            (var6 = new H()).a();
                                                            var6.a(var11.e());
                                                            var6.b(var11.d());
                                                            var6.c(var2.f());
                                                            var8 = "Screen";
                                                            var6.a = var8;
                                                            var6.d("click");
                                                            if (!var11.g().equals("")) {
                                                                var7 = "click(resource-id='" + var11.g() + "')\n";
                                                            } else if (!var11.h().equals("")) {
                                                                var7 = "click(content-desc='" + var11.h() + "')\n";
                                                            } else {
                                                                var7 = "click(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                                                            }

                                                            var6.a(var7, var11.f());
                                                            B.a();
                                                            var5 = B.a(var6);
                                                            var2.a(var5);
                                                            System.out.println("[AndroidAppFSM] AgentController: create A *Click Image* Action!");
                                                        }

                                                        if (var11.j()) {
                                                            (var6 = new H()).a();
                                                            var6.a(var11.e());
                                                            var6.b(var11.d());
                                                            var6.c(var2.f());
                                                            var8 = "Screen";
                                                            var6.a = var8;
                                                            var6.d("clickLong");
                                                            if (!var11.g().equals("")) {
                                                                var7 = "clickLong(resource-id='" + var11.g() + "')\n";
                                                            } else if (!var11.h().equals("")) {
                                                                var7 = "clickLong(content-desc='" + var11.h() + "')\n";
                                                            } else {
                                                                var7 = "clickLong(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                                                            }

                                                            var6.a(var7, var11.f());
                                                            B.a();
                                                            var5 = B.a(var6);
                                                            var2.a(var5);
                                                            System.out.println("[AndroidAppFSM] AgentController: create A *Long Click Image* Action!");
                                                        }

                                                        System.out.println("[AndroidAppFSM] AgentController: up to now, detected invokable actions: ");
                                                        var2.m();
                                                        System.out.println("==========");
                                                    }
                                                } else {
                                                    System.out.println("clickable = " + var11.i() + ", longclickable = " + var11.j());
                                                    if (var11.i()) {
                                                        label195: {
                                                            (var6 = new H()).a();
                                                            var6.a(var11.e());
                                                            var6.b(var11.d());
                                                            var6.c(var2.f());
                                                            var8 = "Screen";
                                                            var6.a = var8;
                                                            var6.d("click");
                                                            if (!var11.d().equals("")) {
                                                                if (!E.r) {
                                                                    if (!var11.b()) {
                                                                        var7 = "click(text='" + var11.d() + "')\n";
                                                                    } else {
                                                                        var7 = "click(textContains='" + var11.c() + "')\n";
                                                                    }
                                                                } else {
                                                                    var7 = "click(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                                                                }
                                                            } else if (!var11.g().equals("")) {
                                                                var7 = "click(resource-id='" + var11.g() + "')\n";
                                                            } else {
                                                                if (var11.h().equals("")) {
                                                                    break label195;
                                                                }

                                                                var7 = "click(content-desc='" + var11.h() + "')\n";
                                                            }

                                                            var6.a(var7, var11.f());
                                                            B.a();
                                                            var5 = B.a(var6);
                                                            var2.a(var5);
                                                            System.out.println("[AndroidAppFSM] AgentController: create A *Click* Action!");
                                                        }
                                                    }

                                                    if (var11.j()) {
                                                        label187: {
                                                            (var6 = new H()).a();
                                                            var6.a(var11.e());
                                                            var6.b(var11.d());
                                                            var6.c(var2.f());
                                                            var8 = "Screen";
                                                            var6.a = var8;
                                                            var6.d("clickLong");
                                                            if (!var11.d().equals("")) {
                                                                if (!E.r) {
                                                                    if (!var11.b()) {
                                                                        var7 = "clickLong(text='" + var11.d() + "')\n";
                                                                    } else {
                                                                        var7 = "clickLong(textContains='" + var11.c() + "')\n";
                                                                    }
                                                                } else {
                                                                    var7 = "clickLong(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                                                                }
                                                            } else if (!var11.g().equals("")) {
                                                                var7 = "clickLong(resource-id='" + var11.g() + "')\n";
                                                            } else {
                                                                if (var11.h().equals("")) {
                                                                    break label187;
                                                                }

                                                                var7 = "clickLong(content-desc='" + var11.h() + "')\n";
                                                            }

                                                            var6.a(var7, var11.f());
                                                            B.a();
                                                            var5 = B.a(var6);
                                                            var2.a(var5);
                                                            System.out.println("[AndroidAppFSM] AgentController: create A *Long Click* Action!");
                                                        }
                                                    }

                                                    System.out.println("[AndroidAppFSM] AgentController: up to now, detected invokable actions: ");
                                                    var2.m();
                                                    System.out.println("==========");
                                                }
                                            }

                                            B.a();
                                            B.c(var2);
                                            System.out.println("==========");
                                            System.out.println("[AndroidAppFSM] AgentController: the final detected invokable actions on this app state: ");
                                            var2.m();
                                            return var2.n();
                                        }
                                    }
                                }
                            }
                        } while(!var11.k());

                        (var6 = new H()).a();
                        var6.a(var11.e());
                        var6.b(var11.d());
                        var6.c(var2.f());
                        var8 = "Screen";
                        var6.a = var8;
                        var6.d("click");
                        if (!var11.d().equals("")) {
                            if (!E.r) {
                                if (!var11.b()) {
                                    var7 = "click(text='" + var11.d() + "')\n";
                                } else {
                                    var7 = "click(textContains='" + var11.c() + "')\n";
                                }
                            } else {
                                var7 = "click(className='" + var11.f() + "',instance='" + var11.a() + "')\n";
                            }
                            break;
                        }

                        if (!var11.g().equals("")) {
                            var7 = "click(resource-id='" + var11.g() + "')\n";
                            break;
                        }

                        if (!var11.h().equals("")) {
                            var7 = "click(content-desc='" + var11.h() + "')\n";
                            break;
                        }
                    }

                    var6.a(var7, var11.f());
                    B.a();
                    var5 = B.a(var6);
                    var2.a(var5);
                    System.out.println("[AndroidAppFSM] AgentController: create A *Check TextView* Action!");
                }
            }
        }
    }
}
