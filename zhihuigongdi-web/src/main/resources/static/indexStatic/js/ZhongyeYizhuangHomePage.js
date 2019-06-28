$(document).ready(function() {

    var galleryThumbs = new Swiper('.gallery-thumbs', {
        spaceBetween: 5,
        slidesPerView: 3.5,
        freeMode: true,
        watchSlidesVisibility: true,
        watchSlidesProgress: true,
    });
    var galleryTop = new Swiper('.gallery-top', {
        spaceBetween: 5,
        autoplay:true,
        autoplay: {
            delay: 3000,
            stopOnLastSlide: false,
            disableOnInteraction: true,
        },
        loop: true,
        pagination: {
            el: '.swiper-pagination',
            bulletElement : 'li',
            clickable :true,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        thumbs: {
            swiper: galleryThumbs
        }
    });
    
    TotalContractAmount();
    ProportionProjecTime();
})

function TotalContractAmount(){
    // 初始化echarts图表(折线图)
    var ContractAmount = echarts.init(document.getElementById('Total-Contract-Amount'));

    // 数据加载完之前先显示一段简单的loading动画
    ContractAmount.showLoading();

    // 加载图表
    ContractAmount.setOption({
        legend: {
            x : 'center',
            y : 'bottom',
            padding: 10,
            textStyle: {
                color: function (params){
                    var colorList = ['#4B84FC','#33E7EB'];
                    return colorList[params.dataIndex];
                }
            },
            itemWidth: 15,
            itemHeight: 15,
            data:['已回款合同额','未回款合同额']
        },
        color:['#4B84FC','#33E7EB'],
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
                     length:50,
                     lineStyle: {
                        color: "#FFFFFF"
                     }
                },
           },
            data:[
                {value:90, name:'已回款合同额'},
                {value:10, name:'未回款合同额'}
            ]
        }]
    });

    // 关闭动画
    ContractAmount.hideLoading();
}



// 项目用时占比
function ProportionProjecTime(){
    // 初始化echarts图表(折线图)
    let Projecttime = echarts.init(document.getElementById('Proportion-project-time'));

    // 数据加载完之前先显示一段简单的loading动画
    Projecttime.showLoading();

    // 加载图表
    Projecttime.setOption({
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
                    var colorList = ['#02AFFF','#40E4E7'];
                    return colorList[params.dataIndex];
                }
            },
            itemWidth: 15,
            itemHeight: 15,
            data:['剩余用时','已用时']
        },
        color:['#02AFFF','#40E4E7'],
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
                     length:50,
                     lineStyle: {
                        color: "#FFFFFF"
                     }
                },
           },
            data:[
                {value:64, name:'剩余用时'},
                {value:36, name:'已用时'}
            ]
        }]
    });

    // 关闭动画
    Projecttime.hideLoading();
}
