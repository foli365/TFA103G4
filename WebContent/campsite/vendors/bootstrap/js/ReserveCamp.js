let items = document.querySelectorAll('.carousel .carousel-item')

items.forEach((el) => {
    const minPerSlide = 3
    let next = el.nextElementSibling
    for (var i = 1; i < minPerSlide; i++) {
        if (!next) {
            // wrap carousel by using first child
            next = items[0]
        }
        let cloneChild = next.cloneNode(true)
        el.appendChild(cloneChild.children[0])
        next = next.nextElementSibling
    }
})

window.onload =
    function() {
        var omDiv = document.getElementsByClassName("order-menu")[0],
            H = 0,
            Y = omDiv
        while (Y) {
            H += Y.offsetTop;
            Y = Y.offsetParent;
        }
        window.onscroll = function() {
            var s = document.body.scrollTop || document.documentElement.scrollTop
            if (s > H) {
                omDiv.style = "position:fixed;top:0;right:140px"
            } else {
                omDiv.style = ""
            }
        }
    }