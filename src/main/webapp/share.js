window.shareComponent = {
	baseUrl: 'http://www.hducc.top/ljj',
	getUrlParam: function (_str, _name) {
        let self = this;
        let _reg = new RegExp('(^|&|\\?)' + _name + '=([^&]*)(&|$)'),
            _r = _str.replace(/script|%22|%3E|%3C|'|"|>|<|\\/g,'_').match(_reg);

        if (_r != null) return decodeURIComponent(_r[2]); return '';
    },
	wxChatRecg: function(configParam) {
		wx.config(configParam.data)
		wx.ready(function(){
			wx.onMenuShareTimeline({
				title: configParam.title,
				link: configParam.link,
				imgUrl: configParam.iconUrl
		    });
		});
	},
	getWeChatRecg: function(iconUrl, title, link) {
		let configParam = {}
		let that = this
		$.ajax({
			url: `http://www.hducc.top/ljj/user/getSign?url=${window.location.href}`,
			method: 'GET',
			headers: {
	          "Content-type": "application/x-www-form-urlencoded"
	        },
	        dataType: 'json',
	        success: function(json) {
	        	configParam['data'] = json.data
		        configParam['data']['debug'] = false
		        configParam['data']['appId'] = json.data.appid
		        configParam['data']['timestamp'] = ~~json.data.timestamp
		        configParam['data']['jsApiList'] = ['onMenuShareTimeline','onMenuShareAppMessage']
		        configParam['iconUrl'] = iconUrl
		        configParam['title'] = title
		        configParam['link'] = link
		        delete configParam['data']['appid']
	    		that.wxChatRecg(configParam)
	        },
	        error: function(err) {
	        	alert(err)
	        }
		})
	},
	setImgUrl: function() {
		document.querySelector('.J-image').src = this.baseUrl + '/upload/personal/' +this.getUrlParam(window.location.href, 'photo')
	},
	init: function(obj) {
		console.log(obj)
		this.getWeChatRecg(obj.iconUrl, obj.title, obj.link)
		this.setImgUrl()
	}
}