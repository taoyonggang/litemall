package org.linlinjava.litemall.db.domain;

import java.util.ArrayList;
import java.util.Arrays;

public class LitemallIntegralRule {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_integral_rule.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_integral_rule.create_rule
     *
     * @mbg.generated
     */
    private Float createRule;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_integral_rule.delete_rule
     *
     * @mbg.generated
     */
    private Float deleteRule;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column litemall_integral_rule.create_user
     *
     * @mbg.generated
     */
    private Integer createUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_integral_rule.id
     *
     * @return the value of litemall_integral_rule.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_integral_rule.id
     *
     * @param id the value for litemall_integral_rule.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_integral_rule.create_rule
     *
     * @return the value of litemall_integral_rule.create_rule
     *
     * @mbg.generated
     */
    public Float getCreateRule() {
        return createRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_integral_rule.create_rule
     *
     * @param createRule the value for litemall_integral_rule.create_rule
     *
     * @mbg.generated
     */
    public void setCreateRule(Float createRule) {
        this.createRule = createRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_integral_rule.delete_rule
     *
     * @return the value of litemall_integral_rule.delete_rule
     *
     * @mbg.generated
     */
    public Float getDeleteRule() {
        return deleteRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_integral_rule.delete_rule
     *
     * @param deleteRule the value for litemall_integral_rule.delete_rule
     *
     * @mbg.generated
     */
    public void setDeleteRule(Float deleteRule) {
        this.deleteRule = deleteRule;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column litemall_integral_rule.create_user
     *
     * @return the value of litemall_integral_rule.create_user
     *
     * @mbg.generated
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column litemall_integral_rule.create_user
     *
     * @param createUser the value for litemall_integral_rule.create_user
     *
     * @mbg.generated
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createRule=").append(createRule);
        sb.append(", deleteRule=").append(deleteRule);
        sb.append(", createUser=").append(createUser);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LitemallIntegralRule other = (LitemallIntegralRule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateRule() == null ? other.getCreateRule() == null : this.getCreateRule().equals(other.getCreateRule()))
            && (this.getDeleteRule() == null ? other.getDeleteRule() == null : this.getDeleteRule().equals(other.getDeleteRule()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateRule() == null) ? 0 : getCreateRule().hashCode());
        result = prime * result + ((getDeleteRule() == null) ? 0 : getDeleteRule().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table litemall_integral_rule
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        id("id", "id", "INTEGER", false),
        createRule("create_rule", "createRule", "REAL", false),
        deleteRule("delete_rule", "deleteRule", "REAL", false),
        createUser("create_user", "createUser", "INTEGER", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String jdbcType;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table litemall_integral_rule
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }
    }
}