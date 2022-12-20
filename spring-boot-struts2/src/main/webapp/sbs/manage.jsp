<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            body {
                font-family: Arial;
            }
            .header-nav {
                background-color: #f1f1f1;
                border: 1px solid #ccc;
            }
            /* Style the tab */
            .tab {
                overflow: hidden;
                background-color: #f1f1f1;
                height: 50px;
                flex: 1;
            }

            /* Style the buttons inside the tab */
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
            }

            /* Change background color of buttons on hover */
            .tab button:hover {
                background-color: #ddd;
            }

            /* Create an active/current tablink class */
            .tab button.active {
                background-color: rgb(237, 57, 57);
                color: #fff;
            }

            /* Style the tab content */
            .tabcontent {
                display: none;
                padding: 6px 0;
                /* border: 1px solid #ccc; */
                border-top: none;
            }
            .logo {
                width: 200px;
            }
            .line-blue {
                height: 5px;
                background-color: slateblue;
            }

            .pagination-nav {
                justify-self: flex-end;
            }
            .pagination-helper {
                justify-self: flex-end;
            }
            .modal-content,
            .modal-body {
                width: 800px;
            }
            .icon-green {
                            filter: invert(48%) sepia(79%) saturate(2476%) hue-rotate(86deg) brightness(118%) contrast(119%);
                        }
                        .icon-red {
                            filter: invert(11%) sepia(96%) saturate(5728%) hue-rotate(343deg) brightness(93%) contrast(89%);
                        }
        </style>
    </head>
    <body>
        <div class="d-flex justify-content-center header-nav">
            <div class="tab d-flex">
                <img class="logo" src="../resources/logo.png" alt="logo" />

                <button class="tablinks" onclick="openCity(event, 'Product')">Sản phẩm</button>
                <button class="tablinks" onclick="openCity(event, 'Customer')">Khách hàng</button>
                <button class="tablinks" onclick="openCity(event, 'User')">Users</button>
            </div>
            <div class="account mr-auto d-flex align-items-center d-flex px-4">
                <img style="width: 15px" src="../resources/user-solid.svg" class="" alt="user" />

                <h4 class="pb-0 px-2 mb-0">
                    user:
                    <s:property value="#session['USER']" />
                </h4>
            </div>
        </div>
        <div id="Product" class="tabcontent">
            <h3>product</h3>
        </div>

        <div id="Customer" class="tabcontent">
            <h3>customer</h3>
        </div>

        <div id="User" class="tabcontent">
            <h3>Users</h3>
            <p class="line-blue"></p>

            <div class="wrapper-action d-flex justify-content-center">
                <div class="action-form">
                    <div class="search-input d-flex">
                        <div class="form-group mr-4">
                            <label for="name">Tên</label>
                            <input type="text" class="form-control" id="name" placeholder="Nhập họ tên" />
                            <!-- <small id="emailHelp" class="form-text text-muted"
                                >We'll never share your email with anyone else.</small
                            > -->
                        </div>
                        <div class="form-group mr-4">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" id="email" placeholder="Nhập email" />
                            <!-- <small id="emailHelp" class="form-text text-muted"
                                >We'll never share your email with anyone else.</small
                            > -->
                        </div>
                        <div class="form-group mr-4">
                            <label for="group">Tên</label>
                            <input type="text" class="form-control" id="group" placeholder="Chọn nhóm" />
                            <!-- <small id="emailHelp" class="form-text text-muted"
                                >We'll never share your email with anyone else.</small
                            > -->
                        </div>
                        <div class="form-group mr-4">
                            <label for="inputState">Tên</label>
                            <select id="inputState" class="form-control pr-5">
                                <option selected>Chọn trạng thái</option>
                                <option>Đóng</option>
                                <option>Mở</option>
                            </select>
                            <!-- <small id="emailHelp" class="form-text text-muted"
                                >We'll never share your email with anyone else.</small
                            > -->
                        </div>
                    </div>

                    <div class="action d-flex justify-content-between">
                        <div class="left">
                            <button
                                type="button"
                                class="btn btn-primary"
                                data-toggle="modal"
                                data-target="#exampleModal"
                                class="btn-primary btn"
                            >
                                Thêm mới
                            </button>
                        </div>
                        <div class="right">
                            <button class="btn-primary btn">Tìm kiếm</button>
                            <button class="btn-success btn">Xóa tìm</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="pagination d-flex justify-content-center align-items-center">
                <div class="d-flex align-items-center">
                    <nav aria-label="Page navigation example" class="px-3 pagination-nav">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                    <p class="pagination-helper">Hiển thị từ 1 ~ 10 trong tổng số 100 user</p>
                </div>
            </div>

            <div class="list-user">
                <table class="table">
                    <thead class="bg-danger text-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Họ tên</th>
                            <th scope="col">Email</th>
                            <th scope="col">Nhóm</th>
                            <th scope="col">Trạng Thái</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="users" >
                                        <tr >
                                            <td></td>
                                            <td class="nowrap"><s:property value="name"/></td>
                                            <td class="nowrap"><s:property value="email"/></td>
                                            <td class="nowrap"><s:property value="groups"/></td>


                                            <td class="nowrap">
                                                <s:if test="status">
                                                    <span class="text-success">Đang hoạt động</span>
                                                </s:if>
                                                <s:else>
                                                    <span class="text-danger">Tạm khóa</span>
                                                </s:else>
                                            </td>

