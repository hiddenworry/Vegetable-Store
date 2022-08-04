/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import static javax.mail.Flags.Flag.USER;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.UserDTO;

/**
 *
 * @author ADMIN
 */
public class AuthenFillter implements Filter {

    private static final boolean debug = true;
    private static final String LOGIN_PAGE = "error.jsp";

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private final List<String> USER_RESOURCE;
    private final List<String> ADMIN_RESOURCE;
    private final List<String> NON_AUTHEN_RESOURCE;

    public AuthenFillter() {
        NON_AUTHEN_RESOURCE = new ArrayList<>();
        NON_AUTHEN_RESOURCE.add("login.jsp");
        NON_AUTHEN_RESOURCE.add("MainController");
        NON_AUTHEN_RESOURCE.add("error.jsp");
        NON_AUTHEN_RESOURCE.add("LoginController");
        NON_AUTHEN_RESOURCE.add("GoogleLoginController");
        NON_AUTHEN_RESOURCE.add(".js");
        NON_AUTHEN_RESOURCE.add(".css");
        NON_AUTHEN_RESOURCE.add(".jpg");
        NON_AUTHEN_RESOURCE.add(".gif");
        NON_AUTHEN_RESOURCE.add(".png");

        //user
        USER_RESOURCE = new ArrayList<>();
        //USER_RESOURCE.add("login.jsp");
        USER_RESOURCE.add("MainController");
        USER_RESOURCE.add("LoginController");
        USER_RESOURCE.add("GoogleLoginController");
        USER_RESOURCE.add("user.jsp");
        USER_RESOURCE.add("error.jsp");
        USER_RESOURCE.add("AddToCartController");
        USER_RESOURCE.add("EditCartController");
        USER_RESOURCE.add("RemoveCartController");
        USER_RESOURCE.add("SearchController");
        USER_RESOURCE.add("PaypalController");
        USER_RESOURCE.add("ReviewPayment");
        USER_RESOURCE.add("CartConfirmController");
        USER_RESOURCE.add("CheckOutController");
        USER_RESOURCE.add("confirm.jsp");
        USER_RESOURCE.add("viewcart.jsp");
        USER_RESOURCE.add("success.jsp");
        USER_RESOURCE.add("LogOutController");

        // admin
        ADMIN_RESOURCE = new ArrayList<>();
        ADMIN_RESOURCE.add("login.jsp");
        ADMIN_RESOURCE.add("MainController");
        ADMIN_RESOURCE.add("LoginController");
        ADMIN_RESOURCE.add("admin.jsp");
        ADMIN_RESOURCE.add("DeleteController");
        ADMIN_RESOURCE.add("UpdateController");
        ADMIN_RESOURCE.add("CreateProductController");
        ADMIN_RESOURCE.add("creaProduct.jsp");
        ADMIN_RESOURCE.add("SearchController");
        ADMIN_RESOURCE.add("LogOutController");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFillter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFillter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String uri = req.getRequestURI();
            boolean checkContain = false;
            for (String value : NON_AUTHEN_RESOURCE) {
                if (uri.contains(value)) {
                    checkContain = true;
                    break;
                }
            }
            if (checkContain) {
                chain.doFilter(request, response);

            } else {
                int index = uri.lastIndexOf("/");
                String resource = uri.substring(index + 1);
                HttpSession session = req.getSession();
                if (session == null || session.getAttribute("USER") == null) {
                    res.sendRedirect(LOGIN_PAGE);
                    return;
                } else {
                    // Phan quyen
                    UserDTO user = (UserDTO) session.getAttribute("USER");

                    if (user.isAdmin() && ADMIN_RESOURCE.contains(resource)) {
                        chain.doFilter(request, response);

                    } else if (!user.isAdmin() && USER_RESOURCE.contains(resource)) {
                        chain.doFilter(request, response);

                    } else {
                        res.sendRedirect(LOGIN_PAGE);

                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthenFillter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFillter()");
        }
        StringBuffer sb = new StringBuffer("AuthenFillter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
