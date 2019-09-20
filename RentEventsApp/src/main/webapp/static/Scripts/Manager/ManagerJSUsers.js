let users;

let userNames=[];
console.log("Hello from Manager Users");


document.getElementById("users-tab").addEventListener("click",showUsers(displayUsers));





function showUsers(displayUsers){
	let requestUrl = "http://localhost:8080/RentEventsApp/api/users";
	console.log("holi");
	sendAjaxGet(requestUrl,displayUsers);
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

function displayUsers(usersJSON){
	users = JSON.parse(usersJSON);
	console.log(users);
	for(let user of users){
		userNames.push(user.userName);
		console.log(user);
	    this.addTableRowUsers(user.firstName, user.lastName, user.userName,user.numberRequestsDone, user.totalSpends);
	}
}





function addTableRowUsers(firstName,lastName,userName,nrd,tspends){
	
	let row = document.createElement("tr");
	
	
	row.innerHTML = `<td>${firstName}</td><td>${lastName}</td><td>${userName}</td><td>${nrd}</td><td>${tspends}</td>`;
	   
	
	document.getElementById("users-table").appendChild(row);
}


