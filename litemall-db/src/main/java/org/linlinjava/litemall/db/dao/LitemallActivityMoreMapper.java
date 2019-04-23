package org.linlinjava.litemall.db.dao;

import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.*;

import java.util.List;

public interface LitemallActivityMoreMapper {
    List<LitemallActivityMore> selectMore(@Param("activity") LitemallActivityMore activity);
    Integer selectMoreCount(@Param("activity") LitemallActivityMore activity);

    List<IntegralSum> selectIntegralSum(@Param("integralType") Integer integralType,@Param("abs") Boolean abs,@Param("userId") Integer userId);
    Integer selectIntegralSumCount(@Param("integralType") Integer integralType,@Param("abs") Boolean abs,@Param("userId") Integer userId);

    //更新商品某规格库存
    void updateProductStock(@Param("productStock")LitemallGoodsProduct productStock);
}