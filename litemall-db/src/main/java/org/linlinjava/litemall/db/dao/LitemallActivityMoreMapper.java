package org.linlinjava.litemall.db.dao;

import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallActivityExample;
import org.linlinjava.litemall.db.domain.LitemallActivityMore;

import java.util.List;

public interface LitemallActivityMoreMapper extends LitemallActivityMapper{
    List<LitemallActivityMore> selectMore(@Param("activity") LitemallActivityMore activity);
    Integer selectMoreCount(@Param("activity") LitemallActivityMore activity);
}