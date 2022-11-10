package org.expensive.layout.winds;

import org.expensive.service.ImageFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 布局：
 * 顶部是输入框，输入要展示图片的网址页面
 * 下边是图片展览框，带滚动条
 * 每个图片有两个按钮“下载”、“删除”
 */
@Component
public class PicsWin extends JDialog {

    @Autowired
    private ImageFinderService imageFinderService;

    private PicsPanel picsPanel;

    public PicsPanel getPicsPanel() {
        return picsPanel;
    }

    public void setPicsPanel(PicsPanel picsPanel) {
        this.picsPanel = picsPanel;
    }

    public PicsWin(Frame owner) {
        super(owner);
        JTextField pageAddressInput = new JTextField("图片页面", 860);
        add(pageAddressInput);
        JButton confirmBtn = new JButton("Search");
        confirmBtn.addActionListener(click -> {
            try {
                String localPath = imageFinderService.getImagesPageFromWebsite(pageAddressInput.getText());
                String[] imgs = imageFinderService.filterImagesFromFile(localPath);
                for (String url:imgs
                     ) {
                    imageFinderService.downloadRemoteImgFile(url, null, "");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        add(confirmBtn);
        this.picsPanel = new PicsPanel();
        picsPanel.setLayout(new FlowLayout());
        add(picsPanel);
        pack();
    }

    /**
     * 填充
     * @param urls 图片的url列表
     */
    private void fillPictures(List<String> urls) throws MalformedURLException {
        for (String s:urls
        ) {
            JLabel flowImage = new JLabel();
            ImageIcon imageIcon = new ImageIcon(new URL(s));
            flowImage.setIcon(imageIcon);
            flowImage.setVerticalAlignment(JLabel.CENTER);
            flowImage.setHorizontalAlignment(JLabel.CENTER);
            picsPanel.getRootPane().add(flowImage);
        }
    }

    class PicsPanel extends JPanel {

        @Override
        public void setLayout(LayoutManager mgr) {
            super.setLayout(mgr);
        }
    }
}
