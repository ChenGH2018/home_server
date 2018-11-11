package com.zhwl.home_server.util;

import java.util.*;

/**
 * 说明：界面显示设定--数据映射
 * 创建人：caishun
 * 创建时间：2016-11-10
 */
public final class InterfaceMapperUtil {

    private static final Map<String ,String> dataType = new HashMap<String, String>();//数据类型
    private static final Map<String,Map<String,String>> dataKey = new HashMap<String,Map<String ,String>>();//数据格式
    private static final Map<Integer,String> autofill = new HashMap<Integer,String>();//数据格式

    private InterfaceMapperUtil(){
    }

    static {

        dataType.put("INTEGER","整数型");
        Map<String,String> StValue = new HashMap<String, String>();
        StValue.put("INTEGER","");
        dataKey.put("整数型",StValue);

        dataType.put("STRING","字符型");
        Map<String,String> intValue = new HashMap<String, String>();
        intValue.put("STRING_","");
        intValue.put("STRING_99","99");
        intValue.put("STRING_999","999");
        intValue.put("STRING_9999","9999");
        intValue.put("STRING_99_99_99","99:99:99");
        intValue.put("STRING_9999_99_9999_99","9999.99-9999.99");
        intValue.put("STRING_9999_99_99_9999_99_99","9999.99.99-9999.99.99");
        dataKey.put("字符型",intValue);

        dataType.put("TIME","时间型");
        Map<String,String> timeValue = new HashMap<String, String>();
        timeValue.put("TIME_HH","HH");
        timeValue.put("TIME_HH_MM_POINT","HH.mm");
        timeValue.put("TIME_HH_MM_SS_POINT","HH.mm.ss");
        timeValue.put("TIME_HH_MM","HH:mm");
        timeValue.put("TIME_HH_MM_SS","HH:mm:ss");
        timeValue.put("TIME_HH_MM_POINT_LINE","HH-mm");
        timeValue.put("TIME_HH_MM_SS_LINE","HH-mm-ss");
        dataKey.put("时间型",timeValue);

        dataType.put("DATE_TIME","日期或时间型");
        Map<String,String> dateValue = new HashMap<String, String>();
        dateValue.put("DATA_YYYY","yyyy");

        dateValue.put("DATA_YYYY_MM_LINE","yyyy-MM");
        dateValue.put("DATA_YYYY_MM_DD_LINE","yyyy-MM-dd");
        dateValue.put("DATA_YYYY_MM_DD_LINE_HH","yyyy-MM-dd HH");
        dateValue.put("DATA_YYYY_MM_DD_LINE_HH_MM","yyyy-MM-dd HH-mm");
        dateValue.put("DATA_YYYY_MM_DD_LINE_HH_MM_SS","yyyy-MM-dd HH-mm-ss");

        dateValue.put("DATA_YYYY_YEAR","yyyy年");
        dateValue.put("DATA_YYYY_YEAR_MM","yyyy年MM月");
        dateValue.put("DATA_YYYY_YEAR_MM_DD","yyyy年MM月dd日");
        dateValue.put("DATA_YYYY_YEAR_MM_DD_HH","yyyy年MM月dd日 HH时");
        dateValue.put("DATA_YYYY_YEAR_MM_DD_HH_MM","yyyy年MM月dd日 HH时mm分");
        dateValue.put("DATA_YYYY_YEAR_MM_DD_HH_MM_SS","yyyy年MM月dd日 HH时mm分ss秒");

        dateValue.put("DATA_YYYY_MM_POINT","yyyy.MM");
        dateValue.put("DATA_YYYY_MM_DD_POINT","yyyy.MM.dd");
        dateValue.put("DATA_YYYY_MM_DD_POINT_HH","yyyy.MM.dd HH");
        dateValue.put("DATA_YYYY_MM_DD_POINT_HH_MM","yyyy.MM.dd HH.mm");
        dateValue.put("DATA_YYYY_MM_DD_POINT_HH_MM_SS","yyyy.MM.dd HH.mm.ss");

        dateValue.put("DATA_YYYYMMLINE","yyyyMM");
        dateValue.put("DATA_YYYYMMDD_LINE","yyyyMMdd");
        dateValue.put("DATA_YYYYMMDDLINE_HH","yyyyMMddHH");
        dateValue.put("DATA_YYYYMMDDLINEHHMM","yyyyMMddHHmm");
        dateValue.put("DATA_YYYYMMDDLINEHHMMSS","yyyyMMddHHmmss");
        dataKey.put("日期或时间型",dateValue);


        autofill.put(0,"操作者号");
        autofill.put(1,"操作者姓名");
        autofill.put(2,"操作者号和姓名");
        autofill.put(3,"当前日期");
        autofill.put(4,"当前时间");
        autofill.put(5,"当前日期和时间");
        autofill.put(6,"当前日期和时间和操作者号");
        autofill.put(7,"无间隔操作者号和当前日期和时间（用于产生唯一号）");
        autofill.put(8,"操作者号和当前日期时间");
    }

