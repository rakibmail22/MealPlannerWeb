<!-- All the files that are required -->
<link rel="stylesheet" href="//localhost:8080/jsp/style/font-awesome.min.css">
<link rel='//localhost:8080/jsp/style/font-awesome.min.css' rel='stylesheet' type='text/css'>
<script src="http://localhost:8080/jsp/script/jquery.validate.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

<!-- Where all the magic happens -->
<!-- LOGIN FORM -->
<div class="text-center" style="padding:50px 0">
    <div class="logo">login</div>
    <!-- Main Form -->
    <div class="login-form-1">
        <form id="login-form" class="text-left" action="/login" method="post">
            <div class="login-form-main-message"></div>
            <div class="main-login-form">
                <div class="login-group">
                    <div class="form-group">
                        <label for="lg_username" class="sr-only">Username</label>
                        <input type="text" class="form-control" id="lg_username" name="lg_username" placeholder="username">
                    </div>
                    <div class="form-group">
                        <label for="lg_password" class="sr-only">Password</label>
                        <input type="password" class="form-control" id="lg_password" name="lg_password" placeholder="password">
                    </div>
            <!--        <div class="form-group login-group-checkbox">
                        <input type="checkbox" id="lg_remember" name="lg_remember">
                        <label for="lg_remember">remember</label>
                    </div>  -->
                </div>
                <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
            </div>
           <!-- <div class="etc-login-form">
                <p>forgot your password? <a href="#">click here</a></p>
                <p>new user? <a href="#">create new account</a></p>
            </div> -->
        </form>
    </div>
    <!-- end:Main Form -->
</div>
