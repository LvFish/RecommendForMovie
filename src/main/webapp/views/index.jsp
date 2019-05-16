<%--
  Created by IntelliJ IDEA.
  User: sl
  Date: 2016/12/25
  Time: 下午8:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8"/>
    <title>首页</title>
    <link href="/assets/css/index.css" rel="stylesheet" type="text/css" />
    <script src="/assets/js/jquery-3.2.0.min.js"></script>
    <script src="/assets/js/index.js"></script>
</head>
<body>
<div class="title">
    <div class="title1" id="title1">选电影</div>
    <div class="tag-list">
        <script type="text/javascript">
            for(var i=0;i != movieType.length;i++){
                // var TupleValue= type[0]; //创建一个名称为TupleValue的数组
                document.write("<input type='button'  class='tag' name='tag' " +
                    " onclick='bt1(\""+movieType[i]+"\","+i+")' id='"+i+"' value='"+movieType[i]+"'/>");//输出数组2
            }
        </script>
    </div>
    <div class="page">
        <button id="pre" onclick="gotoPre()">
            <
        </button>
        <input type="text" class="nowPage" readonly="readonly" id="nowPage" value="1"/>
        <button id="next" onclick="gotoNext()">
            >
        </button>
    </div>
    <div class="lr">
        <div class="div5">
            <div class="div1">
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img0" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t0" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img1" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t1" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img2" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t2" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
            </div>
            <div class="div1">
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img3" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t3" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img4" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t4" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img5" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t5" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
            </div>
            <div class="div1">
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img6" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t6" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img7" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t7" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img8" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t8" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
            </div>
            <div class="div1">
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img9" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t9" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img10" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t10" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
                <div class="div2">
                    <div class="div3">
                        <img  class="img" src="/assets/image/timg.jpeg" name = "img" onclick="btn2(this)" id="img11" alt="图片加载失败">
                    </div>
                    <div class="div4">
                        <input id="t11" class="btn" style="border: none" type="button" value="电影"/>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${uid!=null}">

        <div class="div6">
            <span class="title1">你可能爱看</span>
            <div class="mList">
                <input class="mT" type="button" value="" id="m1" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n1">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m2" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n2">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m3" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n3">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m4" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n4">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m5" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n5">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m6" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n6">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m7" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n7">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m8" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n8">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m9" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n9">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
            <div class="mList">
                <input class="mT" type="button" value="" id="m10" onclick="btn(this)">
                <%--<input class="nT" type="button" value="" id="n10">--%>
                <%--<span class="rT">人观看</span>--%>
            </div>
        </div>

        </c:if>
    </div>
</div>

</body>
</html>