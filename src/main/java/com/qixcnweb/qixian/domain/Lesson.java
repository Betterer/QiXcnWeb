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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "name" )
    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id")
    private School school;                  //关联培训机构

    @Column(name = "image")
    private String image;                   //课程图片

    @Column( name = "price" )
    private Double price;                   //课程价格

    @Column(name = "duration")
    private Integer duration;               //学习时长

    @Column( name = "introduce" )
    private String introduce;               //课程介绍

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;              //关联类型表


    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER, mappedBy = "lesson")
    private List<Comment> commentList;      //关联评论

    @ManyToMany
    @JoinTable(
            name="lesson_teacher",
            joinColumns=@JoinColumn(name="lesson_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="teacher_id", referencedColumnName="id"))
    private List<Teacher> teacherList;       //关联教师

    @Column(name = "status")
    private Integer status;             //课程状态(0:不可用,1:正常)

    @Transient
    private String imageUrl;                //可访问的教师图片OSS url地址

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
