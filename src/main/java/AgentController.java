//
// Source code recreated from A .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class AgentController implements Runnable {
    private int a;
    private Thread b;
    private String c;
    private List d;
    private static String e;

    public AgentController(String var1) {
        this.c = var1;
        this.a = E.Port;
        System.out.println("[AgentController] AgentController: create controller " + this.c + ", at Port: " + this.a);
        new ArrayList();
        this.d = new ArrayList();
    }

    public final void run() {
        System.out.println("[AgentController] AgentController: run controller " + this.c);

        try {
            this.c();
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }

    public final void a() {
        System.out.println("[AgentController] AgentController: start controller " + this.c);
        if (this.b == null) {
            this.b = new Thread(this, this.c);
        }

        this.b.start();
    }

    private void c() throws IOException{
        System.out.println("[AgentController] AgentController: the server is started ... ");
        ServerSocket var1 = new ServerSocket(this.a);

        try {
            Socket var2 = var1.accept();
            System.out.println("[AgentController] AgentController: A client connected to this server. ");

            try {
                BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.getInputStream()));
                PrintWriter var4 = new PrintWriter(var2.getOutputStream(), true);
                StringBuffer var6 = new StringBuffer("");

                while(true) {
                    while(true) {
                        var6.setLength(0);

                        String var5;
                        while((var5 = var3.readLine()) == null) {
                        }

                        System.out.println("[AgentController] AgentController: line = " + var5);
                        if (var5.equals("REQ_TS")) {
                            while(this.d.size() == 0) {
                                try {
                                    Thread.sleep(5000L);
                                } catch (InterruptedException var17) {
                                    var17.printStackTrace();
                                }
                            }

                            int var20 = this.d.size();
                            System.out.println("[AgentController] AgentController: total " + var20 + " test sequences");

                            for(int var7 = 0; var7 < var20; ++var7) {
                                ArrayList var8 = (ArrayList)this.d.get(var7);
                                String var9 = "";

                                String var10;
                                for(Iterator var21 = var8.iterator(); var21.hasNext(); var9 = var9 + var10 + "\n") {
                                    var10 = (String)var21.next();
                                }

                                var4.print(var9);
                                var4.println("END_TC");
                            }

                            var4.println("END_TS");
                            System.out.println("[AgentController] AgentController: the whole test suite has been sent.");
                            this.d.clear();
                        } else if (var5.equals("PULL_COV")) {
                            var5 = var3.readLine();
                            System.out.println("[AgentController] AgentController: the line coverage: " + var5);
                            e = var5;
                        } else if (var5.equals("STOP")) {
                            System.out.println("[AgentController] AgentController: stop mcmc sampling");
                            System.exit(0);
                        } else {
                            System.out.println("[AgentController] E: invalid message!!!");
                            System.exit(0);
                        }
                    }
                }
            } finally {
                var2.close();
            }
        } finally {
            var1.close();
        }
    }

    public final void a(List var1) {
        this.d.addAll(var1);
        System.out.println("[AgentController] AgentController: get the generated test suite from MCMCSampler, total " + this.d.size() + " tests.");
        e = "";
    }

    public final synchronized String b() {
        System.out.println("[AgentController] AgentController: the testManager is waiting for the code coverage from controller ...");
        System.out.println("[AgentController] AgentController: wait for line coverage result ... ");

        while(e.equals("")) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }

        return e;
    }
}
