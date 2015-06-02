/**
 * Created by zxk on 2015/4/29.
 */

$(document).ready(function(){
    $(".check_one").click(function(){
    	
        var obj = $(this).next().css("display");   
        if(obj == "none"){
            $(this).next().show();           
        };
    });

    $(".check_two").click(function(){
    	
        var obj = $(this).css("display");
        if(obj == "block"){
            $(this).hide();
        };
    });
});
