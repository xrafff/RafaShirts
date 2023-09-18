package viewhelper;

import dominio.EntidadeDominio;
import util.Resultado;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IViewHelper {
    public EntidadeDominio get1(HttpServletRequest req, HttpServletResponse resp);
    public void set(HttpServletRequest req, HttpServletResponse resp, Resultado resultado) throws ServletException, IOException;
	EntidadeDominio get(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp);
}
