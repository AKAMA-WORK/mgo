$(document).ready(function() {
    function hideSearchInputs(columns) {
        for (i = 0; i < columns.length; i++) {
            if (columns[i]) {
                $(".filters th:eq(" + i + ")").show();
            } else {
                $(".filters th:eq(" + i + ")").hide();
            }
        }
    }


    // Setup - add a text input to each footer cell
    $('#depart-list-table thead .filters th.filterable').each(function() {
        var title = $('#depart-list-table thead tr:eq(0) th').eq($(this).index()).text();
        $(this).html('<input class="form-control" type="text" placeholder="' + title + '" />');
    });

    // DataTable
    var table = $('#depart-list-table').DataTable({
        responsive: true,
        orderCellsTop: true,
        "pageLength": 50,
        columnDefs: [
            { type: "num", targets: 0 },
            { type: "num", targets: 8 },

        ],
        language: {
            url: 'plugins/datatables/fr.json'
        }
    });

    // Apply the search
    table.columns().eq(0).each(function(colIdx) {
        $('input', $('.filters th')[colIdx]).on('keyup change', function() {
            table
                .column(colIdx)
                .search(this.value)
                .draw();
        });
    });

    table.on("responsive-resize", function (e, datatable, columns) {
        hideSearchInputs(columns);
    });
});



/*
$(document).ready(function () {
    function hideSearchInputs(columns) {
        for (i = 0; i < columns.length; i++) {
            if (columns[i]) {
                $(".filters th:eq(" + i + ")").show();
            } else {
                $(".filters th:eq(" + i + ")").hide();
            }
        }
    }

    $("#depart-list-table thead .filters th.filterable").each(function () {
        var title = $("#depart-list-table thead tr:eq(0) th.filterable").eq($(this).index()).text();
        $(this).html('<input  class="form-control"  type="text" placeholder="' + title + '" />');
    });

    var table = $("#depart-list-table").DataTable({
        responsive: true,
        orderCellsTop: true,
        language: {
            url: 'plugins/datatables/fr.json'
        },

        columnDefs: [
            { responsivePriority: 1, targets: 0 },
            { responsivePriority: 10001, targets: 1 },
            { responsivePriority: 2, targets: -2 }
        ],
        initComplete: function () {
            var api = this.api();

            hideSearchInputs(api.columns().responsiveHidden().toArray());
        }
    });

    table
        .columns()
        .eq(0)
        .each(function (colIdx) {
            $("input", $(".filters th.filterable")[colIdx]).on("keyup change", function () {
                table.column(colIdx).search(this.value).draw();
            });
        });

    table.on("responsive-resize", function (e, datatable, columns) {
        hideSearchInputs(columns);
    });
});
*/