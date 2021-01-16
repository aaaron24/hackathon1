package com.hackathon.endangeredspecies.rooms.mapper;
import com.hackathon.endangeredspecies.rooms.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RoomMapper {

    @Select("SELECT * ROOMS")
    List<Room> selectRooms();

    @Select("SELECT * FROM ROOMS WHERE id = #{id}")
    Room selectRoom(int id);
    
    @Select("SELECT MAX (id) FROM ROOMS")
    Room getMaxRoom();
    
}
