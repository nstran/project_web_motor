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

    $("#change-product-image-design").change(function () {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#change-product-image-design")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                $('.product-link-design').attr('src', res.data.link);
            }
        }, function (err) {
            NProgress.done();
        });
    });

    $("#change-product-image-library").change(function () {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#change-product-image-library")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                $('.product-link-library').attr('src', res.data.link);
            }
        }, function (err) {
            NProgress.done();
        });
    });

    $("#change-product-image-option").change(function () {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#change-product-image-option")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                $('.product-link-product-option').attr('src', res.data.link);
            }
        }, function (err) {
            NProgress.done();
        });
    });


    $("#new-product-image").on("click", function () {
        $('#input-product').val("");
        $('.product-link-design').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');
        $('.product-link-library').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');
        $('.product-link-product-option').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');
        $("#input-content-design").val("");
        $('#input-product-infor').val("");
        $('#input-color-name').val("");
    });


    $(".edit-product-image").on("click", function () {
        var pdInfo = $(this).data("image");
        console.log(pdInfo);
        NProgress.start();
        axios.get("/api/product-image/detail/" + pdInfo).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                dataProduct.id = res.data.data.id;
                dataProduct.productId = res.data.data.productId;
                console.log(dataProduct);
                $("#input-content-design").val(res.data.data.contentDesign);
                $("#input-product-infor").val(res.data.data.productInfor);
                $("#input-color-name").val(res.data.data.colorName);
                $("#input-product").val(res.data.data.productId);
                if (res.data.data.linkDesign != null) {
                    $('.product-link-design').attr('src', res.data.data.linkDesign);
                }
                if (res.data.data.linkLibrary != null) {
                    $('.product-link-library').attr('src', res.data.data.linkLibrary);
                }
                if (res.data.data.linkProductOption != null) {
                    $('.product-link-product-option').attr('src', res.data.data.linkProductOption);
                }
            } else {
                console.log("ahihi");
            }
        }, function (err) {
            NProgress.done();
        })
    });


    $(".btn-save-product-image").on("click", function () {
        if ($("#input-product").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        dataProduct.linkDesign = $('.product-link-design').attr('src');
        dataProduct.linkLibrary = $('.product-link-library').attr('src');
        dataProduct.linkProductOption = $('.product-link-product-option').attr('src');
        dataProduct.contentDesign = $('#input-content-design').val();
        dataProduct.productInfor = $('#input-product-infor').val();
        dataProduct.colorName = $('#input-color-name').val();
        dataProduct.productId = $("#input-product").val();
        NProgress.start();
        console.log(dataProduct.id);
        var linkPost = "/api/product-image/create";
        if (dataProduct.id) {
            linkPost = "/api/product-image/update/" + dataProduct.id;
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