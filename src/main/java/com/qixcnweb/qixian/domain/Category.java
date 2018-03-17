package com.qixcnweb.qixian.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**类型类
 * Created by dingxiaochi on 2018/3/16.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = -7505642973048180448L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "name" )
    private String name;

    @Column( name = "level" )
    private Integer level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Category parentCategory;        //父级类型

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Lesson> lessonList;        //关联课程

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
}
