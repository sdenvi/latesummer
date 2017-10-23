package com.latesummer.utils.http;

import java.beans.ConstructorProperties;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.latesummer.utils.PathUtil;

public class Route
{
  private HttpMethod httpMethod;
  private String path;
  private Object target;
  private Class<?> targetType;
  private Method action;
  private int sort = 2147483647;

  private Map<String, String> pathParams = new HashMap();

  public Route(HttpMethod httpMethod, String path, Class<?> targetType, Method action)
  {
    this.httpMethod = httpMethod;
    this.path = PathUtil.fixPath(path);
    this.targetType = targetType;
    this.action = action;
  }

  public Route(HttpMethod httpMethod, String path, Object target, Class<?> targetType, Method action)
  {
    this.httpMethod = httpMethod;
    this.path = PathUtil.fixPath(path);
    this.target = target;
    this.targetType = targetType;
    this.action = action;
  }

  public HttpMethod getHttpMethod() {
    return this.httpMethod;
  }

  public String getPath() {
    return this.path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Object getTarget() {
    return this.target;
  }

  public void setTarget(Object target) {
    this.target = target;
  }

  public Method getAction() {
    return this.action;
  }

  public Class<?> getTargetType() {
    return this.targetType;
  }

  public Map<String, String> getPathParams() {
    return this.pathParams;
  }

  public void setPathParams(Map<String, String> pathParams) {
    this.pathParams = pathParams;
  }

  public int getSort() {
    return this.sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }

  public String toString()
  {
    return this.httpMethod + "\t" + this.path;
  }

  public static RouteBuilder builder()
  {
    return new RouteBuilder(); } 
  public Route() {  } 
  @ConstructorProperties({"httpMethod", "path", "target", "targetType", "action", "sort", "pathParams"})
  public Route(HttpMethod httpMethod, String path, Object target, Class<?> targetType, Method action, int sort, Map<String, String> pathParams) { this.httpMethod = httpMethod; this.path = path; this.target = target; this.targetType = targetType; this.action = action; this.sort = sort; this.pathParams = pathParams; } 
  public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof Route)) return false; Route other = (Route)o; if (!other.canEqual(this)) return false; Object this$httpMethod = getHttpMethod(); Object other$httpMethod = other.getHttpMethod(); if (this$httpMethod == null ? other$httpMethod != null : !this$httpMethod.equals(other$httpMethod)) return false; Object this$path = getPath(); Object other$path = other.getPath(); if (this$path == null ? other$path != null : !this$path.equals(other$path)) return false; Object this$target = getTarget(); Object other$target = other.getTarget(); if (this$target == null ? other$target != null : !this$target.equals(other$target)) return false; Object this$targetType = getTargetType(); Object other$targetType = other.getTargetType(); if (this$targetType == null ? other$targetType != null : !this$targetType.equals(other$targetType)) return false; Object this$action = getAction(); Object other$action = other.getAction(); if (this$action == null ? other$action != null : !this$action.equals(other$action)) return false; if (getSort() != other.getSort()) return false; Object this$pathParams = getPathParams(); Object other$pathParams = other.getPathParams(); return this$pathParams == null ? other$pathParams == null : this$pathParams.equals(other$pathParams); } 
  protected boolean canEqual(Object other) { return other instanceof Route; } 
  public int hashCode() { int PRIME = 59; int result = 1; Object $httpMethod = getHttpMethod(); result = result * 59 + ($httpMethod == null ? 43 : $httpMethod.hashCode()); Object $path = getPath(); result = result * 59 + ($path == null ? 43 : $path.hashCode()); Object $target = getTarget(); result = result * 59 + ($target == null ? 43 : $target.hashCode()); Object $targetType = getTargetType(); result = result * 59 + ($targetType == null ? 43 : $targetType.hashCode()); Object $action = getAction(); result = result * 59 + ($action == null ? 43 : $action.hashCode()); result = result * 59 + getSort(); Object $pathParams = getPathParams(); result = result * 59 + ($pathParams == null ? 43 : $pathParams.hashCode()); return result;
  }

  public static class RouteBuilder
  {
    private HttpMethod httpMethod;
    private String path;
    private Object target;
    private Class<?> targetType;
    private Method action;
    private int sort;
    private Map<String, String> pathParams;

    public RouteBuilder httpMethod(HttpMethod httpMethod)
    {
      this.httpMethod = httpMethod; return this; } 
    public RouteBuilder path(String path) { this.path = path; return this; } 
    public RouteBuilder target(Object target) { this.target = target; return this; } 
    public RouteBuilder targetType(Class<?> targetType) { this.targetType = targetType; return this; } 
    public RouteBuilder action(Method action) { this.action = action; return this; } 
    public RouteBuilder sort(int sort) { this.sort = sort; return this; } 
    public RouteBuilder pathParams(Map<String, String> pathParams) { this.pathParams = pathParams; return this; } 
    public Route build() { return new Route(this.httpMethod, this.path, this.target, this.targetType, this.action, this.sort, this.pathParams); } 
    public String toString() { return "Route.RouteBuilder(httpMethod=" + this.httpMethod + ", path=" + this.path + ", target=" + this.target + ", targetType=" + this.targetType + ", action=" + this.action + ", sort=" + this.sort + ", pathParams=" + this.pathParams + ")"; }

  }
}