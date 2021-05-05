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

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        ComputerSystem computerSystem = hal.getComputerSystem();

        struct OSInfo = initialize();
        String data = String.format("""
                OS Name -> %s
                OS Platform -> %s
                Processor -> %s
                CPU Name Alternative -> %s
                Architecture -> %s
                Core Count -> %s
                Logical Processors -> %s
                Manufacturer -> %s
                Model -> %s""", OSInfo.OSName(), OSInfo.Platform(),
                System.getenv("PROCESSOR_IDENTIFIER"), cpu.getProcessorIdentifier().getName(),
                System.getenv("PROCESSOR_ARCHITECTURE"), cpu.getPhysicalProcessorCount(),
                System.getenv("NUMBER_OF_PROCESSORS"), computerSystem.getManufacturer(),
                computerSystem.getModel());
        String dir = "\\ArrayListDir\\";
        String fileName = "ArrayList";
        BuffWriterImpl writer = new  BuffWriterImpl();
        writer.setData(data);
        writer.setDir(dir);
        writer.setFileName(fileName);
        System.out.printf("Writing to -> %s%s%s", System.getProperty("user.dir"), writer.getDir(), writer.getFileName());
        try {
            writer.writeBufferedWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

record struct(String OSName, String Platform, boolean windows, boolean unix) { }