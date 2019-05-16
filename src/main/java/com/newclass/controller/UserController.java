package com.newclass.controller;

import com.newclass.bean.UserEntity;
import com.newclass.dao.GradedetailDao;
import com.newclass.dao.MovieRecommendDao;
import com.newclass.dao.MovieViewsDao;
import com.newclass.dao.UserDao;
import com.newclass.service.KmeansService;
import com.newclass.service.RecommendService;
import com.newclass.util.MailUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by liujiawang on 2019/1/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Autowired
    @Qualifier("recommendService")
    private RecommendService recommendService;

    @Autowired
    @Qualifier("kmeansService")
    private KmeansService kmeansService;

    @Autowired
    @Qualifier("movieRecommendDao")
    private MovieRecommendDao movieRecommendDao;

    @Autowired
    @Qualifier("movieViewsDao")
    private MovieViewsDao movieViewsDao;

    @Autowired
    @Qualifier("GradedetailDao")
    private GradedetailDao gradedetailDao;

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password , HttpSession session){
        try {
            System.out.println(username+"   "+password);
            List<UserEntity> list = userDao.getAllEntities();
            System.out.println(list.get(0).getUsername());
            List<UserEntity> users = userDao.getByUserName(username);
            UserEntity user = users.get(0);
            if (password.equals(user.getPassword())) {
                session.setAttribute("uid",user.getUid());
                session.setAttribute("status","true");
                System.out.println("session.uid = "+user.getUid());
                //通过多线程来生成用户的推荐信息
                new Thread() {
                    @Override
                    public void run() {
                        this.setName("userRecommend");//设置名字
                        int uid = user.getUid();
                        try {
                            List<Integer> userList = kmeansService.kmeans(uid);
                            StringBuffer sb = new StringBuffer();
                            sb.append("(");
                            sb.append(userList.get(0));
                            for(int i=1;i<userList.size();i++){
                                sb.append(","+userList.get(i));
                            }
                            sb.append(")");
                            System.out.println(sb.toString()+"     213123123123");
                            Map<String,Double> userSimilary = recommendService.getUserSimilarity(userList,uid);
                            List<Object[]> list = gradedetailDao.getMidByKmeansUid(uid,sb.toString());
                            Map<String,Double> map = new HashMap<>();
                            for(int i=0;i<list.size();i++){
                                String key = list.get(i)[0].toString();
                                String tId = list.get(i)[1].toString();
                                Integer value = Integer.valueOf(list.get(i)[2].toString());
                                if(map.containsKey(key)){
                                    map.put(key,map.get(key)+(value-3)*userSimilary.get(tId));
                                }else{
                                    map.put(key,(value-3)*userSimilary.get(tId));
                                }
                                //System.out.println(list.get(i).toString());
//
                            }
                            //对于
                            map = sortByValueDescending(map);
                            movieRecommendDao.deleteByUid(uid);
                            int tot = 1;
                            for (Map.Entry<String, Double> entry : map.entrySet()) {
                                if(tot>10){
                                    break;
                                }
                                movieRecommendDao.insert(uid,Integer.valueOf(entry.getKey()),tot);
                                tot++;
//                                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                            }


                            //movieRecommendDao.insert(uid,list.get(i),i+1);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
                return "index";
            } else {
                session.setAttribute("message","密码错误");
                return "redirect:/user/login";
            }
        }catch (Exception e){
            session.setAttribute("message","该账号不存在");
            e.printStackTrace();
            return "redirect:/user/login";
        }
    }

    @RequestMapping(value ="/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value ="/register", method = RequestMethod.POST)
    public String register(String username , String password1 , String password2 , String nickname
            , String code , String number , String email , HttpSession session){
        System.out.println(username+" "+password1+" "+password2+" "+code+" "+number);
        if(!code.equals(number)){
            session.setAttribute("message","验证码不正确");
            return "redirect:/user/register";
        }

        if(username==null || username.equals("")){
            session.setAttribute("message","用户名为空");
            return "redirect:/user/register";
        }
        try{
            List<UserEntity> users = userDao.getByUserName(username);
            UserEntity user = users.get(0);
            session.setAttribute("message","用户名已存在");
            return "redirect:/user/register";
        }catch (Exception e){
        }
        if(!password1.equals(password2)){
            session.setAttribute("message","两次密码不一致");
            return "redirect:/user/register";
        }
        userDao.insertUser(username,password1,email,nickname);
        UserEntity user = userDao.getByUserName(username).get(0);
        List<Integer> mIdList = movieViewsDao.getTopMoiveList();
        for(int i=0;i<mIdList.size();i++){
            movieRecommendDao.insert(user.getUid(),mIdList.get(i),i+1);
        }
        session.removeAttribute("message");
        return "redirect:/user/login";
    }


    @RequestMapping(value ="/forget", method = RequestMethod.GET)
    public String forget(){
        return "forget";
    }

    @RequestMapping(value ="/forget", method = RequestMethod.POST)
    public String forget(String username , String password1 , String password2 ,
                         String code , String number , String email , HttpSession session){
//        System.out.println(username+" "+password1+" "+password2+" "+code+" "+number);
        if(!code.equals(number)){
            session.setAttribute("message","验证码不正确");
            return "redirect:/user/forget";
        }

        if(!password1.equals(password2)){
            session.setAttribute("message","两次密码不一致");
            return "redirect:/user/register";
        }
        List<UserEntity> list = userDao.getByUserName(username);
        UserEntity u = null;
        if(list!=null&&list.size()>0){
            u = list.get(0);
        }
        if(u==null||!u.getEmail().equals(email)){
            session.setAttribute("message","身份信息错误");
            return "redirect:/user/forget";
        }
        u.setPassword(password1);
        userDao.save(u);
        session.removeAttribute("message");
        return "redirect:/user/login";
    }

    @ResponseBody
    @RequestMapping(value="/sendEmail",method = RequestMethod.POST)
    public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        String title = "验证码";
        String email = request.getParameter("email");
        StringBuilder builder = new StringBuilder();
        int t = (int)(Math.random()*100000);
        if(t<100000){
            t+=100000;
        }
        String subject = "注册验证码";
        builder.append("<html><head>");
        builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
        builder.append("</head><body>");
        builder.append("尊敬的");
        builder.append(email);
        builder.append("<br /><br />");
        builder.append("\t验证码为：");
        builder.append(t);
        builder.append("<br /><br />");
        builder.append("------------------------------");
        builder.append("<br /><br />");
        String htmlContent = builder.toString();
        try{
            MailUtil.sendMail(email, subject, htmlContent);
            json.put("msg", t);
        }catch(Exception e){
//            ajax = new AjaxUtil(e.getMessage());
            json.put("msg",false);
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String js = json.toString();
        response.getWriter().write(js.toString());
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescending(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
            {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


}
