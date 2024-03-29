package com.ohgiraffers.actorDTO;

import java.sql.Date;

public class ActorDto {

    private int actor_id;
    private String first_name;
    private String last_name;
    private Date last_update;


    public ActorDto() {
    }

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLast_update() {
        return last_update;
    }

    @Override
    public String toString() {
        return "ActorDto{" +
                "actor_id=" + actor_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", last_update=" + last_update +
                '}';
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;


    }
}

