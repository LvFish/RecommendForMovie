package com.newclass.dao;

import com.newclass.bean.MovieEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujiawang on 2019/3/1.
 */
@Repository("movieDao")
public class MovieDao extends BaseDao{

    public MovieEntity getById(int id) {
        return get(MovieEntity.class, id);
    }

    public List<MovieEntity> getByMovieName(String MovieName){
        String hql = "from MovieEntity as movie where movie.name=?";
        Query query = query(hql);
        query.setString(0,MovieName);
        List<MovieEntity> movies = query.list();
        return movies;
    }

    public String getNumberByMovieType(String type){
        String hql = "select count(*) from MovieEntity movie,MovietypeEntity type,InnerMovieTypeEntity inn where " +
                " movie.mid = inn.mid and type.tid = inn.tid and type.value = '"+type+"'";
        Query query = getSession().createQuery(hql);
        return query.list().get(0).toString();
    }

    public List<MovieEntity>getByMovieType(String type,int page,int size){
        int number = page*size;
        String hql = "select movie.id,movie.name,movie.year,movie.url,type.chiness from MovieEntity movie,MovietypeEntity type,InnerMovieTypeEntity inn where " +
                " movie.mid = inn.mid and type.tid = inn.tid and type.value = '"+type+"'";
        Query query = getSession().createQuery(hql);
        query.setMaxResults(size);
        query.setFirstResult(number);
        System.out.println(size+"  "+number);
        return query.list();
    }

    public List<MovieEntity> getAllEntities(){
        String hql = "from MovieEntity";
        Query query = query(hql);
        return query.list();
    }

}
