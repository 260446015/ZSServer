/**
 * Created by zhangxin on 2017/11/16.
 */
(function (win,doc) {
    var Alert = function (option) {
        var defaults = {
            dom: doc.body,
            flag: true,
            text: "关注成功",
            timer: 1500
        };
        this.options = $.extend({},defaults,option);
        this.el = this.options.dom;
    };
    Alert.prototype = {
        constructor:Alert,
        init:function () {
            var _self = this,
                _html = this.createHtml();
            _self.el.appendChild(_html);
        },
        createHtml:function () {
            var mask = doc.createElement("div"),
                p = doc.createElement("p");
                if(typeof this.options.flag == "boolean"){
                    mask.className = this.options.flag ? "alert-custom success" : "alert-custom error";
                }else if(typeof this.options.flag == "string"){
                    mask.className = "alert-custom "+this.options.flag;
                }
            if(this.options.text){
                p.innerText = this.options.text
            }
            mask.appendChild(p);
            return mask;
        },
        show:function () {
            var _this = this;
            _this.init();
            setTimeout(function () {
                _this.hide();
            },_this.options.timer);
        },
        hide:function () {
            this.el.querySelector(".alert-custom").style.display = "none";
            var thisMask = this.el.querySelector(".alert-custom");
            this.el.removeChild(thisMask);
        }
    };
    win.Alert = Alert;
}(window,document));