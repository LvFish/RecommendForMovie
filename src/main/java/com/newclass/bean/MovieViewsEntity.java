package com.newclass.bean;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/3/8.
 */
@Entity
@Table(name = "MovieViews", schema = "ElectronicCommerce", catalog = "")
public class MovieViewsEntity {
    private int wid;
    private Integer mid;
    private Integer watchNumber;

    @Id
    @Column(name = "wid", nullable = false)
    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
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
    @Column(name = "watchNumber", nullable = true)
    public Integer getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(Integer watchNumber) {
        this.watchNumber = watchNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieViewsEntity that = (MovieViewsEntity) o;

        if (wid != that.wid) return false;
        if (mid != null ? !mid.equals(that.mid) : that.mid != null) return false;
        if (watchNumber != null ? !watchNumber.equals(that.watchNumber) : that.watchNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wid;
        result = 31 * result + (mid != null ? mid.hashCode() : 0);
        result = 31 * result + (watchNumber != null ? watchNumber.hashCode() : 0);
        return result;
    }
}
