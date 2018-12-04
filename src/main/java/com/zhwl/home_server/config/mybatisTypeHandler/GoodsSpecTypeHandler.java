package com.zhwl.home_server.config.mybatisTypeHandler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.zhwl.home_server.bean.specification.Specification;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodsSpecTypeHandler extends BaseTypeHandler<List<Specification>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Specification> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,JSONObject.toJSONString(parameter));
    }

    @Override
    public List<Specification> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (Strings.isNullOrEmpty(str))
            return null;
        return JSONObject.parseArray(str,Specification.class);
    }

    @Override
    public List<Specification> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (Strings.isNullOrEmpty(str))
            return null;
        return JSONObject.parseArray(str,Specification.class);
    }

    @Override
    public List<Specification> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (Strings.isNullOrEmpty(str))
            return null;
        return JSONObject.parseArray(str,Specification.class);
    }
}
