var time ="近1周";
$(function () {
    getKeyWordCloud(time);
});
function getKeyWordCloud(d){
    var param={time:d};
    $.ajax({
        url:'/apis/industryinfo/findKeyWord.json',
        type:'POST',
        data:param,
        success:function(res){
            if(res.data.length==0){
                echarts.dispose(document.getElementById("scatter"));
                $('#scatter').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
                $('#industryKeyWordArcitleList').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
            }else{
                var arr = res.data;
                echarts.dispose(document.getElementById("scatter"));
                var scatter = echarts.init(document.getElementById("scatter"));
                echartDataInit(arr);
                option.series[0].data=data1;
                scatter.setOption(option, true);
                scatter.on('click',function(param){
                    var d = $("input[type=radio]:checked").val();
                    getArticleByKeyWord(d,param.name);
                });
                getArticleByKeyWord(d,arr[0].name);
            }
        }

    });
};

function getArticleByKeyWord(a,b){
    var param={time:a,keyWord:b};
    $.ajax({
        type: 'post',
        url: "/apis/industryinfo/findArticleListByKeyWord.json",
        data: param,
        success:function(res){
            if(res.data==null){
                $('#industryKeyWordArcitleList').html('<div class="not-data"><img src="/images/notData.png" /><p class="tips-text">暂无数据</p></div>');
            }else{
                var arr = res.data;
                $('#industryKeyWordArcitleList').html(showList(arr));
                initPages();
            }
        }
    });
};
function showList(arr){
    var array=[];
    $.each(arr,function(index,item){
        array.push(
            '<tr class="gradeX"><input type="hidden" class="form-control input-block" value="'+item.id+'"/><td>'
            +'<a href="'+item.articleLink+'" target="_blank">'+item.aid+ '</a></td><td>'
            +item.articleLink+ '</td><td>'
            +item.industryLabel + '</td><td>'
            +item.title + '</td>'
            + '<td class="actions">'
            +'<a href="javascript:void(0);" class="on-default editindusinfo"><i class="fa fa-pencil"></i></a>'
            +'<a href="javascript:void(0);" class="on-default removeindusinfo modal-basic"><i class="fa fa-trash-o"></i></a></td></tr>'

        )
    });
    var inner = array.join('');
    return inner;
};
function echartDataInit(arr){
    data1 = [
        {
            value: [50, 50], symbolSize: 140, name: arr[0].name, label:label
        },
        {
            value: [25,60], symbolSize: 100, name: arr[1].name, label:label
        },
        {
            value: [65,90], symbolSize: 85, name: arr[2].name, label:label
        },
        {
            value: [32,20], symbolSize: 75, name: arr[3].name, label:label
        },
        {
            value: [55,10], symbolSize: 70, name: arr[4].name, label:label
        },
        {
            value: [70,50], symbolSize: 65, name: arr[5].name, label: label
        },
        {
            value: [35,95], symbolSize: 60, name: arr[6].name, label: label
        },
        {
            value: [50,100], symbolSize: 55, name: arr[7].name, label:label
        },
        {
            value:  [82,90], symbolSize: 50, name: arr[8].name, label:label
        },
        {
            value:  [15,80], symbolSize: 45, name: arr[9].name, label:label
        },
        /*{
            value:  [15,10], symbolSize: 45, name: arr[10].name, label:label
        },
        {
            value:  [82,15], symbolSize: 45, name: arr[11].name, label:label
        }*/
    ];

    return data1;
};
var scatter = echarts.init(document.getElementById("scatter"));
var label = {
    normal: {
        show: true,
        textStyle: {
            fontSize: '16',
            color: '#000000'
        },
        position: 'inside',
        formatter: function(param) {
            return param.data.name;
        }
    }
};
var data1 = [
    {
        value: [50, 50], symbolSize: 140, name: '智能', label:label
    },
    {
        value: [35,60], symbolSize: 100, name: '节能', label:label
    },
    {
        value: [65,90], symbolSize: 85, name: '大数据', label:label
    },
    {
        value: [32,20], symbolSize: 75, name: 'AI', label:label
    },
    {
        value: [55,10], symbolSize: 70, name: 'VR', label:label
    },
    {
        value: [70,50], symbolSize: 65, name: '科技园', label: label
    },
    {
        value: [35,95], symbolSize: 60, name: '行业', label: label
    },
    {
        value: [50,100], symbolSize: 55, name: '创新', label:label
    },
    {
        value:  [82,90], symbolSize: 50, name: '融合', label:label
    },
    {
        value:  [15,80], symbolSize: 50, name: '大数据', label:label
    },
    /*{
        value:  [15,10], symbolSize: 50, name: '智能化', label:label
    },
    {
        value:  [82,15], symbolSize: 50, name: '变化', label:label
    }*/
];
var option = {
    xAxis: {
        show: false,
        type: 'value',
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        }
    },
    yAxis: {
        show: false,
        type: 'value',
        splitLine: {
            lineStyle: {
                type: 'dashed'
            }
        },
    },

    series: [{
        name: '热词',
        type: 'scatter',
        showEffectOn: 'emphasis',
        itemStyle: {
            normal: {
                color: "rgba(0,0,0,0)",
                borderWidth: 1,
                borderType: "solid",
                borderColor: "#093982",
                shadowBlur: 20,
                shadowColor: "#00c3f3",
                shadowOffsetX: 3,
                opacity: 1
            },
            emphasis: {
                color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                        {offset: 0, color: '#00a5fb'},
                        {offset: 0.5, color: '#00caf2'},
                        {offset: 1, color: '#00f0e8'}
                    ]
                ),
                shadowBlur: 0,
                borderWidth: 0,
                shadowOffsetX: 0,
            }
        },
        data: data1
    }]
};
scatter.setOption(option);
$(":radio").click(function(){
    console.log($(this).val());
    time = $(this).val();
    getKeyWordCloud(time);
});

function initPages(){
    $('.editindusinfo').on("click",function(i){
        var _id =  $(this).parents('.gradeX').find('td').eq(0).text();
        window.location.href="/apis/industryinfo/editIndustryInfo.html?id="+_id;
    });
    //删除一条数据
    $(".removeindusinfo").on("click",function(i){
        var id = $(this).parents('.gradeX').find('td').eq(0).text();
        deleteIndustryInfoById(id);
    });

};
function deleteIndustryInfoById(id){
    layer.confirm('确定要删除该数据？', {
        btn: ['确认','取消'] //按钮
    }, function(){
        $.ajax({
            async:false,
            url : "/apis/industryinfo/deleteIndustryInfoById.json?id="+id,
            success : function(res) {
                if(res.success){
                    layer.msg('成功删除', {icon: 1});
                    getIndustry(0,0);
                }else{
                    layer.msg(res.message, {icon: 2});
                }
            }
        });
    });
};
