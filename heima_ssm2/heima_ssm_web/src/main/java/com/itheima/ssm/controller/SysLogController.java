package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "10")Integer size ) throws Exception {
        List<SysLog> sysLogList=sysLogService.findAll(page,size);
        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(sysLogList);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("sysLogs",sysLogList);
        mv.setViewName("syslog-page-list");
        return mv;

    }

    @RequestMapping("/deleteAll.do")
    public String deleteAll() throws Exception {

        sysLogService.deleteAll();
        return "redirect:findAll.do";
    }
}
