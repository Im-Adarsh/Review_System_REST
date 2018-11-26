var rootURL = "http://localhost:8083/items/";
var globalVariable;
findAll();
$('#editReviewForm').hide();

var currentItem;

function showAvgRating(itemName){
	$.ajax({
			type:'GET',
			contentType: 'application/json',
			url: rootURL + itemName +'/view_item_average_rating',
			data:'text',
			success: function(data){
				$.confirm({
			    title: 'Average Rating for ' + itemName.toUpperCase(),
			    content: itemName.toUpperCase() + ' has an average rating of ' +data +'.',
			    buttons: {
			        OK: function () {
			            return
			        },
			        Reviews: function () {
			        	allReviews(itemName);
			        },
			    }
			});
			}
	});
}

$(document).ready(function(){
	$("#pen").click(function(){
		showAvgRating('pen')
	  });
	
	$("#pencil").click(function(){
		showAvgRating('pencil')
	  });
	
	$("#Keyboard").click(function(){
		showAvgRating('Keyboard')
	  });
	
	$("#Bike").click(function(){
		showAvgRating('Bike')
	  });
});

function findAll() {
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json", // data type of response
		success : renderList

	});
}

$('#btnSave').click(function() {
	addItem();
});

function addItem() {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL +  $('#itemName').val() + '/write_review',
		data : formToJSON(),
		success : function(data, textStatus, jqXHR) {
			alert('Item added successfully!')
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('addItem error: ' + textStatus);
		}
	});
}
function formToJSON() {
	return JSON.stringify(
			{
			"comment" : $('#comment').val(),
			"rating" : parseInt($("input[name=rating]:checked").val()),
			"author" : $('#authorName').val()
	}
			);
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);
	$('#itemList li').remove();
	$.each(list, function(index, item) {
		
		$('#itemList').append(
				'<li class="list-group-item list-group-item-light"><a href="javascript:allReviews(\''+item.name+'\')" data-trigger="hover" id="'+item.name+'" data-content="Some content">'+ toUpper(item.name) +'</a></li>'
				);
	});
}

$('#addReviewFormLink').click(function() {
	$('#showReviews tr').remove();
	$('#reviewTable').hide();
	$('#addReviewForm').show();
});

$('#editBtnSave').click(function(){
	editItem();
});

function editItem() {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL +  currentItem + '/edit_item_review',
		dataType : "json",
		data : editFormToJSON(),
		success : function(data, textStatus, jqXHR) {
			alert('Item added successfully')
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('addItem error: ' + textStatus);
		}
	});
}

function editFormToJSON() {
	return JSON.stringify(
			{
			"comment" : $('#editComment').val(),
			"rating" : parseInt($("input[name=editRating]:checked").val()),
			"author" : $('#editAuthorName').val()
	}
			);
}

$('#editReviewLink').click(function() {
	$('#showReviews tr').remove();
	$('#reviewTable').hide();
	$('#addReviewForm').hide();
	$('#editReviewForm').show();
});

function allReviews(itemName) {
	currentItem = itemName;
	$('#addReviewForm').hide();
	$('#reviewTable').show();
	$.ajax({
		type : 'GET',
		url : rootURL +  itemName,
		dataType : "json", // data type of response
		success : showReviews

	});
}
function setValue(authorName){
	$('#editAuthorName').val(authorName)
	$('#fixedItemName').val(currentItem);
	$('#editReviewForm').show();
	
}
var globalAuthor;
function showReviews(data){
	$('#headings tr').remove();
	$('#showReviews tr').remove();
	
	$('#headings').append('<tr>	<th>Rating</th><th>Comment</th><th>Author</th></tr>');
	$.each(data.review,function(index,rev){
		$('#showReviews').append('<tr title="Click to edit your review"><td>'+rev.rating+'</td><td>'+rev.comment+'</td><td><a href="javascript:setValue(\''+rev.author+'\')">'+rev.author+'</td></a></tr>'
		);
	})
	
}

function toUpper(itemName) {
	return itemName.toUpperCase()
}
