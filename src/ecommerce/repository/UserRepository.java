package ecommerce.repository;

import java.util.*;

import ecommerce.model.User;

public class UserRepository {
	private Map<Long, User> users = new HashMap<>();

	public void save(User user) {
		users.put(user.getId(), user);
	}

	public User findById(Long id) {
		return users.get(id);
	}

	public User findByEmail(String email) {
		for (User user : users.values()) {
			if (user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
		}
		return null;
	}

	public List<User> findAll() {
		return new ArrayList<User>(users.values());
	}

}