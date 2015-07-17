package my.change;

public class ChangeJSP {
    private static String[] jsp={
    	"default.jsp",
    	"../board/list.jsp",
    	"../board/content.jsp",
    	"../board/insert.jsp",
    	"../board/reply.jsp",
    	"../board/update.jsp",
    	"../board/delete.jsp",
    	"../board/find.jsp",
    	"../member/join.jsp",
    	"../databoard/list.jsp",
    	"../databoard/insert.jsp",
    	"../databoard/delete.jsp"
    };
    public static String change(int mode)
    {
    	return jsp[mode];
    }
}
