$(function(){
	showAccount();
});
function showAccount(){
	var req = {"park":"中关村软件园"};
	$.ajax({
		type:'post',
		url:'/apis/user/getAccountByUser.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		success:function(res){
			console.log(res.data);
			var thead = '';
			var html = '';
			var arr = res.data;
			var userPrice = 0;
			for (var i = 0; i < arr.length; i++) {
				userPrice += arr[i].totalPrice;
				thead += '<th>'+arr[i].userAccount+'</th>';
				html += '<td>' + arr[i].totalPrice + '</td>';
			}
			console.log(userPrice);
			$("#thead").html(thead);
			$("#account").html(html);
			$("#total").html(userPrice+"元");
		}
	})
}