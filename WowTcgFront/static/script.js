let cardId;
let savedCustomerId;
let savedName;
let customerIdFromCookies;
let customerNameFromCookies;

function getCookie(name) {
	let cookie = {};
	document.cookie.split(';').forEach(function(el) {
		let [k,v] = el.split('=');
		cookie[k.trim()] = v;
	})
	return cookie[name];
}

window.onload = function checkAuth (){
	customerIdFromCookies = getCookie('CustomerId');
	if (customerIdFromCookies > 0) {
		let mainMenu = document.getElementById('mainMenuList');
		let myCollectionLink = document.createElement('li');
		myCollectionLink.id = "myCollectionReference";
		myCollectionLink.innerHTML = "<a href = 'collection.html'> My_collection </a>";
		mainMenu.append(myCollectionLink);

		customerNameFromCookies = getCookie('CustomerName');
		let trimName = customerNameFromCookies.replace(/'/g, '');
		let loginDiv = document.getElementById('loginDiv');
		loginDiv.innerHTML = "<h2>Hello, " + trimName + "</h2>";

		let logout = document.createElement('div');
		logout.id = "logoutDiv";
		logout.innerHTML = "<p><input id='logoutButton' type='button' value='Log Out'></p>";
		loginDiv.append(logout);

		logout.addEventListener('click', async function logout(event) {
		document.cookie = "CustomerId=; Max-Age=0;";
		document.cookie = "CustomerName=; Max-Age=0;";
		location.reload();

		});
	};
};

loginReference.onclick = function () { window.location = 'login.html'; }

 targetQuery = async function query () {

	let setNameIndex = document.getElementById("SetName").options.selectedIndex;
	let setNameValue= document.getElementById("SetName").options[setNameIndex].value;

	let setClassIndex = document.getElementById("Class").options.selectedIndex;
	let setClassValue= document.getElementById("Class").options[setClassIndex].value;

	let typeIndex = document.getElementById("Type").options.selectedIndex;
	let typeValue= document.getElementById("Type").options[typeIndex].value;

	let costIndex = document.getElementById("Cost").options.selectedIndex;
	let costValue= document.getElementById("Cost").options[costIndex].value;

	let factionIndex = document.getElementById("Faction").options.selectedIndex;
	let factionValue= document.getElementById("Faction").options[factionIndex].value;

	let rarityIndex = document.getElementById("Rarity").options.selectedIndex;
	let rarityValue= document.getElementById("Rarity").options[rarityIndex].value;

	let properties = {
		setName: setNameValue,
		cardClass: setClassValue,
		type: typeValue,
		cost: costValue,
		faction: factionValue,
		rarity: rarityValue,
	};
	let response = await fetch ('http://89.179.245.199:8080/getcards/', {

		method: 'POST',

		headers: {
			'Content-Type': 'application/json', // charset=utf-8
			'Accept': 'application/json',
			'Origin': 'http://89.179.245.199:80/',
			'Access-Control-Request-Method': 'POST',
			'Access-Control-Request-Headers': 'Content-Type',
		},
		body: JSON.stringify(properties)
	});
	console.log(JSON.stringify(properties));

	let result = await response.json();
	console.log(result);


	document.getElementById('resultTable').innerHTML = "";
	// let resultTable = document.getElementById('resultTable');
	// let resultDiv = document.createElement("div");
	// resultDiv.id = "resultDiv";
	// resultTable.append(resultDiv);

	let resultCard = result;
	// let resultCard = JSON.parse(result);
	console.log(resultCard);

	if (Object.keys(resultCard).length == 0) {

		let resultTable = document.getElementById('resultTable');
		let notFound = document.createElement('div');
		notFound.id = "notFoundDiv";
		notFound.innerHTML = '<h1 class = "notFoundText"> Cards with selected parameters not found</h1><br/><h1 class = "notFoundText">Change the request parameters and try again </h1>';
		resultTable.append(notFound);
	}

	for (let key in resultCard) {

		if (typeof resultCard[key].cardName !== 'undefined') {

			cardId = resultCard[key].cardId;

			let resultTable = document.getElementById('resultTable');


			let createdOverallDiv = document.createElement('div');
			createdOverallDiv.className = "createdOverallDiv";

			resultTable.append(createdOverallDiv);

			if (customerIdFromCookies > 0) {
				let createdButtonsDiv = document.createElement('div');
				createdButtonsDiv.className = "createdButtonsDiv";

				createdOverallDiv.append(createdButtonsDiv);

				let createdButtonAddDiv = document.createElement('div');
				createdButtonAddDiv.className = "createdButtonAddDiv";
				createdButtonAddDiv.innerHTML = "<input class='resultButton' type='button' value='+' title = 'Add to your collection'>";

				createdButtonsDiv.append(createdButtonAddDiv);

				createdButtonAddDiv.addEventListener('click', async function addCard (event) {

					function getCookie(name) {
						let cookie = {};
						document.cookie.split(';').forEach(function(el) {
							let [k,v] = el.split('=');
							cookie[k.trim()] = v;
						})
						return cookie[name];
					}

					let customerIdFromCookies = getCookie('CustomerId');

					let cardAdd = {
						customerId: customerIdFromCookies,
						cardId: resultCard[key].cardId,
					};
					let addResponse = await fetch ('http://89.179.245.199:8080/addcard/', {

						method: 'POST',

						headers: {
							'Content-Type': 'application/json', // charset=utf-8
							'Accept': 'application/json',
							'Origin': 'http://89.179.245.199:80/',
							'Access-Control-Request-Method': 'POST',
							'Access-Control-Request-Headers': 'Content-Type',
						},
						body: JSON.stringify(cardAdd)
					});
					console.log(JSON.stringify(cardAdd));

					let addResult = await addResponse.status;

					if (addResult == 200) {
						document.getElementById('message').innerHTML = '"'+resultCard[key].cardName + '" has been added to your collection';
						getPopUp();
					} else {
						if (addResult != 200) {
							document.getElementById('message').innerHTML = '"'+resultCard[key].cardName + '" is already in your collection';
							getPopUp();
						}
					}
				});
			}

			let createdImageDiv = document.createElement('div');
			createdImageDiv.className = "createdImageDiv";
			createdImageDiv.innerHTML = "<a target='_blank' href = " + resultCard[key].imageFullUrl + " ><img class = 'modalImage' src= " + resultCard[key].imageUrl + " alt='image'></a>";

			createdOverallDiv.append(createdImageDiv);

			let createdNameDiv = document.createElement('div');
			createdNameDiv.className = "createdParameters";

			let resultCardName = "<span class = 'parametersName'>Name: </span>" + "<span class = 'parametersValue'>" + resultCard[key].cardName + "</span><br/>";
			let resultNumberInSet = "<span class = 'parametersName'>Number in Set: </span>" + "<span class = 'parametersValue'>" + resultCard[key].numberInSet + "</span><br/>";
			let resultType = "<span class = 'parametersName'>Type: </span>" + "<span class = 'parametersValue'>" + resultCard[key].type + "</span><br/>";

			let resultClass;
			if (resultCard[key].cardClass !== 'Any') {
				resultClass = "<span class = 'parametersName'>Class: </span>" + "<span class = 'parametersValue'>" + resultCard[key].cardClass + "</span><br/>";
			} else {
				resultClass = ""
			}

			let resultAllyClass;
			if (resultCard[key].allyClass !== null) {
			// if (typeof resultCard[key].allyClass !== 'undefined') {
				resultAllyClass = "<span class = 'parametersName'>Ally Class: </span>" + "<span class = 'parametersValue'>" + resultCard[key].allyClass + "</span><br/>";
			} else {
				resultAllyClass = ""
			}

			let resultRace;
			if (resultCard[key].race !== null) {
			// if (typeof resultCard[key].race !== 'undefined') {
				resultRace = "<span class = 'parametersName'>Race: </span>" + "<span class = 'parametersValue'>" + resultCard[key].race + "</span><br/>";
			} else {
				resultRace = ""
			}

			let resultFaction;
			if (resultCard[key].faction !== 'Neutral') {
				resultFaction = "<span class = 'parametersName'>Faction: </span>" + "<span class = 'parametersValue'>" + resultCard[key].faction + "</span><br/>";
			} else {
				resultFaction = ""
			}

			let resultCost;
			if (resultCard[key].cost >= 0) {
				resultCost = "<span class = 'parametersName'>Cost: </span>" + "<span class = 'parametersValue'>" + resultCard[key].cost + "</span><br/>";
			} else {
				resultCost = ""
			}

			let resultAttack;
			if (resultCard[key].attack >= 0) {
				resultAttack = "<span class = 'parametersName'>Attack: </span>" + "<span class = 'parametersValue'>" + resultCard[key].attack + "</span><br/>";
			} else {
				resultAttack = ""
			}

			let resultAttackType;
			if (resultCard[key].attackType !== null) {
			// if (typeof resultCard[key].attackType !== 'null') {
				resultAttackType = "<span class = 'parametersName'>Attack Type: </span>" + "<span class = 'parametersValue'>" + resultCard[key].attackType + "</span><br/>";
			} else {
				resultAttackType = ""
			}

			let resultStrikeCost;
			if (resultCard[key].strikeCost >= 0) {
				resultStrikeCost = "<span class = 'parametersName'>Strike Cost: </span>" + "<span class = 'parametersValue'>" + resultCard[key].strikeCost + "</span><br/>";
			} else {
				resultStrikeCost = ""
			}

			let resultHealth;
			if (resultCard[key].health >= 0) {
				resultHealth = "<span class = 'parametersName'>Health: </span>" + "<span class = 'parametersValue'>" + resultCard[key].health + "</span><br/>";
			} else {
				resultHealth = ""
			}

			let resultDefence;
			if (resultCard[key].defence >= 0) {
				resultDefence = "<span class = 'parametersName'>Defence: </span>" + "<span class = 'parametersValue'>" + resultCard[key].defence + "</span><br/>";
			} else {
				resultDefence = ""
			}

			let resultRarity = "<span class = 'parametersName'>Rarity: </span>" + "<span class = 'parametersValue'>" + resultCard[key].rarity + "</span><br/>";
			let resultSetName = "<span class = 'parametersName'>Set: </span>" + "<span class = 'parametersValue'>" + resultCard[key].setName + "</span><br/>";

			let resultResponse = resultCardName + resultSetName + resultNumberInSet + resultRarity + resultType + resultFaction +  resultClass + resultRace + resultAllyClass + resultCost + resultHealth + resultAttack + resultAttackType + resultStrikeCost + resultDefence;

			createdNameDiv.innerHTML = resultResponse;
			createdOverallDiv.append(createdNameDiv);

		};
	};
};

filterButton.onclick = targetQuery;

(function() {
	'use strict';

	function trackScroll() {
		let scrolled = window.pageYOffset;
		let coords = document.documentElement.clientHeight;

		if (scrolled > coords) {
			goTopBtn.classList.add('back_to_top-show');
		}
		if (scrolled < coords) {
			goTopBtn.classList.remove('back_to_top-show');
		}
	}

	function backToTop() {
		if (window.pageYOffset > 0) {
			window.scrollBy(0, -320);
			setTimeout(backToTop, 0);
		}
	}

	let goTopBtn = document.querySelector('.back_to_top');

	window.addEventListener('scroll', trackScroll);
	goTopBtn.addEventListener('click', backToTop);
})();

function getPopUp() {
	jQuery('#success-message').show();
	setTimeout(function() { $("#success-message").fadeOut('slow'); }, 1000);
}

















	
