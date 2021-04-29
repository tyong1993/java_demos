package mymvc;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MymvcDispatcherServlet extends HttpServlet {

    HashMap<String,Object> container = new HashMap<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        if(split.length != 4){
            resp.getWriter().write("请求格式错误");
            return;
        }
        String controller = split[2];
        String action = split[3];
        String controllerKey = "mymvc.controller." + controller + "Controller";
        if(!container.containsKey(controllerKey)){
            try {
                Class<?> aClass = Class.forName(controllerKey);
                Object o = aClass.newInstance();
                container.put(controllerKey,o);
            } catch (Throwable e) {
                resp.getWriter().write("控制器不存在");
                return;
            }
        }
        Object controller1 = container.get(controllerKey);
        Object res = null;
        HashMap<String,Object> model = new HashMap<>();
        try {
            Class<?> aClass = Class.forName(controllerKey);
            Method method = aClass.getMethod(action,HttpServletRequest.class,HashMap.class);
            res = method.invoke(controller1, req, model);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            resp.getWriter().write("你访问的方法不存在");
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        OutputStreamWriter out;
        if(res == null){
            resp.setContentType("text/json;charset=utf-8");
            ServletOutputStream outputStream = resp.getOutputStream();
            out = new OutputStreamWriter(outputStream,"utf-8");
            JSONObject jsonObject = JSONObject.fromObject(model);
            String string = jsonObject.toString();
            out.write(string);
        }else if("java.lang.String".equals(res.getClass().getName())){
            ServletOutputStream outputStream = resp.getOutputStream();
            out = new OutputStreamWriter(outputStream,"utf-8");
            //输出模板
            Configuration freemarker = (Configuration)req.getServletContext().getAttribute("freemarker");
            Template temp = freemarker.getTemplate(res+".ftl");
            try {
                temp.process(model,out);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }else{
            ServletOutputStream outputStream = resp.getOutputStream();
            out = new OutputStreamWriter(outputStream,"utf-8");
            out.write("不接受的返回值类型");
        }
        out.flush();
        out.close();
    }
}
