$(function() {

    $('#client-form-link').click(function(e) {
        $("#client-form").delay(100).fadeIn(100);
        $("#majstor-form").fadeOut(100);
        $('#majstor-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#majstor-form-link').click(function(e) {
        $("#majstor-form").delay(100).fadeIn(100);
        $("#client-form").fadeOut(100);
        $('#client-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});



$(document).ready(function (){

    var gradovi = [
        {"city": "Берово"},{"city": "Битола"},{"city": "Богданци"},{"city": "Валандово"},{"city": "Велес"},{"city": "Виница"},
        {"city": "Гевгелија"},{"city": "Гостивар"},{"city": "Дебар"},{"city": "Делчево"},{"city": "Демир Капија"},{"city": "Демир Хисар"},
        {"city": "Кавадарци"},{"city": "Кичево"},{"city": "Кочани"},{"city": "Кратово"},{"city": "Крива Паланка"},{"city": "Крушево"},
        {"city": "Куманово"},{"city": "Македонски Брод"},{"city": "Македонска Каменица"},{"city": "Неготино"},{"city": "Охрид"},{"city": "Пехчево"},
        {"city": "Прилеп"},{"city": "Пробиштип"},{"city": "Радовиш"},{"city": "Ресен"},{"city": "Свети Николе"},{"city": "Скопје"},
        {"city": "Струга"},{"city": "Струмица"},{"city": "Тетово"},{"city": "Штип"}
    ];

    for (var index = 0; index <= gradovi.length; index++) {
        $('#cityNameList').append('<option value="' + gradovi[index].city + '">'+ gradovi[index].city + '</option>');
    }
});



$(document).ready(function() {

    $('#password').keyup(function() {
        var password = $('#password').val();
        if (checkStrength(password) == false) {
            $('#registerBtn').attr('disabled', true);
        }
    });
    $('#confirm-password').blur(function() {
        if ($('#password').val() !== $('#confirm-password').val()) {
            $('#popover-cpassword').removeClass('invisible');
            $('#registerBtn').attr('disabled', true);
        } else {
            $('#popover-cpassword').addClass('invisible');
            $('#registerBtn').attr('disabled', false);
        }
    });



    function checkStrength(password) {
        var strength = 0;


        //If password contains both lower and uppercase characters, increase strength value.
        if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) {
            strength += 1;
          //  $('#popover-password-top').addClass('invisible');


        } else {
        //    $('#popover-password-top').removeClass('invisible');
        }

        //If it has numbers and characters, increase strength value.
        if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) {
            strength += 1;
         //   $('#popover-password-top').addClass('invisible');

        } else {
         //   $('#popover-password-top').removeClass('invisible');
        }

        //If it has one special character, increase strength value.
        if (password.match(/([!,%,&,@,#,$,^,*,?,_,~,.])/)) {
            strength += 1;
           // $('#popover-password-top').addClass('invisible');

        } else {
          //  $('#popover-password-top').removeClass('invisible');
        }

        if (password.length > 7) {
            strength += 1;
          //  $('#popover-password-top').addClass('invisible');

        } else {
          //  $('#popover-password-top').removeClass('invisible');
        }


        // If value is less than 2

        if (strength < 2) {
            $('#popover-password-top').removeClass('invisible');
            $('#result').removeClass()
            $('#password-strength').addClass('progress-bar-danger');

            $('#result').addClass('text-danger').text('Very Week');
            $('#password-strength').css('width', '10%');
        } else if (strength == 2) {
            $('#result').addClass('good');
            $('#password-strength').removeClass('progress-bar-danger');
            $('#password-strength').addClass('progress-bar-warning');
            $('#result').addClass('text-warning').text('Week')
            $('#password-strength').css('width', '60%');
            return 'Week'
        } else if (strength == 4) {
            $('#result').removeClass()
            $('#result').addClass('strong');
            $('#password-strength').removeClass('progress-bar-warning');
            $('#password-strength').addClass('progress-bar-success');
            $('#result').addClass('text-success').text('Strong');
            $('#password-strength').css('width', '100%');
            $('#popover-password-top').addClass('invisible');

            return 'Strong'
        }

    }

});


$(document).ready(function() {

    $('#password2').keyup(function() {
        var password = $('#password2').val();
        if (checkStrength(password) == false) {
            $('#registerBtn2').attr('disabled', true);
        }
    });
    $('#confirm-password2').blur(function() {
        if ($('#password2').val() !== $('#confirm-password2').val()) {
            $('#popover-cpassword2').removeClass('invisible');
            $('#registerBtn2').attr('disabled', true);
        } else {
            $('#popover-cpassword2').addClass('invisible');
            $('#registerBtn2').attr('disabled', false);
        }
    });



    function checkStrength(password) {
        var strength = 0;


        //If password contains both lower and uppercase characters, increase strength value.
        if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) {
            strength += 1;
            $('#popover-password-top2').addClass('invisible');


        } else {
            $('#popover-password-top2').removeClass('invisible');
        }

        //If it has numbers and characters, increase strength value.
        if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) {
            strength += 1;
            $('#popover-password-top2').addClass('invisible');

        } else {
            $('#popover-password-top2').removeClass('invisible');
        }

        //If it has one special character, increase strength value.
        if (password.match(/([!,%,&,@,#,$,^,*,?,_,~,.])/)) {
            strength += 1;
            $('#popover-password-top2').addClass('invisible');

        } else {
            $('#popover-password-top2').removeClass('invisible');
        }

        if (password.length > 7) {
            strength += 1;
            $('#popover-password-top2').addClass('invisible');

        } else {
            $('#popover-password-top2').removeClass('invisible');
        }


        // If value is less than 2

        if (strength < 2) {
            $('#result2').removeClass()
            $('#password-strength2').addClass('progress-bar-danger');

            $('#result2').addClass('text-danger').text('Very Week');
            $('#password-strength2').css('width', '10%');
        } else if (strength == 2) {
            $('#result2').addClass('good');
            $('#password-strength2').removeClass('progress-bar-danger');
            $('#password-strength2').addClass('progress-bar-warning');
            $('#result2').addClass('text-warning').text('Week')
            $('#password-strength2').css('width', '60%');
            return 'Week'
        } else if (strength == 4) {
            $('#result2').removeClass()
            $('#result2').addClass('strong');
            $('#password-strength2').removeClass('progress-bar-warning');
            $('#password-strength2').addClass('progress-bar-success');
            $('#result2').addClass('text-success').text('Strong');
            $('#password-strength2').css('width', '100%');

            return 'Strong'
        }

    }

});

$(document).ready(function (){
    $(".owl-carousel").owlCarousel({
        autoplay:true,
        autoplayHoverPause: true,
        autoplayTimeout: 2000,
        items: 4,
        nav: true,
        loop:true,
        lazyLoad: true,
        margin: 5,
        padding: 5,
        stagePadding: 5,
        responsive: {
            0 : {
                items: 1,
                dots: false
            },
            485 : {
                items: 2,
                dots: false
            },
            728 : {
                items: 3,
                dots: false
            },
            960 : {
                items: 4,
                dots: false
            },
            1200 : {
                items: 5,
                dots: true
            }
        }
    });

});


// lazy loading imgs in category page
$('.card-img-top').lazyLoad();