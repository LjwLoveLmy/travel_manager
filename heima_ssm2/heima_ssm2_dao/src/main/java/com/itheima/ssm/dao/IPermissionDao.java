package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    //查询用户时根据role的id查询对应的permission
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission) throws Exception;

    @Select("select * from permission where id = #{id}")
    public Permission findById(String id) throws Exception;

    @Delete("delete from role_permission where permissionId = #{permissionId}")
    public void deleteFromRole_Permission(String permissionId) throws Exception;

    @Delete("delete from permission where id = #{permissionId}")
    public void deleteById(String permissionId) throws Exception;
}
