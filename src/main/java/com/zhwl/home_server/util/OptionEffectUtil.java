package com.zhwl.home_server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 说明：选项设置：作用--数据映射
 * 创建人：caishun
 * 创建时间：2016-11-10
 */
public class OptionEffectUtil {

    private static final Map<Integer,String> effectType = new HashMap<Integer, String>();//数据类型

    static {
        effectType.put(0,"操作者号");
        effectType.put(1,"操作者姓名");
        effectType.put(2,"操作者号和姓名");
        effectType.put(3,"当前日期");
        effectType.put(4,"当前时间");
        effectType.put(5,"当前日期和时间");
        effectType.put(6,"当前日期和时间和操作者号");
        effectType.put(7,"无间隔操作者号和当前日期和时间 （用于生产唯一号）");
        effectType.put(8,"操作者号和当前日期时间");
        effectType.put(9,"自动获取全总号");
        effectType.put(10,"自动获取全总名");
        effectType.put(11,"自动获取序号最大值");
    }

    public static Map<Integer,String> getEffectType(){
        return null;
    }

    public static List<PageData> getEffectToList(){
        List<PageData> pageDataList = new ArrayList<PageData>();
        for (int i =0; i<effectType.size() ; i++){
            PageData pd = new PageData();
            pd.put("key",i);
            pd.put("value",effectType.get(i));
            pageDataList.add(pd);
        }
        return pageDataList;
    }

    public static String getEffectTypeValue(Integer key){
        return effectType.get(key);
    }
}
