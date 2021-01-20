package Functions;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SBuildArray {
    private final StringBuilder msg;

    private String Smsg = "";
    private String msgWithPun = "";

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

    public void setMsg(String userInput) {
        this.Smsg = userInput;
    }

    public String getSMsg() {
        return Smsg;
    }
    public void setMsgWithPun(String userInput) {
        this.msgWithPun = userInput;
    }

    public String getMsgWithPun() {
        return msgWithPun;
    }

    public String[] splitMsg() {
        return Smsg.split(" ");
    }

    public String replacePunctuation(String userIn) {
        String[] charSet = {",", ".", "`", "!", "@", "#", "$", "%", "^","&", "*", "(", ")", "_", "+", "{", "}", "|", ":", "\"", "<",
                ">", "?", "~", "-", "=", ";"};
        for (String s : charSet) {
            userIn = userIn.replace(s, "");
        }
        return userIn;
    }

}