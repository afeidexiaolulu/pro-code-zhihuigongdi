$(document).ready(function() {
    // 风速指标分析
    AnalysisWindSpeedIndicators();

    // 空气质量等级
    AirQualityGrade();

    // 2.5趋势分析
    PM255TrendAnalysis();

    // 噪音指数
    NoiseIndexAnalysis();

    // 项目等级分析
    ProjectLevelAnalysis();
})

function AnalysisWindSpeedIndicators(){
    // 初始化echarts图表(折线图)
    let AnalysisWindSpeedIndicators = echarts.init(document.getElementById('Analysis-Wind-Speed-Indicators'));

    // 数据加载完之前先显示一段简单的loading动画
    AnalysisWindSpeedIndicators.showLoading();

    // 加载图表
    AnalysisWindSpeedIndicators.setOption({
        tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}%'
        },
        xAxis: {
            type: 'category',
            data:['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', ' Dec'],
            axisLabel: {
                show: true,
                interval: 0,
                margin: 12,
                textStyle: {
                    color: "#ffffff",
                    fontSize:'10'
                },
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#ffffff'
                }
            }
        },
        yAxis: {
            type: 'value',
            splitLine: {
                show: true,
                lineStyle: {
                    color: ['#ffffff'],
                    type: 'dotted'
                }
            },
            axisLabel: {
                interval: 0,
                margin: 10,
                textStyle: {
                    color: "#ffffff",
                    fontSize:'10'
                },
                formatter: function (value) {
                    var texts = [];
                    if(value==5){
                        texts.push('5%');
                    }
                    else if (value <=10) {
                        texts.push('10%');
                    }
                    else if (value<= 15) {
                        texts.push('15%');
                    }
                    else if(value<= 20){
                        texts.push('20%');
                    }
                    else if(value<= 25){
                        texts.push('25%');
                    }
                    else if(value<= 30){
                        texts.push('30%');
                    }
                    return texts;
                }
            },
            axisLine: {
                lineStyle: {
                    color: '#ffffff'
                }
            }
        },
        series: [
            {
                // name: '',
                type: 'line',
                itemStyle:{ normal:{color:'#33c15f'} },
                data: [3, 4, 6, 8, 12, 15, 17, 16, 14, 10, 7, 5 ]
            },
            {
                // name: '',
                type: 'line',
                itemStyle:{ normal:{color:'#128cfc'} },
                data: [7, 6, 9, 14, 18, 22, 25, 26, 24, 18, 14, 10 ]
            }
        ]

        
    });

    // 关闭动画
    AnalysisWindSpeedIndicators.hideLoading();
}


function AirQualityGrade(){
    // 初始化echarts图表(折线图)
    let AirQualityGrade = echarts.init(document.getElementById('Air-Quality-Grade'));

    // 数据加载完之前先显示一段简单的loading动画
    AirQualityGrade.showLoading();

    // 加载图表
    AirQualityGrade.setOption({
        grid:{
            x : 100,
            y : 'top',
            padding: 20
        },
        legend: {
            orient: 'vertical',
            right:  '20%',
            bottom: 'center',
            textStyle: {
                color: function (params){
                    var colorList = ['#43bcff','#40e4e7','#c600ff','#3b6fff','#f4844d','#bea30a'];
                    return colorList[params.dataIndex];
                },
                paddingBottom:150,
            
            },
            data:['一级','二级','三级','四级','五级','六级'],
            itemWidth: 15,
            itemHeight: 15,
            itemGap: 25
        },
        color:['#43bcff','#40e4e7','#c600ff','#3b6fff','#f4844d','#bea30a'],
        series : [{
            type:'pie',
            radius : [60,80],
            center: ['40%','50%'],
            label: {
                normal: {
                    formatter: '{c}%',
                    textStyle: {
                        color: '#FFFFFF'
                    }
                }
            },
            labelLine:{  
                normal:{  
                     length:50,
                     lineStyle: {
                        color: "#FFFFFF"
                     }
                },
           },
            data:[
                {value:37, name:'一级'},
                {value:23, name:'二级'},
                {value:12.9, name:'三级'},
                {value:12.3, name:'四级'},
                {value:11.2, name:'五级'},
                {value:3.6, name:'六级'},
            ]
        }]
    });

    // 关闭动画
    AirQualityGrade.hideLoading();
}

