package gui.listener;
 
import java.awt.event.ActionEvent;
 
import java.awt.event.ActionListener;
import java.io.File;
 
import javax.swing.JOptionPane;
 
import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;
//监听器是用在更新按钮上
public class ConfigListener implements ActionListener{
 
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if(!GUIUtil.checkNumber(p.tfBudget, "本月预算"))//判断输入的预算值是否是整数
            return;
        String mysqlPath =p.tfMysqlPath.getText();
        if(0!=mysqlPath.length()){
            File commandFile = new File(mysqlPath,"bin/mysql.exe");
            if(!commandFile.exists()){
            	//判断输入的MYSQL安装路径是否正确。 判断办法是看路径对应的bin子目录下是否有mysql.exe文件存在
                JOptionPane.showMessageDialog(p, "Mysql路径不正确");
                p.tfMysqlPath.grabFocus();
                return;
            }
        }
        //上述两个判断都通过了，那么就调用业务类ConfigService进行数据更新
        ConfigService cs= new ConfigService();
        cs.update(ConfigService.budget,p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,mysqlPath);
         
        JOptionPane.showMessageDialog(p, "设置修改成功");
 
    }
 
}