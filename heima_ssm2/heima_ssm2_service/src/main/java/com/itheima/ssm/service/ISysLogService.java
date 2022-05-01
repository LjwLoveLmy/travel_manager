package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    public void save(SysLog sysLog) throws Exception;

    public List<SysLog> findAll(int page,int size) throws Exception;

    //删除日志
    public void deleteAll() throws Exception;
}
