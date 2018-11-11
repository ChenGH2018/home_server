package com.zhwl.home_server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/19.
 */
public class SettingMapperUtil {

    private static final Map<String,String> option = new HashMap<String, String>();//选项设置
    private static final Map<String,String> property = new HashMap<String, String>();//属性设置

    private List<PageData> opList = new ArrayList<PageData>();
    private List<PageData> pyList = new ArrayList<PageData>();


    public SettingMapperUtil (List<PageData> opList,List<PageData> pyList){
        this.opList = opList;
        this.pyList = pyList;
    }

    public static Map<String, String> putOption(List<PageData> opList){
        for(PageData pd : opList){
            option.put(pd.getString("F_ID"),pd.getString("F_OPTION_NAME"));
        }
        return option;
    }

    public static Map<String, String> putProperty(List<PageData> pyList){
        for(PageData pd : pyList){
            property.put(pd.getString("F_ID"),pd.getString("F_CLASSIFY_NAME"));
        }
        return property;
    }


    public static Map<String,String > getOption(){
        return option;
    }

    public static Map<String,String > getProperty(){
        return property;
    }

    public static void main(String[] args){
    }
}
