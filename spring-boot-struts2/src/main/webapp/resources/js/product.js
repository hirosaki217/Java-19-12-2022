var mapProductData = {};

//api get list data with page number
function getProducts(page = 0, size = 10) {
    currentPageProduct = page;
    $.ajax({
        type: 'GET',
        url: 'listproduct.do',
        dataType: 'json',
        data: 'page=' + page + '&size=' + size,
        success: function (data) {
            getAllProducts(data);
        },
    });
}
function getAllProducts(data, size) {
    var products = data.products;
    var userData = '';
    var pagination = '';
    $('#inforProductList').html(
        `Hiển thị từ ${data.page * 10 + 1} ~ ${products.length + data.page * 10} trong tổng số ${
            data.totalRecord
        } product`,
    );
    for (var i = 0; i < products.length; i++) {
        userData += `
                        <tr>
                            <td>${data.page * 10 + i + 1}</td>
                            <td class="nowrap">${products[i].productId ? products[i].productId : ''}</td>
                            <td class="nowrap">${products[i].productName ? products[i].productName : ''}</td>
                            <td class="nowrap">${products[i].description ? products[i].description : ''}</td>
                            <td class="nowrap text-success">$${
                                products[i].productPrice ? products[i].productPrice : ''
                            }</td>
                            <td class="nowrap">${
                                products[i].sales == true
                                    ? '<span class="text-success">Đang bán</span>'
                                    : '<span class="text-danger">Ngưng bán</span>'
                            }</td>

                            <td class="nowrap">

                                <a onclick="editProduct('${products[i].productId}')" data-toggle="modal"
                                    data-target="#productModal"
                                    type="button"  href="#">
                              <img class="icon-green" style="width: 15px" src="../resources/pen-solid.svg"
                                  alt="edit" />
                              </a>
                              &nbsp;&nbsp;

                              <a onclick="deleteProduct('${products[i].productId}', false);" href="#">
                              <img class="icon-red" style="width: 15px" src="../resources/trash-can-solid.svg"
                                  alt="delete" />
                              </a>

           
                          </td>
                        </tr>
`;
    }
    pagination += `
                    <li class="page-item">
                        <a class="page-link" onclick="getProducts(${
                            data.page - 1 <= 0 ? 0 : data.page - 1
                        },${size}); activeProductNumber( ${
        data.page - 1 <= 0 ? 0 : data.page - 1
    },${size});" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    `;
    for (var j = 0; j < data.totalPages; j++) {
        pagination += `
                    <li class="page-item"><a class="page-link num-page ${
                        j == pageProductNumberIndex ? 'is-active-page' : ''
                    }" onclick="getProducts(${j},${size}); activeProductNumber( ${j})"   href="#">${j + 1}</a></li>

                    `;
    }
    pagination += `
                    <li class="page-item">
                        <a class="page-link" onclick="getProducts(${
                            data.page + 1 == data.totalPages ? data.page : data.page + 1
                        },${size}) ; activeProductNumber( ${
        data.page + 1 == data.totalPages ? data.page : data.page + 1
    });"  href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" onclick="getProducts(${
                            data.totalPages - 1
                        },${size}); activeProductNumber( ${data.totalPages - 1});"  href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;&raquo;</span>
                        </a>
                    </li>
                `;
    $('#list-product').html(userData);
    if (data.totalPages <= 1) $('.list-pagination-product').html('');
    else $('.list-pagination-product').html(pagination);
}

