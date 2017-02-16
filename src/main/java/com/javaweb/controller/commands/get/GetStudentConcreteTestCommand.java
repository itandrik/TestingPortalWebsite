package com.javaweb.controller.commands.get;

import com.javaweb.controller.commands.AbstractCommandWrapper;
import com.javaweb.controller.commands.helper.IndexExtractor;
import com.javaweb.model.entity.Answer;
import com.javaweb.model.entity.Test;
import com.javaweb.model.entity.task.Task;
import com.javaweb.model.services.AnswerService;
import com.javaweb.model.services.TaskService;
import com.javaweb.model.services.TestService;
import com.javaweb.model.services.impl.AnswerServiceImpl;
import com.javaweb.model.services.impl.TaskServiceImpl;
import com.javaweb.model.services.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.javaweb.util.Attributes.CONCRETE_TEST;
import static com.javaweb.util.Attributes.TASKS;
import static com.javaweb.util.Pages.CONCRETE_STUDENT_TEST_PAGE;
import static com.javaweb.util.Pages.INTERNAL_SERVER_ERROR_PAGE;

/**
 * @author Andrii Chernysh on 26-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class GetStudentConcreteTestCommand extends AbstractCommandWrapper<Test> {
    private final TestService testService = TestServiceImpl.getInstance();
    private final TaskService taskService = TaskServiceImpl.getInstance();
    private final AnswerService answerService = AnswerServiceImpl.getInstance();
    private IndexExtractor indexExtractor = IndexExtractor.getInstance();

    public GetStudentConcreteTestCommand() {
        super(INTERNAL_SERVER_ERROR_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Test test = getDataFromRequest(request);

        List<Task> tasks = getTasksWithAnswersForTest(test);
        request.getSession().setAttribute(TASKS, tasks);

        writeSpecificDataToRequest(request,test);

        return CONCRETE_STUDENT_TEST_PAGE;
    }

    @Override
    protected Test getDataFromRequest(HttpServletRequest request) {
        int testId = indexExtractor.extractLastPartUriIndexFromRequest(request);
        return testService.getTestById(testId);
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, Test test) {
        test.setDurationTimeInMinutes(getTimeDurationInSeconds(test));
        request.getSession().setAttribute(CONCRETE_TEST, test);
    }

    private List<Task> getTasksWithAnswersForTest(Test test) {
        List<Task> tasks = taskService.getAllTasksForTest(test);
        for (Task task : tasks) {
            List<Answer> answers = answerService.getListOfAnswersForTask(task);
            task.setAnswers(answers);
        }
        return tasks;
    }

    private int getTimeDurationInSeconds(Test test) {
        return test.getDurationTimeInMinutes() * 60;
    }
}
