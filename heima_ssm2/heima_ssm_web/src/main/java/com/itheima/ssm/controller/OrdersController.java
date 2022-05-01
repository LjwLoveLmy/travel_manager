package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    /*//查询所有订单--为分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Orders> odersList = ordersService.findAll();

        mv.addObject("ordersList",odersList);

        mv.setViewName("orders-list");

        return mv;
    }*/
//使用分页
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")//这里必须要加上ROLE_的前缀，要不然所有角色都不能访问该功能，这里表示只有ROLE_ADMIN角色可以访问该功能，这是@Secured的要求。jsr250的注解@RolesAllowed就不需要加ROLE_
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size ) throws Exception {

        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page,size);

        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId) throws Exception {

        Orders orders = ordersService.findById(ordersId);

        ModelAndView mv = new ModelAndView();

        mv.addObject("orders",orders);
        mv.setViewName("orders-show");

        return mv;
    }
}
