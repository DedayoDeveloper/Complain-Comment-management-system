/*
 author: @tobioye88
 company: supersoft
 */

/*
 Version 1
 **set option first
 var options = [
 {
 name: 'fieldName',
 rules: 'required|numeric|min[9]|max[9]|alphabet|alpanumeric|matches(fieldname)|email|'
 }
 ]
 var v = new Validator('[namer=form]', options, function(errors){
 if(errors.length>0){
 //failed
 }else{
 //passed
 }
 }
 });
 */

var Validator = function (form, options, callback) {
    var v = this;
    this.form = document.querySelector(form);
    this.event;
    this.element;
    this.options = options;
    this.errors = [];
    this.callback = callback || function () {};

    this.reload = function () {
        this.form = document.querySelector(form);
        if (this.form) {
            this.form.addEventListener('submit', function (e) {
                v.event = e;
                v.run();
            });
        }
    };
    this.reload();

    this.getValue = function (name) {
        var value;
        var e = document.querySelector('[name=' + name + ']');
        this.element = e;
        if (e.nodeName.toLowerCase() === 'select') {
            //select
            value = e.options[e.selectedIndex].value || e.options[e.selectedIndex].text;
        } else {
            //input, textarea, checkbox, etc
            value = e.value;
        }
        return value;
    };
    this.required = function (name) {
        var value = this.getValue(name);
        if (!(value.toString().length > 0)) {
            this.errors.push({'name': name, 'message': 'Input can not be empty', 'element': this.element});
        }
    };
    this.numeric = function (name) {
        var value = this.getValue(name);
        if (value.match(/\D/g)) {
            this.errors.push({'name': name, 'message': 'Input must be numbers only', 'element': this.element});
        }
    };
    this.alphabet = function (name) {
        var value = this.getValue(name);
        if (value.match(/[^a-zA-Z]/g)) {
            this.errors.push({'name': name, 'message': 'Input must be alfabet only', 'element': this.element});
        }
    };
    this.alpanumeric = function (name) {
        var value = this.getValue(name);
        if (value.match(/[^a-zA-Z0-9]/g)) {
            this.errors.push({'name': name, 'message': 'Input must be numbers or alphabet only', 'element': this.element});
        }
    };
    this.email = function (name) {
        var value = this.getValue(name);
        if (!value.match(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)) {
            this.errors.push({'name': name, 'message': 'Input must be a valid email', 'element': this.element});
        }
    };

    this.min = function (name, par) {
        var value = this.getValue(name);
        var res = (value.toString().length > parseInt(par));
        // console.log(value.toString().length + '>'+ parseInt(par));
        if (!res) {
            this.errors.push({'name': name, 'message': 'Input must be greater than ' + par + ' characters', 'element': this.element});
        }
    };
    this.max = function (name, par) {
        var value = this.getValue(name);
        var res = (value.toString().length < parseInt(par));
        if (res) {
            this.errors.push({'name': name, 'message': 'Input must be less than ' + par + ' characters', 'element': this.element});
        }
    };
    this.matches = function (name, par) {
        var value1 = this.getValue(name);
        var value2 = this.getValue(par);
        if (value1 !== value2) {
            this.errors.push({'name': name, 'message': 'Input ' + name + ' and ' + par + ' must match', 'element': this.element});
        }
    };
    // this.matches = function(name){}

    this.perform = function (rule, name, parm) {
        var par = parm ? parm : false;
        switch (rule) {
            case 'required':
                this.required(name);
                break;
            case 'numeric':
                this.numeric(name);
                break;
            case 'alphabet':
                this.alphabet(name);
                break;
            case 'alpanumeric':
                this.alpanumeric(name);
                break;
            case 'email':
                this.email(name);
                break;
            case 'min':
                this.min(name, par);
                break;
            case 'max':
                this.max(name, par);
                break;
            case 'matches':
                this.max(name, par);
                break;
            default:
                console.warn('rule not found');

        }
    };
    this.run = function () {
        this.errors = [];
        // loop through the options 
        // loop throught the rules
        for (var i = 0; i < this.options.length; i++) {
            var rules = this.options[i].rules.split('|');
            for (var j = 0; j < rules.length; j++) {
                // console.log(rules[j]);
                var comRules = rules[j].split('[');
                // console.log(comRules);
                if (rules[j].indexOf('[') > 0) {
                    // console.log('bracket');
                    var inRule = rules[j].split('[');
                    var parm = inRule[inRule.length - 1].replace(']', '');
                    this.perform(inRule[0], options[i].name, parm);
                } else if (rules[j].indexOf('(') > 0) {
                    // console.log('parentesis');
                    var inRule = rules[j].split('(');
                    var parm = inRule[inRule.length - 1].replace(')', '');
                    this.perform(inRule[0], options[i].name, parm);
                } else {
                    // console.log('all');
                    this.perform(rules[j], options[i].name);
                }
            }
        }
        this.callback(this.errors, this.event);
    };
    this.passed = function () {
        return !this.errors.length;
    };
    this.allErrors = function () {
        return this.errors;
    };
};