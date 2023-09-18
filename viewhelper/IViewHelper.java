package viewhelper;

import dominio.EntidadeDominio;
import util.Resultado;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IViewHelper {
    public EntidadeDominio get(HttpServletRequest req, HttpServletResponse resp);
    public void set(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) throws ServletException, IOException;
}
