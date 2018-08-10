package service;
 
import dao.ConfigDAO;
import entity.Config;
 //ConfigService ����ҵ���࣬������Ǽ�����ֱ�ӵ��õ��࣬Ȼ����ͨ��ConfigServiceȥ����ConfigDAO��
public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "5000";
 
    static ConfigDAO dao= new ConfigDAO();
    static{
        init();
    }
 
    public static void init(){//��ʼ��Ԥ���·��
        init(budget, default_budget);
        init(mysqlPath, "");
    }
     
    private static void init(String key, String value) {//����keyȥ���ң���������ڣ���ʹ��value��ֵ����һ�����ݡ�
         
        Config config= dao.getByKey(key);
        if(config==null){
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }
 
    public String get(String key) {//���ݼ���ȡ��Ӧ��ֵ
        Config config= dao.getByKey(key);
        return config.getValue();
    }
     
    public void update(String key, String value){//���¼���Ӧ��ֵ
        Config config= dao.getByKey(key);
        config.setValue(value);
        dao.update(config);     
    }
     
    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
     
}