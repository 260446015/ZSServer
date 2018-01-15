$(function(){
	showAccount(serarchName);
	$(".btn-info").on("click",function(){
		serarchName = $("#search").val();
		showAccount(serarchName);
	});
});
var serarchName = '中关村软件园';
function showAccount(_serarchName){
	var req = {"park":_serarchName};
	$.ajax({
		type:'post',
		url:'/apis/user/getAccountByUser.json',
		data:JSON.stringify(req),
		contentType:'application/json',
		success:function(res){
			console.log(res.data);
			var html = '';
			var arr = res.data;
			var userPrice = 0;
			for (var i = 0; i < arr.length; i++) {
				userPrice += arr[i].totalPrice;
				html += '<tr><td>'+arr[i].userAccount+'</td><td>'+arr[i].totalPrice+'</td><td>???</td></tr>';
			}
			console.log(userPrice);
			$("#amount").html(html);
			$("#total").html(userPrice+"元");
		}
	})
}