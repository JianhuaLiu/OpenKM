<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.openkm.core.Config" %>
<%@ page import="com.openkm.dao.LanguageDAO" %>
<%@ page import="com.openkm.dao.bean.Language" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.openkm.com/tags/utils" prefix="u" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link rel="Shortcut icon" href="<%=request.getContextPath() %>/logo/favicon" />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/desktop.css" type="text/css" />
  <%
    Locale locale = request.getLocale();
    Cookie[] cookies = request.getCookies();
    String preset = null;
    
    if (cookies != null) {
      for (int i=0; i<cookies.length; i++) {
        if (cookies[i].getName().equals("lang")) {
          preset = cookies[i].getValue();
        }
      }
    }
    
    if (preset == null || preset.equals("")) {
      preset = locale.getLanguage() + "-" + locale.getCountry();
    }
  %>
  <title>OpenKM Login Test</title>
</head>
<body onload="document.forms[0].elements[0].focus()">
  <u:constantsMap className="com.openkm.core.Config" var="Config"/>
  <center style="margin-top: 40px;"><%=Config.TEXT_BANNER %></center>
  <div id="box">
    <div id="logo"></div>
    <div id="error">
      <c:if test="${not empty param.error}">
         Authentication error
        <c:if test="${Config.USER_PASSWORD_RESET && Config.PRINCIPAL_ADAPTER == 'com.openkm.principal.DatabasePrincipalAdapter'}">
          (<a href="password_reset.jsp">Forgot your password?</a>)
        </c:if>
      </c:if>
    </div>
    <div id="text">
      <center><img src="<%=request.getContextPath() %>/img/lock.png"/></center>
      <%=Config.TEXT_WELCOME %>
    </div>
    <div id="form">
      <form name="loginform" method="post" action="j_spring_security_check" onsubmit="setCookie()">
      
      <label for="j_username">User</label><br/>
          <input name="j_username" id="j_username" type="text" value="okmAdmin"/><br/><br/>
        <label for="j_password">Password</label><br/>
        <input name="j_password" id="j_password" type="password" value="admin"/><br/><br/>
        <label for="j_language">Language</label><br/>
        <select name="j_language" id="j_language">
        <option>en-GB</option>
        </select>
        <input value="Login" name="submit" type="submit" class="loginButton"/><br/>
      </form>
    </div>
  </div>
  
  <script type="text/javascript">
    function makeLowercase() {
      var username = document.getElementById('j_username'); 
      username.value = username.value.toLowerCase();
    }

    function setCookie() {
      var exdate = new Date();
      var value = document.getElementById('j_language').value;
      exdate.setDate(exdate.getDate() + 7);
      document.cookie = "lang=" + escape(value) + ";expires=" + exdate.toUTCString();
    }
  </script>
</body>
</html>