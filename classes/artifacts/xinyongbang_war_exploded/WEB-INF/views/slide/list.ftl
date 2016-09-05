[@override name="title"]幻灯片管理[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li>幻灯片管理</li>
</ul>
[/@override]


[@override name="headerText"]
幻灯片 管理
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]

<div class="row margin-md">
    <a href="/slide/create" class="btn btn-md btn-success">新增幻灯片</a>
</div>

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
                            <th>排序</th>
                            <th>关联图片</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            [#if pagination.data??]
                                [#list pagination.data as slide ]
                                <tr>
                                    <td>${slide.sort!}</td>
                                    <td>
                                        <a href="javascript:void(0)" class="slide-but"  data="${slide.picture.picPath!}"
                                           data-toggle="tooltip" data-placement="top" title="点击查看图片">
                                            <span class="label label-info">查看</span>
                                        </a>
                                    </td>
                                    <td>
                                        <a href="[@spring.url '/slide/info/${slide.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击查看详情">
                                            <span class="label label-info">详情</span>
                                        </a>
                                        <a href="[@spring.url '/slide/edit/${slide.id!}'/]"
                                           data-toggle="tooltip" data-placement="top" title="点击修改信息">
                                            <span class="label label-success">修改</span>
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
            [@mc.showPagination '/identity_card/pagination?cardNumber=${command.cardNumber!}&name=${command.name!}' /]
        [/#if]
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="Pic">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title">关联图片</h4>
            </div>
            <div class="modal-body">
                <div>
                    <img class="slide-pic" src="" alt="">
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
<script type="text/javascript">
    $(".slide-but").on("click", function () {
        var _src = $(this).attr("data");
        var _that = $("#Pic");
        $(_that).find(".slide-pic").attr("src", _src)
        _that.modal();
    })
</script>
[/@override]
[@extends name="/decorator.ftl"/]


