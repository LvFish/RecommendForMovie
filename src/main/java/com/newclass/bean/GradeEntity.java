package com.newclass.bean;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by liujiawang on 2019/3/8.
 */
@Entity
@Table(name = "grade", schema = "ElectronicCommerce", catalog = "")
public class GradeEntity {
    private int gid;
    private int mid;
    private int allnumber;
    private int onenumber;
    private int twonumber;
    private int threenumber;
    private int fournumber;
    private int fivenumber;
    private BigDecimal rate;

    @Id
    @Column(name = "gid", nullable = false)
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "mid", nullable = false)
    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "allnumber", nullable = false)
    public int getAllnumber() {
        return allnumber;
    }

    public void setAllnumber(int allnumber) {
        this.allnumber = allnumber;
    }

    @Basic
    @Column(name = "onenumber", nullable = false)
    public int getOnenumber() {
        return onenumber;
    }

    public void setOnenumber(int onenumber) {
        this.onenumber = onenumber;
    }

    @Basic
    @Column(name = "twonumber", nullable = false)
    public int getTwonumber() {
        return twonumber;
    }

    public void setTwonumber(int twonumber) {
        this.twonumber = twonumber;
    }

    @Basic
    @Column(name = "threenumber", nullable = false)
    public int getThreenumber() {
        return threenumber;
    }

    public void setThreenumber(int threenumber) {
        this.threenumber = threenumber;
    }

    @Basic
    @Column(name = "fournumber", nullable = false)
    public int getFournumber() {
        return fournumber;
    }

    public void setFournumber(int fournumber) {
        this.fournumber = fournumber;
    }

    @Basic
    @Column(name = "fivenumber", nullable = false)
    public int getFivenumber() {
        return fivenumber;
    }

    public void setFivenumber(int fivenumber) {
        this.fivenumber = fivenumber;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 1)
    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradeEntity that = (GradeEntity) o;

        if (gid != that.gid) return false;
        if (mid != that.mid) return false;
        if (allnumber != that.allnumber) return false;
        if (onenumber != that.onenumber) return false;
        if (twonumber != that.twonumber) return false;
        if (threenumber != that.threenumber) return false;
        if (fournumber != that.fournumber) return false;
        if (fivenumber != that.fivenumber) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + mid;
        result = 31 * result + allnumber;
        result = 31 * result + onenumber;
        result = 31 * result + twonumber;
        result = 31 * result + threenumber;
        result = 31 * result + fournumber;
        result = 31 * result + fivenumber;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        return result;
    }
}
