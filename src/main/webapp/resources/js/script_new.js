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

    function updateClock() {
        var t = getTimeRemaining(endtime);

        minutesParagraph.innerHTML = ('0' + t.minutes).slice(-2);
        secondsParagraph.innerHTML = ('0' + t.seconds).slice(-2);

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

/*
// define functions
var setScroll,
    myScroll;


// if query string in URL contains scroll=nnn, then scroll position will be restored
setScroll = function () {
    // get query string parameter with "?"
    var search = window.location.search,
        matches;
    // if query string exists
    if (search) {
        // find scroll parameter in query string
        matches = /scroll=(\d+)/.exec(search);
        // jump to the scroll position if scroll parameter exists
        if (matches) {
            window.scrollTo(0, matches[1]);
        }
    }
};


// function appends scroll parameter to the URL or returns scroll value
myScroll = function (url) {
    var scroll, q;
    // Netscape compliant
    if (typeof(window.pageYOffset) === 'number') {
        scroll = window.pageYOffset;
    }
    // DOM compliant
    else if (document.body && document.body.scrollTop) {
        scroll = document.body.scrollTop;
    }
    // IE6 standards compliant mode
    else if (document.documentElement && document.documentElement.scrollTop) {
        scroll = document.documentElement.scrollTop;
    }
    // needed for IE6 (when vertical scroll bar is on the top)
    else {
        scroll = 0;
    }
    // if input parameter does not exist then return scroll value
    if (url === undefined) {
        return scroll;
    }
    // else append scroll parameter to the URL
    else {
        // set "?" or "&" before scroll parameter
        q = url.indexOf('?') === -1 ? '?' : '&';
        // refresh page with scroll position parameter
        window.location.href = url + q + 'scroll=' + scroll;
    }
};


// add onload event listener
if (window.addEventListener) {
    window.addEventListener('load', setScroll, false);
}
else if (window.attachEvent) {
    window.attachEvent('onload', setScroll);
}*/
