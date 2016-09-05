[@override name="title"]用户管理[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li>待认证用户管理</li>
</ul>
[/@override]

[@override name="headerText"]
待认证用户 管理
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]

<div class="smart-widget widget-dark-blue">
    <div class="smart-widget-header">
        <span class="smart-widget-option">
            [#--<span class="refresh-icon-animated" style="display: none;"><i--]
            [#--class="fa fa-circle-o-notch fa-spin"></i></span>--]
                <a href="#" class="widget-toggle-hidden-option"><i class="fa fa-cog"></i></a>
            <a href="#" class="widget-collapse-option" data-toggle="collapse"><i class="fa fa-chevron-up"></i></a>
            [#--<a href="#" class="widget-refresh-option"><i class="fa fa-refresh"></i></a>--]
            <a href="#" class="widget-remove-option"><i class="fa fa-times"></i></a>
        </span>
        <form class="form-inline no-margin" role="form">
            <div class="form-group">
                <label for="userName" class="control-label">用户名</label>
                <input type="text" class="form-control" id="userName" name="userName" value="${command.userName!}"
                       placeholder="用户名"/>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-md btn-success">查询</button>
            </div>
        </form>
    </div>
    <div class="smart-widget-inner">
        <div class="smart-widget-hidden-section" style="display: none;">
            <ul class="widget-color-list clearfix">
                <li style="background-color:#20232b;" data-color="widget-dark"></li>
                <li style="background-color:#4c5f70;" data-color="widget-dark-blue"></li>
                <li style="background-color:#23b7e5;" data-color="widget-blue"></li>
                <li style="background-color:#2baab1;" data-color="widget-green"></li>
                <li style="background-color:#edbc6c;" data-color="widget-yellow"></li>
                <li style="background-color:#fbc852;" data-color="widget-orange"></li>
                <li style="background-color:#e36159;" data-color="widget-red"></li>
                <li style="background-color:#7266ba;" data-color="widget-purple"></li>
                <li style="background-color:#f5f5f5;" data-color="widget-light-grey"></li>
                <li style="background-color:#fff;" data-color="reset"></li>
            </ul>
        </div>
        <div class="smart-widget-body no-padding">
            <div class="padding-md">
                <section class="overflow-auto nice-scrollbar">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>用户名</th>
                            <th>创建时间</th>
                            <th>昵称</th>
                            <th>角色</th>
                            <th>状态</th>
                            <th>查看身份证信息</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as user ]
                                <tr>
                                    <td>${user.userName!}</td>
                                    <td>[@mc.dateShow user.createDate/]</td>
                                    <td>${user.name!}</td>
                                    <td>${user.roles[0].name!}</td>
                                    <td>${(user.status.getName())!}</td>
                                    <td>
                                            <a href="javascript:void(0)" class="identity"
                                               data-name="${user.identityCard.name!}"
                                               data-cardNumber="${user.identityCard.cardNumber!}"
                                               data-frontPic="${user.identityCard.frontPic.picPath!}"
                                               data-backPic="${user.identityCard.backPic.picPath!}"
                                               data-handTakePic="${user.identityCard.handTakePic.picPath!}"
                                               data-toggle="identity" data-placement="top" title="点击查看身份证信息">
                                                <span class="label label-info">查看</span>
                                            </a>
                                    </td>
                                    <td>
                                        <a href="[@spring.url '/user/info/${user.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击查看详情">
                                            <span class="label label-info">查看</span>
                                        </a>
                                        <a href="[@spring.url '/user/approve_pass?id=${user.id!}&version=${user.version!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击认证通过">
                                            <span class="label label-success">认证通过</span>
                                        </a>
                                        <a href="[@spring.url '/user/approve_reject?id=${user.id!}&version=${user.version!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击认证驳回">
                                            <span class="label label-success">驳回</span>
                                        </a>
                                    </td>
                                </tr>
                                [/#list]
                            [/#if]
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="bg-grey">
                [#if pagination!]
            [@mc.showPagination '/user/pagination?userName=${command.userName!}&appKey=${command.appKey!}&status=${command.status!}' /]
        [/#if]
            </div>
        </div>

    </div>
</div>


<div class="modal fade" id="identity">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">身份证信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <ul class="contract-show">
                        <li>
                            <span class="col-md-3">名字</span>
                            <div class="col-md-8 contract-box name"></div>
                        </li>
                        <li>
                            <span class="col-md-3">身份证号码</span>
                            <div class="col-md-8 contract-box number"></div>
                        </li>
                        <li>
                            <span class="col-md-3">身份证正面照片</span>
                            <div class="col-md-8 contract-box frontPic"><img src=""></div>
                        </li>
                        <li>
                            <span class="col-md-3">身份证背面照片</span>
                            <div class="col-md-8 contract-box backPic"><img src=""></div>
                        </li>
                        <li>
                            <span class="col-md-3">手持身份证照片</span>
                            <div class="col-md-8 contract-box handTakePic"><img src=""></div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

    [#include 'shared/confirmation.ftl'/]
[/@override]

[@override name="bottomResources"]
    [@super /]
<script src="[@spring.url '/resources/js/ajax.js'/]"></script>
<script type="text/javascript">
    $("#appKey").selectAjaxData({url: "/app_key/all_list"});

    $(".identity").on("click", function () {
        var _that = $("#identity");
        var data_name = $(this).attr("data-name");
        var data_cardNumber = $(this).attr("data-cardNumber");
        var data_frontPic = $(this).attr("data-frontPic");
        var data_backPic = $(this).attr("data-backPic");
        var data_handTakePic = $(this).attr("data-handTakePic");
        _that.find(".name").text(data_name);
        _that.find(".number").text(data_cardNumber);
        _that.find(".frontPic img").attr("src", data_frontPic);
        _that.find(".backPic img").attr("src", data_backPic);
        _that.find(".handTakePic img").attr("src", data_handTakePic);
        _that.modal();
    })
</script>
[/@override]
[@extends name="/decorator.ftl"/]