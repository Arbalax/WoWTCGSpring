let savedCustomerId;

logInButton.onclick = async function login () {
    startLoadingAnimation();
    let authLoginValue = document.getElementById('loginField').value;
    let authPasswordValue = document.getElementById('passwordField').value;

    let authRequest = {
        login: authLoginValue,
        password: authPasswordValue,
    };

    // let authResponse = await fetch ('http://89.179.245.199:8080/user/login/', {
    let authResponse = await fetch ('https://wowtcgserver.herokuapp.com/user/login/', {

        method: 'POST',

        headers: {
            'Content-Type': 'application/json', // charset=utf-8
            'Accept': 'application/json',
            'Origin': 'http://www.wowtcgdatabase.com/',
            'Access-Control-Request-Method': 'POST',
            'Access-Control-Request-Headers': 'Content-Type',
        },
        body: JSON.stringify(authRequest)
    });


    let authResult = await authResponse.json();
    stopLoadingAnimation();

    document.getElementById('passwordField').value = "";

    for (let key in authResult) {
        savedCustomerId = authResult[key].customerId;
        savedName = authResult[key].customerName;
        document.cookie = "CustomerId= " + authResult[key].customerId;
        document.cookie = "CustomerName= '" + authResult[key].customerName + "'";
    }

    if (savedCustomerId != undefined) {
        location.href = 'index.html';

    } else if ($("#loginWarning").is(':visible')) {
        clearTimeout(loginWarningFade);
        loginWarningFade = setTimeout(function () {
            $("#loginWarning").fadeOut();
        }, 3000);

    } else {
        $("#loginWarning").show();
        loginWarningFade = setTimeout(function () {
            $("#loginWarning").fadeOut();
        }, 3000);
    }
}

signUpButton.onclick = async function signUp () {

    let regLoginValue = document.getElementById('regLoginField').value;
    let regPasswordValue = document.getElementById('regPasswordField').value;
    let regNameValue = document.getElementById('regNameField').value;

    if ((4<=regLoginValue.length&&regLoginValue.length<=30)&&(4<=regPasswordValue.length&&regLoginValue.length<=30)&&(4<=regNameValue.length&&regLoginValue.length<=30)) {
        startLoadingAnimation();
        let regRequest = {
            login: regLoginValue,
            password: regPasswordValue,
            customerName: regNameValue,
        };
        // let regResponse = await fetch('http://89.179.245.199:8080/user/add/', {
        let regResponse = await fetch('https://wowtcgserver.herokuapp.com/user/add/', {

            method: 'POST',

            headers: {
                'Content-Type': 'application/json', // charset=utf-8
                'Accept': 'application/json',
                'Origin': 'http://www.wowtcgdatabase.com/',
                'Access-Control-Request-Method': 'POST',
                'Access-Control-Request-Headers': 'Content-Type',
            },
            body: JSON.stringify(regRequest)
        });


        let regResult = await regResponse.status;


        document.getElementById('regPasswordField').value = "";

        if (regResult == 200) {
            stopLoadingAnimation();
            document.getElementById('accountQuestion').innerHTML = "";
            document.getElementById('signUpContainer').innerHTML = " <h2>Account successfully registered </h2>"
            document.getElementById('loginField').value = regLoginValue;
            document.getElementById('passwordField').value = regPasswordValue;
        } else {
            stopLoadingAnimation();
            if ($("#signUpWarningExist").is(':visible')) {
                clearTimeout(signUpWarningExistFade);
                signUpWarningExistFade = setTimeout(function () {
                    $("#signUpWarningExist").fadeOut();
                }, 5000);

            } else {
                $("#signUpWarningExist").show();
                signUpWarningExistFade = setTimeout(function () {
                    $("#signUpWarningExist").fadeOut();
                }, 5000);
            }
        }
    } else {
        if ($("#signUpWarningCharacters").is(':visible')) {
            clearTimeout(signUpWarningCharactersFade);
            signUpWarningCharactersFade = setTimeout(function () {
                $("#signUpWarningCharacters").fadeOut();
            }, 5000);

        } else {
            $("#signUpWarningCharacters").show();
            signUpWarningCharactersFade = setTimeout(function () {
                $("#signUpWarningCharacters").fadeOut();
            }, 5000);
        }
    }
}

$('input').on('keypress', function (event) {
    let regex = new RegExp("^[a-zA-Z0-9]+$");
    let key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (!regex.test(key)) {
        event.preventDefault();
        return false;
    }
});

function startLoadingAnimation () {
    $(".cssload-container").show();
}

function stopLoadingAnimation () {
    $(".cssload-container").hide();
}