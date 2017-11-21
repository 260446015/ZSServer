/**
 * Created by zhangxin on 2017/10/26.
 */

(function (win,doc) {
    var Loading = function (option) {
        var defaults = {
            dom: doc.body,
            isfullscreen: false,
            text: ""
        };
        this.options = $.extend({},defaults,option);
        if(this.options.isfullscreen){
            this.el = doc.body;
        }else{
            this.el = this.options.dom || doc.body;
        }
    };
    Loading.prototype = {
        constructor:Loading,
        init:function () {
            var _self = this,
            _html = this.createHtml();
            _self.el.style.position = "relative";
            _self.el.style.overflow = "hidden";
            _self.el.appendChild(_html);
        },
        createHtml:function () {
            var mask = doc.createElement("div"),
                spinner = doc.createElement("div"),
                p = doc.createElement("p"),
                circular = '<svg viewBox="25 25 50 50" class="circular"><circle cx="50" cy="50" r="20" fill="none" class="path"></circle></svg>';
            mask.className = this.options.isfullscreen ? "el-loading-mask is-fullscreen" : "el-loading-mask";
            spinner.className = "el-loading-spinner";
            spinner.innerHTML = circular;
            if(this.options.text){
                p.innerText = this.options.text
                spinner.appendChild(p);
            }
            mask.appendChild(spinner);
            return mask;
        },
        show:function () {
            this.init();
        },
        hide:function () {
            this.el.style.overflow = "auto";
            this.el.querySelector(".el-loading-mask").style.display = "none";
            var thisMask = this.el.querySelector(".el-loading-mask");
            this.el.removeChild(thisMask);
        }
    };
    win.Loading = Loading;
}(window,document));