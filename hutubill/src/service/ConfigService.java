package service;
 
import dao.ConfigDAO;
import entity.Config;
 //ConfigService 设置业务类，这个类是监听器直接调用的类，然后再通过ConfigService去调用ConfigDAO。
public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath = "mysqlPath";
    public static final String default_budget = "5000";
 
    static ConfigDAO dao= new ConfigDAO();
    static{
        init();
    }
 
    public static void init(){//初始化预算和路径
        init(budget, default_budget);
        init(mysqlPath, "");
    }
     
    private static void init(String key, String value) {//根据key去查找，如果不存在，就使用value的值插入一条数据。
         
        Config config= dao.getByKey(key);
        if(config==null){
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }
 
    public String get(String key) {//根据键获取相应的值
        Config config= dao.getByKey(key);
        return config.getValue();
    }
     
    public void update(String key, String value){//更新键对应的值
        Config config= dao.getByKey(key);
        config.setValue(value);
        dao.update(config);     
    }
     
    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
     
}