function getDataSearchProduct(data, size) {
    var products = data.products;
    var userData = '';
    var pagination = '';
    $('#inforProductList').html(
        `Hiển thị từ ${data.page * 10 + 1} ~ ${products.length + data.page * 10} trong tổng số ${
            data.totalRecord
        } product`,
    );
    for (var i = 0; i < products.length; i++) {
        userData += `
                        <tr>
                            <td>${i + 1}</td>
                            <td class="nowrap">${products[i].productId ? products[i].productId : ''}</td>
                            <td class="nowrap">${products[i].productName ? products[i].productName : ''}</td>
                            <td class="nowrap">${products[i].description ? products[i].description : ''}</td>
                            <td class="nowrap text-success">$${
                                products[i].productPrice ? products[i].productPrice : ''
                            }</td>
                            <td class="nowrap">${
                                products[i].sales == true
                                    ? '<span class="text-success">Đang bán</span>'
                                    : '<span class="text-danger">Ngưng bán</span>'
                            }</td>

                            <td class="nowrap">

                                <a onclick="editProduct('${products[i].productId}')" data-toggle="modal"
                                    data-target="#productModal"
                                    type="button"  href="#">
                              <img class="icon-green" style="width: 15px" src="../resources/pen-solid.svg"
                                  alt="edit" />
                              </a>
                              &nbsp;&nbsp;

                              <a onclick="deleteProduct('${products[i].productId}', true);" href="#">
                              <img class="icon-red" style="width: 15px" src="../resources/trash-can-solid.svg"
                                  alt="delete" />
                              </a>

           
                          </td>
                        </tr>
`;
    }
    pagination += `
                    <li class="page-item">
                        <a class="page-link" onclick="findProductsByPagin(${
                            data.page - 1 <= 0 ? 0 : data.page - 1
                        },${size}); activeProductNumber( ${
        data.page - 1 <= 0 ? 0 : data.page - 1
    },${size});" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    `;
    for (var j = 0; j < data.totalPages; j++) {
        pagination += `
                    <li class="page-item"><a class="page-link num-page ${
                        j == pageProductNumberIndex ? 'is-active-page' : ''
                    }" onclick="findProductsByPagin(${j},${size}); activeProductNumber( ${j})"   href="#">${
            j + 1
        }</a></li>

                    `;
    }
    pagination += `
                    <li class="page-item">
                        <a class="page-link" onclick="findProductsByPagin(${
                            data.page + 1 == data.totalPages ? data.page : data.page + 1
                        },${size}) ; activeProductNumber( ${
        data.page + 1 == data.totalPages ? data.page : data.page + 1
    });"  href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" onclick="findProductsByPagin(${
                            data.totalPages - 1
                        },${size}); activeProductNumber( ${data.totalPages - 1});"  href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;&raquo;</span>
                        </a>
                    </li>
                `;
    $('#list-product').html(userData);
    if (data.totalPages <= 1) $('.list-pagination-product').html('');
    else $('.list-pagination-product').html(pagination);
}

//api find product data with page number
function findProductsByPagin(page = 0, size = 10) {
    currentPageProduct = page;
    priceFrom = mapProductData.priceFrom ? mapProductData.priceFrom : -1;
    priceTo = mapProductData.priceTo ? mapProductData.priceTo : -1;
    var query =
        'productName=' +
        mapProductData.productName +
        '&strIsSales=' +
        mapProductData.isSales +
        '&priceFrom=' +
        priceFrom +
        '&priceTo=' +
        priceTo;
    $.ajax({
        type: 'GET',
        url: 'findproduct.do',
        dataType: 'json',
        data: query + '&page=' + page + '&size=' + size,
        success: function (data) {
            getDataSearchProduct(data);
        },
    });
}

getProducts();

// search results
$('#btnSearchProduct').on('click', function (e) {
    e.preventDefault();
    isSearchingProduct = true;
    pageProductNumberIndex = 0;
    mapProductData = {};
    var form = document.getElementById('searchProductForm');
    var formData = new FormData(form);

    for (var data of formData) {
        mapProductData[data[0]] = data[1];
    }

    var priceFrom = mapProductData.priceFrom;
    console.log(priceFrom, priceTo);
    var priceTo = mapProductData.priceTo;
    if (priceFrom.length > 0) {
        if (!/^\-?[0-9]{1,}$/.test(priceFrom)) {
            alert('giá tìm phải là số');
            return;
        }
        if (priceTo.length > 0) {
            if (!/^\-?[0-9]{1,}$/.test(priceTo)) {
                alert('giá tìm phải là số');
                return;
            }
            if (priceFrom > priceTo) {
                alert('Khoảng giá tìm kiếm phải hợp lệ');
                return;
            }
        }
    }

    findProductsByPagin();
});

// insert product
$('#btnSaveProduct').on('click', async function (e) {
    e.preventDefault();
    var productForm = document.getElementById('insertProductForm');
    var fdTempt = new FormData(productForm);
    var fd = new FormData();
    var mapProduct = {};
    for (var data of fdTempt) {
        if (data[0] === 'isSales') {
            fd.append('strIsSales', data[1] == '0' ? false : true);
        } else fd.append(data[0], data[1]);
        mapProduct[data[0]] = data[1];
        console.log(data);
    }

    if (!isValidProduct(mapProduct.productName, mapProduct.productPrice, mapProduct.description, mapProduct.isSales))
        return;

    if (!isUpdateProduct) {
        $.ajax({
            type: 'POST',
            url: 'insertproduct.do',
            enctype: 'multipart/form-data',
            data: fd,
            dataType: 'JSON',
            contentType: false,
            processData: false,
            success: function (data) {
                getProducts();
                pageProductNumberIndex = 0;
            },
        });
        $('#searchProductForm').trigger('reset');
    }
    if (isUpdateProduct) {
        $.ajax({
            type: 'POST',
            url: 'updateproduct.do',
            enctype: 'multipart/form-data',
            data: fd,
            dataType: 'JSON',
            contentType: false,
            processData: false,
            success: function (data) {
                if (isSearchingProduct) findProductsByPagin(currentPageProduct);
                else getProducts(currentPageProduct);
            },
        });
    }

    $('#insertProductForm').trigger('reset');
    // document.getElementById('productImageEle').src = '../resources/default_image.png ';
    $('#productModal').modal('hide');
});

