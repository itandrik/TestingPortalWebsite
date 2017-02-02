package com.javaweb.view.tags;

import com.javaweb.model.entity.task.Task;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

/**
 * @author Andrii Chernysh on 02-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class CountOfCorrectAnswersTag implements Tag {
    protected PageContext pageContext;

    private String var;
    private Task value;

    @Override
    public int doStartTag() throws JspException {
        pageContext.setAttribute(var, getCountOfCorrectAnswers());
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    @Override
    public void release() {

    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag tag) {

    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setValue(Task value) {
        this.value = value;
    }

    @Override
    public Tag getParent() {
        return null;
    }

    public String getVar() {
        return var;
    }

    public Task getValue() {
        return value;
    }

    public long getCountOfCorrectAnswers(){
        return value.getCountOfCorrectAnswers();
    }
}
