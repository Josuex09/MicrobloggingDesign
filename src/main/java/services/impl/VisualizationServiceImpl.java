package main.java.services.impl;

import java.util.List;

import main.java.data.PostRepository;
import main.java.data.UserRepository;
import main.java.model.Medal;
import main.java.model.Notification;
import main.java.model.Post;
import main.java.model.User;
import main.java.services.VisualizationService;

//@Service
public class VisualizationServiceImpl implements VisualizationService {

	//modifique userId por username
	UserRepository userRepository;
	PostRepository postRepository;
	
	@Override
	public List<User> showFollowers(String username){
		User user = userRepository.findByUserName(username);
		return user.getFollowers();
	}
	
	@Override
	public List<User> showFollowing(String username){
		User user = userRepository.findByUserName(username);
		return user.getFollows();
	}

	@Override
	public List<Medal> showMedals(String username){
		User user = userRepository.findByUserName(username);
		return user.getMedals();
	}

	@Override
	public List<Post> showFeed(String username){
		User user = userRepository.findByUserName(username);
		List<User> follows =  user.getFollows();
		List<Post> feed = postRepository.findPosts(follows); 
		return feed;    // need to filter the results by date.
	}
	
	@Override
	public List<Post> showUserPost(String username){
		User user = userRepository.findByUserName(username);
		return user.getPosts();
	}

	@Override
	public List<Notification> showUserNotifications(String username) {
		User user = userRepository.findByUserName(username);
		return user.getNotifications();
	}


}
