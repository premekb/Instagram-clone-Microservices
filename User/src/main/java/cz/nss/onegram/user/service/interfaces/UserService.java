package cz.nss.onegram.user.service.interfaces;

import cz.nss.onegram.user.model.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User findById(int id);
    public User findByEmail(String email);
    public void persist(User user);
    public void persist(OAuth2User user);
    public User getCurrentUser();
    public User followUser(int userToFollow_id);
    public void unFollowUser(int userToUnFollow_id);
}
