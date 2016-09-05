[@override name="title"]幻灯片管理 - 幻灯片创建[/@override]
[@override name="topResources"]
    [@super /]

[/@override]

[@override name="breadcrumb"]
<ul class="breadcrumb">
    <li><a href="/">首页</a></li>
    <li><a href="/slide/pagination">幻灯片管理</a></li>
    <li>幻灯片创建</li>
</ul>
[/@override]

[@override name="headerText"]
幻灯片 创建
[/@override]

[@override name="subContent"]
    [@mc.showAlert /]
<div class="row">
    <div class="col-lg-8">
        <form class="form-horizontal" action="/slide/create" method="post" data-parsley-validate>

            [@spring.bind "command.sort"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">排序*</label>
                <div class="col-md-9">
                    <input class="form-control" id="sort" name="sort"
                           value="${command.sort!}" placeholder="输入排序"
                           data-parsley-required="true" data-parsley-required-messages="排序不能为空"
                           data-parsley-trigger="change"/>
                    [@spring.showErrors "sort" "parsley-required"/]
                </div>
            </div>
            [@spring.bind "command.picture"/]
            <div class="form-group">
                <label for="name" class="col-md-3 control-label">上传幻灯片图片*</label>
                <div class="col-md-9">
                    <a class="btn btn-md  btn-success img-upload">点击添加图片</a>
                    <section class="overflow-auto nice-scrollbar">
                        <ul class="img-box">
                        </ul>
                    </section>
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
[#--文件上传进度--]
<div class="file_upload_load"></div>
<script type="application/javascript" src="[@spring.url '/resources/js/layer/layer.js'/]"></script>
<script src="[@spring.url '/resources/js/upload/webuploader.js'/]"></script>
<script type="text/javascript">

    // 初始化Web Uploader
    uploader = WebUploader.create({
        // 自动上传。
        auto: true,
        // 文件接收服务端。
        server: '/upload/img_upload',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '.img-upload',
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });
    // 当有文件添加进来的时候
    uploader.on('fileQueued', function (file) {
        uploader.makeThumb(file, function (error, src) {
            if (error) {
                return;
            }
//                alert(src);
        });
    });
    uploader.on('uploadProgress', function (file, percentage) {
        $('html').addClass('.file_upload_mask');
        $('.file_upload_load').show();
    });

    uploader.on('uploadSuccess', function (file, result) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传成功！", {icon: 1});
        var url = result.files[0].url;
        var $ul = $(".img-box");
        $ul.empty();
        $ul.append('<li><img src=' + url + '/><input type="hidden" value=' + url + ' name="picture"/><div><a href="#" class="btn btn-danger del-img">删除</a></div></li>');
    });

    uploader.on('uploadError', function (handler) {
        $('html').removeClass('.file_upload_mask');
        $('.file_upload_load').hide();
        layer.msg("上传失败！");
    });

    $(".img-box").on("click", "a", function () {
        $(this).parent().parent().remove();
    })
</script>

[/@override]
[@extends name="/decorator.ftl"/]