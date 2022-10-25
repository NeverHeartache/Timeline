package org.expensive.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    private static final String REG_SRC = "(src|SRC)=(\\\"|\\')(.*?)(\\\"|\\')";

    /**
     * 双引号里的正则匹配表达式，暂时未用
     *
     */
    private static final String REG_QUOTATION = "(\\\"|\\')(.*?)(\\\"|\\')";

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
        Pattern imgPatter;
        Matcher imgMatcher;
        String str = "";
        String[] images = null;
        imgPatter = Pattern.compile(REG_IMG, Pattern.CASE_INSENSITIVE);
        imgMatcher = imgPatter.matcher(html);
        while (imgMatcher.find()) {
            String tempSelected = imgMatcher.group();
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
     * @param imgLables img标签数组
     * @return src值的数组
     */
    public static List<String> getSrcOfImg(String[] imgLables) {
        //  pattern 声明正则表达式
        Pattern sPropPattern = Pattern.compile(REG_SRC, Pattern.CASE_INSENSITIVE);
        //  matcher
        Matcher sPropMatcher;
        List<String> lists = new ArrayList<>();
        for (String s : imgLables) {
            sPropMatcher = sPropPattern.matcher(s);
            if (sPropMatcher.find()) {
                String str_src = sPropMatcher.group();
                lists.add(str_src);
            }
        }
        return lists;
    }

    public static List<String> getValueOfSrc(String filePath) {
        //  文件非空判断
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) return null;
        //  从html文件中匹配所有的img标签内容
        String[] imageLabelArray = getImgs(filePath);
        //  正则表达式，用于匹配双引号的内容
        Pattern srcValuePattern = Pattern.compile("\"[^\"]*\"");
        Matcher srcValMatcher;
        List<String> srcValue = new ArrayList<>();
        try {
            //  从上述img标签中过滤出所有的src属性以及对应的值，形式为：src="xxx"
            List<String> imgLables = HtmlUtil.getSrcOfImg(imageLabelArray);
            for (String img : imgLables) {
                srcValMatcher = srcValuePattern.matcher(img);
                if (srcValMatcher.find())
                    srcValue.add(srcValMatcher.group());
            }
            srcValue.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srcValue;
    }

}
