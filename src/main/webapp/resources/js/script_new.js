function addSubject(pathToGo, inputName) {
    var table = document.getElementById('table-with-entities');
    var addButton = document.getElementById('add-button');
    addButton.parentNode.removeChild(addButton);

    var tableRow = document.createElement('tr');
    var tableCol = document.createElement('td');
    tableCol.colSpan = 2;
    var form = document.createElement('form');

    form.method = 'post';
    form.action = pathToGo;
    form.className = "add-entity-form";
    var nameOfSubjectString = document.createElement('p');
    nameOfSubjectString.innerHTML = "Name : ";
    nameOfSubjectString.className = "col-lg-1";
    var nameInput = document.createElement('input');
    nameInput.type = "text";
    nameInput.placeholder = "Enter name";
    nameInput.name = inputName;
    nameInput.className = "col-lg-5";

    var submitButton = document.createElement('button');
    submitButton.type = "submit";
    submitButton.innerHTML = "Accept";
    submitButton.className = "col-offset-lg-1 col-lg-1 btn btn-default btn-md";

    form.appendChild(nameOfSubjectString);
    form.appendChild(nameInput);
    form.appendChild(submitButton);

    tableCol.appendChild(form);
    tableRow.appendChild(tableCol);
    table.appendChild(tableRow);
}

function addTest(pathToGo, inputName, inputDurationTime) {
    var table = document.getElementById('table-with-entities');
    var addButton = document.getElementById('add-button');
    addButton.parentNode.removeChild(addButton);

    var tableRow = document.createElement('tr');
    var tableCol = document.createElement('td');
    tableCol.colSpan = 2;
    var form = document.createElement('form');

    form.method = 'post';
    form.action = pathToGo;
    form.className = "add-entity-form";

    var nameOfTestString = document.createElement('p');
    nameOfTestString.innerHTML = "Name : ";
    nameOfTestString.className = "col-lg-1";
    var nameInput = document.createElement('input');
    nameInput.type = "text";
    nameInput.placeholder = "Enter name";
    nameInput.name = inputName;
    nameInput.className = "col-lg-5";

    var durationTimeString = document.createElement('p');
    durationTimeString.innerHTML = "Duration time in minutes : ";
    durationTimeString.className = "col-lg-2";
    var durationTimeInput = document.createElement('input');
    durationTimeInput.type = "number";
    durationTimeInput.min = '0';
    durationTimeInput.placeholder = "Duration time";
    durationTimeInput.name = inputDurationTime;
    durationTimeInput.className = "col-lg-2";

    var submitButton = document.createElement('button');
    submitButton.type = "submit";
    submitButton.innerHTML = "Accept";
    submitButton.className = "col-lg-offset-1 col-lg-1 btn btn-default btn-md";

    form.appendChild(nameOfTestString);
    form.appendChild(nameInput);
    form.appendChild(durationTimeString);
    form.appendChild(durationTimeInput);
    form.appendChild(submitButton);

    tableCol.appendChild(form);
    tableRow.appendChild(tableCol);
    table.appendChild(tableRow);
}

function addAnswer(addButton, answerNameButton, answerNameText, taskId) {
    var taskTable = document.getElementById('task' + taskId);
    var tr = document.createElement('tr');
    var td = document.createElement('td');
    var label = document.createElement('label');

    var inputText = document.createElement('input');
    inputText.type = "text";
    inputText.name = answerNameText;

    var inputButton = document.createElement('input');
    inputButton.name = answerNameButton;

    var radioDiv = taskTable.getElementsByClassName("radio");
    var wrapperDiv = document.createElement('div');
    if (radioDiv.length == 0) { //checkbox
        inputButton.type = "checkbox";
        wrapperDiv.className = "checkbox";
    } else {    //radio
        inputButton.type = "radio";
        wrapperDiv.className = "radio";
    }

    label.appendChild(inputButton);
    label.appendChild(inputText);
    wrapperDiv.appendChild(label);
    td.appendChild(wrapperDiv);
    tr.appendChild(td);
    var tbody = taskTable.getElementsByTagName('tbody');
    for (var j = tbody.length; j > 0; j--) {
        var trBefore = addButton.parentNode.parentNode.parentNode;
        tbody[0].insertBefore(tr, trBefore);
    }
}

var msg;
function getMsg(addAnswerBundle,explanationTextBundle,
                isOneAnswerBundle,saveAnswerButtonBundle) {
    msg = {
        'addAnswer': addAnswerBundle,
        'explanationText':explanationTextBundle,
        'isOneAnswer':isOneAnswerBundle,
        'saveAnswerButton':saveAnswerButtonBundle
    }
}

