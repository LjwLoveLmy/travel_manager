package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;
import jdk.internal.org.jline.terminal.Size;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

public interface IOrdersService {

    public List<Orders> findAll(int page,int size) throws Exception;

   public Orders findById(String ordersId) throws Exception;
}
