package com.joy.dao;

import com.joy.dao.bean.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * @author joy
 * @version 1.0
 * @date 2020/5/11 21:49
 */
public interface IEmpDao {
    public Emp selectEmpByEmpno(int id);
}
