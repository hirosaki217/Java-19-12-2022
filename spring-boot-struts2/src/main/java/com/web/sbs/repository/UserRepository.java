package com.web.sbs.repository;


import com.web.sbs.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserRepository {
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groups", column = "groups"),
    })
    @Select("SELECT email, name, groups, active, created_at FROM users ORDER BY created_at DESC")
    public List<User> findAll();

    @Select("SELECT email, name, groups, active, created_at FROM users WHERE email like  CONCAT('%', #{user.email}, '%') " +
            "AND " +
            "name LIKE CONCAT('%', #{user.name}, '%')  AND "+
            "groups LIKE CONCAT('%', #{user.groups}, '%') AND "+
            "active = IFNULL(#{user.active}, active) "+
            " ORDER BY created_at DESC LIMIT #{size} OFFSET #{next}")
    public List<User> findUserByOptions(@Param("user") User user, @Param("next") int next,@Param("size") int size);
    @Select("SELECT email, name, groups, active, created_at FROM users WHERE email like  CONCAT('%', #{user.email}, '%') " +
            "AND " +
            "name LIKE CONCAT('%', #{user.name}, '%')  AND "+
            "groups LIKE CONCAT('%', #{user.groups}, '%') AND "+
            "active = IFNULL(#{user.active}, active) "+
            " ORDER BY created_at DESC ")
    public List<User> findUserByOption(@Param("user") User user);
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groups", column = "groups"),
    })
    @Select("SELECT email, name, groups, active, created_at FROM users ORDER BY created_at DESC LIMIT #{size} OFFSET #{next}")
    public List<User> findUsers(@Param("next") int next,@Param("size") int size);
    @Select("SELECT email, password FROM users WHERE email = #{email}")
    public User getUserByEmail(@Param("email") String email);


    @Delete("DELETE FROM users WHERE email = #{email}")
    public void deleteUser(@Param("email") String email);

    @Update("UPDATE users SET users.name = #{user.name}, users.groups = #{user.groups}, users.active= #{user.active}, users.password = #{user.password} WHERE users.email = #{user.email}" )
    public void updateUser(@Param("user") User user);

    @Insert("INSERT INTO users (email, name, password, groups, active, created_at) VALUES( #{user.email}, #{user.name}, #{user.password}, #{user.groups}, #{user.active}, #{user.createdAt})")
    public void addUser(@Param("user") User user);

    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groups", column = "groups"),
    })
    @Select("SELECT email, name, groups, active, created_at FROM users WHERE email = #{email} ")
    public User getUser(@Param("email") String email);

    @Select("UPDATE users SET active = #{active} WHERE users.email = #{email}")
    public void toogleLockUser(@Param("active") boolean active,@Param("email") String email);
}
