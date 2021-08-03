<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<div id="header" class="clearfix">
			<h1>
				<a href="">MySite</a>
			</h1>

			<!-- 
			<ul>
				<li>황일영 님 안녕하세요^^</li>
				<li><a href="" class="btn_s">로그아웃</a></li>
				<li><a href="" class="btn_s">회원정보수정</a></li>
			</ul>
			-->
			<ul>
				<li><a href="" class="btn_s">로그인</a></li>
				<li><a href="" class="btn_s">회원가입</a></li>
			</ul>

		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</td>
									<td><input id="input-pass" type="password" name="pass"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">

					</form>

					<div id="listArea"></div>

					<!-- //guestRead -->


				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<div id="footer">Copyright ⓒ 2020 황일영. All right reserved</div>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">
	$(document).ready(function() {
		//console.log("화면 로딩 직전");

		//ajax 요청

		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			contentType : "application/json",
			//data : {name: "홍길동"},

			dataType : "json",
			success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);

				//화면에 그리기
				for (var i = 0; i < guestList.length; i++) {

					render(guestList[i]); //그리기

				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	//로딩 후 등록 버튼 클릭시

	$("#btnSubmit").on("click", function() {

		event.preventDefault();

		console.log("등록버튼 클릭");

		//name 값 읽어오기

		var userName = $("#input-uname").val();
		console.log(userName);

		//password 값 읽어오기
		var password = $("#input-pass").val();
		console.log(password);

		//content 값 읽어오기
		var content = $("[name='content']").val();
		console.log(content);

		var guestbookVo = {
			name : userName,
			password : password,
			content : content
		};

		$.ajax({
			//url : "${pageContext.request.contextPath }/api/guestbook/write?name" + userName + "&password=" + password + "&content=" + content,
			url : "${pageContext.request.contextPath }/api/guestbook/write",
			type : "get",
			//contentType : "application/json",
			//data : {name: userName, password: password, content: content},
			data : guestbookVo,
			//객체생성

			dataType : "json",
			success : function(guestbookVo) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookVo);

				render(guestbookVo, "up");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	//방명록 1개씩 렌더링
	function render(guestbookVo, type) {

		var str = "";
		str += '<table class="guestRead">';
		str += '   <colgroup>';
		str += '         <col style="width: 10%;">';
		str += '         <col style="width: 40%;">';
		str += '         <col style="width: 40%;">';
		str += '         <col style="width: 10%;">';
		str += '   </colgroup>';
		str += '   <tr>';
		str += '      <td>' + guestbookVo.no + '</td>';
		str += '      <td>' + guestbookVo.name + '</td>';
		str += '      <td>' + guestbookVo.regDate + '</td>';
		str += '      <td><a href="">[삭제]</a></td>';
		str += '   </tr>';
		str += '   <tr>';
		str += '      <td colspan=4 class="text-left">' + guestbookVo.content
				+ '</td>';
		str += '   </tr>';
		str += '</table>';

		if (type === 'down') {

			$("#listArea").append(str);

		} else if ((type === 'up')) {

			$("#listArea").prepend(str);

		} else {
			consle.log("방향을 지정해 주세요");
		}

	}
</script>

</html>