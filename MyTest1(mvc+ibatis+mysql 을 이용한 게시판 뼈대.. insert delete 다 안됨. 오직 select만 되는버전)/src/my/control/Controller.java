package my.control;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Severity;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import my.model.Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map clsMap=new HashMap();
    private Map jspMap=new HashMap(); 
	
   

	public void init(ServletConfig config) throws ServletException {
		try
		{
			
			//xml코드를 읽어서 파싱(경로명)
			String path=config.getInitParameter("xmlPath");
			//파서기를 생성
			//파서를 한다(XML코드를 트리형태로 메모리에 저장)
			//필요한 데이터를 얻어온다
			//***XML은 계층형 (항상 최상위 태그확인)
		
			/*
			 *   DocumentBuilder : XML을 파서
			 *   Document : XML파서된 메모리의 주소
			 *   Element(Node) : XML의 각 태그
			 *   NodeList : 같은 이름을 가지고 있는 태그들의 집합
			 *   Text : 태그와 태그의 값을 제어
			 *   Attr : 태그안에 있는 속성
			 */
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // 객체생성
			DocumentBuilder db=dbf.newDocumentBuilder();  // 파서하여 db라는 객체 생성
			//파서
			Document doc=db.parse(new File(path));			//파서된 메모리의 주소를 갖고옴.
			//최상위 태그
			Element rootElem=doc.getDocumentElement();
			//필요한 태그를 모아서 처리
			NodeList list=rootElem.getElementsByTagName("bean");		// bean 태그들을 list 형태로 가져옴.
			
			//applicationContext.xml 의 id와   , class, jsp 로 이루어진 코드가 있다.
			// id값을 받으면 class를 실행하고 jsp로 포워딩해주는것
			for(int i=0;i<list.getLength();i++)
			{
				Element elem=(Element)list.item(i);
				String id=elem.getAttribute("id");
				String cls=elem.getAttribute("class");
				String jsp=elem.getAttribute("jsp");
				
				//저장
				jspMap.put(id, jsp);
				
				//클래스를 메모리 할당하고 주소만 저장
				Class clsName=Class.forName(cls);
				
				Object obj=clsName.newInstance();
				// newInstance() : 싱글턴 패턴
				clsMap.put(id, obj);
			}
			
		}catch(Exception ex){System.out.println("파싱쪽 에러" + ex.getMessage());}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	protected void doProcess(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		try
		{
			//요청 확인
			String cmd=request.getRequestURI();
			cmd=cmd.substring(request.getContextPath().length()+1,
					cmd.lastIndexOf('.'));
			/*
			 *   http://localhost:8080/IInogardproject/list.do
			 *                        ----------------------- getRequestURI()
			 *                        -------------------getContextPath()
			 */
			Model model=(Model)clsMap.get(cmd);
			String result=model.execute(request);
			
			String jsp="";
			if(result.equals("inogard"))
			{
				jsp=(String)jspMap.get(cmd);
				System.out.println("모델에서 'inogard 받음'");
			}
			else
			{
				jsp="view/error.jsp";
			}
			
			//실행된 JSP파일을 읽어온다(포워딩)
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			System.out.println("리퀘스트 디스패처 완료");
			rd.forward(request, response);
			System.out.println("포워딩 완료");
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
