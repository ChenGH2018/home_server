webpackJsonp([12],{HBSQ:function(e,n){},"Kth+":function(e,n){},NHnr:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var r=t("7+uW"),u={render:function(){var e=this.$createElement,n=this._self._c||e;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},staticRenderFns:[]};var o=t("VU/8")({name:"App"},u,!1,function(e){t("Kth+")},null,null).exports,a=t("/ocq"),i=function(e){return Promise.all([t.e(0),t.e(2)]).then(function(){var n=[t("xJsL")];e.apply(null,n)}.bind(this)).catch(t.oe)};r.default.use(a.a);var s=new a.a({routes:[{path:"/",name:"Login",component:i},{path:"/login",name:"Login",component:i},{path:"/home",name:"主页",component:function(e){return t.e(1).then(function(){var n=[t("lO7g")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{keepAlive:!1,requireAuth:!0},children:[{path:"/sysUser",name:"用户管理",component:function(e){return Promise.all([t.e(0),t.e(10)]).then(function(){var n=[t("IOOP")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{keepAlive:!1,requireAuth:!0}},{path:"/role",name:"权限管理",component:function(e){return t.e(6).then(function(){var n=[t("YWuV")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{keepAlive:!1,requireAuth:!0}},{path:"/menu",name:"菜单管理",component:function(e){return t.e(9).then(function(){var n=[t("xJDa")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{keepAlive:!1,requireAuth:!0}},{path:"/button",name:"按钮管理",component:function(e){return t.e(5).then(function(){var n=[t("kx0S")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{keepAlive:!1,requireAuth:!0}},{path:"/personalCenter",name:"个人中心",component:function(e){return t.e(7).then(function(){var n=[t("x0Ip")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{keepAlive:!1,requireAuth:!0}},{path:"/setting",name:"设置",component:function(e){return Promise.all([t.e(0),t.e(8)]).then(function(){var n=[t("jjGC")];e.apply(null,n)}.bind(this)).catch(t.oe)},meta:{keepAlive:!1,requireAuth:!0}}]},{path:"/forbidden403",component:function(e){return t.e(4).then(function(){var n=[t("rb/4")];e.apply(null,n)}.bind(this)).catch(t.oe)}},{path:"*",component:function(e){return t.e(3).then(function(){var n=[t("sKne")];e.apply(null,n)}.bind(this)).catch(t.oe)}}]}),l=t("zL8q"),c=t.n(l),p=(t("tvR6"),t("mtWM")),h=t.n(p),f=t("mvHQ"),m=t.n(f),d=t("9rMa"),v=t("uAC3"),b=t.n(v),g=new Date;function y(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";return b.a.get(e)}g.setTime(g.getTime()+36e5),r.default.use(d.a);var A=new Date;A.setTime(A.getTime()+36e5);var w=new d.a.Store({state:{user:{id:null==y("user")?"未登录":JSON.parse(y("user")).id,name:null==y("user")?"":JSON.parse(y("user")).name,userface:null==y("user")?"":JSON.parse(y("user")).userface,username:null==y("user")?"":JSON.parse(y("user")).username,phone:null==y("user")?"":JSON.parse(y("user")).phone,telephone:null==y("user")?"":JSON.parse(y("user")).telephone,address:null==y("user")?"":JSON.parse(y("user")).address,remark:null==y("user")?"":JSON.parse(y("user")).remark,roles:null==y("user")?"":JSON.parse(y("user")).roles},routes:[],buttonNumbers:[]},mutations:{initMenu:function(e,n){e.routes=n},initButton:function(e,n){e.buttonNumbers=n},login:function(e,n){e.user=n,function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"",n=arguments.length>1&&void 0!==arguments[1]?arguments[1]:null,t=arguments.length>2&&void 0!==arguments[2]?arguments[2]:{expires:g};b.a.set(e,n,t)}("user",m()(n),{expires:A})},logout:function(e){!function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:"";b.a.remove(e)}("user"),e.user=null,e.routes=[],e.buttonNumbers=[]}},actions:{}}),N=t("pFYg"),O=t.n(N);window.deepClone=function(e){return window.isArr(e)?e.slice(0):JSON.parse(m()(e))},window.deepCopy=function(e,n){n=n||{};for(var t in e)"object"===O()(e[t])?(n[t]=e[t].constructor===Array?[]:{},deepCopy(e[t],n[t])):n[t]=e[t];return n},window.clone=function(e){var n=null;if(e instanceof Array)n=e.concat();else if(e instanceof Function)n=e;else for(var t in n=new Object,e){var r=e[t];"object"!=(void 0===r?"undefined":O()(r))&&(n[t]=r)}return n},window.isArr=function(e){return"[object Array]"===Object.prototype.toString.apply(e)};t("HBSQ");var S=t("6iV/"),J=t.n(S);r.default.prototype.$qs=J.a,r.default.prototype.$hasButton=function(e){var n=w.state.buttonNumbers;for(var t in n)if(e===n[t])return!0;return!1},r.default.prototype.$http=h.a,r.default.config.productionTip=!1,r.default.use(c.a),s.beforeEach(function(e,n,t){var r,u;"Login"!==e.name?null==w.state.user||"未登录"===w.state.user.id?e.meta.requireAuth||null==e.name?t({path:"/",query:{redirect:e.path}}):t():(r=s,(u=w).state.routes.length>0||h()({method:"get",url:"/config/sysMenu"}).then(function(e){1===e.data.status?u.commit("initMenu",e.data.data):(l.Message.error({message:e.msg}),r.push("/"))}).catch(function(e){}),function(e,n){n.state.buttonNumbers.length>0||h()({method:"get",url:"/config/sysButton"}).then(function(t){1===t.data.status?n.commit("initButton",t.data.data):(l.Message.error({message:t.msg}),e.push("/"))}).catch(function(e){})}(s,w),t()):t()}),new r.default({el:"#app",router:s,store:w,components:{App:o},template:"<App/>"})},tvR6:function(e,n){}},["NHnr"]);
//# sourceMappingURL=app.7c038dc26927baba226b.js.map