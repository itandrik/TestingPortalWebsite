function makeTableHeaderColor(isCorrectAnswers, taskId){
    var table = document.getElementById("task" + taskId);

    var tHead = table.getElementsByTagName('th');
    if(isCorrectAnswers) {
        for (var someVariable = 0; someVariable < tHead.length; someVariable++) {
            tHead[someVariable].style.background = "#388e3c";
        }
    }
}