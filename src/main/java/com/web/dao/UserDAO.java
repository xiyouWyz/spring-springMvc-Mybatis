package com.web.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoyou.wyz on 2017/7/30.
 */
@Repository("UserDAO")
public interface UserDAO  extends  BasicDAO{
    public List<Integer> getIdByAcNum();
}
