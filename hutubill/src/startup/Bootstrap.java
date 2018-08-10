package startup;
 
import javax.swing.SwingUtilities;
 
import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;
 
public class Bootstrap {
    public static void main(String[] args) throws Exception{
        GUIUtil.useLNF();
 
        SwingUtilities.invokeAndWait(new Runnable() {//启动图形界面
            @Override
            public void run() {
            	//显示主窗体，并让主面板下方的workingPanel显示消费一览Panel
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}