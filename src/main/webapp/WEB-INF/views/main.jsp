<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HYHB</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<script type="text/javascript">
    function doExcelUploadProcess(){
        var f = new FormData(document.getElementById('form1'));
        $.ajax({
            url: "uploadExcelFile",
            data: f,
            processData: false,
            contentType: false,
            type: "POST",
            success: function(data){
                console.log(data);
                document.getElementById('result').innerHTML = JSON.stringify(data);
            }
        });
    }

    function doExcelDownloadProcess(){
        var f = document.form1;
        f.action = "downloadExcelFile";
        f.submit();
    }

    function doExcelChangeProcess(){
        if(document.getElementById('fileInput').files.length <= 0){
            alert("등록할 파일을 선택해 주세요");
        }else{
            var f = document.form1;
            f.action = "doExcelChangeFile";
            f.submit();
        }

    }
</script>
<form id="form1" name="form1" method="post" enctype="multipart/form-data">
    <input type="file" id="fileInput" name="fileInput">
    <br/><br/><button type="button" onclick="doExcelChangeProcess()">엑셀 제목 변경 작업</button>
</form>
<div id="result">
</div>
</body>
</html>
