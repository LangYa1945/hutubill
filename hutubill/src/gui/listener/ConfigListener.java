package gui.listener;
 
import java.awt.event.ActionEvent;
 
import java.awt.event.ActionListener;
import java.io.File;
 
import javax.swing.JOptionPane;
 
import gui.panel.ConfigPanel;
import service.ConfigService;
import util.GUIUtil;
//�����������ڸ��°�ť��
public class ConfigListener implements ActionListener{
 
    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel p = ConfigPanel.instance;
        if(!GUIUtil.checkNumber(p.tfBudget, "����Ԥ��"))//�ж������Ԥ��ֵ�Ƿ�������
            return;
        String mysqlPath =p.tfMysqlPath.getText();
        if(0!=mysqlPath.length()){
            File commandFile = new File(mysqlPath,"bin/mysql.exe");
            if(!commandFile.exists()){
            	//�ж������MYSQL��װ·���Ƿ���ȷ�� �жϰ취�ǿ�·����Ӧ��bin��Ŀ¼���Ƿ���mysql.exe�ļ�����
                JOptionPane.showMessageDialog(p, "Mysql·������ȷ");
                p.tfMysqlPath.grabFocus();
                return;
            }
        }
        //���������ж϶�ͨ���ˣ���ô�͵���ҵ����ConfigService�������ݸ���
        ConfigService cs= new ConfigService();
        cs.update(ConfigService.budget,p.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,mysqlPath);
         
        JOptionPane.showMessageDialog(p, "�����޸ĳɹ�");
 
    }
 
}