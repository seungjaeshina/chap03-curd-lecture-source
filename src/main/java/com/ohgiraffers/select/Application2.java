package com.ohgiraffers.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;


public class Application2 {
    private static String actorId = "100";
    private static String firstName = "' OR 1=1 AND actor_id = ' 200";

    public static void main(String[] args) {
        String query =
                "SELECT * FROM actor WHERE actor_id = ? AND first_name = ?";
        System.out.println(query);
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rest = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, actorId);
            pstmt.setString(2, firstName);

            rest = pstmt.executeQuery();

            if (rest.next()) {
                System.out.println(rest.getString("first_name") + "님 환영합니다.");
            } else {
                System.out.println("회원 정보가 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rest);
            close(pstmt);
            close(con);
        }


    }
}
