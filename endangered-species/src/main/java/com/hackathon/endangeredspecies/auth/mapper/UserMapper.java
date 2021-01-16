package com.hackathon.endangeredspecies.auth.mapper;


import com.hackathon.endangeredspecies.auth.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (userId, username, password, salt, firstName, lastName, email) VALUES (#{userId}, #{username}, #{password}, #{salt}, #{firstName}, #{lastName}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Select("SELECT MAX (userId) FROM USERS")
    Integer getMaxId();

    @Select("SELECT username FROM USERS WHERE userId = #{userId}")
    String getUsernameById(int userId);

}

