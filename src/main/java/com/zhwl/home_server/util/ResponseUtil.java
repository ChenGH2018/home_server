package com.zhwl.home_server.util;

import javax.servlet.http.HttpServletResponse;

/**
 * response工具类
 */
public class ResponseUtil {

    public static void initResponseExportExcel(HttpServletResponse response, String fileName) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/msexcel");
        fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    }
}
