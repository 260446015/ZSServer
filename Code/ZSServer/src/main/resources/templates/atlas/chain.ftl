<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Access-Control-Allow-Origin" content="*" />
    <title>Document</title>
    <link rel="stylesheet" href="/css/base.css">
    <style>
        body{
        background: url(/plugins/tupu/images/bg.jpg);
        background-size: cover;
        }
        .bigbox {
            width: 1000px;
            height: 500px;
            margin: 20px auto;
        }

        .content-show {
            width: 650px;
            height: 500px;
            overflow: hidden;
        }
        .conteny-list{
            width: 650px;
            height: 500px;
            overflow: hidden;
        } 

        .content-center {
            width: 250px;
            height: 500px;
        }

        .content-text {
            width: 100px;
            height: 500px;
        }

        .content-list {
            margin: 0 auto;
            margin-top: 100px;
        }

        .content-list li {
            width: 100px;
            line-height: 50px;
            text-align: center;
            background: #4ea2f0;
            color: #fff;
            margin-top: 15px;
        }

        .con {
            display: none;
        }

        .block {
            display: block;
        }
        #MainD3 svg {
            width: 650px;
            height: 500px;
            background: #fff;
            z-index: 9999;
        }
        .atlasbox{
            width: 1000px;
            height: 620px;
            position: absolute;
            left: 0 ;
            top: 0;
            display: none;
            background-color: rgb(0,0,0,0);
            background: transparent;
        }
        #iframeId{
            width: 100%;
            height: 100%;
        }
        .colse{
            position: absolute;
            right: 0;
            top: 50px;
            width: 40px;
            line-height: 40px;
            background: #fff;
            color: #000;
            font-weight: bolder;
            border-radius: 100%;
            border: 1px solid #ccc;
            font-size: 20px;
            text-align: center;
        }
    </style>
</head>

<body>
<!-- <form id="form" enctype="multipart/form-data;charset=utf-8">
    <input type="file" name="fileName" onchange="uploadFile()">
</form> -->
<div class="bigbox">
    <div class="content-show fl">
        <div class="conteny-list echarts1 con block" id="echarts1"></div>
        <div class="conteny-list echarts2 con" id="echarts2"></div>
        <div class="conteny-list echarts3 con" id="echarts3"></div>
        <div class="conteny-list echarts4 con" id="echarts4"></div>
    </div>
    <div class="content-text fr">
        <ol class="content-list fr ols">
            <li>感知层</li>
            <li>网络层</li>
            <li>执行层</li>
            <li>应用层</li>
        </ol>
    </div>
</div>
<div class="atlasbox">
    <iframe src="" frameborder="0" id="iframeId"></iframe>
    <span class="colse">X</span>
</div>
<script src="/js/jquery-1.10.2.min.js"></script>
<script src="/js/echarts.min.js"></script>
<script src="/js/index.js"></script>
<script>
    function uploadFile(){
        var uploadFile = new FormData($("#form")[0]);
        $.ajax({
             url: "/atlas/updataAtlas",
             type: "POST",
             data: uploadFile,
             contentType: false,
             processData: false,
             success:function(res){
                 if(res.data != null){
                     layer.msg(res.data, {icon: 1});
                 }else{
                     layer.msg(res.message, {icon: 2});
                 }
                 var param ={type:type,pageSize:pageSize,pageNumber:pageNumber};
                   getType(param);
             }
         });

    };

</script>
</body>

</html>