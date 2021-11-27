/********************/
// Main Nav

function mainNav() {
    const mainNav = document.querySelector(".mainNav");
    const navLinks = [{
            name: "About Us",
            url: "about.html",
            id: "about"
        },
        {
            name: "Our Products",
            url: "products.html",
            id: "products"
        },
        {
            name: "Collection",
            url: "collection.html",
            id: "collection"
        },
        {
            name: "Contacts",
            url: "contacts.html",
            id: "contacts"
        },
    ];

    let templateNav = `
        <div class="mainNav__logo">
            <a href="#home"><img src="${pathAssets}/images/logo-white.svg" alt=""></a>
        </div>
        <div class="mainNav__icon"><span></span></div>
        <div class="mainNav__wrapper">
            <div class="mainNav__links">
            </div>
        </div>`;
    
    document.querySelector('.mainNav').insertAdjacentHTML('beforeend', templateNav);

    navLinks.forEach(function (el) {
        let template = `
            <a class="mainNav__linkItem" href="#${el.id}">${el.name}</a>`;
            document.querySelector('.mainNav__links').insertAdjacentHTML('beforeend', template);
    });

    const logo = document.querySelector(".mainNav__logo img");

    if (mainNav.classList.contains("navDark")) {
        logo.src = `${pathAssets}/images/logo.svg`;
    };

    // Sticky Nav Bar
    window.onscroll = function () {
        getSticky();
    }

    function getSticky() {
        if (window.pageYOffset > 72) {
            mainNav.classList.add("navSticky");
            logo.src = `${pathAssets}/images/logo.svg`;
        } else {
            if (mainNav.classList.contains("navDark")) {
                mainNav.classList.remove("navSticky");
            } else {
                mainNav.classList.remove("navSticky");
                logo.src = `${pathAssets}/images/logo-white.svg`;
            }
        };
    }
    getSticky();

    // Open Menu Mobie
    const iconNav = document.querySelector('.mainNav__icon');
    iconNav.addEventListener('click', openNavMobile);

    function openNavMobile() {
        if (mainNav.classList.contains('navOpen')) {
            mainNav.classList.remove('navOpen');
            document.querySelector('body').style.overflowY = "initial";
            if (window.innerWidth < 799) {
                setTimeout(() => {
                    document.querySelector('.mainNav .mainNav__wrapper').style.height = "auto";
                }, 600);
            }
        } else {
            mainNav.classList.add('navOpen');
            document.querySelector('body').style.overflowY = "hidden";
            if (window.innerWidth < 799) {
                document.querySelector('.mainNav .mainNav__wrapper').style.height = window.innerHeight + "px";
            }
        }
    }

    const linkItem = document.querySelectorAll(".mainNav__linkItem");

    if (window.innerWidth < 799) {
        linkItem.forEach(function (el) {
            el.addEventListener("click", openNavMobile);
        });
    }
};

mainNav();

/********************/
// Footer

function footer() {
    const footer = {
        logo: `${pathAssets}/images/logo-white.svg`,
        name: "Lading Page",
        text: "All Rights Reserved",
        creditsName: "Longday",
        creditsUrl: "#",
        link1: "Privacy Policy",
        link1Url: "#",
    };

    const day = new Date(),
        year = day.getFullYear();

    let templateFooter = `
        <div class="footer__top anime">
            <a href="#"><img class="footer__logo" src="${footer.logo}" alt=""></a>
        </div>
        <div class="footer__bottom anime">
            <p class="footer__text">${year} © ${footer.name} — ${footer.text} | made by <a href="${footer.creditsUrl}" class="hiperlink" target="_blank">${footer.creditsName}</a></p>
            <div class="footer__links">
                <a href="${footer.link1Url}" class="footer__link hiperlink"">${footer.link1}</a>
            </div>
        </div>`;
    document.querySelector(".footer").insertAdjacentHTML("beforeend", templateFooter);
};

footer();