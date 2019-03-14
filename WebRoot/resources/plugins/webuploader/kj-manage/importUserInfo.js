/**
 * 导入用户信息_Uploader初始化
 * @param listName		jsp文件列表div的id
 * @param pickerName	jsp选择按钮的id
 * @returns	uploaer对象
 */
function import_createUploader(listName, pickerName){ 
   /*init webuploader*/  
   var $list=$("#"+listName);   //这几个初始化全局的百度文档上没说明，好蛋疼。  
   //var $btn =$("#ctlBtn");   //开始上传  
  
   var uploader = WebUploader.create({  
       // 选完文件后，是否自动上传。  
       auto: true,
	    // swf文件路径
	    swf: '${base}/resources/plugins/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: '/kj-manage/super/importUserInfo', 
       // 选择文件的按钮。可选。  
       // 内部根据当前运行是创建，可能是input元素，也可能是flash.  
       pick: '#'+pickerName, 
       // 只允许选择图片文件。  
       accept: {  
           title: 'excel',  
           extensions: 'xls,xlsx',  
           mimeTypes: '.xls,.xlsx'  
       }, 
   });  
   //验证文件格式以及文件大小
   uploader.on("error",function (type){
       if (type=="Q_TYPE_DENIED"){
    	   layer.alert("请上传excel文件(xls,xlsx)", {icon: 2,title:"Error"});
       }else if(type=="F_EXCEED_SIZE"){
    	   layer.alert("文件过大", {icon: 2,title:"Error"});
       }
   });
   // 当有文件添加进来的时候  
   uploader.on( 'fileQueued', function( file ) {  // webuploader事件.当选择文件后，文件被加载到文件队列中，触发该事件。等效于 uploader.onFileueued = function(file){...} ，类似js的事件定义。  
	   $list.empty();
	   var $li = $(  
               '<div id="' + file.id + '" class="file-item thumbnail">' +
                   '<div class="info">' + file.name + '</div>' +  
               '</div>'  
               )
       // $list为容器jQuery实例  
       $list.append( $li );
   });  
   // 文件上传过程中创建进度条实时显示。  
   uploader.on( 'uploadProgress', function( file, percentage ) {  
       var $li = $( '#'+file.id ),  
           $percent = $li.find('.progress span');  
  
       // 避免重复创建  
       if ( !$percent.length ) {  
           $percent = $('<p class="progress"><span></span></p>')  
                   .appendTo( $li )  
                   .find('span');  
       }  
  
       $percent.css( 'width', percentage * 100 + '%' );  
   });  
  
   // 文件上传成功，给item添加成功class, 用样式标记上传成功。  
   uploader.on( 'uploadSuccess', function( file, data ) {
	   if(data==true){
		   layer.alert("导入成功",{end:function(){location.reload();}});
	   }else{
		   layer.alert("导入失败");
	   }
   });  
  
   // 文件上传失败，显示上传出错。  
   uploader.on( 'uploadError', function( file ) {  
       var $li = $( '#'+file.id ),  
           $error = $li.find('div.error');  
  
       // 避免重复创建  
       if ( !$error.length ) {  
           $error = $('<div class="error"></div>').appendTo( $li );  
       }  
  
       $error.text('上传失败');  
   });  
  
   // 完成上传完了，成功或者失败，先删除进度条。  
   uploader.on( 'uploadComplete', function( file ) {  
       $( '#'+file.id ).find('.progress').remove();  
   });  
   return uploader;
  }
 
 /**
  * 释放uploader控件
  * @param uploader
 * @param listName		jsp文件列表div的id
  */
 function word_destroyUploader(uploader, listName){
	 $("#"+listName).empty();  
	uploader.destroy();
 }