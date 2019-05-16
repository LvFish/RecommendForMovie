package com.newclass.service;

import com.newclass.bean.CoOccurrenceEntity;
import com.newclass.bean.GradeEntity;
import com.newclass.bean.GradedetailEntity;
import com.newclass.bean.MovieEntity;
import com.newclass.dao.CoOccurrenceDao;
import com.newclass.dao.GradeDao;
import com.newclass.dao.GradedetailDao;
import com.newclass.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by liujiawang on 2019/3/13.
 */

@Transactional
@Service("recommendService")
public class RecommendService {

    @Autowired
    @Qualifier("GradedetailDao")
    private GradedetailDao gradedetailDao;

    @Autowired
    @Qualifier("gradeDao")
    private GradeDao gradeDao;

    @Autowired
    @Qualifier("CoOccurrenceDao")
    private CoOccurrenceDao occurrenceDao;

    @Autowired
    @Qualifier("movieDao")
    private MovieDao movieDao;

    public List<MovieEntity> recommend(int uid,List userList){
//        System.out.println(uid);
        List<GradedetailEntity> list = gradedetailDao.getByUid(uid);

        Map<Integer,Double> map = new HashMap<>();
        int id,value,mid;
        double tv = 0;

        for(int i=0; i<list.size(); i++){
            id = list.get(i).getMid();
            value = list.get(i).getGrade();
//            long startTime = System.currentTimeMillis();
            List<CoOccurrenceEntity> coList = occurrenceDao.queryByIdAndUIdDesc(id,uid);
//            long endTime = System.currentTimeMillis();
//            System.out.println(endTime-startTime);
//            System.out.println(id+" "+uid);
            for(int j=0; j<coList.size(); j++){
                tv = coList.get(j).getSimilarity().doubleValue()*value;
               // System.out.println(String.valueOf(coList.get(j).getOid())+coList.get(j).getSimilarity());
                if(coList.get(j).getXid()==id){
                    mid = coList.get(j).getYid();
                }else{
                    mid = coList.get(j).getXid();
                }
                if(map.get(mid)!=null){
                    map.put(mid,(map.get(mid)+tv));
                }else{
                    map.put(mid,tv);
                }
            }

        }

        List<Map.Entry<Integer,Double>> list2=new ArrayList(map.entrySet());
        Collections.sort(list2, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<MovieEntity> movieList = new ArrayList<>();
        for (int i=0; i<10&&i<list2.size(); i++) {
            HashMap.Entry<Integer, Double> entry = list2.get(i);
            Integer key = entry.getKey();
            movieList.add(movieDao.getById(key));
//            Double val = entry.getValue();
//            map.put(key,val);
//            System.out.println(key+"  "+val);
        }
        return movieList;
    }

    public void updateSimilarity(int uId,int mId,int value){
        List<GradedetailEntity> gradedetailEntities = gradedetailDao.getByUid(uId);
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<gradedetailEntities.size();i++){
            set.add(gradedetailEntities.get(i).getMid());
        }
        GradeEntity grade = gradeDao.queryByMid(mId).get(0);

        List<GradeEntity> gradeList = gradeDao.getAllEntities();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<gradeList.size();i++){
            grade = gradeList.get(i);
            map.put(grade.getMid(),grade.getAllnumber());
        }
        List<CoOccurrenceEntity> coOccurrenceEntities = occurrenceDao.queryByXidDesc(mId);
        CoOccurrenceEntity entity ;
        BigDecimal bigDecimal;
        Integer tx,ty;
        for(int i=0;i<coOccurrenceEntities.size();i++){
            entity = coOccurrenceEntities.get(i);
            tx = entity.getXid();
            ty = entity.getYid();
            if(tx==mId&&set.contains(ty)){
                entity.setValue(entity.getValue()+1);
            }else if(ty==mId&&set.contains(tx)){
                entity.setValue(entity.getValue()+1);
            }
            bigDecimal = new BigDecimal(entity.getValue());
            int tem = map.get(tx)*map.get(ty);
            if(tem!=0)
                bigDecimal.divide(new BigDecimal(tem),6,BigDecimal.ROUND_HALF_UP);
            entity.setSimilarity(bigDecimal);
            occurrenceDao.update(entity);
        }
    }

    public Map<String,Double> getUserSimilarity(List<Integer> userList,Integer uId){
        Map<String,Double> map = new HashMap<>();
        Integer uNumber = Integer.valueOf(gradedetailDao.getNumberByUId(uId));
        for(int i=0;i<userList.size();i++){
            Integer id = userList.get(i);
            if(!id.equals(uId)){
                Integer number = Integer.valueOf(gradedetailDao.getNumberByUId(id));
                Integer tem = Integer.valueOf(gradedetailDao.getNumberBetweenUId(uId,id));
                map.put(id.toString(),tem*1.0/(uNumber*number));
            }
        }
        return map;
    }

}
