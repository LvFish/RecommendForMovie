package com.newclass.dao;

import com.newclass.bean.MovietypeEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by liujiawang on 2019/3/6.
 */
@Repository("movietypeDao")
public class MovietypeDao extends BaseDao{
    public MovietypeEntity getById(int id) {
        return get(MovietypeEntity.class, id);
    }
}
