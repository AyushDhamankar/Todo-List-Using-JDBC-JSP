package com.company.todo;

import com.company.data.TodoStruct;

public interface Todo {
	boolean add(int user_id, String task, String due_date);
	TodoStruct[] getpending(int user_id);
	TodoStruct[] getcompleted(int user_id);
	boolean delete(int task_id);
	boolean complete(int task_id);
	boolean update(int task_id, String task);
}
