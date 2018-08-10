package startup;
 
import javax.swing.SwingUtilities;
 
import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;
 
public class Bootstrap {
    public static void main(String[] args) throws Exception{
        GUIUtil.useLNF();
 
        SwingUtilities.invokeAndWait(new Runnable() {//����ͼ�ν���
            @Override
            public void run() {
            	//��ʾ�����壬����������·���workingPanel��ʾ����һ��Panel
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}