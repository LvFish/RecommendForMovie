package com.newclass.bean;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liujiawang on 2019/1/28.
 */
@Entity
@Table(name = "usercomment", schema = "ElectronicCommerce", catalog = "")
@IdClass(UsercommentEntityPK.class)
public class UsercommentEntity {
    private int uid;
    private int mid;
    private String comment;
    private Timestamp time;

    @Id
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Id
    @Column(name = "mid", nullable = false)
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "comment", nullable = false, length = 255)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsercommentEntity that = (UsercommentEntity) o;

        if (uid != that.uid) return false;
        if (mid != that.mid) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + mid;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
