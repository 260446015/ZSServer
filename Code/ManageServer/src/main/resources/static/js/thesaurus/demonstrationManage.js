$(function () {
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        title: {text: '人民的名义关系图谱'},
        tooltip: {
            formatter: function (x) {
                return x.data.des;
            }
        },
        series: [
            {
                type: 'graph',
                layout: 'force',
                symbolSize: 80,
                roam: true,
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [4, 10],
                edgeLabel: {
                    normal: {
                        textStyle: {
                            fontSize: 20
                        }
                    }
                },
                force: {
                    repulsion: 2500,
                    edgeLength: [10, 50]
                },
                draggable: true,
                itemStyle: {
                    normal: {
                        color: '#4b565b'
                    }
                },
                lineStyle: {
                    normal: {
                        width: 2,
                        color: '#4b565b'

                    }
                },
                edgeLabel: {
                    normal: {
                        show: true,
                        formatter: function (x) {
                            return x.data.name;
                        }
                    }
                },
                label: {
                    normal: {
                        show: true,
                        textStyle: {}
                    }
                },
                data: [{"des":"工业","symbolSize":100,"name":"工业"},{"des":"自动生产线","name":"自动生产线","itemStyle":{"normal":{"color":"red"}}},{"des":"区块链","symbolSize":100,"name":"区块链"},{"des":"经济激励","name":"经济激励","itemStyle":{"normal":{"color":"red"}}},{"des":"机器人","symbolSize":100,"name":"机器人"},{"des":"传感器","name":"传感器","itemStyle":{"normal":{"color":"red"}}},{"des":"经济","symbolSize":100,"name":"经济"},{"des":"技术","name":"技术","itemStyle":{"normal":{"color":"red"}}},{"des":"人工智能","name":"人工智能","itemStyle":{"normal":{"color":"red"}}},{"des":"智能制造","symbolSize":100,"name":"智能制造"},{"des":"系统","name":"系统","itemStyle":{"normal":{"color":"red"}}},{"des":"解决方案","symbolSize":100,"name":"解决方案"},{"des":"信息化","name":"信息化","itemStyle":{"normal":{"color":"red"}}},{"des":"自动化","symbolSize":100,"name":"自动化"},{"des":"信息","name":"信息","itemStyle":{"normal":{"color":"red"}}},{"des":"电子","symbolSize":100,"name":"电子"},{"des":"制造","name":"制造","itemStyle":{"normal":{"color":"red"}}},{"des":"工程","name":"工程","itemStyle":{"normal":{"color":"red"}}},{"des":"云计算","symbolSize":100,"name":"云计算"},{"des":"智能","name":"智能","itemStyle":{"normal":{"color":"red"}}},{"des":"无人机","symbolSize":100,"name":"无人机"},{"des":"智能化","name":"智能化","itemStyle":{"normal":{"color":"red"}}},{"des":"农业","name":"农业","itemStyle":{"normal":{"color":"red"}}},{"des":"大数据","name":"大数据","itemStyle":{"normal":{"color":"red"}}},{"des":"语言识别","name":"语言识别","itemStyle":{"normal":{"color":"red"}}},{"des":"数据","symbolSize":100,"name":"数据"},{"des":"制造业","name":"制造业","itemStyle":{"normal":{"color":"red"}}},{"des":"信息技术","symbolSize":100,"name":"信息技术"},{"des":"信息传递过程","name":"信息传递过程","itemStyle":{"normal":{"color":"red"}}},{"des":"铁路","name":"铁路","itemStyle":{"normal":{"color":"red"}}},{"des":"教育","name":"教育","itemStyle":{"normal":{"color":"red"}}},{"des":"司法","symbolSize":100,"name":"司法"},{"des":"法律","name":"法律","itemStyle":{"normal":{"color":"red"}}},{"des":"物联网","name":"物联网","itemStyle":{"normal":{"color":"red"}}},{"des":"环保","symbolSize":100,"name":"环保"},{"des":"装备","symbolSize":100,"name":"装备"},{"des":"服务","name":"服务","itemStyle":{"normal":{"color":"red"}}},{"des":"科学技术","symbolSize":100,"name":"科学技术"},{"des":"安全","symbolSize":100,"name":"安全"},{"des":"物流","name":"物流","itemStyle":{"normal":{"color":"red"}}},{"des":"数控系统","symbolSize":100,"name":"数控系统"},{"des":"订单处理","name":"订单处理","itemStyle":{"normal":{"color":"red"}}},{"des":"文化","symbolSize":100,"name":"文化"},{"des":"文字","name":"文字","itemStyle":{"normal":{"color":"red"}}},{"des":"智能家居","name":"智能家居","itemStyle":{"normal":{"color":"red"}}},{"des":"健康","symbolSize":100,"name":"健康"},{"des":"食品","name":"食品","itemStyle":{"normal":{"color":"red"}}},{"des":"芯片","name":"芯片","itemStyle":{"normal":{"color":"red"}}},{"des":"智能机床","symbolSize":100,"name":"智能机床"},{"des":"控制系统","symbolSize":100,"name":"控制系统"},{"des":"导航系统","name":"导航系统","itemStyle":{"normal":{"color":"red"}}},{"des":"医疗信息","name":"医疗信息","itemStyle":{"normal":{"color":"red"}}},{"des":"智能家电控制","name":"智能家电控制","itemStyle":{"normal":{"color":"red"}}},{"des":"机器视觉","symbolSize":100,"name":"机器视觉"},{"des":"汽车","symbolSize":100,"name":"汽车"},{"des":"SCADA系统","symbolSize":100,"name":"SCADA系统"},{"des":"知识","name":"知识","itemStyle":{"normal":{"color":"red"}}},{"des":"环境","symbolSize":100,"name":"环境"},{"des":"卫星","symbolSize":100,"name":"卫星"},{"des":"微电子技术","name":"微电子技术","itemStyle":{"normal":{"color":"red"}}},{"des":"软件","name":"软件","itemStyle":{"normal":{"color":"red"}}},{"des":"互联网","symbolSize":100,"name":"互联网"},{"des":"信息技术和新的","name":"信息技术和新的","itemStyle":{"normal":{"color":"red"}}},{"des":"产品制造","name":"产品制造","itemStyle":{"normal":{"color":"red"}}},{"des":"工业物联网","symbolSize":100,"name":"工业物联网"},{"des":"原料采购","name":"原料采购","itemStyle":{"normal":{"color":"red"}}},{"des":"网络技术发展","name":"网络技术发展","itemStyle":{"normal":{"color":"red"}}},{"des":"工艺","name":"工艺","itemStyle":{"normal":{"color":"red"}}},{"des":"光电子技术","name":"光电子技术","itemStyle":{"normal":{"color":"red"}}},{"des":"智能机器人","symbolSize":100,"name":"智能机器人"},{"des":"零售","name":"零售","itemStyle":{"normal":{"color":"red"}}},{"des":"机械","name":"机械","itemStyle":{"normal":{"color":"red"}}},{"des":"习俗和任何人","name":"习俗和任何人","itemStyle":{"normal":{"color":"red"}}},{"des":"","name":"","itemStyle":{"normal":{"color":"red"}}},{"des":"智能制造技术和智能制造系统","name":"智能制造技术和智能制造系统","itemStyle":{"normal":{"color":"red"}}},{"des":"信仰","name":"信仰","itemStyle":{"normal":{"color":"red"}}},{"des":"电商","symbolSize":100,"name":"电商"},{"des":"交通","symbolSize":100,"name":"交通"},{"des":"仓储运输","name":"仓储运输","itemStyle":{"normal":{"color":"red"}}},{"des":"电影","name":"电影","itemStyle":{"normal":{"color":"red"}}},{"des":"刀具","name":"刀具","itemStyle":{"normal":{"color":"red"}}},{"des":"自然环境和社会环境","name":"自然环境和社会环境","itemStyle":{"normal":{"color":"red"}}},{"des":"射频识别技术","symbolSize":100,"name":"射频识别技术"},{"des":"联网","name":"联网","itemStyle":{"normal":{"color":"red"}}},{"des":"自然语言处理","name":"自然语言处理","itemStyle":{"normal":{"color":"red"}}},{"des":"公路","symbolSize":100,"name":"公路"},{"des":"航空","symbolSize":100,"name":"航空"},{"des":"批发经营","name":"批发经营","itemStyle":{"normal":{"color":"red"}}},{"des":"道德","name":"道德","itemStyle":{"normal":{"color":"red"}}},{"des":"语言","name":"语言","itemStyle":{"normal":{"color":"red"}}},{"des":"专家系统","name":"专家系统","itemStyle":{"normal":{"color":"red"}}},{"des":"管理","name":"管理","itemStyle":{"normal":{"color":"red"}}},{"des":"生态","symbolSize":100,"name":"生态"},{"des":"自然科学","name":"自然科学","itemStyle":{"normal":{"color":"red"}}},{"des":"计算机硬件","name":"计算机硬件","itemStyle":{"normal":{"color":"red"}}},{"des":"新能源","name":"新能源","itemStyle":{"normal":{"color":"red"}}},{"des":"图像识别","name":"图像识别","itemStyle":{"normal":{"color":"red"}}},{"des":"装备或系统","name":"装备或系统","itemStyle":{"normal":{"color":"red"}}},{"des":"成像系统","name":"成像系统","itemStyle":{"normal":{"color":"red"}}},{"des":"信息技术基础设施","name":"信息技术基础设施","itemStyle":{"normal":{"color":"red"}}},{"des":"设计","name":"设计","itemStyle":{"normal":{"color":"red"}}},{"des":"金融","symbolSize":100,"name":"金融"},{"des":"芯片设计","name":"芯片设计","itemStyle":{"normal":{"color":"red"}}},{"des":"法律制度","name":"法律制度","itemStyle":{"normal":{"color":"red"}}},{"des":"射频技术和嵌入式技术","name":"射频技术和嵌入式技术","itemStyle":{"normal":{"color":"red"}}},{"des":"电力","symbolSize":100,"name":"电力"},{"des":"通信","name":"通信","itemStyle":{"normal":{"color":"red"}}},{"des":"计算机技术","name":"计算机技术","itemStyle":{"normal":{"color":"red"}}},{"des":"智慧","name":"智慧","itemStyle":{"normal":{"color":"red"}}},{"des":"艺术","name":"艺术","itemStyle":{"normal":{"color":"red"}}},{"des":"新材料和","name":"新材料和","itemStyle":{"normal":{"color":"red"}}},{"des":"医疗","name":"医疗","itemStyle":{"normal":{"color":"red"}}},{"des":"信息存储","name":"信息存储","itemStyle":{"normal":{"color":"red"}}}],
                links: [{"name":"维修","source":"工业","target":"自动生产线"},{"name":"包含","source":"区块链","target":"经济激励"},{"name":"包含","source":"机器人","target":"传感器"},{"name":"讲","source":"经济","target":"技术"},{"name":"之一","source":"技术","target":"人工智能"},{"name":"实现模式","source":"智能制造","target":"系统"},{"name":"发展","source":"解决方案","target":"信息化"},{"name":"目标","source":"自动化","target":"信息"},{"name":"出","source":"电子","target":"制造"},{"name":"领域","source":"自动化","target":"工程"},{"name":"处理","source":"云计算","target":"智能"},{"name":"不得","source":"无人机","target":"系统"},{"name":"精益化","source":"智能制造","target":"智能化"},{"name":"机器人、机器人","source":"机器人","target":"农业"},{"name":"发展","source":"工业","target":"经济"},{"name":"不断","source":"自动化","target":"技术"},{"name":"又","source":"信息","target":"技术"},{"name":"技术","source":"制造","target":"自动化"},{"name":"云计算","source":"技术","target":"大数据"},{"name":"包含","source":"人工智能","target":"语言识别"},{"name":"回暖态势","source":"数据","target":"制造业"},{"name":"技术","source":"信息","target":"制造"},{"name":"包含","source":"信息技术","target":"信息传递过程"},{"name":"工程","source":"工程","target":"铁路"},{"name":"能否","source":"系统","target":"经济"},{"name":"已","source":"信息化","target":"教育"},{"name":"应该","source":"人工智能","target":"系统"},{"name":"处理","source":"司法","target":"法律"},{"name":"建模","source":"系统","target":"机器人"},{"name":"技术","source":"技术","target":"物联网"},{"name":"中国","source":"环保","target":"法律"},{"name":"称为","source":"装备","target":"无人机"},{"name":"国家大数据发展战略","source":"数据","target":"服务"},{"name":"本质、形式、特点、作用","source":"科学技术","target":"法律"},{"name":"应当","source":"安全","target":"无人机"},{"name":"攻关","source":"装备","target":"系统"},{"name":"已","source":"服务","target":"物流"},{"name":"产业","source":"数控系统","target":"制造"},{"name":"包含","source":"制造业","target":"订单处理"},{"name":"包含","source":"文化","target":"文字"},{"name":"主动性动作","source":"传感器","target":"智能家居"},{"name":"包含","source":"文化","target":"技术"},{"name":"化学工业","source":"健康","target":"食品"},{"name":"爱好者论坛","source":"教育","target":"数据"},{"name":"机器","source":"机器人","target":"自动化"},{"name":"集成电路","source":"信息技术","target":"传感器"},{"name":"混乱","source":"装备","target":"铁路"},{"name":"已","source":"技术","target":"芯片"},{"name":"包含","source":"智能机床","target":"物流"},{"name":"包含","source":"控制系统","target":"导航系统"},{"name":"平台","source":"系统","target":"信息"},{"name":"包含","source":"信息","target":"医疗信息"},{"name":"包含","source":"智能","target":"传感器"},{"name":"就是","source":"系统","target":"技术"},{"name":"提供","source":"工业","target":"农业"},{"name":"包含","source":"智能","target":"智能家电控制"},{"name":"都","source":"智能家居","target":"系统"},{"name":"逐渐","source":"机器视觉","target":"技术"},{"name":"质量","source":"汽车","target":"装备"},{"name":"方式","source":"芯片","target":"数据"},{"name":"教育","source":"信息技术","target":"信息化"},{"name":"信息处理","source":"系统","target":"制造"},{"name":"连接","source":"SCADA系统","target":"系统"},{"name":"包含","source":"文化","target":"知识"},{"name":"自然科学、社会科学","source":"环境","target":"农业"},{"name":"包含","source":"物联网","target":"电子"},{"name":"施建难度、运输能力","source":"技术","target":"铁路"},{"name":"CAD、CAP、CAE和NC编程","source":"数据","target":"系统"},{"name":"装备和产品","source":"制造","target":"智能"},{"name":"显示","source":"卫星","target":"数据"},{"name":"包含","source":"信息技术","target":"微电子技术"},{"name":"环节","source":"技术","target":"系统"},{"name":"包含","source":"系统","target":"导航系统"},{"name":"需求","source":"信息化","target":"信息"},{"name":"包含","source":"智能制造","target":"软件"},{"name":"通过","source":"无人机","target":"安全"},{"name":"增加","source":"互联网","target":"服务"},{"name":"一定","source":"系统","target":"信息化"},{"name":"包含","source":"技术","target":"信息技术和新的"},{"name":"突破","source":"技术","target":"科学技术"},{"name":"包含","source":"制造业","target":"产品制造"},{"name":"将","source":"技术","target":"服务"},{"name":"车架与车桥","source":"系统","target":"汽车"},{"name":"嵌入式(软件","source":"工业物联网","target":"自动化"},{"name":"武器","source":"无人机","target":"装备"},{"name":"可","source":"卫星","target":"无人机"},{"name":"包含","source":"制造业","target":"原料采购"},{"name":"具有","source":"传感器","target":"数据"},{"name":"全","source":"技术","target":"机器人"},{"name":"%","source":"制造","target":"装备"},{"name":")","source":"物流","target":"服务"},{"name":"无污染食品","source":"食品","target":"安全"},{"name":"新阶段","source":"工业","target":"智能化"},{"name":"关键技术之一","source":"数据","target":"物联网"},{"name":"包含","source":"信息技术","target":"网络技术发展"},{"name":"领域","source":"制造","target":"制造业"},{"name":"源于","source":"智能制造","target":"人工智能"},{"name":"包含","source":"智能机床","target":"工艺"},{"name":"结合","source":"信息","target":"互联网"},{"name":"包含","source":"信息技术","target":"光电子技术"},{"name":"成本","source":"环保","target":"环境"},{"name":"人类","source":"智能机器人","target":"智能"},{"name":"包含","source":"制造业","target":"零售"},{"name":"包含","source":"智能制造","target":"机械"},{"name":"具有","source":"制造业","target":"智能化"},{"name":"大力","source":"装备","target":"技术"},{"name":"如","source":"机器人","target":"技术"},{"name":"包含","source":"智能制造","target":"电子"},{"name":"区位仪表","source":"卫星","target":"工业"},{"name":"本身","source":"人工智能","target":"智能"},{"name":"包含","source":"文化","target":"习俗和任何人"},{"name":"包含","source":"技术","target":""},{"name":"制度","source":"法律","target":"教育"},{"name":"包含","source":"智能制造","target":"智能制造技术和智能制造系统"},{"name":"进行","source":"机器人","target":"信息"},{"name":"装备","source":"智能制造","target":"制造"},{"name":"还","source":"农业","target":"文化"},{"name":"来源","source":"物联网","target":"信息"},{"name":"意义","source":"技术","target":"互联网"},{"name":"应用于","source":"物联网","target":"技术"},{"name":"旨在","source":"解决方案","target":"技术"},{"name":"实现","source":"经济","target":"系统"},{"name":"发展","source":"装备","target":"工程"},{"name":"Elektro","source":"制造","target":"机器人"},{"name":"转型","source":"大数据","target":"服务"},{"name":"包含","source":"文化","target":"信仰"},{"name":"造成","source":"环境","target":"传感器"},{"name":"更新","source":"数据","target":"信息"},{"name":"研制","source":"制造","target":"智能机器人"},{"name":"无需","source":"技术","target":"数据"},{"name":"搬","source":"自动化","target":"机器人"},{"name":"满足","source":"服务","target":"解决方案"},{"name":"还是","source":"电商","target":"无人机"},{"name":"也","source":"系统","target":"智能"},{"name":"迅速","source":"机器人","target":"工业"},{"name":"进行","source":"传感器","target":"环境"},{"name":"推进","source":"传感器","target":"技术"},{"name":"词目","source":"交通","target":"信息"},{"name":"造成","source":"机器人","target":"互联网"},{"name":"包含","source":"制造业","target":"仓储运输"},{"name":"系统","source":"机器人","target":"控制系统"},{"name":"工艺","source":"传感器","target":"制造"},{"name":"我","source":"机器人","target":"电影"},{"name":"超声波","source":"机器人","target":"装备"},{"name":"包含","source":"智能机床","target":"刀具"},{"name":"新的技术科学","source":"智能","target":"系统"},{"name":"控制","source":"传感器","target":"信息"},{"name":"例如","source":"人工智能","target":"机器人"},{"name":"包含","source":"环境","target":"自然环境和社会环境"},{"name":"物质","source":"食品","target":"工业"},{"name":"设备","source":"铁路","target":"系统"},{"name":"当前","source":"工业","target":"物联网"},{"name":")","source":"智能","target":"人工智能"},{"name":"利用","source":"射频识别技术","target":"系统"},{"name":"防止","source":"经济","target":"安全"},{"name":"不论","source":"制造","target":"服务"},{"name":"宣传教育","source":"法律","target":"经济"},{"name":"包含","source":"智能机床","target":"联网"},{"name":"包含","source":"人工智能","target":"自然语言处理"},{"name":"组成部分","source":"物联网","target":"信息技术"},{"name":"数据所有者","source":"云计算","target":"数据"},{"name":"也","source":"互联网","target":"解决方案"},{"name":"包含","source":"智能","target":"软件"},{"name":"功能角度","source":"制造","target":"系统"},{"name":"能","source":"数据","target":"射频识别技术"},{"name":"公路","source":"公路","target":"汽车"},{"name":"研制","source":"技术","target":"无人机"},{"name":"内涵","source":"信息","target":"数据"},{"name":"水平","source":"航空","target":"科学技术"},{"name":"可以","source":"教育","target":"环境"},{"name":"列入","source":"系统","target":"卫星"},{"name":"方面","source":"服务","target":"环境"},{"name":"保障","source":"智能制造","target":"装备"},{"name":"为","source":"服务","target":"技术"},{"name":"生活","source":"人工智能","target":"信息技术"},{"name":"包含","source":"制造业","target":"批发经营"},{"name":"读写","source":"系统","target":"数据"},{"name":"技术","source":"智能化","target":"云计算"},{"name":"方便","source":"工程","target":"服务"},{"name":"电子地图","source":"物流","target":"信息"},{"name":"事物","source":"汽车","target":"智能化"},{"name":"包含","source":"文化","target":"道德"},{"name":"进行","source":"智能机床","target":"信息"},{"name":"进行","source":"芯片","target":"技术"},{"name":"包含","source":"物联网","target":"软件"},{"name":"总是","source":"环境","target":"系统"},{"name":"挑战","source":"互联网","target":"技术"},{"name":"进行","source":"互联网","target":"信息"},{"name":"中国空间技术研究院","source":"数据","target":"卫星"},{"name":"跳过","source":"区块链","target":"技术"},{"name":"包含","source":"文化","target":"语言"},{"name":"随时","source":"控制系统","target":"信息"},{"name":"产业","source":"大数据","target":"工业"},{"name":"途径","source":"信息技术","target":"教育"},{"name":"替代","source":"环境","target":"机器视觉"},{"name":"研制、制造过程","source":"技术","target":"传感器"},{"name":"卫星","source":"卫星","target":"系统"},{"name":"行为主义","source":"环境","target":"机器人"},{"name":"就","source":"工业","target":"机器人"},{"name":"包含","source":"人工智能","target":"专家系统"},{"name":"阶段","source":"云计算","target":"物联网"},{"name":"装备","source":"智能","target":"制造"},{"name":"律","source":"电影","target":"机器人"},{"name":"感知","source":"机器人","target":"安全"},{"name":"智商","source":"智能","target":"机器人"},{"name":"信息控制","source":"技术","target":"自动化"},{"name":"航空航天","source":"技术","target":"汽车"},{"name":"能力","source":"云计算","target":"技术"},{"name":"共","source":"汽车","target":"数据"},{"name":"基点","source":"电商","target":"服务"},{"name":"很","source":"物联网","target":"射频识别技术"},{"name":"包含","source":"信息技术","target":"管理"},{"name":"应用拓展","source":"物联网","target":"互联网"},{"name":"如","source":"生态","target":"系统"},{"name":"正","source":"技术","target":"信息化"},{"name":"包含","source":"智能","target":"电子"},{"name":"关怀","source":"教育","target":"安全"},{"name":"收集、传递、处理、执行","source":"物联网","target":"智能化"},{"name":"装有","source":"制造","target":"汽车"},{"name":"效率","source":"互联网","target":"教育"},{"name":"有效地","source":"大数据","target":"技术"},{"name":"过程","source":"芯片","target":"制造"},{"name":"娜捷","source":"技术","target":"教育"},{"name":"包含","source":"智能","target":"机械"},{"name":"获取","source":"信息技术","target":"信息"},{"name":"包含","source":"文化","target":"自然科学"},{"name":"包含","source":"信息技术","target":"技术"},{"name":"进行","source":"无人机","target":"卫星"},{"name":"业","source":"制造业","target":"智能制造"},{"name":"应用","source":"技术","target":"机器视觉"},{"name":"测试系统","source":"机器视觉","target":"智能"},{"name":"应用软件","source":"自动化","target":"智能化"},{"name":"包含","source":"智能制造","target":"计算机硬件"},{"name":"范畴","source":"信息技术","target":"物联网"},{"name":"包含","source":"技术","target":"新能源"},{"name":"工业机器人","source":"制造","target":"环境"},{"name":"分布式处理、分布式数据库、云存储、虚拟化技术","source":"大数据","target":"云计算"},{"name":"提供","source":"智能化","target":"智能制造"},{"name":"应用","source":"技术","target":"卫星"},{"name":"可以","source":"数据","target":"大数据"},{"name":"影响","source":"人工智能","target":"经济"},{"name":"首要","source":"物联网","target":"安全"},{"name":"不","source":"智能机器人","target":"环境"},{"name":"包含","source":"人工智能","target":"图像识别"},{"name":"网络","source":"制造","target":"智能化"},{"name":"人","source":"环境","target":"安全"},{"name":"包含","source":"信息","target":"装备或系统"},{"name":"包含","source":"系统","target":"成像系统"},{"name":"包含","source":"射频识别技术","target":"信息技术基础设施"},{"name":"运输","source":"无人机","target":"交通"},{"name":"包含","source":"文化","target":"法律"},{"name":"包含","source":"制造业","target":"设计"},{"name":"可","source":"无人机","target":"数据"},{"name":"不仅","source":"汽车","target":"机器人"},{"name":"数据","source":"信息","target":"区块链"},{"name":"“血脉”","source":"金融","target":"经济"},{"name":"包含","source":"制造","target":"芯片设计"},{"name":"提供","source":"无人机","target":"农业"},{"name":"突破","source":"工程","target":"技术"},{"name":"设计制作","source":"智能机器人","target":"机器人"},{"name":"服务","source":"服务","target":"区块链"},{"name":"课题","source":"环境","target":"智能机器人"},{"name":"能力","source":"工程","target":"制造"},{"name":"生产","source":"铁路","target":"安全"},{"name":"可以","source":"技术","target":"云计算"},{"name":"研究","source":"机器人","target":"系统"},{"name":"得到","source":"科学技术","target":"农业"},{"name":"世界信息技术领域","source":"制造","target":"工业"},{"name":"包含","source":"法律","target":"法律制度"},{"name":"应用","source":"智能家居","target":"物联网"},{"name":"教育、训练","source":"教育","target":"系统"},{"name":"迅速","source":"信息技术","target":"智能化"},{"name":"专家系统","source":"系统","target":"智能机器人"},{"name":"这","source":"科学技术","target":"技术"},{"name":"化","source":"信息化","target":"智能化"},{"name":"投入","source":"技术","target":"装备"},{"name":"月","source":"教育","target":"法律"},{"name":"表现","source":"人工智能","target":"技术"},{"name":"也","source":"环境","target":"信息"},{"name":"系统控制","source":"自动化","target":"系统"},{"name":"工场","source":"技术","target":"工业"},{"name":"过程","source":"人工智能","target":"信息"},{"name":"加工","source":"智能制造","target":"食品"},{"name":"提升","source":"制造业","target":"数据"},{"name":"工作顺序和操作内容","source":"信息","target":"机器人"},{"name":"基金","source":"装备","target":"制造业"},{"name":"滚动轴承","source":"自动生产线","target":"汽车"},{"name":"飞跃","source":"机器人","target":"人工智能"},{"name":"包含","source":"技术","target":"射频技术和嵌入式技术"},{"name":"自动","source":"人工智能","target":"工程"},{"name":"传导","source":"信息","target":"服务"},{"name":"彻底","source":"电力","target":"互联网"},{"name":"发展、","source":"智能机器人","target":"技术"},{"name":"包含","source":"物联网","target":"通信"},{"name":"者","source":"智能机器人","target":"制造"},{"name":"应付","source":"人工智能","target":"环境"},{"name":"发展","source":"系统","target":"工程"},{"name":"组成","source":"系统","target":"无人机"},{"name":"包含","source":"智能","target":"计算机硬件"},{"name":"包含","source":"信息技术","target":"计算机技术"},{"name":"族","source":"文化","target":"智慧"},{"name":"[","source":"传感器","target":"智能"},{"name":"包含","source":"信息技术","target":"通信"},{"name":"建筑工地","source":"公路","target":"铁路"},{"name":"使","source":"信息","target":"系统"},{"name":"判断","source":"智能家居","target":"技术"},{"name":"化","source":"物流","target":"智能化"},{"name":"可操作性","source":"传感器","target":"机器人"},{"name":"隐私","source":"金融","target":"信息"},{"name":"领秀","source":"无人机","target":"航空"},{"name":"检测器","source":"健康","target":"智能"},{"name":"政府","source":"互联网","target":"大数据"},{"name":"包含","source":"文化","target":"艺术"},{"name":"都","source":"大数据","target":"数据"},{"name":"包含","source":"技术","target":"新材料和"},{"name":"出","source":"技术","target":"制造"},{"name":"手机","source":"智能制造","target":"机器人"},{"name":"帮助","source":"机器人","target":"医疗"},{"name":"中国空间技术研究院","source":"工程","target":"卫星"},{"name":"制造，信息咨询服务","source":"信息","target":"装备"},{"name":"飞行速度","source":"服务","target":"航空"},{"name":"任务管理服务","source":"人工智能","target":"互联网"},{"name":"能","source":"系统","target":"互联网"},{"name":"包含","source":"技术","target":"工程"},{"name":"发展","source":"信息技术","target":"互联网"},{"name":"地","source":"工业","target":"制造"},{"name":"工具","source":"法律","target":"服务"},{"name":"转换","source":"安全","target":"数据"},{"name":"包含","source":"智能机床","target":"信息存储"},{"name":"得到","source":"物联网","target":"系统"},{"name":"如","source":"智能制造","target":"传感器"},{"name":"本身","source":"农业","target":"无人机"},{"name":"人类","source":"机器人","target":"智能"},{"name":"又称","source":"机器人","target":"智能机器人"},{"name":"革新以及更好","source":"智能","target":"制造业"},{"name":"能量","source":"技术","target":"电子"},{"name":"意义","source":"信息","target":"人工智能"},{"name":"认识","source":"系统","target":"安全"},{"name":"每个","source":"物联网","target":"传感器"},{"name":"总线","source":"技术","target":"环境"},{"name":"视频","source":"环境","target":"互联网"}]
            }
        ]
    };
    myChart.setOption(option);
})