package OsInfo;

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
}