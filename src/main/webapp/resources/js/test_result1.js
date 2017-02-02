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
    var glyphicon = document.createElement('span');
    if(isCorrectTutorAnswer){
        tableRow.className='correct-answer';
        glyphicon.className='glyphicon glyphicon-ok text-right';
        tableRow.appendChild(glyphicon);
        return;
    }
    if(isIncorrectStudentAnswer){
        tableRow.className='false-answer';
        glyphicon.className='glyphicon glyphicon-remove text-right';
        tableRow.appendChild(glyphicon);
    }
}