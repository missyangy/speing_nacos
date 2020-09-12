package com.nacos.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.nacos.common.BodyReaderHttpServletRequestWrapper;
import com.nacos.common.HttpHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CecFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(CecFilter.class);



    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String ext = request.getParameter("ext");
        String uri = request.getServletPath();
        setResponseContentType(response);
        if ("/v1.0.0/query_token.do".equals(uri) || "/v1.0.0/query_token".equals(uri)
                || "/nari/v1.0.0/query_token.do".equals(uri) || "/nari/v1.0.0/query_token".equals(uri)) {
            chain.doFilter(req, resp);
            return;
        }
        if ("t".equals(ext)) {
            chain.doFilter(req, resp);
            return;
        }
        if (!"post".equalsIgnoreCase(request.getMethod())) {
            printErrorMessage(response, "wrong method");
            return;
        }
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        String body = HttpHelper.getBodyString(requestWrapper);
        JSONObject jsDt = JSON.parseObject(body);
        String authorization = request.getHeader("Authorization");
        if ( StringUtils.isBlank(authorization)) {
            handleResult(response, 0, "", "", "", "");
            return;
        }
        chain.doFilter(requestWrapper, resp);
    }



    private void printErrorMessage(ServletResponse resp, String message) {
        log.info(message);
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw;
        try {
            pw = resp.getWriter();
            pw.write(message);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleResult(ServletResponse resp, int ret, String MsgKey, String data, String key, String sig) {
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw;
        try {
            pw = resp.getWriter();
            Map<String, Object> map = new HashMap<>();
            map.put("Ret", ret);
            map.put("Msg", MsgKey);
            map.put("Data", data);
            String json = new Gson().toJson(map);
            pw.write(json);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init(FilterConfig arg0) {

    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis() / 1000;
        System.out.println(time - new Long(1488331192));


            HashMap<String, String> map = new HashMap<>(5);
            map.put("TimeStamp", "20200113172159");
            map.put("Seq", "0001");
            map.put("OperatorID", "321895837");
            map.put("Data", "+JUjUmfHbihLkWrKmPP3OJjdvBHqXxQj/gZB+Lm7JXm+o/hykCfaQ8H26jW96VVyGfGBA0uYeULBKdH2/3ZI1wn2Tuonb82ilCWgHXrrKPs=");
            map.put("Sig", "29171BF846B964ABFCE1D1F814DE41CC");

            System.out.println(JSON.toJSONString(map));
        
    }

    public static void setResponseContentType(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
//        response.addHeader("Expires", "0"); // Proxies.
//        response.addHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT"); // HTTP 1.1
//        response.addHeader("Cache-Control", "no-cache"); //HTTP 1.1
//        response.addHeader("Cache-Control", "private"); // HTTP 1.1
//        response.addHeader("Cache-Control", "no-store"); // HTTP 1.1
//        response.addHeader("Cache-Control", "must-revalidate"); // HTTP 1.1
//        response.addHeader("Cache-Control", "max-stale=0"); // HTTP 1.1
//        response.addHeader("Cache-Control", "post-check=0"); // HTTP 1.1
//        response.addHeader("Cache-Control", "pre-check=0"); // HTTP 1.1
//        response.addHeader("Pragma", "no-cache"); // HTTP 1.1
//        response.addHeader("Access-Control-Allow-Credentials", "true");
    }
}
