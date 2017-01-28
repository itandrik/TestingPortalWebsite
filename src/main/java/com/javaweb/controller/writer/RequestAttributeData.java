package com.javaweb.controller.writer;

/**
 * @author Andrii Chernysh on 28-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class RequestAttributeData {
    private String attributeKey;
    private Object attributeData;

    public RequestAttributeData(String attributeKey, Object attributeData) {
        this.attributeKey = attributeKey;
        this.attributeData = attributeData;
    }

    public RequestAttributeData setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
        return this;
    }

    public RequestAttributeData setAttributeData(Object attributeData) {
        this.attributeData = attributeData;
        return this;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public Object getAttributeData() {
        return attributeData;
    }
}
