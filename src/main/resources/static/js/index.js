
/**
 * 各要素の高さ取得。
 */
let divNationwidePostion = $(".div-nationwide").offset().top;
let divPrefecturesPostion = $(".div-prefectures").offset().top;
let divPrefecturesSeparatePostion = $(".div-prefectures-separate").offset().top;
/*
 * サーバーからデータを取得。
 */
let paramObj = JSON.parse($("#apiDataBean").val());
/**
 * 初期表示
 * @returns
 */
$(function() {	
    //グラフ情報設定
    setGraphData(createGraphObj("main-graph", ["感染者数", "退院・療養解除数", "死者数"], "人数", paramObj.apiNationwideDataListBean.apiNationwideDataList, 0, "horizontalBar"));
    setGraphData(createGraphObj("prefectures-graph-hokkaido-tohoku",paramObj.apiPrefecturesDataListBean.hokkaidoTohokuNameList, "人数", paramObj.apiPrefecturesDataListBean.hokkaidoTohokuCountList, 0, "horizontalBar"));
    setGraphData(createGraphObj("prefectures-graph-Kanto", paramObj.apiPrefecturesDataListBean.kantoNameList, "人数", paramObj.apiPrefecturesDataListBean.kantoCountList, 0, "horizontalBar"));
    setGraphData(createGraphObj("prefectures-graph-chubu",paramObj.apiPrefecturesDataListBean.chubuNameList, "人数",paramObj.apiPrefecturesDataListBean.chubuCountList, 0, "horizontalBar"));
    setGraphData(createGraphObj("prefectures-graph-kinki",paramObj.apiPrefecturesDataListBean.kinkiNameList, "人数", paramObj.apiPrefecturesDataListBean.kinkiCountList, 0, "horizontalBar"));
    setGraphData(createGraphObj("prefectures-graph-chugoku-shikoku",paramObj.apiPrefecturesDataListBean.chugokushikokuNameList, "人数",paramObj.apiPrefecturesDataListBean.chugokushikokuCountList, 0, "horizontalBar"));
    setGraphData(createGraphObj("prefectures-graph-kyushu-okinawa",paramObj.apiPrefecturesDataListBean.kyushuNameList, "人数",paramObj.apiPrefecturesDataListBean.kyushuCountList, 0, "horizontalBar"));
    setGraphData(createGraphObj("infectes-person-percentage", ["感染者数", "退院・療養解除数", "死者数"], "人数", paramObj.apiPrefecturesDetailListData.otherDataList, 0, "horizontalBar"));
    setGraphData(createGraphObj("accept-HospitalBed", ["総確保病床数",  "使用済病床数"], "病床数", paramObj.apiPrefecturesDetailListData.hospitalBedCountList, 0, "horizontalBar"));
    setGraphData(createGraphObj("accept-InnBed", ["総確保宿泊部屋数",  "使用済宿泊部屋数"], "部屋数", paramObj.apiPrefecturesDetailListData.innCountList, 0, "horizontalBar"));
    //news情報を設定。
    setNewsApiInfo(paramObj);
    //要素の高さを指定。
    setElementHeight();
    //要素をFadeInする。
    elementFadeIn();
    //newsをFadeOut・Inする。
    fadeInOutNewsInfo();
    $("html,body").animate({scrollTop:0},'1');
});

/**
 * 都道府県検索実施
 * @returns
 */
$(".selectBox").change(function(){
	let jsonForm ={
			userName:$(".selectBox").val(),
	}
	
	$.ajax({
		type:"GET",
		url : "/covid-19/search",
		dataType:'json',
		async: true,
		data:jsonForm,
		contentType : "application/json" ,
		acriptCharset:'utf-8',
		success: function(data) {
			destroyChart();
			setGraphData(createGraphObj("infectes-person-percentage", ["感染者数", "退院・療養解除数", "死者数"], "人数", data.apiPrefecturesDetailListData.otherDataList, 0, "horizontalBar"));
			setGraphData(createGraphObj("accept-HospitalBed", ["総確保病床数",  "使用済病床数"], "病床数", data.apiPrefecturesDetailListData.hospitalBedCountList, 0, "horizontalBar"));
			setGraphData(createGraphObj("accept-InnBed", ["総確保宿泊部屋数",  "使用済宿泊部屋数"], "部屋数", data.apiPrefecturesDetailListData.innCountList, 0, "horizontalBar"));
			$("#div-new-Infected-person-count").text(data.apiPrefecturesDetailListData.todayCasesCount+"人");
        },
			
	});
});

/**
 * サイドバー押下時
 */
