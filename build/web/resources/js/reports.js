/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).on('click', '#btnSelectHeadings', function (e) {
    if ($('[name=reportType]:checked').prop('checked')) {
        if ($('#fromDate').val() !== "" && $('#toDate').val() !== "") {
            var spinnerr = '<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>';
            var btn = $(this);
            var btnText = btn.html();
            console.log(btnText);
            btn.html(spinnerr);
            setTimeout(function () {
                btn.html(btnText);
                console.log("ayomide");
            }, 3000);
        } else {
            e.preventDefault();
            alert("You have not specified the date range");
        }

    } else {
        e.preventDefault();
        alert("You have not specified type of report!");
    }
});
$(document).on('show.bs.modal', '#downloadreport', function (event) {
    var button = $(event.relatedTarget);
    var wallnumm = button.data("walletnumber");
    var modal = $(this);
    modal.find('[name=walletnumber]').val(wallnumm);
//    console.log(wallnumm);
//    console.log(modal.find('[name=walletnumber]').val());
});
$(document).on('hidden.bs.modal', '#downloadreport', function (event) {
//    console.log(walletArray);
//    var wnumbers = walletArray.join(",");
//    console.log(wnumbers);
    $('#btnSelectHeadings').html('<i class="fa fa-download"></i> Download');
    var modal = $(this);
    var form = modal.closest('form');
    modal.find('.form-check-input').prop('checked', false);
});
$(document).on('hidden.bs.modal', '#vwdownloadreport', function (event) {
//    console.log(walletArray);
//    var wnumbers = walletArray.join(",");
//    console.log(wnumbers);
    $('#btnSelectHeadings').html('<i class="fa fa-download"></i> Download');
    var modal = $(this);
    var form = modal.closest('form');
    modal.find('.form-check-input').prop('checked', false);
    form[0].reset();
});
$(document).on('show.bs.modal', '#generatereport', function (event) {
    var button = $(event.relatedTarget);
    var wallnumm = button.data("walletnumber");
    var modal = $(this);
    modal.find('[name=walletnumber]').val(wallnumm);
    console.log(wallnumm);
    console.log(modal.find('[name=walletnumber]').val());
});
$(document).on('click', '#btnSearchReports', function (e) {
    e.preventDefault();
    var token;
    var spinnerr = '<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>';
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "" && $('#thisWalletnumber').val() != "") {
        if ($('[name = thisWalletnumber]').val() === "all_wallets") {
            var btnText = btn.html();
            btn.html(spinnerr);
//            $('#divShowWalletDetails').html("");
            $('#divShowWalletDetails').html(spinnerr);
            var url = 'getallwallettransactionsreports';
//            $.post(url, {"fromDate": $('#fromDate').val(), "toDate":$('#toDate').val()}, function (result) {
            $.post(url, dataVal, function (result) {
                $('#divShowWalletDetails').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 3000);
        }

        else {            
            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowWalletDetails').html(spinnerr);
            var url = 'getwallettransactionsreports';
            $.post(url, dataVal, function (result) {
                $('#divShowWalletDetails').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 1500);
        }

    } else {
        e.preventDefault();
        alert("You have not selected all required fields");
    }
});
$(document).on('click', '#btnGetBillPayments', function (e) {
    e.preventDefault();
    var token;
//    var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "") {
        if ($('[name = thisWalletnumber]').val() === "all_wallets") {
//            token = prompt("Please enter your token");
//            if (!token) {
//                return;
//            }

//            $.post('checktoken', {"token": token}, function (result) {
//                $('#divShowWalletDetails').html(spinnerr);
//                console.log(result);
//                if (result === "Successful") {

                    var btnText = btn.html();
                    btn.html(spinnerr);
                    $('#divShowBillsPayments').html("");
                    var url = 'getallbillspaymentreports';
                    $.post(url, {"fromDate": $('#fromDate').val(), "toDate":$('#toDate').val()}, function (result) {
                        $('#divShowBillsPayments').html(result);
                    });
                    setTimeout(function () {
                        btn.html(btnText);
                    }, 3000);

//                } else {
//                    $('#divShowWalletDetails').html('<div class="alert alert-danger text-center font-weight-bold">You provided the wrong token</div>');
//
//                }
//            });
        } else {
//            var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
//            var btn = $(this);
//            var btnText = btn.html();
//            console.log(btnText);
            
            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowBillsPayments').html(spinnerr);
            var url = 'getbillspaymentreports';
            $.post(url, dataVal, function (result) {
                $('#divShowBillsPayments').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 1500);
        }

    } else {
        e.preventDefault();
        alert("You have not specified the date range");
    }

});
$(document).on('click', '#btnGetAirtimeVendingReports', function (e) {
    e.preventDefault();
    var token;
//    var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "") {
        if ($('[name = thisWalletnumber]').val() === "all_wallets") {
//            token = prompt("Please enter your token");
//            if (!token) {
//                return;
//            }

//            $.post('checktoken', {"token": token}, function (result) {
//                $('#divShowWalletDetails').html(spinnerr);
//                console.log(result);
//                if (result === "Successful") {

            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowAirtimeVendingReports').html("");
            var url = 'getallairtimevendingreports';
            $.post(url, {"fromDate": $('#fromDate').val(), "toDate": $('#toDate').val()}, function (result) {
                $('#divShowAirtimeVendingReports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 3000);

//                } else {
//                    $('#divShowWalletDetails').html('<div class="alert alert-danger text-center font-weight-bold">You provided the wrong token</div>');
//
//                }
//            });
        } else {
//            var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
//            var btn = $(this);
//            var btnText = btn.html();
//            console.log(btnText);

            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowAirtimeVendingReports').html(spinnerr);
            var url = 'getairtimevendingreports';
            $.post(url, dataVal, function (result) {
                $('#divShowAirtimeVendingReports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 1500);
        }

    } else {
        e.preventDefault();
        alert("You have not specified the date range");
    }

});
$(document).on('click', '#btnGetFundTransferReports', function (e) {
    e.preventDefault();
    var token;
//    var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "") {
        if ($('[name = thisWalletnumber]').val() === "all_wallets") {
            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowFundTransferReports').html("");
            var url = 'getallfundtransferreports';
            $.post(url, {"fromDate": $('#fromDate').val(), "toDate": $('#toDate').val()}, function (result) {
                $('#divShowFundTransferReports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 3000);
        } else {
            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowFundTransferReports').html(spinnerr);
            var url = 'getfundtransferreports';
            $.post(url, dataVal, function (result) {
                $('#divShowFundTransferReports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 1500);
        }

    } else {
        e.preventDefault();
        alert("You have not specified the date range");
    }

});

$(document).on('click', '#btnGetReportsByCategory', function (e) {
    e.preventDefault();
    var token;
//    var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    console.log(dataVal);
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "" && $('#thisProduct').val() !== "") {
        if ($('[name = thisWalletnumber]').val() === "all_wallets") {
            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowCategorisedReports').html("");
            var url = 'getallreportsbycategory';
            $.post(url, {"fromDate": $('#fromDate').val(), "toDate": $('#toDate').val()}, function (result) {
                $('#divShowCategorisedReports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 3000);
        } else {
            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowCategorisedReports').html(spinnerr);
            var url = 'getreportsbycategory';
            $.post(url, dataVal, function (result) {
                $('#divShowCategorisedReports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 1500);
        }

    } else {
        e.preventDefault();
        alert("You may have forgotten to specify date range or report category");
    }

});

$(document).on('click', '#btnGetBulkPaymentReports', function (e) {
    e.preventDefault();
    var token;
//    var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "") {
        if ($('[name = thisWalletnumber]').val() === "all_wallets") {
//            token = prompt("Please enter your token");
//            if (!token) {
//                return;
//            }

//            $.post('checktoken', {"token": token}, function (result) {
//                $('#divShowWalletDetails').html(spinnerr);
//                console.log(result);
//                if (result === "Successful") {

            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowBulkpaymentreports').html("");
            var url = 'getbulkpaymentsreports';
            $.post(url, {"fromDate": $('#fromDate').val(), "toDate": $('#toDate').val()}, function (result) {
                $('#divShowBulkpaymentreports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 3000);

//                } else {
//                    $('#divShowWalletDetails').html('<div class="alert alert-danger text-center font-weight-bold">You provided the wrong token</div>');
//
//                }
//            });
        } else {
//            var spinnerr = `<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>`;
//            var btn = $(this);
//            var btnText = btn.html();
//            console.log(btnText);

            var btnText = btn.html();
            btn.html(spinnerr);
            $('#divShowBulkpaymentreports').html(spinnerr);
            var url = 'getbulkpaymentsreports';
            $.post(url, dataVal, function (result) {
                $('#divShowBulkpaymentreports').html(result);
            });
            setTimeout(function () {
                btn.html(btnText);
            }, 1500);
        }

    } else {
        e.preventDefault();
        alert("You have not specified the date range");
    }

});

$(document).on('change', '#checkForWallets', function (e) {
    var chk = $(this);
    var chkVal = chk.val();
    console.log(chkVal);
    $('#thisWalletnumber').val(chkVal);
});
$(document).on('hidden.bs.modal', '#downloadreportforthisfileModal', function (event) {
    var modal = $(this);
    modal.find('.form-check-input').prop('checked', false);
//    form[0].reset();
});

$(document).on("click", '.clickable-row', function(event){
    var url = $(this).attr('data-href');
    var sessionIdd = $(this).attr('data-sessionId');
    $.post(url, {"sessionId":sessionIdd}, function (result) {
        $('#showSearchResults').html(result);
    });
});

$(document).on('click', '#btnSearchReports1', function (e) {
    e.preventDefault();
    var token;
    var spinnerr = '<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>';
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    console.log(dataVal);
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "" && $('#product').val() !== "") {
    var btnText = btn.html();
    btn.html(spinnerr);
    $('#divShowWalletDetails1').html(spinnerr);
    var url = 'gettransactionperproductreport';
    $.post(url, dataVal, function (result) {
    $('#divShowWalletDetails1').html(result);
    console.log(url);
    });
    setTimeout(function () {
    btn.html(btnText);
    }, 1500);
    }
    else {
    e.preventDefault();
    alert("You have not selected all required fields");
    }
});


$(document).on("click", '.clickable-row2', function(event){
    var url = $(this).attr('data-href');
    var productName = $(this).attr('data-productName');
    $.post(url, {"productName":productName}, function (result) { 
    $('#showSearchResults2').html(result);
    });
});

$(document).on('click', '#btnSearchReports2', function (e) {
    e.preventDefault();
    var token;
    var spinnerr = '<i class="fa fa-spinner fa-spin" aria-hidden="true"></i>';
    var btn = $(this);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    console.log(dataVal);
    if ($('#fromDate').val() !== "" && $('#toDate').val() !== "" && $('#status').val() !== "") {
        var btnText = btn.html();
        btn.html(spinnerr);
        $('#divShowWalletDetails2').html(spinnerr);
        var url = 'getcredittransactionperstatusreport';
        $.post(url, dataVal, function (result) {
            $('#divShowWalletDetails2').html(result);
            console.log(url);
        });

        setTimeout(function () {
            btn.html(btnText);
            }, 1500);
    }
    else {
        e.preventDefault();
        alert("You have not selected all required fields");
    }
});

$(document).on("click", '.clickable-row3', function(event){
    var url = $(this).attr('data-href');
    var status = $(this).attr('data-status');
    $.post(url, {"status":status}, function (result) { 
    $('#showSearchResults3').html(result);
    });
});