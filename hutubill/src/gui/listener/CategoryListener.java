package gui.listener;
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JOptionPane;
 
import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

public class CategoryListener implements ActionListener {
 
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel p = CategoryPanel.instance;
 
        JButton b = (JButton) e.getSource();
        if (b == p.bAdd) {//校验输入内容不为空后，通过CategoryService.add()添加到数据库
            String name = JOptionPane.showInputDialog(null);
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
 
            new CategoryService().add(name);
 
        }
        if (b == p.bEdit) {//校验输入内容不为空后，根据CategoryPanel中getSelectedCategory()获取id, 然后再通过CategoryService.update更新到数据库
            Category c = p.getSelectedCategory();
            int id = c.id;
            String name = JOptionPane.showInputDialog("修改分类名称", c.name);
            if (0 == name.length()) {
                JOptionPane.showMessageDialog(p, "分类名称不能为空");
                return;
            }
 
            new CategoryService().update(id, name);
        }
        if (b == p.bDelete) {//判断是否有消费记录，如果有消费记录，则不能删除。 接着进行删除确认提示，确认后，通过CategoryService.delete()进行删除。
            Category c = p.getSelectedCategory();
            if (0 != c.recordNumber) {
                JOptionPane.showMessageDialog(p, "本分类下有消费记录存在，不能删除");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确认要删除？"))
                return;
 
            int id = c.id;
            new CategoryService().delete(id);
        }
        p.updateData();
    }
 
}