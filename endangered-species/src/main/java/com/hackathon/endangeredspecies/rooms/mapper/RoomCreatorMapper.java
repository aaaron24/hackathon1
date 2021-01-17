package com.hackathon.endangeredspecies.rooms.mapper;

import com.hackathon.endangeredspecies.rooms.model.Room;
import com.hackathon.endangeredspecies.rooms.model.RoomCreator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoomCreatorMapper {

    @Select("SELECT * FROM ROOMCREATORS WHERE id = #{id}")
    Room selectRoomCreator(int id);

    @Select("SELECT MAX (id) FROM ROOMCREATORS")
    Integer getMaxRoomCreator();

    @Insert("INSERT INTO ROOMCREATORS (id, user_id, room_id) VALUES (#{id}, #{user_id}, #{room_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRoomCreator(RoomCreator roomCreator);

}
