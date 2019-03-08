package com.newclass.dao;

import com.newclass.bean.InnerMovieTypeEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujiawang on 2019/3/6.
 */
@Repository("innerMovieTypeDao")
public class InnerMovieTypeDao extends BaseDao{
    public InnerMovieTypeEntity getById(int id) {
        return get(InnerMovieTypeEntity.class, id);
    }

    public List<InnerMovieTypeEntity> findByMid(int id){
        String hql = "from InnerMovieTypeEntity as movies where movies.mid = ?";
        Query query = query(hql);
        query.setString(0,String.valueOf(id));
        System.out.println(hql);
        return query.list();
    }
}
