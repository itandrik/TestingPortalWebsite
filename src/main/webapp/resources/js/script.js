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