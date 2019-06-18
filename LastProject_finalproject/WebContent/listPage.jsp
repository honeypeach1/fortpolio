<%@page import="net.project.hotellist.db.HotelListBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<!--<![endif]-->
<head>

<!-- meta -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Theme Blog - Web Template Design</title>

<!-- stylesheets -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/animate.css">
<link rel="stylesheet" href="assets/css/list_style.css">
<link rel="stylesheet" href="inc/incCSS/css/searchCategory.css">


<!--  Necessary scripts  -->

<script type="text/javascript" src="assets/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="assets/js/jQuery.scrollSpeed.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

<script> 	
	$(document).ready(function(){
		var min;
		var max;
		var star="";
		var review_score="";
		var index;
		var fac_checkNum="";
		var check_in = "\""+$('#check_in').val()+"\"";
		var check_out = "\""+$('#check_out').val()+"\"";
	 //$(function() {
		 $("#slider-range").slider({
         	range: true,
         	min: 0,
         	max: 300000,
         	step: 10000,
         	values: [0, 300000],
         	slide: function(event, ui) {
         		if(ui.values[1] == 300000){
         			$("#amount2").val(ui.values[0]+"원" + " ~ " + ui.values[1]+"+");
         		}else{
         			$("#amount2").val(ui.values[0]+"원" + " ~ " + ui.values[1]+"원");
         		}
             	
             	min = ui.values[0];
             	max = ui.values[1];
         	},
         	stop: function (event, ui) {
         		$.ajax({
         			// url : "ajaxdata.do?input_text="+$(".input_text").val()+"&min="+ui.values[0]+"&max="+ui.values[1],
         			url : "ajaxdataInfo.hdo",
         			type : "POST",
                	data : {"input_text":$("#input_txt").val(),"min":ui.values[0],"max":ui.values[1],"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
                	success : function(InfoList){
                		/* alert("첫번째 통신완료");
                  		alert("통신데이터 값 : " + InfoList); */
                  		$(".listblog-item").remove();
                  		$.ajax({
                 			// url : "ajaxdata.do?input_text="+$(".input_text").val()+"&min="+ui.values[0]+"&max="+ui.values[1],
                 			url : "ajaxdataFac.hdo",
                 			type : "POST",
                        	data : {"input_text":$("#input_txt").val(),"min":ui.values[0],"max":ui.values[1],"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
                        	success : function(FacList){
                        		/* alert("두번쨰 통신완료");
                          		alert("통신데이터 값 : " + FacList); */
                          		$.each(InfoList, function(index, Infoitem){
                          			var div = "<article class='listblog-item'> <div class='listrow'> <div class='col-md-3'>" 
                          			div+="<a href='./RoomDetails.rd?hotel_no="+Infoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>";
                          			div+= "<img src='images/hotelimages/"+Infoitem.hotel_main_image+"' class='img-thumbnail center-block' alt='Blog Post Thumbnail'> </a>";
                          			div+= "</div> <div class='col-md-9'>";
                          			div+= "<p><a href='./RoomDetails.rd?hotel_no="+Infoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>"+Infoitem.hotel_name+"</a>";
                          			for(var i=0; i<Infoitem.hotel_class;i++){
                          				div+= "<img src='images/starimages/star_image.png' width='20px;' height='20px;'>";
                          			}
                          			div+="</p><span class='review_score'>"+Infoitem.review_star+"/5.0</span> <span style='margin-left: 5px;' class='review_text'>";
                          			if(Infoitem.review_star>=4.5){
                          				div+="최고좋음!!";
                          			}else if(Infoitem.review_star>=4.0){
                          				div+="매우좋음!!";
                          			}else if(Infoitem.review_star>=3.5){
                          				div+="좋음!!";
                          			}else if(Infoitem.review_star>=3.0){
                          				div+="보통!";
                          			}else if(Infoitem.review_star<3.0){
                          				div+="별로!";
                          			}
                          			div+="</span><br><div style='margin-top: 5px;'>";
                          			$.each(FacList, function(index, Facitem){
                              			if(Infoitem.hotel_no == Facitem.hotel_no){
                              				div+="<img src='icon/"+Facitem.hotel_option_name+"' width='30px' height='30px' title='"+Facitem.hotel_option_title+"'>";
                              			}
                              		});
                          			div+="</div> <div style='width:700px; height:auto; float:right;'><span class='room_price' style='float:right;'>"+Infoitem.room_price+"원</span></div>";
                          			div+="<div style='width:700px; height:auto; float:right;'><span class='room_btn' style='float:right;'><input type='button' value='예약' class='listblog-button' onclick='detail("+Infoitem.hotel_no+","+check_in+","+check_out+","+$('.people_num').val()+");'></span></div>";
                          			div+="</div></article> <!-- /.blog-item -->";
                          			$(".listcol-md-8").append(div);
                          		});
                        	},
                        	error : function(data){
                        	    alert('통신실패!!');
                        	    alert(data);
                        	},
                        });  
                	},
                	error : function(data){
                	    alert('통신실패!!');
                	    alert(data);
                	},
                });  
			}
     	});
		min = $("#slider-range").slider("values", 0);
		max = $("#slider-range").slider("values", 1);
    	$("#amount2").val($("#slider-range").slider("values", 0)+"원" + " ~ " + $("#slider-range").slider("values", 1)+"+");
     	
    	$(".hotel_classCheck").change(function(){
        	if($(".hotel_classCheck").is(":checked")){
				//컨트롤 ㅡ 액션 ㅡ dao,bean - 돌아오는건 success 옵션
				//alert("체크");
				// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
				// 여기서 체크된 것들만 골라서 배열로 저장을 해야하는데 어떻게 해야하누
		
				// 여러개가 눌렸을 때 전부 출력이 될 수 있게 하나의 객체에 담는다.
				star = "";
				$(".hotel_classCheck").each(function(){  // .each()는 forEach를 뜻한다.
					if($(this).is(":checked")){  // ":checked"를 이용하여 체크가 되어있는지 아닌지 확인한다.
						star += $(this).val();// 체크된 객체를 str에 저장한다.
						if(star.length >=2){
							if(star.charAt(0) == 5 && star.charAt(1) == 3){
								$(".hotel_classCheck").prop('checked', true);
							}
						}
								
					}
				});
				// $("#multiPrint").text(star);  // #multiPrint에 체크된 원소를 출력한다.
				
	   
		    	$.ajax({
		    		url : "ajaxdataInfo.hdo",
         			type : "POST",
                	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	            	//data : str,
	            	success : function(StarInfoList){
	            		$(".listblog-item").remove();
	            		$.ajax({
	    		    		url : "ajaxdataFac.hdo",
	             			type : "POST",
	                    	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	    	            	//data : str,
	    	            	success : function(StarFacList){
	    	            		$.each(StarInfoList, function(index, StarInfoitem){
	    	            			var div = "<article class='listblog-item'> <div class='listrow'> <div class='col-md-3'>" 
	                          		div+="<a href='./RoomDetails.rd?hotel_no="+StarInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>";
                          			div+= "<img src='images/hotelimages/"+StarInfoitem.hotel_main_image+"' class='img-thumbnail center-block' alt='Blog Post Thumbnail'> </a>";
                          			div+= "</div> <div class='col-md-9'>";
                          			div+= "<p><a href='./RoomDetails.rd?hotel_no="+StarInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>"+StarInfoitem.hotel_name+"</a>";
                          			for(var i=0; i<StarInfoitem.hotel_class;i++){
                          				div+= "<img src='images/starimages/star_image.png' width='20px;' height='20px;'>";
                          			}
                          			div+="</p><span class='review_score'>"+StarInfoitem.review_star+"/5.0</span> <span style='margin-left: 5px;' class='review_text'>";
                          			if(StarInfoitem.review_star>=4.5){
                          				div+="최고좋음!!";
                          			}else if(StarInfoitem.review_star>=4.0){
                          				div+="매우좋음!!";
                          			}else if(StarInfoitem.review_star>=3.5){
                          				div+="좋음!!";
                          			}else if(StarInfoitem.review_star>=3.0){
                          				div+="보통!";
                          			}else if(StarInfoitem.review_star<3.0){
                          				div+="별로!";
                          			}
                          			div+="</span><br><div style='margin-top: 5px;'>";
                          			$.each(StarFacList, function(index, StarFacitem){
                              			if(StarInfoitem.hotel_no == StarFacitem.hotel_no){
                              				div+="<img src='icon/"+StarFacitem.hotel_option_name+"' width='30px' height='30px' title='"+StarFacitem.hotel_option_title+"'>";
                              			}
                              		});
                          			div+="</div> <div style='width:700px; height:auto; float:right;'><span class='room_price' style='float:right;'>"+StarInfoitem.room_price+"원</span></div>";
                          			div+="<div style='width:700px; height:auto; float:right;'><span class='room_btn' style='float:right;'><input type='button' value='예약' class='listblog-button' onclick='detail("+StarInfoitem.hotel_no+","+check_in+","+check_out+","+$('.people_num').val()+");'></span></div>";
                          			div+="</div></article> <!-- /.blog-item -->";
                          			$(".listcol-md-8").append(div);
	    	            		});
	    	            	},
	    	            	error : function(){
	    	            	    //alert('통신실패!!');
	    	            	}
	    	            });  
	            		
	            	},
	            	error : function(){
	            	    //alert('통신실패!!');
	            	}
	            });  
			}else{
            	star = "";
            	$.ajax({
		    		url : "ajaxdataInfo.hdo",
         			type : "POST",
                	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	            	//data : str,
	            	success : function(StarInfoList){
	            		$(".listblog-item").remove();
	            		$.ajax({
	    		    		url : "ajaxdataFac.hdo",
	             			type : "POST",
	                    	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	    	            	//data : str,
	    	            	success : function(StarFacList){
	    	            		$.each(StarInfoList, function(index, StarInfoitem){
	    	            			var div = "<article class='listblog-item'> <div class='listrow'> <div class='col-md-3'>" 
	                          		div+="<a href='./RoomDetails.rd?hotel_no="+StarInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>";
                          			div+= "<img src='images/hotelimages/"+StarInfoitem.hotel_main_image+"' class='img-thumbnail center-block' alt='Blog Post Thumbnail'> </a>";
                          			div+= "</div> <div class='col-md-9'>";
                          			div+= "<p><a href='./RoomDetails.rd?hotel_no="+StarInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>"+StarInfoitem.hotel_name+"</a>";
                          			for(var i=0; i<StarInfoitem.hotel_class;i++){
                          				div+= "<img src='images/starimages/star_image.png' width='20px;' height='20px;'>";
                          			}
                          			div+="</p><span class='review_score'>"+StarInfoitem.review_star+"/5.0</span> <span style='margin-left: 5px;' class='review_text'>";
                          			if(StarInfoitem.review_star>=4.5){
                          				div+="최고좋음!!";
                          			}else if(StarInfoitem.review_star>=4.0){
                          				div+="매우좋음!!";
                          			}else if(StarInfoitem.review_star>=3.5){
                          				div+="좋음!!";
                          			}else if(StarInfoitem.review_star>=3.0){
                          				div+="보통!";
                          			}else if(StarInfoitem.review_star<3.0){
                          				div+="별로!";
                          			}
                          			div+="</span><br><div style='margin-top: 5px;'>";
                          			$.each(StarFacList, function(index, StarFacitem){
                              			if(StarInfoitem.hotel_no == StarFacitem.hotel_no){
                              				div+="<img src='icon/"+StarFacitem.hotel_option_name+"' width='30px' height='30px' title='"+StarFacitem.hotel_option_title+"'>";
                              			}
                              		});
                          			div+="</div> <div style='width:700px; height:auto; float:right;'><span class='room_price' style='float:right;'>"+StarInfoitem.room_price+"원</span></div>";
                          			div+="<div style='width:700px; height:auto; float:right;'><span class='room_btn' style='float:right;'><input type='button' value='예약' class='listblog-button' onclick='detail("+StarInfoitem.hotel_no+","+check_in+","+check_out+","+$('.people_num').val()+");'></span></div>";
                          			div+="</div></article> <!-- /.blog-item -->";
                          			$(".listcol-md-8").append(div);
	    	            		});
	    	            	},
	    	            	error : function(){
	    	            	    //alert('통신실패!!');
	    	            	}
	    	            });  
	            		
	            	},
	            	error : function(){
	            	    //alert('통신실패!!');
	            	}
	            });  
        	}
        	// $("#multiPrint").text(star);  // #multiPrint에 체크된 원소를 출력한다.
    	});
    	
    	$(".review_scoreCheck").change(function(){
        	if($(".review_scoreCheck").is(":checked")){
				//컨트롤 ㅡ 액션 ㅡ dao,bean - 돌아오는건 success 옵션
				//alert("체크");
				// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
				// 여기서 체크된 것들만 골라서 배열로 저장을 해야하는데 어떻게 해야하누
		
				// 여러개가 눌렸을 때 전부 출력이 될 수 있게 하나의 객체에 담는다.
				review_score ="";
				$(".review_scoreCheck").each(function(){  // .each()는 forEach를 뜻한다.
					if($(this).is(":checked")){  // ":checked"를 이용하여 체크가 되어있는지 아닌지 확인한다.
						review_score += $(this).val();// 체크된 객체를 str에 저장한다.
						index = $(".review_scoreCheck").index(this);
					}
				});
				var review_scoreCheck = document.querySelectorAll(".review_scoreCheck");
				for(var i=0;i<=index;i++){
					//$(".review_scoreCheck:eq("+index+")").prop('checked', true);
					review_scoreCheck[i].checked = true;
				} 
				
				
		    	 $.ajax({
		    		url : "ajaxdataInfo.hdo",
         			type : "POST",
                	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	            	//data : str,
	            	success : function(ReviewInfoList){
	            		 $(".listblog-item").remove();
	            		 $.ajax({
	    		    		url : "ajaxdataFac.hdo",
	             			type : "POST",
	                    	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	    	            	//data : str,
	    	            	success : function(ReviewFacList){
	    	            		$.each(ReviewInfoList, function(index, ReviewInfoitem){
	    	            			var div = "<article class='listblog-item'> <div class='listrow'> <div class='col-md-3'>" 
	                          		div+="<a href='./RoomDetails.rd?hotel_no="+ReviewInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>";
                          			div+= "<img src='images/hotelimages/"+ReviewInfoitem.hotel_main_image+"' class='img-thumbnail center-block' alt='Blog Post Thumbnail'> </a>";
                          			div+= "</div> <div class='col-md-9'>";
                          			div+= "<p><a href='./RoomDetails.rd?hotel_no="+ReviewInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>"+ReviewInfoitem.hotel_name+"</a>";
                          			for(var i=0; i<ReviewInfoitem.hotel_class;i++){
                          				div+= "<img src='images/starimages/star_image.png' width='20px;' height='20px;'>";
                          			}
                          			div+="</p><span class='review_score'>"+ReviewInfoitem.review_star+"/5.0</span> <span style='margin-left: 5px;' class='review_text'>";
                          			if(ReviewInfoitem.review_star>=4.5){
                          				div+="최고좋음!!";
                          			}else if(ReviewInfoitem.review_star>=4.0){
                          				div+="매우좋음!!";
                          			}else if(ReviewInfoitem.review_star>=3.5){
                          				div+="좋음!!";
                          			}else if(ReviewInfoitem.review_star>=3.0){
                          				div+="보통!";
                          			}else if(ReviewInfoitem.review_star<3.0){
                          				div+="별로!";
                          			}
                          			div+="</span><br><div style='margin-top: 5px;'>";
                          			$.each(ReviewFacList, function(index, ReviewFacitem){
                              			if(ReviewInfoitem.hotel_no == ReviewFacitem.hotel_no){
                              				div+="<img src='icon/"+ReviewFacitem.hotel_option_name+"' width='30px' height='30px' title='"+ReviewFacitem.hotel_option_title+"'>";
                              			}
                              		});
                          			div+="</div> <div style='width:700px; height:auto; float:right;'><span class='room_price' style='float:right;'>"+ReviewInfoitem.room_price+"원</span></div>";
                          			div+="<div style='width:700px; height:auto; float:right;'><span class='room_btn' style='float:right;'><input type='button' value='예약' class='listblog-button' onclick='detail("+ReviewInfoitem.hotel_no+","+check_in+","+check_out+","+$('.people_num').val()+");'></span></div>";
                          			div+="</div></article> <!-- /.blog-item -->";
                          			$(".listcol-md-8").append(div); 
	    	            		});
	    	            	},
	    	            	error : function(){
	    	            	    //alert('통신실패!!');
	    	            	}
	    	            });  
	            		
	            	},
	            	error : function(){
	            	    //alert('통신실패!!');
	            	}
	            });  
			}else{
				review_score = "";
				$.ajax({
		    		url : "ajaxdataInfo.hdo",
         			type : "POST",
                	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	            	//data : str,
	            	success : function(ReviewInfoList){
	            		 $(".listblog-item").remove();
	            		 $.ajax({
	    		    		url : "ajaxdataFac.hdo",
	             			type : "POST",
	                    	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	    	            	//data : str,
	    	            	success : function(ReviewFacList){
	    	            		$.each(ReviewInfoList, function(index, ReviewInfoitem){
	    	            			var div = "<article class='listblog-item'> <div class='listrow'> <div class='col-md-3'>" 
	                          		div+="<a href='./RoomDetails.rd?hotel_no="+ReviewInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>";
                          			div+= "<img src='images/hotelimages/"+ReviewInfoitem.hotel_main_image+"' class='img-thumbnail center-block' alt='Blog Post Thumbnail'> </a>";
                          			div+= "</div> <div class='col-md-9'>";
                          			div+= "<p><a href='./RoomDetails.rd?hotel_no="+ReviewInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>"+ReviewInfoitem.hotel_name+"</a>";
                          			for(var i=0; i<ReviewInfoitem.hotel_class;i++){
                          				div+= "<img src='images/starimages/star_image.png' width='20px;' height='20px;'>";
                          			}
                          			div+="</p><span class='review_score'>"+ReviewInfoitem.review_star+"/5.0</span> <span style='margin-left: 5px;' class='review_text'>";
                          			if(ReviewInfoitem.review_star>=4.5){
                          				div+="최고좋음!!";
                          			}else if(ReviewInfoitem.review_star>=4.0){
                          				div+="매우좋음!!";
                          			}else if(ReviewInfoitem.review_star>=3.5){
                          				div+="좋음!!";
                          			}else if(ReviewInfoitem.review_star>=3.0){
                          				div+="보통!";
                          			}else if(ReviewInfoitem.review_star<3.0){
                          				div+="별로!";
                          			}
                          			div+="</span><br><div style='margin-top: 5px;'>";
                          			$.each(ReviewFacList, function(index, ReviewFacitem){
                              			if(ReviewInfoitem.hotel_no == ReviewFacitem.hotel_no){
                              				div+="<img src='icon/"+ReviewFacitem.hotel_option_name+"' width='30px' height='30px' title='"+ReviewFacitem.hotel_option_title+"'>";
                              			}
                              		});
                          			div+="</div> <div style='width:700px; height:auto; float:right;'><span class='room_price' style='float:right;'>"+ReviewInfoitem.room_price+"원</span></div>";
                          			div+="<div style='width:700px; height:auto; float:right;'><span class='room_btn' style='float:right;'><input type='button' value='예약' class='listblog-button' onclick='detail("+ReviewInfoitem.hotel_no+","+check_in+","+check_out+","+$('.people_num').val()+");'></span></div>";
                          			div+="</div></article> <!-- /.blog-item -->";
                          			$(".listcol-md-8").append(div); 
	    	            		});
	    	            	},
	    	            	error : function(){
	    	            	    //alert('통신실패!!');
	    	            	}
	    	            });  
	            		
	            	},
	            	error : function(){
	            	    //alert('통신실패!!');
	            	}
	            });
        	}
    	});
    	
    	$(".fac_checkbox").change(function(){
        	if($(".fac_checkbox").is(":checked")){
				//컨트롤 ㅡ 액션 ㅡ dao,bean - 돌아오는건 success 옵션
				//alert("체크");
				// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
				// 여기서 체크된 것들만 골라서 배열로 저장을 해야하는데 어떻게 해야하누
		
				// 여러개가 눌렸을 때 전부 출력이 될 수 있게 하나의 객체에 담는다.
				fac_checkNum = "";
				$(".fac_checkbox").each(function(){  // .each()는 forEach를 뜻한다.
					if($(this).is(":checked")){  // ":checked"를 이용하여 체크가 되어있는지 아닌지 확인한다.
						fac_checkNum += $(this).val();// 체크된 객체를 str에 저장한다.
					}
				});
				
				
				$("#multiPrint").text(fac_checkNum);
		    	 $.ajax({
		    		url : "ajaxdataInfo.hdo",
         			type : "POST",
                	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	            	//data : str,
	            	success : function(CategoryInfoList){
	            		 $(".listblog-item").remove();
	            		 $.ajax({
	    		    		url : "ajaxdataFac.hdo",
	             			type : "POST",
	                    	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	    	            	//data : str,
	    	            	success : function(CategoryFacList){
	    	            		$.each(CategoryInfoList, function(index, CategoryInfoitem){
	    	            			var div = "<article class='listblog-item'> <div class='listrow'> <div class='col-md-3'>" 
	                          		div+="<a href='./RoomDetails.rd?hotel_no="+CategoryInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>";
                          			div+= "<img src='images/hotelimages/"+CategoryInfoitem.hotel_main_image+"' class='img-thumbnail center-block' alt='Blog Post Thumbnail'> </a>";
                          			div+= "</div> <div class='col-md-9'>";
                          			div+= "<p><a href='./RoomDetails.rd?hotel_no="+CategoryInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>"+CategoryInfoitem.hotel_name+"</a>";
                          			for(var i=0; i<CategoryInfoitem.hotel_class;i++){
                          				div+= "<img src='images/starimages/star_image.png' width='20px;' height='20px;'>";
                          			}
                          			div+="</p><span class='review_score'>"+CategoryInfoitem.review_star+"/5.0</span> <span style='margin-left: 5px;' class='review_text'>";
                          			if(CategoryInfoitem.review_star>=4.5){
                          				div+="최고좋음!!";
                          			}else if(CategoryInfoitem.review_star>=4.0){
                          				div+="매우좋음!!";
                          			}else if(CategoryInfoitem.review_star>=3.5){
                          				div+="좋음!!";
                          			}else if(CategoryInfoitem.review_star>=3.0){
                          				div+="보통!";
                          			}else if(CategoryInfoitem.review_star<3.0){
                          				div+="별로!";
                          			}
                          			div+="</span><br><div style='margin-top: 5px;'>";
                          			$.each(CategoryFacList, function(index, CategoryFacitem){
                              			if(CategoryInfoitem.hotel_no == CategoryFacitem.hotel_no){
                              				div+="<img src='icon/"+CategoryFacitem.hotel_option_name+"' width='30px' height='30px' title='"+CategoryFacitem.hotel_option_title+"'>";
                              			}
                              		});
                          			div+="</div> <div style='width:700px; height:auto; float:right;'><span class='room_price' style='float:right;'>"+CategoryInfoitem.room_price+"원</span></div>";
                          			div+="<div style='width:700px; height:auto; float:right;'><span class='room_btn' style='float:right;'><input type='button' value='예약' class='listblog-button' onclick='detail("+CategoryInfoitem.hotel_no+","+check_in+","+check_out+","+$('.people_num').val()+");'></span></div>";
                          			div+="</div></article> <!-- /.blog-item -->";
                          			$(".listcol-md-8").append(div); 
	    	            		});
	    	            	},
	    	            	error : function(){
	    	            	    //alert('통신실패!!');
	    	            	}
	    	            });  
	            		
	            	},
	            	error : function(){
	            	    //alert('통신실패!!');
	            	}
	            });   
			}else{ // 모든 체크박스 해제시
				fac_checkNum = "";
				$.ajax({
		    		url : "ajaxdataInfo.hdo",
         			type : "POST",
                	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	            	//data : str,
	            	success : function(CategoryInfoList){
	            		 $(".listblog-item").remove();
	            		 $.ajax({
	    		    		url : "ajaxdataFac.hdo",
	             			type : "POST",
	                    	data : {"input_text":$("#input_txt").val(),"min":min,"max":max,"star":star,"review_score":review_score,"fac_checkNum":fac_checkNum,"people_num":$('.people_num').val()},
	    	            	//data : str,
	    	            	success : function(CategoryFacList){
	    	            		$.each(CategoryInfoList, function(index, CategoryInfoitem){
	    	            			var div = "<article class='listblog-item'> <div class='listrow'> <div class='col-md-3'>" 
	                          		div+="<a href='./RoomDetails.rd?hotel_no="+CategoryInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>";
                          			div+= "<img src='images/hotelimages/"+CategoryInfoitem.hotel_main_image+"' class='img-thumbnail center-block' alt='Blog Post Thumbnail'> </a>";
                          			div+= "</div> <div class='col-md-9'>";
                          			div+= "<p><a href='./RoomDetails.rd?hotel_no="+CategoryInfoitem.hotel_no+"&check_in="+$('#check_in').val()+"&check_out="+$('#check_out').val()+"&people_num="+$('.people_num').val()+"' target='_blank'>"+CategoryInfoitem.hotel_name+"</a>";
                          			for(var i=0; i<CategoryInfoitem.hotel_class;i++){
                          				div+= "<img src='images/starimages/star_image.png' width='20px;' height='20px;'>";
                          			}
                          			div+="</p><span class='review_score'>"+CategoryInfoitem.review_star+"/5.0</span> <span style='margin-left: 5px;' class='review_text'>";
                          			if(CategoryInfoitem.review_star>=4.5){
                          				div+="최고좋음!!";
                          			}else if(CategoryInfoitem.review_star>=4.0){
                          				div+="매우좋음!!";
                          			}else if(CategoryInfoitem.review_star>=3.5){
                          				div+="좋음!!";
                          			}else if(CategoryInfoitem.review_star>=3.0){
                          				div+="보통!";
                          			}else if(CategoryInfoitem.review_star<3.0){
                          				div+="별로!";
                          			}
                          			div+="</span><br><div style='margin-top: 5px;'>";
                          			$.each(CategoryFacList, function(index, CategoryFacitem){
                              			if(CategoryInfoitem.hotel_no == CategoryFacitem.hotel_no){
                              				div+="<img src='icon/"+CategoryFacitem.hotel_option_name+"' width='30px' height='30px' title='"+CategoryFacitem.hotel_option_title+"'>";
                              			}
                              		});
                          			div+="</div> <div style='width:700px; height:auto; float:right;'><span class='room_price' style='float:right;'>"+CategoryInfoitem.room_price+"원</span></div>";
                          			div+="<div style='width:700px; height:auto; float:right;'><span class='room_btn' style='float:right;'><input type='button' value='예약' class='listblog-button' onclick='detail("+CategoryInfoitem.hotel_no+","+check_in+","+check_out+","+$('.people_num').val()+");'></span></div>";
                          			div+="</div></article> <!-- /.blog-item -->";
                          			$(".listcol-md-8").append(div); 
	    	            		});
	    	            	},
	    	            	error : function(){
	    	            	    //alert('통신실패!!');
	    	            	}
	    	            });  
	            		
	            	},
	            	error : function(){
	            	    //alert('통신실패!!');
	            	}
	            });
        	}
        	$("#multiPrint").text(fac_checkNum);  // #multiPrint에 체크된 원소를 출력한다.
    	});
    	
	});
	 
	 // 데이트피커
	 
	   //검색 날짜제한 
             $(function() {              
                 
               //datepicker 한국어로 사용하기 위한 언어설정
               $.datepicker.setDefaults($.datepicker.regional['ko']); 
               
               // 시작일(fromDate)은 종료일(toDate) 이후 날짜 선택 불가
               // 종료일(toDate)은 시작일(fromDate) 이전 날짜 선택 불가
 
               //시작일.
               $('#check_in').datepicker({
                    //dateFormat: "yy-mm-dd",
                    monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
                    dayNamesMin:["일","월","화","수","목","금","토"],
                   //buttonImage: "/jdAdmin/images/calendar.png", // 버튼 이미지
                   //buttonImageOnly : true,             // 버튼 이미지만 표시할지 여부
                   //buttonText: "날짜선택",             // 버튼의 대체 텍스트
                   dateFormat: "yy-mm-dd",             // 날짜의 형식
                   changeMonth: true,                  // 월을 이동하기 위한 선택상자 표시여부
                       minDate: 0,  //오늘 이전  날짜 선택 불가                  // 선택할수있는 최소날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                   onClose: function( selectedDate ) {    
                       // 시작일(fromDate) datepicker가 닫힐때
                       // 종료일(toDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                       $("#check_out").datepicker( "option", "minDate", selectedDate );
                   }                
               });
 
               //종료일
               $('#check_out').datepicker({
                    //dateFormat: "yy-mm-dd",
                    monthNamesShort: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
                    dayNamesMin:["일","월","화","수","목","금","토"], 
                   dateFormat: "yy-mm-dd",
                   changeMonth: true,
                   minDate: 0, // 오늘  날짜 선택 불가
                   onClose: function( selectedDate ) {
                       // 종료일(toDate) datepicker가 닫힐때
                       // 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
                       $("#check_in").datepicker( "option", "maxDate", selectedDate );
                   }                
               });
               
               
               
           });

	 function detail(hotel_no,check_in,check_out,people_num){
		 window.open("./RoomDetails.rd?hotel_no="+hotel_no+"&check_in="+check_in+"&check_out="+check_out+"&people_num="+people_num);
	 }
</script>
<!-- 지도 페이지 추가 시작 -->
<!-- map 토글  -->
<script type="text/javascript">
function map_v(){
	   $( '#map-view' ).toggle();
	   
       map.relayout();
       

}

</script>
<!-- map 토글  -->
<!-- 지도 페이지 추가 끝 -->
</head>

<body>
<%
	request.setCharacterEncoding("UTF-8");
/* 	session.setAttribute("user_num", "1");
	session.setAttribute("user_name", "권혁"); */
	int count = 0;
%>
	<jsp:include page="./inc/header.jsp" flush="false" />
	<!-- Menu -->

	<div class="menu">
		<div class="background_image"
			style="background-image: url(images/mainimages/menu.jpg)"></div>
		<div
			class="menu_content d-flex flex-column align-items-center justify-content-center">
			<ul class="menu_nav_list text-center">
				<li><a href="#">Home</a></li>
				<li><a href="#">About us</a></li>
				<li><a href="#">Rooms</a></li>
				<li><a href="#">News</a></li>
				<li><a href="#">Contact</a></li>
			</ul>
			<div class="menu_review">
				<a href="#">Book Now</a>
			</div>
		</div>
	</div>

	<!-- Home -->

	<div class="home">
		<div class="parallax_background parallax-window"
			data-parallax="scroll" data-image-src="images/mainimages/mainImage.jpg"
			data-speed="0.8"></div>
		<div class="home_container">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="home_content text-center">
							<div class="home_title">
								<h1>
									<font style="font-size: 85px">Hotel & Resort</font>
								</h1>
							</div>
							<div class="home_text">
								<font style="font-size: 25px">What do you want
									Accommodations? Anything searching know!</font>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Search Box -->

		<div class="search_box" style="height: 120px">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="search_box_container d-flex flex-row align-items-center justify-content-start">
						<div class="search_form_container">
							<!-- 검색 폼 태그 -->
								<form class="search_form" action="./goToListPageSearch.hdo"
									method="get" id="search_form">
									<div
										class="d-flex flex-lg-row flex-column align-items-center justify-content-start">
										<!-- 검색창 -->
										<div id="outer" style="margin-right: 5px">
											<div id="inner">
												<section class="demo">
												<div class="cont"> <!--데이트 피커  -->
													<input class="input_text" type="text" id="check_in"
														readonly="readonly" placeholder="체크인"
														style="width: 170px; height: 65px" name="check_in"
															value="${param.check_in }">
													<input class="input_text" type="text" id="check_out"
														readonly="readonly" placeholder="체크아웃"
														style="width: 170px; height: 65px" name="check_out"
															value="${param.check_out }">
												</div>
												</section>
											</div>
										</div>

										<!-- 인원 셀렉트 부분 -->
										<select
											style="width: 200px; height: 65px; margin-left: -1px; margin-right: 2px" class="people_num"
											name="people_num">
											<option value="1" <c:if test="${param.people_num eq 1}" >selected</c:if> >성인 1명</option>
											<option value="2" <c:if test="${param.people_num eq 2}" >selected</c:if> >성인 2명</option>
											<option value="4" <c:if test="${param.people_num eq 4}" >selected</c:if> >가족(4인 기준)</option>
											<option value="5" <c:if test="${param.people_num eq 5}" >selected</c:if> >단체</option>
										</select> 
										<input class="input_text" type="text" name="input_text" id="input_txt"
											placeholder="호텔이나 지역을 입력하세요." 	style="width: 300px; height: 65px"
										value="${param.input_text }"> 
										<input class="search_button" type="submit" value="search">
									</div>
								</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- main article -->
	<div class="horizontal_wrapper">
	<!-- SearchCategory -->
	<div style="float: left;">
	<form action="">
		<fieldset style="width: 200px; margin-right:30px;" class="search_field">
			<h2>검색 조건</h2>
			<!--     <div> -->
			<!--         <ul> -->
			<!--             <li><input id="radio1" name="radio" type="radio" class="radio" checked="checked"> <label for="radio1">Option 1</label></li> -->
			<!--             <li><input id="radio2" name="radio" type="radio" class="radio"> <label for="radio2">Option 2</label></li> -->
			<!--             <li><input id="radio3" name="radio" type="radio" class="radio"> <label for="radio3">Option 3</label></li> -->
			<!--         </ul> -->
			<!--     </div> -->
			<!-- <div> -->
				<!-- <ul> -->
					<h4>1박 평균요금</h4>
					 <p>
        				<input type="text" id="amount2" readonly style="border:0; color:#f6931f; font-weight:bold; width:200px;">
    				</p>
    				<div id="slider-range" style="width: 200px;"></div>



					<!-- <li><input id="checkbox1" name="checkbox" class="checkbox" type="checkbox" value="1">
						<label for="checkbox1">0 ~ 50,000</label></li>
					<li><input id="checkbox2" name="checkbox" class="checkbox" type="checkbox" value="2">
						<label for="checkbox2">50,000 ~ 100,000</label></li>
					<li><input id="checkbox3" name="checkbox" class="checkbox" type="checkbox" value="3">
						<label for="checkbox3">100,000 ~ 150,000</label></li>
					<li><input id="checkbox4" name="checkbox" class="checkbox" type="checkbox" value="4">
						<label for="checkbox4">150,000 ~ 200,000</label></li>
					<li><input id="checkbox5" name="checkbox" class="checkbox" type="checkbox" value="5">
						<label for="checkbox5">200,000+</label></li> -->
				<!-- </ul> -->
			
			<hr>
			<div class="category">
				<ul>
					<h4>호텔 등급</h4>
					<li><input id="checkbox6" name="hotel_classCheck" class="hotel_classCheck" type="checkbox" value="5">
						<label for="checkbox6">5성</label></li>
					<li><input id="checkbox7" name="hotel_classCheck" class="hotel_classCheck" type="checkbox" value="4">
						<label for="checkbox7">4성</label></li>
					<li><input id="checkbox8" name="hotel_classCheck" class="hotel_classCheck" type="checkbox" value="3">
						<label for="checkbox8">3성</label></li>
				</ul>
			</div>
			<hr>
			<div class="category">
				<ul>
					<h4>평점</h4>
					<li><input id="checkbox11" name="review_scoreCheck" class="review_scoreCheck" type="checkbox" value="1">
						<label for="checkbox11">4.5/5.0  이상!!</label></li>
					<li><input id="checkbox12" name="review_scoreCheck" class="review_scoreCheck" type="checkbox" value="2">
						<label for="checkbox12">4.0/5.0 이상!!</label></li>
					<li><input id="checkbox13" name="review_scoreCheck" class="review_scoreCheck" type="checkbox" value="3">
						<label for="checkbox13">3.5/5.0 이상!!</label></li>
					<li><input id="checkbox14" name="review_scoreCheck" class="review_scoreCheck" type="checkbox" value="4">
						<label for="checkbox14">3.0/5.0 이상!!</label></li>
				</ul>
			</div>
			<hr>
			<div class="category">
				<ul>
					<h4>편의시설</h4>
					<li><input id="checkbox16" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="1">
						<label for="checkbox16">와이파이</label></li>
					<li><input id="checkbox17" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="2">
						<label for="checkbox17">수영장</label></li>
					<li><input id="checkbox18" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="3">
						<label for="checkbox18">주차장</label></li>
					<li><input id="checkbox19" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="4">
						<label for="checkbox19">금연</label></li>
					<li><input id="checkbox20" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="5">
						<label for="checkbox20">환전소</label></li>
					<li><input id="checkbox21" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="6">
						<label for="checkbox21">레스토랑</label></li>
					<li><input id="checkbox22" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="7">
						<label for="checkbox22">피트니스</label></li>
					<li><input id="checkbox23" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="8">
						<label for="checkbox23">안내데스크</label></li>
					<li><input id="checkbox24" name="fac_checkbox" class="fac_checkbox" type="checkbox" value="9">
						<label for="checkbox24">칵테일바</label></li>
				</ul>
			</div>
			<hr>
		</fieldset>
	</form>
	</div>
	<!-- SearchCategory -->

	<!-- 리스트 페이지 -->
	
	<!-- <div>
		<span onclick="this.parentNode.style.display = 'none';">&times;</span>
	</div> -->
	<div class="listcontainer" style="float: left;">
			<div class="parentlistrow">

			<!-- blog-contents -->
			<section class="listcol-md-8"> 
			<c:choose>
			<c:when test="${!empty hotelinfoList}">
			<c:forEach items="${hotelinfoList}" var="hotelinfoList">
			<article class="listblog-item">
			<div class="listrow">
				<div class="col-md-3">
					<a href="./RoomDetails.rd?hotel_no=${hotelinfoList.getHotel_num()}&check_in=${param.check_in}&check_out=${param.check_out}&people_num=${param.people_num}" target="_blank"> <!-- 여기 사진 -->
					<img src="images/hotelimages/${hotelinfoList.getHotel_main_image() }"
						class="img-thumbnail center-block" alt="Blog Post Thumbnail">
					</a>
				</div>
				<%
				count++;
				%>
				<div class="col-md-9">
					<p>
						<a href="./RoomDetails.rd?hotel_no=${hotelinfoList.getHotel_num()}&check_in=${param.check_in}&check_out=${param.check_out}&people_num=${param.people_num}" target="_blank">
						${hotelinfoList.getHotel_name() } <!-- 여기 호텔 이름 -->
						</a>
						<c:forEach begin="1" end="${hotelinfoList.getHotel_class()}" step="1">
							<img src="images/starimages/star_image.png" width="20px;" height="20px;">
						</c:forEach>
						
					</p>
					<span class="review_score">${hotelinfoList.getReview_star() }/5.0</span> 
					<span style="margin-left: 5px;" class="review_text">
						<c:choose>
							<c:when test="${hotelinfoList.getReview_star() >= 4.5}">
								최고좋음!!
							</c:when>
							<c:when test="${hotelinfoList.getReview_star() >= 4.0}">
								매우좋음!!
							</c:when>
							<c:when test="${hotelinfoList.getReview_star() >= 3.5}">
								좋음!!
							</c:when>
							<c:when test="${hotelinfoList.getReview_star() >= 3.0}">
								보통!
							</c:when>
							<c:when test="${hotelinfoList.getReview_star() < 3.0}">
								별로!
							</c:when>
						</c:choose>
					</span><br>
					<div style="margin-top: 5px;">
					<c:forEach items="${hotelfacList }" var="hotelfacList">
					<c:if test="${hotelinfoList.getHotel_num() eq hotelfacList.getHotel_num() }">
					<img src="icon/${hotelfacList.getHotel_option_name() }" width="30px" height="30px" title="${hotelfacList.getHotel_option_title() }">
					</c:if>
					</c:forEach>
					</div>
					<!-- <!-- 지도 페이지 추가 시작 
					
						<div id="map-name" onclick="map_v()" style="width:500px; height:auto; float:left;">
						<div class="map-toggle">
							<p class="map_view"> 위치보기
							
							</p>
						</div>
					</div> -->
					<div style="width:200px; height:auto; float:right;"><span class="room_price" style="float:right;">${hotelinfoList.getMinPrice() }원</span></div>
					<div style="width:700px; height:auto; float:right;">
					<span class="room_btn" style="float:right;">
					<input type="button" value="예약" class="listblog-button" onclick="detail('${hotelinfoList.getHotel_num()}','${param.check_in}','${param.check_out}','${param.people_num}');">
					</span>
					</div>
				</div>
			</div>
			
			</article> <!-- /.blog-item -->
			<%-- <!-- 지도 상세페이지 -->
			<div class="map" id="map-view" style="display:none;"> <!-- style="display:none;" -->
				<div class="map-box">
					<h4> <strong> 위치 </strong> </h4> <hr>
				</div>
				<div class="map-detail">
					<jsp:include page="hotelMap.jsp">
						<jsp:param value="${hotelinfoList.getHotel_address()}" name="addr" />
					</jsp:include>
				</div>
			</div>
			<!-- 지도 상세페이지 -->
			<!-- 지도 페이지 추가 끝 --> --%>
			</c:forEach>
			</c:when>
			</c:choose>
			
			<!-- <div class="page-turn">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 text-center">
						<nav>
						<ul class="pagination pagination-sm">
							<li class="disabled"><a href="#" aria-label="Previous">
									<span aria-hidden="true">Prev</span>
							</a></li>
							<li class="active"><a href="index.html">1</a></li>
							<li><a href="page2.html">2</a></li>
							<li><a href="page3.html">3</a></li>
							<li><a href="page4.html">4</a></li>
							<li><a href="page5.html">5</a></li>
							<li><a href="page6.html" aria-label="Next"> <span
									aria-hidden="true">Next</span>
							</a></li>
						</ul>
						/.pagination </nav>
					</div>
				</div>
			</div> -->
			<!-- /.page-turn --> </section>
			<!--  <!-- end of blog-contents 

                    sidebar
                    <aside class="col-md-4 col-sm-8 col-xs-8">
                        <div class="sidebar">
							
							<div id="map" style="width:450px;height:600px;"></div>
								<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 넣으시면 됩니다."></script>
								<script>
									var container = document.getElementById('map');
									var options = {
										center: new daum.maps.LatLng(33.450701, 126.570667),
										level: 8
									};

									var map = new daum.maps.Map(container, options);
								</script>
                        </div>
                    </aside> 
                    end of sidebar -->

		</div>
	</div>
	<!-- end of /.container --> 
	
	</div>
	
	<!-- main article -->
	

	<jsp:include page="./inc/Footer.jsp" flush="false" />

	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="styles/bootstrap-4.1.2/popper.js"></script>
	<script src="styles/bootstrap-4.1.2/bootstrap.min.js"></script>
	<script src="plugins/greensock/TweenMax.min.js"></script>
	<script src="plugins/greensock/TimelineMax.min.js"></script>
	<script src="plugins/scrollmagic/ScrollMagic.min.js"></script>
	<script src="plugins/greensock/animation.gsap.min.js"></script>
	<script src="plugins/greensock/ScrollToPlugin.min.js"></script>
	<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
	<script src="plugins/easing/easing.js"></script>
	<script src="plugins/progressbar/progressbar.min.js"></script>
	<script src="plugins/colorbox/jquery.colorbox-min.js"></script>
	<script src="plugins/parallax-js-master/parallax.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyC7Mwb0KdtW4wJCYdVG_rh5_qqvYLjK3Fg"></script>
	<script src="js/custom.js"></script>
	

</body>
</html>