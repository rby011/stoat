//
// Source code recreated from AndroidSDKDir .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public final class ConfigOptions {
    public static String AndroidSDKDir;     //  aList
    public static String AndroidLIBDir;     //  bList
    public static int MaxFSMBuildingEvent;  //  c
    public static int Port;                 //  setActionType
    public static String FSMOutputConfig;   //  restoreFSMFromFile
    public static String FSMOutputDir;      //  getActionType
    public static String MCMCSamplingOutputConfig;  //  increaseExecutionCount
    public static String MCMCSamplingOutputDir;     //  getExecutionCount
    public static String i;                 //  i
    public static String FSMFilePath;       //  j
    public static boolean StaticAnalysisResult;     //  k
    public static int MaxMCMCIteration;     //  l
    public static int MaxTestSuiteSize;     //  m
    public static int MaxTestSequenceLength;//  n
    public static int MaxTestSuiteComparision;      //  o
    public static int ProabilityModelMode;          //  p
    public static String ProabilityModelFileName;   //  q
    public static boolean r = true;
    public static boolean s = true;
    public static int t = 2;
    public static boolean u = true;

    public static void a(String var0) {
        FSMOutputDir = var0 + "/stoat_fsm_output";
        FSMOutputConfig = FSMOutputDir + "/CONF.txt";
    }

    public static void b(String var0) {
        MCMCSamplingOutputDir = var0 + "/stoat_mcmc_sampling_output";
        MCMCSamplingOutputConfig = MCMCSamplingOutputDir + "/CONF.txt";
    }

    public static void c(String cfgFileName) {
        System.out.println("config file name: " + cfgFileName);
        File var1 = new File(cfgFileName);

        while(!var1.exists()) {
            System.out.println("AppState: wait for the config file ");

            try {
                Thread.sleep(2000L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        try {
            FileInputStream fin = new FileInputStream(cfgFileName);
            InputStreamReader iReader = new InputStreamReader(fin, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(iReader);

            try {
                String var8;
                while((var8 = br.readLine()) != null) {
                    if (var8.contains("=")) {
                        String[] var9;
                        String var10;
                        if ((var10 = (var9 = var8.split("="))[0].trim()).equals("ANDROID_SDK_DIR")) {
                            AndroidSDKDir = var9[1].trim();
                            System.out.println("[ConfigOptions] AgentController: ANDROID_SDK_DIR = " + AndroidSDKDir);
                        } else if (var10.equals("ANDROID_LIB_DIR")) {
                            AndroidLIBDir = var9[1].trim();
                            System.out.println("[ConfigOptions] AgentController: ANDROID_LIB_DIR = " + AndroidLIBDir);
                        } else if (var10.equals("PORT")) {
                            Port = Integer.parseInt(var9[1].trim());
                            System.out.println("[ConfigOptions] AgentController: PORT = " + Port);
                        } else if (var10.equals("FSM_FILE_LOCATION")) {
                            FSMFilePath = var9[1].trim();
                            System.out.println("[ConfigOptions] AgentController: FSM_FILE_LOCATION = " + FSMFilePath);
                        } else if (var10.equals("STATIC_ANALYSIS")) {
                            StaticAnalysisResult = var9[1].trim().equals("yes");
                            System.out.println("[ConfigOptions] AgentController: DO_STATIC_ANALYSIS = " + StaticAnalysisResult);
                        } else if (var10.equals("MAX_MCMC_ITERATION")) {
                            MaxMCMCIteration = Integer.parseInt(var9[1].trim());
                            System.out.println("[ConfigOptions] AgentController: MAX_MCMC_ITERATION =  " + MaxMCMCIteration);
                        } else if (var10.equals("MAX_TEST_SUITE_SIZE")) {
                            MaxTestSuiteSize = Integer.parseInt(var9[1].trim());
                            System.out.println("[ConfigOptions] AgentController: MAX_TEST_SUITE_SIZE =  " + MaxTestSuiteSize);
                        } else if (var10.equals("MAX_TEST_SEQUENCE_LENGTH")) {
                            MaxTestSequenceLength = Integer.parseInt(var9[1].trim());
                            System.out.println("[ConfigOptions] AgentController: MAX_TEST_SEQUENCE_LENGTH =  " + MaxTestSequenceLength);
                        } else if (var10.equals("MAX_FSM_BUILDING_EVENTS")) {
                            MaxFSMBuildingEvent = Integer.parseInt(var9[1].trim());
                            System.out.println("[ConfigOptions] AgentController: MAX_FSM_BUILDING_EVENTS =  " + MaxFSMBuildingEvent);
                        } else if (var10.equals("MAX_TEST_SUITE_SIZE_COMPARE_MODEL")) {
                            MaxTestSuiteComparision = Integer.parseInt(var9[1].trim());
                            System.out.println("[ConfigOptions] AgentController: MAX_TEST_SUITE_SIZE_COMPARE_MODEL = " + MaxTestSuiteComparision);
                        } else if (var10.equals("PROB_MODEL_MODE")) {
                            ProabilityModelMode = Integer.parseInt(var9[1].trim());
                            System.out.println("[ConfigOptions] AgentController: PROB_MODEL_MODE = " + ProabilityModelMode);
                        } else if (var10.equals("PROB_MODEL_FILE_NAME")) {
                            ProabilityModelFileName = var9[1].trim();
                            System.out.println("[ConfigOptions] AgentController: PROB_MODEL_FILE_NAME = " + ProabilityModelFileName);
                        }
                    }
                }
                br.close();
            } catch (IOException ex) {
                System.out.println("[ConfigOptions] ConfigOptions: Error occurs when reading the configuration file. ");
                return;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("[ConfigOptions] ConfigOptions: Can not find the configuration file: CONF.txt! ");
            ex.printStackTrace();
        }

    }
}
