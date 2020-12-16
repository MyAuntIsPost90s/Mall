//用户
function UserDao(){
	this.user=null;
	this.getNowUser=function(callback){
        $.post(Config.UrlHead+'/userInfo/nowUser'
            ,function (data) {
               callback(data);
            },'json');
	}
}

function ShopCartDao() {

}