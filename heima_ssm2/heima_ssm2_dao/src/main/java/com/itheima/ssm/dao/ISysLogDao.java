package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.SysLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ISysLogDao {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values( #{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog) throws Exception;

    @Select("select * from syslog")
    public List<SysLog> findAll() throws Exception;

    @Delete("delete from syslog")
    public void deleteAll()throws Exception;
}
