package util;
 
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class GUIUtil {
    private static String imageFolder = "e:/project/hutubill/img";
 
    public static void setImageIcon(JButton b, String fileName, String tip) {//����ť����ͼ����ı��Լ���ʾ����
        ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }
 
    public static void setColor(Color color, JComponent... cs) {//����������ǰ��ɫ
        for (JComponent c : cs) {
            c.setForeground(color);
        }
    }
 
    /**
     * 
     * @param p
     * @param strechRate �������1��ʾ����Ļ
     */
    public static void showPanel(JPanel p,double strechRate) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strechRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }
     
    public static void showPanel(JPanel p) {//Ĭ�ϱ���
        showPanel(p,0.85);
    }   
 
    public static boolean checkNumber(JTextField tf, String input) {//У��һ����������Ƿ������ָ�ʽ
        if (!checkEmpty(tf, input))//�п�
            return false;
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);//�ַ���תΪ����
            return true;
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, input + " ��Ҫ������");
            tf.grabFocus();
            return false;
        }
    }
 
    public static boolean checkZero(JTextField tf, String input) {//У��һ������������Ƿ�����
        if (!checkNumber(tf, input))//�ж�����
            return false;
        String text = tf.getText().trim();
 
        if (0 == Integer.parseInt(text)) {
            JOptionPane.showMessageDialog(null, input + " ����Ϊ��");
            tf.grabFocus();
            return false;
        }
        return true;
    }
 
    public static boolean checkEmpty(JTextField tf, String input) {//У��һ������������Ƿ��ǿ�
        String text = tf.getText().trim();//��ȥ�ո�
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " ����Ϊ��");
            tf.grabFocus();
            return false;
        }
        return true;
 
    }
 
    public static int getInt(JTextField tf) { //תΪ����
        return Integer.parseInt(tf.getText());
    }
 
    public static void useLNF() {//ʹ��ˮ��Ƥ����
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}