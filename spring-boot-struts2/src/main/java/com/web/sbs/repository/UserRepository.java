package com.web.sbs.repository;


import com.web.sbs.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {
    @Select("SELECT email, name, groups, status FROM users")
    public List<User> findAll();

    @Select("SELECT email, password FROM users WHERE email = #{email}")
    public User getUserByEmail(@Param("email") String email);


    @Delete("DELETE FROM users WHERE email = #{email}")
    public void deleteUser(@Param("email") String email);

    @Update("UPDATE users SET users.name = #{user.name}, users.groups = #{user.groups}, users.status= #{user.status}, users.password = #{user.password}, users.last_login = #{user.lastLogin}, users.ip_last_login = #{user.ipLastLogin} WHERE users.email = #{user.email}" )
    public void updateUser(@Param("user") User user);

    @Insert("INSERT INTO users (email, name, password, groups, status) VALUES( #{user.email}, #{user.name}, #{user.password}, #{user.groups}, #{user.status})")
    public void addUser(@Param("user") User user);

}
