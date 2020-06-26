let savedCustomerId;

$('input').on('keypress', function (event) {
    var regex = new RegExp("^[a-zA-Z0-9]+$");
    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (!regex.test(key)) {
        event.preventDefault();
        return false;
    }
});

logInButton.onclick = async function login () {
    // let authForm = document.forms.loginForm;
    // let authLogin = authForm.elements.loginField;
    // let authLoginValue = authLogin.value;
    // let authPassword = authForm.elements.passwordField;
    // let authPasswordValue = authPassword.value;

    let authLoginValue = document.getElementById('loginField').value;
    let authPasswordValue = document.getElementById('passwordField').value;

    let authRequest = {
        login: authLoginValue,
        password: authPasswordValue,
    };
    let authResponse = await fetch ('http://127.0.0.1:8080/auth/', {

        method: 'POST',

        headers: {
            'Content-Type': 'application/json', // charset=utf-8
            'Accept': 'application/json',
            'Origin': 'http://127.0.0.1:8070/',
            'Access-Control-Request-Method': 'POST',
            'Access-Control-Request-Headers': 'Content-Type',
        },
        body: JSON.stringify(authRequest)
    });
    console.log(JSON.stringify(authRequest));
    // alert (JSON.stringify(properties));


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
    // } else {
    //     if (document.getElementById('createdLoginWarning')) {
    //     document.getElementById('createdLoginWarning').innerHTML="";
    //     document.getElementById('createdLoginWarning').innerHTML="<p> Incorrect login or password </p>";
    //     setTimeout(function () {
    //         document.getElementById('createdLoginWarning').style.display = 'none';
    //     },3000);
    // } else {
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

    let regRequest = {
        login: regLoginValue,
        password: regPasswordValue,
        customerName: regNameValue,
    };
    let regResponse = await fetch('http://127.0.0.1:8080/reg/', {

        method: 'POST',

        headers: {
            'Content-Type': 'application/json', // charset=utf-8
            'Accept': 'application/json',
            'Origin': 'http://127.0.0.1:8070/',
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
        alert('Account successfully registered');
    } else {
        if (regResult == 400|500) {
            alert("This login is already registered.");
        }
    }
}