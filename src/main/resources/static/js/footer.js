// BACK TO TOP

window.onscroll = function () {
    scrollFunction()
}

function scrollFunction() {
    if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
        document.getElementById("back-to-top").style.display = "block";
    } else {
        document.getElementById("back-to-top").style.display = "none";
    }
}

$('#back-to-top').click(function () {
    $('body,html').animate({
        scrollTop: 0
    }, 300);
});
