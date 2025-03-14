<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--  <meta name="description" content="<%= request.getAttribute("story") %>">--%>
<%--  <meta property="og:title" content="<%= request.getAttribute("title") %>">--%>
<%--  <meta property="og:image" content="<%= request.getAttribute("poster") %>">--%>
<%--  <meta property="og:description" content="<%= request.getAttribute("story") %>">--%>
  <title><%= request.getAttribute("title") %> - AI 영화 생성기</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>

<h1>🎬 영화 포스터 & 줄거리</h1>

<div class="result">
<%--  <h2><%= request.getAttribute("title") %></h2>--%>
<%--  <p><%= request.getAttribute("story") %></p>--%>
<%--  <img src="<%= request.getAttribute("poster") %>" alt="영화 포스터">--%>
  <div class="base"></div>
    <% if (session.getAttribute("storyline") != null && session.getAttribute("title") != null) { %>
    <h2><%= session.getAttribute("title")%></h2>
    <p><%= session.getAttribute("storyline")%> </p>
    <%--  <p><%= session.getAttribute("reasoning")%> </p>--%>
    <% } %>
  <br>
  <div class="thinking">
    <% if (session.getAttribute("thinking") != null) { %>
    <p><%= session.getAttribute("thinking")%> </p>
    <% } %>
  </div>
  <br>
<%--  <div class="reasoning">--%>
<%--    <% if (session.getAttribute("reasoning") != null) { %>--%>
<%--    <p><%= session.getAttribute("reasoning")%> </p>--%>
<%--    <% } %>--%>
<%--  </div>--%>
<%--  <br>--%>
  <div class="image">
    <% if (session.getAttribute("image") != null) { %>
    <img src="<%= session.getAttribute("image")%>" alt="Example Image" width="500" height="300" loading="lazy" />
    <% } %>
  </div>
</div>

<div class="reset">
  <a href="index.jsp">🔙 다시 생성하기</a>
</div>

</body>
</html>
