//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class TestManager {
    public List a = new ArrayList();
    private List b = new ArrayList();
    private static int suiteSize;
    private static int d;
    private static int seqLength;
    private static int emulatorPoolSize;
    private AgentController g;
    private static TestManager h = null;
    private Set i = null;
    private Set j = null;

    private TestManager() {
    }

    public static TestManager a() {
        if (h == null) {
            h = new TestManager();
        }

        return h;
    }

    public final void a(AndroidAppFSM fsm) {
        suiteSize = ConfigOptions.MaxTestSuiteSize;/*seqLength.m*/
        if ((seqLength = (int) Math.sqrt((double) fsm.transitionCount)) > ConfigOptions.MaxTestSequenceLength/*seqLength.n*/) {
            seqLength = ConfigOptions.MaxTestSequenceLength;/*seqLength.n;*/
        }

        emulatorPoolSize = 1;
        System.out.println("[TestManager] AgentController: the test manager configuration< emulators pool size: " + emulatorPoolSize
                + ", maxTestSuiteSize: " + suiteSize + ", maxTestCaseLength: " + seqLength + " >");
        String controllerName = "MCMC-droid-Controller";
        System.out.println("[TestManager] AgentController: start the emulator controller ... ");
        if (this.g == null) {
            this.g = new AgentController(controllerName);
        }

        this.g.a();
    }

    private static int a(List var0) {
        int var1;
        if ((var1 = var0.size()) == 0) {
            return -1;
        } else {
            ArrayList var2 = new ArrayList();

            int var3;
            J var4;
            for (var3 = 0; var3 < var1; ++var3) {
                if ((var4 = (J) var0.get(var3)).e() < 3) {
                    var2.add(var4);
                }
            }

            if (var2.size() == 0) {
                for (var3 = 0; var3 < var1; ++var3) {
                    (var4 = (J) var0.get(var3)).g();
                    var2.add(var4);
                }
            }

            var3 = var2.size();
            if (/*!KA && */var3 <= 0) {
                throw new AssertionError();
            } else {
                int var5 = Math.abs((new Random()).nextInt()) % var3;
                return ((J) var2.get(var5)).c();
            }
        }
    }

    private static int a(List var0, int var1) {
        int var2 = -1;
        int var3;
        if ((var3 = var0.size()) == 0) {
            return -1;
        } else {
            ArrayList var4 = new ArrayList();

            int var5;
            J var6;
            for (var5 = 0; var5 < var3; ++var5) {
                if ((var6 = (J) var0.get(var5)).e() < var1) {
                    var4.add(var6);
                }
            }

            if (var4.size() == 0) {
                for (var5 = 0; var5 < var3; ++var5) {
                    (var6 = (J) var0.get(var5)).g();
                    var4.add(var6);
                }
            }

            var5 = var4.size();
            double var14 = 0.0D;

            for (int var8 = 0; var8 < var5; ++var8) {
                var14 += ((J) var4.get(var8)).d();
            }

            double var15;
            if (ConfigOptions.u) {
                var15 = Math.random() * var14;
            } else {
                var15 = var14 - Math.random() * var14;
            }

            double var10 = 0.0D;

            for (int var12 = 0; var12 < var5; ++var12) {
                J var13 = (J) var4.get(var12);
                var10 += var13.d();
                if (var15 <= var10) {
                    var2 = var13.c();
                    var13.f();
                    break;
                }
            }

            System.out.println("selected transition id: " + var2);
            return var2;
        }
    }

    private static void a(Map var0) {
        Iterator var3 = var0.entrySet().iterator();

        while (var3.hasNext()) {
            Iterator var1 = ((List) ((Entry) var3.next()).getValue()).iterator();

            while (var1.hasNext()) {
                ((J) var1.next()).g();
            }
        }

    }


    private void a(AndroidAppFSM var1, Map var2) {
        System.out.println("[TestManager] AgentController: start test generation ...");
        String var3 = AndroidAppFSM.b();
        if (/*!KA &&*/var3 == null) {
            throw new AssertionError();
        } else {
            System.out.println("[TestManager] AgentController: the entry state name: " + var3);
            d = 0;
            int var4 = 0;
            this.c();
            this.d();
            a(var2);
            this.b.clear();
            int var6 = 1;

            do {
                String var5 = var3;
                ArrayList var7 = new ArrayList();

                ArrayList var8;
                Transition var12;
                for (var8 = new ArrayList(); var4 < seqLength; var5 = var12.getDestinationState()) {
                    ++var4;
                    List var9 = (List) var2.get(var5);
                    int var10 = -1;
                    if (/*seqLength.p*/ConfigOptions.ProabilityModelMode == 1) {
                        var10 = a(var9, 5);
                    } else if (/*seqLength.p*/ConfigOptions.ProabilityModelMode == 2) {
                        var10 = a(var9);
                    } else {
                        System.exit(-1);
                    }

                    if (var10 == -1) {
                        System.out.println("[TestManager] AgentController: the test sequence ends here!");
                        break;
                    }

                    String var13 = (var12 = var1.a(var5).a(var10)).d();
                    int var11 = var12.c();
                    this.a(var5);
                    this.b(var12.getTransitionID());
                    var7.add(var13);
                    var8.add(var11);
                }

                this.a.add(var7);
                this.b.add(var8);
                var4 = 0;
                if (++d % 5 == 0) {
                    this.a(var1, /*seqLength.getExecutionCount*/ConfigOptions.MaxTestSequenceLength + "/markov_model_test_suite_data.txt", var6);
                    ++var6;
                }
            } while (d < /*seqLength.o*/ConfigOptions.MaxTestSuiteComparision);

            System.out.println("[TestManager] AgentController: exceed the max test suite size: " +
                    /*seqLength.o*/ConfigOptions.MaxTestSuiteComparision + ", covered nodes cnt: " + this.i.size() +
                    ", total node cnt: " + var1.stateCount + ", covered edges cnt: " + this.j.size() + ", total edge cnt: " + var1.transitionCount);
            this.a(var1, ConfigOptions.MCMCSamplingOutputDir/*seqLength.getExecutionCount*/ + "/markov_model_test_suite_data.txt", var6);
        }
    }

    private void a(AndroidAppFSM var1, String var2, int var3) {
        String var4 = "";
        var4 = var4 + " ----- " + var3 + "th iteration ----\n";
        var4 = var4 + "nodeCoverage: " + this.c(var1) + ", edgeCoverage: " + this.d(var1) + ", diversity: " + this.b() + "\n";

        try {
            FileWriter var6;
            (var6 = new FileWriter(var2, true)).write(var4);
            var6.write("-----\n\n");
            var6.close();
        } catch (IOException var5) {
            System.out.println("[TestManager] failed to open Action file");
            System.exit(0);
        }
    }

    public final void a(AndroidAppFSM fsm, Map var2, int var3) {
        System.out.println("[TestManager] AgentController: generate test suite according to the new Markov Model ... ");
        if (var3 == 0) {
            System.out.println("[TestManager] test generation strategy: HIGH_PROBAB_TEST_GENERATION");
            Map var14 = var2;
            AndroidAppFSM ifsm = fsm;
            TestManager var12 = this;
            System.out.println("[TestManager] AgentController: start test generation ...");
            String stateName = AndroidAppFSM.b();
            if (/*!KA && */stateName == null) {
                throw new AssertionError();
            }

            System.out.println("[TestManager] AgentController: the entry state name: " + stateName);
            d = 0;
            int var5 = 0;
            this.c();
            this.d();
            a(var2);
            this.b.clear();

            while (true) {
                int var7 = ifsm.stateCount;
                int var6 = var12.i.size();
                System.out.println("[TestManager] AgentController: total nodes cnt: " + var7 + ", covered nodes cnt: " + var6);
                if (var6 == var7) {
                    break;
                }

                String var15 = stateName;
                ArrayList var16 = new ArrayList();
                ArrayList var8 = new ArrayList();

                while (var5 < seqLength) {
                    ++var5;
                    int var9;
                    if ((var9 = a((List) var14.get(var15), 3)) == -1) {
                        System.out.println("[TestManager] AgentController: the test sequence ends here!");
                        var12.a(var15);
                        break;
                    }

                    Transition var17 = ifsm.a(var15).a(var9);
                    System.out.println("selected transition:" + var17);
                    String var10 = var17.d();
                    int var11 = var17.c();
                    var12.a(var15);
                    var12.b(var17.getTransitionID());
                    var16.add(var10);
                    var8.add(var11);
                    if ((var15 = var17.getDestinationState()).contains("EMPTY_APP_STATE")) {
                        System.out.println("[TestManager] AgentController: th next state is an EMPTY state, the test sequence generation ends here!");
                        break;
                    }
                }

                var12.a.add(var16);
                var12.b.add(var8);
                var5 = 0;
                if (++d >= suiteSize) {
                    System.out.println("[TestManager] AgentController: exceed the max test suite size: " + suiteSize + ", covered nodes cnt: " + var12.i.size() + ", total node cnt: " + ifsm.stateCount + ", covered edges cnt: " + var12.j.size() + ", total edge cnt: " + ifsm.transitionCount);
                    break;
                }
            }
        } else if (var3 == 1) {
            System.out.println("[TestManager] test generation strategy: COMPARE_PROBAB_TEST_GENERATION");
            this.a(fsm, var2);
        }

        System.out.println("[TestManager] AgentController: dump the generated test suite to the file ...");
        this.b(/*seqLength.getExecutionCount*/ConfigOptions.MCMCSamplingOutputDir + "/mcmc_all_history_testsuites.txt");
    }

    public final double b(AndroidAppFSM var1) {
        TraceDiversity var2 = new TraceDiversity();
        System.out.println("unique actions cnt = " + var1.d().size());
        Iterator var3 = var1.d().iterator();

        while (var3.hasNext()) {
            Integer var4 = (Integer) var3.next();
            var2.bList.add(var4);
        }

        var3 = this.b.iterator();

        while (var3.hasNext()) {
            ArrayList var8 = (ArrayList) var3.next();
            ArrayList var5 = new ArrayList();
            Iterator var9 = var8.iterator();

            while (var9.hasNext()) {
                Integer var6 = (Integer) var9.next();
                var5.add(var6);
            }

            var2.aList.add(var5);
        }

        double var7 = Math.sqrt(var2.a() / (double) var1.d().size());
        System.out.println("diversity value = " + var7);
        return var7;
    }

    private int b() {
        int var1 = this.b.size();
        if (/*!KA &&*/ var1 <= 0) {
            throw new AssertionError();
        } else {
            int var2 = 0;
            HashSet var3 = new HashSet();

            for (int var4 = 0; var4 < seqLength; ++var4) {
                for (int var5 = 0; var5 < var1; ++var5) {
                    ArrayList var6 = (ArrayList) this.b.get(var5);
                    if (var4 < var6.size()) {
                        Integer var7 = (Integer) var6.get(var4);
                        var3.add(var7);
                    }
                }

                var2 += var3.size();
                var3.clear();
            }

            return var2;
        }
    }

    private void c() {
        if (this.i == null) {
            System.out.println("[TestManager] AgentController: the nodes state set is null, init it!");
            this.i = new HashSet();
        } else {
            System.out.println("[TestManager] AgentController: reset the nodes state set!");
            this.i.clear();
        }

        System.out.println("[TestManager] AgentController: output the covered nodes: ");
        Iterator var1 = this.i.iterator();

        while (var1.hasNext()) {
            String var2 = (String) var1.next();
            System.out.print(var2 + "  ");
        }

        System.out.println();
    }

    private void d() {
        if (this.j == null) {
            System.out.println("[TestManager] AgentController: the edge set is null, init it!");
            this.j = new HashSet();
        } else {
            System.out.println("[TestManager] AgentController: reset the edge set!");
            this.j.clear();
        }

        System.out.println("[TestManager] AgentController: output the covered edges: ");
        Iterator var1 = this.j.iterator();

        while (var1.hasNext()) {
            Integer var2 = (Integer) var1.next();
            System.out.print(var2 + "  ");
        }

        System.out.println();
    }

    private void a(String var1) {
        if (this.i != null) {
            this.i.add(var1);
        } else {
            System.out.println("[TestManager] ConfigOptions: nodes state set is null? ");
            System.exit(0);
        }
    }

    private void b(int var1) {
        if (this.j != null) {
            this.j.add(var1);
        } else {
            System.out.println("[TestManager] ConfigOptions: edges set is null? ");
            System.exit(0);
        }
    }

    public final double c(AndroidAppFSM var1) {
        int var2 = this.i.size();
        int var3 = var1.stateCount;
        return (double) var2 / (double) var3;
    }

    public final double d(AndroidAppFSM var1) {
        int var2 = this.j.size();
        int var3 = var1.transitionCount;
        return (double) var2 / (double) var3;
    }

    public final String a(int var1) {
        System.out.println("[TestManager] AgentController: this is *" + var1 + "* th iteration in MCMC sampling.");
        this.g.a(this.a);
        this.a.clear();
        String var2 = this.g.b();
        System.out.println("[TestManager] AgentController: the *" + var1 + "* th iteration in MCMC sampling is finished!!! ");
        return var2;
    }

    private void b(String var1) {
        String var3 = "";

        try {
            FileWriter var2 = new FileWriter(var1, true);
            var3 = var3 + "the " + MCMCSampler.a + "th test suite\n";
            var3 = var3 + "the covered nodes (states): " + this.i.size() + "\n";
            var3 = var3 + "==================\n";

            String var6;
            for (Iterator var4 = this.a.iterator(); var4.hasNext(); var3 = var3 + "\n\n") {
                for (Iterator var5 = ((ArrayList) var4.next()).iterator(); var5.hasNext(); var3 = var3 + var6) {
                    var6 = (String) var5.next();
                }
            }

            var2.write(var3);
            var2.close();
        } catch (IOException var7) {
            System.out.println("[TestManager] ConfigOptions: failed to open the file: " + var1 + " to dump the test suite.");
        }
    }
}
