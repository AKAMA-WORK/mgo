const searchParams = new URLSearchParams(window.location.search)
const idDepartureFromURL = searchParams.get('departure_id')

const getDeparturePrice = () => {
    return idDepartureFromURL ? Number($('#depart-price').val()) : Number(($("#depart-dispo option:selected").text().split("-")[2]).trim())
}

const getDepartureId = () => {

    return idDepartureFromURL ? idDepartureFromURL : $("#depart-dispo").val()
}

const getDepartureInfo = () => {
    return idDepartureFromURL ? $('#depart-info').val() : $("#depart-dispo option:selected").text()
}

const getDepartureFromCity = () => {
    return idDepartureFromURL ? $('#depart-ville-depart').val() : $("#ville_depart option:selected").text()
}

const getDepartureToCity = () => {
    return idDepartureFromURL ? $('#depart-ville-destination').val() :  $("#ville_destination option:selected").text()
}

$(".tab-wizard").steps({
    headerTag: "h5",
    bodyTag: "section",
    transitionEffect: "fade",
    titleTemplate: '<span class="step">#index#</span> #title#',
    labels: {
        finish: "Valider",
        next: "Suivant",
        previous: "Précédent",
    },
    onStepChanged: function (event, currentIndex, priorIndex) {
        $('.steps .current').prevAll().addClass('disabled');
        // We skip the departure step when id departure from url is not undefined
        if ((idDepartureFromURL && currentIndex === 2) || currentIndex == 3) {
            $("#destination-resume")
                .html(getDepartureFromCity() + " -> " + getDepartureToCity()
                    + " </br> " + getDepartureInfo()
                );

            $("#client-resume").text($("#civilite option:selected").text() + " " + $("#nom").val() + " - " +
                $("#phone").val()
            );

            $("#place-resume").text($("#places").val());

            $("#amount-resume").text($("#montant").val());

        }
    },
    onFinished: function (event, currentIndex) {
        //$('#success-modal').modal('show');

        const departureId = getDepartureId()
        if (!departureId) {
            swal(
                {
                    /*title: 'Bien!',*/
                    text: "Dans Etape 1 : Veuillez choisir la destination",
                    type: 'error',
                    confirmButtonClass: 'btn btn-success'
                }
            );
            return;
        }

        if ($("#places").val() == "") {
            swal(
                {
                    /*title: 'Bien!',*/
                    text: "Dans Etape 2 : Veuillez choisir la place",
                    type: 'error',
                    confirmButtonClass: 'btn btn-success'
                }
            );
            return;
        }

        if ($("#phone").val() == "") {
            swal(
                {
                    /*title: 'Bien!',*/
                    text: "Dans Etape 3 : Veuillez renseigner le numero téléphone du client",
                    type: 'error',
                    confirmButtonClass: 'btn btn-success',
                }
            );
            return;
        }

        if ($("#nom").val() == "") {
            swal(
                {
                    /*title: 'Bien!',*/
                    text: "Dans Etape 3 : Veuillez renseigner le nom du client",
                    type: 'error',
                    showCancelButton: false,
                    confirmButtonClass: 'btn btn-success'
                }
            );
            return;
        }
        $("#form-reservation").submit();
    }
});

$("#afficher-depart").click(function () {
    let date = $("#date_depart").val().trim();
    let cityDepart = $("#ville_depart").val().trim();
    let cityDestination = $("#ville_destination").val().trim();
    let departSelect = document.getElementById("depart-dispo");
    departSelect.innerHTML = "<option></option>";
    if (date.length > 0) {
        let r = new XMLHttpRequest();
        let params = 'villeDepart=' + cityDepart + '&villeDestination=' + cityDestination + '&date=' + date;
        r.open("POST", "/api/depart", true);
        r.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        r.responseType = 'json';
        r.onreadystatechange = function () {
            if (r.readyState != 4 || r.status != 200) {
                return
            };
            let json = r.response;
            if (json == null) {
                window.location.reload();
                return;
            }

            let options = "<option></option>";
            for (var i = 0; i < json.length; i++) {
                let value = json[i].id;
                let text = json[i].info;
                let option = "<option value='" + value + "'>" + text + "</option>"
                options += option;
            }
            departSelect.innerHTML = options;

            if (json.length == 0) {
                swal(
                    {
                        /*title: 'Bien!',*/
                        text: "Aucun départ n'est trouvé à cette date",
                        type: 'error',
                        showCancelButton: false,
                        confirmButtonClass: 'btn btn-success'
                    }
                );
            } else {
                swal(
                    {
                        /*title: 'Bien!',*/
                        text: json.length + " départ(s) trouvés",
                        type: 'success',
                        confirmButtonClass: 'btn btn-success'
                    }
                );
            }
        };
        r.send(params);
    }
});


