package action.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.*;
import boardmysql.*;
public class MainAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String menu=request.getParameter("menu");
		if(menu==null){
			menu="1";
		}
		
		String data="";
		
		if(menu.equals("1")){
			
			 data="<h3>워킹홀리데이 의 협정 체결국가 및 지역</h3><br>"
			         +"<h4>워킹홀리데이 의 협정 체결국가 및 지역</h4>"
			         + "우리나라는 현재 23개 국가 및 지역과 워킹홀리데이 협정 및 1개 국가와 청년교류제도(YMS) 협정을 체결하고 있습니다.<br>"
			         + "우리 청년들은 네덜란드, 뉴질랜드, 대만, 덴마크, 독일, 벨기에, 스웨덴, 아일랜드, 오스트리아, 이스라엘, <br>"
			         + "이탈리아,일본, 체코, 칠레, 캐나다, 포르투갈, 프랑스, 헝가리, 호주, 홍콩, 스페인, 폴란드, <br>"
			         + "아르헨티나 워킹홀리데이와 영국 청년교류제도(YMS)에 참여할 수 있습니다.<br>"
			         + "또한 이들 국가 청년들도 우리 워킹홀리데이 제도에 참여할 수 있습니다.<br>"
			         + "외교부는 우리 청년들이 많은 나라로 진출하여 글로벌 인재로 성장해 갈 수 있도록 <br>"
			         + "워킹홀리데이 제도를 확대해 나갈 예정입니다.<br><br>"
			         + "<h4>워킹홀리데이 비자</h4> "
			         + "워킹홀리데이에 참가하기 위해서는 해당 대사관 (또는 총영사관) 또는 이민국 등에서 <br> "
			         + "워킹홀리데이 비자를 신청하셔야 합니다.<br>"
			         + "이 비자는 해당 국가 및 지역에 체류하는 동안 여행과 일을 할 수 있는 관광취업비자로서 <br>"
			         + "현지에서 관광 경비 조달을 위해 합법적으로 임시 취업을 할 수 있도록 허용하는 비자입니다. <br>"
			         + "체결 국가 및 지역별로 요구하는 비자발급 조건, 구비서류, 신청기간 등이 상이하기 때문에 <br>"
			         + "국가 및 지역을 선택하신 후 해당 국가 및 지역에 대한 비자정보를 꼼꼼히 살펴 보시기 바랍니다.<br>";
			
		}else if(menu.equals("2")){
			
			data="<h3>워킹홀리데이 참가자를 위한 '안전행동수칙' 12가지</h3><br>"
		               + "<img src=\"/brisbane/imgs/12.jpg\" width=\"400\"/>";
			
		}else if(menu.equals("3")){
			
			 data="<h3>호주</h3>"
		               + "수도 : 캔버라 (Canberra)<br>"
		               + "언어 : 영어<br>"
		               + "호주는 우리나라와 가장 먼저 워킹홀리데이 협정을 체결한 국가입니다.<br>"
		               + "현재 높은 최저임금과 쿼터 무제한으로 가장 많은 우리 워홀러들이 참가하고 있습니다.<br><br>"
		               + "▣ 호주 국가 정보<br>"
		               + "ㅇ 호주 개관 : http://overseas.mofa.go.kr/au-ko/brd/m_3895/list.do<br>"
		               + "ㅇ 호주 정치·외교 : http://overseas.mofa.go.kr/au-ko/brd/m_3897/list.do<br>"
		               + "ㅇ 호주 경제 : http://overseas.mofa.go.kr/au-ko/brd/m_3902/list.do<br><br>"
		               + "▣<br>"
		               + "ㅇ hello워홀 센터<br>"
		               + "http://overseas.mofa.go.kr/au-ko/brd/m_3938/list.do<br>"
		               + "주호주대사관 홈페이지 내 \"hello 워홀\" 센터에 보다 자세한 자료가 수록되어 있습니다<br><br>"
		               + "ㅇ 주호주대사관 (A.C.T, South Australia, Western Australia, Tasmania)<br>"
		               + "http://overseas.mofa.go.kr/au-ko/index.do<br>"
		               + "https://www.facebook.com/KoreanEmbassyAustralia/<br><br>"
		               + "ㅇ 주시드니총영사관 (New South Wales, Northern Territory, Queensland)<br>"
		               + "http://overseas.mofa.go.kr/au-sydney-ko/index.do<br>"
		               + "http://www.facebook.com/koreasydney<br><br>"
		               + "ㅇ 주멜번분관 (VIC)<br>"
		               + "http://overseas.mofa.go.kr/au-melbourne-ko/index.do<br><br>";
			
		}else if(menu.equals("4")){
			
			 data="<h3>▣ 일자리 정보</h3><br>"
		               + "ㅇ 신문 구인란 : 주로 수, 토요일자 신문에 구인광고가 나옴. <br>"
		               + "일간지 및 지역 신문에서 구인 구직 광고를 제공함.<br>"
		               + " 특히 토요일에 발행되는 시드니 모닝헤럴드의 구인란을 참조할 것<br><br>"
		               + "ㅇ 사설취업알선소 (Recruitment Agency) : 취업자들의 개인신상을 관리하며, 주로 사무직을 알선함. <br>"
		               + "구직자에게 별도의 소개료를 요구하지 않음. 일부 에이전시로부터 피해를 당한 사례도 종종 있다는 것에 주의할 것<br><br>"
		               + "ㅇ 한인 업소 : 구직 경쟁이 치열하며 급료가 적은 편임. <br>"
		               + "한인업소에서 일할 경우, 호주 문화를 접할 기회가 적다는 것을 고려해야 함.<br>"
		               + "시드니에서는 매주 금요일 주요 한인 업소에서 4~5종의 교민 생활 잡지를 구할 수 있음<br><br>"
		               + "ㅇ 주요 한인 웹사이트<br>"
		               + "- 썬브리스번: http://www.sunbrisbane.com<br>"
		               + "- 비전매거진: http://www.qldvision.com.au<br>"
		               + "- 호주길따라: http://www.hojugiltara.com<br>"
		               + "- 호주스카이: http://www.hojusky.com<br>"
		               + "- 호주마당: http://www.hojumadang.com<br>"
		               + "- 한호일보: http://hanhodaily.com<br>"
		               + "- 한국신문: http://koreanherald.com.au<br>"
		               + "- 코리안 투데이: http://www.koreantoday.com.au<br><br>"
		               + "ㅇ 이력서를 100군데 정도 돌리면 1군데에서 피드백을 받을 수 있다는 농담이 있을 정도로 구직이 쉽지 않으나 <br>"
		               + "두드리지 않는 문은 스스로 열리지 않는다는 사실을 명심할 것.<br>"
		               + "ㅇ 대부분 이메일로 이력서 송부<br>"
		               + "ㅇ 면접은 전화 인터뷰 혹은 1 대1 인터뷰가 많음<br><br>"
		               + "ㅇ 현지 구직사이트<br>"
		               + "- www.jobsearch.gov.au<br>"
		               + "- www.gumtree.com.au"
		               + "- www.backpackerjobboard.com.au<br>"
		               + "- www.careerone.com.au<br>"
		               + "- www.jobsearch.com.au<br>"
		               + "- www.jobsjobsjobs.com.au<br>"
		               + "- www.seek.com.au<br>"
		               + "- https://www.hojunara.com/index.php (호주 한인 커뮤니티)<br>"
		               + "- http://cafe336.daum.net/_c21_/home?grpid=ZZGF (퍼스 한인 카페)<br>";
			
		}
		
		request.setAttribute("data", data);
		
		return "/member/main.jsp";
	}

}
