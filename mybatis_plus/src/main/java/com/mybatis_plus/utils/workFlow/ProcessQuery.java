package com.mybatis_plus.utils.workFlow;

import lombok.Data;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProcessQuery {

    protected String deploymentId;
    protected String name;
    protected String nameLike;
    protected String category;
    protected String categoryLike;
    protected String categoryNotEquals;
    protected String key;
    protected String keyLike;
    protected String tenantId;
    protected String tenantIdLike;
    protected boolean withoutTenantId;
    protected String processDefinitionKey;
    protected String processDefinitionKeyLike;
    protected boolean latest;

    @Autowired
    private RepositoryService repositoryService;

    public DeploymentQuery getDeploymentQuery() {
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();


        return deploymentQuery;
    }

}
