package BuffWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface BuffWriter {
    void writeBufferedWriter() throws IOException;

    void setDir(String val);
    String getDir();

    void setFileName(String fileName);
    String getFileName();

    void setData(String data);
    String getData();

    static String getErrorPath() {
        return System.getProperty("user.dir") + "\\ERROR-CACHE\\ERROR-1.txt";
    }

    static void writeBufferedWriter(String data, String getDirectory, String getFileName) throws IOException {

        String errorPath = getErrorPath();
        String path = System.getProperty("user.dir") + getDirectory + getFileName + ".txt";
        File file = new File(path);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        try {
            br.write(data + "\r\n");
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
            FileWriter fw = new FileWriter(errorPath, true);
            fw.write(String.valueOf(e));
            fw.close();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();

                FileWriter fw = new FileWriter(errorPath, true);
                fw.write(String.valueOf(e));
                fw.close();
            }
        }
    }
    public static String createFile(String getDir, String getName) throws IOException {
        String errorPath = System.getProperty("user.dir") + "\\ERROR-CACHE\\ERROR-1.txt";

        try {
            File myOBJ = new File(System.getProperty("user.dir") + getDir + getName + ".txt");
            if (myOBJ.createNewFile()) {
                System.out.println("File created: " +  myOBJ.getName());
                return "File Created";
            }
            else {
                return "File Exists";
            }
        }
        catch (IOException e) {
            e.printStackTrace();

            FileWriter fw = new FileWriter(errorPath, true); //Writes the error code to the error cache file
            fw.write(String.valueOf(e));
            fw.close();
            return "An error has occurred";
        }
    }
}
