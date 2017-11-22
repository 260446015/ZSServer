/**
 * Created by zhangxin on 2017/11/22.
 */
$(function () {
    AMapUI.setDomLibrary($);

    //加载BasicControl，loadUI的路径参数为模块名中 'ui/' 之后的部分
    AMapUI.loadUI(['control/BasicControl'], function(BasicControl) {

        var map = new AMap.Map('map', {
            // mapStyle: 'amap://styles/f369a26753f3255e722f040f6bb304df'
            resizeEnable: true,
            zoom:13,
            // center: [116.397428, 39.90923]
        });
        //根据 IP 定位到当前城市
        setTimeout(function () {
            var citysearch = new AMap.CitySearch();
            //自动获取用户IP，返回当前城市
            citysearch.getLocalCity(function(status, result) {
                if (status === 'complete' && result.info === 'OK') {
                    if (result && result.city && result.bounds) {
                        var cityinfo = result.city;
                        var citybounds = result.bounds;
                        document.getElementById('tip').innerHTML = '您当前所在城市：'+cityinfo;
                        //地图显示当前城市
                        map.setBounds(citybounds);
                    }
                } else {
                    document.getElementById('tip').innerHTML = result.info;
                }
            });
        },2000);
        //缩放控件
        map.addControl(new BasicControl.Zoom({
            position: 'rb', //left top，左上角
            showZoomNum: false //显示zoom值
        }));
    });
});
