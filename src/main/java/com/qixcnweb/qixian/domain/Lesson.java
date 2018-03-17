package com.qixcnweb.qixian.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**课程类
 * Created by dingxiaochi on 2018/3/16.
 */
@Entity
@Table(name = "lesson")
public class Lesson implements Serializable {

    private static final long serialVersionUID = -8998157940601916649L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "name" )
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;                  //关联培训机构

    @Column( name = "price" )
    private Double price;

    @Column( name = "introduce" )
    private String introduce;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;              //关联类型表

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER, mappedBy = "lesson")
    private List<Comment> commentList;      //关联评论

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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
