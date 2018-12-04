package com.zhwl.home_server.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create by chenguihao
 * note：集合处理工具类
 */
public class CollectionUtil {

    /**
     * 数组转可增减元素的List（Arrays.asList()方法返回的是抽象List不可以add/remove
     */
    public static<T>  List<T> arrayToList(T[] array){
        ArrayList<T> arrayList = new ArrayList<>();
        Arrays.stream(array).forEach(Element->arrayList.add(Element));
        return arrayList;
    }
    public static String[] listToArray(List<String> list){
        String[] strings = new String[list.size()];
        return list.toArray(strings);
    }
}
