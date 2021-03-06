$(document).ready(function () {

    $('.minus-btn').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var $input = $this.closest('div').find('input');
        var value = parseInt($input.val());

        if (value > 1) {
            value = value - 1;
        }

        $input.val(value);

    });

    $('.plus-btn').on('click', function (e) {
        e.preventDefault();
        var $this = $(this);
        var $input = $this.closest('div').find('input');
        var value = parseInt($input.val());

        if (value < 100) {
            value = value + 1;
        } else {
            value = 100;
        }

        $input.val(value);
    });

    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    var delete_cookie = function (name) {
        document.cookie = name + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    };

    function getCookie(name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    }

    function checkCookie() {
        var user = getCookie("username");
        if (user != "") {
            alert("Welcome again " + user);
        } else {
            user = prompt("Please enter your name:", "");
            if (user != "" && user != null) {
                setCookie("username", user, 365);
            }
        }
    }

    $(".add-to-cart").on("click", function () {
        var dataCart = {};
        var pdInfo = $(this).data("product");
        dataCart.amount = document.getElementById('getAmount').value;
        dataCart.productId = pdInfo;
        console.log(dataCart);
        dataCart.guid = getCookie("guid");
        // dataCart.sizeId = $(this).data("size");
        if (document.getElementById('getAmount').value < 0) {
            document.getElementById('getAmount').value = 1;
        }

        NProgress.start();
        var linkPost = "/api/cart-product/add";
        axios.post(linkPost, dataCart).then(function (res) {
            NProgress.done();
            if (res.data.success) {
                swal(
                    'Thành Công',
                    res.data.message,
                    'success'
                ).then(function () {
                    location.reload();
                });
            } else {
                swal(
                    'Lỗi',
                    res.data.message,
                    'error'
                );
                document.getElementById('getAmount').value = 1;
            }
        }, function (err) {
            NProgress.done();
            swal(
                'Lỗi',
                'Lỗi',
                'error'
            );
        });
    });

});