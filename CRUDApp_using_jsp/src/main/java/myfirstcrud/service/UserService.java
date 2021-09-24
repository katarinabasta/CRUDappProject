package myfirstcrud.service;

import java.util.List;

import myfirstcrud.model.User;

public interface UserService {
	 
	public List listAllUsers();
	 
	 public void addUser(User user);
	 
	 public void updateUser(User user);
	 
	 public void deleteUser(int id);
	 
//	 public User findUserById(int id);

}
