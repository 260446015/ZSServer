var re = /^[\u4E00-\u9FA5]/; //验证中文
var myreg = /^1[34578]\d{9}$/; //验证手机号
var email1 = /^[\w]+(\.[\w]+)*@[\w]+(\.[\w]+)+$/; //邮箱
//判断是否为空
function isEmpty(str) {
    if (str.length == 0) {
        return true;
    } else {
        return false;
    }
};
//验证中文格式
function isChinese(str) {
    return re.test(str);
};
//验证手机号格式
function isPhone(str) {
    return myreg.test(str);
};
//验证邮箱的格式
function isEmail(str) {
    return email1.test(str);
};

function addRegister() {
    var _name = $('#name').val();
    var _address = $('#address').val();
    var _phone = $('#phone').val();
    var _email = $('#email').val();
    var _position = $('#position').val();
    var _units = $('#units').val();
    var _garden = $('#garden').val();
    var _develop = $('#develop').val();
    var _captcha = $('#captcha').val();
    var param = {
        realName: _name,
        telphone: _phone,
        userEmail: _email,
        userJob: _position,
        userDepartment: _units,
        userPark: _garden,
        area: _address,
        userComp: _develop,
        captcha: _captcha
    };
    //判断用户输入的信息是否正确
    if (isEmpty(_name)) {
        $('#name').focus();
        new Alert({ flag: false, text: "姓名不能为空", timer: 2000 }).show();
    } else {
        if (!isChinese(_name)) {
            $('#name').focus();
            new Alert({ flag: false, text: "姓名输入格式有误", timer: 2000 }).show();
        }
    }
    if (isEmpty(_phone)) {
        $('#phone').focus();
        new Alert({ flag: false, text: "手机号码不能为空", timer: 2000 }).show();
    } else {
        if (!isPhone(_phone)) {
            $('#phone').focus();
            new Alert({ flag: false, text: "手机号码输入格式有误", timer: 2000 }).show();
        }
    }
    if (isEmpty(_email)) {
        $('#email').focus();
        new Alert({ flag: false, text: "邮箱不能为空", timer: 2000 }).show();
    } else {
        if (!isEmail(_email)) {
            $('#email').focus();
            new Alert({ flag: false, text: "邮箱输入格式有误", timer: 2000 }).show();
        }
    }

    //如果都不为空，才进行ajax请求
    if (!isEmpty(_name) && !isEmpty(_address) && !isEmpty(_phone) && !isEmpty(_email) &&
        !isEmpty(_position) && !isEmpty(_units) && !isEmpty(_garden) &&
        !isEmpty(_develop) && !isEmpty(_captcha)) {
        $.ajax({
            url: '/apis/reg/regiterUser.do',
            type: 'POST',
            asynyc: false,
            contentType: 'application/json',
            data: JSON.stringify(param),
            success: function(res) {
                if (res.data == true) {
                    new Alert({ flag: true, text: res.message, timer: 2000 }).show();
                } else {
                    new Alert({ flag: false, text: res.message, timer: 2000 }).show();
                }
                $(location).attr('href', '/login.do');
            }

        });
    }
};
var countdown = 60;

function settime(e) {
    if (countdown == 0) {
        e.removeAttribute("disabled");
        $(".btns").html("免费获取验证码");
        countdown = 60;
    } else {
        e.setAttribute("disabled", true);
        $(".btns").html("重新发送(" + countdown + ")");
        countdown--;
    };
    setTimeout(function() {
        settime(e)
    }, 1000);
};

function getPhone(e) {

    var _name = $("#name").val();
    var _phone = $("#phone").val();

    if (isEmpty(_name)) {
        $('#name').focus();
        new Alert({ flag: false, text: "姓名不能为空", timer: 2000 }).show();
    } else {

        if (!isChinese(_name)) {
            $('#name').focus();
            new Alert({ flag: false, text: "姓名输入格式有误", timer: 2000 }).show();
        }
    }
    if (isEmpty(_phone)) {
        $('#phone').focus();
        new Alert({ flag: false, text: "手机号码不能为空", timer: 2000 }).show();
    } else {
        settime(e);
        if (!isPhone(_phone)) {
            $('#phone').focus();
            new Alert({ flag: false, text: "手机号码输入格式有误", timer: 2000 }).show();
        }
    }
    var param = { realName: _name, telphone: _phone };
    if (!isEmpty(_phone) && isChinese(_name) && !isEmpty(_name) && isPhone(_phone)) {
        $.ajax({
            url: '/apis/reg/getPhoneCaptchaByUserNameAndPhone.do',
            type: 'POST',
            asynyc: false,
            contentType: 'application/json',
            data: JSON.stringify(param),
            success: function(res) {
                if (res.data == null) {
                    new Alert({ flag: false, text: res.message, timer: 2000 }).show();
                } else {
                    new Alert({ flag: true, text: '验证码为：' + res.data + ',请尽快填写', timer: 2000 }).show();
                }
            }

        });
    }
};