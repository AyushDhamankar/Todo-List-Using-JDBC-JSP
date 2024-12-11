package com.company.auth;

import com.company.data.UserStruct;

public interface UserAuth {
	UserStruct login(String username, String password);
	String register(String username, String password);
}
