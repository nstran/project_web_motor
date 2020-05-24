$(document).ready(function () {

    var dataOrder = {};

    $("#new-order").on("click", function () {
        dataOrder = {};
        $("#input-customer-name").val("");
        $('#input-user-name').val("");
        $('#input-address').val("");
        $("#input-email").val("");
        $('#input-phone-number').val("");
        $("#input-cmnd").val("");
        $("#input-status").val("");

    });


    $(".edit-order").on("click", function () {
        var oInfo = $(this).data("order");
        console.log(oInfo);
        NProgress.start();
        axios.get("/api/order/detail/" + oInfo).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                dataOrder.id = res.data.data.id;
                $("#input-user-name").val(res.data.data.userName);
                $("#input-customer-name").val(res.data.data.customerName);
                $("#input-address").val(res.data.data.address);
                $("#input-email").val(res.data.data.email);
                $("#input-phone-number").val(res.data.data.phoneNumber);
                $("#input-cmnd").val(res.data.data.cmnd);
                $("#input-status").val(res.data.data.statusId);

            } else {
                console.log("ahihi");
            }
        }, function (err) {
            NProgress.done();
        })
    });


    $(".btn-save-order").on("click", function () {
        if ($("#input-customer-name").val() === "" || $("#input-cmnd").val() === "" || $("#input-address").val() === "") {
            swal(
                'Error',
                'You need to fill all values',
                'error'
            );
            return;
        }
        dataOrder.customerName = $('#input-customer-name').val();
        dataOrder.address = $('#input-address').val();
        dataOrder.email = $('#input-email').val();
        dataOrder.phoneNumber = $("#input-phone-number").val();
        dataOrder.cmnd = $("#input-cmnd").val();
        dataOrder.statusId = $("#input-status").val();
        NProgress.start();
        console.log(dataOrder.id);
        var linkPost = "/api/order/create";
        if (dataOrder.id) {
            linkPost = "/api/order/update/" + dataOrder.id;
        }

        axios.post(linkPost, dataOrder).then(function (res) {
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
                'Some error when saving order',
                'error'
            );
        })
    });


});