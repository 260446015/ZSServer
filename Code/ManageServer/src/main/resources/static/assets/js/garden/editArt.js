$(function(){
	$(".btn-success").on("click",function(){
		var data = $(".form-control");
		var arr = new Array();
		data.each(function(){
			arr.push($(this).val());
		})
		console.log(arr);
		var req = {"title":arr[0],"summary":arr[1],"park":arr[2],"source":arr[3],"publishTime":arr[4],"sourceLink":arr[5],"articleLink":arr[6],"industry":arr[7],"industryLabel":arr[8],"industryType":arr[9],"author":arr[10],"vector":arr[11],"dimension":arr[12],"content":arr[13]};
		saveData(req);
	})
	$(".btn-danger").on("click",function(){
		window.location.reload();
	})
})
function saveData(_req){
	$.ajax({
		type:'post',
		contentType:'application/json',
		data:JSON.stringify(_req),
		url:'/apis/area/saveData.json',
		success:function(res){
			console.log(res.data);
		}
	})
}