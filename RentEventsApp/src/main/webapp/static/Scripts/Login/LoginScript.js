
//document.getElementById("loginButton").addEventListener("click", requestLogin);
//document.getElementById("summit").addEventListener("click",createUser);
document.getElementById("loginButton").addEventListener("click",requestLogin);

function requestLogin(){
	
	let url= "http://localhost:8080/RentEventsApp/login"
	let xhr = new XMLHttpRequest();
	xhr.open("POST",url);
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status === 200){
			
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token",auth);
			
			window.location.haref="http://localhost:8080/RentEventsApp/manager";
			console.log("function javascript");
		}
		
		if(this.readyState === 4){
			console.log(this);
		}
	}
	
	let user = document.getElementById("user").value;
	let pass = document.getElementById("pass").value;
	
	console.log(user);
	console.log(pass);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	
	let requestBody = `username=${user}&password=${pass}`;
	
	xhr.send(requestBody);
	
}

//console.log("Hello from login");
//let requestUrl = "http://localhost:8080/RentEventsApp/api/users";

//function sendAjaxGet(url, callback){
//	let xhr = new XMLHttpRequest();
//	
//	xhr.open("GET", url);
//	
//	xhr.onreadystatechange = function(){
//		if(xhr.readyState===4 && xhr.status===200){
//			callback(xhr.response);
//		}
//	}
//	
//	xhr.send();
//}
//
//function displayUsers(usersJSON){
//	let users = JSON.parse(usersJSON);
//	console.log(users);
//}

//sendAjaxGet(requestUrl,displayUsers);

//function displayUsers(usersJSON){
//	console.log(usersJSON);
//	let users = JSON.parse(usersJSON);
//	console.log(users);
//}
//
//function requestLogin(){
//	console.log("holi");
//	//window.location.href="http://localhost:8080/RentEventsApp/profile";
//	sendAjaxGet(requestUrl,displayUsers);
//	
//}
//
//function createUser(){
//	let userName = document.getElementById("user-name").value;
//	window.location.href="http://localhost:8080/RentEventsApp/login";
//	console.log(userName);
//}