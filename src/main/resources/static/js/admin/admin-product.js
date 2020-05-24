$(document).ready(function () {

    var dataProduct = {};

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview-product-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#change-product-mainImage").change(function () {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#change-product-mainImage")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                $('.product-main-image').attr('src', res.data.link);
            }
        }, function (err) {
            NProgress.done();
        });
    });

    $("#change-product-backgroundImage").change(function () {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#change-product-backgroundImage")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                $('.product-background-image').attr('src', res.data.link);
            }
        }, function (err) {
            NProgress.done();
        });
    });


    $("#new-product").on("click", function () {
        dataProduct = {};
        $('.product-main-image').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');
        $('.product-background-image').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');
        $("#input-product-category").val("");
        $('#input-product-name').val("");
        $('#input-product-infor').val("");
        $("#input-product-price").val("");
        $("#input-product-amount").val("");
    });


    $(".edit-product").on("click", function () {
        var pdInfo = $(this).data("product");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/product/detail/" + pdInfo).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                dataProduct.id = res.data.data.id;
                $("#input-product-name").val(res.data.data.name);
                $("#input-product-infor").val(res.data.data.infor);
                $("#input-product-category").val(res.data.data.categoryId);
                $("#input-product-price").val(res.data.data.price);
                $("#input-product-amount").val(res.data.data.amount);
                if (res.data.data.mainImage != null) {
                    $('.product-main-image').attr('src', res.data.data.mainImage);
                }
                if (res.data.data.backgroundImage != null) {
                    $('.product-background-image').attr('src', res.data.data.backgroundImage);
                }
            } else {
                console.log("ahihi");
            }
        }, function (err) {
            NProgress.done();
        })
    });


    $(".btn-save-product").on("click", function () {
        if ($("#input-product-name").val() === "" || $("#input-product-content").val() === "" || $("#input-product-price").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        dataProduct.mainImage = $('.product-main-image').attr('src');
        dataProduct.backgroundImage = $('.product-background-image').attr('src');
        dataProduct.categoryId = $("#input-product-category").val();
        dataProduct.name = $('#input-product-name').val();
        dataProduct.infor = $('#input-product-infor').val();
        dataProduct.price = $("#input-product-price").val();
        dataProduct.amount = $("#input-product-amount").val();
        NProgress.start();
        console.log(dataProduct.id);
        var linkPost = "/api/product/create";
        if (dataProduct.id) {
            linkPost = "/api/product/update/" + dataProduct.id;
        }

        axios.post(linkPost, dataProduct).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                swal(
                    'Good job!',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
                });
            } else {
                swal(
                    'Error',
                    res.data.message,
                    'error'
                );
            }
        }, function (err) {
            NProgress.done();
            swal(
                'Error',
                'Some error when saving product',
                'error'
            );
        })
    });


});