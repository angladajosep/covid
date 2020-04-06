$(function () {
        var chartjs_cases;
        var chartjs_deaths;
        var chartjs_uci;
        var chartjs_hospital;
        var chartjs_recovered;

        $('#states').select2({
            ajax: {
                url: 'http://localhost:8080/api/covid/states',
                processResults: function (data) {
                    // Transforms the top-level key of the response object from 'items' to 'results'
                    return {
                        results: data
                    };
                }
            }
        });
        var test = document.getElementById('renderBtn');
        test.onclick = function () {

            if (chartjs_cases != undefined) {
                chartjs_cases.destroy();
            }
            if (chartjs_deaths != undefined) {
                chartjs_deaths.destroy();
            }
            if (chartjs_uci != undefined) {
                chartjs_uci.destroy();
            }
            if (chartjs_hospital != undefined) {
                chartjs_hospital.destroy();
            }
            if (chartjs_recovered != undefined) {
                chartjs_recovered.destroy();
            }

            getChartData();

            function renderCaseChart(datasets, labels) {
                var ctx = document.getElementById("chartjs_cases").getContext('2d');
                chartjs_cases = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: datasets
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }
            function renderDeathChart(datasets, labels) {
                var ctx = document.getElementById("chartjs_deaths").getContext('2d');
                chartjs_deaths = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: datasets
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }
            function renderUciChart(datasets, labels) {
                var ctx = document.getElementById("chartjs_uci").getContext('2d');
                chartjs_uci = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: datasets
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }
            function renderHospitalChart(datasets, labels) {
                var ctx = document.getElementById("chartjs_hospital").getContext('2d');
                chartjs_hospital = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: datasets
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }
            function renderRecoveredChart(datasets, labels) {
                var ctx = document.getElementById("chartjs_recovered").getContext('2d');
                chartjs_recovered = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: datasets
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            }


            function getChartData() {
                $.ajax({
                    url: "http://localhost:8080/api/covid/data/case",
                    type: "get",
                    data: {states: $('#states').val()},
                    traditional: true,
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
                    data: {states: $('#states').val()},
                    traditional: true,
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
                $.ajax({
                    url: "http://localhost:8080/api/covid/data/uci",
                    type: "get",
                    data: {states: $('#states').val()},
                    traditional: true,
                    success: function (result) {
                        $("#loadingMessage").html("");
                        var datasets = result.datasets;
                        var labels = result.labels;
                        renderUciChart(datasets, labels);
                    },
                    error: function (err) {
                        $("#loadingMessage").html("Error");
                    }
                });
                $.ajax({
                    url: "http://localhost:8080/api/covid/data/hostpital",
                    type: "get",
                    data: {states: $('#states').val()},
                    traditional: true,
                    success: function (result) {
                        $("#loadingMessage").html("");
                        var datasets = result.datasets;
                        var labels = result.labels;
                        renderHospitalChart(datasets, labels);
                    },
                    error: function (err) {
                        $("#loadingMessage").html("Error");
                    }
                });
                $.ajax({
                    url: "http://localhost:8080/api/covid/data/recovered",
                    type: "get",
                    data: {states: $('#states').val()},
                    traditional: true,
                    success: function (result) {
                        $("#loadingMessage").html("");
                        var datasets = result.datasets;
                        var labels = result.labels;
                        renderRecoveredChart(datasets, labels);
                    },
                    error: function (err) {
                        $("#loadingMessage").html("Error");
                    }
                });


            }
        }


    }
);



