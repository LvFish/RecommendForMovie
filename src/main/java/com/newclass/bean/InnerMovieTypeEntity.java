package com.newclass.bean;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/1/28.
 */
@Entity
@Table(name = "inner_movie_type", schema = "ElectronicCommerce", catalog = "")
@IdClass(InnerMovieTypeEntityPK.class)
public class InnerMovieTypeEntity {
    private int mid;
    private int tid;

    @Id
    @Column(name = "mid", nullable = false)
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Id
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnerMovieTypeEntity that = (InnerMovieTypeEntity) o;

        if (mid != that.mid) return false;
        if (tid != that.tid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mid;
        result = 31 * result + tid;
        return result;
    }
}
