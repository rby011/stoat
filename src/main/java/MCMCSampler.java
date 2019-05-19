//
// Source code recreated from Action .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.Map.Entry;

public class MCMCSampler {
    private Map b = new HashMap();
    private static int c = 0;
    private static int d = 0;
    public static int a;
    private static int e;
    private double f;
    private double g;
    private double h = 0.0D;
    private double i = 0.0D;
    private double j = 0.0D;
    private double k = 0.0D;
    private double l = 0.0D;
    private double m = 0.0D;
    private int n = 0;
    private int o = -1;
    private static String p = "";
    private static MCMCSampler q = null;

    private MCMCSampler() {
        a = 1;
        e = ConfigOptions.MaxMCMCIteration/*restoreFSMFromFile.l*/;
        this.f = 0.0D;
        this.g = 0.0D;
    }

    private static MCMCSampler a() {
        if (q == null) {
            q = new MCMCSampler();
        }

        return q;
    }

    private void a(AndroidAppFSM var1) {
        System.out.println("[Gibbs Sampler] AgentController: create gibbs vector...");
        Iterator var15 = var1.a().entrySet().iterator();

        while (true) {
            AppState var2;
            String var3;
            String var4;
            do {
                if (!var15.hasNext()) {
                    c = this.b.size();
                    System.out.println("[Gibbs Sampler] AgentController: the gibbs vector has been created, the number of states:  " + c + ", the number of edges: " + d);
                    return;
                }

                var3 = (var2 = (AppState) ((Entry) var15.next()).getValue()).d();
                var4 = var2.e();
            } while (!var2.l());

            ArrayList var5 = new ArrayList();

            for (Iterator var16 = var2.j().entrySet().iterator(); var16.hasNext(); ++d) {
                Entry var6;
                int var7 = (Integer) (var6 = (Entry) var16.next()).getKey();
                double var13 = ((Transition) var6.getValue()).f();
                J var17 = new J(var3, var4, var7, var13);
                var5.add(var17);
            }

            System.out.println("create state name =" + var3);
            this.b.put(var3, var5);
        }
    }

    private void a(String var1) {
        System.out.println("[Gibbs Sampler] AgentController: restore the transitions probabilites from the file: " + var1);

        try {
            FileInputStream var8 = new FileInputStream(var1);
            InputStreamReader var9 = new InputStreamReader(var8, Charset.forName("UTF-8"));
            BufferedReader var10 = new BufferedReader(var9);

            String var2;
            try {
                while ((var2 = var10.readLine()) != null) {
                    System.out.println("AgentController: " + var2);
                    String[] var11;
                    String var3 = (var11 = var2.trim().split("\\s+"))[0];
                    String var4 = var11[1];
                    var2 = var11[2];
                    Iterator var12 = ((List) this.b.get(var3)).iterator();

                    while (var12.hasNext()) {
                        J var5;
                        if ((var5 = (J) var12.next()).c() == Integer.parseInt(var4)) {
                            var5.a(Double.parseDouble(var2));
                        }
                    }
                }
            } catch (IOException var6) {
                System.out.println("[Gibbs Sampler] ConfigOptions: failed to restore transition probabilites, *readline* !");
                var6.printStackTrace();
                return;
            }
        } catch (FileNotFoundException var7) {
            System.out.println("[Gibbs Sampler] ConfigOptions: can not found the optimal markov model file!");
            var7.printStackTrace();
        }

    }

    private void a(List var1, double var2) {
        int var20;
        if ((var20 = var1.size()) > 0) {
            double var5 = 0.0D;
            ArrayList var3 = new ArrayList();

            int var4;
            double var12;
            for (var4 = 0; var4 < var20; ++var4) {
                double var10000 = ((J) var1.get(var4)).d();
                double var16 = 0.1D;
                double var14 = var10000;
                double var18;
                var12 = Math.abs((new Random()).nextInt()) % 2 == 0 ? ((var18 = var14 - var16) <= 0.0D ? var14 : var18) : ((var18 = var14 + var16) >= 1.0D ? var14 : var18);
                var3.add(var12);
                var5 += var12;
            }

            for (var4 = 0; var4 < var20; ++var4) {
                J var7;
                double var10 = (var7 = (J) var1.get(var4)).d();
                var12 = (Double) var3.get(var4) / var5;
                var7.a(var12);
                System.out.println("[Gibbs Sampler] AgentController: state: " + var7.b() + ", transition id: " + var7.c() + ",  orig prob: " + var10 + ", new prob: " + var12);
            }

        }
    }

    private static void a(Map var0, Map var1) {
        Iterator var4 = var0.entrySet().iterator();

        while (var4.hasNext()) {
            Entry var2;
            String var3 = (String) (var2 = (Entry) var4.next()).getKey();
            List var5 = (List) var2.getValue();
            var1.put(var3, var5);
        }

    }

