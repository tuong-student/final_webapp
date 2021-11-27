const pathAssets = "assets";
window.addEventListener("scroll", event => {
    headertest();
    footertest();
});

function headertest() {
    const header = document.querySelector("header");
        if (window.pageYOffset > 800) {
            header.style.position = "relative"
        } else {
            header.style.position = "sticky"
        }
};

function footertest() {
    const footer = document.querySelector("footer");
        if (window.pageYOffset > 800) {
            footer.style.position = "sticky"
        } else {
            footer.style.position = "relative"
        }
};

headertest();
footertest();

window.addEventListener("load", event => {
    // Anime Modules
    //let delay = 1;
    observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.intersectionRatio > 0) {
                entry.target.classList.add('animeModules');
            }
        });
    });

    function animeModules() {
        document.querySelectorAll('section').forEach(el => observer.observe(el))
        document.querySelectorAll('footer').forEach(el => observer.observe(el))
        document.querySelectorAll('header').forEach(el => observer.observe(el))
    }

    animeModules();
})