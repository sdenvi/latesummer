package com.latesummer.utils.http;

import io.netty.util.concurrent.FastThreadLocal;

public class WebContext
{
  private static final FastThreadLocal<WebContext> fastThreadLocal = new FastThreadLocal();
  //private static Blade blade;
  private static String contextPath;
  private Request request;
  private Response response;

  public WebContext(Request request, Response response)
  {
    this.request = request;
    this.response = response;
  }

  public static void set(WebContext webContext) {
    fastThreadLocal.set(webContext);
  }

  public static WebContext get() {
    return (WebContext)fastThreadLocal.get();
  }

  public static void remove() {
    fastThreadLocal.remove();
  }

  public static Request request() {
    WebContext webContext = get();
    return null != webContext ? webContext.request : null;
  }

  public static Response response() {
    WebContext webContext = get();
    return null != webContext ? webContext.response : null;
  }

  /*public static void init(Blade blade_, String contextPath_) {
    blade = blade_;
    contextPath = contextPath_;
  }*/

  /*public static Blade blade() {
    return blade;
  }*/

  public static String contextPath() {
    return contextPath;
  }
}