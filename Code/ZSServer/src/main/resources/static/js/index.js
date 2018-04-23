$(function () {
    $(".ols").on("click", "li", function () {
        var index = $(this).index();
        $(".con").eq(index).addClass("block").siblings().removeClass("block")
    });
    $(".colse").on("click",function(){
        $(".atlasbox").hide()
        $(".content-show").show()
    }) 
    var echarts1 = echarts.init(document.getElementById("echarts1"));
    echarts1option = {
        series: [{
            type: 'graph',
            layout: 'none',
            symbolSize: 12,
            roam: false,
            focusNodeAdjacency:true,
            label: {
                normal: {
                    show: true,
                    position: 'bottom',
                    distance: 5
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff'
                }
            },
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 2,
                    curveness: .01,
                    color: '#009ad6'
                }
            },
            data: [
                {
                    name: '智能制造产业链',
                    x: 0,
                    y: 60
                }, {
                    name: '传感器',
                    x: -30,
                    y: 50
                },
                {
                    name: 'RFID',
                    x: -10,
                    y: 50
                }, {
                    name: '机器视觉',
                    x: 10,
                    y: 50
                },
                {
                    name: '智能芯片',
                    x: 30,
                    y: 50
                }, {
                    name: '传感器生产',
                    x: -40,
                    y: 40
                }, {
                    name: '传感识别技术',
                    x: -20,
                    y: 40
                }, {
                    name: '图像摄取硬件',
                    x: 10,
                    y: 40
                }, {
                    name: '图像处理技术',
                    x: 30,
                    y: 40
                },
            ],
            links: [{
                source: '智能制造产业链',
                target: '传感器'
            }, {
                source: '智能制造产业链',
                target: 'RFID'
            }, {
                source: '智能制造产业链',
                target: '机器视觉'
            }, {
                source: '智能制造产业链',
                target: '智能芯片'
            }, {
                source: '传感器',
                target: '传感器生产'
            }, {
                source: '传感器',
                target: '传感识别技术'
            }, {
                source: '机器视觉',
                target: '图像摄取硬件'
            }, {
                source: '机器视觉',
                target: '图像处理技术'
            }]
        }]
    };
    echarts1.setOption(echarts1option);
    
    echarts1.on("click", function (req) {
        $(".content-show").hide()
        var url = '/atlas/atlas';
        $(".atlasbox").show();
        var subject = req.name;
        console.log(subject);
        $.ajax({
            url:'http://localhost:8095/apis/atlas/keyNo.json?name='+req.name,
            dataType: 'json',
            success:function(res){
               var keyno=res.data;
               console.log(req.name)
               url = url + "?keyno="+keyno+"&name="+subject;
               $(".atlasbox iframe").attr("src",url)  
            }
        })   
    });

    var echarts2 = echarts.init(document.getElementById("echarts2"));
    echarts2option = {
        series: [{
            type: 'graph',
            layout: 'none',
            symbolSize: 12,
            roam: false,
            focusNodeAdjacency:true,
            label: {
                normal: {
                    show: true,
                    position: 'bottom',
                    distance: 5
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff'
                }
            },
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 2,
                    curveness: .01,
                    color: '#40E0D0'
                }
            },
            data: [
                {
                    name: '智能制造产业链',
                    x: 0,
                    y: 50
                }, {
                    name: '大数据',
                    x: -10,
                    y: 40
                },
                {
                    name: '云计算',
                    x: 0,
                    y: 40
                }, {
                    name: '工业物联网',
                    x: 10,
                    y: 40
                },
                {
                    name: '信息采集技术',
                    x: -10,
                    y: 30
                }, {
                    name: '信息智能化技术',
                    x: -5,
                    y: 30
                }, {
                    name: '工业以太网',
                    x:5,
                    y: 30
                }
            ],
            links: [{
                source: '智能制造产业链',
                target: '大数据'
            }, {
                source: '智能制造产业链',
                target: '云计算'
            }, {
                source: '智能制造产业链',
                target: '工业物联网'
            }, {
                source: '大数据',
                target: '信息采集技术'
            }, {
                source: '大数据',
                target: '信息智能化技术'
            }, {
                source: '工业物联网',
                target: '工业以太网'
            }]
        }]
    };
    echarts2.setOption(echarts2option);
    echarts2.on("click", function (req) {
        $(".content-show").hide()
        var url = '/atlas/atlasPage';
        $(".atlasbox").show();
        $.ajax({
            url:'/atlas/keyNo.json?name='+req.name,
            dataType: 'json',
            success:function(req){
               var keyno=req.data;
               url = url + "?keyno="+keyno;
               $(".atlasbox iframe").attr("src",url)  
            }
        })   
    });
  
    var echarts3 = echarts.init(document.getElementById("echarts3"));
    echarts3option = {
        series: [{
            type: 'graph',
            layout: 'none',
            symbolSize: 12,
            roam: false,
            focusNodeAdjacency:true,
            label: {
                normal: {
                    show: true,
                    position: 'bottom',
                    distance: 5
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff'
                }
            },
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 2,
                    curveness: .01,
                    color: '#1E90FF'
                }
            },
            data: [
                {
                    name: '智能制造产业链',
                    x: 10,
                    y: 60
                }, {
                    name: '工业机器人',
                    x: -20,
                    y: 40
                },
                {
                    name: '智能机床',
                    x: 0,
                    y: 40
                }, {
                    name: '自动化装备',
                    x: 20,
                    y: 40
                },
                {
                    name: '3D打印',
                    x: 40,
                    y: 40
                }, {
                    name: '工控系统',
                    x: -20,
                    y: 20
                }, {
                    name: '减速器',
                    x: -10,
                    y: 20
                }, {
                    name: '数控机床',
                    x: 0,
                    y: 20
                }, {
                    name: '数控系统',
                    x: 10,
                    y: 20
                }, {
                    name: '智能装备解决方案',
                    x: 20,
                    y: 20
                }, {
                    name: '系统自动化集成装备',
                    x: 35,
                    y: 20
                }, {
                    name: '伺候系统',
                    x: -30,
                    y: 0
                }, {
                    name: '控制系统',
                    x: -20,
                    y: 0
                }
            ],
            links: [{
                source: '智能制造产业链',
                target: '工业机器人'
            }, {
                source: '智能制造产业链',
                target: '智能机床'
            }, {
                source: '智能制造产业链',
                target: '自动化装备'
            }, {
                source: '智能制造产业链',
                target: '3D打印'
            }, {
                source: '工业机器人',
                target: '工控系统'
            }, {
                source: '工业机器人',
                target: '减速器'
            }, {
                source: '智能机床',
                target: '数控机床'
            }, {
                source: '智能机床',
                target: '数控系统'
            }, {
                source: '自动化装备',
                target: '智能装备解决方案'
            }, {
                source: '自动化装备',
                target: '系统自动化集成装备'
            }, {
                source: '工控系统',
                target: '伺候系统'
            }, {
                source: '工控系统',
                target: '控制系统'
            }]
        }]
    };
    echarts3.setOption(echarts3option);
    echarts3.on("click", function (req) {
        $(".content-show").hide()
        var url = '/atlas/atlasPage';
        $(".atlasbox").show();
        $.ajax({
            url:'/atlas/keyNo.json?name='+req.name,
            dataType: 'json',
            success:function(req){
               var keyno=req.data;
               url = url + "?keyno="+keyno;
               $(".atlasbox iframe").attr("src",url)  
            }
        })   
    });

    var echarts4 = echarts.init(document.getElementById("echarts4"));
    echarts4option = {
        series: [{
            type: 'graph',
            layout: 'none',
            symbolSize: 12,
            roam: false,
            focusNodeAdjacency:true,
            label: {
                normal: {
                    show: true,
                    position: 'bottom',
                    distance: 5
                }
            },
            itemStyle: {
                normal: {
                    color: '#fff'
                }
            },
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 2,
                    curveness: .01,
                    color: '#1E90FF'
                }
            },
            data: [
                {
                    name: '智能制造产业链',
                    x: 0,
                    y: 60
                }, {
                    name: '自动化生产线',
                    x: -20,
                    y: 50 
                },
                {
                    name: '智能工厂',
                    x: 20,
                    y: 50
                }, {
                    name: '自动化系统集成',
                    x: -30,
                    y: 30
                }, {
                    name: '自动化解决方案',
                    x: -10,
                    y: 30
                },{
                    name:'工业软件',
                    x:10,
                    y:30
                },{
                    name:'智能控制系统',
                    x:30,
                    y:30
                },{
                    name:'运营管理类',
                    x:-10,
                    y:10
                },{
                    name:'生产控制类',
                    x:10,
                    y:10
                },{
                    name:'研发设计类',
                    x:30,
                    y:10
                },{
                    name:'ERP',
                    x:-20,
                    y:0
                },{
                    name:'CRM',
                     x:-15,
                    y:0
                },{
                    name:'QM',
                     x:-10,
                    y:0
                },{
                    name:'SCM',
                     x:-5,
                    y:0
                },{
                    name:'MES',
                    x:5,
                    y:0
                },{
                    name:'SCADA',
                    x:15,
                    y:0
                },{
                    name:'CAD',
                    x:20,
                    y:0
                },{
                    name:'PPM',
                    x:30,
                    y:0
                },{
                    name:'PLM',
                    x:40,
                    y:0
                }
            ],
            links:[{
                source:'智能制造产业链',
                target:'自动化生产线'
            },{
                source:'智能制造产业链',
                target:'智能工厂'
            },{
                source:'自动化生产线',
                target:'自动化系统集成'
            },{
                source:'自动化生产线',
                target:'自动化解决方案'
            },{
                source:'智能工厂',
                target:'工业软件'
            },{
                source:'智能工厂',
                target:'智能控制系统'
            },{
                source:'工业软件',
                target:'运营管理类'
            },{
                source:'工业软件',
                target:'生产控制类'
            },{
                source:'工业软件',
                target:'研发设计类'
            },{
                source:'运营管理类',
                target:'ERP'
            },{
                source:'运营管理类',
                target:'CRM'
            },{
                source:'运营管理类',
                target:'QM'
            },{
                source:'运营管理类',
                target:'SCM'
            },{
                source:'生产控制类',
                target:'MES'
            },{
                source:'生产控制类',
                target:'SCADA'
            },{
                source:'研发设计类',
                target:'CAD'
            },{
                source:'研发设计类',
                target:'PPM'
            },{
                source:'研发设计类',
                target:'PLM'
            }]
        }]
    };
    echarts4.setOption(echarts4option);
    echarts4.on("click", function (req) {
        $(".content-show").hide()
        var url = '/atlas/atlasPage';
        $(".atlasbox").show();
        $.ajax({
            url:'/atlas/keyNo.json?name='+req.name,
            dataType: 'json',
            success:function(req){
               var keyno=req.data;
               url = url + "?keyno="+keyno;
               $(".atlasbox iframe").attr("src",url)  
            }
        })   
    });
})

