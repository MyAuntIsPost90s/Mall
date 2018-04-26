//头部组件
function HeaderService() {
    var html = '<div class="am-container header"><ul class="message-l"><div class="topMessage">' +
        '<div class="menu-hd"><a href="login.html" target="_top" class="h">亲，请登录</a>&nbsp;|&nbsp;<a href="register.html" target="_top">免费注册</a>' +
        '</div></div></ul><ul class="message-r"><div class="topMessage home">' +
        '<div class="menu-hd"><a href="home.html" target="_top" class="h">商城首页</a></div>' +
        '</div><div class="topMessage my-shangcheng"><div class="menu-hd MyShangcheng"><a href="information.html" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>' +
        '</div><div class="topMessage mini-cart">' +
        '<div class="menu-hd"><a id="mc-menu-hd" href="shopcart.html" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span>(<strong id="J_MiniCartNum" class="h">0</strong>)</a></div>' +
        '</div></ul></div>';
    //初始化
    this.init = function () {
        $('#ctrl-header-nav').before(html);
        $('#ctrl-header-nav').remove();

        if (userBase.getUser() != null) {
            $('.header .message-r').show();
            $('.header .message-l').hide();
        }
        else {
            $('.header .message-r').hide();
            $('.header .message-l').show();
        }

        //加载购物车数量
        if (userBase.getShopcart() != null) {
            $('#J_MiniCartNum').html(userBase.getShopcart().length);
        }
    }

    //跳转登陆
    this.toLogin = function () {
        window.location.href = Config.MallAppUrlHead + '/login.html';
    }

    this.reloadShopcart = function () {
        userBase.reloadShopcart(function (data) {
            var count = data.data.total;
            $('#J_MiniCartNum').html(count);
        });
    }
}

//边框组件
function MenuService() {
    var html = '<div class=tip><div id="sidebar"><div id="wrap"><div id="prof" class="item ">' +
        '<a href="information.html"><span class="setting "></span></a><div class="ibar_login_box status_login ">' +
        '<div class="avatar_box "><p class="avatar_imgbox "><img src="Contents/images/no-img_mid_.jpg" /></p>' +
        '<ul class="user_info"><li class="li-realname">用户名sl1903</li>' +
        '<li class="li-username">普通会员</li></ul>' +
        '</div><div class="login_btnbox ">' +
        '<a href="#" class="login_order ">我的订单</a><a href="login.html" id="login_out">退出</a>' +
        '</div><i class="icon_arrow_white"></i>' +
        '</div></div><div id="shopCart" class="item ">' +
        '<a href="shopcart.html"><span class="message "></span></a>' +
        '<p>购物车</p><p class="cart_num">0</p></div><div class="quick_toggle ">' +
        '<li class="qtitem "><a href="feedback.html"><span class="kfzx "></span></a><div class="mp_tooltip ">客服中心<i class="icon_arrow_right_black "></i></div>' +
        '</li><li class="qtitem "><a><span class="mpbtn_qrcode "></span></a>' +
        '<div class="mp_qrcode " style="display:none; "><img src="Contents/images/weixin_code_145.png " /><i class="icon_arrow_white "></i></div>' +
        '</li><li class="qtitem ">' +
        '<a href="#top " class="return_top "><span class="top "></span></a></li></div>' +
        '<div id="quick_links_pop " class="quick_links_pop hide "></div></div></div>' +
        '</div>';

    this.init = function () {
        $('#ctrl-menu').before(html);
        $('#ctrl-menu').remove();
        if (userBase.getUser() != null) {
            if(userBase.getUser().userheadimgurl!=''){
                $('.avatar_imgbox img').attr('src',Config.UrlHead + userBase.getUser().userheadimgurl);
            }
            $('.user_info .li-username').html(userBase.getUser().username);
            $('.user_info .li-realname').html(userBase.getUser().realname);
        }
        else {
            $('.user_info').hide();
            $('#shopCart').hide();
            $('#prof').hide();
        }

        //加载购物车数量
        if (userBase.getShopcart() != null) {
            $('#shopCart .cart_num').html(userBase.getShopcart().length);
        }
        bindEvent();
    }

    this.reloadShopcart = function () {
        userBase.reloadShopcart(function (data) {
            var count = data.data.total;
            $('#shopCart .cart_num').html(count);
        });
    }

    function bindEvent() {
        $('#shopCart').click(function () {
            window.location.href = Config.MallAppUrlHead + '/shopcart.html';
        })

        $('#login_out').click(function () {
            $.post(Config.UrlHead+'/account/loginOut');
        })
        jQuery(function ($) {

            //鼠标悬停信息

            $("#wrap .item").mouseenter(function () {
                $(this).children(".mp_tooltip").animate({left: -92, queue: true});
                $(this).children(".mp_tooltip").css("visibility", "visible");
                $(this).children(".ibar_login_box").css("display", "block");
            });
            $("#wrap .item").mouseleave(function () {
                $(this).children(".mp_tooltip").css("visibility", "hidden");
                $(this).children(".mp_tooltip").animate({left: -121, queue: true});
                $(this).children(".ibar_login_box").css("display", "none");
            });
            $(".quick_toggle li").mouseover(function () {
                $(this).children(".mp_qrcode").show();
                $(this).children(".mp_tooltip").animate({left: -92, queue: true});
                $(this).children(".mp_tooltip").css("visibility", "visible");
            });
            $(".quick_toggle li").mouseleave(function () {
                $(this).children(".mp_qrcode").hide();
                $(this).children(".mp_tooltip").css("visibility", "hidden");
                $(this).children(".mp_tooltip").animate({left: -121, queue: true});
            });

        })
        $(".return_top").click(function () {
            ds.scrollTo(0, 0);
            hideReturnTop();
        })
    }
}

