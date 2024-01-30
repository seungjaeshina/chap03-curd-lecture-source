package com.ohgiraffers.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;


public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rest = null;

        try {
            pstmt = con.prepareStatement("SELECT actor_id, first_name FROM actor");

            rest = pstmt.executeQuery();

            while (rest.next()){
                System.out.println(rest.getString("actor_id") + "," + rest.getString("first_name"));
            }





        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(rest);
            close(pstmt);
            close(con);
        }


    }

}
