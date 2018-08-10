package util;
 
import java.awt.Component;
import java.awt.Dimension;
 
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
import gui.panel.WorkingPanel;
 
public class CenterPanel extends JPanel {
 
    private double rate;// �������
    private JComponent c; // ��ʾ�����
    private boolean strech; // �Ƿ�����
 
    public CenterPanel(double rate, boolean strech) {
        this.setLayout(null);
        this.rate = rate;
        this.strech = strech;
    }
 
    public CenterPanel(double rate) {
        this(rate, true);
    }
 
    public void repaint() {//strech��true���ͻ�������������Ĵ�С����������Ĵ�С���ﵽ�����Ч��
    	//                  strech��false, ��ʹ�������preferredSize����������Ч����
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();
 
            if (strech)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);
 
            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
                    containerSize.height / 2 - c.getSize().height / 2);//ʹ�þ��Զ�λ�ķ�ʽ����������м�λ�á�
        }
        super.repaint();
    }
 
    public void show(JComponent p) {//�Ȱ���������е�������Ƴ���Ȼ����µ�����ӽ��������ҵ���updateUI���н�����Ⱦ��
    	
        this.c = p;
        Component[] cs = getComponents();
        for (Component c : cs) {
            remove(c);
        }
        add(p);
 
        if (p instanceof WorkingPanel)
            ((WorkingPanel) p).updateData();
 
        this.updateUI();//updateUI�ᵼ��swingȥ����repaint()������
    }
 
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(200, 200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85, true);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b = new JButton("abc");
        cp.show(b);
 
    }
 
}