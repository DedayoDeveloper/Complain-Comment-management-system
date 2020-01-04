

// FORM VALIDATOR ENGINE
var addFinancialInstitutionValidate = new Validator('[name=addFinancialInstitutionForm]', [
    {
        name: 'name',
        rules: 'required'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=addFinancialInstitutionForm]');
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
            console.log(result);
            if (result === 'Financial Institution successfully added') {
                form.after('<div class="alert alert-success text-center m-3">Financial Institution successfully added</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                        goTo('managePartnerInstitutions');
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


// FORM VALIDATOR ENGINE
var editFinancialInstitutionValidate = new Validator('[name=editFinancialInstitutionForm]', [{
        name: 'code', 
        rules: 'exactly[6]'
    },
    {
        name: 'name',
        rules: 'required'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=editFinancialInstitutionForm]');
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
            console.log(result);
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Successful</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                        goTo('managePartnerInstitutions');
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



var addCollectionItemValidate = new Validator('[name=addCollectItemForm]', [{
        name: 'partnercode', 
        rules: 'required'
    },
    {
        name: 'item',
        rules: 'required'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=addCollectItemForm]');
    
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
            console.log(result);
            if (result === 'collection item successfully added') {
                form.hide();
                $('#result').html(spinner);             
                form.after('<div class="alert alert-success text-center m-3">collection item successfully added</div>');
                setTimeout(function () {
                    
                        
                    form[0].reset();
                    form.next().remove();
                         goTo('collectionItems');
//                    form[0].reset();
//                    form.next().remove();
//                    $('#result').html('<div class="alert alert-success text-center m-3">collection item successfully added</div>');
                    
                }, 2000);
               
            } else {
                $('#result').html(spinner);  
//                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed ! Try again' + '</div>');
                setTimeout(function () {
                    $('#result').html('<div class="alert alert-danger text-center m-3">' + 'Failed ! Try again' + '</div>');
                }, 3000);
            }
        });
    }
});



var addUserCollectionItemValidate = new Validator('[name=addUserCollectItemForm]', [{
        name: 'item',
        rules: 'required'
        
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=addUserCollectItemForm]');
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
            console.log(result);
            if (result === 'collection item successfully added') {
                form.hide();
                $('#result').html(spinner);             
               form.after('<div class="alert alert-success text-center m-3">collection item successfully added</div>');
                setTimeout(function () {
                    
                 form[0].reset();
                 form.next().remove();
//                    $('#result').html('<div class="alert alert-success text-center m-3">collection item successfully added</div>'); 
                     goTo('collectionItems');
                }, 2000);
               
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


           
var updateAmountValidate = new Validator('[name=updateAmountForm]', [{
        name: 'newamounttype',
        rules: 'required'
         },
    {
        name: 'item',
        rules: 'required'
        
         },
    {
        name: 'partnercode',
        rules: 'required'
        
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=updateAmountForm]');
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
            console.log(result);
            if (result === 'Update successfull') {
                form.hide();
                $('#result').html(spinner);             
                form.after('<div class="alert alert-success text-center m-3">collection item successfully added</div>');
                setTimeout(function () {
                   form[0].reset();
                  form.next().remove();
//                    $('#result').html('<div class="alert alert-success text-center m-3">Update successfull</div>'); 
                  goTo('collectionItems');
                }, 2000);
                  
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


var updateUserAmountValidate = new Validator('[name=updateUserAmountForm]', [{
        name: 'newamounttype',
        rules: 'required'
         },
   
    {
        name: 'item',
        rules: 'required'
        
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=updateUserAmountForm]');
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
            console.log(result);
            if (result === 'Update successfull') {
                form.hide();
                $('#result').html(spinner);             
                form.after('<div class="alert alert-success text-center m-3">collection item successfully added</div>');
                setTimeout(function () {
                    form[0].reset();
                   form.next().remove();
//                    $('#result').html('<div class="alert alert-success text-center m-3">Update successfull</div>'); 
                        goTo('collectionItems');
                }, 2000);
                
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