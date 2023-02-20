


const concatStrings = (lname, fname, separator) => {

    if (lname && fname) {
        return lname + separator + fname;
    }

    return lname ? lname : fname;
}


const deleteComputedValues = (values) => {

    if (values) {
        delete values.fullname
        delete values.idfullinfo,
            delete values.contactfullname,
            delete values.contactallphones
    }

    return values
}

const getComputedValues = (values) => {

    const fullname = values.civility ? values.civility + " " + concatStrings(values.lname, values.fname, " ")
        : concatStrings(values.lname, values.fname, " ");

    let idfullinfo = "";
    let idshortinfo = "";



    if (values.idnumber && values.idtype) {
        const typeCol = ALL_ID_TYPES.find((idType) => idType.ididtype == values.idtype)?.idtypecol
        idfullinfo += typeCol + " ";

        idshortinfo = typeCol + " N° " + values.idnumber;



        idfullinfo += "N° " + values.idnumber + " ";


        if (values.idissuelocation || values.idissuedate) {
            idfullinfo += "fait ";


            if (values.idissuelocation) {
                idfullinfo += "à " + values.idissuelocation + " ";
            }

            if (values.idissuedate) {
                idfullinfo += "le " + values.idissuedate + " ";
            }
        }

        if (values.idduplicatalocation || values.idduplicatadate) {
            idfullinfo += "duplicata delivré ";


            if (values.idduplicatalocation) {
                idfullinfo += "à " + values.idduplicatalocation + " ";
            }

            if (values.idduplicatadate) {
                idfullinfo += "le " + values.idduplicatadate + " ";
            }
        }
    }

    idfullinfo = idfullinfo.trim();


    const contactfullname = values.contactcivility
        ? values.contactcivility + " " + concatStrings(values.contactlname, values.contactfname, " ")
        : concatStrings(values.contactlname, values.contactfname, " ");

    const contactallphones = concatStrings(values.contactphone, values.contactphone1, "/");



    return {
        fullname,
        idfullinfo,
        contactfullname,
        contactallphones,
        idshortinfo
    }


}


const getFormValues = (form) => {

    const serialized = $(form).serializeArray();
    return serialized.reduce((values, { name, value }) => {

        return {
            ...values,
            [name]: value
        };

    }, ({}));


}



$('document').ready(function () {
    const clientForm = $('#manifold-client-form')

    const resetClientForm = () => {
        const values = getFormValues('#manifold-client-form-form')
        Object.keys(values).forEach(name => {
            $('#manifold-client-form-form [name="' + name + '"]').val('');
        })

    }


    clientForm.modal({ backdrop: 'static', keyboard: false, show: false })




    $('#validate-client-form').click(function (e) {
        e.preventDefault();
        e.stopPropagation();


        const formValues = getFormValues('#manifold-client-form-form');

        // TODO : Validate form

        console.log('Form values ', formValues);
        const values = {
            ...formValues,
            ...getComputedValues(formValues)
        }

        const node = '#manifold-table .place-number[data-place="' + values.place + '"]'
        Object.keys(values).forEach(key => {
            $(node).attr('data-' + key, values[key])
        })

        $(node).parent().find('.fullname').html(values.fullname)
        $(node).parent().find('.idshortinfo').html(values.idshortinfo)

        clientForm.modal('hide');

        resetClientForm();

    })



    $('#manifold-table tr td').each(function () {

        const td = this;

        $(this).click((e) => {


            e.preventDefault();
            e.stopPropagation();

            const self = $(td).find('.place-number.occuped')

            if (self.get()[0]) {

                const values = $(self).data()
                Object.keys(values).forEach(name => {

                    const selector = '#manifold-client-form-form [name="' + name + '"]'
                    $(selector).val(values[name]);

                    if($(selector).hasClass('custom-select2')){
                        $(selector).trigger('change');
                    }

                })
                clientForm.modal('show')


            }




        })


    })


    $('#manifold-validate-form').click((e) => {
        e.preventDefault();
        e.stopPropagation();

        const manifolds = [];
        $('#manifold-table .place-number.occuped').each(function () {
            manifolds.push(deleteComputedValues($(this)[0].dataset));
        });


        console.log(JSON.stringify(manifolds));


        $('#manifolds').val(JSON.stringify(manifolds));


        $("#manifold-form").submit();



    })


    // Mode
    $('#manifold-client-form .advanced-mode').css('display', 'none');
    $('#switch-mode-button').click((e) => {
        e.preventDefault();
        e.stopPropagation();

        const mode = $(this).attr('data-mode');


        if (mode === 'advanced') {
            $(this).attr('data-mode', 'simple');
            $('#manifold-client-form .advanced-mode').css('display', 'none');
            $(this).html(`Plus d'informations ...`);

        }
        else {


            $(this).attr('data-mode', 'advanced');
            $('#manifold-client-form .advanced-mode').css('display', 'flex');
            $(this).html(`Moins d'informations ...`);
        }
    })
});


