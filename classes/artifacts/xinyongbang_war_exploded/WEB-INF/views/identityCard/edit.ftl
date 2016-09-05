[@override name="title"]身份证管理 - 身份证修改[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/identity_card/pagination">身份证管理</a></li>
    <li>身份证修改</li>
</ul>
[/@override]

[@override name="headerText"]
区域 修改
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/identity_card/edit" method="post" data-parsley-validate>

            <input type="hidden" name="id" value="${identityCard.id!command.id}" />
            <input type="hidden" name="version" value="${identityCard.version!command.version}" />

            [@spring.bind "command.cardNumber"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">身份证号码*</label>
                <div class="col-md-9">
                    <input class="form-control" id="name" name="name"
                           value="${identityCard.cardNumber!command.cardNumber}" placeholder="输入身份证号码"
                           data-parsley-required="true" data-parsley-required-messages="身份证号码不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "cardNumber" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.name"/]
            <div class="form-group">
                <label for="shortName" class="col-md-3 control-label">身份证上面的姓名*</label>
                <div class="col-md-9">
                    <input class="form-control" id="name" name="name"
                           value="${identityCard.name!command.name}" placeholder="输入身份证上面的姓名"
                           data-parsley-required="true" data-parsley-required-messages="身份证上面的姓名不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "name" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.frontPic"/]
            <div class="form-group">
                <label for="longitude" class="col-md-3 control-label">身份证正面照*</label>
                <div class="col-md-9">
                    <input class="form-control" id="frontPic" name="frontPic"
                           value="${identityCard.frontPic!command.frontPic}" placeholder="输入身份证正面照片"
                           data-parsley-required="true" data-parsley-required-messages="身份证正面照片不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "frontPic" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.backPic"/]
            <div class="form-group">
                <label for="backPic" class="col-md-3 control-label">身份证背面照面*</label>
                <div class="col-md-9">
                    <input class="form-control" id="backPic" name="backPic"
                           value="${area.backPic!command.backPic}" placeholder="输入身份证背面照面"
                           data-parsley-required="true" data-parsley-required-messages="身份证背面照面不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "backPic" "parsley-required"/]
                </div>
            </div>

            [@spring.bind "command.parent"/]

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
<script src="[@spring.url '/resources/js/area.js'/]"></script>
<script>
    $(".area-cascade").areaCascade("parent");
</script>
[/@override]
[@extends name="/decorator.ftl"/]