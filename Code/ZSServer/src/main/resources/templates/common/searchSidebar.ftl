<div class="left-nav">
            <ul>
            <@shiro.hasPermission name="intelligent_recommendation">
                <li id="recommend">
                    <a href="/intelligent/intelligentRecommendation.html">
                        <div class="circle-out industryIcon">
                            <div class="circle-in">

                            </div>
                        </div>
                        智能推荐
                    </a>
                </li>
                </@shiro.hasPermission>
            <@shiro.hasPermission name="accurate_screening">
                <li id="screen">
                    <a href="/accurateScreening/accurateScreening.html ">
                        <div class="circle-out parkIcon">
                            <div class="circle-in">

                            </div>
                        </div>
                        精准筛选
                    </a>
                </li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="directional_search">
                <li id="search">
                    <a href="/apis/getcompany/beamSearch.html">
                        <div class="circle-out rzIcon">
                            <div class="circle-in">

                            </div>
                        </div>
                        定向搜索
                    </a>
                </li>
                </@shiro.hasPermission>
            </ul>
        </div>