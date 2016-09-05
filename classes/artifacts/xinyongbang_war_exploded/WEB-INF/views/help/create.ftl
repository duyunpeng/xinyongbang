[@override name="title"]版本管理 - 版本创建[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb" xmlns="http://www.w3.org/1999/html">
    <li><a href="/">首页</a></li>
    <li><a href="/app_Version/pagination">帮助管理</a></li>
    <li>帮助创建</li>
</ul>
[/@override]

[@override name="headerText"]
帮助 创建
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/help/create" method="post" data-parsley-validate>

            [@spring.bind "command.title"/]
            <div class="form-group">
                <label for="title" class="col-md-3 control-label">标题*</label>
                <div class="col-md-9">
                    <input class="form-control" id="title" name="title"
                           value="${command.title!}" placeholder="输入标题"
                           data-parsley-required="true" data-parsley-required-messages="标题不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "appVersion" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.content"/]
            <div class="form-group">
                <label for="updateContent" class="col-md-3 control-label">内容*</label>
                <div class="col-md-9">
                    <textarea rows="5" cols="68" placeholder="输入内容" id="content" name="content"
                              data-parsley-required="true" data-parsley-required-messages="更新内容不能为空"
                              data-parsley-trigger="change">${command.updateContent!}</textarea>
                    [@spring.showErrors "updateContent" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.status"/]
            <div class="form-group">
                <label for="status" class="col-md-3 control-label">状态*</label>
                <div class="col-md-9">
                    <select class="form-control" name="status" id="status"
                            data-parsley-required="true" data-parsley-required-messages="请选择帮助状态"
                            data-parsley-trigger="change">
                        [#assign status = (command.status!)?default("") /]
                        <option value="">请选择</option>
                        <option value="ENABLE" [@mc.selected status "ENABLE"/]>启用</option>
                        <option value="DISABLE" [@mc.selected status "DISABLE"/]>禁用</option>
                    </select>
                    [@spring.showErrors "status" "parsley-required"/]
                </div>
            </div>

            <div class="text-center m-top-md">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-success">创建</button>
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