package org.expensive.layout.jframe;

import org.expensive.common.utils.GlobalPropertiesUtil;
import org.expensive.layout.layout.center.CenterLayoutComponent;
import org.expensive.layout.layout.east.EastLayoutComponent;
import org.expensive.layout.layout.north.NorthLayoutComponent;
import org.expensive.layout.layout.south.SouthLayoutComponent;
import org.expensive.layout.layout.west.WestLayoutComponent;
import org.expensive.layout.winds.PicsPanel;
import org.expensive.layout.winds.PicsWin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class GlobalFrame extends JFrame {
    @Autowired
    private GlobalPropertiesUtil globalPropertiesUtil;
    @Autowired
    private PicsWin picsWin;
    @Autowired
    private PicsPanel picsPanel;

    public GlobalFrame() {
        //  菜单内容相关
        addJMenuBar();
        //  应用内容相关
        setApplicationProperties();
        //  应用内容布局相关
        initBorderLayout();
        //  进行程序打包；
        this.pack();
    }

    /**
     * border layout 布局相关；
     */
    private void initBorderLayout() {
        SouthLayoutComponent southLayout = new SouthLayoutComponent();
        NorthLayoutComponent northLayout = new NorthLayoutComponent();
        WestLayoutComponent westLayout = new WestLayoutComponent();
        EastLayoutComponent eastLayout = new EastLayoutComponent();
        CenterLayoutComponent centerLayout = new CenterLayoutComponent();

        add(southLayout, BorderLayout.SOUTH);
        add(northLayout, BorderLayout.NORTH);
        add(westLayout, BorderLayout.WEST);
        add(eastLayout, BorderLayout.EAST);
        add(centerLayout, BorderLayout.CENTER);
    }

    private void setApplicationProperties() {
        setTitle("是时候休息一下了，我的朋友！");
        setExtendedState(JFrame.MAXIMIZED_BOTH);// JFrame最大化
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addJMenuBar() {
        Font menuFont = new Font("Helvetica", Font.BOLD, 24);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // "File" menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.setFont(menuFont);
        fileMenu.addSeparator();
        JMenuItem existMenuItem = new JMenuItem("Exist");
        existMenuItem.setFont(menuFont);
        existMenuItem.addActionListener(clickEvent -> System.exit(0));
        fileMenu.add(existMenuItem);

        //  "Edit" menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(menuFont);
        editMenu.addSeparator();
        //
        menuBar.add(editMenu);

        //  "Tool" menu
        JMenu toolMenu = new JMenu("Tool");
        toolMenu.setFont(menuFont);
        toolMenu.addSeparator();
        //open socket
        JMenuItem caculator = new JMenuItem("open socket panel");
        caculator.addActionListener(click -> System.out.println("待使用计算器..."));
        toolMenu.add(caculator);
        //  open images menu item
        JMenuItem picWinMenuItem = new JMenuItem("open img dialog");
        picWinMenuItem.addActionListener(click -> {
//            picsPanel.initPanel();
//            //  set content pane
//            setContentPane(picsPanel);
            //  打开图片弹窗
            picsWin.setVisible(true);
        });
        toolMenu.add(picWinMenuItem);
        menuBar.add(toolMenu);
    }

    public GlobalPropertiesUtil getGlobalPropertiesUtil() {
        return globalPropertiesUtil;
    }

    public void setGlobalPropertiesUtil(GlobalPropertiesUtil globalPropertiesUtil) {
        this.globalPropertiesUtil = globalPropertiesUtil;
    }
}
