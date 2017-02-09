package com.javaweb.controller.commands;

import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.task.AnswerType;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.AnswerService;
import com.javaweb.model.services.TaskService;
import com.javaweb.model.services.impl.AnswerServiceImpl;
import com.javaweb.model.services.impl.TaskServiceImpl;
import com.javaweb.util.Parameters;
import com.javaweb.util.Paths;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import static com.javaweb.model.entity.task.AnswerType.MANY_ANSWERS;
import static com.javaweb.model.entity.task.AnswerType.ONE_ANSWER;
import static com.javaweb.util.Parameters.*;
import static com.javaweb.util.Paths.REDIRECTED;

/**
 * @author Andrii Chernysh on 04-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class PostAddTaskCommand implements Command {
    private TaskService taskService = TaskServiceImpl.getInstance();
    private AnswerService answerService = AnswerServiceImpl.getInstance();
    private Logger logger = Logger.getLogger(PostAddTaskCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Task task = extractTaskFromRequest(request);

        int taskId = taskService.addTask(task);
        answerService.addAnswersForTask(task.getAnswers(),taskId);

        response.sendRedirect(request.getRequestURI().replaceAll(Paths.ADD_TASK,""));
        return REDIRECTED;
    }

    private Task extractTaskFromRequest(HttpServletRequest request){
        String question = request.getParameter(QUESTION_PARAMETER);
        String explanation = request.getParameter(EXPLANATION_PARAMETER);

        List<Answer> answers = extractListOfAnswersFromRequest(request);
        String requestAnswerType = request.getParameter(ANSWER_TYPE_PARAMETER);
        AnswerType answerType = requestAnswerType == null ? MANY_ANSWERS: ONE_ANSWER;
        return new Task.Builder()
                .setQuestion(question)
                .setExplanation(explanation)
                .setAnswers(answers)
                .setAnswerType(answerType)
                .build();
    }

    private List<Answer> extractListOfAnswersFromRequest(HttpServletRequest request) {
        List<Answer> answers = new ArrayList<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        List<String> correctAnswers = Arrays.asList(request.getParameterValues(Parameters.ANSWER_PARAMETER));
        while(parameterNames.hasMoreElements()){
            String nextParameter = parameterNames.nextElement();
            if(nextParameter.startsWith(Parameters.ANSWER_TEXT_PARAMETER)){
                boolean isCorrectAnswer = correctAnswers.contains(nextParameter);
                Answer answer = new Answer.Builder()
                        .setAnswerText(request.getParameter(nextParameter))
                        .setIsCorrect(isCorrectAnswer)
                        .build();
                answers.add(answer);
            }
        }
        return answers;
    }
}
