package gui.panel;
 
import java.awt.BorderLayout;
 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;
//用BorderLayout,分居中和南面。
//居中使用的是JScrollPane，其中放着JTable。
//南面使用一个JPanel，里面放3个按钮。
public class CategoryPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }
 
    public static CategoryPanel instance = new CategoryPanel();
 
    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    String columNames[] = new String[] { "分类名称", "消费次数" };
 
    public CategoryTableModel ctm = new CategoryTableModel();
    public JTable t = new JTable(ctm);
 
    public CategoryPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);
        JScrollPane sp = new JScrollPane(t);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);
 
        this.setLayout(new BorderLayout());
        this.add(sp, BorderLayout.CENTER);
        this.add(pSubmit, BorderLayout.SOUTH);
         
        addListener();
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
 
    public Category getSelectedCategory() {//方便获取JTable上当前选中的Category对象
        int index = t.getSelectedRow();
        return ctm.cs.get(index);
    }
     
    public void updateData() {//增加一个updateData方法，用于在增加，删除，和修改之后，更新表格中的信息，并默认选中第一行。 
        ctm.cs = new CategoryService().list();
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0, 0);
         
        if(0==ctm.cs.size()){//如果表格里没有数据，删除和修改按钮不可使用。
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }   
     
    public void addListener() {
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }
 
}