$("[class^='sidebar-content']").on("click",function(){
	let className = $(this).attr("class");
	let positon = 0;
	if(className == "sidebar-content1"){
		positon = divNationwidePostion - 170;
	}else if (className == "sidebar-content2"){
		positon = divPrefecturesPostion  - 170;
	}else{
		positon = divPrefecturesSeparatePostion  - 170 ;
	}
	$("html,body").animate({scrollTop:positon},200);
});

/**
 * リンク押下時
 * @returns
 */
$("[class^='newsInfo']").on("click",function(){
	 event.preventDefault();
	window.open($(this).attr('href'), '_blank');
});

/**
 * スクロール時の動き
 */
$(window).scroll(function(){
	let nowScrollpositon = 	$(window).scrollTop();
	if(nowScrollpositon +500  >= divPrefecturesPostion){
		$(".div-prefectures").fadeIn();
	}
	if(nowScrollpositon +500  >= divPrefecturesSeparatePostion){
		$(".div-prefectures-separate").fadeIn();
	}
});

//グラフのインスタンス情報を保持。
let chartArray = [];
/**
 * グラフにデータ投入。
 * @param graphData
 * @returns
 */
function createGraph(graphData) {
	
	let chart = new Chart(graphData.ctx, {
		type: graphData.type,
		data: graphData.data,
		options: graphData.options
	});
	chartArray.push(chart);
	
}

/**
 * インプットデータを編集。
 * @param dataObj
 * @returns
 */
function setGraphData(dataObj) {
    let ctx = document.getElementById(dataObj.id);

    let data = {
        labels: dataObj.labels,
        datasets: [{
            label: dataObj.label,
            data: dataObj.data,
            backgroundColor: 'rgba(255, 100, 100, 1)'
        }]
    };

    let options = {
        scales: {
            xAxes: [{
                ticks: {
                    min: dataObj.min
                }
            }]
        }
    };

    let graphData = {
        ctx: ctx,
        data: data,
        options: options,
        type: dataObj.type
    }

    createGraph(graphData);
}

/**
 * グラフのインプットオブジェクトを作成。
 * @param id
 * @param labels
 * @param title
 * @param data
 * @param min
 * @param type
 * @returns
 */
function createGraphObj(id, labels, title, data, min, type) {
    let dataObj = {
        id: id,
        labels: labels,
        label: title,
        data: data,
        min: min,
        type: type
    }
    return dataObj;
}

/**
 * グラフのインスタンスを解放。
 * @returns
 */
function destroyChart() {
	chartArray[7].destroy();
	chartArray[8].destroy();
	chartArray[9].destroy();
	chartArray.splice(9,1);
	chartArray.splice(8,1);
	chartArray.splice(7,1);
	}

/**
 * news情報を設定。
 * @param paramObj
 * @returns
 */
function setNewsApiInfo(paramObj){
	let newsApiInfoArray = [];
	newsApiInfoArray = paramObj.apiNewsData.channel.item;

	$(".div-catprion .span-href a").each(function(index,element){
		let random = Math.floor(Math.random() * newsApiInfoArray.length);
		$(this).text(newsApiInfoArray[random].title);
		$(this).attr("href",newsApiInfoArray[random].link);
	});
}

/**
 * 要素の高さを指定
 * @returns
 */
function setElementHeight(){
	let mainHeight = $("html").height() -$(".div-header").height(); 
	$("main").css("height",mainHeight);
	$(".div-side-bar").css("height",mainHeight);
}

/**
 * 画面要素をFadeIn Hideする。
 * @returns
 */
function elementFadeIn(){
	$("div").each(function(){
		let elementClass = $(this).attr("class");
		if(elementClass == "div-prefectures" || elementClass == "div-prefectures-separate"){
			$("."+elementClass).hide();
		}else{
			$("."+elementClass).hide().fadeIn("slow");
		}
	});
}

/**
 * Newsを入れ替える。
 * @returns
 */
function fadeInOutNewsInfo(){
	let newsFade = function(){
		//newsのタイトルの横幅を取得
		let spanWidth = $(".span-href a").width()+10;
		//既存のnewsを画面外へ移動。
		$(".span-href").animate({
			 'marginLeft': '-'+spanWidth
		},2000,function(){
			//画面外へ移動したnewsの要素削除
			$(this).remove();
			//要素を作成・
			$(".div-catprion").append('<span class="span-href"><a href="" class="newsInfo" rel="noopener noreferrer"></a></span>')
			$(".span-href").css({
				"visibility":"hidden"
			});
			setNewsApiInfo(paramObj);
			//作成した要素を画面外へ移動。
			$(".span-href").animate({
				'marginLeft': "1920px"
			},function(){
				//画面外の要素を画面内に戻す。
				$(".span-href").css({
					"visibility":"visible"
				});
				$(".span-href").animate({
					'marginLeft': "0px"
				},2000)
			})
		});
	}
	setInterval(newsFade,10000);
	
}
