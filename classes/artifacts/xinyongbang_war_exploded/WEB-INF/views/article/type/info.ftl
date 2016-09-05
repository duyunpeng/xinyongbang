[@override name="title"]文章类型管理 - 文章类型查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/article_type/pagination">文章类型管理</a></li>
    <li>文章类型查看</li>
</ul>
[/@override]

[@override name="headerText"]
文章类型 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">文章类型名称</span>
                <div class="col-md-8 contract-box">${articleType.name!}</div>
            </li>
            <li>
                <span class="col-md-3">文章类型创建时间</span>
                <div class="col-md-8 contract-box">[@mc.dateShow articleType.createDate/]</div>
            </li>
            <li>
                <span class="col-md-3">文章类型状态</span>
                <div class="col-md-8 contract-box">${(articleType.status.getName())!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/article_type/create' /]" class="btn btn-success">再创建一个</a>
                    <a href="[@spring.url '/article_type/pagination' /]" class="btn btn-default">返回列表</a>
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