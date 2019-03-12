package com.newclass.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by liujiawang on 2019/3/12.
 */
@Repository("movieViewsDao")
public class MovieViewsDao extends BaseDao{
    public String getNumberByMid(int mid){
        String hql = "select movie.watchNumber from MovieViewsEntity movie where mid = "+mid;
        Query query = getSession().createQuery(hql);
        return query.list().get(0).toString();
    }
}
