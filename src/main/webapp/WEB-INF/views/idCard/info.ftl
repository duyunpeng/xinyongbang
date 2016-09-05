[@override name="title"]身份证信息管理 - 身份证信息查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/id_card/pagination">身份证信息管理</a></li>
    <li>身份证信息查看</li>
</ul>
[/@override]

[@override name="headerText"]
身份证信息 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">身份证号码</span>
                <div class="col-md-8 contract-box">${idCard.cardNumber!}</div>
            </li>
            <li>
                <span class="col-md-3">身份证上面的姓名</span>
                <div class="col-md-8 contract-box">${idCard.name!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/id_card/pagination' /]" class="btn btn-default">返回列表</a>
                </div>
            </div>
        </ul>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
<script type="text/javascript">
    var data_list = $(".data-list");
    data_list.slimScroll({
        height: '600px'
    });
</script>
[/@override]
[@extends name="/decorator.ftl"/]