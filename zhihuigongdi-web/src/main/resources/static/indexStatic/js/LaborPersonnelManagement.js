$(document).ready(function() {
    // 在册作业人员所属单位分析
    LaborUnit();

    // 在册人员年龄段统计
    AgeOfWorkers();

    // 在册人员技术等级分析
    LaborTechnicalLevel();

    // 危险报警
    DangerAlarm("1");

    //今日作业人数
    NumberWorkersToday();

    // 黑名单
    BlackList();
   
})

function LaborUnit(){
    $.ajax({
        url: getRootPath() + '/index/analysisOfTheCompanyToWhichTheRegisteredOperatorsBelong',
        dataType: 'json',
        cache: false,
        type: 'get',
        success: function(data){
            var indexBeamListType = [];
                seriesDtae = [];
            $.each(data.indexBeamList,function(key,value){
                indexBeamListType.push(value.type)
                seriesDtae.push({value:value.number, name:value.type})
            })

            // 初始化echarts图表(折线图)
            let LaborUnit = echarts.init(document.getElementById('LaborUnit'));

            // 数据加载完之前先显示一段简单的loading动画
            LaborUnit.showLoading();

            // 加载图表
            LaborUnit.setOption({
                tooltip : {
                    trigger: 'item',
                    // formatter: "{b} : {c} ({d}%)"
                    formatter: "{b}占{d}%"
                },
                grid:{
                    x : 100,
                    y : 'top',
                    padding: 50,
                   
                },
                legend: {
                    orient: 'vertical',
                    right: '10%',
                    y : 'center',
                    textStyle: {
                        color: function (params){
                            var colorList = ['#43bcff','#40e4e7','#c600ff','#3b6fff'];
                            return colorList[params.dataIndex];
                        },
                    },
                    itemWidth: 15,
                    itemHeight: 15,
                    itemGap: 15,
                    data:indexBeamListType,
                    
                },
                color:['#43bcff','#40e4e7','#c600ff','#3b6fff'],
                series : [{
                    type:'pie',
                    radius: ['40%', '60%'],
                    center: ['35%','55%'],
                    label: {
                        normal: {
                            formatter: '{c}人',
                            textStyle: {
                                color: '#FFFFFF'
                            }
                        }
                    },
                    labelLine:{  
                        normal:{  
                             length:25,
                             lineStyle: {
                                color: "#FFFFFF"
                             }
                        },
                   },
                    data:seriesDtae
                }]
            });

            // 关闭动画
            LaborUnit.hideLoading();
        },
        error:function(){
            $("#LaborUnit").html("数据请求失败！");
        }
    });
}

function AgeOfWorkers(){
    $.ajax({
        url: getRootPath() + '/index/ageStatisticsOfRegisteredPersonnel',
        dataType: 'json',
        cache: false,
        type: 'get',
        success: function(data){
            var AgeOfWorkersxAxisDate = [];
                seriesAgeOfWorkersDate = [];
            $.each(data.indexBeamList,function(key,value){
                AgeOfWorkersxAxisDate.push(value.type)
                seriesAgeOfWorkersDate.push(value.number)
            })

             // 初始化echarts图表(折线图)
            let AgeOfWorkers = echarts.init(document.getElementById('AgeOfWorkers'));

            // 数据加载完之前先显示一段简单的loading动画
            AgeOfWorkers.showLoading();

            // 加载图表
            AgeOfWorkers.setOption({
                tooltip : {
                    trigger: 'item',
                    formatter: "{a}:{c}人"
                },
                grid:{
                  left:"5%",
                  right:"5%",
                  top:"20%",
                  bottom:"5%",
                  containLabel: true
                },
                xAxis: {
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#9fceff'
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#7389b9'
                        }
                    },
                    data: AgeOfWorkersxAxisDate
                },
                yAxis: {
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: ['#0f3298'],
                            type: 'solid',
                        }
                    },
                    axisLabel: {
                        interval: 0,
                        margin: 10,
                        textStyle: {
                            color: "#9fceff",
                            
                        }
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#7389b9'
                        }
                    }
                },
                series: [{
                    name: '人数统计为',
                    type: 'bar',
                    barWidth: 20,
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
                                offset: 0,
                                color: "#007fff" // 0% 处的颜色
                            },{
                                offset: 0.5,
                                color: "#01abff" // 100% 处的颜色
                            },{
                                offset: 1,
                                color: "#02deff" // 100% 处的颜色
                            }], false)
                            
                        }
                    },
                    data: seriesAgeOfWorkersDate
                }]
            });

            // 关闭动画
            AgeOfWorkers.hideLoading();
        },
        error:function(){
            $("#AgeOfWorkers").html("数据请求失败！");
        }
    })
}

