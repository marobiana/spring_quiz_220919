<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기 추가</title>
	<%-- AJAX를 사용할 때는 jquery가 원본이어야 한다. --%>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script> 
	
	<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

	<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
	
	<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>즐겨찾기 추가하기</h1>
		
		<div class="form-group">
			<label for="title">제목</label>
			<input type="text" id="title" class="form-control">
		</div>

		<div class="form-group">
			<label for="url">URL 주소</label>
			<div class="form-inline">
				<input type="text" id="url" class="form-control col-10">
				<button type="button" id="checkBtn" class="btn btn-info">중복확인</button>
			</div>
			<small id="duplicationText" class="text-danger d-none">중복된 URL 입니다.</small>
			<small id="avaliableText" class="text-success d-none">저장 가능한 URL 입니다.</small>
		</div>
		
		<button type="button" id="addFavoriteBtn" class="btn btn-success btn-block">추가</button>
	</div>
	
	<script>
		$(document).ready(function() {
			$('#addFavoriteBtn').on('click', function() {
				let title = $('#title').val().trim();
				let url = $('#url').val().trim();
				
				if (title == '') {
					alert("제목을 입력하세요");
					return;
				}
				
				if (url == '') {
					alert("주소를 입력하세요");
					return;
				}
				
				// http로 시작하지도 않고, https로도 시작하지 않으면 alert
				if (url.startsWith('http') == false && url.startsWith("https") == false) {
					alert("주소 형식이 잘못되었습니다.");
					return;
				}
				
				// AJAX 통신
				$.ajax({
					// request
					type:"post"
					, url:"/lesson06/quiz01/add_favorite"
					, data: {"name":title, "url":url}
					
					// response
					, success:function(data) {  // string json => object
						//alert(data);
						if (data.result == "성공") {
							location.href = "/lesson06/quiz01/after_add_favorite_view";
						}
					}
					, error:function(e) {
						alert("에러" + e);
					}
				});
			});
			
			// 중복확인
			$("#checkBtn").on('click', function() {
				let url = $('#url').val().trim();
				
				if (url == '') {
					alert("주소를 입력하세요");
					return;
				}
				
				// http로 시작하지도 않고, https로도 시작하지 않으면 alert
				if (url.startsWith('http') == false && url.startsWith("https") == false) {
					alert("주소 형식이 잘못되었습니다.");
					return;
				}
				
				// ajax -> 디비 확인
				$.ajax({
					// request
					type:"post"
					, url:"/lesson06/quiz02/is_duplication_url"
					, data:{"url":url}
					
					// response
					, success:function(data) {
						if (data.is_duplication) {
							// 중복 
							$('#avaliableText').addClass('d-none');
							$('#duplicationText').removeClass("d-none");
						} else {
							// 사용 가능한 URL
							$('#duplicationText').addClass("d-none");
							$('#avaliableText').removeClass('d-none');
						}
					}
					, error:function(e) {
						alert("에러 " + e);
					}
				});
			});
		});
	</script>
</body>
</html>



