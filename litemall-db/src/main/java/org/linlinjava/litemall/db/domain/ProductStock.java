package org.linlinjava.litemall.db.domain;

public class ProductStock {
    //update litemall_goods_product set number = #{number}
   // where goods_id=#{goodsId} and specifications=#{specifications}
    Integer number;
    Integer goodsId;
    String specifications;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
