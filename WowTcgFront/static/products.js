//
// filterButton.onclick = async function query() {
//
// 	// const resultTable = document.getElementById('resultTable');
// 	// const children = resultTable.children;
// 	// for (let i = 0; i < children.length; i++) {
// 	// 	const child = children[i];
// 	// 	document.getElementById('resultTable').removeChild(child);
// 	// }
//
//
//
// 	let setNameIndex = document.getElementById("SetName").options.selectedIndex;
// 	let setNameValue= document.getElementById("SetName").options[setNameIndex].value;
//
// 	let rarityIndex = document.getElementById("Rarity").options.selectedIndex;
// 	let rarityValue= document.getElementById("Rarity").options[rarityIndex].value;
//
// 	let typeIndex = document.getElementById("Type").options.selectedIndex;
// 	let typeValue= document.getElementById("Type").options[typeIndex].value;
//
//
//
//
// 	let properties = {
// 		setName: setNameValue,
// 		rarity: rarityValue,
// 		type: typeValue,
// 	};
// 	let response = await fetch ('http://127.0.0.1:8080/WowTCGWebserver/', {
//
// 		method: 'POST',
//
// 		headers: {
// 			'Content-Type': 'application/json', // charset=utf-8
// 			'Accept': 'application/json',
// 			'Origin': 'http://127.0.0.1:8070/',
// 			'Access-Control-Request-Method': 'POST',
// 			'Access-Control-Request-Headers': 'Content-Type',
// 		},
// 		body: JSON.stringify(properties)
// 	});
// 	console.log(JSON.stringify(properties));
// 	// alert (JSON.stringify(properties));
//
//
// 	let result = await response.json();
// 	console.log(result);
//
//
// 	let resultCard = JSON.parse(result);
// 	console.log(resultCard);
//
// 	document.getElementById('resultTable').innerHTML = "";
//
//
//
// 	// let card ='';
// 	for (let key in resultCard) {
// 		let resultTable = document.getElementById('resultTable');
// 		let createdOverallDiv = document.createElement('div');
// 		createdOverallDiv.className = "createdOverallDiv";
// 		resultTable.append(createdOverallDiv);
// 		let createdImageDiv = document.createElement('div');
// 		createdImageDiv.className = "createdImageDiv";
// 		createdImageDiv.innerHTML = "<img src='" + resultCard[key].imageUrl +"' alt='image'>";
// 		createdOverallDiv.append(createdImageDiv);
// 		let createdNameDiv = document.createElement('div');
// 		createdNameDiv.className = "createdNameDiv";
// 		createdNameDiv.innerHTML = "Name:    " + resultCard[key].cardName;
// 		createdOverallDiv.append(createdNameDiv);
//
// 		console.log(resultCard[key].imageUrl);
//
//
//
// 		// card += resultCard[key].cardName;
// 	}
// 	// document.getElementById('card').innerHTML = card;
//
// };















	
