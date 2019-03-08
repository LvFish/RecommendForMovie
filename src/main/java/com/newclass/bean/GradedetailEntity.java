package com.newclass.bean;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/1/28.
 */
@Entity
@Table(name = "gradedetail", schema = "ElectronicCommerce", catalog = "")
@IdClass(GradedetailEntityPK.class)
public class GradedetailEntity {
    private int uid;
    private int mid;
    private int grade;

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
    @Column(name = "grade", nullable = false)
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradedetailEntity that = (GradedetailEntity) o;

        if (uid != that.uid) return false;
        if (mid != that.mid) return false;
        if (grade != that.grade) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + mid;
        result = 31 * result + grade;
        return result;
    }
}
