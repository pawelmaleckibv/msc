package master.msc.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    File logFile;

    private Logger(String logName) {
        try {
            this.logFile = new File("C:\\logs\\" + logName + ".txt");
            if (!this.logFile.exists()) {
                this.logFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logTimes(String logType, String entityName, long startTime, long endTime, long totalTime){
        logF("%s for : %s started at: %s and ended at: %s, Total time equals : %s \n", logType, entityName, String.valueOf(startTime),
                String.valueOf(endTime), String.valueOf(totalTime));
    }

    public void logF(String text, String ... args){
        try {
            FileWriter fileWriter = new FileWriter(this.logFile, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf(text, args);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void singleLog(String entityName, long time){
        logF("%s started at: %s \n", entityName, String.valueOf(time));
    }

    static Map<String, Logger> instances = new HashMap<>();

    public static Logger getLogger(String loggerName) {
        Logger logger = instances.get(loggerName);
        if (logger == null) {
            logger = new Logger(loggerName);
            instances.put(loggerName, logger);
        }
        return logger;
    }


}
