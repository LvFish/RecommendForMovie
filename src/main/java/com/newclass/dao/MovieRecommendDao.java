package com.newclass.dao;

import com.newclass.bean.MovieRecommendEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujiawang on 2019/3/14.
 */
@Repository("movieRecommendDao")
public class MovieRecommendDao extends BaseDao {
    public List<MovieRecommendEntity> queryByUId(int uid){
        String hql = "from MovieRecommendEntity as m where m.uid = ? order by m.rank desc";
        Query query = query(hql);
        query.setString(0, String.valueOf(uid));
        return query.list();
    }

    public void deleteByUid(int uid){
        String hql = "delete MovieRecommendEntity as m where m.uid = ?";
        Query query = query(hql);
        query.setString(0,String.valueOf(uid));
        query.executeUpdate();
    }

    public void insert(int uid,int mid,int rank){
        MovieRecommendEntity movieRecommendEntity = new MovieRecommendEntity();
        movieRecommendEntity.setUid(uid);
        movieRecommendEntity.setMid(mid);
        movieRecommendEntity.setRank(rank);
        save(movieRecommendEntity);
    }

}
