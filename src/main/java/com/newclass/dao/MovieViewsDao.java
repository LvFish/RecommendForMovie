package com.newclass.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Integer> getTopMoiveList(){
        String hql = "select movie.mid from MovieViewsEntity movie order by watchNumber";
        Query query = getSession().createQuery(hql);
        query.setMaxResults(10);
        return query.list();
    }
}
