function addSubject(pathToGo, inputName){
    var table = document.getElementById('table-with-subjects');
    var addButton = document.getElementById('add-button');
    addButton.parentNode.removeChild(addButton);

    var tableRow = document.createElement('tr');
    var tableCol = document.createElement('td');
    var form = document.createElement('form');

    form.method='post';
    form.action=pathToGo;
    form.className="add-subject-form";
    var nameOfSubjectString = document.createElement('p');
    nameOfSubjectString.innerHTML="Name : ";
    nameOfSubjectString.className="col-lg-1";
    var nameInput = document.createElement('input');
    nameInput.type="text";
    nameInput.placeholder = "Name of subject";
    nameInput.name=inputName;
    nameInput.className="col-lg-5";
    var submitButton = document.createElement('button');
    submitButton.type="submit";
    submitButton.innerHTML="Accept";
    submitButton.className="col-lg-1 btn btn-default btn-lg";

    form.appendChild(nameOfSubjectString);
    form.appendChild(nameInput);
    form.appendChild(submitButton);

    tableCol.appendChild(form);
    tableRow.appendChild(tableCol);
    table.appendChild(tableRow);
}

function getTimeRemaining(endtime) {
    var t = Date.parse(endtime) - Date.parse(new Date());
    var seconds = Math.floor((t / 1000) % 60);
    var minutes = Math.floor((t / 1000 / 60) % 60);

    return {
        'total': t,
        'minutes': minutes,
        'seconds': seconds
    };
}

function initializeClock(endtime) {
    var minutesParagraph = document.getElementById('minutes');
    var secondsParagraph = document.getElementById('seconds');

    function updateClock() {
        var t = getTimeRemaining(endtime);

        minutesParagraph.innerHTML = ('0' + t.minutes).slice(-2);
        secondsParagraph.innerHTML = ('0' + t.seconds).slice(-2);

        if (t.total <= 0) {
            clearInterval(timeinterval);
        }
    }

    updateClock();
    var timeinterval = setInterval(updateClock, 1000);
}

function startTimer(minutes) {
    var deadline = new Date(Date.parse(new Date()) + minutes * 60 * 1000);
    initializeClock(deadline);
}
