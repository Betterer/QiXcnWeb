$("#page_div").bs_pagination({
    currentPage: $("#currentPage").val(),
    rowsPerPage: $("#rowsPerPage").val(),
    maxRowsPerPage: $("#maxRowsPerPage").val(),
    totalPages: $("#totalPages").val(),
    totalRows: $("#totalRows").val(),

    visiblePageLinks: 5,

    showGoToPage: false,
    showRowsPerPage: false,
    showRowsInfo: false,
    showRowsDefaultInfo: false,

    directURL: false, // or a function with current page as argument
    disableTextSelectionInNavPane: false, // disable text selection and double click

    bootstrap_version: "3",

    // bootstrap 3
    containerClass: "center",

    mainWrapperClass: "row",

    navListContainerClass: "col-xs-12 col-sm-12 col-md-6",
    navListWrapperClass: "",
    navListClass: "pagination pagination_custom",
    navListActiveItemClass: "active",

    navGoToPageContainerClass: "col-xs-6 col-sm-4 col-md-2 row-space",
    navGoToPageIconClass: "glyphicon glyphicon-arrow-right",
    navGoToPageClass: "form-control small-input",

    navRowsPerPageContainerClass: "col-xs-6 col-sm-4 col-md-2 row-space",
    navRowsPerPageIconClass: "glyphicon glyphicon-th-list",
    navRowsPerPageClass: "form-control small-input",

    navInfoContainerClass: "col-xs-12 col-sm-4 col-md-2 row-space",
    navInfoClass: "",

    // element IDs
    nav_list_id_prefix: "nav_list_",
    nav_top_id_prefix: "top_",
    nav_prev_id_prefix: "prev_",
    nav_item_id_prefix: "nav_item_",
    nav_next_id_prefix: "next_",
    nav_last_id_prefix: "last_",

    nav_goto_page_id_prefix: "goto_page_",
    nav_rows_per_page_id_prefix: "rows_per_page_",
    nav_rows_info_id_prefix: "rows_info_",

    onChangePage: function(event, data) { // returns page_num and rows_per_page after a link has clicked
        var formId = $("#formId").val();
        var currentPageInputId = $("#currentPageInputId").val();
        console.log(data.currentPage);
        console.log(formId);
        console.log(currentPageInputId);
        $("#"+currentPageInputId).val(data.currentPage);
        $("#"+formId).submit();

    },
    onLoad: function() { // returns page_num and rows_per_page on plugin load
    }
});