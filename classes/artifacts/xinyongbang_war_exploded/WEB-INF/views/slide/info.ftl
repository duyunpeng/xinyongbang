[@override name="title"]幻灯片管理 - 幻灯片查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/slide/pagination">幻灯片管理</a></li>
    <li>幻灯片信息查看</li>
</ul>
[/@override]

[@override name="headerText"]
幻灯片信息 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">排序号</span>
                <div class="col-md-8 contract-box">${slide.sort!}</div>
            </li>
            <li>
                <span class="col-md-3">关联图片</span>
                <div class="col-md-8 contract-box"> <img src="${slide.picture.picPath!}"></div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/slide/create' /]" class="btn btn-success">再创建一个</a>
                    <a href="[@spring.url '/slide/pagination' /]" class="btn btn-default">返回列表</a>
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