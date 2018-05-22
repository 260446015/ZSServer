$("#uploadKeyword").click(function () {
    var uploadFile = $('<input type="file" name="" onchange="uploadKeyload(this)">');
    (function () {
        uploadFile.click();
    })();
})
function uploadKeyload(e) {
    var formData = new FormData();
    formData.append("fileName",e.files[0]);
    $.ajax({
        url: "/apis/atlas/updataAtlas.json",
        data: formData,
        type: "Post",
        dataType: "json",
        cache: false,//上传文件无需缓存
        processData: false,//用于对data参数进行序列化处理 这里必须false
        contentType: false, //必须
        success: function (result) {
            alert("上传" + (result.data == true ?"完成!":"失败!"));
        },
    })
}