function PM255TrendAnalysis(){
    // 初始化echarts图表(折线图)
    let PM255TrendAnalysis = echarts.init(document.getElementById('PM255-Trend-Analysis'));

    // 数据加载完之前先显示一段简单的loading动画
    PM255TrendAnalysis.showLoading();

    // 加载图表
    PM255TrendAnalysis.setOption({
        title: {
            left: 'center',
            bottom: 0,
            text: 'PM2.5趋势分析',
            textStyle: {
                color: "#9fceff",
                fontSize:'14',
                fontFamily:'PingFangSC_Light'
            }
        },
        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        xAxis: [
            {
                type: 'category',
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: '#9fceff'
                    }
                },
                data : ['25','30','35','40','45','50','55']
            },
            {
                type: 'category',
                axisLine: {
                    onZero: true,
                    lineStyle: {
                        color: '#9fceff'
                    }
                },
                data: []
            }
        ],
        yAxis: [
            {
                type: 'value',
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: ['#1037ae'],
                        type: 'solid'
                    }
                },
                axisLabel: {
                    interval: 0,
                    margin: 10,
                    textStyle: {
                        color: "#9fceff",
                        fontSize:'8'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#9fceff'
                    }
                }
            }
        ],
        series: [
            {
                name:'',
                type:'line',
                smooth: true,
                xAxisIndex: 1,
                itemStyle:{ normal:{color:'#1889a7'} },
                data: [5.9, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
            },
            {
                name:'',
                type:'line',
                smooth: true,
                itemStyle:{ normal:{color:'#a63ba3'} },
                data: [5.9, 18.7, 48.3, 69.2, 231.6, 46.6, 55.4, 18.4, 10.3, 0.7]
            }
        ]
    });

    // 关闭动画
    PM255TrendAnalysis.hideLoading();
}

function NoiseIndexAnalysis(){
    // 初始化echarts图表(折线图)
    let NoiseIndexAnalysis = echarts.init(document.getElementById('Noise-Index-Analysis'));

    // 数据加载完之前先显示一段简单的loading动画
    NoiseIndexAnalysis.showLoading();

    // 加载图表
    NoiseIndexAnalysis.setOption({
        title: {
            left: 'center',
            bottom: 0,
            text: '噪音指数分析',
            textStyle: {
                color: "#9fceff",
                fontSize:'14',
                fontFamily:'PingFangSC_Light'
            }
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#9fceff'
                }
            }
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                axisLine: {
                    lineStyle: {
                        color: '#9fceff'
                    }
                },
                data : ['25','30','35','40','45','50','55']
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitLine: {
                    show: true,
                    lineStyle: {
                        color: ['#1037ae'],
                        type: 'solid'
                    }
                },
                axisLabel: {
                    interval: 0,
                    margin: 10,
                    textStyle: {
                        color: "#9fceff",
                        fontSize:'8'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: '#9fceff'
                    }
                }
            }
        ],
        series : [
            {
                // name:'',
                type:'line',
                smooth: true,
                areaStyle: {},
                itemStyle:{
                    normal: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0, color: 'rgba(20,210,193,1)'
                            }, {
                                offset: 0.5, color: 'rgba(20,210,193,0.2)'
                            }, {
                                offset: 1, color: 'rgba(20,210,193,0)'
                            }]
                        }
                    }
                },
                data:[170, 150, 190, 210, 215, 220, 240]
            },
            {
                // name:'',
                type:'line',
                smooth: true,
                areaStyle: {},
                itemStyle:{
                    normal: {
                        color: {
                            type: 'linear',
                            x: 0,
                            y: 0,
                            x2: 0,
                            y2: 1,
                            colorStops: [{
                                offset: 0, color: 'rgba(20,54,185,1)'
                            }, {
                                offset: 0.5, color: 'rgba(20,54,185,0.2)'
                            }, {
                                offset: 1, color: 'rgba(20,54,185,0)'
                            }]
                        }
                    }
                },
                data:[150, 150, 140, 160, 170, 180, 190]
            }
        ]
    });

    // 关闭动画
    NoiseIndexAnalysis.hideLoading();
}

function ProjectLevelAnalysis(){
    // 初始化echarts图表(折线图)
    let ProjectLevelAnalysis = echarts.init(document.getElementById('Project-Level-Analysis'));

    // 数据加载完之前先显示一段简单的loading动画
    ProjectLevelAnalysis.showLoading();

    // 加载图表
    ProjectLevelAnalysis.setOption({
        grid:{
            x : 'center',
            y : 'top',
        },
        legend: {
            x : 'center',
            y : 'bottom',
            padding: 10,
            textStyle: {
            color: function (params){
                    var colorList = ['#43bcff','#40e4e7','#3b6fff'];
                    return colorList[params.dataIndex];
                }
            },
            itemWidth: 13,
            itemHeight: 13,
            data:['一级','二级','三级']
        },
        color:['#43bcff','#40e4e7','#3b6fff'],
        series : [{
            type:'pie',
            radius : [50,70],
            center: ['50%','50%'],
            label: {
                normal: {
                    formatter: '{c}%',
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
            data:[
                {value:50, name:'一级'},
                {value:30, name:'二级'},
                {value:20, name:'三级'}
            ]
        }]
    });

    // 关闭动画
    ProjectLevelAnalysis.hideLoading();
}