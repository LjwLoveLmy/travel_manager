package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    /**
     * 查询所有用户信息
     * @return
     * @throws Exception
     */
    public List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     * @param userInfo
     * @throws Exception
     */
    public void save(UserInfo userInfo) throws Exception;

    public UserInfo findById(String id) throws Exception;

    public List<Role> findOtherRoles(String userId)throws Exception;

    public void addRoleToUser(String userId, String[] roleIds) throws Exception;

    public void deleteUser(String userId) throws Exception;

    public UserInfo editUser(UserInfo userInfo) throws Exception;

    public UserInfo findUserById(String userId) throws Exception;

    public List<UserInfo> findAllByPage(int page, int size)throws Exception;
}