function resetNotify() {
    var errName = $('#errorProductName');
    var errPrice = $('#errorProductPrice');
    var errDescription = $('#errorProductDescription');
    var errIsSales = $('#errorIsSales').html('');
    errName.html('');
    errPrice.html('');
    errDescription.html('');
    errIsSales.html('');
}

function isValidProduct(productName, productPrice, description, isSales) {
    var isValid = true;
    var errName = $('#errorProductName');
    var errPrice = $('#errorProductPrice');
    var errDescription = $('#errorProductDescription');
    var errIsSales = $('#errorIsSales').html('');
    errName.html('');
    errPrice.html('');
    errDescription.html('');
    errIsSales.html('');
    if (!productName || productName.length == 0) {
        errName.html('tên không được để trống');
        isValid = false;
    } else if (productName.length < 6) {
        errName.html('tên phải lớn hơn 5 ký tự');
        isValid = false;
    }
    if (!productPrice || productPrice.length == 0) {
        errPrice.html('giá không được để trống');
        isValid = false;
    } else if (!isNaN(productPrice)) {
        if (productPrice <= 0) {
            errPrice.html('giá phải lớn hơn 0');
            isValid = false;
        }
    } else {
        errPrice.html('giá phải là số');
        isValid = false;
    }

    if (!isSales) {
        errIsSales.html('vui lòng chọn trạng thái');
        isValid = false;
    }

    return isValid;
}

// show file

$('#fileProductUpload').change(function () {
    var file = $('#fileProductUpload')[0].files[0];
    const reader = new FileReader();
    var productImageEle = document.getElementById('productImageEle');

    reader.addEventListener(
        'load',
        () => {
            // convert image file to base64 string
            productImageEle.src = reader.result;
        },
        false,
    );
    if (file) {
        var fileName = file.name;

        reader.readAsDataURL(file);

        $('#textFileName').val(fileName);
    } else {
        productImageEle.src = '../resources/default_image.png ';

        $('#textFileName').val('');
        $('#fileProductUpload').val('');
    }
});
// reset Form search product
$('#btnResetFormProduct').click(function () {
    pageProductNumberIndex = 0;
    isSearchingProduct = false;
    $('#searchProductForm').trigger('reset');
    getProducts();
});

// delete file
$('#btnDeleteFile').click(function () {
    var productImageEle = document.getElementById('productImageEle');
    productImageEle.src = '../resources/default_image.png ';
    $('#textFileName').val('');
    $('#fileProductUpload').val('');
});

// edit product
function editProduct(id) {
    resetNotify();
    isUpdateProduct = true;
    $('#insertProductForm').trigger('reset');
    $.ajax({
        type: 'GET',
        url: 'findproductbyid.do',
        dataType: 'json',
        data: 'productId=' + id,
        success: function (data) {
            loadDataToForm(data.product);
        },
    });
}

function loadDataToForm(product) {
    $('#modalProductId').val(product.productId);
    $('#modalProductName').val(product.productName);
    $('#modalPrice').val(product.productPrice);
    $('#modalDescription').val(product.description);
    $('#modalIsSales')
        .val(product.sales == true ? 1 : 0)
        .change();
    // var url = 'file:/' + product.productImage.replace('\\', '/');
    // if (product.productImage) $('#productImageEle').attr('src', url);
}

// delete User
function deleteProduct(productId, isSearch = false) {
    var text = 'Bạn có muốn xoá sản phẩm có productId: ' + productId + ' không?';
    if (confirm(text) == true)
        $.ajax({
            type: 'POST',
            url: 'deleteproduct.do',
            data: 'productId=' + productId,
            success: function (data) {
                if (isSearch) {
                    mapData = { ...mapDataSearch };

                    findProductsByPagin(currentPageProduct);
                } else getProducts(currentPageProduct);
            },
        });
}
