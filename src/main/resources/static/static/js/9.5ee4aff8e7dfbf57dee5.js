webpackJsonp([9],{OCdL:function(t,e){},xJDa:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={data:function(){return{labelWidth:"100px",tableLoading:!1,fullloading:!1,dialogTitle:"",dialogVisible:!1,iconVisible:!1,addbool:!1,keywords:"",menuList:[],menu:{id:null,menuName:"",url:"",component:"",menuOrder:"",iconCls:"",parentId:""},parentId:"",iconCls:"fa fa-plus",icons:[],mainPage:!0,idstr:"",total:-1,page:1,size:6}},mounted:function(){this.getList("1",!1)},methods:{getList:function(t,e){var i=this,n=this;this.parentId=t,this.mainPage=e,this.tableLoading=!0,this.$http.get("/system/menu/getByParentId/"+t).then(function(t){i.tableLoading=!1,t&&200==t.status&&(1==t.data.status?n.menuList=t.data.data:i.$message.error("查询失败"))}).catch(function(t){"403"==t.response.status&&i.$router.push({path:"/forbidden403"})})},add:function(){this.menu={},this.addbool=!0,this.dialogVisible=!0,this.dialogTitle="新增"},update:function(t,e){this.dialogVisible=!0,this.addbool=!1,this.dialogTitle=e.title,this.menu=window.clone(t),this.iconCls=void 0==t.iconCls?"fa fa-plus":t.iconCls},searchClick:function(){alert("ff")},handleSelectionChange:function(t){var e="";for(var i in t)e+=t[i].id+",";this.idstr=e},cancelEidt:function(){this.dialogVisible=!1},sendMsg:function(){this.addbool?this.postMsg():this.putMsg()},postMsg:function(){var t=this;this.menu.parentId=this.parentId,this.menu.iconCls=void 0==this.menu.iconCls?"fa fa-plus":this.iconCls;var e=this.menu,i=this;this.$http({method:"post",url:"/system/menu/save",data:e}).then(function(e){var n=e.data;1==n.status?(t.dialogVisible=!1,t.$message({message:"新增成功",type:"success"}),t.getList(i.parentId,i.mainPage)):null!=n.msg?t.$message.error(n.msg):t.$message.error("新增失败")}).catch(function(t){console.log(t)})},putMsg:function(){var t=this,e=this.menu,i=this;this.$http({method:"put",url:"/system/menu/update",data:e}).then(function(e){var n=e.data;1==n.status?(t.dialogVisible=!1,t.$message({message:"修改成功",type:"success"}),t.getList(i.parentId,i.mainPage)):null!=n.msg?t.$message.error(n.msg):t.$message.error("修改失败")}).catch(function(t){console.log(t)})},deleteMsg:function(t){var e=this,i=this;this.$confirm("确定删除此操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){i.$http({method:"delete",url:"/system/menu/delete/"+t}).then(function(t){var n=t.data;1==n.status?(e.$message({message:"删除成功",type:"success"}),e.getList(i.parentId,i.mainPage)):null!=n.msg?e.$message.error(n.msg):e.$message.error("删除失败")}).catch(function(t){console.log(t)})}).catch(function(){e.$message({type:"info",message:"已取消删除"})})},handleClose:function(){this.iconVisible=!1},openIcon:function(){var t=this;this.iconVisible=!0,this.$http({method:"get",url:"/optionalIcon/getAll"}).then(function(e){var i=e.data;1==i.status&&(t.icons=i.data)}).catch(function(t){})},checkIcon:function(t){this.iconVisible=!1,this.menu.iconCls=t,this.iconCls=t,console.log(t)}}},a={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{directives:[{name:"loading",rawName:"v-loading",value:t.fullloading,expression:"fullloading"}],staticStyle:{"margin-top":"10px"},attrs:{id:"base"}},[i("div",{staticStyle:{"margin-top":"20px"}},[i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.tableLoading,expression:"tableLoading"}],staticStyle:{width:"100%"},attrs:{data:t.menuList,size:"mini",border:"",stripe:""},on:{"selection-change":t.handleSelectionChange}},[i("el-table-column",{attrs:{type:"selection",align:"left"}}),t._v(" "),i("el-table-column",{attrs:{prop:"menuName",align:"center",label:"菜单名称"}}),t._v(" "),i("el-table-column",{attrs:{prop:"url",align:"center",label:"链接"}}),t._v(" "),i("el-table-column",{attrs:{prop:"component",align:"center",label:"组件"}}),t._v(" "),i("el-table-column",{attrs:{prop:"iconCls",align:"center",label:"图标"},scopedSlots:t._u([{key:"default",fn:function(t){return[i("i",{class:[t.row.iconCls],attrs:{slot:"prefix"},slot:"prefix"})]}}])}),t._v(" "),i("el-table-column",{attrs:{label:"操作",align:"center",width:"300",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[i("el-button",{directives:[{name:"show",rawName:"v-show",value:!t.mainPage,expression:"!mainPage"}],staticStyle:{padding:"6px 10px",margin:"2px"},attrs:{type:"primary",size:"mini"},on:{click:function(i){t.getList(e.row.id,!0)}}},[t._v("查看子菜单\n          ")]),t._v(" "),t.$hasButton("menuEdit")?i("el-button",{staticStyle:{padding:"6px 10px",margin:"2px"},attrs:{type:"primary",size:"mini"},on:{click:function(i){t.update(e.row,{title:"修改"})}}},[t._v("修改\n          ")]):t._e(),t._v(" "),t.$hasButton("menuDel")?i("el-button",{staticStyle:{padding:"6px 10px",margin:"2px"},attrs:{type:"danger",size:"mini"},on:{click:function(i){t.deleteMsg(e.row.id)}}},[t._v("删除\n          ")]):t._e()]}}])})],1)],1),t._v(" "),i("div",{staticClass:"clearfix"},[i("div",{staticClass:"fl",staticStyle:{"margin-top":"20px"}},[t.$hasButton("menuAdd")?i("el-button",{attrs:{size:"mini",type:"success",icon:"el-icon-search"},on:{click:t.add}},[t._v("新增")]):t._e(),t._v(" "),i("el-button",{directives:[{name:"show",rawName:"v-show",value:t.mainPage,expression:"mainPage"}],attrs:{size:"mini",icon:"el-icon-back"},on:{click:function(e){t.getList(1,!1)}}},[t._v("返回")])],1)]),t._v(" "),i("el-form",{staticStyle:{margin:"0px",padding:"0px"}},[i("div",{staticStyle:{"text-align":"left"},attrs:{id:"Bdialog"}},[i("el-dialog",{staticStyle:{padding:"0px"},attrs:{"close-on-click-modal":!1,visible:t.dialogVisible,width:"50%"},on:{"update:visible":function(e){t.dialogVisible=e}}},[i("div",{staticClass:"dialog_B Detail"},[i("div",{staticStyle:{width:"700px",margin:"auto"}},[i("h1",{staticClass:"H1"},[t._v(t._s(t.dialogTitle))]),t._v(" "),i("el-form",{staticStyle:{margin:"0px",padding:"0px"}},[i("el-form-item",{attrs:{label:"菜单名称：",prop:"menuName","label-width":t.labelWidth}},[i("el-input",{attrs:{placeholder:"请输入菜单名称","prefix-icon":"el-icon-edit"},model:{value:t.menu.menuName,callback:function(e){t.$set(t.menu,"menuName",e)},expression:"menu.menuName"}})],1),t._v(" "),i("el-form-item",{attrs:{label:"链接：",prop:"url","label-width":t.labelWidth}},[i("el-input",{attrs:{placeholder:"请输入链接","prefix-icon":"el-icon-edit"},model:{value:t.menu.url,callback:function(e){t.$set(t.menu,"url",e)},expression:"menu.url"}})],1),t._v(" "),i("el-form-item",{attrs:{label:"组件：",prop:"component","label-width":t.labelWidth}},[i("el-input",{attrs:{placeholder:"请输入组件","prefix-icon":"el-icon-edit"},model:{value:t.menu.component,callback:function(e){t.$set(t.menu,"component",e)},expression:"menu.component"}})],1),t._v(" "),i("el-form-item",{attrs:{label:"序号：",prop:"menuOrder","label-width":t.labelWidth}},[i("el-input",{attrs:{placeholder:"请输入序号","prefix-icon":"el-icon-edit",type:"number",min:"1"},model:{value:t.menu.menuOrder,callback:function(e){t.$set(t.menu,"menuOrder",e)},expression:"menu.menuOrder"}})],1),t._v(" "),i("el-form-item",{attrs:{label:"图标：",prop:"iconCls","label-width":t.labelWidth}},[i("el-button",{staticClass:"setLeft",attrs:{type:"primary",plain:""},on:{click:t.openIcon}},[i("i",{class:[t.iconCls]})])],1)],1),t._v(" "),i("span",{staticClass:"dialog-footer",staticStyle:{"text-align":"right","margin-top":"20px",width:"100%",display:"block"},attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{size:"small"},on:{click:function(e){t.cancelEidt()}}},[t._v("取 消")]),t._v(" "),i("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(e){t.sendMsg()}}},[t._v("确 定")])],1)],1)])])],1)]),t._v(" "),i("el-dialog",{attrs:{title:"请选择图标",visible:t.iconVisible,width:"30%","before-close":t.handleClose},on:{"update:visible":function(e){t.iconVisible=e}}},[i("ul",{staticClass:"iconUl"},t._l(t.icons,function(e){return i("li",{staticClass:"setLeft"},[i("el-button",{attrs:{type:"primary",plain:""},on:{click:function(i){t.checkIcon(e.name)}}},[i("i",{class:[e.name]})])],1)})),t._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(e){t.iconVisible=!1}}},[t._v("取 消")])],1)])],1)},staticRenderFns:[]};var s=i("VU/8")(n,a,!1,function(t){i("OCdL")},null,null);e.default=s.exports}});
//# sourceMappingURL=9.5ee4aff8e7dfbf57dee5.js.map