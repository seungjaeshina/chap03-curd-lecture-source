package com.ohgiraffers.select;


import com.ohgiraffers.actorDTO.ActorDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.close;

public class Application4 {
    public static void main(String[] args) {

        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rest = null;
        ActorDto actorDto = null;
        List<ActorDto> actorList = null;
        try {
            stmt = con.createStatement();

            Scanner sc = new Scanner(System.in);
            System.out.println("조회하려는 배우 번호를 입력해 주세요 : ");
            String actorId = sc.nextLine();
            String query = "SELECT * FROM actor WHERE actor_id= '" + actorId + "'";

            rest = stmt.executeQuery(query);

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
            close(stmt);
            close(con);
        }
        for (ActorDto actorDto1 : actorList) {

            System.out.println(actorDto1);
        }
    }
}