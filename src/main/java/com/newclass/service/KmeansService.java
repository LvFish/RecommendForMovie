package com.newclass.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.*;

/**
 * Created by liujiawang on 2019/4/23.
 */

class Point{
    float type[];
    int flag = -1;
    public Point(){
        type = new float[20];
        for(int i=1;i<=18;i++){
            type[i] = 0;
        }
    }
    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=18;i++){
            sb.append(type[i]+" ");
        }
        return sb.toString();
    }
}

@Transactional
@Service("kmeansService")
public class KmeansService {

    Point pc[] = null;
    Point pcore[] = null;
    Point pcoren[] = null;
    int allNumber = 0;
    //计算新的聚类中心
    public void calAverage(){
        for(int i=0;i<pcore.length;i++){
            // System.out.println("属于<"+pcore[i]+">的点有：");
            float lengthX = 0;
            float lengthY = 0;
            float lens[] = new float[20];
            for(int t=1;t<=18;t++){
                lens[t] = 0;
            }
            int number = 0;
            for(int j=0;j<allNumber;j++){
                if(pc[j].flag == (i+1)){
                    //  System.out.println(pc[j]);
//					lengthX += pc[j].x;
//					lengthY += pc[j].y;
                    for(int t=1;t<=18;t++){
                        lens[t] += pc[j].type[t];
                    }
                    number++;
                }
            }
            pcoren[i] = new Point();
//			pcoren[i].x = lengthX / number;
//			pcoren[i].y = lengthY / number;
            for(int t=1;t<=18;t++){
                pcoren[i].type[t] = lens[t]/number;
            }
            pcoren[i].flag = 0;
            //  System.out.println("新的聚类中心为<"+pcoren[i]+">");

        }
    }

    public void moveCore(int t){
        //  System.out.println(t+"-------------------------");
        //  System.out.println();
        //  System.out.println();
        searchBelong();
        calAverage();
        double moveDist = 0;
        int flag = 0;
        //判断迭代是否完毕
        for(int i=0;i<pcore.length;i++){
            flag = 0;
            moveDist = distPPoint(pcore[i], pcoren[i]);
            if(moveDist > 0.1){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            // System.out.println("迭代完毕");
        }else{
            copyCore(pcore,pcoren);
            moveCore(t+1);
        }
    }

    public void copyCore(Point[] oldCore,Point[] newCore){
        for(int i=0;i<pcore.length;i++){
//			oldCore[i].x = newCore[i].x;
//			oldCore[i].y = newCore[i].y;
            for(int t=1;t<=18;t++){
                oldCore[i].type[t] = newCore[i].type[t];
            }
            oldCore[i].flag = 0;
        }
    }

    public void searchBelong(){
        for(int i=0;i<allNumber;i++){
            double dist = Float.MAX_VALUE;
            int label = -1;
            for(int j=0;j<pcore.length;j++){
                double distance = distPPoint(pc[i],pcore[j]);
                if(distance < dist){
                    dist = distance;
                    label = j;
                }
            }
            pc[i].flag = label + 1;
        }
    }

    public double distPPoint(Point i,Point j){
        float sum = 0;
        for(int t=1;t<=18;t++){
            //System.out.println(allNumber);
            sum += Math.pow(i.type[t]-j.type[t],2);
        }
        return Math.sqrt(sum);
    }

    public List<Integer> kmeans(int uId) throws SQLException, ClassNotFoundException {
        Map<Integer,Map<Integer,Integer>> maps = new HashMap<>();
        String url="jdbc:mysql://127.0.0.1:3306/ElectronicCommerce";


        String user="root";

        String password="root";
        int cnt = 0;
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(url, user  , password);
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select gd.uid,imt.tid,count(imt.tid) from gradedetail gd,movie m,inner_movie_type imt where \n" +
                "-- gd.uid = 1 and \n" +
                "gd.mid = m.mid\n" +
                "and m.mid = imt.mid\n" +
                "group by gd.uid,imt.tid\n"
                + "order by gd.uid");
        Integer uid,tid,number,t;
        Map<Integer,Integer> temp;
        Map<Integer,Integer> findUid = new HashMap<>();
        while(rs.next()){
            uid = rs.getInt(1);
            tid = rs.getInt(2);
            number = rs.getInt(3);
            temp = maps.get(uid);
            if(temp == null){
                temp = new HashMap<>();
                temp.put(tid,number);
                maps.put(uid,temp);
            }else{
                temp.put(tid,number);
            }
        }
        //关闭资源
        rs.close();
        st.close();
        conn.close();
        cnt = maps.size();
        pc = new Point[cnt+5];
        pcore = new Point[60];
        pcoren = new Point[60];
        Map<Integer,Integer> values;
        int cntt = 0 ;
        int find = 0;
        for (Map.Entry<Integer, Map<Integer,Integer>> entry : maps.entrySet()) {
            pc[cntt] = new Point();
            uid = entry.getKey();
            findUid.put(cntt,uid);
            if(uid.equals(uId)){
                find = cntt;
            }
            //System.out.println(uid+" "+cntt);
            values = entry.getValue();
            for (Map.Entry<Integer, Integer> va : values.entrySet()) {
                tid = va.getKey();
                number = va.getValue();
                //System.out.println(uid+" "+tid+" "+number);
                pc[cntt].type[tid] = number;
            }
            cntt++;
        }
        //System.out.println(cntt);
        allNumber = cntt;
        int tt[] = new int[60];
        tt[0] = new Random().nextInt(cntt);
        pcore[0] = new Point();
        for(int i=1;i<=18;i++){
            pcore[0].type[i] = pc[tt[0]].type[i];
        }
        for(int i=0;i<60;i++){
            int flag = 0;
            int tempRandom = new Random().nextInt(cntt);
            for(int j=0;j<i;j++){
                if(tt[j]==tempRandom){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                i--;
            }else{
                tt[i] = tempRandom;
                pcore[i] = new Point();
                for(int j=1;j<=18;j++){
                    pcore[i].type[j] = pc[tt[i]].type[j];
                }
                pcore[i].flag = 0;	//0表示聚类中心
            }
        }

//		System.out.println("生成随机点如下：");
//		for(int i=0;i<cntt;i++){
//			System.out.println(pc[i]);
//		}
//		System.out.println("生成聚类中心如下");
//		for(int i=0;i<pcore.length;i++){
//			System.out.println("<"+pcore[i]+">");
//		}
        //System.out.println(cnt);
        moveCore(0);
        int flag = pc[find].flag;
        int tex = 0;
        List<Integer> userList = new ArrayList<>();
        for(int i=0;i<allNumber;i++){
            if(pc[i].flag==flag){
                //System.out.print((i+1)+" ");
                userList.add(findUid.get(i));
            }
        }
        //System.out.println(userList.size());
        System.out.println(userList);
        return userList;
    }

}