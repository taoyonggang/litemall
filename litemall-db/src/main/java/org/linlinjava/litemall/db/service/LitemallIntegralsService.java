package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallIntegralsMapper;
import org.linlinjava.litemall.db.domain.LitemallIntegrals;
import org.linlinjava.litemall.db.domain.LitemallIntegralsExample;
import org.linlinjava.litemall.db.domain.LitemallUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class LitemallIntegralsService {
    @Resource
    private LitemallIntegralsMapper integralsMapper;
    @Resource
    private LitemallUserService userService;

    /**
     * 查询指定用户积分明细
     * @param userId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallIntegrals> queryIntegrals(Integer userId,Integer page, Integer size, String sort, String order){
        LitemallIntegralsExample example = new LitemallIntegralsExample();
        LitemallIntegralsExample.Criteria criteria = example.createCriteria();

        if (userId!=null && userId>=0) {
            criteria.andUserIdEqualTo(userId);
        }else{ //不允许查询所有积分
            return null;
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return integralsMapper.selectByExample(example);

    }

    public List<LitemallIntegrals> queryAllIntegrals(Integer userId,String sort, String order){
        LitemallIntegralsExample example = new LitemallIntegralsExample();
        LitemallIntegralsExample.Criteria criteria = example.createCriteria();

        if (userId!=null && userId>=0) {
            criteria.andUserIdEqualTo(userId);
        }else{ //不允许查询所有用户积分
            return null;
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        return integralsMapper.selectByExample(example);

    }

    /**
     * 从积分明细表查询所有有效积分
     * @param userId
     * @return
     */
    public Integer queryIntegralSum(Integer userId){
        if (userId!=null && userId>=0) {
            List<LitemallIntegrals> integrals= this.queryAllIntegrals(userId,null,null);
            if(integrals!=null&&integrals.size()>0){
                int sum = 0;
                for (LitemallIntegrals integral:integrals) {
                    //过滤无效积分
                    if (integral.getEffective().equals((byte) 1)) {
                        sum += integral.getIntegralDo();
                    }
                }
                return sum;
            }
            return 0;
        }else{ //不允许查询所有积分
            return 0;
        }
    }

    /**
     * 从积分明细表查询所有存在积分
     *
     * @param userId
     * @return
     */
    public Integer queryIntegralExistSum(Integer userId) {
        if (userId != null && userId >= 0) {
            List<LitemallIntegrals> integrals = this.queryAllIntegrals(userId, null, null);
            if (integrals != null && integrals.size() > 0) {
                int sum = 0;
                for (LitemallIntegrals integral : integrals) {
                    sum += integral.getIntegralDo();
                }
                return sum;
            }
            return 0;
        } else { //不允许查询所有积分
            return 0;
        }
    }

    public Integer queryTotalCount(Integer userId){

        if (userId!=null && userId>=0) {
            LitemallIntegralsExample example = new LitemallIntegralsExample();
            LitemallIntegralsExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userId);
            Integer count = (int)integralsMapper.countByExample(example);
            return count;
        }else{
            return 0;
        }
    }

    /**
     * 增加积分记录
     * @param action
     * @param InteggralDo
     * @param userId
     * @return
     */
    @Transactional
    public Integer addIntegral(String action, Integer InteggralDo, Integer userId, Integer type, Integer effective,Integer orderId) {

        LitemallUser user = userService.findById(userId);
        if (user!=null) {
            user.setIntegral(user.getIntegral()+InteggralDo);
            userService.updateById(user);
            LitemallIntegrals record = new LitemallIntegrals();
            record.setAction(action);
            record.setUserId(userId);
            record.setOrderId(orderId);
            record.setEffective(effective.byteValue());
            record.setIntegralType(type.byteValue());
            record.setIntegralDo(InteggralDo);
            record.setAddTime(now());
            return integralsMapper.insert(record);
        }
        return -1;
    }


    /**
     * 使某一类积分记录有效/无效
     *
     * @param type
     * @param effective
     * @param userId
     * @return
     */
    @Transactional
    public Integer updateIntegral(Integer userId, Integer type, Integer effective) {

        LitemallIntegralsExample example = new LitemallIntegralsExample();
        LitemallIntegralsExample.Criteria criteria = example.createCriteria();

        if (userId != null && userId >= 0) {
            criteria.andUserIdEqualTo(userId);
            criteria.andIntegralTypeEqualTo(type.byteValue());
        } else { //不允许查询所有用户积分
            return -1;
        }

        List<LitemallIntegrals> records = integralsMapper.selectByExample(example);

        if (records != null && records.size() > 0) {
            LitemallIntegrals record = records.get(0);
            record.setEffective(effective.byteValue());
            return integralsMapper.updateByPrimaryKey(record);
        }

        return -1;


    }


}
