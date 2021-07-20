<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Hero, beans.Slime" %>
<%
Hero hero = new Hero();
Slime slimeA = new Slime('A');
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>かんたんなRPG</title>
<link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/battle.css" type="text/css">
</head>
<body>
<!-- HEADERは別ファイルから読み込む -->
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div id="main">


<form action="Battle3GoServlet" method="post">
<div id="wrapper">

<div class="item">
<img src="/RPG/img/sample.png">
<input type="radio" name="target" value="1" id="target1"><label for="target1">${dragon.name} / ${dragon.hp}</label>
</div>

</div>

<div id="flexWrapper">
<div class="command">



<input type="radio" name="action" value="1" id="action1"><label for="action1">たたかう</label>


<input type="radio" name="action" value="2" id="action2"><label for="action2">ファイヤー</label>


<input type="radio" name="action" value="3" id="action3"><label for="action3">自暴自棄</label>


	<input type="submit" value="OK">
</form>
</div>

<p class="message" id="test">戦闘時のコメントが表示される場所</p>

</div>

<p class="chara">${hero.name} / HP:${hero.hp} / MP:${hero.mp}　★　${fighter.name} / HP:${fighter.hp} / MP:${fighter.mp}　★　${wizard.name} / HP:${wizard.hp} / MP:${wizard.mp}</p>
</div>

<!-- FOOTERは別ファイルから読み込む -->
<jsp:include page="/WEB-INF/jsp/footer.jsp" />

<script src="/RPG/javascript/battle.js"></script>
</body>
</html>