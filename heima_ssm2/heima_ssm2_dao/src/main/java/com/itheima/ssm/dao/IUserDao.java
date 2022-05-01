package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.sun.org.apache.bcel.internal.ExceptionConst;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findById(String id) throws Exception;

    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    public List<Role> findOtherRoles(String userId) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")//这里需要注意，因为有两个参数，使用@Param注解给这两个参数取名字，这样mybatis才能区别出这是属于两个对象的参数
    public void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId) throws Exception;

    @Delete("delete from users_role where userId = #{userId}")
    public void deleteFromUsers_permission(String userId) throws Exception;

    @Delete("delete from users where id = #{userId}")
    public void deleteById(String userId)throws Exception;

    @Update("update users set username = #{username},email= #{email},phoneNum=#{phoneNum},status=#{status} ")
    public UserInfo editUser(UserInfo userInfo) throws Exception;

    @Select("select * from users where id = #{userId}")
    public UserInfo findUserById(String userId) throws Exception;

    @Select("select * from users")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "phoneNum" ,column = "phoneNum"),
            @Result(property = "status" ,column = "status")
    })
    public List<UserInfo> findAllByPage(int page, int size) throws Exception;
}
