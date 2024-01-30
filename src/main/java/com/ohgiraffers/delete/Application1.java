package com.ohgiraffers.delete;



import com.ohgiraffers.actorDTO.ActorDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 배우 ID : ");
        int actorID = sc.nextInt();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();
        ActorDto actorDto = new ActorDto();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/actor-query.xml"));
            String query = prop.getProperty("deleteMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, actorID);

            result = pstmt.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }
        if (result > 0) {
            System.out.println("성공적으로 삭제되었습니다.");
        }else {
            System.out.println("삭제가 되지 않았습니다.");
        }

    }

}






