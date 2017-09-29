// 删除
  function onDel(){
    layer.open({
           content: '确认删除该片数据'
           ,btn: ['确认', '取消']
           ,yes: function(index, layero){
           //按钮【按钮一】的回调
          }
          ,btn2: function(index, layero){
           //按钮【按钮二】的回调
    
           //return false 开启该代码可禁止点击该按钮关闭
          }
          ,cancel: function(){ 
          //右上角关闭回调
    
          //return false 开启该代码可禁止点击该按钮关闭
           }
        });
  }
  // 置顶
  function toTop(){
    layer.open({
           content: '确认置顶该片数据'
           ,btn: ['确认', '取消']
           ,yes: function(index, layero){
           //按钮【按钮一】的回调
          }
          ,btn2: function(index, layero){
           //按钮【按钮二】的回调
    
           //return false 开启该代码可禁止点击该按钮关闭
          }
          ,cancel: function(){ 
          //右上角关闭回调
    
          //return false 开启该代码可禁止点击该按钮关闭
           }
        });
  }
