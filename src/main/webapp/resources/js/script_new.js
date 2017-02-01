function addSubject(pathToGo, inputName) {
    var table = document.getElementById('table-with-subjects');
    var addButton = document.getElementById('add-button');
    addButton.parentNode.removeChild(addButton);

    var tableRow = document.createElement('tr');
    var tableCol = document.createElement('td');
    var form = document.createElement('form');

    form.method = 'post';
    form.action = pathToGo;
    form.className = "add-subject-form";
    var nameOfSubjectString = document.createElement('p');
    nameOfSubjectString.innerHTML = "Name : ";
    nameOfSubjectString.className = "col-lg-1";
    var nameInput = document.createElement('input');
    nameInput.type = "text";
    nameInput.placeholder = "Name of subject";
    nameInput.name = inputName;
    nameInput.className = "col-lg-5";
    var submitButton = document.createElement('button');
    submitButton.type = "submit";
    submitButton.innerHTML = "Accept";
    submitButton.className = "col-lg-1 btn btn-default btn-lg";

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
    var secondsRemaining = document.getElementsByName('time_remaining');

    function updateClock() {
        var t = getTimeRemaining(endtime);

        minutesParagraph.innerHTML = ('0' + t.minutes).slice(-2);
        secondsParagraph.innerHTML = ('0' + t.seconds).slice(-2);
        for (var i = 0; i < secondsRemaining.length; i++) {
            secondsRemaining[i].value = t.minutes * 60 + t.seconds;
        }

        if (t.total <= 0) {
            clearInterval(timeInterval);
        }
    }

    updateClock();
    var timeInterval = setInterval(updateClock, 1000);
}

function startTimer(seconds) {
    var deadline = new Date(Date.parse(new Date()) + seconds * 1000);
    initializeClock(deadline);
}
/*function getTimeRemainingInSeconds() {
    var minutesParagraph = document.getElementById('minutes');
    var secondsParagraph = document.getElementById('seconds');

    var minutes = parseInt(minutesParagraph.innerHTML);
    var seconds = parseInt(secondsParagraph.innerHTML);

    var secondsRemaining = document.getElementById('seconds-remaining');
    secondsRemaining.value = minutes * 60 + seconds;

    return minutes * 60 + seconds;
}*/
function makeAllTasksFromListDisabled(tasks) {
    if (tasks != null) {
        tasks.forEach(function (item, i, arr) {
            var table = document.getElementById("task" + item);

            var tHead = table.getElementsByTagName('th');
            for (var someVariable = 0; someVariable < tHead.length; someVariable++) {
                tHead[someVariable].style.background = "#388e3c";
            }

            var elements = table.getElementsByTagName("label");
            for (var j = 0; j < elements.length; j++) {
                elements[j].firstChild.disabled = true;
            }

            var submitButton = table.getElementsByTagName('button');
            for (var someVariable1 = 0; someVariable1 < submitButton.length; someVariable1++) {
                submitButton[someVariable1].className = "btn btn-lg btn-default";
                submitButton[someVariable1].disabled = true;
            }
        });
    }
}
