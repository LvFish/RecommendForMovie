package com.newclass.controller;

import com.newclass.bean.MovieEntity;
import com.newclass.dao.CoOccurrenceDao;
import com.newclass.dao.GradedetailDao;
import com.newclass.dao.MovieDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiawang on 2019/3/8.
 */
@Controller
@RequestMapping("/init")
public class InitController {
    //计算所有电影的共值
    @Autowired
    @Qualifier("GradedetailDao")
    private GradedetailDao gradedetailDao;

    @Autowired
    @Qualifier("CoOccurrenceDao")
    private CoOccurrenceDao coOccurrenceDao;

    @Autowired
    @Qualifier("movieDao")
    private MovieDao movieDao;

    @RequestMapping(value = "/oc", method = RequestMethod.POST)
    public void oc(HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();

        List<MovieEntity> list = movieDao.getAllEntities();
//        json.put("msg4",list.get(0));
//        json.put("msg5",list.get(1));
//        json.put("msg1",gradedetailDao.getNumberById(list.get(0).getMid()));
//        json.put("msg2",gradedetailDao.getNumberById(list.get(1).getMid()));
//        json.put("msg3",gradedetailDao.getNumberBetweenId(list.get(0).getMid(),list.get(1).getMid()));
        int xNumber,yNumber,xid,yid;
        List<Integer> numberList = new ArrayList<>();       //每部电影别看了多少次
        for(int i=0; i<list.size(); i++){
            xNumber = Integer.valueOf(gradedetailDao.getNumberById(list.get(i).getMid()));
            numberList.add(xNumber);
        }
        for(int i=0; i<list.size()-1; i++){
            xid = list.get(i).getMid();
            for(int j=i+1; j<list.size(); j++){
                yid = list.get(j).getMid();
                yNumber = Integer.valueOf(gradedetailDao.getNumberBetweenId(xid,yid));
                coOccurrenceDao.insertCo(xid,yid,yNumber);
            }
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }
}
