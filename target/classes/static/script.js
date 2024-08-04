/* Nhảy hình chỗ SP */
document
    .querySelectorAll('.product-image-container')
    .forEach(
        function (container) {
            container
                .addEventListener(
                    'mouseover',
                    function () {
                        this
                            .querySelector('.product-hover-image').style.opacity = '1';
                        this
                            .querySelector('.quick-add').style.opacity = '1';
                        this
                            .querySelector('.quick-add').style.bottom = '0'; // Move the text up when hovering
                    });

            container
                .addEventListener(
                    'mouseout',
                    function () {
                        this
                            .querySelector('.product-hover-image').style.opacity = '0';
                        this
                            .querySelector('.quick-add').style.opacity = '0';
                        this
                            .querySelector('.quick-add').style.bottom = '0px';
                    });
        });
window.addEventListener('scroll', function () {
    var navbar = document.querySelector('.navbar');
    if (navbar) { // Check if the navbar exists
        if (window.scrollY > 50) {
            navbar.classList.add('sticky-top'); // Thêm lớp sticky-top khi cuộn
            navbar.classList.remove('hide-nav'); // Xóa lớp hide-nav nếu có
        } else {
            navbar.classList.remove('sticky-top'); // Xóa lớp sticky-top nếu không cuộn
            navbar.classList.add('hide-nav'); // Thêm lớp hide-nav khi không cuộn
        }
    }
});

const swiper = new Swiper('.swiper-container', {
    // Optional parameters
    loop: true,
    // centeredSlides: true,
    slidesPerView: 4, // Initial slides per view
    spaceBetween: 0, // Space between slides

    // Navigation arrows
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },

    // Responsive breakpoints
    breakpoints: {
        // when window width is >= 768px
        768: {
            slidesPerView: 2,
        },
        // when window width is >= 1024px
        1024: {
            slidesPerView: 4,
        },
        // when window width is >= 1280px
        1280: {
            slidesPerView: 4,
        }
    }
});
document.addEventListener('DOMContentLoaded', function () {
    var sidebarLinks = document.querySelectorAll('.sidebar li');
    var sections = document.querySelectorAll('.form-section');
    sidebarLinks.forEach(function (link) {
        link.addEventListener('click', function () {
            sidebarLinks.forEach(function (l) {
                l.classList.remove('active');
                var indicator = l.querySelector('.indicator');
                if (indicator) {
                    indicator.style.display = 'none';
                }
            });
            link.classList.add('active');
            var indicator = link.querySelector('.indicator');
            if (indicator) {
                indicator.style.display = 'block';
            }
            var target = link.getAttribute('data-target');
            sections.forEach(function (section) {
                section.style.display = 'none';
            });
            document.querySelector(target).style.display = 'block';
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    var tabLinks = document.querySelectorAll('.tab-link');
    var contents = document.querySelectorAll('.order-history-content');

    tabLinks.forEach(function (link) {
        link.addEventListener('click', function (event) {
            event.preventDefault();

            tabLinks.forEach(function (l) {
                l.classList.remove('active');
                l.style.color = '#888';
                l.style.borderBottom = 'none';
            });
            contents.forEach(function (content) {
                content.style.display = 'none';
            });

            link.classList.add('active');
            link.style.color = '#2d7a32';
            link.style.borderBottom = '2px solid #2d7a32';
            var target = document.querySelector(link.getAttribute('data-target'));
            target.style.display = 'block';
        });
    });
});

const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signInButton.addEventListener('click', () => {
    container.classList.remove('right-panel-active');
});
signUpButton.addEventListener('click', () => {
    container.classList.add('right-panel-active');
});


