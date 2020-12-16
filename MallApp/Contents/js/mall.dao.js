//用户
function UserDao() {
    //获取当前用户
    this.getNowUser = function (callback) {
        $.post(Config.UrlHead + '/userInfo/nowUser'
            , function (data) {
                callback(data);
            }, 'json');
    }
    //修改
    this.update = function (params, callback) {
        var url = Config.UrlHead + '/userInfo/update';
        $.post(url
            , params
            , function (data) {
                callback(data);
            }, 'json')
    }
}

//反馈
function FeedbackDao() {
    //添加
    this.add = function (params, callback) {
        var url = Config.UrlHead + '/feedback/add';
        $.post(url
            , params
            , function (data) {
                callback(data);
            }, 'json')
    }
}

//购物车
function ShopcartDao() {
    this.getMyShopcart = function (shopcart, callback) {
        if (shopcart == null) {
            return;
        }
        shopcart.page = 1;
        shopcart.rows = 9999;
        $.post(Config.UrlHead + '/shopcart/list'
            , shopcart
            , function (data) {
                callback(data);
            }, 'json');
    }

    //加入购物车
    this.add = function (shopcart, callback) {
        if (shopcart == null) {
            return;
        }
        $.post(Config.UrlHead + '/shopcart/add'
            , shopcart
            , function (data) {
                callback(data);
            }, 'json');
    }

    //批量修改
    this.batchUpdate=function (shopcarts,callback) {
        if (shopcarts == null || shopcarts.length < 1) {
            return;
        }

        $.ajax({
            url: Config.UrlHead + '/shopcart/batchUpdate',
            contentType: "application/json ; charset=utf-8",
            data: JSON.stringify(shopcarts),
            method: 'post',
            dataType: 'json',
            success: function (data) {
                callback(data);
            }
        });
    }

    //批量删除
    this.batchDelete = function (ids, callback) {
        if (ids == null || ids.length < 1) {
            return;
        }

        $.ajax({
            url: Config.UrlHead + '/shopcart/batchDelete',
            contentType: "application/json ; charset=utf-8",
            data: JSON.stringify(ids),
            method: 'post',
            dataType: 'json',
            success: function (data) {
                callback(data);
            }
        });
    }
}

//用户地址
function AddressDao() {
    //添加
    this.add = function (params, callback) {
        $.post(Config.UrlHead + '/address/add'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }

    //修改
    this.update = function (params, callback) {
        $.post(Config.UrlHead + '/address/update'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }

    //获取集合
    this.list = function (params, callback) {
        $.post(Config.UrlHead + '/address/list'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }

    //批量删除
    this.batchDelete = function (ids, callback) {
        if (ids == null || ids.length < 1) {
            return;
        }
        $.ajax({
            url: Config.UrlHead + '/address/batchDelete',
            contentType: "application/json ; charset=utf-8",
            data: JSON.stringify(ids),
            method: 'post',
            dataType: 'json',
            success: function (data) {
                callback(data);
            }
        });
    }
}

//用户订单
function OrderFormDao(){
    //提交用户订单
    this.add=function (params,callback) {
        $.post(Config.UrlHead + '/orderForm/addUserOrder'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }

    //获取单个物品信息
    this.single=function(id,callback){
        $.post(Config.UrlHead + '/orderForm/single'
            , {orderformid:id}
            , function (data) {
                callback(data);
            }, 'json');
    }

    //获取物品订单
    this.orderWithGoods=function(params,callback){
        $.post(Config.UrlHead + '/orderForm/orderWithGoods'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }

    //修改
    this.update=function(params,callback){
        $.post(Config.UrlHead + '/orderForm/update'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }
}

//webapp
function WebAppDao() {
    //获取首页
    this.getHome = function (callback) {
        $.post(Config.UrlHead + '/webapp/home'
            , function (data) {
                callback(data);
            }, 'json');
    }

    //获取分类
    this.getGoodsKinds = function (callback) {
        $.post(Config.UrlHead + '/webapp/goodskinds'
            , function (data) {
                callback(data);
            }, 'json');
    }

    //商品详情
    this.getIntroduction = function (params, callback) {
        $.post(Config.UrlHead + '/webapp/introduction'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }

    //付款页面
    this.getPay = function (callback) {
        $.post(Config.UrlHead + '/webapp/pay'
            , function (data) {
                callback(data);
            }, 'json');
    }

    //获取商品信息
    this.getGoods = function (params, callback) {
        $.post(Config.UrlHead + '/webapp/list4goods'
            , params
            , function (data) {
                callback(data);
            }, 'json');
    }
}

//用户信息基础
function UserBase() {
    var user = null;
    var shopcart = null;

    new UserDao().getNowUser(function (data) {
        if (data.code == 1) {
            user = data.data;
            console.log('load user success');
            console.log(user);
            return;
        }
        console.log('load user fail');
    });
    if (user != null) {
        new ShopcartDao().getMyShopcart({}
            , function (data) {
                if (data.code == 1 && data.data != null) {
                    shopcart = data.data.rows;
                    console.log('load shopcart success');
                    console.log(shopcart);
                    return;
                }
                console.log('load shopcart fail');
            });
    }

    this.getUser = function () {
        return user;
    }

    this.getShopcart = function () {
        return shopcart;
    }


    //刷新购物车
    this.reloadShopcart = function (callback) {
        new ShopcartDao().getMyShopcart({}
            , function (data) {
                if (data.code == 1 && data.data != null) {
                    shopcart = data.data.rows;
                    console.log('load shopcart success');
                    console.log(shopcart);
                    callback(data);
                    return;
                }
                console.log('load shopcart fail');
            });
    }
}

var userBase = null;
var webappDao = null;
$(function () {
    webappDao = new WebAppDao();
    $.ajaxSetup({async: false});
    userBase = new UserBase();
    $.ajaxSetup({async: true});
})