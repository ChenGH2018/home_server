package com.zhwl.home_server.util;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * By   chenguihao
 * Date:2018-09-23
 * note:    poi包装工具类，该类是类似于模板类，个性化定制给继承该类的子类重写，如果没有重写，默认按照普通格式导出。
 * 方法说明：
 * getHSSFWorkbook()是该类的主要方法，会对数据进行填充到POI对象中，填充每个元素时会调用getCellStype()方法进行样式设置。
 * 当所有内容填充完毕后会调用setCellWidth()方法进行列的宽度初始化。
 * getCellStype() 和 setCellWidth()是给子类重写再个性化的方法。如果不重写，会按照默认格式和宽度进行设定。
 */
@Getter
@Setter
public class PoiUtil {
    public HSSFWorkbook wb;
    public HSSFSheet sheet;
    //存放rows对应元素的长度描述List
    public List<Integer[]> rowsLengthList;
    public List<Object[]> rows;
    public Short titleFontSize = 12;
    public Short contentFontSize = 10;
    public HSSFCellStyle titleCellStyle;
    public HSSFCellStyle contentCellStyle;
    public HSSFFont titleFont;
    public HSSFFont contentFont;

    public PoiUtil() {
        this.wb = new HSSFWorkbook();
        this.sheet = wb.createSheet();
        this.titleCellStyle = this.getTitleCellStyle();
        this.contentCellStyle = this.getContentCellStyle();
    }

    public PoiUtil(HSSFWorkbook wb) {
        this.wb = wb;
    }

    /**
     * 将标题和内容解析成Excel对象并返回
     */
    public HSSFWorkbook getHSSFWorkbook(final List<Object[]> rows) {
        Objects.requireNonNull(rows);   //不能为空
        this.rows = rows;
        rowsLengthList = new ArrayList<>(rows.size());
        for (Object[] row : rows) {
            rowsLengthList.add(new Integer[row.length]);
        }
        //将rows的数据填充到HSSFWorkbook
        for (int i = 0; i < rows.size(); i++) { //i == 行在表格的下标
            HSSFRow row = sheet.createRow(i);
            Object[] objects = rows.get(i);
            for (int j = 0; j < objects.length; j++) {//j == 元素在行的下标
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(getCellStype(i, j));
                try {
                    if (objects[j] instanceof String) {   //填充数据到Excel对象
                        if (Strings.isNullOrEmpty((String) objects[j])) {
                            cell.setCellValue("无");
                            rowsLengthList.get(i)[j] = "无".getBytes("UTF-8").length;
                        } else {
                            cell.setCellValue((String) (objects[j]));
                            rowsLengthList.get(i)[j] = ((String) (objects[j])).getBytes("UTF-8").length;
                        }
                    } else if (objects[j] instanceof Date) {
                        cell.setCellValue((Date) (objects[j]));
                        rowsLengthList.get(i)[j] = ((Date) (objects[j])).toString().length();
                    } else if (objects[j] instanceof Integer) {
                        cell.setCellValue((Integer) (objects[j]));
                        rowsLengthList.get(i)[j] = ((Integer) (objects[j])).toString().getBytes("UTF-8").length;
                    } else if (objects[j] instanceof Double) {
                        cell.setCellValue((Double) (objects[j]));
                        rowsLengthList.get(i)[j] = ((Double) (objects[j])).toString().getBytes("UTF-8").length;
                    } else if (objects[j] == null) {
                        cell.setCellValue("空");
                        rowsLengthList.get(i)[j] = "空".getBytes("UTF-8").length;
                    } else {
                        throw new RuntimeException("格式转换错误、无法将数据填充到Excel对象中:" + objects[j]);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        setCellWidth();
        return wb;
    }

    //    public HSSFCellStyle getCellStype(Integer rowIndex, Integer cellIndex){
//        return initCellStype(rowIndex,cellIndex);
//    }
    public HSSFCellStyle getCellStype(Integer rowIndex, Integer cellIndex) {
        return rowIndex == 0 ? titleCellStyle:contentCellStyle;
    }

    public void setCellWidth() {
        //第一个for是要遍历所有的列
        for (int i = 0; i < rowsLengthList.get(0).length; i++) {//根据每一列最长字符串的长度设置列宽度
            int width = -1;
            //第二个for是要遍历所有的行
            for (int j = 0; j < rowsLengthList.size(); j++) {
                Double width0 = (double) ((rowsLengthList.get(j)[i]) * 256);
                if (j == 0) width0 *= 1.3;   //如果是标题则因为加粗和12字体，所以*1.3
                width = Math.max(width0.intValue(), width);
            }
            if (width != -1) sheet.setColumnWidth(i, width);
        }
    }

    public HSSFCellStyle getTitleCellStyle() {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //设置字体样式
        HSSFFont font = wb.createFont();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //垂直居中
        font.setFontName("Arial");
        font.setFontHeightInPoints(titleFontSize);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//标题加粗
        cellStyle.setFont(font);
        return cellStyle;

    }

    public HSSFCellStyle getContentCellStyle() {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        //设置字体样式
        HSSFFont font = wb.createFont();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //垂直居中
        font.setFontName("Arial");
        font.setFontHeightInPoints(contentFontSize);
        cellStyle.setFont(font);
        return cellStyle;
    }
}
