<html>
<head>
    <title>Login</title>
    <!-- All the files that are required -->
    <link rel="stylesheet" href="//localhost:8080/jsp/style/font-awesome.min.css">
    <link rel='//localhost:8080/jsp/style/font-awesome.min.css' rel='stylesheet' type='text/css'>
    <script src="http://localhost:8080/jsp/script/jquery.validate.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
</head>
<body>
<!-- Where all the magic happens -->
<!-- LOGIN FORM -->
<div class="text-center" style="padding:50px 0">
    <div class="logo">Login</div>
    <!-- Main Form -->
    <div class="login-form-1">
        <form id="login-form" class="text-left" action="/login" method="post">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                    <div class="form-group">
                        <label for="lg_username" class="sr-only">Username</label>
                        <input type="text" class="form-control" id="lg_username" name="lg_username"
                               placeholder="username">
                    </div>
                    <div class="form-group">
                        <label for="lg_password" class="sr-only">Password</label>
                        <input type="password" class="form-control" id="lg_password" name="lg_password"
                               placeholder="password">
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
        </form>
    </div>
</div>

<div class="text-center" style="padding:50px 0">
    <div class="logo">Sign Up</div>
    <!-- Main Form -->
    <div class="login-form-1">
        <form id="signup-form" class="text-left" action="/signup" method="post">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                    <div class="form-group">
                        <label for="sg_name" class="sr-only">Name</label>
                        <input type="text" class="form-control" id="sg_name" name="sg_name"
                               placeholder="name">
                    </div>

                    <div class="form-group">
                        <label for="sg_email" class="sr-only">Email</label>
                        <input type="text" class="form-control" id="sg_email" name="sg_email"
                               placeholder="email">
                    </div>
                    <div class="form-group">
                        <label for="sg_password" class="sr-only">Password</label>
                        <input type="password" class="form-control" id="sg_password" name="sg_password"
                               placeholder="password">
                    </div>
                    <div class="form-group">
                        <label for="sg_password2" class="sr-only">Re-type</label>
                        <input type="password" class="form-control" id="sg_password2" name="sg_password2"
                               placeholder="password">
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
        </form>
    </div>
</div>
</body>
</html>



<%--
<div class="signup-form-1">
    <form id="signup-form" class="text-left" action="/hello" method="post">
        <div class="signup-main-form">
            <div class="signup-group">
                <div class="signup-form-group">
                    <label for="lg_email" class="sr-only">Email</label>
                    <input type="text" class="form-control" id="lg_email" name="lg_email"
                           placeholder="email">
                </div>
                <div class="signup-form-group">
                    <label for="lg_name" class="sr-only">Name</label>
                    <input type="text" class="form-control" id="lg_name" name="lg_name"
                           placeholder="name">
                </div>
                <div class="signup-form-group">
                    <label for="lg_signup_pass" class="sr-only">Password</label>
                    <input type="password" class="form-control" id="lg_signup_pass" name="lg_signup_pass"
                           placeholder="password">
                </div>
                <div class="signup-form-group">
                    <label for="lg_signup_pass2" class="sr-only">Password</label>
                    <input type="password" class="form-control" id="lg_signup_pass2" name="lg_signup_pass2"
                           placeholder="password2">
                </div>
            </div>
            <button type="submit" class="signup-button">Sign Up</button>
        </div>
    </form>

</div>
--%>
