package com.newclass.dao;

import com.newclass.bean.CoOccurrenceEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujiawang on 2019/3/8.
 */
@Repository("CoOccurrenceDao")
public class CoOccurrenceDao extends BaseDao {
    public void insertCo(int xid,int yid,int value){
        CoOccurrenceEntity co = new CoOccurrenceEntity();
        co.setXid(xid);
        co.setYid(yid);
        co.setValue(value);
        save(co);
    }

    public List<CoOccurrenceEntity> queryByXidDesc(int xid){
        String hql = "from CoOccurrenceEntity co where co.xid = ? or co.yid = ? order by co.value desc";
        Query query = query(hql);
        query.setMaxResults(10);
        query.setString(0,String.valueOf(xid));
        query.setString(1,String.valueOf(xid));
        return query.list();
    }


}
