<%@ taglib prefix="s" uri="/struts-tags" %> <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
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

            .is-active-page {
                color: red !important;
            }
        </style>
    </head>

    <body onload="getUsers();">
        <div class="d-flex justify-content-center header-nav">
            <div class="tab d-flex">
                <img class="logo" src="../resources/logo.png" alt="logo" />

                <button class="tablinks" onclick="openTab(event, 'Product')">Sản phẩm</button>
                <button class="tablinks" onclick="openTab(event, 'Customer')">Khách hàng</button>
                <button class="tablinks" onload="openTab(this, 'User')" onclick="openTab(event, 'User')">Users</button>
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
                <form method="GET" id="searchForm" enctype="multipart/form-data">
                    <div class="action-form">
                        <div class="search-input d-flex">
                            <div class="form-group mr-4">
                                <label for="name">Tên</label>
                                <input
                                    type="text"
                                    name="name"
                                    class="form-control"
                                    id="name"
                                    placeholder="Nhập họ tên"
                                />
                                <!-- <small id="emailHelp" class="form-text text-muted"
                                >We'll never share your email with anyone else.</small
                            > -->
                            </div>
                            <div class="form-group mr-4">
                                <label for="email">Email</label>
                                <input
                                    type="text"
                                    name="email"
                                    class="form-control"
                                    id="email"
                                    placeholder="Nhập email"
                                />
                                <!-- <small id="emailHelp" class="form-text text-muted"
                                >We'll never share your email with anyone else.</small
                            > -->
                            </div>
                            <div class="form-group mr-4">
                                <label for="group">Nhóm</label>
                                <input
                                    type="text"
                                    name="groups"
                                    class="form-control"
                                    id="group"
                                    placeholder="Chọn nhóm"
                                />
                                <!-- <small id="emailHelp" class="form-text text-muted"
                                >We'll never share your email with anyone else.</small
                            > -->
                            </div>
                            <div class="form-group mr-4">
                                <label for="inputState">Trạng thái</label>
                                <select name="active" id="inputState" class="form-control pr-5">
                                    <option selected>Chọn trạng thái</option>
                                    <option value="0">Đóng</option>
                                    <option value="1">Mở</option>
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
                                <button type="submit" id="btnSearch" class="btn-primary btn">Tìm kiếm</button>
                                <button type="button" onclick="resetForm()" class="btn-success btn">Xóa tìm</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="pagination d-flex justify-content-center align-items-center">
                <div class="d-flex align-items-center">
                    <nav aria-label="Page navigation example" class="px-3 pagination-nav">
                        <ul class="pagination list-pagination">
                            <!-- <li class="page-item">
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
                            </li> -->
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
                    <tbody id="tbody">
                        <!-- <s:iterator value="users">
                                <tr>
                                    <td></td>
                                    <td class="nowrap">
                                        <s:property value="name" />
                                    </td>
                                    <td class="nowrap">
                                        <s:property value="email" />
                                    </td>
                                    <td class="nowrap">
                                        <s:property value="groups" />
                                    </td>


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
                                            <s:param name="user.email" value="email" />
                                        </s:url>
                                        <a href="<s:property value=" #url" />">
                                        <img class="icon-green" style="width: 15px" src="../resources/pen-solid.svg"
                                            alt="edit" />
                                        </a>
                                        &nbsp;&nbsp;
                                        <s:url action="delete" var="url">
                                            <s:param name="user.email" value="email" />
                                        </s:url>
                                        <a href="<s:property value=" #url" />">
                                        <img class="icon-red" style="width: 15px" src="../resources/trash-can-solid.svg"
                                            alt="delete" />
                                        </a>
                                        &nbsp;&nbsp;
                                        <s:url action="lockPerson" var="url">
                                            <s:param name="user.email" value="email" />
                                        </s:url>
                                        <a href="<s:property value=" #url" />">
                                        <img style="width: 15px" src="../resources/user-xmark-solid.svg"
                                            alt="lock or unlock" />
                                        </a>
                                    </td>
                                </tr>
                            </s:iterator> -->
                    </tbody>
                </table>
            </div>
            <div class="pagination d-flex justify-content-center align-items-center">
                <div class="d-flex align-items-center">
                    <nav aria-label="Page navigation example" class="px-3 pagination-nav">
                        <ul class="pagination list-pagination">
                            <!-- <li class="page-item">
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
                            </li> -->
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
                    <form id="insertForm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Thêm user/ Chỉnh sửa User</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group row">
                                    <label for="modalName" class="col-sm-2 col-form-label">Tên</label>
                                    <div class="col-sm-10">
                                        <input name="name" type="text" class="form-control" id="modalName" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalEmail" class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <input name="email" type="email" class="form-control" id="modalEmail" />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalPassword" class="col-sm-2 col-form-label">Mật khẩu</label>
                                    <div class="col-sm-10">
                                        <input
                                            name="password"
                                            type="password"
                                            class="form-control"
                                            id="modalPassword"
                                        />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalRePassword" class="col-sm-2 col-form-label">Xác nhận</label>
                                    <div class="col-sm-10">
                                        <input
                                            name="repassword"
                                            type="password"
                                            class="form-control"
                                            id="modalRePassword"
                                        />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="modalGroup" class="col-sm-2 col-form-label">Nhóm</label>
                                    <div class="col-sm-10">
                                        <select name="groups" id="modalGroup" class="form-control pr-5">
                                            <option value="">Chọn nhóm</option>
                                            <option value="ADMIN">Admin</option>
                                            <option value="CUSTOMER" selected>Customer</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="" class="col-sm-2 col-form-label">Trạng thái</label>
                                    <input type="checkbox" checked name="active" />
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                <button type="button" id="btnSave" class="btn btn-primary">Lưu</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- end modal -->
        </div>

        <script>
            var pageNumberIndex = 0;
            function openTab(evt, tabName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName('tabcontent');
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = 'none';
                }
                tablinks = document.getElementsByClassName('tablinks');
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(' active', '');
                }
                document.getElementById(tabName).style.display = 'block';
                evt.currentTarget.className += ' active';
            }
            function activeNumber(pageIndex = 0) {
                pageNumberIndex = pageIndex;
                var pages = document.getElementsByClassName('num-page');
                // for (var i = 0; i < pages.length; i++) {
                //     pages[i].className = pages[i].className.replace(' is-active-page', '');
                // }
                console.log(pages);
                pages[pageNumberIndex].className += ' is-active-page';
            }
            // window.onload(openTab())
        </script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.2/jquery.min.js"
            integrity="sha512-tWHlutFnuG0C6nQRlpvrEhE4QpkG1nn2MOUMWmUeRePl4e3Aki0VB6W1v3oLjFtd0hVOtRQ9PHpSfN6u6/QXkQ=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
        ></script>
        <!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
                crossorigin="anonymous"></script> -->
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
        <script type="text/javascript">
            var mapData = {};

            $('.page-link').on('click', function (e) {
                e.preventDefault(); // Now link won't go anywhere

                console.log('element was clicked');
            });

            // load data search users
            function getDataSearchUsers(data, size) {
                var users = data.users;
                var userData = '';
                var pagination = '';
                var x = 0;
                for (var i = 0; i < users.length; i++) {
                    console.log(users[i].isActive);
                    userData += `
                                        <tr>
                                            <td>\${i + 1}</td>
                                            <td class="nowrap">\${users[i].name ? users[i].name : ''}</td>
                                            <td class="nowrap">\${users[i].email ? users[i].email : '' }</td>
                                            <td class="nowrap">\${users[i].groups ? users[i].groups : ''}</td>
                                            <td class="nowrap">\${
                                                users[i].isActive == true
                                                    ? '<span class="text-success">Đang hoạt động</span>'
                                                    : '<span class="text-danger">Tạm khóa</span>'
                                            }</td>

                                            <td class="nowrap">

                                              <a href="edit.do?user.email=/\${users[i].email}">
                                              <img class="icon-green" style="width: 15px" src="../resources/pen-solid.svg"
                                                  alt="edit" />
                                              </a>
                                              &nbsp;&nbsp;

                                              <a href="delete.do?user.email=/\${users[i].email}">
                                              <img class="icon-red" style="width: 15px" src="../resources/trash-can-solid.svg"
                                                  alt="delete" />
                                              </a>
                                              &nbsp;&nbsp;

                                              <a href="actionLock.do?user.email=/\${users[i].email}">
                                              <img style="width: 15px" src="../resources/user-xmark-solid.svg"
                                                  alt="lock or unlock" />
                                              </a>
                                          </td>
                                        </tr>
            `;
                }
                pagination += `
                                    <li class="page-item" >
                                        <a class="page-link" onclick="getSearchUsers(\${data.page - 1 <= 0 ? 0 : data.page - 1},\${size})" href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    `;
                for (var j = 0; j < data.totalPages; j++) {
                    pagination += `
                                    <li class="page-item"><a class="page-link num-page" onclick="getSearchUsers(\${j},\${size}); activeNumber( \${j})"   href="#">\${j+1}</a></li>

                                    `;
                }
                pagination += `
                                    <li class="page-item">
                                        <a class="page-link" onclick="getSearchUsers(\${data.page +1 == data.totalPages ? data.page : data.page +1},\${size})"  href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                    <li class="page-item" >
                                        <a class="page-link" onclick="getSearchUsers(\${data.totalPages -1},\${size})"  href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;&raquo;</span>
                                        </a>
                                    </li>
                                `;
                $('#tbody').html(userData);
                $('.list-pagination').html(pagination);
            }

            //load data users
            function getDataUsers(data, size) {
                var users = data.users;
                var userData = '';
                var pagination = '';
                var x = 0;
                for (var i = 0; i < users.length; i++) {
                    console.log(users[i].isActive);
                    userData += `
                                        <tr>
                                            <td>\${i + 1}</td>
                                            <td class="nowrap">\${users[i].name ? users[i].name : ''}</td>
                                            <td class="nowrap">\${users[i].email ? users[i].email : '' }</td>
                                            <td class="nowrap">\${users[i].groups ? users[i].groups : ''}</td>
                                            <td class="nowrap">\${
                                                users[i].isActive == true
                                                    ? '<span class="text-success">Đang hoạt động</span>'
                                                    : '<span class="text-danger">Tạm khóa</span>'
                                            }</td>

                                            <td class="nowrap">

                                              <a href="edit.do?user.email=/\${users[i].email}">
                                              <img class="icon-green" style="width: 15px" src="../resources/pen-solid.svg"
                                                  alt="edit" />
                                              </a>
                                              &nbsp;&nbsp;

                                              <a href="delete.do?user.email=/\${users[i].email}">
                                              <img class="icon-red" style="width: 15px" src="../resources/trash-can-solid.svg"
                                                  alt="delete" />
                                              </a>
                                              &nbsp;&nbsp;

                                              <a href="actionLock.do?user.email=/\${users[i].email}">
                                              <img style="width: 15px" src="../resources/user-xmark-solid.svg"
                                                  alt="lock or unlock" />
                                              </a>
                                          </td>
                                        </tr>
            `;
                }
                pagination += `
                                    <li class="page-item">
                                        <a class="page-link" onclick="getUsers(\${data.page - 1 <= 0 ? 0 : data.page - 1},\${size})" href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    `;
                for (var j = 0; j < data.totalPages; j++) {
                    pagination += `
                                    <li class="page-item"><a class="page-link num-page" onclick="getUsers(\${j},\${size}); activeNumber( \${j})"   href="#">\${j+1}</a></li>

                                    `;
                }
                pagination += `
                                    <li class="page-item">
                                        <a class="page-link" onclick="getUsers(\${data.page +1 == data.totalPages ? data.page : data.page +1},\${size})"  href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                    <li class="page-item">
                                        <a class="page-link" onclick="getUsers(\${data.totalPages - 1},\${size})"  href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;&raquo;</span>
                                        </a>
                                    </li>
                                `;
                $('#tbody').html(userData);
                $('.list-pagination').html(pagination);
            }
            // search results
            $('#btnSearch').on('click', function (e) {
                e.preventDefault();
                mapData = {};
                var form = document.getElementById('searchForm');
                var formData = new FormData(form);

                for (var data of formData) {
                    mapData[data[0]] = data[1];
                }
                getSearchUsers();
            });
            // reset form search
            function resetForm() {
                $('#searchForm').trigger('reset');
                getUsers();
            }
            // api search users with page number
            function getSearchUsers(page = 0, size = 5) {
                console.log(mapData);
                var active = mapData.active == '0' ? false : true;
                var query =
                    'name=' +
                    mapData.name +
                    '&email=' +
                    mapData.email +
                    '&groups=' +
                    mapData.groups +
                    '&active=' +
                    active;
                $.ajax({
                    type: 'GET',
                    url: 'searchuser.do',
                    data: query + '&page=' + page + '&size=' + size,
                    dataType: 'json',
                    success: function (data) {
                        console.log(data.users);
                        getDataSearchUsers(data);
                    },
                }); //
            }
            //api get data with page number
            function getUsers(page = 0, size = 5) {
                $.ajax({
                    type: 'GET',
                    url: 'listuser.do',
                    data: 'page=' + page + '&size=' + size,
                    success: function (data) {
                        getDataUsers(data);
                    },
                });
            }

            // insert users
            $('#btnSave').on('click', function (e) {
                e.preventDefault();
                var form = document.getElementById('insertForm');
                var formData = new FormData(form);
                var mapData = {};
                for (var data of formData) {
                    mapData[data[0]] = data[1];
                }
                mapData.active = mapData.active == 'on' ? true : false;
                var query =
                    'name=' +
                    mapData.name +
                    '&email=' +
                    mapData.email +
                    '&groups=' +
                    mapData.groups +
                    '&password=' +
                    mapData.password +
                    '&active=' +
                    mapData.active;
                $.ajax({
                    type: 'POST',
                    url: 'insertuser.do',
                    data: query,
                    success: function (data) {
                        getUsers(0, 5);
                    },
                });
                console.log(mapData);
            });
        </script>
    </body>
</html>