const loadPlaces = (value) => {
    if (value == "")
        return;

    let tableBody = document.getElementById("table-place");
    tableBody.innerHTML = "";

    let r = new XMLHttpRequest();
    r.open("GET", "/api/depart/place?depart=" + value, true);
    r.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    r.responseType = 'json';
    r.onreadystatechange = function () {
        if (r.readyState != 4 || r.status != 200) return;
        let json = r.response;

        if (json == null) {
            window.location.reload();
            return;
        }

        let line = json[0].line;
        let column = json[0].column;

        for (let i = 1; i <= line; i++) {
            let newTR = document.createElement('tr');
            for (let j = 1; j <= column; j++) {
                let currentPlace = json.find(place => place.x == i && place.y == j);
                let newTD = document.createElement('td');
                newTD.classList.add("place");

                if (currentPlace.place === -2) {
                    newTD.classList.add("driver");
                    newTD.title = "Chauffeur";
                    newTD.innerHTML = '<i class="icon-copy dw dw-user-1"></i>';
                    newTR.appendChild(newTD);
                }

                if (currentPlace.place === -1) {
                    newTD.classList.add("no-chair");
                    newTD.appendChild(document.createTextNode(" "));
                    newTR.appendChild(newTD);
                }

                if (currentPlace.occuped) {
                    newTD.classList.add("occuped")
                    newTD.appendChild(document.createTextNode(currentPlace.place));
                    newTR.appendChild(newTD)
                }
                else if (currentPlace.place > 0) {
                    newTD.appendChild(document.createTextNode(currentPlace.place));
                    newTR.appendChild(newTD);
                    newTD.classList.add("free");
                    newTD.onclick = function (e) { reservePlace(e, value, currentPlace.place); }
                }
            }
            tableBody.appendChild(newTR);
        }
    };
    r.send();


}


$("#depart-dispo").change(function (e) {
    loadPlaces(e.target.value)
});

function reservePlace(e, depart, placeNumber) {
    let currentTD = e.target;
    let places = $("#places").val().split("-");
    if (places.includes(placeNumber + "")) {
        places = places.filter(function (ele) {
            return ele != placeNumber;
        });
        currentTD.classList.remove("selected");
        currentTD.classList.add("free");
    }
    else {
        places.push(placeNumber);
        currentTD.classList.remove("free");
        currentTD.classList.add("selected");
    }

    places = places.filter(function (ele) {
        return ele != '';
    });

    if (places.length == 1)
        $("#places").val(places[0]);
    else
        $("#places").val(places.join("-"));

    let nbpl = $("#places").val().split("-").length;
    let sarandalana = getDeparturePrice()
    $("#montant").val(nbpl * sarandalana);
}

$("#phone").change(function (e) {
    let r = new XMLHttpRequest();
    r.open("GET", "/api/client/find?phone=" + e.target.value.replace(' ', ''), true);
    r.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    r.responseType = 'json';
    r.onreadystatechange = function () {
        if (r.readyState != 4 || r.status != 200) return;
        let json = r.response;

        if (json == null) {
            window.location.reload();
            return;
        }
        if (json.found) {
            $("#civilite").val(json.civilite);
            $("#nom").val(json.nom);
        }
    };
    r.send();
});

$(function () {
    if (idDepartureFromURL) {
        loadPlaces(idDepartureFromURL)
    }

})