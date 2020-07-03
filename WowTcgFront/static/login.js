let savedCustomerId;

logInButton.onclick = async function login () {

    let authLoginValue = document.getElementById('loginField').value;
    let authPasswordValue = document.getElementById('passwordField').value;

    let authRequest = {
        login: authLoginValue,
        password: authPasswordValue,
    };

    let authResponse = await fetch ('http://89.179.245.199:8080/auth/', {

        method: 'POST',

        headers: {
            'Content-Type': 'application/json', // charset=utf-8
            'Accept': 'application/json',
            'Origin': 'http://89.179.245.199:80/',
            'Access-Control-Request-Method': 'POST',
            'Access-Control-Request-Headers': 'Content-Type',
        },
        body: JSON.stringify(authRequest)
    });
    console.log(JSON.stringify(authRequest));

    let authResult = await authResponse.json();
    console.log(authResult);

    document.getElementById('passwordField').value = "";

    for (let key in authResult) {
        savedCustomerId = authResult[key].customerId;
        savedName = authResult[key].customerName;
        console.log(savedCustomerId, savedName);
        document.cookie = "CustomerId= " + authResult[key].customerId;
        document.cookie = "CustomerName= '" + authResult[key].customerName + "'";
        console.log(document.cookie);
    }
    console.log(savedCustomerId);

    if (savedCustomerId != undefined) {
        location.href = 'index.html';
    } else {
        let authDiv = document.getElementById('authorization');
        let createdWarning = document.createElement('div');
        createdWarning.id = "createdLoginWarning";
        createdWarning.innerHTML = "<p> Incorrect login or password </p>"
        setTimeout(function () {
            createdWarning.style.display = 'none';
        }, 3000);
        authDiv.append(createdWarning);
    }
}

signUpButton.onclick = async function signUp () {

    let regLoginValue = document.getElementById('regLoginField').value;
    let regPasswordValue = document.getElementById('regPasswordField').value;
    let regNameValue = document.getElementById('regNameField').value;
    console.log(regLoginValue.length&&regPasswordValue.length&&regNameValue.length);

    if ((4<=regLoginValue.length&&regLoginValue.length<=30)&&(4<=regPasswordValue.length&&regLoginValue.length<=30)&&(4<=regNameValue.length&&regLoginValue.length<=30)) {

        let regRequest = {
            login: regLoginValue,
            password: regPasswordValue,
            customerName: regNameValue,
        };
        let regResponse = await fetch('http://89.179.245.199:8080/reg/', {

            method: 'POST',

            headers: {
                'Content-Type': 'application/json', // charset=utf-8
                'Accept': 'application/json',
                'Origin': 'http://89.179.245.199:80/',
                'Access-Control-Request-Method': 'POST',
                'Access-Control-Request-Headers': 'Content-Type',
            },
            body: JSON.stringify(regRequest)
        });
        console.log(JSON.stringify(regRequest));

        let regResult = await regResponse.status;
        console.log(regResult);

        document.getElementById('regPasswordField').value = "";

        if (regResult == 200) {
            document.getElementById('accountQuestion').innerHTML = "";
            document.getElementById('signUpContainer').innerHTML = " <h2>Account successfully registered </h2>"
            document.getElementById('loginField').value = regLoginValue;
            document.getElementById('passwordField').value = regPasswordValue;
        } else {
            let regDiv = document.getElementById('registration');
            let createdSignUpExistWarning = document.createElement('div');
            createdSignUpExistWarning.className = "createdSignUpWarning";
            createdSignUpExistWarning.innerHTML = "<p> That login is taken. Try another </p>"
            setTimeout(function () {
                createdSignUpExistWarning.style.display = 'none';
            }, 5000);
            regDiv.append(createdSignUpExistWarning);
        }

    } else {
        let regDiv = document.getElementById('registration');
        let createdSignUpWarning = document.createElement('div');
        createdSignUpWarning.className = "createdSignUpWarning";
        createdSignUpWarning.innerHTML = "<p> All fields must contain from 4 to 30 characters </p>"
        setTimeout(function () {
            createdSignUpWarning.style.display = 'none';
        }, 5000);
        regDiv.append(createdSignUpWarning);
    }
}

$('input').on('keypress', function (event) {
    var regex = new RegExp("^[a-zA-Z0-9]+$");
    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (!regex.test(key)) {
        event.preventDefault();
        return false;
    }
});