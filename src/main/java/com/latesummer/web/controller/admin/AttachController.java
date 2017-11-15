package com.latesummer.web.controller.admin;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.latesummer.web.controller.BaseController;

/**
 * 附件管理
 *Cteate By Jenvi Sue On 2017年11月1日
 */
public class AttachController extends BaseController {

    public static final String CLASSPATH = new File(AttachController.class.getResource("/").getPath()).getPath() + File.separatorChar;

    /*@Inject
    private SiteService siteService;

    *//**
     * 附件页面
     *
     * @param request
     * @param page
     * @param limit
     * @return
     *//*
    @Route(value = "", method = HttpMethod.GET)
    public String index(Request request, @Param(defaultValue = "1") int page,
                        @Param(defaultValue = "12") int limit) {

        Attach       attach     = new Attach();
        Page<Attach> attachPage = attach.page(page, limit);
        request.attribute("attachs", attachPage);
        request.attribute(Types.ATTACH_URL, Commons.site_option(Types.ATTACH_URL, Commons.site_url()));
        request.attribute("max_file_size", TaleConst.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }

    *//**
     * 上传文件接口
     * <p>
     * 返回格式
     *
     * @param request
     * @return
     *//*
    @Route(value = "upload", method = HttpMethod.POST)
    @JSON
    public RestResponse upload(Request request) {

        log.info("UPLOAD DIR = {}", TaleUtils.upDir);

        Users                 users       = this.user();
        Integer               uid         = users.getUid();
        Map<String, FileItem> fileItemMap = request.fileItems();
        Collection<FileItem>  fileItems   = fileItemMap.values();
        List<Attach>          errorFiles  = new ArrayList<>();
        List<Attach>          urls        = new ArrayList<>();
        try {
            fileItems.forEach((FileItem f) -> {
                String fname = f.getFileName();

                if ((f.getLength() / 1024) <= TaleConst.MAX_FILE_SIZE) {
                    String fkey = TaleUtils.getFileKey(fname);

                    String ftype    = f.getContentType().contains("image") ? Types.IMAGE : Types.FILE;
                    String filePath = TaleUtils.upDir + fkey;

                    try {
                        Files.write(Paths.get(filePath), f.getData());
                    } catch (IOException e) {
                        log.error("", e);
                    }

                    Attach attach = new Attach();
                    attach.setFname(fname);
                    attach.setAuthor_id(uid);
                    attach.setFkey(fkey);
                    attach.setFtype(ftype);
                    attach.setCreated(DateKit.nowUnix());
                    attach.save();

                    urls.add(attach);
                    siteService.cleanCache(Types.C_STATISTICS);
                } else {
                    Attach attach = new Attach();
                    attach.setFname(fname);
                    errorFiles.add(attach);
                }
            });
            if (errorFiles.size() > 0) {
                return RestResponse.builder().success(false).payload(errorFiles).build();
            }
            return RestResponse.ok(urls);
        } catch (Exception e) {
            String msg = "文件上传失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return RestResponse.fail(msg);
        }
    }

    @Route(value = "delete")
    @JSON
    public RestResponse delete(@Param Integer id, Request request) {
        try {
            Attach attach = new Attach();
            Attach temp   = attach.find(id);
            if (null == temp) {
                return RestResponse.fail("不存在该附件");
            }
            attach.delete(id);
            siteService.cleanCache(Types.C_STATISTICS);
            String upDir = CLASSPATH.substring(0, CLASSPATH.length() - 1);
            Files.delete(Paths.get(upDir + attach.getFkey()));
            new Logs(LogActions.DEL_ATTACH, attach.getFkey(), request.address(), this.getUid()).save();
        } catch (Exception e) {
            String msg = "附件删除失败";
            if (e instanceof TipException) msg = e.getMessage();
            else log.error(msg, e);
            return RestResponse.fail(msg);
        }
        return RestResponse.ok();
    }*/

}