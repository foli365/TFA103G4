$('.feat-btn').click(function() {
    $('nav ul.feat-show').toggleClass("show");
});
$('.serv-btn').click(function() {
    $('nav ul.serv-show').toggleClass("show1");
});
$('.bom-btn').click(function() {
    $('nav ul.bom-show').toggleClass("show2");
});
$('.mky-btn').click(function() {
    $('nav ul.mky-show').toggleClass("show3");
});
$("a.product_list").on("click", function() {

});
$("a.alert_managament").on("click", function() {
    $(window).attr('location', 'alert.html');
});
$("a.camp_list").on("click", function() {
    $(window).attr('location', 'camp.html');
});
$("a.product_up").on("click", function() {
    $(window).attr('location', 'product.html');
});
$("h1").on("click", function() {
    $(window).attr('location', 'home.html');
});
$(".min_picture").on("click", function() {
    $(window).attr('location', 'home.html');
});
$("button.button_editok").on("click", function() {
//	alert("abc");
});
$("button.btny").on("click", function() {
    $("div.pop").show();
});
$("button.btny").on("click", function(){
    $("div.bg").toggleClass("-on");
});

$("button.button_cancle").on("click", function() {
    $("div.pop").hide();
});
$("button.button_cancle").on("click", function() {
    $("div.bg").removeClass("-on");
});