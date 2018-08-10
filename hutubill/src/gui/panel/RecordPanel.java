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
//���������BorderLayout ��Ϊ������м䡣
//������һ��JPanel���м�Ҳ��һ��JPanel��
//�����JPanelʹ��4��2�е� GridLayout��
//�м��JPanelʹ��Ĭ�ϵ�FlowLayout

public class RecordPanel extends WorkingPanel {
    static{//����Ƥ��
        GUIUtil.useLNF();
    }
    public static RecordPanel instance = new RecordPanel();
 
    JLabel lSpend = new JLabel("����(��)");
    JLabel lCategory = new JLabel("����");
    JLabel lComment = new JLabel("��ע");
    JLabel lDate = new JLabel("����");
 
    public JTextField tfSpend = new JTextField("0");
    
    //CategoryComboBoxModelʵ����ComboBoxModel�ӿ�
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datepick = new JXDatePicker(new Date());
     
    JButton bSubmit = new JButton("��һ��");
 
    public RecordPanel() {//���췽��
    	//����ǩ�Ͱ�ť�ֱ��ϻ�ɫ�͵���ɫ
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
 
    public Category getSelectedCategory(){//�ṩgetSelectedCategory()���ڻ�ȡ��ǰѡ�еķ������
        return (Category) cbCategory.getSelectedItem();
    }
 
    @Override
    public void updateData() {// updateData()�������ݣ���Ҫ�Ǹ����������еķ�����Ϣ�������ø����������Ϣ���ã��Լ��ý���ͣ���ڽ����������
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
        datepick.setDate(new Date()); //������ʾ��ǰ���ڡ�
    }   
 
    @Override
    public void addListener() {
        // TODO Auto-generated method stub
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }
 
}