package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.commands.helper.IndexExtractor;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.AnswerService;
import com.javaweb.model.services.TaskService;
import com.javaweb.model.services.impl.AnswerServiceImpl;
import com.javaweb.model.services.impl.TaskServiceImpl;
import com.javaweb.util.Attributes;
import com.javaweb.util.Pages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Pages.INTERNAL_SERVER_ERROR_PAGE;

/**
 * @author Andrii Chernysh on 04-Feb-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class GetTutorConcreteTestCommand extends AbstractCommandWrapper<Test> {
    private TaskService taskService = TaskServiceImpl.getInstance();
    private AnswerService answerService = AnswerServiceImpl.getInstance();
    private IndexExtractor indexExtractor = IndexExtractor.getInstance();

    public GetTutorConcreteTestCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Test test = getDataFromRequest(request);
        writeSpecificDataToRequest(request, test);

        return Pages.CONCRETE_TUTOR_TEST_PAGE;
    }

    @Override
    protected Test getDataFromRequest(HttpServletRequest request) {
        int testId = indexExtractor.extractLastPartUriIndexFromRequest(request);

        return new Test.Builder()
                .setId(testId)
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Test test) {
        List<Task> tasks = getTasksWithAnswersForTest(test);
        request.setAttribute(Attributes.TASKS, tasks);
    }

    private List<Task> getTasksWithAnswersForTest(Test test) {
        List<Task> tasks = taskService.getAllTasksForTest(test);
        for (Task task : tasks) {
            List<Answer> answers = answerService.getListOfAnswersForTask(task);
            task.setAnswers(answers);
        }
        return tasks;
    }
}
