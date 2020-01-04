
// FORM VALIDATOR ENGINE
var addUserValidate = new Validator('[name=form_reg_user]', [{
        name: 'username',
        rules: 'required'
    },
    {
        name: 'firstname',
        rules: 'required|alphabet'
    },
    {
        name: 'surname',
        rules: 'required'
    },
    {
        name: 'phonenumber',
        rules: 'required|numeric'
    },{
        name: 'transactiontoken',
        rules: 'required|min[3]'
    },
    {
        name: 'accountnumber',
        rules: 'required'
    },
    {
        name: 'accountname',
        rules: 'required'
    },
    {
        name: 'kyclevel',
        rules: 'required'
    },
    {
        name: 'bvn',
        rules: 'required'
    },
    {
        name: 'financialinstitution',
        rules: 'required'
    },
    {
        name: 'usertype',
        rules: 'required'
    },
    
    {
        name: 'password',
        rules: 'required|min[8]'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=form_reg_user]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        //Form validation Failled
        // Do nothing
        errors.forEach(function(elem, index){
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small> ');
            if(index === 0)
                $(errors[0].element)[0].focus();
        });
//        console.log('error');
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">User Successfully Added</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
//                    $('#addNewUserModal').modal('hide');
                    setTimeout(function () {
                        goTo('users');
                    }, 1000);
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed ! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }

        });
    }
});

var userValidate = new Validator('[name=editUserForm]', [{
        name: 'firstname',
        rules: 'required'
    },
    {
        name: 'surname',
        rules: 'required'
    },
    {
        name: 'phoneNumber',
        rules: 'required|numeric'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=editUserForm]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        //Form validation Failled
        // Do nothing
        errors.forEach(function(elem, index){
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small>');
            if(index === 0)
                $(errors[0].element).focus();
        });
//        console.log('error');
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">User details successfully updated</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#editUserModal').modal('hide');
                    goTo('users');
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

var valConfirmVerify;
$(document).on('click', '#btnPartialBvn', function(event){
    event.preventDefault();
    var modal = $(this);
//    var form = $('[name=verifyBvn]');
    var getBvn = $('[name=bvn]').val();
    console.log("this:"+getBvn);
    valConfirmVerify = 'yes';
$('#confirmVerifyBvn').modal('hide');
});
$(document).on('show.bs.modal', '#confirmVerifyBvn', function (event) {
    button = $(event.relatedTarget);
    var getBvn = $('[name=bvn]').val();
    var srcAccount = $('[name=srcAccount]').val();
    var token = $('[name=token]').val();
    console.log("getBvn: " + getBvn);
    console.log("srcAccount: " + srcAccount);
    console.log("token: " + token);
    var modal = $(this);
    modal.find('[name=bvn]').val(getBvn);
    modal.find('[name=srcAccount]').val(srcAccount);
    modal.find('[name=token]').val(token);
//                   
});

var spinner = '<div class="text-center p-5"><i class="fa fa-spinner fa-spin" aria-hidden="true" style="font-size:100px;"></i></div>';

$(document).on('hidden.bs.modal', function (event) {
    button = $(event.relatedTarget);
    var confirmVerify = button.data('confirmVerify');
    var getBvn = $('[name=bvn]').val();
    var srcAccount = $('[name=srcAccount]').val();
    var token = $('[name=token]').val();
    var modal = $(this);
    if(valConfirmVerify === 'yes'){
        $('#divShowBvnConfirmation').html("");
        $('#divShowBvnConfirmation').html(spinner);
        $.post('verifyBvn', {"bvn": getBvn, "srcAccount": srcAccount, "token": token}, function (result) {
            $('#divShowBvnConfirmation').html(result);
        }); 
    }
});


//$('#verifyBvn').load(function(e){
//   console.log("i loaded");
//   var form = $(this);
//   form.reset();
//});