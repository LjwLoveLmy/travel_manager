package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import com.itheima.ssm.utils.DateStringEditor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
@Autowired
private IProductService productService;

/*@InitBinder
public void InitBinder(WebDataBinder binder){
    //通过binder注册一个PropertyEditor--->转换器对象
    binder.registerCustomEditor(Date.class,new DateStringEditor());

}*/


//查询所有产品
@RequestMapping("/findAll.do")
@RolesAllowed("ADMIN")//只有拥有admin角色的用户才能有查询所有产品的权限
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size ) throws Exception {
        List<Product> ps = productService.findAll(page,size);
        //PageInfo就是一个分页bean
        PageInfo pageInfo = new PageInfo(ps);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("productList",ps);
        mv.setViewName("product-page-list1");
        return mv;
    }


    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
