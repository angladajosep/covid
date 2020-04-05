var caseChart;
var deathChart;

function renderCaseChart(datasets, labels) {
    var ctx = document.getElementById("myChart").getContext('2d');
    caseChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: datasets
        },
        options: {
            /*title: {
                display: true,
                position: top,
                text: 'Absolut Case Numbers'
            },*/
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            legend: {
                display: true,
                position: 'right'
            }
        }
    });
}
function renderDeathChart(datasets, labels) {
    var ctx = document.getElementById("deathChart").getContext('2d');
    deathChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: datasets
        },
        options: {
            /*title: {
                display: true,
                position: top,
                text: 'Absolut Death Numbers'
            },*/
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            legend: {
                display: true,
                position: 'right'
            }
        }
    });
}


function getChartData() {
    $("#loadingMessage").html('<img src="giphy.gif" alt="" srcset="">');
    console.log('Selected values: ' +$('#states').multipleSelect('getSelects'));
    console.log('Selected texts: ' + $('#states').multipleSelect('getSelects', 'text'));
    $.ajax({
        url: "http://localhost:8080/api/covid/data/case",
        type: "get",
        data: {states: $('#states').multipleSelect('getSelects')},
        traditional : true,
        success: function (result) {
            $("#loadingMessage").html("");
            var datasets = result.datasets;
            var labels = result.labels;
            renderCaseChart(datasets, labels);
        },
        error: function (err) {
            $("#loadingMessage").html("Error");
        }
    });

    $.ajax({
        url: "http://localhost:8080/api/covid/data/death",
        type: "get",
        data: {states: $('#states').multipleSelect('getSelects')},
        traditional : true,
        success: function (result) {
            $("#loadingMessage").html("");
            var datasets = result.datasets;
            var labels = result.labels;
            renderDeathChart(datasets, labels);
        },
        error: function (err) {
            $("#loadingMessage").html("Error");
        }
    });


}

$("#renderBtn").click(
    function () {
        if (caseChart != undefined) {
            caseChart.destroy();
        }
        if (deathChart != undefined) {
            deathChart.destroy();
        }
        getChartData();
    }
);