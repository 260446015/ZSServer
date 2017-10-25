var industry = '互联网+';
var industryLabel = '不限';
var time = '一年';
var current = 0;
var dimension = '专家论';
function myClick(a, b, c, d, e) {
	$('#label-list>ul').on('click', 'li', function() {
		$(this).addClass("layui-this").siblings().removeClass('layui-this');
	})
	layui.use('element', function() {

		var element = layui.element;
		element.tabChange('demo', '11');
	});
	industry = a;
	industryLabel = b;
	time = c;
	dimension = d;
	var req = {
		industry : industry,
		industryLabel : industryLabel,
		time : time,
		dimension : dimension,
		pageNum : e == null ? 0 : e,
		pageSize : 10
	};
	myRequest(req, '/head/getExpertOpinion.json');
};

function showTable(data, elem, dimension) {
	layui.use([ 'table', 'element' ], function() {
		var table = layui.table;
		var laypage = layui.laypage;

		// 调用分页
		laypage.render({
			elem : elem,
			count : data.totalElements,
			curr : current + 1,
			jump : function(obj, first) {
				if (first) {
					show(data.content, dimension);
				}
				// 模拟渲染
				if (!first) {
					current = obj.curr;
					var req = {
						industry : industry,
						industryLabel : industryLabel,
						time : time,
						dimension : dimension,
						pageNum : obj.curr,
						pageSize : obj.limit
					};
					$.ajax({
						type : 'post',
						url : "/head/getExpertOpinion.json",
						async : false,
						contentType : 'application/json',
						data : JSON.stringify(req),
						success : function(response) {
							// document.getElementById('biuuu_city_list').innerHTML
							// ="";
							show(response.data.content, dimension);
						}
					});
				}
			}
		});
	});
}

function getDetail(id) {
	$.ajax({
		type : 'get',
		url : '/head/getDetail.json?id=' + id,
		success : function() {
			
		}
	});
}

function back() {
	window.history.back();
}

function checkall(e) {

	if ($('#allcheck').prop('checked')) {
		$("input[name='checkname']").prop('checked', true);

	} else {
		$("input[name='checkname']").prop('checked', false)
	}
}
function deleteCheck() {
	var check = $("[name='checkname']");
	var arr = [];
	check.each(function() {
		if ($(this).prop('checked')) {
			arr.push($(this).prop('value'));
		}
	});
	if(arr.length==0){
		layer.alert("您没有选择任何文章进行删除！");
	}else{
		onDel(arr);
	}

}
function myTop(id) {
	layer.confirm('确定置顶该文章？', function(index) {
		$.ajax({
			url : "/art/totop",
			contentType : 'application/json',
			data : {
				id : id
			},
			success : function(response) {
				layer.alert(response.data);
				myClick(industry, industryLabel, time, dimension,current);
			}
		});
		layer.close(index);
	});
	
}

function onDel(id) {
	layer.confirm('确定删除该文章？', function(index) {
		$.ajax({
			url : "/art/delete",
			contentType : 'application/json',
			data : {
				ids : id
			},
			success : function(response) {
				layer.alert(response.data);
				myClick(industry, industryLabel, time, dimension,current);
			}
		});
		layer.close(index);
	});
}