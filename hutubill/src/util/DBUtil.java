package util;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class DBUtil {
	//定义了很多和数据库连接相关的信息，如果账号密码发生改变，修改起来很容易
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "hutubill";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "18767173201";
    static String timezone = "GMT%2B8";
    static String useSSL = "FALSE";
    static{
        try {//驱动初始化放在了静态初始化块里，因为这行代码需要先执行，而且只需要执行一次就足够了。
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  //提供了一个静态的public的getConnection方法获取连接
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=%s&useSSL=%s", ip, port, database, encoding,timezone,useSSL);
        return DriverManager.getConnection(url, loginName, password);
    }
}