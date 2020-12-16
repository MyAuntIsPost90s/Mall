package mall.common.util;

import javax.servlet.http.HttpServletRequest;

public class FileUtil {

    public static String getRealPathPath(HttpServletRequest request, String path) {
        if (path != null && path.length() > 0 && path.substring(0, 1).equals("/")) {
            path = path.substring(1);
        }

        return request.getServletContext().getRealPath("/") + path;
    }
}
