package main.java.services.impl;

import main.java.data.UserRepository;
import main.java.model.User;
import main.java.services.SessionService;

//@Service
public class SessionServiceImpl implements SessionService {
	UserRepository userRepository;
	


    // *****************
    @Override
	public  User getCurrentUser(String username){
    	/*
    	String username = ((UserDetails) ((UsernamePasswordAuthenticationToken) 
    		principal).getPrincipal()).getUsername();*/
        return userRepository.findByUserName(username);
    }
	
	@Override
	public boolean validateEmail(String email){
		// validate email syntax
		if(userRepository.findUserByEmail(email)==null){ // must send email...
			return true;
		}
		return false;
		
	}
	
	@Override
	public User getUser(String username){
		return userRepository.findByUserName(username);
	}

	@Override
	public User signUp(String username, String email, String password,
			String passwordConfirm) {
	       if (password.equals(passwordConfirm)) {
	            User existingUser = userRepository.findByUserName(username);
	            if (existingUser == null) {
	                User user = new User(username,password,email); // encode password .
	                String id = userRepository.save(user);
	                return userRepository.findByUserName(id);
	            } else {
	                return null;
	            }
	        }

	        return null;
	}
}