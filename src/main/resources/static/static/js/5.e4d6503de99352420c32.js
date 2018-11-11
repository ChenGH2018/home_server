webpackJsonp([5],{L7EE:function(t,e){},kx0S:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={data:function(){return{labelWidth:"100px",tableLoading:!1,fullloading:!1,dialogTitle:"",dialogVisible:!1,addbool:!1,keywords:"",buttonList:[],button:{id:null,buttonNumber:"",buttonName:"",url:"",requestMethod:"",menuId:"",menu:""},treeNodeI:0,checkMenuIds:[],props:{label:"menuName",children:"children"},treeData:[],idstr:"",total:-1,page:1,size:10,totalPage:0,menuName:""}},mounted:function(){this.getList(),this.loadMenu()},methods:{getList:function(){var t=this,e=this;this.tableLoading=!0,this.$http.get("/system/button/getByPage?page="+e.page+"&size="+e.size).then(function(a){if(t.tableLoading=!1,a&&200==a.status)if(1==a.data.status){var i=a.data.data;e.buttonList=i.data,e.total=i.totalResult,e.totalPage=i.totalPage,e.page=i.page,e.size=i.size}else t.$message.error("查询失败")}).catch(function(e){"403"==e.response.status&&t.$router.push({path:"/forbidden403"})})},loadMenu:function(){var t=this;this.$http({method:"get",url:"/system/menu/getMenuTree"}).then(function(e){var a=e.data;1==a.status&&(t.treeData=a.data)}).catch(function(t){})},add:function(){this.button={},this.addbool=!0,this.dialogVisible=!0,this.dialogTitle="新增"},update:function(t,e){this.dialogVisible=!0,this.addbool=!1,this.dialogTitle=e.title,this.button=window.clone(t),this.checkMenuIds=[],this.checkMenuIds.push(t.menuId),1===this.checkMenuIds.length&&(this.treeNodeI=1)},handleSelectionChange:function(t){var e="";for(var a in t)e+=t[a].id+",";this.idstr=e},cancelEidt:function(){this.dialogVisible=!1},sendMsg:function(){this.addbool?this.postMsg():this.putMsg()},postMsg:function(){var t=this;this.button.menuId=this.$refs.tree.getCheckedKeys(!0).toString();var e=this.button;this.$http({method:"post",url:"/system/button/save",data:e}).then(function(e){var a=e.data;1==a.status?(t.dialogVisible=!1,t.$message({message:"新增成功",type:"success"}),t.getList()):null!=a.msg?t.$message.error(a.msg):t.$message.error("新增失败")}).catch(function(t){console.log(t)})},putMsg:function(){var t=this;this.button.menuId=this.$refs.tree.getCheckedKeys(!0).toString();var e=this.button;this.$http({method:"put",url:"/system/button/update",data:e}).then(function(e){var a=e.data;1==a.status?(t.dialogVisible=!1,t.$message({message:"修改成功",type:"success"}),t.getList()):null!=a.msg?t.$message.error(a.msg):t.$message.error("修改失败")}).catch(function(t){console.log(t)})},deleteMsg:function(t){var e=this,a=this;this.$confirm("确定删除此操作?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){a.$http({method:"delete",url:"/system/button/deleteByIds/"+t}).then(function(t){var a=t.data;1==a.status?(e.$message({message:"删除成功",type:"success"}),e.getList()):null!=a.msg?e.$message.error(a.msg):e.$message.error("删除失败")}).catch(function(t){console.log(t)})}).catch(function(){e.$message({type:"info",message:"已取消删除"})})},deleteAll:function(){var t=this.idstr;if(!t)return this.$message.error("提示，您还没有选择任何信息"),!1;this.deleteMsg(t)},changePage:function(t){var e=this,a=this;this.tableLoading=!0,this.$http.get("/system/button/getByPage?page="+t+"&size="+a.size).then(function(t){if(e.tableLoading=!1,t&&200==t.status)if(1==t.data.status){var i=t.data.data;a.buttonList=i.data,a.total=i.totalResult,a.totalPage=i.totalPage,a.page=i.page,a.size=i.size}else e.$message.error("查询失败");else t&&401==t.status&&e.$message.error("没有权限")}).catch(function(t){"403"==t.response.status&&e.$router.push({path:"/forbidden403"})})},handleSizeChange:function(t){this.size=t,this.searchKeyword()},handleCheckChange:function(t,e,a){this.treeNodeI++,this.treeNodeI%2==0&&(this.$refs.tree.setCheckedNodes([]),e&&this.$refs.tree.setCheckedNodes([t]),this.treeNodeI=0)},searchKeyword:function(){var t=this,e=this;this.tableLoading=!0,this.$http.get("/system/button/getByPage?page="+e.page+"&size="+e.size+"&menuName="+e.menuName).then(function(a){if(t.tableLoading=!1,a&&200==a.status)if(1==a.data.status){var i=a.data.data;e.buttonList=i.data,e.total=i.totalResult,e.totalPage=i.totalPage,e.page=i.page,e.size=i.size}else t.$message.error("查询失败")}).catch(function(e){"403"==e.response.status&&t.$router.push({path:"/forbidden403"})})}}},s={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.fullloading,expression:"fullloading"}],staticStyle:{"margin-top":"10px"},attrs:{id:"base"}},[a("div",{staticClass:"mbT20 ",attrs:{align:"left"}},[a("el-input",{staticStyle:{width:"25%"},attrs:{placeholder:"请输入按钮所属菜单",clearable:""},model:{value:t.menuName,callback:function(e){t.menuName=e},expression:"menuName"}}),t._v(" "),a("el-button",{staticStyle:{"margin-left":"20px"},attrs:{type:"primary",icon:"el-icon-search"},on:{click:t.searchKeyword}},[t._v("搜索")])],1),t._v(" "),a("br"),t._v(" "),a("div",{staticStyle:{"margin-top":"20px"}},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.tableLoading,expression:"tableLoading"}],staticStyle:{width:"100%"},attrs:{data:t.buttonList,size:"mini",border:"",stripe:""},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",align:"left"}}),t._v(" "),a("el-table-column",{attrs:{prop:"buttonNumber",align:"center",label:"按钮编号"}}),t._v(" "),a("el-table-column",{attrs:{prop:"buttonName",align:"center",label:"按钮名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"url",align:"center",label:"链接"}}),t._v(" "),a("el-table-column",{attrs:{prop:"requestMethod",align:"center",label:"请求方式"}}),t._v(" "),a("el-table-column",{attrs:{prop:"menu.menuName",align:"center",label:"所属菜单"}}),t._v(" "),a("el-table-column",{attrs:{label:"操作",align:"center",width:"300",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[t.$hasButton("buttonEdit")?a("el-button",{staticStyle:{padding:"3px 4px 3px 4px",margin:"2px"},attrs:{type:"primary",size:"mini"},on:{click:function(a){t.update(e.row,{title:"修改"})}}},[t._v("修改\n          ")]):t._e(),t._v(" "),t.$hasButton("buttonDel")?a("el-button",{staticStyle:{padding:"3px 4px 3px 4px",margin:"2px"},attrs:{type:"danger",size:"mini"},on:{click:function(a){t.deleteMsg(e.row.id)}}},[t._v("删除\n          ")]):t._e()]}}])})],1)],1),t._v(" "),a("div",{staticClass:"clearfix"},[a("div",{staticClass:"fl",staticStyle:{"margin-top":"20px"}},[t.$hasButton("buttonAdd")?a("el-button",{attrs:{size:"mini",type:"success",icon:"el-icon-search"},on:{click:t.add}},[t._v("新增")]):t._e(),t._v(" "),t.$hasButton("buttonDel")?a("el-button",{attrs:{size:"mini",type:"danger",icon:"el-icon-delete"},on:{click:function(e){t.deleteAll()}}},[t._v("删除")]):t._e()],1),t._v(" "),a("div",{staticStyle:{"text-align":"right","margin-top":"10px"}},[a("el-pagination",{attrs:{background:"",layout:"total,sizes,prev, pager, next","page-count":t.totalPage,"page-size":t.size,total:t.total,"current-page":t.page,"page-sizes":[3,6,10,20]},on:{"current-change":t.changePage,"size-change":t.handleSizeChange}})],1)]),t._v(" "),a("el-form",{staticStyle:{margin:"0px",padding:"0px"}},[a("div",{staticStyle:{"text-align":"left"},attrs:{id:"Bdialog"}},[a("el-dialog",{staticStyle:{padding:"0px"},attrs:{"close-on-click-modal":!1,visible:t.dialogVisible,width:"50%"},on:{"update:visible":function(e){t.dialogVisible=e}}},[a("div",{staticClass:"dialog_B Detail"},[a("div",{staticStyle:{width:"700px",margin:"auto"}},[a("h1",{staticClass:"H1"},[t._v(t._s(t.dialogTitle))]),t._v(" "),a("el-form",{staticStyle:{margin:"0px",padding:"0px"}},[a("el-form-item",{attrs:{label:"按钮编号：",prop:"buttonNumber","label-width":t.labelWidth}},[a("el-input",{attrs:{placeholder:"请输入按钮编号","prefix-icon":"el-icon-edit"},model:{value:t.button.buttonNumber,callback:function(e){t.$set(t.button,"buttonNumber",e)},expression:"button.buttonNumber"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"按钮名称：",prop:"buttonName","label-width":t.labelWidth}},[a("el-input",{attrs:{placeholder:"请输入按钮名称","prefix-icon":"el-icon-edit"},model:{value:t.button.buttonName,callback:function(e){t.$set(t.button,"buttonName",e)},expression:"button.buttonName"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"链接：",prop:"url","label-width":t.labelWidth}},[a("el-input",{attrs:{placeholder:"请输入链接","prefix-icon":"el-icon-edit"},model:{value:t.button.url,callback:function(e){t.$set(t.button,"url",e)},expression:"button.url"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"请求方式：",prop:"requestMethod","label-width":t.labelWidth}},[a("el-input",{attrs:{placeholder:"请输入请求方式","prefix-icon":"el-icon-edit"},model:{value:t.button.requestMethod,callback:function(e){t.$set(t.button,"requestMethod",e)},expression:"button.requestMethod"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"所属菜单：",prop:"menuId","label-width":t.labelWidth}},[a("el-tree",{ref:"tree",attrs:{props:t.props,data:t.treeData,"default-expanded-keys":t.checkMenuIds,"default-checked-keys":t.checkMenuIds,"node-key":"id","check-strictly":"","show-checkbox":""},on:{"check-change":t.handleCheckChange}})],1)],1),t._v(" "),a("span",{staticClass:"dialog-footer",staticStyle:{"text-align":"right","margin-top":"20px",width:"100%",display:"block"},attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"small"},on:{click:function(e){t.cancelEidt()}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{size:"small",type:"primary"},on:{click:function(e){t.sendMsg()}}},[t._v("确 定")])],1)],1)])])],1)])],1)},staticRenderFns:[]};var n=a("VU/8")(i,s,!1,function(t){a("L7EE")},null,null);e.default=n.exports}});
//# sourceMappingURL=5.e4d6503de99352420c32.js.map