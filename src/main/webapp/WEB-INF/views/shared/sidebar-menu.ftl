<aside class="sidebar-menu fixed">
    <div class="sidebar-inner scrollable-sidebar">
        <div class="main-menu">
            <ul class="accordion" id="sidebar">
                <li class="menu-header">
                    Main Menu
                </li>
                <li class="bg-palette1 active">
                    <a href="/">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-home fa-lg"></i></span>
                            <span class="text m-left-sm">首页</span>
                        </span>
                    </a>
                </li>
                <li class="openable bg-palette2">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-users fa-lg"></i></span>
                            <span class="text m-left-sm">用户管理</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/user/pagination'/]">
                                <span class="submenu-label">用户管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/user/user_approve'/]">
                                <span class="submenu-label">待认证用户</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="openable bg-palette3">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-book fa-lg"></i></span>
                            <span class="text m-left-sm">文章</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/article/pagination'/]">
                                <span class="submenu-label">文章管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/article_type/pagination'/]">
                                <span class="submenu-label">文章类型管理</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="bg-palette2">
                    <a href="[@spring.url '/gold_detailed/pagination'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-money fa-lg"></i></span>
                            <span class="text m-left-sm">金币明细</span>
                        </span>
                    </a>
                </li>
                <li class="bg-palette2">
                    <a href="[@spring.url '/slide/pagination'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-camera fa-lg"></i></span>
                             <span class="submenu-label">幻灯片管理</span>
                        </span>
                    </a>
                </li>
                <li class="bg-palette2">
                    <a href="[@spring.url '/report/pagination'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-bullhorn fa-lg"></i></span>
                             <span class="submenu-label">举报管理</span>
                        </span>
                    </a>
                </li>
                <li class="bg-palette2">
                    <a href="[@spring.url '/help/pagination'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-money fa-lg"></i></span>
                            <span class="text m-left-sm">帮助</span>
                        </span>
                    </a>
                </li>
                <li class="openable bg-palette4">
                    <a href="#">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa fa-list fa-lg"></i></span>
                            <span class="text m-left-sm">系统设置</span>
                            <span class="submenu-icon"></span></span>
                        <span class="menu-content-hover block">Menu</span>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="[@spring.url '/area/pagination'/]">
                                <span class="submenu-label">区域管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/app_key/pagination'/]">
                                <span class="submenu-label">AppKey管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/permission/pagination'/]">
                                <span class="submenu-label">权限管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/role/pagination'/]">
                                <span class="submenu-label">角色管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/account/pagination'/]">
                                <span class="submenu-label">账号管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/app_Version/pagination'/]">
                                <span class="submenu-label">版本管理</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/feed_back/pagination'/]">
                                <span class="submenu-label">意见反馈</span>
                            </a>
                        </li>
                        <li>
                            <a href="[@spring.url '/id_card/pagination'/]">
                                <span class="submenu-label">身份证信息</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="bg-palette1">
                    <a href="[@spring.url '/logout'/]">
                        <span class="menu-content block">
                            <span class="menu-icon"><i class="block fa ion-log-out fa-lg"></i></span>
                            <span class="text m-left-sm">退出</span>
                        </span>
                    </a>
                </li>
            </ul>
        </div>
    </div><!-- sidebar-inner -->
</aside>