    /*
    * 数据映射： 数据类型 --> 数据格式
    * */
    private static final void putDataType(){

        /*dataType.put(0,"整数型");
            Map<Integer,String> StValue = new HashMap<Integer, String>();
            StValue.put(0,"");
        dataKey.put("整数型",StValue);

        dataType.put(1,"字符型");
            Map<Integer,String> intValue = new HashMap<Integer, String>();
            intValue.put(0,"99");
            intValue.put(1,"999");
            intValue.put(2,"9999");
            intValue.put(3,"99:99:99");
            intValue.put(4,"9999.99-9999.99");
            intValue.put(5,"9999.99.99-9999.99.99");
        dataKey.put("字符型",intValue);

        dataType.put(2,"日期或时间型");
            Map<Integer,String> dateValue = new HashMap<Integer, String>();
            dateValue.put(0,"HH");
            dateValue.put(1,"HH.mm");
            dateValue.put(2,"HH.mm.ss");
            dateValue.put(3,"HH:mm");
            dateValue.put(4,"HH:mm:ss");
            dateValue.put(5,"HH-mm");
            dateValue.put(6,"HH-mm-ss");
            dateValue.put(7,"yyyy");
            dateValue.put(8,"yyyy-MM");
            dateValue.put(9,"yyyy-MM-dd");
            dateValue.put(10,"yyyy-MM-dd HH");
            dateValue.put(11,"yyyy-MM-dd HH-mm");
            dateValue.put(12,"yyyy-MM-dd HH-mm-ss");
            dateValue.put(13,"yyyy年");
            dateValue.put(14,"yyyy年MM月");
            dateValue.put(15,"yyyy年MM月dd日");
            dateValue.put(16,"yyyy年MM月dd日 HH时");
            dateValue.put(17,"yyyy年MM月dd日 HH时mm分");
            dateValue.put(18,"yyyy年MM月dd日 HH时mm分ss秒");
            dateValue.put(19,"yyyy.MM");
            dateValue.put(20,"yyyy.MM.dd");
            dateValue.put(21,"yyyy.MM.dd HH");
            dateValue.put(22,"yyyy.MM.dd HH.mm");
            dateValue.put(23,"yyyy.MM.dd HH.mm.ss");
        dataKey.put("日期或时间型",dateValue);*/

    }

    /*
    * 数据映射： 自动填数标识 --> 数据
    * */
    private static final void putAutofill(){
        /*autofill.put(0,"操作者号");
        autofill.put(1,"操作者姓名");
        autofill.put(2,"操作者号和姓名");
        autofill.put(3,"当前日期");
        autofill.put(4,"当前时间");
        autofill.put(5,"当前日期和时间");
        autofill.put(6,"当前日期和时间和操作者号");
        autofill.put(7,"无间隔操作者号和当前日期和时间（用于产生唯一号）");
        autofill.put(8,"操作者号和当前日期时间");*/
    }


    /*
    * 获取数据类型
    * */
    public static List<PageData> getDataType(){
        Map<String , String> value = dataType;
        List<PageData> pList = new ArrayList<PageData>();

        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(value.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for(Map.Entry<String,String> mapping:list){
            PageData pd = new PageData();
            pd.put("key",mapping.getKey());
            pd.put("value",mapping.getValue());
            pList.add(pd);
            //System.out.println(mapping.getKey()+":"+mapping.getValue());
        }

        return pList;
    }

    /*
    * 获取数据格式
    * @param key
    * */
    public static List<PageData> getDataValue(String key){
        Map<String , String> value = dataKey.get(dataType.get(key));
        List<PageData> pList = new ArrayList<PageData>();

        List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>(value.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        try {
            if(value.size() != 0&&value != null){
                Iterator iter = value.entrySet().iterator();
                for(Map.Entry<String,String> mapping:list){
                    PageData pd = new PageData();
                    pd.put("key",mapping.getKey());
                    pd.put("value",mapping.getValue());
                    pList.add(pd);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return pList;
    }

    /*
    * 获取自动填数标识--列表
    * */
    public static List<PageData> getAutofill(){
        Map<Integer , String> value = autofill;
        List<PageData> pList = new ArrayList<PageData>();

        for(int i=0;i<value.size();i++){
            PageData pd = new PageData();
            pd.put("key",i);
            pd.put("value",value.get(i));
            pList.add(pd);
        }

        return pList;
    }

    /*
    * 获取数据类型--值
    * */
    public static String getDataTypeValue(String key){
        return dataType.get(key);
    }

    /*
    * 获取数据格式--值
    * */
    public static String getDataValueValue(String tkey,String vkey){
        //System.out.println(dataKey.get(tkey).get(vkey)+"++++++++++++++");
        /*return dataKey.get(tkey).get(vkey);*/
        return dataKey.get(tkey).get(vkey) == null ? "" : dataKey.get(tkey).get(vkey);
    }

    /*
    * 获取自动填数标识--值
    * */
    public static String getAutofillValue(Integer key){
        return autofill.get(key);
    }




    public static void main(String[] args){
        System.out.println(InterfaceMapperUtil.getAutofillValue(1));
    }

}
