package util;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
  
public class DBUtil {
	//�����˺ܶ�����ݿ�������ص���Ϣ������˺����뷢���ı䣬�޸�����������
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "hutubill";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "18767173201";
    static String timezone = "GMT%2B8";
    static String useSSL = "FALSE";
    static{
        try {//������ʼ�������˾�̬��ʼ�������Ϊ���д�����Ҫ��ִ�У�����ֻ��Ҫִ��һ�ξ��㹻�ˡ�
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
  //�ṩ��һ����̬��public��getConnection������ȡ����
    public static Connection getConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=%s&useSSL=%s", ip, port, database, encoding,timezone,useSSL);
        return DriverManager.getConnection(url, loginName, password);
    }
}