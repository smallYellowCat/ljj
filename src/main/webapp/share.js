window.shareComponent = {
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
        title: configParam.title || '',
        link: configParam.link || '',
        imgUrl: configParam.iconUrl || ''
      });
      wx.onMenuShareAppMessage({
        title: configParam.title || '', 
        desc: configParam.desc || '', 
        link: configParam.link || '', 
        imgUrl: configParam.iconUrl || '', 
        type: 'link'
      });
      wx.hideMenuItems({
        menuList: ["menuItem:share:qq","menuItem:share:weiboApp","menuItem:favorite","menuItem:share:facebook","menuItem:share:QZone", "menuItem:openWithSafari", "menuItem:share:email", "menuItem:share:brand"]
      });
    });
  },
  getWeChatRecg: function(iconUrl, title, link, desc) {
    let configParam = {}
    let that = this
    $.ajax({
      url: `http://www.hducc.top/ljj/user/getSign?url=${encodeURIComponent(link)}`,
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
        configParam['data']['jsApiList'] = ['onMenuShareTimeline','onMenuShareAppMessage', 'hideMenuItems']
        configParam['iconUrl'] = iconUrl
        configParam['title'] = title
        configParam['link'] = link
        configParam['desc'] = desc
        delete configParam['data']['appid']
        that.wxChatRecg(configParam)
      },
      error: function(err) {
      }
    })
  },
  init: function(obj) {
    this.getWeChatRecg(obj.iconUrl, obj.title, obj.link, obj.desc);
  }
}