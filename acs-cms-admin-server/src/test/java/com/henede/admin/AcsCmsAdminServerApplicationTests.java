package com.henede.admin;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcsCmsAdminServerApplicationTests {
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Test
	public void contextLoads() {
		ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey("myProcess_1",System.currentTimeMillis()+"");
		System.out.println(processInstance);
		Task task = this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		System.out.println(task);
		this.taskService.complete(task.getId());
		task = this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
		System.out.println(task);
	}

}
