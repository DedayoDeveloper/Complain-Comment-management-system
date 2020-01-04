

var createWalletValidate = new Validator('[name=form_create_wallet]', [{
        name: 'walletname',
        rules: 'required'
    },
    {
        name: 'financialinstitution',
        rules: 'required'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=form_create_wallet]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small> ');
            if (index === 0)
                $(errors[0].element)[0].focus();
        });
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        console.log(data);
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Wallet Successfully Created</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#addNewWalletModal').modal('hide');
                    setTimeout(function () {
                        goTo('walletManagement');
                    }, 1000);
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }

        });
    }
});

var walletValidate = new Validator('[name=form_edit_wallet]', [{
        name: 'walletname',
        rules: 'required'
    },
    {
        name: 'financialinstitution',
        rules: 'required'
    }
], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=form_edit_wallet]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        //Form validation Failled
        // Do nothing
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small>');
            if (index === 0)
                $(errors[0].element).focus();
        });
//        console.log('error');
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Wallet details successfully updated</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#editWalletModal').modal('hide');
                    goTo('walletManagement');
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed update! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }

        });
    }
});

var fundwalletValidate = new Validator('[name=fund_wallet]', [
    {
        name:'wallet_id',
        rules:'required'
    },
    {   name:'accountnumber',
        rules:'required'  
    },
    {
        name: 'amount', 
        rules: 'required'
    }
], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=fund_wallet]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        //Form validation Failled
        // Do nothing
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small>');
            if (index === 0)
                $(errors[0].element).focus();
        });
//        console.log('error');
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Wallet funding request successfully submitted</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#payoutModal').modal('hide');
                    setTimeout(function(){
                        goTo('mainWallet');
                    },1000);
                   
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed update! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }
        });
    }
});

var walletarray = [];
$(document).on('change', '.WalletBox', function () {
    var chk = $(this);
    var id = chk.next().val();
    if (chk.prop("checked")) {
        walletarray.push(id);
    } else {
        if (walletarray.indexOf(id) > -1) {
            var i = walletarray.indexOf(id);
            walletarray.splice(i, 1);
        }
    }
});

$(document).on('change', '.masterCheck3', function () {
    if ($(this).prop('checked') === true) {
        walletarray.length = 0;
        $('.wallet_records').find('input:not(.masterCheck2)').prop('checked', true);
        var checkedValues = $('input:checkbox:checked').map(function () {
            return this.value;
        }).get();
        var itemtoRemove = "on";
        checkedValues.splice($.inArray(itemtoRemove, checkedValues), 1);
        walletarray = checkedValues;
        console.log(walletarray);
    } else {
        $('.wallet_records').find('input:not(.masterCheck3)').prop('checked', false);
    }
});
$(document).on('click', '.walletbutton', function (e) {
    e.preventDefault();
    var button = $(this);
    var modal = button.closest('.modal');
    var modalFooter = modal.find('.modal-footer');
    modalFooter.hide('fast');
    var url = "deleteWallet";
    var data = walletarray.join(",");
    console.log(data);
    if (walletarray.length > 0) {
        $.post(url, {array: data}, function (result) {
            if (result === "Successful") {
                $('#deleteWallets').html('<div class="alert alert-success text-center">' + 'Successfully removed' + '</div>');
                setTimeout(function () {
                    $.each(walletarray, function (i, val) {
                        $('[type="checkbox"][value="' + val+ '"]').parent().parent().remove();
                    });
                    $('#ask_before_delete').modal('hide');
                    modalFooter.show('fast');
                    $('#deleteWallets').html('');
                }, 3000);
            } else {
                $('#deleteWallets').html('<div class="alert alert-danger text-center">' + 'Failed to remove! Try again' + '</div>');
                setTimeout(function () {
                    modalFooter.show('fast');
                    $('#deleteWallets').html('');
                }, 3000);
            }
            console.log(result);
        });
    }
});

var fundmainwalletValidate = new Validator('[name=fund_main_wallet]', [
    {
        name: 'amount',
        rules: 'required'
    }
], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=fund_main_wallet]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        //Form validation Failled
        // Do nothing
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small>');
            if (index === 0)
                $(errors[0].element).focus();
        });
//        console.log('error');
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Wallet fund request successfully submitted</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#fundMainWalletModal').modal('hide');
                    goTo('mainWallet');
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed update! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }

        });
    }
});

var fundOtherwalletValidate = new Validator('[name=fund_other_wallet]', [
    {
        name:'wallet_id',
        rules:'required'
    },
    {   name:'accountnumber',
        rules:'required'  
    },
    {
        name: 'amount', 
        rules: 'required'
    },
    {
        name: 'narrations', 
        rules: 'required'
    }
], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=fund_other_wallet]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small>');
            if (index === 0)
                $(errors[0].element).focus();
        });
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Wallet successfully funded</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#fundWalletModal').modal('hide');
                    setTimeout(function(){
                        goTo('walletManagement');
                    },1000);     
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed update! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }
        });
    }
});

$(document).on('click', '#searchForEachWallets', function (e) {
    e.preventDefault();
    $('#result1').html(spinner);
    var form = $(this).closest('form');
    var dataVal = form.serialize();
    var url = 'searchEachWallets';
    $.get(url, dataVal, function (result) {
        $('#result1').html(result);
        console.log(url);
        console.log(result);
    });
});

var fundglobalwalletValidate = new Validator('[name=fundglobalwallet]', [
    {
        name: 'amount',
        rules: 'required'
    }
], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=fundglobalwallet]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
//Form validation Failled
// Do nothing
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small>');
            if (index === 0)
                $(errors[0].element).focus();
        });
    } else {
//Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Global Wallet funding request successfully submitted</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#fundGlobalWalletModal').modal('hide');
                    goTo('globalwallet');
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed to fund! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }

        });
    }
});