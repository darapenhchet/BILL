$(function() {
	var customer = {};
	
	var _ctx = ($("meta[name='ctx']").attr("content")===undefined) ? "" : $("meta[name='ctx']").attr("content");

	// Prepend context path to all jQuery AJAX requests
	$.ajaxPrefilter(function( options, originalOptions, jqXHR ) {
	    if (!options.crossDomain) {
	        options.url = _ctx + options.url;
	    }
	});
	customer.donates = function(){
		var input = {
			"expiry_date" : "20160229",
	    	"remark" : "HRD DONATION",
/*	    	"customer_code" : $("#txtCustomerCode").val(),
	    	"customer_name" : $("#txtCustomerName").val(),
	    	"customer_email" : $("#txtCustomerEmail").val(),
	    	"customer_phone" : $("#txtCustomerPhone").val(),*/
	    	"currency" : "USD",
	    	"amount" : $("#txtAmount").val()
		};
		$.ajax({ 
		    url: "/donates", 
		    type: 'POST', 
		    dataType: 'JSON', 
		    data: JSON.stringify(input),  
		    beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
            },
		    success: function(data) { 
		    	console.log(data);
		    	if(data.RESP_DATA.response_code=="200"){
		    		$("#alertSuccess").show();
		    		$("#alertSuccess").delay(5000).fadeOut(400);
		    	}else{
		    		$("#errorMessage").html(data.RESP_DATA.response_msg);
		    		$("#alertDanger").show();
		    		$("#alertDanger").delay(5000).fadeOut(400);
		    	}
		    },
		    error:function(data,status,er) { 
		        console.log("error: "+data+" status: "+status+" er:"+er);
		    }
		});				
	};
	
	customer.signup = function(){
		var input = {
				"name" : $("#txtCustomerName").val(),
		    	"email" : $("#txtCustomerEmailSignup").val(),
		    	"phone" : $("#txtCustomerPhone").val(),
		    	"password" : $("#txtPasswordSingup").val(),
			};
			$.ajax({ 
			    url: "/users/signup", 
			    type: 'POST', 
			    dataType: 'JSON', 
			    data: JSON.stringify(input),  
			    beforeSend: function(xhr) {
	                xhr.setRequestHeader("Accept", "application/json");
	                xhr.setRequestHeader("Content-Type", "application/json");
	                xhr.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
	            },
			    success: function(data) { 
			    	console.log(data);
			    	if(data.RESP_CODE==200){
			    		$("#signUp #alertSuccess").show();
			    		$("#signUp #alertSuccess").delay(5000).fadeOut(400);
			    	}else{
			    		$("#signUp #errorMessage").html(data.RESP_DATA.RESP_MSG);
			    		$("#signUp #alertDanger").show();
			    		$("#signUp #alertDanger").delay(5000).fadeOut(400);
			    	}
			    },
			    error:function(data,status,er) { 
			        console.log("error: "+data+" status: "+status+" er:"+er);
			    }
			});	
	};
	
	$("#btnSubmit").click(function(){
		customer.donates();
	});
	
	$("#btnSignup").click(function(){
		customer.signup();
	})
	
});