package com.zhwl.home_server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明：界面显示设定-- html 处理
 * 创建人：caishun
 * 创建时间：2016-11-25
 */
public class InterfaceHtmlUtil {

    /*
    * 删除回车/空格
    * */
    private static String mateBlank(String html){
        String regEx = "\\\\s*|\\t|\\r|\\n|  ";//匹配回车/空格
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(html);
        return matcher.replaceAll("");
     }

     /*
     * 删除 interface_edit_active 标签
     * */
    private static String mateP(String html){
        String regEx = "interface_edit_active";//匹配 interface_edit_active
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(html);
        return matcher.replaceAll("");
    }

    /*
    * 将 双引号 换为单引号
    * */
    private static String mateQuotes(String html){
        String regEx = "\"";//匹配 interface_edit_active
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(html);
        return matcher.replaceAll("\'");
    }

    /*
    * 处理html
    * */
    public static String ok(String html){

        html = mateBlank(html);
        html = mateQuotes(html);
        html = mateP(html);

        return html;
    }

    public static void main(String[] args){
        String html = InterfaceHtmlUtil.ok("\n" +
                "\n" +
                "                                    \n" +
                "\n" +
                "                                    \n" +
                "\n" +
                "                                    \n" +
                "\n" +
                "                                    <div class=\"ui-state-defaul interface_edit_control\"><p class=\"control_span\"><span>全宗单位</span>:</p><select name=\"\" id=\"1\" style=\"width: 706.938px;\"></select><p class=\"masking\" style=\"display: none;\"></p></div><div class=\"ui-state-defaul interface_edit_control\"><p class=\"control_span\"><span>档号</span>:</p><select name=\"\" id=\"0\" style=\"width: 706.938px;\"></select><p class=\"masking\" style=\"display: none;\"></p></div><div class=\"ui-state-defaul interface_edit_control\"><p class=\"control_span\"><span>归档年度</span>:</p><select name=\"\" id=\"2\" style=\"width: 706.938px;\"></select><p class=\"masking\" style=\"display: none;\"></p></div><div class=\"ui-state-defaul interface_edit_control interface_edit_active\" style=\"height: 62px;\"><p class=\"control_span\" style=\"line-height: 60px;\"><span>全宗号</span>:</p><textarea id=\"3\" style=\"width: 706.938px;\" readonly=\"\"></textarea><p class=\"masking\" style=\"line-height: 60px; display: none;\"></p></div><div class=\"ui-state-defaul interface_edit_control red_field\" style=\"height: 62px;\"><p class=\"control_span\" style=\"line-height: 60px;\"><span>保管期限</span>:</p><textarea id=\"4\" style=\"width: 706.938px;\"></textarea><p class=\"masking\" style=\"line-height: 60px; display: none;\"></p></div>");
        System.out.println(html);
    }
}