    private Map a(Map var1) {
        HashMap var2 = new HashMap();
        a((Map) var1, var2);
        Iterator var6 = var2.entrySet().iterator();

        while (var6.hasNext()) {
            Entry var3;
            String var4 = (String) (var3 = (Entry) var6.next()).getKey();
            List var7 = (List) var3.getValue();
            if (Math.abs((new Random()).nextInt()) % 2 == 1) {
                System.out.println("mutate app state: " + var4);
                this.a(var7, 0.1D);
            } else {
                System.out.println("keep app state unchanged: " + var4);
            }
        }

        return var2;
    }

    private Map a(AndroidAppFSM var1, Map var2) {
        HashMap var3 = new HashMap();
        a((Map) var2, var3);
        Random var4 = new Random();
        int var5 = var1.stateCount;
        System.out.println("[MCMCSampler]: the last selected app state name: " + p);

        int var7;
        String var8;
        do {
            var7 = Math.abs(var4.nextInt()) % var5;
        } while ((var8 = (String) var1.restoreFSMFromFile().get(var7)).equals(p));

        List var6 = (List) var3.get(var8);
        p = var8;
        System.out.println("[MCMCSampler]: now the selected app state name: " + var8);
        this.a(var6, 0.1D);
        return var3;
    }

    private void b(AndroidAppFSM var1) {
        this.a(var1);
        this.a(ConfigOptions.MCMCSamplingOutputDir/*restoreFSMFromFile.getExecutionCount*/ + "/" + ConfigOptions.ProabilityModelFileName/*restoreFSMFromFile.q*/);
        ArrayList var2 = new ArrayList();
        TestManager.a().a(var1, this.b, 1);
        var2.clear();
        var2.addAll(TestManager.a().a);
        TestManager.a().a.clear();

        for (int var4 = 0; var4 < var2.size(); ++var4) {
            ArrayList var3 = (ArrayList) var2.get(var4);
            TestManager.a().a.add(var3);
            if ((var4 + 1) % 5 == 0) {
                String var5 = TestManager.a().a(a);
                this.h = Double.parseDouble(var5);
                System.out.println("[Gibbs Sampler] AgentController: the test suite execution is finished. ");
                this.b(ConfigOptions.MCMCSamplingOutputDir/*restoreFSMFromFile.getExecutionCount*/ + "/markov_model_test_suite_data.txt");
                ++a;
                TestManager.a().a.clear();
            }
        }

    }

    private void c(AndroidAppFSM var1) {
        this.a(var1);
        double var2 = 0.0D;
        Map var5 = null;
        a(ConfigOptions.MCMCSamplingOutputDir/*restoreFSMFromFile.getExecutionCount*/ + "/initial_markov_model.txt", this.b);

        while (a <= e) {
            b(this.b);
            a(ConfigOptions.MCMCSamplingOutputDir/*restoreFSMFromFile.getExecutionCount*/ + "/mcmc_models.txt", this.b, this.o);
            this.b(ConfigOptions.MCMCSamplingOutputDir/*restoreFSMFromFile.getExecutionCount*/ + "/mcmc_data.txt");
            if (ConfigOptions.t == 2) {
                var5 = this.a(this.b);
            } else if (ConfigOptions.t == 1) {
                var5 = this.a(var1, this.b);
            }

            TestManager.a().a(var1, var5, 0);
            this.i = TestManager.a().c(var1);
            this.j = TestManager.a().d(var1);
            this.k = TestManager.a().b(var1);
            System.out.println("nodeCov = " + this.i + ", edgeCov = " + this.j + ", diversity = " + this.k);
            String var4 = TestManager.a().a(a);
            this.h = Double.parseDouble(var4);
            this.g = 0.4D * this.h + 0.3D * this.j + 0.3D * this.k;
            this.l = (this.f - this.g) * 100.0D;
            if (this.g >= var2) {
                var2 = this.g;
                a(ConfigOptions.MCMCSamplingOutputDir/*restoreFSMFromFile.getExecutionCount*/ + "/optimal_markov_model.txt", var5);
                this.n = a + 1;
                System.out.println("[Gibbs Sampler] AgentController: the current optimal model is from " + this.n + "th iteration!");
            }

            double var7 = this.g;
            boolean var10000;
            if (this.f == 0.0D) {
                System.out.println("[Gibbs Sampler] AgentController: ACCEPT the new gibbs model (the first iteration) ! ");
                this.f = var7;
                var10000 = true;
            } else {
                this.m = Math.exp(-0.33D * (this.f - var7) * 100.0D);
                System.out.println("[Gibbs Sampler] AgentController: acceptance ratio = " + this.m);
                if (this.m >= 1.0D) {
                    System.out.println("[Gibbs Sampler] AgentController: ACCEPT the new gibbs model by ratio>=1 ! ");
                    this.f = var7;
                    var10000 = true;
                } else {
                    double var9 = Math.random();
                    System.out.println("[Gibbs Sampler] AgentController: the random variable value: " + var9);
                    if (var9 >= 0.0D && var9 <= this.m) {
                        System.out.println("[Gibbs Sampler] AgentController: ACCEPT the new gibbs model by probability :  " + this.m);
                        this.f = var7;
                        var10000 = true;
                    } else {
                        System.out.println("[Gibbs Sampler] AgentController: REJECT the new gibbs model by probability: " + this.m);
                        var10000 = false;
                    }
                }
            }

            if (var10000) {
                this.b = var5;
                this.o = 1;
            } else {
                this.o = 0;
            }

            System.out.println("[Gibbs Sampler] AgentController: the test suite execution is finished. ");
            ++a;
        }

    }

