package BuffWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BuffWriterImpl implements BuffWriter {

    private String _dir;
    private String _fileName;
    private String _data;

    @Override
    public void writeBufferedWriter() throws IOException {
        String errorPath = System.getProperty("user.dir") + "\\ERROR-CACHE\\ERROR-1.txt";
        String path = System.getProperty("user.dir") + _dir + _fileName + ".txt";
        File file = new File(path);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        try {
            br.write(_data + "\r\n");
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

    @Override
    public void setDir(String val) {
        _dir = val;
    }

    @Override
    public String getDir() {
        return _dir;
    }

    @Override
    public void setFileName(String fileName) {
        _fileName = fileName;
    }

    @Override
    public String getFileName() {
        return _fileName;
    }

    @Override
    public void setData(String data) {
        _data = data;
    }

    @Override
    public String getData() {
        return _data;
    }
}
