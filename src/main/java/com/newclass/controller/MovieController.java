package com.newclass.controller;

import com.newclass.bean.*;
import com.newclass.dao.*;
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
    @Qualifier("GradedetailDao")
    private GradedetailDao gradedetailDao;
    @Autowired
    @Qualifier("innerMovieTypeDao")
    private InnerMovieTypeDao innerMovieTypeDao;
    @Autowired
    @Qualifier("movietypeDao")
    private MovietypeDao movietypeDao;
    @Autowired
    @Qualifier("CoOccurrenceDao")
    private CoOccurrenceDao occurrenceDao;
    @Autowired
    @Qualifier("movieViewsDao")
    private MovieViewsDao movieViewsDao;
    @Autowired
    @Qualifier("movieRecommendDao")
    private MovieRecommendDao movieRecommendDao;
    @Autowired
    @Qualifier("recommendService")
    private RecommendService recommendService;

    @RequestMapping(value = "/queryByType", method = RequestMethod.POST)
    public void queryByType(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
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
        if(session.getAttribute("uid")!=null) {
            List<MovieRecommendEntity> recommendEntityList = movieRecommendDao.queryByUId(
                    Integer.valueOf(session.getAttribute("uid").toString()));
            List<MovieEntity> list2 = new ArrayList<>();
            for (int i = 0; i < recommendEntityList.size(); i++) {
                list2.add(movieDao.getById(recommendEntityList.get(i).getMid()));
            }
            json.put("movieList", list2);
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    @RequestMapping(value="/movieDetail")
    public String movieDetail(HttpServletRequest request, HttpSession session){
        session.removeAttribute("rate");
        session.setAttribute("id",request.getParameter("id"));
        Object uId = session.getAttribute("uid");
        if(uId!=null){
            System.out.println(uId.toString()+"-----------------");
            List<GradedetailEntity> list = gradedetailDao.getByUid(Integer.valueOf(uId.toString()),Integer.valueOf(request.getParameter("id").toString()));
            if(list!=null&&list.size()!=0){
                System.out.println("-----------"+list.get(0).getGrade());
                session.setAttribute("rate",list.get(0).getGrade());
            }
        }
        return "movieDetail";
    }

    @RequestMapping(value = "/queryById", method = RequestMethod.POST)
    public void queryById(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {
        String id;
        id = request.getParameter("id");
        session.removeAttribute("rate");
        if(id==null)
            id = (String)request.getSession().getAttribute("id");
//        System.out.println(id);
        JSONObject json = new JSONObject();
        int mid = Integer.parseInt(id);
        Object uId = session.getAttribute("uid");
        if(uId!=null){
            System.out.println(uId.toString()+"-----------------");
            List<GradedetailEntity> list = gradedetailDao.getByUid(Integer.valueOf(uId.toString()),mid);
            if(list!=null&&list.size()!=0){
                System.out.println("-----------"+list.get(0).getGrade());
                session.setAttribute("rate",list.get(0).getGrade());
            }
        }
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
        System.out.println("mid+    "+mid);
        List<CoOccurrenceEntity> lists = new ArrayList<>();
        if(uId==null) {
            lists = occurrenceDao.queryByXidDesc(mid);
        }else{
            lists = occurrenceDao.queryByIdAndUIdDesc(mid,Integer.valueOf(uId.toString()));
        }
        List<MovieEntity> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        int t = 0;
        for(int i=0;i<lists.size();i++){
            t = lists.get(i).getYid();
            if(t==mid){
                t = lists.get(i).getXid();
            }
            System.out.println(t+"-----------------------");
            list2.add(movieDao.getById(t));
            list3.add(Integer.valueOf(movieViewsDao.getNumberByMid(t)));
        }
        MovieEntity tem;
        for(int i=0;i<10;i++){
            for(int j=i+1;j<10;j++){
                if(list3.get(i)<list3.get(j)){
                    t = list3.get(i);
                    list3.set(i,list3.get(j));
                    list3.set(j,t);
                    tem = list2.get(i);
                    list2.set(i,list2.get(j));
                    list2.set(j,tem);
                }

            }
        }
        json.put("numberList",list3);
        json.put("movieList",list2);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    @RequestMapping(value="/movieRecommend")
    public void movieRecommend(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String id;
        id = request.getParameter("id");
        if(id==null)
            id = (String)request.getSession().getAttribute("id");
//        System.out.println(id);
        JSONObject json = new JSONObject();
        int mid = Integer.parseInt(id);

        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    @RequestMapping(value="/gradeMovie")
    public  void gradeMovie(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int uId,mId,value;
        uId = Integer.valueOf(request.getSession().getAttribute("uid").toString());
        mId = Integer.valueOf(request.getSession().getAttribute("id").toString());
        value = Integer.valueOf(request.getParameter("value"));
        request.getSession().setAttribute("rate",value);
        GradedetailEntity gradedetailEntity = new GradedetailEntity();
        gradedetailEntity.setUid(uId);
        gradedetailEntity.setMid(mId);
        gradedetailEntity.setGrade(value);
        gradedetailDao.save(gradedetailEntity);
        GradeEntity grade = gradeDao.queryByMid(mId).get(0);
        switch (value){
            case 1:
                grade.setOnenumber(grade.getOnenumber()+1);break;
            case 2:
                grade.setTwonumber(grade.getTwonumber()+1);break;
            case 3:
                grade.setThreenumber(grade.getThreenumber()+1);break;
            case 4:
                grade.setFournumber(grade.getFournumber()+1);break;
            case 5:
                grade.setFivenumber(grade.getFivenumber()+1);break;
        }
        grade.setAllnumber(grade.getAllnumber()+1);
        gradeDao.update(grade);
        new Thread() {
            @Override
            public void run() {
                this.setName("gradeMovie");//设置名字
                recommendService.updateSimilarity(uId,mId,value);
            }
        }.start();
        JSONObject json = new JSONObject();
        json.put("msg",true);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    @RequestMapping(value="updateMovieGrade")
    public  void updateMovieGrade(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int uId,mId,value;
        uId = Integer.valueOf(request.getParameter("uId"));
        mId = Integer.valueOf(request.getParameter("mId"));
        value = Integer.valueOf(request.getParameter("value"));
        request.getSession().setAttribute("rate",value);
        GradedetailEntity gradedetailEntity = gradedetailDao.getByUid(uId,mId).get(0);
        gradedetailEntity.setGrade(value);
        gradedetailDao.update(gradedetailEntity);
        JSONObject json = new JSONObject();
        json.put("msg",true);
        json.put("grade",value);
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }
}
