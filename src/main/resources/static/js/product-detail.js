//SLIDER SHOW
$('.owl-carousel').owlCarousel({
    loop: true,
    margin: 20,
    dots: false,
    nav: true,
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 1
        },
        1000: {
            items: 1
        }
    }
})
// ANIMATION-PRODUCT
// Column-1
$(window).scroll(function () {
    $(".library .col-md-3:nth-child(1)").each(function () {
        var pos = $(this).offset().top;
        var winTop = $(window).scrollTop();
        if (pos < winTop + 500) {
            $(this).addClass("slide-product-column-1");
        }
    });
});

// Column-2
$(window).scroll(function () {
    $(".library .col-md-3:nth-child(2)").each(function () {
        var pos = $(this).offset().top;
        var winTop = $(window).scrollTop();
        if (pos < winTop + 500) {
            $(this).addClass("slide-product-column-2");
        }
    });
});

// Column-3
$(window).scroll(function () {
    $(".library .col-md-3:nth-child(3)").each(function () {
        var pos = $(this).offset().top;
        var winTop = $(window).scrollTop();
        if (pos < winTop + 500) {
            $(this).addClass("slide-product-column-3");
        }
    });
});

// Column-4
$(window).scroll(function () {
    $(".library .col-md-3:nth-child(4)").each(function () {
        var pos = $(this).offset().top;
        var winTop = $(window).scrollTop();
        if (pos < winTop + 500) {
            $(this).addClass("slide-product-column-4");
        }
    });
});