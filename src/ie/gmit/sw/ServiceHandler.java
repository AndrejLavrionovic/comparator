package ie.gmit.sw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceHandler extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String remoteHost = null;
	private static long jobNumber = 0;
	private Numberable num;

	private Requestable request;
	private static RequestQueue queue = new RequestQueue();
	private static Results results = new Results();
	private JobPool jobPool;

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Initialise some request varuables with the submitted form info. These are local to this method and thread safe...
		String algorithm = req.getParameter("cmbAlgorithm");
		String s = req.getParameter("txtS");
		String t = req.getParameter("txtT");
		String taskNumber = req.getParameter("frmTaskNumber");


		out.print("<html><head><title>Distributed Systems Assignment</title>");		
		out.print("</head>");		
		out.print("<body>");
		
		
		
		//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		// Section is organising request to comparator
		// 1) jobNumber generating
		// 2) creating request object
		// 3) Put request into queue
		// 4) Check is there anything in the queue?
		//    and if there is pass the request to the comparator via RMI
		// 5) Place result into hashmap
		// 6) If result is ready (it is in the hashmap) display it.
		
		
		
		// get jab number (1)
		num = new JobNumberGenerator();
		num.generateNumber();
		jobNumber = num.getJobNumber();
		
		System.out.println("generated number is => " + jobNumber);
		
		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			
			// Object carrying request data for compare (2)
			request = new Request(s, t, algorithm, taskNumber);
			
			//Add job to in-queue (3)
			queue.addRequest(request);
			
			
		}else{
			//Check out-queue for finished job
		}
		
		if(!queue.isQueueEmpty()){
			jobPool = new JobPool();
			jobPool.compareStrings(queue, results);
		}
		//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		
		
		
		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");
		
		out.print("<font color=\"#993333\"><b>");
		out.print("RMI Server is located at " + remoteHost);
		out.print("<br>Algorithm: " + algorithm);		
		out.print("<br>String <i>s</i> : " + s);
		out.print("<br>String <i>t</i> : " + t);
		out.print("<br>This servlet should only be responsible for handling client request and returning responses. Everything else should be handled by different objects.");
		out.print("Note that any variables declared inside this doGet() method are thread safe. Anything defined at a class level is shared between HTTP requests.");				
		out.print("</b></font>");

		out.print("<P> Next Steps:");	
		out.print("<OL>");
		out.print("<LI>Generate a big random number to use a a job number, or just increment a static long variable declared at a class level, e.g. jobNumber.");	
		out.print("<LI>Create some type of an object from the request variables and jobNumber.");	
		out.print("<LI>Add the message request object to a LinkedList or BlockingQueue (the IN-queue)");			
		out.print("<LI>Return the jobNumber to the client web browser with a wait interval using <meta http-equiv=\"refresh\" content=\"10\">. The content=\"10\" will wait for 10s.");	
		out.print("<LI>Have some process check the LinkedList or BlockingQueue for message requests.");	
		out.print("<LI>Poll a message request from the front of the queue and make an RMI call to the String Comparison Service.");			
		out.print("<LI>Get the <i>Resultator</i> (a stub that is returned IMMEDIATELY by the remote method) and add it to a Map (the OUT-queue) using the jobNumber as the key and the <i>Resultator</i> as a value.");	
		out.print("<LI>Return the result of the string comparison to the client next time a request for the jobNumber is received and the <i>Resultator</i> returns true for the method <i>isComplete().</i>");	
		out.print("</OL>");	
		
		out.print("<form name=\"frmRequestDetails\">");
		out.print("<input name=\"cmbAlgorithm\" type=\"hidden\" value=\"" + algorithm + "\">");
		out.print("<input name=\"txtS\" type=\"hidden\" value=\"" + s + "\">");
		out.print("<input name=\"txtT\" type=\"hidden\" value=\"" + t + "\">");
		out.print("<input name=\"frmTaskNumber\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");
		
		out.print("<h3>Request queue</h3>");	
		out.print("<OL>");
		BlockingQueue<Requestable> q = queue.getQueue();
		if(q.size() > 0){
			for(Requestable item : q){
				out.print("<LI>Job Number ==> " + item.getJobNumber() + "</LI>");
			}
		}
		else{
			out.print("<LI>Queue is empty now.</LI>");
		}
		out.print("</OL>");
		
		out.print("</body>");	
		out.print("</html>");	
		
		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmRequestDetails.submit();\", 10000);");
		out.print("</script>");
		
		if(!results.isResultsEmpty() && results.isResultReady(taskNumber)){
			out.print("<h3>Request is here:</h3>");
			out.print("<p style=\"font-size: 36px; font-weight: bold\">" + results.takeResult(taskNumber).getResult() + "</p>");
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}
