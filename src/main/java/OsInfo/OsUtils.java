package OsInfo;

import BuffWriter.BuffWriterImpl;
import java.io.IOException;
import oshi.*;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;

public class OsUtils {
    private static String OS = null;
    private static String PLATFORM = null;

    public static String getOsName() {
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS;
    }
    public static String startOsInfo() {
        if (PLATFORM == null) { PLATFORM = System.getProperty("os.arch"); }
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS + " " + PLATFORM;
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }
    public static boolean isUnix() {
        return getOsName().startsWith("Unix");
    }

    public static struct initialize() {
        return new struct(getOsName(), startOsInfo(), isWindows(), isUnix());
    }
    public static void writeToFile() {
        struct OSInfo = initialize();
        String data = String.format("""
                OS Name -> %s
                OS Platform -> %s
                Is Windows -> %s
                Is Unix %s
                Processor -> %s
                Architecture -> %s
                Amount of Processors -> %s""", OSInfo.OSName(), OSInfo.Platform(), OSInfo.windows(), OSInfo.unix(),
                System.getenv("PROCESSOR_IDENTIFIER"), System.getenv("PROCESSOR_ARCHITECTURE"),
                System.getenv("NUMBER_OF_PROCESSORS"));
        String dir = "\\ArrayListDir\\";
        String fileName = "ArrayList";
        BuffWriterImpl writer = new  BuffWriterImpl();
        writer.setData(data);
        writer.setDir(dir);
        writer.setFileName(fileName);
        try {
            writer.writeBufferedWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printSysInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        ComputerSystem computerSystem = hal.getComputerSystem();

        System.out.printf("""
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s
                        %s%n""",
                cpu.getLogicalProcessorCount(),
                cpu.getMaxFreq(),
                cpu.getProcessorIdentifier().getName(),
                cpu.getProcessorIdentifier().getFamily(),
                cpu.getProcessorIdentifier().getIdentifier(),
                cpu.getProcessorIdentifier().getProcessorID(),
                cpu.getProcessorIdentifier().getMicroarchitecture(),
                cpu.getProcessorIdentifier().getVendor(),
                cpu.getPhysicalProcessorCount(),
                computerSystem.getManufacturer(),
                computerSystem.getModel()
                );
    }
}

record struct(String OSName, String Platform, boolean windows, boolean unix) {
}