//分类导航组件
function GoodsKindBarService() {

    var html = '<div class="shopNav"><div class="slideall"><div class="long-title"><span class="all-goods">全部分类</span></div><div class="nav-cont">' +
        '<ul><li class="index"><a href="home.html">首页</a></li></ul>' +
        '</div><div id="nav" class="navfull"><div class="area clearfix">' +
        '<div class="category-content" id="guide_2"> <div class="category">' +
        '<ul class="category-list" id="js_climit_li"></ul>' +
        '</div></div></div></div>' +
        '<div class="am-g am-g-fixed smallnav"><div class="am-u-sm-3">' +
        '<a href="sort.html"><img src="Contents/images/navsmall.jpg"/>' +
        '<div class="title">商品分类</div>' +
        '</a></div><div class="am-u-sm-3">' +
        '<a href="#"><img src="Contents/images/huismall.jpg"/>' +
        '<div class="title">大聚惠</div></a></div>' +
        '<div class="am-u-sm-3">' +
        '<a href="#"><img src="Contents/images/mansmall.jpg"/>' +
        '<div class="title">个人中心</div>' +
        '</a></div><div class="am-u-sm-3">' +
        '<a href="#"><img src="Contents/images/moneysmall.jpg"/>' +
        '<div class="title">投资理财</div> ' +
        '</a></div></div>' +
        '</div></div>';

    this.init = function () {
        $('#div-nav-goodskind').before(html);
        $('#div-nav-goodskind').remove();

        webappDao.getGoodsKinds(function (data) {
            if (data.code != 1) {
                return;
            }
            //加载分类
            var html = '';
            var goodskinds = data.data;
            for (var i = 0; i < goodskinds.length; i++) {
                html += '<li data-goodskindid="' + goodskinds[i].goodskindid + '" class="appliance js_toggle relative first"><div class="category-info">' +
                    '<h3 class="category-name b-category-name"><a class="ml-22" href="search.html?goodskindid=' + goodskinds[i].goodskindid + '" title="' + goodskinds[i].goodskindname + '">' + goodskinds[i].goodskindname + '</a></h3>' +
                    '</div></li>';
            }
            $('#js_climit_li').html(html);

            //绑定鼠标点击事件
            $('.shopNav .slideall .long-title').click(function () {
                $('#nav').toggle();
            })
        })
    }
}