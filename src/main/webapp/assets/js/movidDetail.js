var id;
var movieDetail;
var movieType;
var movieGrade;
var numberList;
var movieList;
btn = function(val){
    var name = val.id;
    var number =  new Number(name.substring(1));
    window.location.href="/movie/movieDetail?id="+movieList[number-1].mid;
}
init = function(){
    if(movieDetail.url!="default")
        document.getElementById("img").setAttribute("src",movieDetail.url);
    document.getElementById("name").setAttribute("value",movieDetail.name+"("+movieDetail.year+")");
    document.getElementById("type").setAttribute("value",movieType);
    document.getElementById("grade").setAttribute("value",movieGrade.rate+"分");
    document.getElementById("num").setAttribute("value",movieGrade.allnumber+"人评价");
    if(movieGrade.allnumber!=0) {
        document.getElementById("one").setAttribute("value", (movieGrade.onenumber / movieGrade.allnumber * 100).toFixed(2) + "%");
        document.getElementById("two").setAttribute("value", (movieGrade.twonumber / movieGrade.allnumber * 100).toFixed(2) + "%");
        document.getElementById("three").setAttribute("value", (movieGrade.threenumber / movieGrade.allnumber * 100).toFixed(2) + "%");
        document.getElementById("four").setAttribute("value", (movieGrade.fournumber / movieGrade.allnumber * 100).toFixed(2) + "%");
        document.getElementById("five").setAttribute("value", (movieGrade.fivenumber / movieGrade.allnumber * 100).toFixed(2) + "%");
        document.getElementById("c1").style.width = ((movieGrade.onenumber / movieGrade.allnumber * 100) + "px");
        document.getElementById("c2").style.width = ((movieGrade.twonumber / movieGrade.allnumber * 100) + "px");
        document.getElementById("c3").style.width = ((movieGrade.threenumber / movieGrade.allnumber * 100) + "px");
        document.getElementById("c4").style.width = ((movieGrade.fournumber / movieGrade.allnumber * 100) + "px");
        document.getElementById("c5").style.width = ((movieGrade.fivenumber / movieGrade.allnumber * 100) + "px");
    }
    for(var i=0; i<10&&i<numberList.length; i++){
        var tempM = "m"+(i+1).toString();
        var tempN = "n"+(i+1).toString();
        document.getElementById(tempM).setAttribute("value",movieList[i].name);
        document.getElementById(tempN).setAttribute("value",numberList[i]+"人观看");
    }
}
$(document).ready(function(){
    $.post("/movie/queryById",
        {

        },
        function(data,status){
            if(status){
                // console.log(data.msg);
                id = data.msg.id;
                movieDetail = data.msg;
                movieType = data.movieType;
                movieGrade = data.movieGrade;
                numberList = data.numberList;
                movieList = data.movieList;
                console.log(movieGrade);
                console.log(movieDetail);
                console.log(movieType);
                init();
            }else{
                alert("查询失败");
            }
        });

});
