package com.newclass.bean;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/3/14.
 */
@Entity
@Table(name = "MovieRecommend", schema = "ElectronicCommerce", catalog = "")
public class MovieRecommendEntity {
    private int rid;
    private Integer uid;
    private Integer mid;
    private Integer rank;

    @Id
    @Column(name = "rid", nullable = false)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "uid", nullable = true)
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "mid", nullable = true)
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "rank", nullable = true)
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieRecommendEntity that = (MovieRecommendEntity) o;

        if (rid != that.rid) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (mid != null ? !mid.equals(that.mid) : that.mid != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (mid != null ? mid.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }
}
