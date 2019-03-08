package com.newclass.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by liujiawang on 2019/1/28.
 */
public class InnerMovieTypeEntityPK implements Serializable {
    private int mid;
    private int tid;

    @Column(name = "mid", nullable = false)
    @Id
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Column(name = "tid", nullable = false)
    @Id
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

        InnerMovieTypeEntityPK that = (InnerMovieTypeEntityPK) o;

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
