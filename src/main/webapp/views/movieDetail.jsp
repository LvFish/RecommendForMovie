<%--
  Created by IntelliJ IDEA.
  User: liujiawang
  Date: 2019/3/7
  Time: 6:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>电影推荐系统</title>
    <link href="/assets/css/movieDetail.css" rel="stylesheet" type="text/css" />
    <script src="/assets/js/jquery-3.2.0.min.js"></script>
    <script src="/assets/js/movidDetail.js"></script>
</head>
<body>
    <div>
        <div class="div1">
            <div>
                <input type="button" value="电影" name="name" class="txt1" id="name">
            </div>
            <div>
                <div class="div2">
                <div>
                    <img src="/assets/image/timg.jpeg" class="img" name="img" id="img" alt="图片加载失败">
                </div>
                <div>
                    <input type="button" value="电影" name="name" class="txt2" id="type">
                </div>
            </div>
                <div class="div2">

            </div>
                <div class="div2">
                <div>
                    <text class="txt3">评分</text>
                </div>
                <div class="div4">
                    <input type="button" value="0.0" class="txt4" id="grade">
                    <input type="button" value="0人评价" class="txt5" id="num">
                </div>
                <div class="div4">
                    <text class="txt3">5星</text>
                    <input type="button" class="color" id="c5">
                    <input type="button" value="0.0%" class="txt4" id="five">
                </div>
                <div class="div4">
                    <text class="txt3">4星</text>
                    <input type="button" class="color" id="c4">
                    <input type="button" value="0.0%" class="txt4" id="four">
                </div>
                <div class="div4">
                    <text class="txt3">3星</text>
                    <input type="button" class="color" id="c3">
                    <input type="button" value="0.0%" class="txt4" id="three">
                </div>
                <div class="div4">
                    <text class="txt3">2星</text>
                    <input type="button" class="color" id="c2">
                    <input type="button" value="0.0%" class="txt4" id="two">
                </div>
                <div class="div4">
                    <text class="txt3">1星</text>
                    <input type="button" class="color" id="c1">
                    <input type="button" value="0.0%" class="txt4" id="one">
                </div>
                <div></div>
            </div>
            </div>
        </div>

    </div>
</body>
</html>