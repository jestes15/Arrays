package Functions;

import BuffWriter.BuffWriterImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SBuildArray {
    private final StringBuilder msg;

    private String string = "";
    private String msgWithPun = "";

    private String _dir;
    private String _fileName;

    public SBuildArray() {
        this.msg = new StringBuilder();
    }

    private StringBuilder getDate() {
        StringBuilder stringMsg = new StringBuilder();
        LocalDateTime time = LocalDateTime.now();
        int currentTimeMIN = time.getMinute();
        int currentTimeHOUR = time.getHour();
        int currentTimeDAY = time.getDayOfMonth();
        int currentTimeMONTH = time.getMonthValue();
        int currentTimeYEAR = time.getYear();
        stringMsg.append(currentTimeHOUR).append(":").append(currentTimeMIN).
                append(" on ").append(currentTimeMONTH).append("/").append(currentTimeDAY).append("/").
                append(currentTimeYEAR);
        return stringMsg;
    }

    public String getMsg() {
        return msg.toString();
    }

    public void initialize() {
        msg.append(getDate());
        msg.append("\n").append("======================================================\n");
    }

    public void append(String appendMsg) {
        msg.append(appendMsg);
    }

    public void append(String header, int[][] array) {
        msg.append(header);
        for (int[] ints : array) msg.append(Arrays.toString(ints)).append("\n");
    }

    public void setMsg(String string) {
        this.string = string;
    }
    public String getString() {
        return string;
    }

    public void setMsgWithPun(String userInput) {
        this.msgWithPun = userInput;
    }
    public String getMsgWithPun() {
        return msgWithPun;
    }

    public String[] splitMsg() {
        return string.split(" ");
    }

    public String replacePunctuation(String userIn) {
        String[] charSet = {",", ".", "`", "!", "@", "#", "$", "%", "^","&", "*", "(", ")", "_", "+", "{", "}", "|", ":", "\"", "<",
                ">", "?", "~", "-", "=", ";"};
        for (String s : charSet) {
            userIn = userIn.replace(s, "");
        }
        return userIn;
    }

    public void set_dir(String dir) {
        _dir = dir;
    }
    public String get_dir() {
        return _dir;
    }

    public void set_fileName(String fileName) {
        _fileName = fileName;
    }
    public String get_fileName() {
        return _fileName;
    }

    public void writeToFile() throws IOException {
        BuffWriterImpl writer = new BuffWriterImpl();
        writer.setFileName(get_fileName());
        writer.setDir(get_dir());
        writer.setData(getMsg());
        writer.writeBufferedWriter();
    }
}