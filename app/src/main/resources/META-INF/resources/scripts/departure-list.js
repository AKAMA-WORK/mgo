//data-table 


$(function () {

    $('.print-manifold-action').click(function (e) {
        e.preventDefault();
        e.stopPropagation();

        const url = window.location.origin + $(this).attr('href');

        printJS(url)


    })

});
