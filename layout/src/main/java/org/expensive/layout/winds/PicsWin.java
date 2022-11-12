package org.expensive.layout.winds;

import lombok.extern.slf4j.Slf4j;
import org.expensive.layout.jframe.GlobalFrame;
import org.expensive.service.ImageFinderService;
import org.expensive.service.impl.ImageFinderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    private static final Logger log = LoggerFactory.getLogger(PicsWin.class);
    private int width;
    private int height;
    @Autowired
    private ImageFinderService imageFinderService;

    public PicsWin() {
        //  set default close event
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        width = 860;
        height = 460;
        getRootPane().setSize(width, height);
        initPanel();
        pack();
    }

    public void initPanel() {
        //
        JTextField pageAddressInput = new JTextField("", 20);
        getRootPane().add(pageAddressInput);

        JButton confirmBtn = new JButton("Search");
        confirmBtn.addActionListener(click -> {
            try {
                String localPath = imageFinderService.getImagesPageFromWebsite(pageAddressInput.getText());
                String[] imgs = imageFinderService.filterImagesFromFile(localPath);
                List<String> values = imageFinderService.filterSrcValueFromImgLabel(imgs);
                for (String url:values
                ) {
                    log.info(url);
                    imageFinderService.downloadRemoteImgFile(url, null, "");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getRootPane().add(confirmBtn);
        //  重置按钮
        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> pageAddressInput.setText("https://erogazo.info/archives/9164/10"));
        getRootPane().add(resetBtn);
        //
        getRootPane().setLayout(new FlowLayout());
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
            getRootPane().add(flowImage);
        }
    }
}
