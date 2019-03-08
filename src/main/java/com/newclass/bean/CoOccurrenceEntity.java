package com.newclass.bean;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/3/8.
 */
@Entity
@Table(name = "co_occurrence", schema = "ElectronicCommerce", catalog = "")
public class CoOccurrenceEntity {
    private int oid;
    private Integer xid;
    private Integer yid;
    private Integer value;

    @Id
    @Column(name = "oid", nullable = false)
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "xid", nullable = true)
    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    @Basic
    @Column(name = "yid", nullable = true)
    public Integer getYid() {
        return yid;
    }

    public void setYid(Integer yid) {
        this.yid = yid;
    }

    @Basic
    @Column(name = "value", nullable = true)
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoOccurrenceEntity that = (CoOccurrenceEntity) o;

        if (oid != that.oid) return false;
        if (xid != null ? !xid.equals(that.xid) : that.xid != null) return false;
        if (yid != null ? !yid.equals(that.yid) : that.yid != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid;
        result = 31 * result + (xid != null ? xid.hashCode() : 0);
        result = 31 * result + (yid != null ? yid.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
