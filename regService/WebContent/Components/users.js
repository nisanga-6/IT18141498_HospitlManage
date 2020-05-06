$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnReg", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validateUserForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------

	var type = ($("#hiduserIDReg").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "userAPI",
		type : type,
		data : $("#formReg").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onItemSaveComplete(response.responseText, status);
		}
	});

});

function onItemSaveComplete(response, status) {

	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error") 
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else 
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hiduserIDReg").val("");
	$("#formReg")[0].reset();

}


$(document).on("click", ".btnUpdate", function(event){
	 $("#hiduserIDReg").val($(this).closest("tr").find('#hiduserIDUpdate').val());
	 $("#username").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#name").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#password").val($(this).closest("tr").find('td:eq(2)').text());
});




$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "userAPI",
		type : "DELETE",
		data : "userid=" + $(this).data("userid"),
		dataType : "text",
		complete : function(response, status) {
			onItemDeleteComplete(response.responseText, status);
		}
	});
});

function onItemDeleteComplete(response, status) 
{
	if (status == "success") 
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} 
	else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else 
	{
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}
	
	
	function  validateUserForm()
	{
	// CODE
	if ($("#username").val().trim() == "")
	 {
	 return "username.";
	 }
	// NAME
	if ($("#name").val().trim() == "")
	 {
	 return "Insert name.";
	 }
	
	if ($("#password").val().trim() == "")
	 {
	 return "Insert password.";
	 }
	
	return true;
	}



