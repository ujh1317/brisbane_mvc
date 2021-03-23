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
			
			 data="<h3>��ŷȦ������ �� ���� ü�ᱹ�� �� ����</h3><br>"
			         +"<h4>��ŷȦ������ �� ���� ü�ᱹ�� �� ����</h4>"
			         + "�츮����� ���� 23�� ���� �� ������ ��ŷȦ������ ���� �� 1�� ������ û�ⱳ������(YMS) ������ ü���ϰ� �ֽ��ϴ�.<br>"
			         + "�츮 û����� �״�����, ��������, �븸, ����ũ, ����, ���⿡, ������, ���Ϸ���, ����Ʈ����, �̽���, <br>"
			         + "��Ż����,�Ϻ�, ü��, ĥ��, ĳ����, ��������, ������, �밡��, ȣ��, ȫ��, ������, ������, <br>"
			         + "�Ƹ���Ƽ�� ��ŷȦ�����̿� ���� û�ⱳ������(YMS)�� ������ �� �ֽ��ϴ�.<br>"
			         + "���� �̵� ���� û��鵵 �츮 ��ŷȦ������ ������ ������ �� �ֽ��ϴ�.<br>"
			         + "�ܱ��δ� �츮 û����� ���� ����� �����Ͽ� �۷ι� ����� ������ �� �� �ֵ��� <br>"
			         + "��ŷȦ������ ������ Ȯ���� ���� �����Դϴ�.<br><br>"
			         + "<h4>��ŷȦ������ ����</h4> "
			         + "��ŷȦ�����̿� �����ϱ� ���ؼ��� �ش� ���� (�Ǵ� �ѿ����) �Ǵ� �̹α� ��� <br> "
			         + "��ŷȦ������ ���ڸ� ��û�ϼž� �մϴ�.<br>"
			         + "�� ���ڴ� �ش� ���� �� ������ ü���ϴ� ���� ����� ���� �� �� �ִ� ����������ڷμ� <br>"
			         + "�������� ���� ��� ������ ���� �չ������� �ӽ� ����� �� �� �ֵ��� ����ϴ� �����Դϴ�. <br>"
			         + "ü�� ���� �� �������� �䱸�ϴ� ���ڹ߱� ����, ���񼭷�, ��û�Ⱓ ���� �����ϱ� ������ <br>"
			         + "���� �� ������ �����Ͻ� �� �ش� ���� �� ������ ���� ���������� �Ĳ��� ���� ���ñ� �ٶ��ϴ�.<br>";
			
		}else if(menu.equals("2")){
			
			data="<h3>��ŷȦ������ �����ڸ� ���� '�����ൿ��Ģ' 12����</h3><br>"
		               + "<img src=\"/brisbane/imgs/12.jpg\" width=\"400\"/>";
			
		}else if(menu.equals("3")){
			
			 data="<h3>ȣ��</h3>"
		               + "���� : ĵ���� (Canberra)<br>"
		               + "��� : ����<br>"
		               + "ȣ�ִ� �츮����� ���� ���� ��ŷȦ������ ������ ü���� �����Դϴ�.<br>"
		               + "���� ���� �����ӱݰ� ���� ���������� ���� ���� �츮 ��Ȧ������ �����ϰ� �ֽ��ϴ�.<br><br>"
		               + "�� ȣ�� ���� ����<br>"
		               + "�� ȣ�� ���� : http://overseas.mofa.go.kr/au-ko/brd/m_3895/list.do<br>"
		               + "�� ȣ�� ��ġ���ܱ� : http://overseas.mofa.go.kr/au-ko/brd/m_3897/list.do<br>"
		               + "�� ȣ�� ���� : http://overseas.mofa.go.kr/au-ko/brd/m_3902/list.do<br><br>"
		               + "��<br>"
		               + "�� hello��Ȧ ����<br>"
		               + "http://overseas.mofa.go.kr/au-ko/brd/m_3938/list.do<br>"
		               + "��ȣ�ִ��� Ȩ������ �� \"hello ��Ȧ\" ���Ϳ� ���� �ڼ��� �ڷᰡ ���ϵǾ� �ֽ��ϴ�<br><br>"
		               + "�� ��ȣ�ִ��� (A.C.T, South Australia, Western Australia, Tasmania)<br>"
		               + "http://overseas.mofa.go.kr/au-ko/index.do<br>"
		               + "https://www.facebook.com/KoreanEmbassyAustralia/<br><br>"
		               + "�� �ֽõ���ѿ���� (New South Wales, Northern Territory, Queensland)<br>"
		               + "http://overseas.mofa.go.kr/au-sydney-ko/index.do<br>"
		               + "http://www.facebook.com/koreasydney<br><br>"
		               + "�� �ָ���а� (VIC)<br>"
		               + "http://overseas.mofa.go.kr/au-melbourne-ko/index.do<br><br>";
			
		}else if(menu.equals("4")){
			
			 data="<h3>�� ���ڸ� ����</h3><br>"
		               + "�� �Ź� ���ζ� : �ַ� ��, ������� �Ź��� ���α��� ����. <br>"
		               + "�ϰ��� �� ���� �Ź����� ���� ���� ���� ������.<br>"
		               + " Ư�� ����Ͽ� ����Ǵ� �õ�� ����췲���� ���ζ��� ������ ��<br><br>"
		               + "�� �缳����˼��� (Recruitment Agency) : ����ڵ��� ���νŻ��� �����ϸ�, �ַ� �繫���� �˼���. <br>"
		               + "�����ڿ��� ������ �Ұ��Ḧ �䱸���� ����. �Ϻ� �������÷κ��� ���ظ� ���� ��ʵ� ���� �ִٴ� �Ϳ� ������ ��<br><br>"
		               + "�� ���� ���� : ���� ������ ġ���ϸ� �޷ᰡ ���� ����. <br>"
		               + "���ξ��ҿ��� ���� ���, ȣ�� ��ȭ�� ���� ��ȸ�� ���ٴ� ���� ����ؾ� ��.<br>"
		               + "�õ�Ͽ����� ���� �ݿ��� �ֿ� ���� ���ҿ��� 4~5���� ���� ��Ȱ ������ ���� �� ����<br><br>"
		               + "�� �ֿ� ���� ������Ʈ<br>"
		               + "- ��긮����: http://www.sunbrisbane.com<br>"
		               + "- �����Ű���: http://www.qldvision.com.au<br>"
		               + "- ȣ�ֱ����: http://www.hojugiltara.com<br>"
		               + "- ȣ�ֽ�ī��: http://www.hojusky.com<br>"
		               + "- ȣ�ָ���: http://www.hojumadang.com<br>"
		               + "- ��ȣ�Ϻ�: http://hanhodaily.com<br>"
		               + "- �ѱ��Ź�: http://koreanherald.com.au<br>"
		               + "- �ڸ��� ������: http://www.koreantoday.com.au<br><br>"
		               + "�� �̷¼��� 100���� ���� ������ 1�������� �ǵ���� ���� �� �ִٴ� ����� ���� ������ ������ ���� ������ <br>"
		               + "�ε帮�� �ʴ� ���� ������ ������ �ʴ´ٴ� ����� ����� ��.<br>"
		               + "�� ��κ� �̸��Ϸ� �̷¼� �ۺ�<br>"
		               + "�� ������ ��ȭ ���ͺ� Ȥ�� 1 ��1 ���ͺ䰡 ����<br><br>"
		               + "�� ���� ��������Ʈ<br>"
		               + "- www.jobsearch.gov.au<br>"
		               + "- www.gumtree.com.au"
		               + "- www.backpackerjobboard.com.au<br>"
		               + "- www.careerone.com.au<br>"
		               + "- www.jobsearch.com.au<br>"
		               + "- www.jobsjobsjobs.com.au<br>"
		               + "- www.seek.com.au<br>"
		               + "- https://www.hojunara.com/index.php (ȣ�� ���� Ŀ�´�Ƽ)<br>"
		               + "- http://cafe336.daum.net/_c21_/home?grpid=ZZGF (�۽� ���� ī��)<br>";
			
		}
		
		request.setAttribute("data", data);
		
		return "/member/main.jsp";
	}

}
