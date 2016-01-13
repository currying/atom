function post() {
	$.ajax({
		url : "/atom/rest/login",
		type : 'POST',
		data : JSON.stringify({
			"userId" : "admin",
			"password" : "admin"
		}),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		success : function(data, status, xhr) {
			if (status == "success")
				location.href = "index";
		},
		Error : function(xhr, error, exception) {
			alert(exception.toString());
		}
	});
}
