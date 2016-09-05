[@override name="title"]文章类型管理 - 文章类型创建[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/article_type/pagination">文章类型管理</a></li>
    <li>文章类型创建</li>
</ul>
[/@override]

[@override name="headerText"]
文章类型 创建
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/article_type/edit" method="post" data-parsley-validate>

            <input type="hidden" name="id" value="${articleType.id!command.id}"/>
            <input type="hidden" name="version" value="${articleType.version!command.version}"/>

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label for="title" class="col-md-3 control-label">文章类型名称*</label>
                <div class="col-md-9">
                    <input class="form-control" id="name" name="name"
                           value="${articleType.name!command.name}" placeholder="输入文章类型名称"
                           data-parsley-required="true" data-parsley-required-messages="文章类型名称不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "name" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">修改</button>
            </div>
        </form>
    </div>
    <div class="col-lg-3">
        <ul class="blog-sidebar-list font-18">创建注意事项
            <li>*位必填项</li>
        </ul>
    </div>
</div>

[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]
[@extends name="/decorator.ftl"/]