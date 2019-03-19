package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallActivityMapper;
import org.linlinjava.litemall.db.domain.LitemallActivity;
import org.linlinjava.litemall.db.domain.LitemallActivityExample;
import org.linlinjava.litemall.db.domain.LitemallActivity.Column;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

//import org.linlinjava.litemall.db.domain.LitemallActivity.Column;

@Service
public class LitemallActivityService {
    @Resource
    private LitemallActivityMapper activityMapper;

    @Resource
    private LitemallTopicService topicService;

    private Column[] columns = new Column[]{Column.id, Column.activityId, Column.userId, Column.promoterId, Column.orign, Column.addTime};




    public int add(LitemallActivity activity) {
        activity.setAddTime(LocalDateTime.now());
        return activityMapper.insertSelective(activity);
    }

    public int updateById(LitemallActivity activity) {
        //activity.setUpdateTime(LocalDateTime.now());
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdEqualTo(activity.getId());
        return activityMapper.updateByExampleSelective(activity, example);
    }

    public List<LitemallActivity> queryList(int activityId,int userid,int offset, int limit) {
        return queryList(activityId,userid,offset, limit, "add_time", "desc");
    }

    public List<LitemallActivity> queryList(int activityId,int userId,int offset, int limit, String sort, String order) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false).andPromoterIdEqualTo(userId);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return activityMapper.selectByExampleSelective(example,columns);
    }

    public List<LitemallActivity> queryMyList(int userid,int offset, int limit, String sort, String order) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userid);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return activityMapper.selectByExampleSelective(example,columns);
    }

    /**
     * 获取当前推荐人所有推荐人数
     * @param pid
     * @return
     */
    public int queryTotal(Integer activityId,Integer pid) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false).andActivityIdEqualTo(activityId).andPromoterIdEqualTo(pid);
        return (int) activityMapper.countByExample(example);
    }

    /**
     * 获取当前用户所有活动
     * @param userId
     * @return
     */
    public int queryMyActivityCount(Integer userId) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false).andUserIdEqualTo(userId);
        return (int) activityMapper.countByExample(example);
    }

    /**
     * 获取当前用户参加活动数
     * @param userId
     * @return
     */
    public int queryActivityCount(Integer activityId,Integer userId) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andDeletedEqualTo(false).andActivityIdEqualTo(activityId).andUserIdEqualTo(userId);
        return (int) activityMapper.countByExample(example);
    }

    public LitemallActivity findById(Integer id) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return activityMapper.selectOneByExample(example);
    }

    public List<LitemallActivity> queryRelatedList(Integer id,int activityId,int pid, int offset, int limit) {
        LitemallActivityExample example = new LitemallActivityExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        List<LitemallActivity> Activitys = activityMapper.selectByExample(example);
        if (Activitys.size() == 0) {
            return queryList(activityId,pid,offset, limit, "add_time", "desc");
        }
        LitemallActivity Activity = Activitys.get(0);

        example = new LitemallActivityExample();
        example.or().andIdNotEqualTo(Activity.getId()).andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        List<LitemallActivity> relateds = activityMapper.selectByExample(example);
        if (relateds.size() != 0) {
            return relateds;
        }

        return queryList(activityId,pid,offset, limit, "add_time", "desc");
    }

    /**
     * 按各种条件统计活动情况，包括指定活动，推广人，参与人，时间段
     * @param activityId
     * @param promoterId
     * @param userId
     * @param addTime
     * @param endTime
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<LitemallActivity> querySelective(Integer activityId, Integer promoterId, Integer userId, LocalDateTime addTime, LocalDateTime endTime, Integer page, Integer limit, String sort, String order) {
        LitemallActivityExample example = new LitemallActivityExample();
        LitemallActivityExample.Criteria criteria = example.createCriteria();

        if (activityId!=null&&activityId>0) {
            criteria.andActivityIdEqualTo(activityId);
        }

        if (promoterId!=null&&promoterId>0) {
            criteria.andPromoterIdEqualTo(promoterId);
        }

        if (userId!=null&&userId>0) {
            criteria.andUserIdEqualTo(userId);
        }

        if (addTime!=null) {
            criteria.andAddTimeGreaterThan(addTime);
        }

        if (endTime!=null) {
            criteria.andAddTimeLessThan(endTime);
        }

        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return activityMapper.selectByExample(example);
    }

    public int countSelective(Integer activityId, Integer promoterId, Integer userId, LocalDateTime addTime, LocalDateTime endTime, Integer page, Integer limit, String sort, String order) {
        LitemallActivityExample example = new LitemallActivityExample();
        LitemallActivityExample.Criteria criteria = example.createCriteria();

        if (activityId!=null&&activityId>0) {
            criteria.andActivityIdEqualTo(activityId);
        }

        if (promoterId!=null&&promoterId>0) {
            criteria.andPromoterIdEqualTo(promoterId);
        }

        if (userId!=null&&userId>0) {
            criteria.andUserIdEqualTo(userId);
        }

        if (addTime!=null) {
            criteria.andAddTimeGreaterThan(addTime);
        }

        if (endTime!=null) {
            criteria.andAddTimeLessThan(endTime);
        }

        criteria.andDeletedEqualTo(false);

        return (int) activityMapper.countByExample(example);
    }

    public void deleteById(Integer id) {
        activityMapper.logicalDeleteByPrimaryKey(id);
    }


    public  List<LitemallActivity> selectByUserId(Integer userId, Integer page, Integer size, String sort, String order) {
        return  activityMapper.selectByUserId(userId);
    }
}
