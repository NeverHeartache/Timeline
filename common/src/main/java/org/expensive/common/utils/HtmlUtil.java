package org.expensive.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hilbert.xu
 */
public class HtmlUtil {

    /**
     * 过滤所有以html标签
     */
    private final static String REG_HTML = "<([^>]*)>";
    /**
     * img标签
     */
    private static final String REG_IMG = "(<img\\b.*?(?:\\>|\\/>))";

    /**
     * src的正则匹配表达式
     */
    private static final String REG_SRC = "/src=[\\\"|'](.*?)[\\\"|']/gi";

    /**
     * @param htmlStr
     * @return 删除Html标签
     */
    public static String delHTMLTag(String htmlStr) {
        Pattern p_html = Pattern.compile(REG_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        // 过滤html标签
        htmlStr = m_html.replaceAll("");
        return htmlStr;
    }

    /**
     * 获取完整img标签
     *
     * @param html
     * @return
     */
    public static String[] getImgs(String html) {
        Pattern p_image;
        Matcher m_image;
        String str = "";
        String[] images = null;
        p_image = Pattern.compile(REG_IMG, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(html);
        while (m_image.find()) {
            String tempSelected = m_image.group();
            if (StringUtils.isBlank(str)) {
                str = tempSelected;
            } else {
                String temp = tempSelected;
                str = str + "," + temp;
            }
        }
        if (StringUtils.isNotBlank(str)) {
            images = str.split(",");
        }
        return images;
    }

    /**
     * 过滤img标签中的src属性值
     * @param imgs img标签数组
     * @return src值的数组
     */
    public static String[] getSrcOfImg(String[] imgs) {
        Pattern s_prop = Pattern.compile(REG_SRC, Pattern.CASE_INSENSITIVE);
        String[] srcArray = null;
        for (String s : imgs) {

        }
        return null;
    }

}
