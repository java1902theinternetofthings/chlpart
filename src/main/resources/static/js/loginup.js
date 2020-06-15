(function(){
	Ext.onReady(function () {
 
    //  创建“登录”面板
    var loginPanel = new Ext.form.Panel({
        //  设置面板的位置、宽高、样式
        //  注意：此处我用的是相对布局relative（用于比较两者的区别）
        id: 'loginPan',
        standardSubmit:true,//设置FormPanel的standardSubmit:true属性
        					//则下面默认Ajax提交方式也将变成普通的表单提交。
        x: 363,
        y: 0,
        width: 700,
        height: 480,
        baseCls: '',
        defaults: {
            border: false
        },
        //  添加面板里面的物品
        items: [
            {
                //  第一件：logo图
                id: 'loginLogo',
                height: 400,
                frame: false,
                html: '<img src="img/8659870_205527569166_2.jpg">'
            },
            {
                //  第二件：登录的表单
                id: 'loginForm',
                defaultType: 'textfield',
                frame: false,
                defaults: {
                    allowBlank: false
                },
                //  添加表单里面的物品：两个输入框
                items: [
                    {
                        name: "username",
                        cls: "text_field",
                        inoutType: 'text',
                        width: 330,
                        height: 28,
                        emptyText: '请输入账号',
                        blankText: '账号不能为空'
                    },
                    {
                        name: "password",
                        cls: "text_field",
                        inputType: 'password',
                        width: 330,
                        height: 28,
                        emptyText: '请输入密码',
                        blankText: '密码不能为空'
                    }
                    ],buttons:[{
                    	text:"登录",
                    	handler:function(){  
                			loginPanel.getForm().submit({  
                    		method:"POST",  
                   			waitMsg:"登录中，请稍后...",
                   			url:"/login"
                });   
                
            }   
                    
                    }]
            } 
        ]
    });
    

    
 	
        		
 
    // 创建窗口，从而让内容达到居中效果
    var centerWindow = new Ext.Window({
        baseCls: '',
        width: 1400,
        height: 500,
        layout: 'absolute',
        closable: false,
        draggable: false,
        resizable: false,
        shadow: false,
        border: false,
        items: [loginPanel]
        
    });
 
    	//  显示窗口
    	centerWindow.show();
 
    	//  添加浏览器缩放自动居中效果
    	Ext.EventManager.onWindowResize(function () {
        	centerWindow.center();
    	});
    	
    	
	});

})();