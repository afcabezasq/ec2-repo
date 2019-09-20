

showRequests(displayRequests);


function showRequests(displayRequests){
	let requestUrl = "http://localhost:8080/RentEventsApp/api/requests";
	
	sendAjaxGet(requestUrl,displayRequests);
}

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState===4 && xhr.status===200){
			callback(xhr.response);
		}
	}
	
	xhr.send();
}
var uname;
var res;
let da;
function displayRequests(requestsJSON){
	
	
	userRequests = JSON.parse(requestsJSON);
	console.log(userRequests);
	
	for(let rst of userRequests){
		
		var stringRst = JSON.stringify(rst);
		res = stringRst.split(/,|"|:"|:/);
		let uNameId = parseInt(res[7]);
		let dt = parseInt(res[15],10);
		da =new Date(dt);
		
		
		console.log(da);
		
		addTableRowRequests(userNames[uNameId], rst.peopleExpected, rst.locationRequested, da);

	}
	
}

		 
function addTableRowRequests( userName,  peopleExpected,locationName,date){
	
	let row = document.createElement("tr");
	row.innerHTML = `<td>${userName}</td><td>${peopleExpected}</td><td>${locationName}</td><td type="date">${date}</td>`;
	document.getElementById("historicRequests-table").appendChild(row);
}




