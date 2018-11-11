package com.zhwl.home_server.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/24.
 */
public class InterfaceUtil {

    private static final Map<Integer,String> ch = new HashMap<Integer,String>();//中文字段
    private static final Map<Integer,String> en = new HashMap<Integer,String>();//英文字段

    private List<PageData> chList = new ArrayList<PageData>();
    private List<PageData> enList = new ArrayList<PageData>();

    public InterfaceUtil(List<PageData> varList){
        int i = 0;
        for (PageData pd : varList){
            PageData chpd = new PageData();
            PageData enpd = new PageData();
            chpd.put("key",i);
            chpd.put("value",pd.get("F_COLUMN_CH"));
            enpd.put("key",i);
            enpd.put("value",pd.get("F_COLUMN_EN"));
            chList.add(chpd);
            enList.add(enpd);
            i++;
        }
        putch(chList);
        puten(enList);
    }

    public static Map<Integer,String> putch(List<PageData> chList){
        for(int i=0; i<chList.size() ; i++){
            ch.put(i,chList.get(i).getString("F_COLUMN_CH"));
        }
        return ch;
    }

    public static Map<Integer,String> puten(List<PageData> enList){
        for(int i=0; i<enList.size() ; i++){
            en.put(i,enList.get(i).getString("F_COLUMN_EN"));
        }
        return en;
    }

    public List<PageData> getChList(){
        return chList;
    }

    public List<PageData> getEnList(){
        return enList;
    }

    public String getChValue(Integer key){
        return ch.get(key);
    }

    public String getEnValue(Integer key){
        return en.get(key);
    }
}
