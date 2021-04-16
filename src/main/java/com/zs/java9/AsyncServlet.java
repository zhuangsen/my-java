package com.zs.java9;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author madison
 * @description
 * @date 2020/12/5 1:54 下午
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/AsyncServlet"})
public class AsyncServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long t1 = System.currentTimeMillis();

        // 开启异步
        AsyncContext asyncContext = req.startAsync();

        // 执行业务代码
        CompletableFuture.runAsync(() -> doSomeThing(asyncContext, asyncContext.getRequest(), asyncContext.getResponse()));

        System.out.println("async use:" + (System.currentTimeMillis() - t1));
    }

    private void doSomeThing(AsyncContext asyncContext, ServletRequest request, ServletResponse response) {
        // 模拟耗时操作
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            response.getWriter().append("done");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 业务代码处理完毕, 通知结束
        asyncContext.complete();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
