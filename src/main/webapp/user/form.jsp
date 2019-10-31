<%@ page import="framework.models.User" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 15/05/2019
  Time: 15:51
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
<% User user = (User) request.getAttribute("user");%>

<section>
    <div class="row">
        <div class="col-12">
            <div class="row">
                <div class="col-12">
                    <h1>new User</h1>
                </div>
            </div>
            <div class="row  justify-content-center">
                <div class="col-10">
                    <form action="<%=application.getContextPath()%>/user/saveuser" method="post">
                        <input hidden="hidden" name="id" value="<%=user.getId()%>" type="text">
                        <div class="form-group">
                            <label for="nom">Nom</label>
                            <input name="nom" type="text" class="form-control" id="nom"
                                   aria-describedby="titreHelp"
                                   value="<%=user.getNom()%>"
                                   placeholder="Nom">
                        </div>
                        <div class="form-group">
                            <label for="Prenom">Prenom</label>
                            <input name="prenom" type="text" class="form-control" id="Prenom"
                                   aria-describedby="titreHelp"
                                   value="<%=user.getPrenom()%>"
                                   placeholder="Prenom">
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
