package gui.panel;
 
import java.awt.BorderLayout;
 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
 
import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;
 
public class MainPanel extends JPanel {
    static {
        GUIUtil.useLNF();//设置水晶皮肤
    }
 
    public static MainPanel instance = new MainPanel();//单例模式，生成一个面板
                                       //后续的监听器访问这个容器里的组件的便利性
    public JToolBar tb = new JToolBar(); //工具栏
    public JButton bSpend = new JButton();//按钮为public，易于访问
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();
 
    public CenterPanel workingPanel;
 
    private MainPanel() {//置这些按钮的图标、文字和提示
 
        GUIUtil.setImageIcon(bSpend, "home.png", "消费一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");
 
        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategory);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);
 
        workingPanel = new CenterPanel(0.8);//工作面板，用于将来显示不同的功能面板。
 
        setLayout(new BorderLayout());
        add(tb, BorderLayout.NORTH);
        add(workingPanel, BorderLayout.CENTER);
         
        addListener();
    }
 
    private void addListener() {
        ToolBarListener listener = new ToolBarListener();
      //实例化一个ToolBarListener 监听器，工具栏上的按钮，都加上这么一个监听器对象即可。
        bSpend.addActionListener(listener);
        bRecord.addActionListener(listener);
        bCategory.addActionListener(listener);
        bReport.addActionListener(listener);
        bConfig.addActionListener(listener);
        bBackup.addActionListener(listener);
        bRecover.addActionListener(listener);
         
    }
 
    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);//显示这个面板实例，测试起来非常方便。 
    }
}