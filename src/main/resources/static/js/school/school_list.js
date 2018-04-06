

//选择查询条件时
$(".action_options").on('click',function(){
    var name = $(this).attr('data');
    var value = $(this).html();
    $('#search_by').html(value);
    $('#searchBy').val(name);
});

$(".school_search_btn").on('click',function(){
    $("#school_search").submit();
});