package com.ohgiraffers.select;


import com.ohgiraffers.actorDTO.ActorDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application3 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        ActorDto actorDto = null;
        List<ActorDto> actorList = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 배우의 앞 이름을 입력하시오");
        String firstName = sc.nextLine();

        String query = "SELECT * FROM actor WHERE first_name Like CONCAT('&',?, '%')";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, firstName);

            rest = pstmt.executeQuery();

            actorList = new ArrayList<>();

            while (rest.next()) {
                actorDto = new ActorDto();

                actorDto.setActor_id(rest.getInt("actor_id"));
                actorDto.setFirst_name(rest.getString("first_name"));
                actorDto.setLast_name(rest.getString("Last_name"));
                actorDto.setLast_update(rest.getDate("Last_update"));

                actorList.add(actorDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            close(rest);
            close(pstmt);
            close(con);
        }
        for (ActorDto actorDto1 : actorList) {
            System.out.println(actorDto1);
        }
    }
}

