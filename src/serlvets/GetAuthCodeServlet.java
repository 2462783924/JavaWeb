package serlvets;


import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "GetAuthCodeServlet", value = "/GetAuthCodeServlet")
public class GetAuthCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetAuthCodeServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authCode = AuthCode.getAuthCode();
        request.getSession().setAttribute("authCode", authCode);

        try {
            ImageIO.write(AuthCode.getAuthImg(authCode), "JPEG", response.getOutputStream());
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
