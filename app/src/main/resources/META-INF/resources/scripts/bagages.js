
$(function () {

    const authorizedluggage = parseFloat($('#company-authorized-luggage').val())
    const priceextraluggage = parseFloat($('#company-price-extra-luggage').val())


    console.log(`Compute luggage`, authorizedluggage, priceextraluggage)


    const changeExtrasWeigthAndAmount = (
        weight, amount
    ) => {
        $('#extra-weight').val(weight)
        $('#extra-weight-amount').val(amount)
    }

    $('#weight').keyup(function () {
        let value = $('#weight').val();

        if (!value) {
            changeExtrasWeigthAndAmount('', '')
            return
        }

        const weight = parseFloat(value)
        if (weight <= authorizedluggage) {
            changeExtrasWeigthAndAmount('', '')
            return
        }

        const extrasWeight = weight - authorizedluggage
        const amount = extrasWeight * priceextraluggage

        changeExtrasWeigthAndAmount(extrasWeight, amount)
    })

})