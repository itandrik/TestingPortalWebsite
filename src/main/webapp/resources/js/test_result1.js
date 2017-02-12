function makeTableHeaderColor(isCorrectAnswers, taskId) {
    var table = document.getElementById("task" + taskId);

    var tHead = table.getElementsByTagName('th');
    var glyphicon  = document.getElementById('glyphicon-result' + taskId);
    for (var someVariable = 0; someVariable < tHead.length; someVariable++) {
        if (isCorrectAnswers) {
            tHead[someVariable].style.background = "#388e3c";
            glyphicon.className = 'glyphicon glyphicon-ok';
        } else {
            tHead[someVariable].style.background = "#FFAAAA";
            glyphicon.className = 'glyphicon glyphicon-remove';
        }
    }
}

function performColorForAnswer(answerId, isIncorrectStudentAnswer, isCorrectTutorAnswer){
    var tableRow = document.getElementById('td' + answerId);
    tableRow.firstElementChild.style.display='inline';
    var glyphicon = document.createElement('span');
    if(isCorrectTutorAnswer){
        tableRow.className='correct-answer';
        glyphicon.className='glyphicon glyphicon-ok text-right';
        tableRow.firstElementChild.appendChild(glyphicon);
        return;
    }
    if(isIncorrectStudentAnswer){
        tableRow.className='false-answer';
        glyphicon.className='glyphicon glyphicon-remove text-right';
        tableRow.firstElementChild.appendChild(glyphicon);
    }
}

function performColorForHistory(grade){
    var historyContainer = document.getElementsByClassName('history-container');
    for(var i = 0; i < historyContainer.length; i++){
        if(grade >= 90) {
            historyContainer[i].style.background="rgba(8,136,8,.2)";
        } else if(grade >= 60){
            historyContainer[i].style.background="rgba(255,255,0,.2)";
        } else if(grade >= 0){
            historyContainer[i].style.background="rgba(255,0,0,.2)";
        }
    }
}