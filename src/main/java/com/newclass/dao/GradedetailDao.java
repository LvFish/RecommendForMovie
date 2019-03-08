package com.newclass.dao;

import com.newclass.bean.GradedetailEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liujiawang on 2019/3/8.
 */
@Repository("GradedetailDao")
public class GradedetailDao extends BaseDao{
    public List<GradedetailEntity> getAllEntities(){
        String hql = "from GradedetailEntity";
        Query query = query(hql);
        return query.list();
    }

    public String getNumberById(int id){
        //select count(*) from gradedetail where gradedetail.mid = 1;
        String hql = "select count(*) from GradedetailEntity grade where grade.mid = "+id;
        Query query = getSession().createQuery(hql);
        return query.list().get(0).toString();
    }

    public String getNumberBetweenId(int xid,int yid){
        //select count(*) from gradedetail m1,gradedetail m2 where m1.mid = 1 and m2.mid = 2 and m1.uid = m2.uid;
//        String hql = "select count(*) from GradedetailEntity grade1,GradedetailEntity grade2 where" +
//                " grade1.mid = "+xid+" grade2.mid = "+yid+" and grade1.uid = grade2.uid";
//        Query query = getSession().createQuery(hql);
//        return query.list().get(0).toString();
        SQLQuery SQLquery = getSession().createSQLQuery("select count(*) from ElectronicCommerce.gradedetail m1," +
                "ElectronicCommerce.gradedetail m2 where m1.mid = 1 and m2.mid = 2 and m1.uid = m2.uid");
        List list = SQLquery.list();
        return list.get(0).toString();
    }
}
