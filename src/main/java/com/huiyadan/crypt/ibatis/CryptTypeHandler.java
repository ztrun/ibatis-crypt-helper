package com.huiyadan.crypt.ibatis;

import com.ibatis.sqlmap.engine.type.BaseTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 只支持String类型的加解密
 *
 * @author huiyadanli
 */
public class CryptTypeHandler extends BaseTypeHandler {

    /**
     * Sets a parameter on a prepared statement
     *
     * @param ps        - the prepared statement
     * @param i         - the parameter index
     * @param parameter - the parameter value
     * @param jdbcType  - the JDBC type of the parameter
     * @throws SQLException if setting the parameter fails
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, String jdbcType) throws SQLException {
        ps.setString(i, EncryptUtil.encrypt((String) parameter));
    }

    /**
     * Gets a column from a result set
     *
     * @param rs         - the result set
     * @param columnName - the column name to get
     * @return - the column value
     * @throws SQLException if getting the value fails
     */
    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        return decrypt(rs.getString(columnName));
    }

    /**
     * Gets a column from a result set
     *
     * @param rs          - the result set
     * @param columnIndex - the column to get (by index)
     * @return - the column value
     * @throws SQLException if getting the value fails
     */
    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        return decrypt(rs.getString(columnIndex));
    }

    /**
     * Gets a column from a callable statement
     *
     * @param cs          - the statement
     * @param columnIndex - the column to get (by index)
     * @return - the column value
     * @throws SQLException if getting the value fails
     */
    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return decrypt(cs.getString(columnIndex));
    }

    /**
     * Converts the String to the type that this handler deals with
     *
     * @param s - the String value
     * @return - the converted value
     */
    @Override
    public Object valueOf(String s) {
        return s;
    }

    private String decrypt(String ori) {
        return EncryptUtil.decrypt(ori);
    }
}
