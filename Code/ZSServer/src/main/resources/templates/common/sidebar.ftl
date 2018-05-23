 <div class="left-nav posa">
            <ul>
            <@shiro.hasPermission name="industry_map">
                <li class="active">
                    <a href="/indusMap/industryMap.html">
                        <div class="circle-out industryIcon">
                            <div class="circle-in">

                            </div>
                        </div>
                        产业地图
                    </a>
                    <ul class="left-nav-menu">
                       <@shiro.hasPermission name="industry_dynamics">
                        <li id="dynamic">
                            <a href="/indus/industryDynamics.html">产业动态</a>
                        </li>
                       </@shiro.hasPermission>
                       <@shiro.hasPermission name="industry_summit">
                        <li id="summit">
                            <a href="/summit/industrySummitMeeting.html">产业峰会</a>
                        </li>
                       </@shiro.hasPermission>
                       <@shiro.hasPermission name="knowledge_map">
                        <li id="atlas">
                            <a href="/apis/atlas/chain.html">知识图谱</a>
                        </li>
                       </@shiro.hasPermission>
                    </ul>
                </li>
              </@shiro.hasPermission>
             <@shiro.hasPermission name="park_map">
                <li>
                    <a href="/apis/area/garden/gardenMap.html">
                        <div class="circle-out parkIcon">
                            <div class="circle-in">

                            </div>
                        </div>
                        园区地图
                    </a>
                    <ul class="left-nav-menu">
                 <@shiro.hasPermission name="global_park">
                        <li>
                            <a href="/apis/area/garden/allCityPark.html">全域园区</a>
                        </li>
           </@shiro.hasPermission>
                   <@shiro.hasPermission name="focus_on_the_park">
                        <li>
                            <a href="/apis/area/garden/followPark.html">关注园区</a>
                        </li>
                   </@shiro.hasPermission>
                    </ul>
                </li>
             </@shiro.hasPermission>
            <@shiro.hasPermission name="financing_express">
                <li>
                    <a href="/apis/financing/financingExpress.html">
                        <div class="circle-out rzIcon">
                            <div class="circle-in">

                            </div>
                        </div>
                        融资速递
                    </a>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="investment_report">
                <li>
                    <a href="/apis/report/industryReport.html">
                        <div class="circle-out merchantsIcon">
                            <div class="circle-in">

                            </div>
                        </div>
                        招商报告
                    </a>
                </li>
           </@shiro.hasPermission>
            </ul>
        </div>
<!-- header部分  end -->