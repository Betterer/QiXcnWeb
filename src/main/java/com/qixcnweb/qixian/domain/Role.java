package com.qixcnweb.qixian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色信息表
 * Created by dingxiaochi on 2018/3/5.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {


    private static final long serialVersionUID = -2223816783399954097L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;        //角色名称

    @Column(name = "description")
    private String description;         //角色描述

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    private List<User> userList = new ArrayList<User>();    //关联用户


    @ManyToMany(mappedBy="roleList")
    private List<Authority> authorityList = new ArrayList<Authority>();     //关联权限

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }
}
