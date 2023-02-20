$('document').ready(function(){
	$('.data-table').DataTable({
		scrollCollapse: true,
		autoWidth: false,
		responsive: true,
		columnDefs: [{
			targets: "datatable-nosort",
			orderable: false,
		}],
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		"language": {
			"info": "_START_-_END_ sur _TOTAL_ départs",
            "infoEmpty":      "Affichage de 0 à 0 sur 0 enregistrements",
			searchPlaceholder: "Mot clé",
			search: "Rechercher",
			"emptyTable":     "Aucun départ correspond à ce mot clé",
			"lengthMenu":     "Montrer _MENU_ enregistrements",
			"zeroRecords":     "Aucun départ n'est trouvé",
			paginate: {
				next: '<i class="ion-chevron-right"></i>',
				previous: '<i class="ion-chevron-left"></i>'  
			}
		},
	});
});