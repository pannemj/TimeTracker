$(document).ready(function() {

			$("#search-time-form").submit(function(event) {
				// stop submit the form event. Do this manually using ajax post
				// function
				event.preventDefault();
			});

			$("#search-time-submit-btn").bind(
					"click",
					(function() {

						var url = "/records";
						var emailAddress = $("#emailAddress").val();

						if (!(emailAddress === undefined || emailAddress === null
								|| emailAddress === "")) {
							url = url + "?emailId=" + emailAddress;
						}
						
						$("#search-time-submit-btn").prop("disabled", true);
						

						$.ajax({
							type : "GET",
							contentType : "application/json",
							url : url,
							dataType : 'json',
							cache : false,
							timeout : 600000,
							success : function(data) {
								
								$('#timeTrackerDetailsTable').DataTable({
							        "processing": false,
							        "serverSide": false,
							        "paging": false,
							        "ordering":false,
							        "searching": false,
							        "data": data,
							        "columns": [
							            {"data": "startDate","width": "10%"},
							            {"data": "endDate","width": "10%"},
							            {"data": "emailAddress","width": "10%"}
							        ]
							    });
							},
							error : function(e) {
								var json = "<h4><pre>" + e.responseText
										+ "</pre>";
								$('#feedback').html(json);
								$("#search-time-submit-btn").prop("disabled",
										false);
							}
						});

					}));

		});