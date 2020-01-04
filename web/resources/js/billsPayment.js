// FORM VALIDATOR ENGINE
var bPValidate = new Validator('[name=form_bill]', [{
        name: 'biller',
        rules: 'required'
    },
    {
        name: 'srcAccount',
        rules: 'required'
    },
    {
        name: 'amount',
        rules: 'required'
    }, {
        name: 'billerinfo',
        rules: 'required'
    }, {
        name: 'phone',
        rules: 'required|numeric'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=form_bill]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        //Form validation Failled
        // Do nothing
        errors.forEach(function(elem, index){
            $(elem).addClass('is-invalid').after('<small class="errors text-danger">' + elem.message + '</small>');
            if(index === 0)
                $(errors[0].element)[0].focus();
        });
//        console.log('error');
    } else {
        //Form validation Passed
        $('#payBillsModal').modal('toggle');
    }
});

    $(document).on('click', '#btn-next', function (e) {
        e.preventDefault();
        var form = $('[name=form_bill]');
        var data = form.serialize();
        console.log(data);
        var url = form.attr('action');
        $('#result1').html(spinner);
        $.post(url, data, function (result) {
            form[0].reset();
            $('#result1').html(result);
        });
    });

    $(document).on('click', '#btn-back-contbpay', function (e) {
        e.preventDefault();
        var form = $('[name=form_cont_bill]');
        var data = form.serialize();
        console.log(data);
        var url = $(this).attr('href');
        console.log(url);
        $.post(url, data, function (result) {
            form[0].reset();
            $('#result1').html(result);
        });
    });

    $(function () {
        $(document).on('click', '.pay_bill', function (event) {
            event.preventDefault();
            $(this).closest('form').submit();
        });

        $(document).on('click', '.screen-1-billers', function (event) {
            event.preventDefault();
            var biller = $(this).data('loc');
            var billerId = $(this).data('id');
            var imagepath = $(this).data('imagepath');
            var validate_biller = $(this).data('validate_biller');
            var capricorn_id = $(this).data('capricorn_id');
            var capricorn_paymentcollectionid = $(this).data('capricorn_paymentcollectionid');
            var capricorn_paymentmethod = $(this).data('capricorn_paymentmethod');
            var serviceId = $(this).data('serviceid');
            console.log('serviceId: ' + serviceId);
            console.log('validate_biller: ' + validate_biller);
            console.log('capricorn_paymentmethod: ' + capricorn_paymentmethod);

            goTo("bills/?billername="+biller+"&id="+billerId+"&imagepath="+imagepath+"&validate_biller="+validate_biller+"&capricorn_id="+capricorn_id+"&capricorn_paymentcollectionid="+capricorn_paymentcollectionid+"&capricorn_paymentmethod="+capricorn_paymentmethod+"&capricorn_serviceId="+serviceId);
        });

        $(document).on('click','.btn-screen-3', function (event) {
            $('.screen-2').hide();
            var url = "payBill";
            var data = $('[name=form_bill]').serialize();
            $.post(url, data, function (result) {
                window.location.reload(true);
                console.log(result);
            });
        });

        $(document).on('click', '.bills_back', function (event) {
            var btnParent = $(this).closest('.col-md-6');
            btnParent.parent().children().hide();
            console.log(btnParent.parent().first());
            btnParent.parent().children().first().show();
        });
    });

//    $(document).on('click', '.completebillspay', function (e) {
//        e.preventDefault();
//        $(this).hide('fast');
//        var form = $(this).closest('form');
//        var data = form.serialize();
//        var url = 'completeBillsPayment';
//        $.post(url, data, function (result) {
//            if (result == '00') {
//               form.after('<div class="alert alert-success text-center m-3">Bills successfully submitted</div>');
//            }  else {
//                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed ! Try again' + '</div>');
//            }
//        });
//    });

    $(document).on('click', '.completebillspay', function (e) {
        e.preventDefault();
        $(this).hide('fast');
        var form = $(this).closest('form');
        var data = form.serialize();
        var url = 'completeBillsPayment';
        $.get(url, data, function (result) {
            if (result == '00') {
                alert('Bill payment successfully submitted');

                var url = 'viewBillsPayReceipt';
                console.log(url);

                $('#result1').html(spinner);
                $.get(url, function (result) {
                    $('#result1').html(result);
                });
            }

//            else if (result == '98') {
//                $('#transfer-alert').html('Wrong token entered, fund transfer NOT successfully processed').addClass('alert alert-danger');
//
//                alert('Wrong token entered, fund transfer NOT successfully submitted');
//            }
            else {
//                $('#transfer-alert').html('Fund transfer NOT successfully submitted').addClass('alert alert-danger');

                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed ! Try again' + '</div>');

                alert('Bill Payment NOT successfully submitted');
            }
        });
    });