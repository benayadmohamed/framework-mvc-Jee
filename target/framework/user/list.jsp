<%@ page import="framework.models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15/05/2019
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<jsp:include page="../header.jsp"/>
<% List<User> users = (List<User>) request.getAttribute("users");%>
<section>
    <div class="row">
        <div class="col-12">
            <div class="">
                <div class="col-12">
                    <h1>List des users</h1>
                </div>
            </div>
            <div class="row  justify-content-center">
                <div class="col-10">
                    <form class="input-group mb-3" action="<%=application.getContextPath()%>/user/search" method="GET">
                        <input class="form-control" value="${motCle}" type="text" name="motCle"/>
                        <button class="btn btn-primary">Rchercher</button>
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Nom</th>
                            <th scope="col">Prenom</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (User user : users) {%>
                        <tr>
                            <th><%=user.getId()%>
                            </th>
                            <th><%=user.getNom()%>
                            </th>
                            <th><%=user.getPrenom()%>
                            </th>


                            <th>
                                <a onclick="return confirm('Etes vous sur de de supprimer cette ressource?')"
                                   href="<%=application.getContextPath()%>/user/delete?id=<%=user.getId()%>">
                                    Supprimer</a>
                                <a href="<%=application.getContextPath()%>/user/edit?id=<%=user.getId()%>">
                                    Modifier</a>
                            </th>
                        </tr>
                        <% }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
