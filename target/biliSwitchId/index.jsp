<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- bootstrap4 -->
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style type="text/css">
#content {
	margin-top: 100px;
}

</style>
<title>bilibili AV号转BV号工具</title>
</head>
<body>
	<div id="content" class="mx-auto bg-warning" style="width: 700px">
	<h1><p class="mx-auto bg-warning bg-primary text-white" style="width:450px;margin-bottom: 50px;">bilibili AV号转BV号工具</p></h1>
		<div class="input-group mb-3" style="width: 300px; float: left;">
			<div class="input-group-prepend">
				<span class="input-group-text">AV</span>
			</div>
			<input id="av" type="text" class="form-control" placeholder="AV">
		</div>
		<button id="switch" type="button" class="btn btn-success"
			style="margin-left: 5px; margin-right: 5px; float: left;"><-></button>
		<div class="input-group mb-3" style="width: 300px; float: left;">
			<div class="input-group-prepend">
				<span class="input-group-text">BV</span>
			</div>
			<input id="bv" type="text" class="form-control" placeholder="BV">
		</div>
	</div>
	<div style="margin-top: 150px; text-align: center;">
	<p>av号填入格式如:170001, av170001</p>
	<p>bv号填入格式如:BV17x411w7KC</p>
	</div>

	<script type="text/javascript">
		$(function() {
			$('#switch').click(function() {
				var av = $('#av').val();
				var bv = $('#bv').val();
				var flag;
				var parameter;
				
				if (av != null && av != "") {
					av = av.replace("av","");
					parameter = 'aid='+av;
					flag = 'bv';
				} else if (bv != null && bv != "") {
					parameter = 'bvid='+bv;
					flag = 'av';
				}
				$.post('switch.do?op=query', {
					'parameter' : parameter
				}, function(data) {
						if (data == 0){
							alert('您的输入有误！');
						}else{
							if(flag == "av")
								$('#'+flag).val("av" + data);
							else
								$('#'+flag).val(data);
						}
				});
			});
		});
	</script>
</body>
</html>









