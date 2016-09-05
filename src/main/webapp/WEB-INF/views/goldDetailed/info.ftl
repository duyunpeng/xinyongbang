[@override name="title"]金币明细 - 金币明细查看[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/gold_detailed/pagination">金币明细</a></li>
    <li>金币明细</li>
</ul>
[/@override]

[@override name="headerText"]
金币明细 查看
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-12">
        <ul class="contract-show">
            <li>
                <span class="col-md-3">用户</span>
                <div class="col-md-8 contract-box">${goldDetailed.user.userName!}</div>
            </li>
            <li>
                <span class="col-md-3">时间</span>
                <div class="col-md-8 contract-box">[@mc.dateShow goldDetailed.createDate/]</div>
            </li>
            <li>
                <span class="col-md-3">类型</span>
                <div class="col-md-8 contract-box">${(goldDetailed.flowType.getName())!}</div>
            </li>
            <li>
                <span class="col-md-3">金币数</span>
                <div class="col-md-8 contract-box">${goldDetailed.goldNumber!}</div>
            </li>
            <li>
                <span class="col-md-3">说明</span>
                <div class="col-md-8 contract-box">${goldDetailed.description!}</div>
            </li>
            <div>
                <div class="col-sm-offset-6 col-sm-12">
                    <a href="[@spring.url '/gold_detailed/pagination' /]" class="btn btn-default">返回列表</a>
                </div>
            </div>
        </ul>
    </div>
</div>
[/@override]

[@override name="bottomResources"]
    [@super /]
[/@override]
[@extends name="/decorator.ftl"/]