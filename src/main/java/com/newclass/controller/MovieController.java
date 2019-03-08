package com.newclass.controller;

import com.newclass.bean.GradeEntity;
import com.newclass.bean.InnerMovieTypeEntity;
import com.newclass.bean.MovieEntity;
import com.newclass.bean.MovietypeEntity;
import com.newclass.dao.GradeDao;
import com.newclass.dao.InnerMovieTypeDao;
import com.newclass.dao.MovieDao;
import com.newclass.dao.MovietypeDao;
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
import java.util.List;

/**
 * Created by liujiawang on 2019/3/1.
 */
@Controller
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    @Qualifier("movieDao")
    private MovieDao movieDao;
    @Autowired
    @Qualifier("gradeDao")
    private GradeDao gradeDao;
    @Autowired
    @Qualifier("innerMovieTypeDao")
    private InnerMovieTypeDao innerMovieTypeDao;
    @Autowired
    @Qualifier("movietypeDao")
    private MovietypeDao movietypeDao;
    @RequestMapping(value = "/queryByType", method = RequestMethod.POST)
    public void queryByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        int nowPage = Integer.valueOf(request.getParameter("nowPage"));
        int size = Integer.valueOf(request.getParameter("size"));
        JSONObject json = new JSONObject();
        List<MovieEntity> list = movieDao.getByMovieType(type,nowPage,size);
        System.out.println(movieDao.getNumberByMovieType(type));
        Integer allNumber = Integer.valueOf(movieDao.getNumberByMovieType(type));
        json.put("msg",list);
        json.put("number",allNumber);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    @RequestMapping(value="/movieDetail")
    public String movieDetail(HttpServletRequest request, HttpSession session){
        session.setAttribute("id",request.getParameter("id"));
        return "movieDetail";
    }

    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public void queryById(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
        String id;
        id = request.getParameter("id");
        if(id==null)
            id = (String)request.getSession().getAttribute("id");
//        System.out.println(id);
        JSONObject json = new JSONObject();
        int mid = Integer.parseInt(id);
//        System.out.println(mid);
        MovieEntity movie = movieDao.getById(mid);
//        System.out.println(movie.getName()+"  "+movie.getMid());
        List<InnerMovieTypeEntity> list = innerMovieTypeDao.findByMid(mid);
//        System.out.println(list.get(0).toString());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size(); i++){
            MovietypeEntity type = movietypeDao.getById(list.get(i).getTid());
            if(i!=0){
                sb.append("/");
            }
            sb.append(type.getChiness());
        }
//        System.out.println(types.toString());
        GradeEntity grade = gradeDao.queryByMid(mid).get(0);
        json.put("msg",movie);
        json.put("movieType",sb.toString());
        json.put("movieGrade",grade);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

}
