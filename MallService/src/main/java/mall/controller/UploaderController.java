package mall.controller;

import com.ch.web.gateway.request.RequestFile;
import com.ch.web.gateway.session.SessionHolder;
import mall.common.Constant;
import mall.common.util.FileUtil;
import mall.uimodel.LayUIEditImg;
import mall.util.RandomNum;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("uploader")
public class UploaderController {
    /**
     * 上传文件
     *
     * @param basePath
     */
    @ResponseBody
    @RequestMapping("upload")
    public void upload(HttpServletRequest request, String basePath) throws IOException {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		RequestFile requestFile = sessionHolder.getRequestFile();
		if (requestFile == null || requestFile.isEmpty()) {
			sessionHolder.fail("操作失败");
			return;
		}
		String path = basePath + RandomNum.getLGID() + ".png";
		File file = new File(FileUtil.getRealPathPath(request, path));
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		MultipartFile multipartFile = requestFile.getFile();
		Thumbnails.of(multipartFile.getInputStream()).size(500, 500).outputQuality(0.7).outputFormat("png")
				.toFile(file);
		sessionHolder.success("操作成功", path, "");
    }

    /**
     * 上传图片（主要给富文本编辑器使用）在这里面成功和失败的code相反
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping("uploadImg")
    public void uploadImg(HttpServletRequest request) throws IOException {
        SessionHolder sessionHolder = SessionHolder.currentSessionHolder();
		RequestFile requestFile = sessionHolder.getRequestFile();
		if (requestFile == null || requestFile.isEmpty()) {
			sessionHolder.success("操作失败");
			return;
		}
		String path = Constant.EDITIMG_URL + RandomNum.getLGID() + ".png";
		MultipartFile multipartFile = requestFile.getFile();
		Thumbnails.of(multipartFile.getInputStream()).size(500, 500).outputQuality(0.7).outputFormat("png")
				.toFile(new File(FileUtil.getRealPathPath(request, path)));
		sessionHolder.fail(new LayUIEditImg(
				"http://" + request.getServerName() + ":" + request.getServerPort() + Constant.URLHEAD + path));
    }
}
