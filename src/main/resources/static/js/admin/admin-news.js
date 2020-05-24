$(document).ready(function () {

    var dataNews = {};


    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#preview-news-img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }


    $("#change-news-mainImage").change(function () {
        readURL(this);
        var formData = new FormData();
        NProgress.start();
        formData.append('file', $("#change-news-mainImage")[0].files[0]);
        axios.post("/api/upload/upload-image", formData).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                $('.news-main-image').attr('src', res.data.link);
            }
        }, function (err) {
            NProgress.done();
        });
    });


    $("#new-news").on("click", function () {
        dataNews = {};
        $('#input-news-name').val("");
        $("#input-news-content").val("");
        $("#input-news-author").val("");
        $('.news-main-image').attr('src', 'https://www.vietnamprintpack.com/images/default.jpg');
    });


    $(".edit-news").on("click", function () {
        var nInfo = $(this).data("news");
        console.log(nInfo);
        NProgress.start();
        axios.get("/api/news/detail/" + nInfo).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                dataNews.id = res.data.data.id;
                $("#input-news-name").val(res.data.data.name);
                $("#input-news-content").val(res.data.data.content)
                $("#input-news-author").val(res.data.data.author);
                if (res.data.data.mainImage != null) {
                    $('.news-main-image').attr('src', res.data.data.mainImage);
                }
            } else {
                console.log("ahihi");
            }
        }, function (err) {
            NProgress.done();
        })
    });


    $(".btn-save-news").on("click", function () {
        if ($("#input-news-name").val() === "" ||  $("#input-news-author").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }


        dataNews.name = $('#input-news-name').val();
        dataNews.mainImage = $('.news-main-image').attr('src');
        dataNews.content = $("#input-news-content").val();
        dataNews.author = $("#input-news-author").val();
        NProgress.start();
        console.log(dataNews.id);
        var linkPost = "/api/news/create";
        if (dataNews.id) {
            linkPost = "/api/news/update/" + dataNews.id;
        }

        axios.post(linkPost, dataNews).then(function (res) {
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
                'Some error when saving news',
                'error'
            );
        })
    });


});