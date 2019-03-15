package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallUsergrade;
import org.linlinjava.litemall.db.domain.LitemallUsergradeExample;

public interface LitemallUsergradeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    long countByExample(LitemallUsergradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallUsergradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int insert(LitemallUsergrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int insertSelective(LitemallUsergrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallUsergrade selectOneByExample(LitemallUsergradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallUsergrade selectOneByExampleSelective(@Param("example") LitemallUsergradeExample example, @Param("selective") LitemallUsergrade.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallUsergrade> selectByExampleSelective(@Param("example") LitemallUsergradeExample example, @Param("selective") LitemallUsergrade.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    List<LitemallUsergrade> selectByExample(LitemallUsergradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallUsergrade selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallUsergrade.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    LitemallUsergrade selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallUsergrade record, @Param("example") LitemallUsergradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallUsergrade record, @Param("example") LitemallUsergradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallUsergrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_usergrade
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallUsergrade record);
}