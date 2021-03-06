
// FORM VALIDATOR ENGINE
var cPasswordValidate = new Validator('[name=changePasswordForm]', [
    {
        name: 'oPassword',
        rules: 'required|min[8]'
    },
    {
        name: 'nPassword',
        rules: 'required|min[8]||matches(cPassword)|strictPassword'
    },
    {
        name: 'cPassword',
        rules: 'required|matches(nPassword)'
    }], function (errors, event) {
    event.preventDefault();
    var form = $('[name=changePasswordForm]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        //Form validation Failled
        console.log(errors);
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + ' </small>');
            if (index === 0)
                $(errors[0].element)[0].focus();
        });;
    } else {
        //Form validation Passed
        var data = form.serialize();
        form.hide('fast');
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center">' + result + '</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                }, 3000);
                window.location = "welcome";
            } else {
                form.after('<div class="alert alert-danger text-center">' + result + '</div>');
                setTimeout(function () {
                    form.show('fast');
                    form.next().remove();
                }, 3000);
            }
        });
    }
});
