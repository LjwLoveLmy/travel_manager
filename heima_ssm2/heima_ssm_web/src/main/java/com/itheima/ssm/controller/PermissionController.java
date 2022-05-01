package com.itheima.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Permission;

import com.itheima.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

 @Autowired
 IPermissionService permissionService;


        @RequestMapping("/deletePermission.do")
        public String deleteRole(@RequestParam(name = "id",required = true) String permissionId) throws Exception {
            permissionService.deletePermission(permissionId);
            return "redirect:findAll.do";
        }

        @RequestMapping("/findById.do")
        public ModelAndView findById(String id) throws Exception {
            ModelAndView mv = new ModelAndView();

            Permission permission=permissionService.findById(id);
            mv.addObject("permission",permission);
            mv.setViewName("permission-show");
            return mv;
        }


         @RequestMapping("/save.do")
         public String save(Permission permission) throws Exception {

             permissionService.save(permission);

             return "redirect:findAll.do";
         }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "10")Integer size ) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-page-list");
        return mv;
    }
}
