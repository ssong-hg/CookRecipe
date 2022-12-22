/**
 * 
 */
 
	$(".uploadedList1").on("click", "span", function(event){
		$(this).parent("div").remove();
	});
		
	function getOriginalName(fileName){
		if(checkImageType(fileName)){
			return;
		}
		
		var idx = fileName.indexOf("_") + 1 ;
		
		return fileName.substr(idx);
	}
	
	function getThumbnailName(fileName){
		var front = fileName.substr(0,12);
		var end = fileName.substr(12);
		
		console.log("front : " + front);
		console.log("end : " + end);
		
		return front + "s_" + end;
	}
	
	function checkImageType(fileName){
		var pattern = /jpg|gif|png|jpeg/i;
		
		return fileName.match(pattern);
	}
	
	$("#create").submit(function(event){
		event.preventDefault();
		
		var that = $(this);
		
		var str ="";
		$(".uploadedList1 a").each(function(index){	
			 var value = $(this).attr("href");
			 value = value.substr(27);
		
			 str += "<input type='hidden' name='files["+index+"]' value='"+ value +"'> ";
		});
		
		console.log("str = " + str);
		
		that.append(str);

		that.get(0).submit();
	});

	 
 	$(".inputFile1").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList1").append(str);
				
				
			}
		});
	});
	
$(".inputFile2").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList2").append(str);
				
				
			}
		});
	});	
	
$(".inputFile3").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList3").append(str);
				
				
			}
		});
	});	
	
	
$(".inputFile4").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList4").append(str);
				
				
			}
		});
	});	
	
	
$(".inputFile5").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList5").append(str);
				
				
			}
		});
	});	
	
	
$(".inputFile6").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList6").append(str);
				
				
			}
		});
	});	
	
	
	
$(".inputFile7").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList7").append(str);
				
				
			}
		});
	});	
	
	
$(".inputFile8").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList8").append(str);
				
				
			}
		});
	});	
	
	
$(".inputFile9").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList9").append(str);
				
				
			}
		});
	});	
	
$(".inputFile10").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList10").append(str);
				
				
			}
		});
	});	
$(".inputFile11").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList11").append(str);
				
				
			}
		});
	});	
	
$(".inputFile12").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList12").append(str);
				
				
			}
		});
	});	
	
$(".inputFile13").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList13").append(str);
				
				
			}
		});
	});	

$(".inputFile14").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList14").append(str);
				
				
			}
		});
	});	
	
	
$(".inputFile15").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList15").append(str);
				
				
			}
		});
	});	
	
$(".inputFile16").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList16").append(str);
				
				
			}
		});
	});	
	
$(".inputFile17").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList17").append(str);
				
				
			}
		});
	});	
	
$(".inputFile18").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList18").append(str);
				
				
			}
		});
	});	
	
$(".inputFile19").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList19").append(str);
				
				
			}
		});
	});	
	
	
$(".inputFile20").on("change", function(event){
		console.log("change");
		
		var files = event.target.files;
		
		var file = files[0];

		console.log(file);
		
		var formData = new FormData();
		
		formData.append("file", file);
		console.log("file",file);
		$.ajax({
			url: "/uploadAjax",
			data: formData,
			dataType:"text",
			processData: false,
			contentType: false,
			type: "POST",
			success: function(data){
				console.log(data);
				console.log(index);
				var str ="";
			  
				if(checkImageType(data)){
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + "<img src='/displayFile?fileName=" + getThumbnailName(data) + "'/>" + "</a><span>X</span></div>";
				}
				else{
					str = "<div><a href='/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a><span>X</span></div>";
				}
			  
				$(".uploadedList20").append(str);
				
				
			}
		});
	});	

	

	
	
	
	
	
	