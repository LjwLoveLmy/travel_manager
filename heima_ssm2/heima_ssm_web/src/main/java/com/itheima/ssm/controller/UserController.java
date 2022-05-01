package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Transactional
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    //用户的分页操作
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")//这里必须要加上ROLE_的前缀，要不然所有角色都不能访问该功能，这里表示只有ROLE_ADMIN角色可以访问该功能，这是@Secured的要求。jsr250的注解@RolesAllowed就不需要加ROLE_
    public ModelAndView findAllByPage(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size ) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAllByPage(page,size);

        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-page-list");
        return mv;
    }

    @RequestMapping("/findUserById.do")
    public ModelAndView findUserById(@RequestParam(name = "id",required = true) String userId) throws Exception {
        UserInfo userInfo=userService.findUserById(userId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userInfo",userInfo);
        mv.setViewName("user-edit");
        return mv;
    }

    @RequestMapping("/editUser.do")
    public String editUserById(@RequestParam(name = "userInfo",required = true) UserInfo userInfo) throws Exception {
        userService.editUser(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/deleteUser.do")
    public String deleteUser(@RequestParam(name = "id",required = true) String userId) throws Exception {
        userService.deleteUser(userId);

        return "redirect:findAll.do";
    }


    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds) throws Exception {

        userService.addRoleToUser(userId,roleIds);

        return "redirect:findAll.do";
    }


    //查询用户以及用户可以添加的角色
@RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId) throws Exception {
    ModelAndView mv = new ModelAndView();
    //根据用户的id查询用户
    UserInfo userInfo = userService.findById(userId);
    //根据用户查询用户可以添加的角色
    List<Role> otherRoles=userService.findOtherRoles(userId);

    mv.addObject("user",userInfo);
    mv.addObject("roleList",otherRoles);
    mv.setViewName("user-role-add");
    return mv;
    }


    //查询指定id的用户详情
    @RequestMapping("findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }
    //添加用户
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='tom'")//指定只有tom这个用户才能使用save功能
    public String save(UserInfo userInfo) throws Exception {

        userService.save(userInfo);
        return "redirect:findAll.do";
    }

   /* @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll() throws Exception {
        List<UserInfo> userList = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }*/


}
