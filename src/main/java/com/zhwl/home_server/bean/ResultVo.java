package com.zhwl.home_server.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by lin on 2018/1/29.
 */
public class ResultVo {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer status; //响应业务状态  1:成功;  0:失败

    private String msg;// 响应消息

    private Object data;//响应中的数据

    private Integer errorCode;  //失败后的错误码、新增于2018-11-12

    public ResultVo() {
    }

    public ResultVo(Object data) {
        this.status = 1;
        this.msg = "success";
        this.data = data;
    }

    //    public ResultVo(Integer status, String msg, Object data) {
//        this.status = status;
//        this.msg = msg;
//        this.data = data;
//    }
    public ResultVo(Integer status, Integer errorCode, String msg, Object data) {
        this.status = status;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVo ok() {
        return ok(null);
    }

    public static ResultVo ok(Object data) {
        return new ResultVo(data);
    }

    public static ResultVo ok(Integer status, String msg, Object data) {
        return new ResultVo(status, null, msg, data);
    }

    //用于即使失败也可以返回数据的场景
    public static ResultVo fail(Integer status, Integer errorCode, String msg, Object data) {
        return new ResultVo(status, errorCode, msg, data);
    }

    //失败只返回消息
    public static ResultVo fail(String msg) {
        return new ResultVo(0, 402, msg, null);
    }

    //失败返回错误码
    public static ResultVo fail(Integer errorCode, String msg) {
        return new ResultVo(0, errorCode, msg, null);
    }

    //失败返回数据
    public static ResultVo fail(Object data) {
        return new ResultVo(0, 402, "error", data);
    }

    public Boolean isOK() {
        return this.status == 1;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 将json结果集转化为ResultVo对象
     * @param jsonData json数据
     * @param clazz SysResult中的object类型
     * @return
     */
//    public static ResultVo formatToPojo(String jsonData, Class<?> clazz) {
//        try {
//            if (clazz == null) {
//                return MAPPER.readValue(jsonData, ResultVo.class);
//            }
//            JsonNode jsonNode = MAPPER.readTree(jsonData);
//            JsonNode data = jsonNode.get("data");
//            Object obj = null;
//            if (clazz != null) {
//                if (data.isObject()) {
//                    obj = MAPPER.readValue(data.traverse(), clazz);
//                } else if (data.isTextual()) {
//                    obj = MAPPER.readValue(data.asText(), clazz);
//                }
//            }
//            return fail(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ResultVo format(String json) {
        try {
            return MAPPER.readValue(json, ResultVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
//    public static ResultVo formatToList(String jsonData, Class<?> clazz) {
//        try {
//            JsonNode jsonNode = MAPPER.readTree(jsonData);
//            JsonNode data = jsonNode.get("data");
//            Object obj = null;
//            if (data.isArray() && data.size() > 0) {
//                obj = MAPPER.readValue(data.traverse(),
//                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
//            }
//            return fail(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
