package org.linlinjava.litemall.db.domain;

import java.util.ArrayList;
import java.util.List;

public class LitemallIntegralRuleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public LitemallIntegralRuleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public LitemallIntegralRuleExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public LitemallIntegralRuleExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public static Criteria newAndCreateCriteria() {
        LitemallIntegralRuleExample example = new LitemallIntegralRuleExample();
        return example.createCriteria();
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andIdEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("id = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andIdNotEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("id <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andIdGreaterThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("id > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andIdGreaterThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("id >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andIdLessThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("id < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andIdLessThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("id <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateRuleIsNull() {
            addCriterion("create_rule is null");
            return (Criteria) this;
        }

        public Criteria andCreateRuleIsNotNull() {
            addCriterion("create_rule is not null");
            return (Criteria) this;
        }

        public Criteria andCreateRuleEqualTo(Float value) {
            addCriterion("create_rule =", value, "createRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateRuleEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_rule = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateRuleNotEqualTo(Float value) {
            addCriterion("create_rule <>", value, "createRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateRuleNotEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_rule <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateRuleGreaterThan(Float value) {
            addCriterion("create_rule >", value, "createRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateRuleGreaterThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_rule > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateRuleGreaterThanOrEqualTo(Float value) {
            addCriterion("create_rule >=", value, "createRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateRuleGreaterThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_rule >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateRuleLessThan(Float value) {
            addCriterion("create_rule <", value, "createRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateRuleLessThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_rule < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateRuleLessThanOrEqualTo(Float value) {
            addCriterion("create_rule <=", value, "createRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateRuleLessThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_rule <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateRuleIn(List<Float> values) {
            addCriterion("create_rule in", values, "createRule");
            return (Criteria) this;
        }

        public Criteria andCreateRuleNotIn(List<Float> values) {
            addCriterion("create_rule not in", values, "createRule");
            return (Criteria) this;
        }

        public Criteria andCreateRuleBetween(Float value1, Float value2) {
            addCriterion("create_rule between", value1, value2, "createRule");
            return (Criteria) this;
        }

        public Criteria andCreateRuleNotBetween(Float value1, Float value2) {
            addCriterion("create_rule not between", value1, value2, "createRule");
            return (Criteria) this;
        }

        public Criteria andDeleteRuleIsNull() {
            addCriterion("delete_rule is null");
            return (Criteria) this;
        }

        public Criteria andDeleteRuleIsNotNull() {
            addCriterion("delete_rule is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteRuleEqualTo(Float value) {
            addCriterion("delete_rule =", value, "deleteRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andDeleteRuleEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("delete_rule = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeleteRuleNotEqualTo(Float value) {
            addCriterion("delete_rule <>", value, "deleteRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andDeleteRuleNotEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("delete_rule <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeleteRuleGreaterThan(Float value) {
            addCriterion("delete_rule >", value, "deleteRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andDeleteRuleGreaterThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("delete_rule > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeleteRuleGreaterThanOrEqualTo(Float value) {
            addCriterion("delete_rule >=", value, "deleteRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andDeleteRuleGreaterThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("delete_rule >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeleteRuleLessThan(Float value) {
            addCriterion("delete_rule <", value, "deleteRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andDeleteRuleLessThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("delete_rule < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeleteRuleLessThanOrEqualTo(Float value) {
            addCriterion("delete_rule <=", value, "deleteRule");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andDeleteRuleLessThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("delete_rule <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andDeleteRuleIn(List<Float> values) {
            addCriterion("delete_rule in", values, "deleteRule");
            return (Criteria) this;
        }

        public Criteria andDeleteRuleNotIn(List<Float> values) {
            addCriterion("delete_rule not in", values, "deleteRule");
            return (Criteria) this;
        }

        public Criteria andDeleteRuleBetween(Float value1, Float value2) {
            addCriterion("delete_rule between", value1, value2, "deleteRule");
            return (Criteria) this;
        }

        public Criteria andDeleteRuleNotBetween(Float value1, Float value2) {
            addCriterion("delete_rule not between", value1, value2, "deleteRule");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(Integer value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateUserEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_user = ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(Integer value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateUserNotEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_user <> ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(Integer value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateUserGreaterThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_user > ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateUserGreaterThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_user >= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(Integer value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateUserLessThanColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_user < ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(Integer value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andCreateUserLessThanOrEqualToColumn(LitemallIntegralRule.Column column) {
            addCriterion(new StringBuilder("create_user <= ").append(column.getEscapedColumnName()).toString());
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<Integer> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<Integer> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(Integer value1, Integer value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {
        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private LitemallIntegralRuleExample example;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        protected Criteria(LitemallIntegralRuleExample example) {
            super();
            this.example = example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public LitemallIntegralRuleExample example() {
            return this.example;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        /**
         * This interface was generated by MyBatis Generator.
         * This interface corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public interface ICriteriaAdd {
            /**
             * This method was generated by MyBatis Generator.
             * This method corresponds to the database table litemall_integral_rule
             *
             * @mbg.generated
             * @project https://github.com/itfsw/mybatis-generator-plugin
             */
            Criteria add(Criteria add);
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}