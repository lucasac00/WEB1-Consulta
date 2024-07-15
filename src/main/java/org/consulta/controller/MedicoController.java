package org.consulta.controller;

import org.consulta.dao.MedicoDAO;
import org.consulta.domain.Medico;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/medicos/*")
public class MedicoController extends HttpServlet {
    private MedicoDAO medicoDAO = new MedicoDAO();

    @Override
    public void init(){
        medicoDAO = new MedicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Medico medico = (Medico) request.getSession().getAttribute("medico");

    }
}
