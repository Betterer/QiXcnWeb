package com.qixcnweb.qixian.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**学校类
 * Created by dingxiaochi on 2018/3/16.
 */

@Entity
@Table(name = "school")
public class School implements Serializable {

    private static final long serialVersionUID = 4132185266625012025L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;                  //机构负责人

    @Column( name = "name")
    private String name;

    @Column( name = "image")
    private String image;

    @Column( name = "address")
    private String address;

    @Column( name = "telephone")
    private String telephone;

    @Column( name = "cellphone")
    private String cellphone;

    @Column( name = "email")
    private String email;

    @Column( name = "position")
    private String position;

    @Column( name = "geohash")
    private String geohash;

    @Column( name = "introduce")
    private String introduce;

    @Column( name = "web_site")
    private String webSite;

    @Column( name = "license")
    private String license;

    @Column(name = "status")
    private Integer status;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, mappedBy = "school")
    private List<Lesson> lessonList;                //关联课程


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", geohash='" + geohash + '\'' +
                ", introduce='" + introduce + '\'' +
                ", webSite='" + webSite + '\'' +
                ", license='" + license + '\'' +
                ", status='" + status + '\'' +
                ", lessonList=" + lessonList +
                '}';
    }
}
