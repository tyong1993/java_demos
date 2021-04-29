package filter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Locale;

public class FreeMarkerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        String ftl = (String)servletRequest.getAttribute("ftl");
        if(ftl != null && !"".equals(ftl)){
            Configuration freemarker = (Configuration)servletRequest.getServletContext().getAttribute("freemarker");
            Template temp = freemarker.getTemplate(ftl+".ftl");
            //tomcat默认使用GBK编码,要输出utf-8的字节流在实例化OutputStreamWriter的时候得指定编码格式为utf-8
            ServletOutputStream outputStream = servletResponse.getOutputStream();
            OutputStreamWriter out = new OutputStreamWriter(outputStream,"utf-8");
            try {
                temp.process(servletRequest.getAttribute("model"),out);
                out.flush();
                out.close();
            } catch (TemplateException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void destroy() {

    }
}
