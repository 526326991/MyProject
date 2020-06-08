
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Properties;

public class TestTime {

    //默认时间
    private static String currentTime = "17:30:00";
    private static final String file = "info.properties";


    /*    public static String getCurrentTime() {
        return currentTime;
    }*/
    public static void setCurrentTime(String currentTime) {
        TestTime.currentTime = currentTime;
    }


    public static void main(String[] args) throws Exception {
        setTime();
        time();
    }

    private static void time() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long startTime = System.currentTimeMillis();
        LocalDate nowDate = LocalDate.now();
        String currentDate = nowDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String s = sb.append(currentDate).append(" ").append(currentTime).toString().substring(0, 19);
        long endTime = LocalDateTime.parse(s, formatter1)
                .atZone(
                        ZoneId.systemDefault()
                )
                .toInstant().toEpochMilli();
        print(startTime, endTime);
    }

    private static void setTime() throws Exception {
        File f = new File(System.getProperty("user.dir")+"/"+file);

        Properties prop = new Properties();
        //读取属性文件a.properties
        InputStream in = new BufferedInputStream(new FileInputStream(f));
        prop.load(in);     ///加载属性列表
        Iterator<String> it = prop.stringPropertyNames().iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (key.equals("time")){
                setCurrentTime(prop.getProperty(key));
            }
        }
        in.close();
    }


    public static void print(long startTime, long endTime) {
        long midTime = (endTime - startTime) / 1000;
        while (midTime > 0) {
            midTime--;
            long hh = midTime / 60 / 60 % 60;
            long mm = midTime / 60 % 60;
            long ss = midTime % 60;
            System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒 下班！！！");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
