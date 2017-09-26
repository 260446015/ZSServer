/**
 * Created by Administrator on 2017/9/15 0015.
 */
//主入口文件
/**
 项目JS主入口
 以依赖Layui的layer和form模块为例
 **/
/*layui.config({
    dir: '../layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
    ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
    ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
    ,base: '' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
});*/
/*
 layui.define(['layer', 'form'], function(exports){
 var layer = layui.layer
 ,form = layui.form
 ,laypage = layui.laypage
 ,layedit = layui.layedit;

 layer.msg('Hello World');

 exports(['laypage', 'layedit'], function(){
 }); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
 });

 layui.use(['laypage', 'layedit'], function(laypage, layedit){

 //使用分页
 laypage();

 //建立编辑器
 layedit.build();
 });*/

layui.use(['element','form'], function(){
    var element = layui.element;
    var form = layui.form;
    //一些事件监听
    element.on('tab(demo)', function(data){
        console.log(data);
    });
    //有些表单元素可能是动态插入的
    form.render();//更新全部
    form.render('select'); //更新select
    form.render(null, 'test1'); //更新 lay-filter="test1" 所在容器内的全部表单状态
    form.render('select', 'test2'); //更新 lay-filter="test2" 所在容器内的全部 select 状态
});
