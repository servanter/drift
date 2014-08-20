$(document).ready(function(){
	$("#release_button").click(function(){
		sendBottle();
	});
});

// send bottle to service
function sendBottle(){
	var user_phone = $("#user_phone").val();
	//var bottle_content = encodeURI(encodeURI($("#bottle_content").val()));
	var bottle_content = $("#bottle_content").val();
	var url = $("#service_path").val() + "/drift.do?date=" + new Date();
	$.post(url,{user_phone:user_phone,bottle_content:bottle_content},function(data){
		alert(data);
		$("#bottle_result").val(data);
	});
}

// call back
function sendBottleBack(data){
	alert(data);
	$("#bottle_result").val(data);
}