function LaborTechnicalLevel(){
    $.ajax({
        url: getRootPath() + '/index/analysisOfTechnicalLevelOfRegisteredPersonnel',
        dataType: 'json',
        cache: false,
        type: 'get',
        success: function(data){
            var LaborTechnicalLevelAxisDate = [];
                seriesLaborTechnicalLevelDate = [];
            $.each(data.indexBeamList,function(key,value){
                LaborTechnicalLevelAxisDate.push(value.type)
                seriesLaborTechnicalLevelDate.push({value:value.number, name:value.type})
            })
            // 初始化echarts图表(折线图)
            let LaborTechnicalLevel = echarts.init(document.getElementById('LaborTechnicalLevel'));

            // 数据加载完之前先显示一段简单的loading动画
            LaborTechnicalLevel.showLoading();

            // 加载图表
            LaborTechnicalLevel.setOption({
                tooltip : {
                    trigger: 'item',
                    formatter: "{b}有{c}人"
                },
                grid:{
                    x : 100,
                    y : 'top',
                    padding: 20
                },
                legend: {
                    x : 'center',
                    y : 'bottom',
                    textStyle: {
                        color: function (params){
                            var colorList = ['#43bcff','#40e4e7','#c600ff','#3b6fff'];
                            return colorList[params.dataIndex];
                        },
                    },
                    itemWidth: 15,
                    itemHeight: 15,
                    itemGap: 50,
                    data:LaborTechnicalLevelAxisDate
                },
                color:['#43bcff','#40e4e7','#c600ff','#3b6fff'],
                series : [{
                    type:'pie',
                    radius: ['40%', '60%'],
                    center: ['50%','50%'],
                    label: {
                        normal: {
                            formatter: '{d}%',
                            textStyle: {
                                color: '#FFFFFF'
                            }
                        }
                    },
                    labelLine:{  
                        normal:{  
                             length:25,
                             lineStyle: {
                                color: "#FFFFFF"
                             }
                        },
                   },
                    data:seriesLaborTechnicalLevelDate
                }]
            });
            // 关闭动画
            LaborTechnicalLevel.hideLoading();
        },
        error:function(){
            $("#LaborTechnicalLevel").html("数据请求失败！");
        }
    })
}


function  DangerAlarm(type){
    $.ajax({
        url: getRootPath() + '/index/dangerAlarm?type=' + type,
        dataType: 'json',
        cache: false,
        type: 'get',
        success: function(data){
            $("#DangerAlarm").html("")
            var DangerAlarm = "";
            $.each(data.indexBeamList,function(key,value){
                DangerAlarm  += "<p><span>" + value.type +"</span>"+ value.number +"<span></span>例</p>"
            })
            $("#DangerAlarm").html(DangerAlarm);
        },
        error:function(){
            $("#DangerAlarm").html("数据请求失败！");
        }
    })
}

function NumberWorkersToday(){
    $.ajax({
        url: getRootPath() + '/index/numberOfPeopleWorkingToday',
        dataType: 'json',
        cache: false,
        type: 'get',
        success: function(data){
            $("#NumberWorkersToday").html("")
            var NumberWorkersToday = "";
            $.each(data.indexBeamList,function(key,value){
                NumberWorkersToday  += "<p><span>"+ value.type +"</span><span>"+ value.number +"</span>人</p>"
            })
            $("#NumberWorkersToday").html(NumberWorkersToday);
        },
        error:function(){
            $("#NumberWorkersToday").html("数据请求失败！");
        }
    })
}

var dataBlackList = '';
function BlackList(){
    $.ajax({
        url: getRootPath() + '/index/blackList',
        dataType: 'json',
        cache: false,
        type: 'get',
        success: function(data){
            dataBlackList = data.blackList;
            $(".BlacklistRepositoryMember").html("");
            var BlacklistRepositoryMember = ""
            $.each(dataBlackList,function(key,value){
                BlacklistRepositoryMember += 
                 '<tr>' +
                    '<td>'+ value.name +'</td>' +
                    '<td>'+ value.sex +'</td>' +
                    '<td>'+ value.identityNumber +'</td>' +
                    '<td>'+ value.belongCompany +'</td>' +
                '</tr>';
            })
            $(".BlacklistRepositoryMember").html(BlacklistRepositoryMember);
            MyMarhq();
            
        },
        error:function(){
            $("#DangerAlarm").html("数据请求失败！");
        }
    })
}

function MyMarhq(){
    var MyMarhq = '';
    clearInterval(MyMarhq);
    if(dataBlackList.length > 5){
        $('.BlacklistRepositoryMember').html($('.BlacklistRepositoryMember').html()+$('.BlacklistRepositoryMember').html());
        $('.BlacklistRepositoryMember').css('top', '0');
        var tblTop = 0;
        var speedhq = 100; // 数值越大越慢
        var outerHeight = $('.BlacklistRepositoryMember').find("tr").outerHeight();
        function Marqueehq(){
            if(tblTop <= -outerHeight*dataBlackList.length){
                tblTop = 0;
            } else {
                tblTop -= 1;
            }
            $('.BlacklistRepositoryMember').css('top', tblTop+'px');
        }
    
        MyMarhq = setInterval(Marqueehq,speedhq);
    
        // 鼠标移上去取消事件
        $(".BlacklistRepositoryMember").hover(function (){
            clearInterval(MyMarhq);
        },function (){
            clearInterval(MyMarhq);
            MyMarhq = setInterval(Marqueehq,speedhq);
        })
    
    }
}