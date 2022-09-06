package org.expensive.layout.layout.dialog;

import javax.swing.*;
import java.awt.*;

/**
 * socket 对话框
 */
public class SocketPanelDialog extends JDialog {
    private JPanel jPanel;

    public void initPanel() {
        jPanel.setLayout(new GridLayout(2,1));

    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }
}
