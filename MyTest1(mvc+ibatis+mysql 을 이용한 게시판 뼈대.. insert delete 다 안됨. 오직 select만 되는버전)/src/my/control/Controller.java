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
			
			//xml�ڵ带 �о �Ľ�(��θ�)
			String path=config.getInitParameter("xmlPath");
			//�ļ��⸦ ����
			//�ļ��� �Ѵ�(XML�ڵ带 Ʈ�����·� �޸𸮿� ����)
			//�ʿ��� �����͸� ���´�
			//***XML�� ������ (�׻� �ֻ��� �±�Ȯ��)
		
			/*
			 *   DocumentBuilder : XML�� �ļ�
			 *   Document : XML�ļ��� �޸��� �ּ�
			 *   Element(Node) : XML�� �� �±�
			 *   NodeList : ���� �̸��� ������ �ִ� �±׵��� ����
			 *   Text : �±׿� �±��� ���� ����
			 *   Attr : �±׾ȿ� �ִ� �Ӽ�
			 */
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // ��ü����
			DocumentBuilder db=dbf.newDocumentBuilder();  // �ļ��Ͽ� db��� ��ü ����
			//�ļ�
			Document doc=db.parse(new File(path));			//�ļ��� �޸��� �ּҸ� �����.
			//�ֻ��� �±�
			Element rootElem=doc.getDocumentElement();
			//�ʿ��� �±׸� ��Ƽ� ó��
			NodeList list=rootElem.getElementsByTagName("bean");		// bean �±׵��� list ���·� ������.
			
			//applicationContext.xml �� id��   , class, jsp �� �̷���� �ڵ尡 �ִ�.
			// id���� ������ class�� �����ϰ� jsp�� ���������ִ°�
			for(int i=0;i<list.getLength();i++)
			{
				Element elem=(Element)list.item(i);
				String id=elem.getAttribute("id");
				String cls=elem.getAttribute("class");
				String jsp=elem.getAttribute("jsp");
				
				//����
				jspMap.put(id, jsp);
				
				//Ŭ������ �޸� �Ҵ��ϰ� �ּҸ� ����
				Class clsName=Class.forName(cls);
				
				Object obj=clsName.newInstance();
				// newInstance() : �̱��� ����
				clsMap.put(id, obj);
			}
			
		}catch(Exception ex){System.out.println("�Ľ��� ����" + ex.getMessage());}
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
			//��û Ȯ��
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
				System.out.println("�𵨿��� 'inogard ����'");
			}
			else
			{
				jsp="view/error.jsp";
			}
			
			//����� JSP������ �о�´�(������)
			RequestDispatcher rd = request.getRequestDispatcher(jsp);
			System.out.println("������Ʈ ����ó �Ϸ�");
			rd.forward(request, response);
			System.out.println("������ �Ϸ�");
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
}
