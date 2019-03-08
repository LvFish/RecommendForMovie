package com.newclass.dao;

import com.newclass.bean.CoOccurrenceEntity;
import org.springframework.stereotype.Repository;

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
}
