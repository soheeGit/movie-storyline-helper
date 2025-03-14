package org.example.moviestorylinehelper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.moviestorylinehelper.service.TogetherService;

import java.io.IOException;

@WebServlet ("/")
public class RootController extends Controller {

    final static TogetherService togetherService = TogetherService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        view(req, resp, "index");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idea = req.getParameter("idea");
        String genre = req.getParameter("genre");
        HttpSession session = req.getSession();
        if(idea == null || idea.isEmpty() || genre == null || genre.isEmpty()) {
            session.setAttribute("message", "질문이 비어 있습니다.");
            view(req, resp, "index");
            return;
        }
        try {
            String basePrompt = togetherService.useBaseModel(genre, idea);
            String title = basePrompt.split("\\*\\*제목\\*\\*:")[1].split("\\n\\n")[0].trim();
            String storyline = basePrompt.split("\\*\\*줄거리\\*\\*:")[1].trim();
            session.setAttribute("title", title);
            session.setAttribute("storyline", storyline);
            String deepAnswer = togetherService.useReasoning(basePrompt);
            System.out.println(deepAnswer);
            String[] deepAnswerArr = deepAnswer.trim().split("</think>");
            String thinking = deepAnswerArr[0].split("<think>")[1].trim();
            String reasoning = deepAnswerArr[1].trim();
            session.setAttribute("reasoning", reasoning);
            session.setAttribute("thinking", thinking);
            String image = togetherService.useImage(basePrompt);
            session.setAttribute("image", image);
            resp.sendRedirect(req.getContextPath() + "/result");
        } catch (JsonProcessingException e) {
            session.setAttribute("message", "오류 발생: 영화 생성에 실패했습니다.");
            view(req, resp, "index");
        }
    }
}
