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
	let tbody = document.getElementById("logListTableBody");
	tbody.textContent = '';

	if (logList && Array.isArray(logList)) {
		for (let log of logList) {
			let tr = document.createElement('tr');
			tbody.appendChild(tr);

			let td = document.createElement('td');
			td.textContent = log.id;
			tr.appendChild(td);

			td = document.createElement('td');
			td.textContent = log.name;
			tr.appendChild(td);

			tr.addEventListener('click', function(event) {
				let logId = log.id;
				getLogDetails(logId)
			});
		}
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
	
	let h3 = document.createElement('h3');
	h3.textContent = log.name;
	detailDiv.appendChild(h3);

}


}
