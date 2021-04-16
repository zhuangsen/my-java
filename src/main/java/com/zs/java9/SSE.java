package com.zs.java9;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author madison
 * @description
 * @date 2020/12/5 2:17 下午
 */
@WebServlet("/SSE")
public class SSE extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SSE() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/event-stream");
        resp.setCharacterEncoding("utf-8");

        for (int i = 0; i < 5; i++) {
            // 指定事件标识
            resp.getWriter().write("event:me\n");
            // data: + 数据 + 2个回车
            resp.getWriter().write("data:" + i + "\n\n");
            resp.getWriter().flush();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
