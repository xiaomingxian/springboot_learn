package com.mybatis_plus.pojo;

import lombok.Data;
import org.activiti.engine.impl.bpmn.data.IOSpecification;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ProcessDefinitionSelf implements Serializable {
    private String name;
    private String description;
    private String key;
    private int version;
    private String category;
    private String deploymentId;
    private String resourceName;
    private String tenantId = "";
    private Integer historyLevel;
    private String diagramResourceName;
    private boolean isGraphicalNotationDefined;
    private Map<String, Object> variables;
    private boolean hasStartFormKey;
    private int suspensionState;
    private boolean isIdentityLinksInitialized;
    private List<IdentityLinkEntity> definitionIdentityLinkEntities;
    private IOSpecification ioSpecification;
    private String engineVersion;

}
