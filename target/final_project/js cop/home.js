/********************/
// Slider Heading

function sliderHeading() {

    const sliderHeadingItems = [{
            img: `${pathAssets}/images/default.jpg`,
            alt: "",
        },
        {
            img: `${pathAssets}/images/default.jpg`,
            alt: "",
        },
        {
            img: `${pathAssets}/images/default.jpg`,
            alt: "",
        },
        {
            img: `${pathAssets}/images/default.jpg`,
            alt: "",
        },
    ];

    const heading = {
        title: "Landing Page",
        subtitle: "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts."
    };

    let templatesliderHeading = `
        <div class="sliderHeading__wrapper">
            <div class="sliderHeading__text anime">
                <div class="sliderHeading__title">${heading.title}</div>
                <div class="sliderHeading__subtitle">${heading.subtitle}</div>
            </div>

            <div class="sliderHeading__slider swiper-container anime">
                <div class="swiper-wrapper"></div>
                <div class="swiper-pagination"></div>
                <div class="sliderHeading__arrows">
                    <div class="swiper-button-prev"></div>
                    <div class="swiper-button-next"></div>
                </div>
            </div>
        </div>`;

    document.querySelector(".sliderHeading").insertAdjacentHTML("beforeend", templatesliderHeading);

    sliderHeadingItems.forEach(function (el) {
        let templateSlider = `
            <div class="swiper-slide">
                <figure class="sliderHeading__image anime">
                    <img src="${el.img}" alt="">
                </figure>
            </div>`;
        document.querySelector(".sliderHeading .swiper-wrapper").insertAdjacentHTML("beforeend", templateSlider);

    });

    var mySwiper = new Swiper('.sliderHeading .swiper-container', {
        // Optional parameters
        loop: true,
        speed: 500,

        // If we need pagination
        pagination: {
            el: '.sliderHeading .swiper-pagination',
        },

        // Navigation arrows
        navigation: {
            nextEl: '.sliderHeading .swiper-button-next',
            prevEl: '.sliderHeading .swiper-button-prev',
        },
    });


};

sliderHeading();

/********************/
// Intro

function intro() {
    const intro = [{
            img: `${pathAssets}/images/default.jpg`,
            alt: "alt",
            title: "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.",
        },
    ];

    intro.forEach(function (el) {
        let template = `
            <div class="moduleIntro__wrapper">
                <figure class="moduleIntro__image animeImg">
                    <img src="${el.img}" alt="${el.alt}">
                </figure>
                <div class="moduleIntro__content">
                    <h2 class="moduleIntro__title anime">${el.title}</h2>
                </div>
            </div>`;

        document.querySelector(".moduleIntro").insertAdjacentHTML("beforeend", template);
    });
};

intro();

/********************/
// Content Media - about

function contentMedia() {
    const contentMedia = [{
            img: `${pathAssets}/images/default.jpg`,
            alt: "alt",
            title: "About Us",
            text: "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.",
            ctaText: "know more",
            ctaStyle: "cta01"
        },
    ];

    contentMedia.forEach(function (el) {
        let template = `
            <div class="contentMedia__wrapper">
                <figure class="contentMedia__image animeImg">
                    <img src="${el.img}" alt="${el.alt}">
                </figure>
                <div class="contentMedia__content">
                    <h2 class="contentMedia__title mainTitle anime">${el.title}</h2>
                    <p class="contentMedia__description anime">${el.text}</p>
                    <div class="ctaContainer anime"><a href="" class="cta ${el.ctaStyle}">${el.ctaText}</a></div>
                </div>
            </div>`;
        document.querySelector('.contentMedia.about').insertAdjacentHTML("beforeend", template);
    });
};

contentMedia();

/********************/
// Content Media - Collection

function contentMedia2() {
    const contentMedia = [{
            img: `${pathAssets}/images/default.jpg`,
            alt: "alt",
            title: "Collection",
            text: "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.",
            ctaText: "know more",
            ctaStyle: "cta01"
        },
    ];

    contentMedia.forEach(function (el) {
        let template = `
            <div class="contentMedia__wrapper">
                <figure class="contentMedia__image animeImg">
                    <img src="${el.img}" alt="${el.alt}">
                </figure>
                <div class="contentMedia__content">
                    <h2 class="contentMedia__title mainTitle anime">${el.title}</h2>
                    <p class="contentMedia__description anime">${el.text}</p>
                    <div class="ctaContainer anime"><a href="" class="cta ${el.ctaStyle}">${el.ctaText}</a></div>
                </div>
            </div>`;
        document.querySelector('.contentMedia.collection').insertAdjacentHTML("beforeend", template);
    });
};

contentMedia2();

/********************/
// CTA Block

function ctaBlock() {
    const ctaBlock = [{
            title: "Get in touch",
            subtitle: "Have a question or comment? We would love to hear from you.",
            text: "",
            ctaText: "Contact Us",
            ctaStyle: "cta01"
        },
    ];

    ctaBlock.forEach(function (el) {
        let template = `
            <div class="ctaBlock__wrapper">
                <div class="ctaBlock__content">
                    <div class="ctaBlock__text">
                        <h2 class="ctaBlock__title mainTitle anime">${el.title}</h2>
                        <h3 class="ctaBlock__subtitle anime">${el.subtitle}</h3>
                        <div class="ctaContainer anime"><a href="" class="cta ${el.ctaStyle}">${el.ctaText}</a></div>
                    </div>
                </div>
            </div>`;
        document.querySelector(".ctaBlock").insertAdjacentHTML("beforeend", template);
    });
};

ctaBlock();