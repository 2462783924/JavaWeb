package util;

import java.sql.*;

public class DBConnection {
        private static String url = "jdbc:mysql://localhost:3306/school?user=root&password=a2462783924&characterEncoding=utf-8&useSSL=false";
        private static String drivername = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(drivername);
        } catch (ClassNotFoundException var1) {
            var1.printStackTrace();
        }

    }

    public DBConnection() {
    }

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("连接成功");
            return conn;
        } catch (SQLException var1) {
            System.out.println("conn连接失败");
            return null;
        }
    }

    public static void free(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException var67) {
            var67.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException var65) {
                var65.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException var64) {
                    var64.printStackTrace();
                }

            }

        }

    }
}
