$(document).ready(function () {
    $(document).on("change", ".searchBy", function (event) {
        event.preventDefault();

        if (event.target.value === "dateRegistered") {
                $("#divShowOtherDetails").show();

        } else {
            $("#divShowOtherDetails").hide();

        }
    });
    $(document).on("click", "#btnSearchForMobileUsers", function (event) {
        event.preventDefault();
        
        var form = $(this).closest('form');
        var url = $(form).attr('action');
        var dataVal = $(form).serialize();
        
        
        console.log(dataVal);
        $.ajax({
           url: url,
           method: "post",
           data: dataVal
        })
                .done(function (result) {
                    $("#divShowMobileUsers").html(result);
                })
                .fail(function(error){
                    `<div class="alert alert-success text-center"><p>Error occurred while fetching data</p></div>`;
                });     
        
        
        
        
    });
});