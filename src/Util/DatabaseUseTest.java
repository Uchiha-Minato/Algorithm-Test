package Util;

import testChinaRailway.Lines;
import testChinaRailway.LinesStation;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUseTest {

    public static Connection connection;
    private static final String username = "root";
    private static final String password = "pea123";

    public static void main(String[] args) throws SQLException{

        getDatabaseConnection();

        PreparedStatement pstmt;

 /*       String sql_insert1 = "insert into crlines(line_name) values(?)";
        pstmt = connection.prepareStatement(sql_insert1);
        pstmt.setString(1, String.valueOf(Lines.Beijing_Guangzhou));
        pstmt.execute();
        System.out.println("数据添加成功" + Lines.Beijing_Guangzhou);*/


        String query_sql = "select * from crlines order by line_id";
        pstmt = connection.prepareStatement(query_sql);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            String line = rs.getString("line_name");
            System.out.println(line);
        }

        String query_sql1 = "select * from stations where line_name=? order by distance desc";
        pstmt = connection.prepareStatement(query_sql1);
        pstmt.setString(1, String.valueOf(Lines.Beijing_HKWKowloog));
        rs = pstmt.executeQuery();

        ArrayList<LinesStation> stationsList = new ArrayList<>();
        while(rs.next()) {
            LinesStation station = new LinesStation(rs.getString("station_name"), rs.getInt("distance"));
            stationsList.add(station);
        }
        for (LinesStation linesStation : stationsList) {
            System.out.print(linesStation.getStation());
            System.out.print("\t");
            System.out.println(linesStation.getDistance());
        }

        System.out.println(Lines.LanZhou_LianYun);
        System.out.println(Lines.values().length);

        Lines[] list = Lines.values();
        //System.out.println(Arrays.toString(list));
        ArrayList<String> lines = new ArrayList<>();
        int row = list.length;
        if(row != 0) {
            for (Lines line: list) {
                lines.add(String.valueOf(line));
            }
        }
        System.out.println(lines);

        addEnumIntoDB(connection);

        connection.close();
    }

    public static void getDatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功...");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cr_lines?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",username,password);
            System.out.println("数据库连接成功...");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addEnumIntoDB(Connection connection) throws SQLException {
        String insert_sql = "insert into crlines(line_name) values(?)";
        Lines[] list = Lines.values();
        for(Lines line: list) {
            PreparedStatement pstmt = connection.prepareStatement(insert_sql);
            pstmt.setString(1, String.valueOf(line));
            pstmt.execute();
            System.out.println("添加成功, " + line);
        }
    }

}
