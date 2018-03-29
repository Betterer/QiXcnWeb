package com.qixcnweb.qixian.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dingxiaochi on 2018/3/5.
 */
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = -2567268503447312228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name = "role_authority", joinColumns = { @JoinColumn(name = "authority_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roleList = new HashSet<Role>();           //关联角色表


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }
}
