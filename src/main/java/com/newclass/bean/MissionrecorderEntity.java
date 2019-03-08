package com.newclass.bean;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/1/28.
 */
@Entity
@Table(name = "missionrecorder", schema = "ElectronicCommerce", catalog = "")
@IdClass(MissionrecorderEntityPK.class)
public class MissionrecorderEntity {
    private int uid;
    private int mid;
    private int type;

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
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MissionrecorderEntity that = (MissionrecorderEntity) o;

        if (uid != that.uid) return false;
        if (mid != that.mid) return false;
        if (type != that.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + mid;
        result = 31 * result + type;
        return result;
    }
}
