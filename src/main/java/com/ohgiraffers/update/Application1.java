package com.ohgiraffers.update;

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
        System.out.println("변경할 배우 아이디 입력 : ");
        int actorID = sc.nextInt();
        System.out.println("변경할 배우의 앞에 이름 : ");
        sc.nextLine();
        String firstName = sc.nextLine();
        System.out.println("변경할 배우의 뒷 이름 : ");
        String lastName = sc.nextLine();

        ActorDto changeActor = new ActorDto();
        changeActor.setActor_id(actorID);
        changeActor.setFirst_name(firstName);
        changeActor.setLast_name(lastName);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/actor-query.xml"));
            String query = prop.getProperty("updateMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changeActor.getFirst_name());
            pstmt.setString(2, changeActor.getLast_name());
            pstmt.setInt(3, changeActor.getActor_id());


            result = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        if (result > 0) {
            System.out.println("성공적으로 업데이트 되었습니다.");
        } else {
            System.out.println("수정에 실패하셨습니다.");
        }
    }
}
