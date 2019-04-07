package org.linlinjava.litemall.db.dao;

import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.IntegralSum;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallActivityExample;
import org.linlinjava.litemall.db.domain.LitemallActivityMore;

import java.util.List;

public interface LitemallActivityMoreMapper {
    List<LitemallActivityMore> selectMore(@Param("activity") LitemallActivityMore activity);
    Integer selectMoreCount(@Param("activity") LitemallActivityMore activity);

    List<IntegralSum> selectIntegralSum(@Param("integralType") Integer integralType,@Param("abs") Boolean abs,@Param("userId") Integer userId);
    Integer selectIntegralSumCount(@Param("integralType") Integer integralType,@Param("abs") Boolean abs,@Param("userId") Integer userId);
}