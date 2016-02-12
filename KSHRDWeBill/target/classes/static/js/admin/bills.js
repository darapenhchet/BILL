$(function(){
	
	var _ctx = ($("meta[name='ctx']").attr("content")===undefined) ? "" : $("meta[name='ctx']").attr("content");
	
	// Prepend context path to all jQuery AJAX requests
	$.ajaxPrefilter(function( options, originalOptions, jqXHR ) {
	    if (!options.crossDomain) {
	        options.url = _ctx + options.url;
	    }
	});
	
	var donors = {};
	
	donors.findAll = function(){
		$.ajax({
			url: "/rest/administrator/bills",
			type: "GET",
			data: {
				page: 0,
				limit: 30,
				name: $("#txtSearch").val()
			},
			success: function(data){
				console.log(data);
				/*$("tbody#DONORS").html('');
				$("#DONORS_TEMPLATE").tmpl(data.content).appendTo("tbody#DONORS");
				$("#TOTAL_DONORS").html(data.totalElements);*/
			},
			error: function(data){
				
			}
		});
	};
	
	donors.findAll();
		
	$(document).on('click', '#name', function(){
		$("#popupBillTransactionHist").bPopup();
		//alert($(this).find("a").data("id"));
		donors.findAllBillsByAccountId(13);
	});
});