(function() {

	Ext.onReady(function() {
		// 数据部分store
		var i = '';
		var info = '';
		var classification = '';
		var itemsPerPage = 20;
		var store = Ext.create('Ext.data.Store', {
			fields : ["id", "classification", "townshipname", "villagename",
					"sitename", "level", "content", "effectiveness", "deal",
					"time"],
			pageSize : itemsPerPage, // 页容量
			// 是否在服务端排序 （true的话，在客户端就不能排序）
			remoteSort : false,
			remoteFilter : true,
			proxy : {
				type : 'ajax',
				url : 'page',
				reader : { // 这里的reader为数据存储组织的地方，下面的配置是为json格式的数据，例如：[{"total":50,"rows":[{"a":"3","b":"4"}]}]
					type : 'json', // 返回数据类型为json格式
					root : 'warning.warn',// 数据
					totalProperty : 'warning.total' // 数据总条数
				}
			},
			autoLoad : {
				start : 0,
				limit : itemsPerPage
			}
				// 即时加载数据
			});

		// grid组件
		var grid = Ext.create('Ext.grid.Panel', {
			renderTo : Ext.getBody(),
			store : store,
			height : 638,
			width : 1392,
			selModel : {
				selType : 'checkboxmodel'
			}, // 选择框
			columns : [ // 标题列表
			{
						text : '编号',
						dataIndex : 'id',
						maxWidth : 30
					}, {
						text : '告警分类',
						dataIndex : 'classification',
						align : 'left',
						minWidth : 200
					}, {
						text : '乡镇名',
						dataIndex : 'townshipname',
						maxWidth : 80
					}, {
						text : '村名',
						dataIndex : 'villagename',
						align : 'left',
						minWidth : 80
					}, {
						text : '站点名',
						dataIndex : 'sitename',
						maxWidth : 80,
						align : 'left'
					}, {
						text : '告警级别',
						dataIndex : 'level',
						maxWidth : 80
					}, {
						text : '告警内容',
						dataIndex : 'content',
						minWidth : 300
					}, {
						text : '是否有效',
						dataIndex : 'effectiveness'
					}, {
						text : '是否处理',
						dataIndex : 'deal'
					}, {
						text : '告警时间',
						dataIndex : 'time',
						width : 150,
						dateFormat : 'Y-m-d H:i:s'
					}, {
						text : '操作',
						xtype : 'gridcolumn',
						width : 145,
						align : 'center',
						renderer : function(value, metaData, record) {
							var id = metaData.record.id;
							Ext.defer(function() {
								Ext.widget('button', {
									renderTo : id,
									height : 20,
									width : 70,
									text : '创建工单',
									icon : 'img/drop-add.gif',
									handler : function() {
										var ids = record.get("id");// 获取对应行id
										// alert(ids);
										Ext.Ajax.request({
											url : "info?id=" + ids,
											type : "post",
											dataType : "json",
											async : false,
											success : function(response) {
												info = Ext.decode(response.responseText);
												classification = info.warninfo.classification;
												sitename = info.warninfo.sitename;
												// 返回对应信息
											},
											error : function() {
											}
										});
										// 相应事件方法
										var form = new Ext.FormPanel({
											// labelAlign: 'top',
											bodyStyle : 'padding:5px 5px 0',
											layout : 'form',
											items : [{
														xtype : 'textfield',
														fieldLabel : '工单名称<span style=color:red;>*</span>',
														id:'cl',
														readOnly : true,
														name:'sheetname',
														value:classification
													}, {
														xtype : 'textfield',
														fieldLabel : '站点<span style=color:red;>*</span>',
														id : 'si',
														name:'sname',
														value:sitename,
														readOnly : true
													}, {
														xtype : 'combo',
														fieldLabel : '设备',
														name:'equipmentname',
														eidtable: false,
														store : new Ext.data.SimpleStore({
															fields : ["id", "name"],
															data : [['1', '设备1'], ['2', '设备2']]
														}),
														displayField : 'name',
														emptyText : '请选择'
													}, {
														xtype : 'textfield',
														height : 160, // 高度
														width : 30, // 宽度
														name:'descr',
														fieldLabel : '问题描述'
													}, {
														xtype : 'datefield',
														name:'deadline',
														eidtable: false,
														format: "d-m-y",
														fieldLabel : '截止日期'
													}],
											buttonAlign : 'center',
											buttons : [{
												text : '保存',
												handler : function() {
													form.getForm().submit({
																method : "POST",
																url : "/addsheet",
																success : function() {
																	win.close(this);
																},
																error : function() {
																	
																}
															});
												}
											}, {
												text : '关闭',
												handler : function() {
													win.close(this);
												}
											}]
										});
										var win = Ext.create(
												"Ext.window.Window", {
													title : "添加工单", // 标题
													draggable : false,
													height : 500, // 高度
													width : 400, // 宽度
													layout : "fit", // 窗口布局类型
													modal : true, // 是否模态窗口，默认为false
													resizable : false,
													items : [form]
												});
										win.show();
									}
								});
							}, 50);
							return Ext.String.format('<div id="{0}"></div>', id);
						}
					}// 创建按钮
			],

			// 底部工具条显示信息，调整页数
			bbar : [{
						xtype : 'pagingtoolbar',
						store : store,
						displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
						emptyMsg : "没有数据",
						beforePageText : "当前页",
						afterPageText : "共{0}页",
						displayInfo : true
					}],

			listeners : {
				'itemclick' : function(view, record, item, index, e) {
					i = record.data.id
				}
			},

			// 头部工具栏
			tbar : [{
						text : '无效告警',
						icon : 'img/drop-no.gif',
						handler : change
					}, "-", {
						text : '告警处理',
						icon : 'img/drop-yes.gif',
						handler : deal
					}, "-", {
						text : '删除',
						icon : 'img/tab-close.gif',
						handler : del
					}, "-", {
						id : "c",
						xtype : "combo",
						queryMode : "local",
						editable : false,
						valueField : "id",
						labelWidth : 100,
						labelAlign : "right",
						labelSeparator : "",
						fieldLabel : "告警分类",
						emptyText : '请选择',
						store : new Ext.data.SimpleStore({
									fields : ["id", "name"],
									data : [['1', '出水流量为零告警'], ['2', '动力设备超时'],
											['3', '水流量小于阈值告警'],
											['4', '水流量小于下限告警'],
											['5', '水流量大于上限告警'],
											['6', '无水流量数据告警'],
											['7', '工单处理超时告警'],
											['8', '配电箱非法开启告警'],
											['9', '考勤次数不达标告警'],
											['10', '无效考勤告警']]
								}),

						displayField : 'name'
					}, "-", {
						id : "t",
						xtype : "combo",
						queryMode : "local",
						editable : false,
						valueField : "id",
						labelWidth : 30,
						labelAlign : "right",
						labelSeparator : "",
						fieldLabel : "乡镇",
						emptyText : '请选择',
						store : store,
						displayField : 'townshipname'
					}, "-", {
						id : "v",
						xtype : "combo",
						queryMode : "local",
						editable : false,
						valueField : "id",
						labelWidth : 20,
						labelAlign : "right",
						labelSeparator : "",
						fieldLabel : "村",
						emptyText : '请选择',
						store : store,
						displayField : 'villagename'
					}, "-", {
						id : "s",
						xtype : "combo",
						queryMode : "local",
						editable : false,
						valueField : "id",
						labelWidth : 30,
						labelAlign : "right",
						labelSeparator : "",
						fieldLabel : "站点",
						emptyText : '请选择',
						store : store,
						displayField : 'sitename'
					}, "-", {
						id : "e",
						xtype : "combo",
						queryMode : "local",
						editable : false,
						valueField : "id",
						labelWidth : 50,
						labelAlign : "right",
						labelSeparator : "",
						fieldLabel : "是否有效",
						emptyText : '请选择',
						store : new Ext.data.SimpleStore({
									fields : ["id", "name"],
									data : [['1', '有效告警'], ['2', '无效告警']]
								}),

						displayField : 'name'
					}, "-", {
						text : '搜索',
						icon : 'img/drop-yes.gif',
						handler : search
					}]
		});

		function change() {
			if (i) {
				Ext.Msg.confirm('确认', '是否改变其状态', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
											url : "effect?id=" + i,
											type : "post",
											dataType : "text",
											success : function(data) {
												store.reload();// 修改数据后刷新
											},
											error : function() {
											}
										});
							} else {
								store.reload();// 修改数据后刷新
							}
						});
			}
		}

		function del() {
			if (i) {
				Ext.Msg.confirm('确认', '真的要删除吗？', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
											url : "del?id=" + i,
											type : "post",
											dataType : "text",
											success : function(data) {
												store.reload();// 修改数据后刷新
											},
											error : function() {
											}
										});
							} else {
								store.reload();// 修改数据后刷新
							}
						});
			}
		}

		function deal() {
			if (i) {
				Ext.Msg.confirm('确认', '是否改变其状态', function(btn) {
							if (btn == 'yes') {
								Ext.Ajax.request({
											url : "deal?id=" + i,
											type : "post",
											dataType : "text",
											success : function(data) {
												store.reload();// 修改数据后刷新
											},
											error : function() {
											}
										});
							} else {
								store.reload();// 修改数据后刷新
							}
						});
			}
		}

		function search() {

		}

	});

})();