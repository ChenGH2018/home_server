package com.zhwl.home_server.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import java.util.List;

public class SetMealAmountToExcel extends PoiUtil{

    @Override
    public HSSFCellStyle getCellStype(Integer rowIndex, Integer cellIndex) {
        HSSFCellStyle cellStype = super.getCellStype(rowIndex, cellIndex);
        if(rowIndex != 0 ){ //不是标题的话
            if(cellIndex == 6 || cellIndex == 8 ){//第2列--第7列
                    cellStype.setWrapText(true);    //设置自动换行
            }
        }
        return cellStype;
    }

    @Override
    public void setCellWidth() {
        super.setCellWidth();
        List<Integer[]> rowsLengthList = getRowsLengthList();
        sheet.setColumnWidth(1,(int)(23.14+0.71)*256);   //
        sheet.setColumnWidth(3,(int)(23.14+0.71)*256);
        for (Integer[] integers : rowsLengthList) {

            if(integers[6]>=30)  sheet.setColumnWidth(6,(int)(20+0.71)*256);
            if(integers[8]>=44)  sheet.setColumnWidth(8,(int)(20+0.71)*256);
        }
//        for(int i = 0;i<rowsLengthList.size();i++){
//            System.out.println(sheet.getRow(i).getHeight());
//        }
    }
}
