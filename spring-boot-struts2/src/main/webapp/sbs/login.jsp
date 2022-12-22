<%@ taglib prefix="s" uri="/struts-tags" %> <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous"
        />
        <title>Document</title>
        <style>
            .wrapper {
                width: 800px;
                height: 400px;
            }
            .form-login {
                width: 700px;
            }

            #emailHelp,
            #passwordHelp {
                color: red !important;
            }

            ul > li > span {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container d-flex justify-content-center flex-column align-items-center">
            <div class="wrapper d-flex justify-content-center flex-column align-items-center">
                <div class="logo">
                    <img src="../resources/logo.png" alt="logo" />
                </div>
                <form method="POST" class="form-login pt-3" action="login.do">
                    <div class="form-group">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-light">
                                    <img src="../resources/usericon.png" alt="" />
                                </div>
                            </div>
                            <s:textfield
                                type="email"
                                class="form-control"
                                id="exampleInputEmail1"
                                aria-describedby="emailHelp"
                                placeholder="Email"
                                name="user.email"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <div class="input-group-text bg-light">
                                    <img src="../resources/password.png" alt="" />
                                </div>
                            </div>
                            <s:textfield
                                type="password"
                                class="form-control"
                                id="exampleInputPassword1"
                                placeholder="Password"
                                name="user.password"
                            />
                        </div>
                    </div>
                    <small class="text-danger form-text text-muted">
                        <s:actionerror />
                        <s:fielderror />
                    </small>
                    <div class="form-check">
                        <s:checkbox name="remember" fieldValue="true" label="Remember" />
                    </div>
                    <button type="submit" class="btn btn-primary">Đăng nhập</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </div>
        </div>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.2/jquery.min.js"
            integrity="sha512-tWHlutFnuG0C6nQRlpvrEhE4QpkG1nn2MOUMWmUeRePl4e3Aki0VB6W1v3oLjFtd0hVOtRQ9PHpSfN6u6/QXkQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>
        <script
            src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"
        ></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"
        ></script>
    </body>
</html>
