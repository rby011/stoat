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
import java.util.Arrays;

public class SocketServer {
    private static int a = 1;
    private int b;
    private String c;
    private String d;
    private String e;

    public SocketServer() {
    }

    private void a(int var1) throws IOException {
        System.out.println("[SocketServer] AgentController: the server is started ...waiting at Port " + var1);
        ServerSocket var13 = new ServerSocket(var1);

        try {
            while (true) {
                Socket var2 = var13.accept();
                System.out.println("[SocketServer] AgentController: A client connected to this server. ");

                try {
                    BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.getInputStream()));
                    PrintWriter var4 = new PrintWriter(var2.getOutputStream(), true);
                    System.out.println("[SocketServer] AgentController: received an app state from [a3e]: ");
                    StringBuffer var6 = new StringBuffer("");

                    label221:
                    while (true) {
                        while (true) {
                            var6.setLength(0);

                            String var5;
                            while ((var5 = var3.readLine()) != null && !var5.contains("EOM")) {
                                var6.append(var5 + "\n");
                            }

                            if (!var5.equals("AVD_STATE_EOM")) {
                                if (var5.equals("ENTRY_ACTIVITY_EOM")) {
                                    E.i = var6.toString().replace("\n", "");
                                    System.out.println("[SocketServer] AgentController: get the entry activity: " + E.i);
                                    break label221;
                                }

                                if (var5.equals("UI_FILE_NAME_EOM")) {
                                    System.out.println("[SocketServer] AgentController: get the current UI file name");
                                    this.e = var6.toString().replace("\n", "");
                                    ++a;
                                } else if (var5.equals("PACKAGE_NAME_EOM")) {
                                    System.out.println("[SocketServer] AgentController: get the current package name ...");
                                    this.c = var6.toString().replace("\n", "");
                                } else if (var5.equals("ACTIVITY_NAME_EOM")) {
                                    this.d = var6.toString().replace("\n", "");
                                    System.out.println("[SocketServer] AgentController: parse the received UI file to view components ...");
                                    AndroidAppFSM.c().a(this.e, this.d);
                                } else if (var5.equals("ACTION_EOM")) {
                                    if (!(var5 = var6.toString().replace("\n", "")).equals("")) {
                                        System.out.println("[SocketServer] AgentController: an action is executed in [a3e], action id = " + var5);
                                        this.b = Integer.parseInt(var5.trim());
                                        AndroidAppFSM.c().a(this.e, this.b);
                                    } else {
                                        System.out.println("[SocketServer] AgentController: no action is executed in [a3e].");
                                    }
                                } else if (var5.equals("FINISH_EOM")) {
                                    System.out.println("[SocketServer] AgentController: detect invokable actions on the received app state: " + this.e);
                                    var5 = AndroidAppFSM.c().g(this.e);
                                    var4.print(var5);
                                    break label221;
                                }
                            } else if (var6.toString().contains("READY")) {
                                System.out.println("[SocketServer] AgentController: the android emulator is ready now. ");
                                a = 0;
                            } else if (var6.toString().contains("STOP")) {
                                System.out.println("[SocketServer] AgentController: close the server.");
                                var2.close();
                                var13.close();
                                System.exit(0);
                            }
                        }
                    }

                    System.out.println("-----------");
                    AndroidAppFSM.c().f(E.FSMOutputDir/*restoreFSMFromFile.f*/ + "/allstates.txt");
                    AndroidAppFSM.c().b(E.FSMOutputDir/*restoreFSMFromFile.f*/ + "/fsm_states_edges.txt");
                    AndroidAppFSM.c().c(E.FSMOutputDir/*restoreFSMFromFile.f*/ + "/app.gv");
                    AndroidAppFSM.c().d(E.FSMOutputDir/*restoreFSMFromFile.f*/ + "/FSM.txt");
                    var4.println("server: Goodbye!");
                    System.out.println("[SocketServer] AgentController: the server sends *Goodbye* to the [a3e] client, and close the connection with the [a3e] client. ");
                    System.out.println("[SocketServer] AgentController: the current executed events count: " + a + " maximum allowed events: " + E.MaxFSMBuildingEvent/*restoreFSMFromFile.updateTransition*/);
                    System.out.println("-----------");
                    System.out.println("\n\n");
                    var4.close();
                } finally {
                    var2.close();
                }
            }
        } finally {
            var13.close();
        }
    }

    public static void main(String[] args) {
        E.a(args[0]);
        E.c(/*restoreFSMFromFile.restoreFSMFromFile*/E.FSMOutputConfig);
        args = (String[]) Arrays.copyOfRange(args, 1, args.length);
        System.out.println("AgentController: start android app static analysis to detect actions.... ");
        if (E.StaticAnalysisResult/*restoreFSMFromFile.k*/) {
            (new AndroidAppAnalysis()).a(args);
        }

        B.a();
        B.c();
        System.out.println("AgentController: finish static analysis, there are total [" + B.a + "] statically inferred actions.");
        B.a();
        B.b();
        SocketServer var1 = new SocketServer();
        int var3 = E.Port/*restoreFSMFromFile.d*/;
        System.out.println("Port = " + var3);

        try {
            var1.a(var3);
        } catch (IOException var2) {
            System.out.println("Get app states exception!");
            var2.printStackTrace();
        }
    }
}
