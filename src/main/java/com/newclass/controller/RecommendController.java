package com.newclass.controller;

import com.newclass.bean.MovieEntity;
import com.newclass.bean.MovieRecommendEntity;
import com.newclass.dao.MovieDao;
import com.newclass.dao.MovieRecommendDao;
import com.newclass.service.RecommendService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liujiawang on 2019/3/13.
 */
@Controller
@RequestMapping("/commend")
public class RecommendController {

    @Autowired
    @Qualifier("recommendService")
    private RecommendService recommendService;

    @Autowired
    @Qualifier("movieDao")
    private MovieDao movieDao;

    @Autowired
    @Qualifier("movieRecommendDao")
    private MovieRecommendDao movieRecommendDao;

    @RequestMapping(value="/forUser" , method = RequestMethod.POST)
    public void forUser(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        JSONObject json = new JSONObject();
//        List<MovieEntity> list = recommendService.recommend(Integer.valueOf(request.getParameter("uid")));
        json.put("recommend",null);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    @RequestMapping(value="/recommend", method = RequestMethod.POST)
    public void recommend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        List<MovieRecommendEntity> recommendEntityList = movieRecommendDao.queryByUId(Integer.valueOf(request.getParameter("uid")));
        List<MovieEntity> list = new ArrayList<>();
        for(int i=0;i<recommendEntityList.size();i++){
            list.add(movieDao.getById(recommendEntityList.get(i).getMid()));
        }
        json.put("recommend",list);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

}
