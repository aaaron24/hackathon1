package com.hackathon.endangeredspecies.rooms.mapper;
import com.hackathon.endangeredspecies.auth.model.User;
import com.hackathon.endangeredspecies.rooms.model.Room;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RoomMapper {

    @Select("SELECT * ROOMS")
    List<Room> selectRooms();

    @Select("SELECT roomName FROM ROOMS")
    List<String> selectRoomNames();

    @Select("SELECT animal FROM ROOMS")
    List<String> selectRoomAnimals();

    @Select("SELECT * FROM ROOMS WHERE id = #{id}")
    Room selectRoom(int id);

    @Select("SELECT * FROM ROOMS WHERE roomName = #{roomName}")
    Room selectRoomByName(String roomName);

    @Select("SELECT * FROM ROOMS WHERE animal = #{animal}")
    Room selectRoomByAnimal(String animal);

    @Select("SELECT MAX (id) FROM ROOMS")
    Integer getMaxRoom();
     
    @Select("SELECT * FROM ROOMS WHERE roomCreator = #{roomCreator}")
    List<Room> roomsCreated(String roomCreator);
    
    @Delete("DELETE FROM ROOMS WHERE roomName = #{roomName}")
    void deleteRoom(String roomName);

    @Update("UPDATE ROOMS SET roomName = #{newRoomName}, animal = #{newAnimal} WHERE id = #{id}")
    void editRoom(int id, String newRoomName, String newAnimal);
    
    @Insert("INSERT INTO ROOMS (id, roomName, roomCreator, animal, money) VALUES (#{id}, #{roomName}, #{roomCreator}, #{animal}, #{money})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRoom(Room room);
}
