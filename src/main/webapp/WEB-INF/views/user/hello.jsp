<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="${domain_js}/jquery/1.11.0/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${domain_js}/swfupload/swfupload.js"></script>
    <script type="text/javascript" src="${domain_js}/swfupload/handlers.js"></script>
    <script type="text/javascript">
        var domain_base = "${domain_base}";
        var domain_css  = "${domain_css}";
    </script>
    <script type="text/javascript">
        $().ready(function(){

            /**
             * 初始化文件上传插件
             */
            var swfu = EWSWFUpload(domain_base, "500KB", "*.jpg;*.png;");


        });


        function updateProcessBar(rate) {
            $("#progressNumber").html(rate + "%");
            $("#progressBar").css("width", rate * 2.5 + 'px');
        }

        function uploadImageSuccess(text) {
            var obj = JSON.parse(text);
            $('#thumbnailUrl').val(obj.data.thumbUrl)
            $('#coverUrl').val(obj.data.realUrl);
            //显示图片
            $("#newsPicImage").attr("src", obj.data.realUrl);
            $("#logoPic").css("display", "block");
            $("#progressNumber").hide();
            $("#progressBar").hide();
        }

        function uploadImageError(text) {
            $('#coverUrl').val("");
            $("#logoPic").hide();
            $("#newsPicImage").attr("src", "");
            $("#progressNumber").hide();
            $("#progressBar").hide();
            $("#fileErrorMessage").show();
            $("#fileErrorMessage").html(text);
        }

        function removeImage() {
            $("#file").val("");
            $("#coverUrl").val("");
            $("#logoPic").hide();
            $("#progressNumber").hide();
            $("#progressBar").hide();
            $("#fileErrorMessage").hide();
        }

    </script>
</head>
<body>

<h1>${user.name}</h1>
<h1>${user.id}</h1>
<h1>${user.phone}</h1>

<br/>
<form action="" method="post" enctype="multipart/form-data" >

    上传图片：

    <div id="swfbtnDiv" style="position:relative;border:1px solid #FFFFFF;border: solid 1px;">
        <span id="spanButtonPlaceholder"></span>
    </div>

    <div id="progressNumber" style="display: none;">0%</div>

    <div id="progressBar" style="display: none; width: 0px;"></div>
    <font color="red"><label id="fileErrorMessage" style="display: none;"></label></font>
</form>


</body>
</html>