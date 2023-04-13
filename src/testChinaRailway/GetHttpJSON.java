package testChinaRailway;

import Util.SslUtil;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetHttpJSON {

    public GetHttpJSON() {

    }

    public GetHttpJSON(String trainDate, String fromStation, String toStation, int type){
        this.train_date = trainDate;
        this.from_station = fromStation;
        this.to_station = toStation;
        this.type = type;
    }

    private String train_date;
    private String from_station;
    private String to_station;
    private int type;
    //private String randCode;

    public String Get(){
        try{

            String url1 = "https://kyfw.12306.cn/otn/leftTicketPrice/query";
            String realURL = url1 + "?" + "leftTicketDTO.train_date=" + train_date
                    + "&leftTicketDTO.from_station=" + from_station
                    + "&leftTicketDTO.to_station=" + to_station
                    + "&leftTicketDTO.type=" + type
                    + "&randCode=";
            URL url = new URL(realURL);
            System.out.println(realURL);
            //在连接之前设置属性
            SslUtil.ignoreSsl();
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setConnectTimeout(1000);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);

            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null){
                result.append(line);
            }
            reader.close();
            conn.disconnect();
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
