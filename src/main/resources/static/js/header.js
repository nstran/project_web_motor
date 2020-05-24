// ROTATE-ICON
$(".rotate-icon").click(function() {
    $(".rotate").toggleClass("down");
})

// DROP-CATEGORY-RESPONSIVE
function dropCategory() {
    document.getElementById("sub-menu").classList.toggle("show");
}
window.onclick = function(event) {
    if (!event.target.matches('.dropbtn')) {
        var dropdowns = document.getElementsByClassName("sub-menu");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('.sidepanel .show')) {
                openDropdown.classList.remove('.sidepanel .show');
            }
        }
    }
}

// RESPONSIVE MENU

document.getElementById('onpenMenu').addEventListener('click', () => {
    mySidepanel.style.transform = 'translatex(0)';
});

document.getElementById('closeMenu').addEventListener('click', () => {
    mySidepanel.style.transform = 'translatex(-100%)';
});


document.getElementById('search').addEventListener('click', () => {
    searchResponsive.style.transform = 'translatex(0%)';
});

document.getElementById('closebtn').addEventListener('click', () => {
    searchResponsive.style.transform = 'translatex(100%)';
});


