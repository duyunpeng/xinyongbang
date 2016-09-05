[@override name="title"]帮助管理 - 版本查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/app_Version/pagination">帮助管理</a></li>
    <li>帮助查看</li>
</ul>
[/@override]

[@override name="headerText"]
帮助 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">标题</span>
                <div class="col-md-8 contract-box">${help.title!}</div>
            </li>
            <li>
                <span class="col-md-3">创建时间</span>
                <div class="col-md-8 contract-box">[@mc.dateShow help.createDate!/]</div>
            </li>
            <li>
                <span class="col-md-3">内容</span>
                <div class="col-md-8 contract-box">${help.content!}</div>
            </li>
            <li>
                <span class="col-md-3">状态</span>
                <div class="col-md-8 contract-box">${(help.status.getName())!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/help/create' /]" class="btn btn-success">再创建一个</a>
                    <a href="[@spring.url '/help/pagination' /]" class="btn btn-default">返回列表</a>
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