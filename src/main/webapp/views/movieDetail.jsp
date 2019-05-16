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
            <div class="title1" id="title1"><a href="/index" style="color:green;text-decoration:none;">选电影</a></div>
            <div>
                <input type="button" value="电影" name="name" class="txt1" id="name">
            </div>
            <div class="line">
                <div class="line1">
                <div class="div2">
                <div>
                    <img src="/assets/image/timg.jpeg" class="img" name="img" id="img" alt="图片加载失败">
                </div>
                <div>
                    <input type="button" value="电影" name="name" class="txt2" id="type">
                    <input type="hidden" value="${id}" id="movieId">
                </div>
            </div>
                <div class="div2">
                <c:if test="${uid!=null}">
                    <c:if test="${rate!=null}">
                        你的评分是：${rate}
                    </c:if>
                    <c:if test="${rate==null}">
                        请评分。
                        <div class="button-div">
                            <button onclick="updateGrade(1)" class="grdae-button">1</button>
                            <button onclick="updateGrade(2)" class="grdae-button">2</button>
                            <button onclick="updateGrade(3)" class="grdae-button">3</button>
                            <button onclick="updateGrade(4)" class="grdae-button">4</button>
                            <button onclick="updateGrade(5)" class="grdae-button">5</button>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${uid==null}">
                    请评分。
                    <div class="button-div">
                        <button onclick="updateGrade(1)" class="grdae-button">1</button>
                        <button onclick="updateGrade(2)" class="grdae-button">2</button>
                        <button onclick="updateGrade(3)" class="grdae-button">3</button>
                        <button onclick="updateGrade(4)" class="grdae-button">4</button>
                        <button onclick="updateGrade(5)" class="grdae-button">5</button>
                    </div>
                </c:if>
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
                    <div class="t3">
                    <text class="txt3">5星</text>
                    </div>
                    <div class="color" id="c5"></div>
                    <div class="t4">
                    <input type="button" value="0.0%" class="txt4" id="five">
                    </div>
                </div>
                <div class="div4">
                    <div class="t3">
                    <text class="txt3">4星</text>
                    </div>
                    <div class="color" id="c4"></div>
                    <div class="t4"><input type="button" value="0.0%" class="txt4" id="four"></div>
                </div>
                <div class="div4">
                    <div class="t3"><text class="txt3">3星</text></div>
                    <div class="color" id="c3"></div>
                    <div class="t4"><input type="button" value="0.0%" class="txt4" id="three"></div>
                </div>
                <div class="div4">
                    <div class="t3"><text class="txt3">2星</text></div>
                    <div class="color" id="c2"></div>
                    <div class="t4"><input type="button" value="0.0%" class="txt4" id="two"></div>
                </div>
                <div class="div4">
                    <div class="t3"><text class="txt3">1星</text></div>
                    <div class="color" id="c1"></div>
                    <div class="t4"><input type="button" value="0.0%" class="txt4" id="one"></div>
                </div>
                <div></div>
            </div>
            </div>
                <div class="line2">
                    <span class="title">看了该电影的人还看了</span>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m1" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n1">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m2" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n2">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m3" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n3">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m4" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n4">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m5" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n5">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m6" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n6">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m7" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n7">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m8" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n8">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m9" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n9">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                    <div class="mList">
                        <input class="mT" type="button" value="" id="m10" onclick="btn(this)">
                        <input class="nT" type="button" value="" id="n10">
                        <%--<span class="rT">人观看</span>--%>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
