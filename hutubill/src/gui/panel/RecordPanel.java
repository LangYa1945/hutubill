package gui.panel;
 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
import org.jdesktop.swingx.JXDatePicker;
 
import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;
//这个面板采用BorderLayout 分为北面和中间。
//北面是一个JPanel，中间也是一个JPanel。
//北面的JPanel使用4行2列的 GridLayout。
//中间的JPanel使用默认的FlowLayout

public class RecordPanel extends WorkingPanel {
    static{//设置皮肤
        GUIUtil.useLNF();
    }
    public static RecordPanel instance = new RecordPanel();
 
    JLabel lSpend = new JLabel("花费(￥)");
    JLabel lCategory = new JLabel("分类");
    JLabel lComment = new JLabel("备注");
    JLabel lDate = new JLabel("日期");
 
    public JTextField tfSpend = new JTextField("0");
    
    //CategoryComboBoxModel实现了ComboBoxModel接口
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datepick = new JXDatePicker(new Date());
     
    JButton bSubmit = new JButton("记一笔");
 
    public RecordPanel() {//构造方法
    	//给标签和按钮分别上灰色和淡蓝色
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
        JPanel pInput =new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4,2,gap,gap));
         
        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datepick);
         
        pSubmit.add(bSubmit);
         
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);
         
        addListener();
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }
 
    public Category getSelectedCategory(){//提供getSelectedCategory()用于获取当前选中的分类对象。
        return (Category) cbCategory.getSelectedItem();
    }
 
    @Override
    public void updateData() {// updateData()更新数据，主要是更新下拉框中的分类信息，并且让各个输入框信息重置，以及让焦点停留在金额的输入框上
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }
     
    public void resetInput(){
        tfSpend.setText("0");
        tfComment.setText("");
        if(0!=cbModel.cs.size())
            cbCategory.setSelectedIndex(0);
        datepick.setDate(new Date()); //用于显示当前日期。
    }   
 
    @Override
    public void addListener() {
        // TODO Auto-generated method stub
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }
 
}