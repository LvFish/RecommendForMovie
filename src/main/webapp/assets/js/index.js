var movieType = ["黑色","动作","冒险","恐怖","浪漫","战争","西方","记录","科幻",
    "戏剧","惊悚","儿童","犯罪","奇幻","卡通","喜剧","神秘","音乐"];
var typeValue = ["Film-Noir","Action","Adventure","Horror","Romance","War","Western","Documentary","Sci-Fi"
    ,"Drama","Thriller","Children","Crime","Fantasy","Animation","Comedy","Mystery","Musical"];
var nowPage = 0;
var size = 12;
var sum = 0;
var list ;
var nowType;
var nowId;
var leftPage,rightPage;
bt1 = function (value,id) {
    console.log(nowId+" "+id);
    nowType = value;
    if(nowId!=id) {
        nowId = id;
        nowPage = 0;
        document.getElementById("nowPage").setAttribute("value",(nowPage+1).toString());
    }
    // console.log(value+" "+id);
    var button = document.getElementById(id);
    // console.log(button.getAttribute("value"));
    for(var number=0; number<movieType.length; number++){
        document.getElementById(number.toString()).style.backgroundColor="white";
    }
    button.style.backgroundColor="#4b8ccb";
    $.post("/movie/queryByType",
        {
            type: typeValue[id],
            size: size,
            nowPage:nowPage,
        },
        function(data,status){
            if(status){
                // console.log(data.msg);
                list = data.msg;
                sum = data.number;
                leftPage = 0;
                rightPage = Math.ceil(sum/size);
                changeView(data.number);
                $('html, body').animate({scrollTop:0}, 'slow');
                //登陆成功
                // code = data.msg;
                // document.getElementById('code').setAttribute("value",code);
            }else{
                alert("查询失败");
            }
        });

}

btn2 = function(val){
    var name = val.id;
    var number =  new Number(name.substring(3));
    window.open("/movie/movieDetail?id="+list[number][0]);
}

changeView = function(num){
    // console.log(num);
    num = num - nowPage*size;
    var nId,imgId;
    for(var i=0; i<num&&i<size; i++){
        nId = "t"+i.toString();
        imgId = "img"+i.toString();
        // console.log(nId+"  "+imgId);
        if(i<num){
            var src = "";
            if(list[i][3]=="default")
                src = "/assets/image/timg.jpeg";
            else
                src = list[i][3];
            document.getElementById(nId).setAttribute("value",list[i][1]);
            document.getElementById(imgId).setAttribute("src",src);
        }else{
            document.getElementById(nId).style.display="none";
            document.getElementById(imgId).style.display="none";
        }
    }
}

gotoPre = function(){
    nowPage--
    changeBtn();
    bt1(nowType,nowId);
    document.getElementById("nowPage").setAttribute("value",(nowPage+1).toString());
}

gotoNext = function(){
    nowPage++;
    changeBtn();
    bt1(nowType,nowId);
    document.getElementById("nowPage").setAttribute("value",(nowPage+1).toString());
}

changeBtn = function(){
    console.log(nowPage+" "+leftPage+" "+rightPage);
    if(nowPage <= leftPage){
        document.getElementById("pre").setAttribute("disabled","disabled");
    }else{
        document.getElementById("pre").removeAttribute("disabled");
    }

    if(nowPage >= rightPage){
        document.getElementById("next").setAttribute("disabled","disabled");
    }else{
        document.getElementById("next").removeAttribute("disabled");
    }
}
$(document).ready(function(){
    document.getElementById("0").style.backgroundColor="#4b8ccb";
    nowId = "0";
    nowType = typeValue[0];
    $.post("/movie/queryByType",
        {
            type: typeValue[0],
            size: size,
            nowPage:nowPage,
        },
        function(data,status){
            if(status){
                // console.log(data.msg);
                list = data.msg;
                sum = data.number;
                leftPage = 0;
                rightPage = Math.ceil(sum/size);
                changeView(data.number);
                changeBtn();
                $('html, body').animate({scrollTop:0}, 'slow');
                //登陆成功
                // code = data.msg;
                // document.getElementById('code').setAttribute("value",code);
            }else{
                alert("查询失败");
            }
        });

});



