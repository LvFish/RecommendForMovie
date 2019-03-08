package com.newclass.bean;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/1/28.
 */
@Entity
@Table(name = "movietype", schema = "ElectronicCommerce", catalog = "")
public class MovietypeEntity {
    private int tid;
    private String value;
    private String chiness;

    @Id
    @Column(name = "tid", nullable = false)
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "value", nullable = false, length = 255)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "chiness", nullable = true, length = 255)
    public String getChiness() {
        return chiness;
    }

    public void setChiness(String chiness) {
        this.chiness = chiness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovietypeEntity that = (MovietypeEntity) o;

        if (tid != that.tid) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (chiness != null ? !chiness.equals(that.chiness) : that.chiness != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (chiness != null ? chiness.hashCode() : 0);
        return result;
    }
}
