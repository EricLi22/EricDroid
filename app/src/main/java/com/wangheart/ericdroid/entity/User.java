package com.wangheart.ericdroid.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Author : eric
 * CreateDate : 2017/9/29  10:17
 * Email : ericli_wang@163.com
 * Version : 2.0
 * Desc :
 * Modified :
 */

@DatabaseTable(tableName = "tb_user")
public class User {

    /**
     * id : 1001
     * name : eric
     * sex : 1
     * birth : 1990-01-05
     */

    @DatabaseField(id = true,index = true,unique = true)
    private int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private int sex;
    @DatabaseField
    private String birth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birth='" + birth + '\'' +
                '}';
    }
}
