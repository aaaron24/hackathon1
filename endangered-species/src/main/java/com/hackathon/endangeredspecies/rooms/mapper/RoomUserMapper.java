package com.hackathon.endangeredspecies.rooms.mapper;

import com.hackathon.endangeredspecies.rooms.model.Room;
import com.hackathon.endangeredspecies.rooms.model.RoomUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomUserMapper {

    @Select("SELECT * FROM ROOMUSERS WHERE id = #{id}")
    Room selectRoomUser(int id);

    @Select("SELECT MAX (id) FROM ROOMUSERS")
    Integer getMaxRoomUser();

    @Insert("INSERT INTO ROOMUSERS (id, user_id, user_id) VALUES (#{id}, #{user_id}, #{user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRoomUser(RoomUser roomUser);
}
