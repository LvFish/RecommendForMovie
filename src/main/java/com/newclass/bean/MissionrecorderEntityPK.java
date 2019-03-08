package com.newclass.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by liujiawang on 2019/1/28.
 */
public class MissionrecorderEntityPK implements Serializable {
    private int uid;
    private int mid;

    @Column(name = "uid", nullable = false)
    @Id
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Column(name = "mid", nullable = false)
    @Id
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissionrecorderEntityPK that = (MissionrecorderEntityPK) o;

        if (uid != that.uid) return false;
        if (mid != that.mid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + mid;
        return result;
    }
}
