$(function () {
        $.ajax({
            url: "http://localhost:8080/api/covid/states",
            success: function (result) {
                chargeCombo(result);
            },
            error: function (err) {
                $("#loadingMessage").html("Error");
            }
        });
    }
);

function chargeCombo(data) {
    $('#states').multipleSelect({
        data: data
    })
}