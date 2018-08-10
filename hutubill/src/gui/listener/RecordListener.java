package gui.listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
 
import javax.swing.JOptionPane;
 
import entity.Category;
import gui.panel.CategoryPanel;
import gui.panel.MainPanel;
import gui.panel.RecordPanel;
import gui.panel.SpendPanel;
import service.RecordService;
import util.GUIUtil;
 
public class RecordListener implements ActionListener {
//	1. 首先判断是否有消费分类信息，如果没有提示先增加消费分类
//	2. 输入的金额不能为0
//	3. 调用RecordService的add添加消费记录
//	4. 提示添加成功
//	5. 添加成功后，切换到消费一览
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p  =RecordPanel.instance;
        if(0==p.cbModel.cs.size()){
            JOptionPane.showMessageDialog(p, "暂无消费分类，无法添加，请先增加消费分类");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
         
        if(!GUIUtil.checkZero(p.tfSpend,"花费金额"))
            return;
        int spend = Integer.parseInt(p.tfSpend.getText());
        Category c = p.getSelectedCategory();
        String comment = p.tfComment.getText();
        Date d = p.datepick.getDate();
        new RecordService().add(spend, c, comment, d);
        JOptionPane.showMessageDialog(p, "添加成功");
         
        MainPanel.instance.workingPanel.show(SpendPanel.instance);
         
    }
 
}