package lgv.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandPrompt {

    Process process;
    ProcessBuilder builder;

    /**
     * This method run command on windows and mac as a single command
     * @param command to run
     */
    public String runCommand(String command) throws InterruptedException, IOException {
        runByOS(command);
        return printCommandLog();
    }

    /**
     * This method run command on windows and mac as shell script
     * @param command to run
     */
    public String runCommand(String[] command) throws InterruptedException, IOException {
        runByOS(command);
        return printCommandLog();
    }

    /**
     * Run command for UI automation in XCode
     * @param command to run
     */
    public String runCommandForUiAutomationInXcode(String command) throws InterruptedException, IOException {
        runByOS(command);

        // get std output
        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line="";
        String allLine="";
        int i=1;
        while((line=r.readLine()) != null){

            if(line.contains("An error occurred while trying to run the script")) {
                Log.errorAndStop( line);
            }else if(line.toLowerCase().contains("error: ") ||
                    line.toLowerCase().contains("fail: ") ) {
//                IOSConfig.errorFlag = true;
                Log.error( line);
            }else{
                Log.debug( line);
            }
            allLine=allLine+""+line+"\n";
            i++;
        }
        return allLine;
    }

    /**
     * Run command for UI automation in XCTest
     * @param command to run
     */
    public String runCommandForUIXCTest(String command) throws InterruptedException, IOException {
        String line="";
        String allLine="";
        int i=1;

        runByOS(command);

        // get std output
        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));

        while( (line=r.readLine()) != null){

            Log.debug( line);
            allLine=allLine+""+line+"\n";
            i++;
        }
        return allLine;
    }

    /**
     * Run command for UI automation in XCode
     * @param command to run
     */
    public String getCPToken(String[] command) throws InterruptedException, IOException {
        runByOS(command);
        String line="";
        String allLine="";
        String token="";
        int i=1;

        // get std output
        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));

        while((line=r.readLine()) != null){

            allLine=allLine+""+line+"\n";
            if(line.contains("token:")){
                token = line.replace("token:", "");
                break;
            }
            i++;
        }
        return token;
    }

    /**
     * This method run command on windows and mac as shell script
     * @param command to run
     */
    private void runByOS(String[] command) throws InterruptedException, IOException{
        String os = System.getProperty("os.name");
        // build cmd proccess according to os
        if(os.contains("Windows")) // if windows
        {
            builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Thread.sleep(1000);
            process = builder.start();
        }
        else {
            // If Mac
            Log.debug("Run command: \n" + command);
            process = Runtime.getRuntime().exec(command);
        }
    }

    /**
     * This method run command on windows and mac as a single command
     * @param command to run
     */
    private void runByOS(String command) throws InterruptedException, IOException{
        String os = System.getProperty("os.name");
        Log.debug("Run on OS : " + os);
        // build cmd proccess according to os
        if(os.contains("Windows"))
        {
            builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Thread.sleep(1000);
            process = builder.start();
        }
        else {
            //Mac, Linux
            Log.debug("Run command: \n" + command);
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
        }
    }

    /**
     * Get command response
     */
    private String printCommandLog() throws InterruptedException, IOException{
        // get std output
        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line="";
        String allLine="";
        int i=1;
        while((line=r.readLine()) != null){

            allLine=allLine+""+line+"\n";
            if(line.contains("Console LogLevel: debug"))
                break;
            i++;
        }
        process.destroy();
        return allLine;
    }
}
