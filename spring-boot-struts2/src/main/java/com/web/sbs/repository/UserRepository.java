package com.web.sbs.repository;


import com.web.sbs.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {
//    find all user
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
            @Result(property = "isDelete", column = "is_delete"),
    })
    @Select("SELECT email, name, group_role, active, created_at, is_delete FROM users WHERE is_delete = 0 ORDER BY created_at DESC")
    public List<User> findAll();
// find user has pagination
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
    })
    @Select("SELECT email, name, group_role, active, created_at FROM users WHERE email like  CONCAT('%', #{user.email}, '%') " +
            "AND " +
            "name LIKE CONCAT('%', #{user.name}, '%')  AND "+
            "group_role LIKE CONCAT('%', #{user.groupRole}, '%') AND "+
            "active = IFNULL(#{user.active}, active) AND "+
            "is_delete = 0 "+
            " ORDER BY created_at DESC LIMIT #{size} OFFSET #{next}")
    public List<User> findUserByOptionHasPagin(@Param("user") User user, @Param("next") int next, @Param("size") int size);
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
    })
    @Select("SELECT email, name, group_role, active, created_at FROM users WHERE email like  CONCAT('%', #{user.email}, '%') " +
            "AND " +
            "name LIKE CONCAT('%', #{user.name}, '%')  AND "+
            "group_role LIKE CONCAT('%', #{user.groupRole}, '%') AND "+
            "active LIKE CONCAT('%', #{active}, '%') AND "+
            "is_delete = 0 "+
            " ORDER BY created_at DESC LIMIT #{size} OFFSET #{next}")
    public List<User> findUserByOptionHasPagin2(@Param("user") User user,@Param("active") String active, @Param("next") int next, @Param("size") int size);
//    find all search user
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
    })
    @Select("SELECT email, name, group_role, active, created_at FROM users WHERE email like  CONCAT('%', #{user.email}, '%') " +
            "AND " +
            "name LIKE CONCAT('%', #{user.name}, '%')  AND "+
            "group_role LIKE CONCAT('%', #{user.groupRole}, '%') AND "+
            "active = IFNULL(#{user.active}, active) AND"+
            " is_delete = 0 "+
            " ORDER BY created_at DESC ")
    public List<User> findUserByOption(@Param("user") User user);

    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
    })
    @Select("SELECT email, name, group_role, active, created_at FROM users WHERE email like  CONCAT('%', #{user.email}, '%') " +
            "AND " +
            "name LIKE CONCAT('%', #{user.name}, '%')  AND "+
            "group_role LIKE CONCAT('%', #{user.groupRole}, '%') AND "+
            "active LIKE CONCAT('%', #{active}, '%') AND"+
            " is_delete = 0 "+
            " ORDER BY created_at DESC ")
    public List<User> findUserByOption2(@Param("user") User user, @Param("active") String active);
//   get user with pagination
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
            @Result(property = "isDelete", column = "is_delete"),
    })
    @Select("SELECT email, name, group_role, active, created_at, is_delete FROM users WHERE is_delete = 0 ORDER BY created_at DESC LIMIT #{size} OFFSET #{next}")
    public List<User> findAllWithPagin(@Param("next") int next, @Param("size") int size);
//    get user by email
    @Select("SELECT email, password, name FROM users WHERE email = #{email}")
    public User getUserByEmail(@Param("email") String email);

//    delete user

    @Delete("UPDATE users SET users.is_delete = 1 WHERE email = #{email}")
    public void deleteUser(@Param("email") String email);

//    update user
    @Update("UPDATE users SET users.name = #{user.name}, users.group_role = #{user.groupRole}, users.active= #{user.active}, users.password = #{user.password} WHERE users.email = #{user.email}" )
    public void updateUser(@Param("user") User user);

    @Update("UPDATE users SET users.name = #{user.name}, users.group_role = #{user.groupRole}, users.active= #{user.active}, users.ip_last_login = #{user.ipLastLogin}, users.last_login = #{user.lastLogin} WHERE users.email = #{user.email}" )
    public void updateUserWithoutPassword(@Param("user") User user);
    @Update("UPDATE users SET  users.ip_last_login = #{user.ipLastLogin}, users.last_login = #{user.lastLogin}, users.remember_token = #{user.rememberToken} WHERE users.email = #{user.email}" )
    public void updateLastLogin(@Param("user") User user);

//    insert user
    @Insert("INSERT INTO users (email, name, password, group_role, active, created_at, is_delete) VALUES( #{user.email}, #{user.name}, #{user.password}, #{user.groupRole}, #{user.active}, #{user.createdAt}, #{user.isDelete})")
    public void addUser(@Param("user") User user);

//    get user by email
    @Results({
            @Result(property = "active", column = "active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
    })
    @Select("SELECT email, name, group_role, active, created_at FROM users WHERE email = #{email} ")
    public User getUser(@Param("email") String email);

//    lock unlock user
    @Select("UPDATE users SET active = #{active} WHERE users.email = #{email}")
    public void toogleLockUser(@Param("active") boolean active,@Param("email") String email);

    @Results({


            @Result(property = "email", column = "email"),
            @Result(property = "name", column = "name"),
            @Result(property = "groupRole", column = "group_role"),
            @Result(property = "password", column = "password"),
            @Result(property = "rememberToken", column = "remember_token"),
    })
    @Select("SELECT users.email, users.password ,users.name , users.remember_token, users.group_role FROM users WHERE users.remember_token = #{rememberToken} AND users.is_delete = 0")
    public User findUserByToken(@Param("rememberToken") String rememberToken);
}
