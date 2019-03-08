var id;
var movieDetail;
var movieType;
var movieGrade;
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
                console.log(movieGrade);
                console.log(movieDetail);
                console.log(movieType);
                init();
            }else{
                alert("查询失败");
            }
        });

});