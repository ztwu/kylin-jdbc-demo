package com.iflytek.edcc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with Intellij IDEA.
 * User: ztwu2
 * Date: 2017/12/1
 * Time: 15:18
 * Description
 */

public class KylinJdbcTest {

    public static void main(String[] args) throws Exception {

        System.out.println("加载驱动");
        Class.forName("org.apache.kylin.jdbc.Driver");
        System.out.println("数据库连接");
        Connection db = DriverManager.getConnection(
                "***",
                "***",
                "***");
        System.out.println("数据库连接成功");

        Statement st = db.createStatement();

        select(st);

        st.close();
    }

    /**
     * 查询数据
     * @param st
     * @throws Exception
     */
    private static void select(Statement st) throws Exception{
        ResultSet rs = st.executeQuery(
                "select city_id,count(distinct exam_id) from fact_exam_overview as t1\n" +
                        "left join dim_school as t2\n" +
                        "    on t1.school_id = t2.school_id\n" +
                        "where city_id = '1155'\n" +
                        "group by city_id");
        while (rs.next()) {
            System.out.println(rs.getString(1)+":"+rs.getLong(2));
        }
        rs.close();
    }

}
