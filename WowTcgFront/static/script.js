
filterButton.onclick = async function query() {

	// const resultTable = document.getElementById('resultTable');
	// const children = resultTable.children;
	// for (let i = 0; i < children.length; i++) {
	// 	const child = children[i];
	// 	document.getElementById('resultTable').removeChild(child);
	// }



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
	let response = await fetch ('http://127.0.0.1:8080/WowTCGWebserver/', {
		
		method: 'POST',
		
		headers: {
			'Content-Type': 'application/json', // charset=utf-8
			'Accept': 'application/json',
			'Origin': 'http://127.0.0.1:8070/',
			'Access-Control-Request-Method': 'POST',
			'Access-Control-Request-Headers': 'Content-Type',
		},
		body: JSON.stringify(properties)
	});
	console.log(JSON.stringify(properties));
	// alert (JSON.stringify(properties));

	
	let result = await response.json();
	console.log(result);

	document.getElementById('resultTable').innerHTML = "";



	let resultCard = result;
	// let resultCard = JSON.parse(result);
	console.log(resultCard);

	if (Object.keys(resultCard).length == 0) {

		document.getElementById('resultTable').innerHTML = '<p id = "notFoundText"> Cards with selected parameters not found<br/>Change the request parameters and try again </p>';
		// document.getElementById('resultTable').innerHTML = '<p id = "notFoundText2"> Change the request parameters and try again </p>';
	}




	// let card ='';
	for (let key in resultCard) {

		if (typeof resultCard[key].cardName !== 'undefined') {



			let resultTable = document.getElementById('resultTable');

			let createdOverallDiv = document.createElement('div');
			createdOverallDiv.className = "createdOverallDiv";

			resultTable.append(createdOverallDiv);



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
			if (typeof resultCard[key].allyClass !== 'undefined') {
				resultAllyClass = "<span class = 'parametersName'>Ally Class: </span>" + "<span class = 'parametersValue'>" + resultCard[key].allyClass + "</span><br/>";
			} else {
				resultAllyClass = ""
			}

			let resultRace;
			if (typeof resultCard[key].race !== 'undefined') {
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
			if (typeof resultCard[key].attackType !== 'undefined') {
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















	