function addTask(action, questionParam, answerButtonParam,
                 answerTextParam, explanationParam,
                 answerTypeParam, taskId, btnInsertBefore) {
    var form = document.createElement('form');
    form.action=action;
    form.method="post";
    var table = document.createElement('table');
    table.id="task" + taskId;
    table.className="table table-bordered table-shadow";
    var thead = document.createElement('thead');
    thead.className="thead-changed-style";
    var trHead = document.createElement('tr');
    var th = document.createElement('th');
    th.className="tree-header";
    th.colSpan="2";
    var taskQuestionInput = document.createElement('input');
    taskQuestionInput.placeholder = "Enter question here";
    taskQuestionInput.name = questionParam;
    th.appendChild(taskQuestionInput);
    trHead.appendChild(th);
    thead.appendChild(trHead);

    var tbody = document.createElement('tbody');
    var trAnswer = document.createElement('tr');
    var trAddAnswer = document.createElement('tr');
    var trExplanationAnswerType = document.createElement('tr');
    var trSave = document.createElement('td');

    var tdAnswer = document.createElement('td');
    var tdAddAnswer = document.createElement('td');
    var tdExplanationAnswerType = document.createElement('td');
    var tdSave = document.createElement('td');

    var divRadio = document.createElement('div');
    divRadio.className = "radio";
    var labelAnswer = document.createElement('label');
    var inputRadioButton = document.createElement('input');
    inputRadioButton.type = "radio";
    inputRadioButton.name = answerButtonParam;
    var inputAnswerText = document.createElement('input');
    inputAnswerText.name = answerTextParam;
    labelAnswer.appendChild(inputRadioButton);
    labelAnswer.appendChild(inputAnswerText);
    divRadio.appendChild(labelAnswer);
    tdAnswer.appendChild(divRadio);
    trAnswer.appendChild(tdAnswer);

    var addAnswerButton = document.createElement('button');
    addAnswerButton.type = "button";
    addAnswerButton.className = "btn btn-lg btn-primary";
    var glyphiconPlusSpan = document.createElement('span');
    glyphiconPlusSpan.className = "glyphicon glyphicon-plus";
    addAnswerButton.appendChild(glyphiconPlusSpan);
    addAnswerButton.innerHTML=msg.addAnswer;
    tdAddAnswer.appendChild(addAnswerButton);
    trAddAnswer.appendChild(tdAddAnswer);

    var formGroupExplanationDiv = document.createElement('div');
    formGroupExplanationDiv.className = "form-group";
    var explanationLabel = document.createElement('label');
    explanationLabel.innerHTML = msg.explanationText;
    var explanationTextArea = document.createElement('textarea');
    explanationTextArea.class = "form-control";
    explanationTextArea.rows = "6";
    explanationTextArea.style.resize = "vertical";
    explanationTextArea.name = explanationParam;
    formGroupExplanationDiv.appendChild(explanationLabel);
    formGroupExplanationDiv.appendChild(explanationTextArea);

    var cbAnswerTypeDiv = document.createElement('div');
    cbAnswerTypeDiv.className = "checkbox";
    var labelCheckbox = document.createElement('label');
    var answerTypeCheckboxInput = document.createElement('input');
    answerTypeCheckboxInput.type = "checkbox";
    answerTypeCheckboxInput.name = answerTypeParam;
    answerTypeCheckboxInput.checked=true;
    labelCheckbox.appendChild(answerTypeCheckboxInput);
    labelCheckbox.innerHTML=msg.isOneAnswer;
    cbAnswerTypeDiv.appendChild(labelCheckbox);
    tdExplanationAnswerType.appendChild(formGroupExplanationDiv);
    tdExplanationAnswerType.appendChild(cbAnswerTypeDiv);
    trExplanationAnswerType.appendChild(tdExplanationAnswerType);

    var submitButtonDiv = document.createElement('div');
    submitButtonDiv.className="text-right";
    var buttonSubmit = document.createElement('button');
    buttonSubmit.type="submit";
    buttonSubmit.className="btn btn-lg btn-primary";
    buttonSubmit.innerHTML=msg.saveAnswerButton;
    submitButtonDiv.appendChild(buttonSubmit);
    tdSave.appendChild(submitButtonDiv);
    trSave.appendChild(tdSave);

    tbody.appendChild(trAnswer);
    tbody.appendChild(trAddAnswer);
    tbody.appendChild(trExplanationAnswerType);
    tbody.appendChild(trSave);

    table.appendChild(thead);
    table.appendChild(tbody);
    form.appendChild(table);
    document.getElementsByClassName('data')[0].insertBefore(form,btnInsertBefore.parentNode);
    addAnswerButton.onclick = addAnswer(addAnswerButton, answerButtonParam, answerTextParam, taskId);
    answerTypeCheckboxInput.onchange = handleChange(answerTypeCheckboxInput, taskId);
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

function handleChange(checkbox, taskId) {
    var taskTable = document.getElementById('task' + taskId);
    if (checkbox.checked == true) {
        var rowsCheckbox = taskTable.getElementsByClassName("checkbox");
        for (var i = 0; i < rowsCheckbox.length - 1; i++) {
            rowsCheckbox[i].firstElementChild.firstElementChild.type = "radio";
        }
        for (i in rowsCheckbox)
            for (var i = rowsCheckbox.length - 1; i > 0; i--) {
                rowsCheckbox[0].className = "radio";
            }
    } else {
        var rowsRadio = taskTable.getElementsByClassName("radio");
        for (var j = 0; j < rowsRadio.length; j++) {
            rowsRadio[j].firstElementChild.firstElementChild.type = "checkbox";
        }
        for (var j = rowsRadio.length; j > 0; j--) {
            rowsRadio[0].className = "checkbox";
        }
    }
}