    private static void b(Map var0) {
        System.out.println("[Gibbs Sampler] AgentController: output the gibbs vector <state name, transition id, probability>");
        Iterator var3 = var0.entrySet().iterator();

        while (var3.hasNext()) {
            Iterator var1 = ((List) ((Entry) var3.next()).getValue()).iterator();

            while (var1.hasNext()) {
                J var2 = (J) var1.next();
                System.out.println("    \t\t" + var2.b() + "\t" + var2.c() + "\t" + var2.d());
            }
        }

    }

    private void b(String var1) {
        String var2 = "";
        var2 = var2 + "---- " + a + "th iteration in MCMC Sampling -----\n";
        var2 = var2 + "nodeCoverage: " + this.i + ", edgeCoverage: " + this.j + ", lineCoverage: " + this.h + ", diversity_value: " + this.k + ", fintess value substraction: " + this.l + ", current fitness value: " + this.f + ", accept ration: " + this.m + ", --> accept(1) or reject(0): " + this.o + ", best_markov_model_id(iteration): " + this.n + "\n";

        try {
            FileWriter var4;
            (var4 = new FileWriter(var1, true)).write(var2);
            var4.write("-----\n\n");
            var4.close();
        } catch (IOException var3) {
            System.out.println("[Gibbs Sampler] failed to open Action file");
            System.exit(0);
        }
    }

    private static void a(String var0, Map var1, int var2) {
        String var3 = "";
        var3 = var3 + "---- " + a + "th iteration in MCMC Sampling -----\n";
        if (var2 == 1) {
            var3 = var3 + "---- new accepted model ---- \n";
        } else if (var2 == 0) {
            var3 = var3 + "---- old accepted model ---- \n";
        }

        try {
            FileWriter var6 = new FileWriter(var0, true);
            Iterator var7 = var1.entrySet().iterator();

            J var4;
            while (var7.hasNext()) {
                for (Iterator var8 = ((List) ((Entry) var7.next()).getValue()).iterator(); var8.hasNext(); var3 = var3 + var4.a() + "\t" + var4.c() + "\t" + var4.d() + "\n") {
                    var4 = (J) var8.next();
                }
            }

            var6.write(var3);
            var6.write("-----\n\n");
            var6.close();
        } catch (IOException var5) {
            System.out.println("[Gibbs Sampler] failed to open Action file");
            System.exit(0);
        }
    }

    private static void a(String var0, Map var1) {
        String var2 = "";

        try {
            FileWriter var6 = new FileWriter(var0, false);
            Iterator var7 = var1.entrySet().iterator();

            J var4;
            while (var7.hasNext()) {
                for (Iterator var3 = ((List) ((Entry) var7.next()).getValue()).iterator(); var3.hasNext(); var2 = var2 + var4.a() + "\t" + var4.c() + "\t" + var4.d() + "\n") {
                    var4 = (J) var3.next();
                }
            }

            var6.write(var2);
            var6.close();
        } catch (IOException var5) {
            System.out.println("[Gibbs Sampler] failed to open Action file");
            System.exit(0);
        }
    }

    public static void main(String[] var0) {
        System.out.println("args[0]: " + var0[0]);
        AndroidAppFSM var1;
        if (var0[0].equals("--test")) {
            System.out.println("start mcmc sampling");
            ConfigOptions.b(var0[1]);
            ConfigOptions.c(/*restoreFSMFromFile.increaseExecutionCount*/ConfigOptions.MCMCSamplingOutputConfig);
            (var1 = new AndroidAppFSM((byte) 0)).restoreFSMFromFile(ConfigOptions.FSMFilePath/*restoreFSMFromFile.j*/ + "/FSM.txt");
            var1.f();
            TestManager.a().a(var1);
            a().c(var1);
        } else {
            if (var0[0].equals("--compare")) {
                System.out.println("compare mcmc sampling");
                ConfigOptions.c("CONF.txt");
                (var1 = new AndroidAppFSM((byte) 0)).restoreFSMFromFile(ConfigOptions.FSMFilePath/*restoreFSMFromFile.j*/ + "/FSM.txt");
                var1.f();
                TestManager.a().a(var1);
                a().b(var1);
            }

        }
    }
}
