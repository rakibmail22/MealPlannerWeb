<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Login</title>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
    <!-- All the files that are required -->
    <c:set var="basePath" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${basePath}/statics/style/font-awesome.min.css">
    <link rel="stylesheet" href="${basePath}/statics/style/meal-table.css">
    <script src="${basePath}/statics/script/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
</head>
<body>
<!-- Where all the magic happens -->
<!-- LOGIN FORM -->
<div class="text-center" style="padding:50px 0">
    <div class="logo">Login</div>
    <!-- Main Form -->
    <div class="login-form-1">
        <form:form id="login-form" class="text-left" action="login" method="post" commandName="loginFormInfo">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                    <div class="form-group">
                        <label for="lg_username" class="sr-only">Username</label>
                        <form:input type="text" class="form-control" id="lg_username" name="username"
                               placeholder="email" path="username"/>
                        <form:errors path="username" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="lg_password" class="sr-only">Password</label>
                        <form:input type="password" class="form-control" id="lg_password" name="password"
                               placeholder="password" path="password"/>
                        <form:errors path="password" cssClass="error"/>
                    </div>
                    <!--        <div class="form-group login-group-checkbox">
                                <input type="checkbox" id="lg_remember" name="lg_remember">
                                <label for="lg_remember">remember</label>
                            </div>  -->
                </div>
                <button type="submit" class="login-button">Log In</button>
            </div>
            <!-- <div class="etc-login-form">
                 <p>forgot your password? <a href="#">click here</a></p>
                 <p>new user? <a href="#">create new account</a></p>
             </div> -->
        </form:form>
    </div>
</div>

<div class="text-center" style="padding:50px 0">
    <div class="logo">Sign Up</div>
    <!-- Main Form -->
    <div class="login-form-1">
        <form:form id="signup-form" class="text-left" action="signup" method="post" commandName="signUpFormInfo">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                    <div class="form-group">
                        <label for="sg_name" class="sr-only">Name</label>
                        <form:input type="text" class="form-control" id="sg_name" name="sg_name"
                               placeholder="name" path="name"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>

                    <div class="form-group">
                        <label for="sg_email" class="sr-only">Email</label>
                        <form:input type="text" class="form-control" id="sg_email" name="sg_email"
                               placeholder="email" path="email"/>
                        <form:errors path="email" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="sg_password" class="sr-only">Password</label>
                        <form:input type="password" class="form-control" id="sg_password" name="sg_password"
                               placeholder="password" path="password"/>
                        <form:errors path="password" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="sg_password2" class="sr-only">Re-type</label>

                        <form:input type="password" class="form-control" id="sg_password2" name="sg_password2"
                               placeholder="password" path="verifyPassword"/>
                        <form:errors path="verifyPassword" cssClass="error"/>
                    </div>

                    <!--        <div class="form-group login-group-checkbox">
                                <input type="checkbox" id="lg_remember" name="lg_remember">
                                <label for="lg_remember">remember</label>
                            </div>  -->
                </div>
                <button type="submit" class="login-button">Sign Up</button>
            </div>
            <!-- <div class="etc-login-form">
                 <p>forgot your password? <a href="#">click here</a></p>
                 <p>new user? <a href="#">create new account</a></p>
             </div> -->
        </form:form >
    </div>
</div>
</body>
</html>