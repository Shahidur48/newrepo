var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#userinfo").html("");
}

function connect() {
    var socket = new SockJS('/websocket-example');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/user", {}, JSON.stringify({'name': $("#name").val()}));
    document.getElementById("tableRow").style.visibility="visible";
}

function sendUpdatedTeam() {
    const statusField = document.getElementById("status").innerText;
    const teamName = document.getElementById("teamName").innerText;

    var teamObject = {
        "teamName" : teamName,
        "teamState" : statusField
    }

    stompClient.send("/app/updateTeam", {}, JSON.stringify(teamObject));
    document.getElementById("userinfo").deleteRow(0);
}


function showGreeting(message) {
    // var msg = message.content+':'+message.content2;
    var teamName = message.content;
    var teamStatus = message.content2;
    var teamRank = message.content3;


    $("#userinfo").append("<tr>" +
        "<td id=\"teamName\">" + teamName + "</td>" +
        "<td id=\"status\">" + teamStatus + "</td>" +
        "<td>" + teamRank + "</td>" +
        "<td><button id=\"edit-button\" type=\"submit\">Edit</button></td>"+
        "<td><button style=\"display:none;\" id=\"submit-btn\" type=\"submit\">Submit</button></td>"+
        "</tr>");

    const edit_button = document.getElementById("edit-button");
    const statusField = document.getElementById("status");
    const submitBtn = document.getElementById("submit-btn");

    if (statusField.innerText == "Accepted"){
        console.log(statusField.innerText)
        statusField.style.backgroundColor = "green";
    }
    else if(statusField.innerText == "Pending"){
        statusField.style.backgroundColor = "orange";
    }
    else {
        statusField.style.backgroundColor = "gray";
    }

    edit_button.addEventListener("click", function() {
        console.log("Hello");
        statusField.contentEditable="true";
        submitBtn.style.display = "inline";
        submitBtn.addEventListener("click",function (){

           statusField.contentEditable="false";
            sendUpdatedTeam();
        });
    } );
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});