<td class="nowrap">
                                                <s:url action="edit" var="url">
                                                    <s:param name="user.email" value="email"/>
                                                </s:url>
                                                <a href="<s:property value="#url"/>">
                                                    <img class="icon-green" style="width: 15px" src="../resources/pen-solid.svg" alt="edit" />
                                                </a>
                                                &nbsp;&nbsp;&nbsp;
                                                <s:url action="delete" var="url">
                                                      <s:param name="user.email" value="email"/>
                                                </s:url>
                                                <a href="<s:property value="#url"/>">
                                                    <img class="icon-red" style="width: 15px" src="../resources/trash-can-solid.svg" alt="delete" />
                                                </a>
                                                &nbsp;&nbsp;&nbsp;
                                                                                                <s:url action="lockPerson" var="url">
                                                                                                    <s:param name="user.email" value="email"/>
                                                                                                </s:url>
                                                                                                <a href="<s:property value="#url"/>">
                                                                                                    <img style="width: 15px" src="../resources/user-xmark-solid.svg" alt="lock or unlock" />
                                                                                                </a>
                                            </td>
                                        </tr>
                                    </s:iterator>

                    </tbody>
                </table>
            </div>
            <div class="pagination d-flex justify-content-center align-items-center">
                <div class="d-flex align-items-center">
                    <nav aria-label="Page navigation example" class="px-3 pagination-nav">
                        <ul class="pagination">
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- Modal -->
            <div
                class="modal fade"
                id="exampleModal"
                tabindex="-1"
                aria-labelledby="exampleModalLabel"
                aria-hidden="true"
            >
                <div class="modal-dialog d-flex justify-content-center">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Thêm user/ Chỉnh sửa User</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div class="form-group row">
                                    <label for="modalName" class="col-sm-2 col-form-label">Tên</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="modalName" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalEmail" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="modalEmail" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalPassword" class="col-sm-2 col-form-label">Mật khẩu</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" id="modalPassword" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalRePassword" class="col-sm-2 col-form-label">Xác nhận</label>
                                    <div class="col-sm-10">
                                        <input type="password" class="form-control" id="modalRePassword" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalGroup" class="col-sm-2 col-form-label">Nhóm</label>
                                    <div class="col-sm-10">
                                        <select id="modalGroup" class="form-control pr-5">
                                            <option selected>Chọn nhóm</option>
                                            <option>Admin</option>
                                            <option>abc</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="" class="col-sm-2 col-form-label">Trạng thái</label>
                                    <div class="col-sm-10">TRUE</div>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                            <button type="button" class="btn btn-primary">Lưu</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end modal -->
        </div>

        <script>
            function openCity(evt, cityName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName('tabcontent');
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = 'none';
                }
                tablinks = document.getElementsByClassName('tablinks');
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(' active', '');
                }
                document.getElementById(cityName).style.display = 'block';
                evt.currentTarget.className += ' active';
            }

            // window.onload(openCity())
        </script>
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










<!--
<td class="nowrap">
                                                <s:url action="inputPerson" var="url">
                                                    <s:param name="person.personId" value="personId"/>
                                                </s:url>
                                                <a href="<s:property value="#url"/>">
                                                    <img style="width: 15px" src="./pen-solid.svg" alt="edit" />
                                                </a>
                                                &nbsp;&nbsp;&nbsp;
                                                <s:url action="deletePerson" var="url">
                                                    <s:param name="person.personId" value="personId"/>
                                                </s:url>
                                                <a href="<s:property value="#url"/>">
                                                    <img style="width: 15px" src="./trash-can-solid.svg" alt="delete" />
                                                </a>
                                                &nbsp;&nbsp;&nbsp;
                                                                                                <s:url action="lockPerson" var="url">
                                                                                                    <s:param name="person.personId" value="personId"/>
                                                                                                </s:url>
                                                                                                <a href="<s:property value="#url"/>">
                                                                                                    <img style="width: 15px" src="./user-xmark-solid.svg" alt="lock or unlock" />
                                                                                                </a>
                                            </td>
-->








