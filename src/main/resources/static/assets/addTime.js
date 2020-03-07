$(document).ready(function() {

	$("#add-time-form").submit(function(event) {
		//stop submit the form event. Do this manually using ajax post function
		event.preventDefault();
	});

	$("#add-time-submit-btn").bind("click", (function() {

		var addTimeForm = {};
		addTimeForm["startDate"] = $("#startDate").val();
		addTimeForm["endDate"] = $("#endDate").val();
		addTimeForm["emailAddress"] = $("#emailAddress").val();

		$("#add-time-submit-btn").prop("disabled", true);

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/record",
			data : JSON.stringify(addTimeForm),
			dataType : 'json',
			cache : false,
			timeout : 600000,
			success:function(){
				$("#startDate").val("");
				$("#endDate").val("");
				$("#emailAddress").val("");
				$('#feedback').html("");
			},
			error : function(e) {
				var json = "<h4><pre>" + e.responseText + "</pre>";
				$('#feedback').html(json);
				$("#add-time-submit-btn").prop("disabled", false);
			}
		});

	}));

	$("#add-time-clear-btn").bind("click", (function() {
		$('#doc_title').val("");
		$("#startDate").val("");
		$("#endDate").val("");
		$("#emailAddress").val("");
		$('#feedback').html("");
		$("#add-time-clear-btn").prop("disabled", false);
	}));

});