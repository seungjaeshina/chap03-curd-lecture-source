package com.ohgiraffers.insert;

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

public class Application2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("배우 First Name 입력");
        String firstName = sc.nextLine();
        System.out.println("배우 Last Name 입력");
        String lastName = sc.nextLine();


        ActorDto newMenu = new ActorDto();

        newMenu.setFirst_name(firstName);
        newMenu.setLast_name(lastName);
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int resualt = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/actor-query.xml"));
            String query = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, newMenu.getFirst_name());
            pstmt.setString(2, newMenu.getLast_name());

            resualt = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);

        }

        if (resualt > 0) {
            System.out.println("새로운 배우 등록 완료!");

        } else {
            System.out.println("배우 등록 실패");
        }
    }
}