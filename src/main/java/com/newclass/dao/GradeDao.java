package com.newclass.dao;

import com.newclass.bean.GradeEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujiawang on 2019/3/6.
 */
@Repository("gradeDao")
public class GradeDao extends BaseDao{
    public List<GradeEntity> queryByMid(int id){
        String hql = "from GradeEntity as grade where grade.mid = ?";
        Query query = query(hql);
        query.setString(0,String.valueOf(id));
        return query.list();
    }
}
