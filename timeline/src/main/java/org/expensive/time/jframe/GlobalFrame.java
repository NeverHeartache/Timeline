package org.expensive.time.jframe;

import org.expensive.time.layout.center.CenterLayoutComponent;
import org.expensive.time.layout.east.EastLayoutComponent;
import org.expensive.time.layout.north.NorthLayoutComponent;
import org.expensive.time.layout.south.SouthLayoutComponent;
import org.expensive.time.layout.west.WestLayoutComponent;

import javax.swing.*;
import java.awt.*;

public class GlobalFrame extends JFrame {
//    private static final int DEFAULT_WIDTH = 600;
//    private static final int DEFAULT_HEIGHT = 400;
//    private static final int X_Position = 781;
//    private static final int Y_Position = 292;
    private JMenuBar menuBar;
    private SouthLayoutComponent southLayout;
    private NorthLayoutComponent northLayout;
    private WestLayoutComponent westLayout;
    private EastLayoutComponent eastLayout;
    private CenterLayoutComponent centerLayout;

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
        southLayout = new SouthLayoutComponent();
        northLayout = new NorthLayoutComponent();
        westLayout = new WestLayoutComponent();
        eastLayout = new EastLayoutComponent();
        centerLayout = new CenterLayoutComponent();
        add(southLayout, BorderLayout.SOUTH);
        add(northLayout, BorderLayout.NORTH);
        add(westLayout, BorderLayout.WEST);
        add(eastLayout, BorderLayout.EAST);
        add(centerLayout, BorderLayout.CENTER);
    }

    private void setApplicationProperties() {
        setTitle("Timeline!Time to have a rest!");
//        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
//        setLocation(X_Position, Y_Position);
        setExtendedState(JFrame.MAXIMIZED_BOTH);// JFrame最大化
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addJMenuBar() {
        Font menuFont = new Font("Helvetica", Font.CENTER_BASELINE, 24);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // "File" menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.setFont(menuFont);
        fileMenu.addSeparator();
        JMenuItem existMenuItem = new JMenuItem("Exist");
        existMenuItem.setFont(menuFont);
        existMenuItem.addActionListener(clickEvent -> {
            System.exit(0);
        });
        fileMenu.add(existMenuItem);
        //  "Edit" menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setFont(menuFont);
        editMenu.addSeparator();
        menuBar.add(editMenu);


    }


}
