console.log('script.js loaded');

window.addEventListener('load', function(e) {
	console.log('Window loaded DOM created');
	init();
});

function init() {
	console.log('In init');
	//load all events
	// add event listeners for existing buttons / forms etc
	getAllLogs();
}

function getAllLogs() {
	// XHR to GET list endpoint of API, call displayAllEvents to show on page
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/logs');

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let logList = JSON.parse(xhr.responseText)
				console.log(logList)
				displayAllLogs(logList)

				//	displayAllLogs(JSON.parse(xhr.responseText));
			}
			else {

			}
		}
	};

	xhr.send();
}

function displayAllLogs(logList) {
	//iterate, add rows to table
	let tbody = document.getElementById('logTable');
	tbody.textContent = '';
	
	console.log('in displayAllLogs');

	if (logList && Array.isArray(logList)) {
		console.log("array not empty");
		
		logList.forEach( log => {
			let row = tbody.insertRow();
			
			let id = row.insertCell(0);
			id.innerHTML = log.id;
			id.label = "weee";
			
			let category = row.insertCell(1);
			category.innerHTML = log.category;
			
			let details = row.insertCell(2);
			details.innerHTML = log.details;
			
			let duration = row.insertCell(3);
			duration.innerHTML = log.duration;
			
			let activity = row.insertCell(4);
			activity.innerHTML = log.activity;
		})
}

function getLogDetails(logId) {
	//XHR for single event
	console.log('Getting Log details for log ID ' + logId)
	
	let xhr = new XMLHttpRequest();
	xhr.open('GET', `api/logs/${logId}`);
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4) {
			if(xhr.status === 200) {
				let stand = JSON.parse(xhr.responseText);
				displayLogDetails(log);
			}
			else {
				//show "Not Found"
			}
		}
	};
	xhr.send();

}

function displayLogDetails(log) {
	// DOM  display in details div; description, image, logo, address, etc
	//update/delete buttons with click events to pass that stand ID to new 
	// update/delete function
	
	let detailDiv = document.getElementById("logDetailsDiv");
	detailDiv.textContent = '';
	
	let h2 = document.createElement('h2');
	h2.textContent = log.name;
	detailDiv.appendChild(h2);
	
	let deleteLogDiv = document.createElement('deleteLogDiv');
	deleteLogDiv.name = 'deleteLogForm';
	detailDiv.appendChild(deleteLogDiv);
	
	let logIdInput = document.createElement('input');
	logIdInput.type = 'hidden';
	logIdInput.name = 'logId';
	logIdInput.value = log.id;
	deleteLogDiv.appendChild(logIdInput);
	
	let delButton = document.createElement('button');
	delButton.textContent = 'Delete this Log';
	deleteLogDiv.appendChild(delButton);
	
	delButton.classList.add('btn');
	delButton.classList.add('btn-danger');
	
	delButton.addEventListener('click', function(event) {
		event.preventDefault();
		//console.log(document.delete)
		let logId = document.deleteLog.logId.value;
		console.log('Delete Log ' + logId);
		deleteLog(logId);
	});
	
	let updateLogDiv = document.getElementById('updateLogDiv');
	let updateForm = document.createElement('form');
	
	updateForm.name = 'updateLogForm';
	logDetails.appendChild(updateForm);
	
	logIdInput = document.createElement('input');
	logIdInput.type = 'hidden';
	logIdInput.name = 'logId';
	logIdInput.value = log.id;
	
	updateForm.appendChild(logIdInput);
	
	let updatedButton = document.createElement('button');
	updatedButton.textContent = 'Update this Log';
	updateForm.appendChild(updatedButton);
	
	updatedButton.classList.add('btn');
	updatedButton.classList.add('btn-primanry')
	
	updatedButton.addEventListener('click', function(event) {
		event.preventDefault();
		
		let logId = document.updateLogForm.logId.value;
		console.log('updated log ' + logId);
	});
	
	let h3 = document.createElement('h3');
	h3.textContent = "Category: " + log.category;
	logDetails.appendChild(h3);
	
	let h4 = document.createElement('h4');
	h4.textContent = "Activities: " + log.activity;
	logDetails.appendChild(h4);
	
	let paragraph = document.createElement('p');
	paragraph.textContent = log.details;
	logDetails.appendChild(paragraph);
	
	let picture = document.createElement('img');
	picture.src = log.imgUrl;
	picture.classList.add('detailImage');
	logDetails.appendChild(picture);

}

function deleteLog(logId) {
	let xhr = new XMLHttpRequest();
	
	xhr.open('DELETE', "api/logs/" + logId);
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4) {
			if (xhr.status === 204) {
				console.log("Log Deleted. " + xhr.responseText);
				getAllLogs();
			}
			else {
				console.error("Error: " + xhr.status);
			}
		}
	};
	xhr.send();
}


}
